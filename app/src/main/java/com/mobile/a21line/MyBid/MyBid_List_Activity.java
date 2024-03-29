package com.mobile.a21line.MyBid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.AddMemoEvent;
import com.mobile.a21line.AddMemoFlag;
import com.mobile.a21line.Bid.Bid_LVAdapter;
import com.mobile.a21line.Bid.Bid_Listitem;
import com.mobile.a21line.Bid.Bid_Popup_Sorting;
import com.mobile.a21line.R;
import com.mobile.a21line.Result.Result_LVAdapter;
import com.mobile.a21line.Result.Result_Listitem;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayout;
import com.omadahealth.github.swipyrefreshlayout.library.SwipyRefreshLayoutDirection;
import com.squareup.otto.Subscribe;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Accepted on 2018-05-14.
 */

public class MyBid_List_Activity extends AppCompatActivity {

    Context mContext;

    View footer;

    Button btn_total;
    Button btn_bid;
    Button btn_result;

    ListView lv_total;
    ListView lv_bidable;
    ListView lv_result;

    Bid_LVAdapter total_adapter;
    Bid_LVAdapter bid_adapter;
    Result_LVAdapter result_adapter;

    ArrayList<Bid_Listitem> total_arraylist;
    ArrayList<Bid_Listitem> bid_arraylist;
    ArrayList<Result_Listitem> result_arraylist;

    ArrayList<MyBid_moveGroup_ListItem> mydocArrayList;


    String SortType = "RegDTime";
    String RegDTime = "0";
    String GroupName;

    String SDate = getMonthAgoDate(1);
    String EDate = getMonthAgoDate(0);

    String GCode;
    int startNum = 0;
    int totalNum = 0;

    int type = 0;
    SwipyRefreshLayout swipyRefreshLayout;

    LinearLayout ll_progressContainer_mybid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mybid_list_activity);
        mContext = getApplicationContext();

        GCode = String.valueOf(getIntent().getIntExtra("GCode", -1));
        GroupName = getIntent().getStringExtra("GName");

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText(GroupName);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);


        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ll_progressContainer_mybid = (LinearLayout)findViewById(R.id.ll_progressContainer_mybid);

        footer= getLayoutInflater().inflate(R.layout.listview_footer,null,false);

        btn_total = findViewById(R.id.btn_total_list_mybid);
        btn_bid = findViewById(R.id.btn_bid_list_mybid);
        btn_result = findViewById(R.id.btn_result_list_mybid);

        lv_total = findViewById(R.id.lv_total_bidlist_mybid);
        lv_bidable = findViewById(R.id.lv_bidable_bidlist_mybid);
        lv_result = findViewById(R.id.lv_result_bidlist_mybid);

        total_arraylist = new ArrayList<>();
        bid_arraylist = new ArrayList<>();
        result_arraylist = new ArrayList<>();

        total_adapter = new Bid_LVAdapter(mContext,total_arraylist, this, true);
        bid_adapter = new Bid_LVAdapter(mContext,bid_arraylist, this, true);
        result_adapter = new Result_LVAdapter(mContext,result_arraylist, this, true);

        lv_total.setAdapter(total_adapter);
        lv_bidable.setAdapter(bid_adapter);
        lv_result.setAdapter(result_adapter);


        btn_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedTotal();
            }
        });

        btn_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedBid();
            }
        });

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedResult();
            }
        });

        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext,Bid_Popup_Sorting.class);
                i.putExtra("SDate", SDate);
                i.putExtra("EDate", EDate);
                i.putExtra("SortType", SortType);
                startActivityForResult(i, 0);
            }
        });

        ((ImageView)findViewById(R.id.iv_scrollup_mybid)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(type == 0){
                    lv_total.smoothScrollToPosition(0);
                }else if (type == 1){
                    lv_bidable.smoothScrollToPosition(0);
                }else{
                    lv_result.smoothScrollToPosition(0);
                }
            }
        });

        swipyRefreshLayout = findViewById(R.id.total_swipy_mybid_list);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if(totalNum > startNum)
                    getMydocBidList();
                swipyRefreshLayout.setRefreshing(false);
            }
        });


        getMydocBidList();

        AddMemoEvent.getInstance().register(this);
    }

    private void clickedTotal()
    {
        type = 0;
        initSearchData();

        swipyRefreshLayout = findViewById(R.id.total_swipy_mybid_list);
        swipyRefreshLayout.setVisibility(View.VISIBLE);
        findViewById(R.id.bidable_swipy_mybid_list).setVisibility(View.GONE);
        findViewById(R.id.result_swipy_mybid_list).setVisibility(View.GONE);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if(totalNum > startNum)
                    getMydocBidList();
                swipyRefreshLayout.setRefreshing(false);
            }
        });

        btn_total.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_total.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_total.setTypeface(null, Typeface.BOLD);
        btn_total.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_bid.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_bid.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_bid.setTypeface(null, Typeface.NORMAL);
        btn_bid.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_result.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_result.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_result.setTypeface(null, Typeface.NORMAL);
        btn_result.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
        getMydocBidList();
    }

    private void clickedBid()
    {
        type = 1;
        initSearchData();

        swipyRefreshLayout = findViewById(R.id.bidable_swipy_mybid_list);
        swipyRefreshLayout.setVisibility(View.VISIBLE);
        findViewById(R.id.total_swipy_mybid_list).setVisibility(View.GONE);
        findViewById(R.id.result_swipy_mybid_list).setVisibility(View.GONE);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if(totalNum > startNum)
                    getMydocBidList();
                swipyRefreshLayout.setRefreshing(false);
            }
        });


        btn_bid.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_bid.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_bid.setTypeface(null, Typeface.BOLD);
        btn_bid.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_total.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_total.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_total.setTypeface(null, Typeface.NORMAL);
        btn_total.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_result.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_result.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_result.setTypeface(null, Typeface.NORMAL);
        btn_result.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
        getMydocBidList();
    }

    private void clickedResult()
    {
        type = 2;
        initSearchData();
        swipyRefreshLayout = findViewById(R.id.result_swipy_mybid_list);
        swipyRefreshLayout.setVisibility(View.VISIBLE);
        findViewById(R.id.total_swipy_mybid_list).setVisibility(View.GONE);
        findViewById(R.id.bidable_swipy_mybid_list).setVisibility(View.GONE);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if(totalNum > startNum)
                    getMydocBidList();
                swipyRefreshLayout.setRefreshing(false);
            }
        });

        btn_result.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_result.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_result.setTypeface(null, Typeface.BOLD);
        btn_result.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_bid.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_bid.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_bid.setTypeface(null, Typeface.NORMAL);
        btn_bid.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_total.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_total.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_total.setTypeface(null, Typeface.NORMAL);
        btn_total.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
        getMydocBidList();
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        AddMemoEvent.getInstance().unregister(this);
    }

    @Subscribe
    public void getPost(AddMemoFlag flag){
        ArrayList arrayList;
        if(type == 0){
            arrayList = total_arraylist;
            Bid_Listitem item = (Bid_Listitem)arrayList.get(flag.getPosition());
            item.setMybidClicked(true);
            if((flag.isMoveGroup() && flag.isAdded()) || !flag.isMoveGroup()){
                item.setHasMemo(flag.isAdded());
            }

            arrayList.set(flag.getPosition(), item);

            total_adapter.notifyDataSetChanged();
        }else if(type == 1){
            arrayList = bid_arraylist;
            Bid_Listitem item = (Bid_Listitem)arrayList.get(flag.getPosition());
            item.setMybidClicked(true);
            if((flag.isMoveGroup() && flag.isAdded()) || !flag.isMoveGroup()){
                item.setHasMemo(flag.isAdded());
            }

            arrayList.set(flag.getPosition(), item);

            bid_adapter.notifyDataSetChanged();
        }else{
            arrayList = result_arraylist;
            Result_Listitem item = (Result_Listitem)arrayList.get(flag.getPosition());
            item.setMybidClicked(true);
            if((flag.isMoveGroup() && flag.isAdded()) || !flag.isMoveGroup()){
                item.setHasMemo(flag.isAdded());
            }

            arrayList.set(flag.getPosition(), item);

            result_adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 0){
            if(intent != null) {
                SDate = (intent.hasExtra("SDate")) ? intent.getStringExtra("SDate") : SDate;
                EDate = (intent.hasExtra("EDate")) ? intent.getStringExtra("EDate") : EDate;
                SortType = intent.getStringExtra("SortType");
                total_arraylist.clear();
                bid_arraylist.clear();
                result_arraylist.clear();
                totalNum = 0;
                startNum = 0;
                getMydocBidList();
            }
        }else if(requestCode == 1){

        }else if(requestCode == 3){
            if(resultCode == RESULT_OK){
                int position = intent.getIntExtra("Position", -1);
                if(intent.getBooleanExtra("isDelete", false) || !GCode.equals("-1")) {
                    if (position >= 0) {
                        if (type == 0) {
                            total_arraylist.remove(position);
                            total_adapter.notifyDataSetChanged();
                        } else if (type == 1) {
                            bid_arraylist.remove(position);
                            bid_adapter.notifyDataSetChanged();
                        } else {
                            result_arraylist.remove(position);
                            result_adapter.notifyDataSetChanged();
                        }
                    }
                }
            }
        }
    }

    public void getMydocBidList(){

        ll_progressContainer_mybid.setVisibility(View.VISIBLE);
        lv_bidable.setVisibility(View.GONE);
        lv_total.setVisibility(View.GONE);
        lv_result.setVisibility(View.GONE);

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/getMydocBidList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    Log.d("JsonArray = ", obj.toString());

                    String footerString;
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);


                        if(i == obj.length() -1 && totalNum == 0){
                            totalNum = o.getInt("TotalCnt");

                        }else {
                            if(RegDTime.equals("0")){
                                RegDTime = parseDateTimeToDate(o.getString("RegDTime"), true);
                                Log.d("RegDTime", RegDTime);
                            }
                            if(type == 0) {
                                total_arraylist.add(new Bid_Listitem("[" + o.getString("OrderBidHNum") + "]", o.getString("BidName"), o.getString("OrderName"), parseDateTimeToDate(o.getString("RegDTime"), false), toNumFormat(o.getString("EstimatedPrice")) + "원", o.getInt("MyDocAddedFlag") > 0
                                        , o.getString("BidNo") + "-" + o.getString("BidNoSeq"), o.getInt("BidState_Code"), o.getInt("HasMemoFlag") > 0));
                            }else if (type == 1){
                                bid_arraylist.add(new Bid_Listitem("[" + o.getString("OrderBidHNum") + "]", o.getString("BidName"), o.getString("OrderName"), parseDateTimeToDate(o.getString("RegDTime"), false), toNumFormat(o.getString("EstimatedPrice")) + "원", o.getInt("MyDocAddedFlag") > 0
                                        , o.getString("BidNo") + "-" + o.getString("BidNoSeq"), o.getInt("BidState_Code"), o.getInt("HasMemoFlag") > 0));
                            }else{
                                String comName = o.optString("ComName", "NoData");
                                result_arraylist.add(new Result_Listitem("[" + o.getString("OrderBidHNum") + "]", o.getString("BidName"), o.getString("OrderName"), comName
                                        , toNumFormat(o.optString("JoinPrice", "0")) + "원", o.getInt("MyDocAddedFlag") > 0, comName.equals("NoData")
                                        , o.getString("EtcInfo"), o.getString("BidNo") + "-" + o.getString("BidNoSeq"), o.getInt("BidState_Code"), o.getInt("HasMemoFlag") > 0));
                            }
                            Log.d("Bid Data = ", o.toString());
                        }

                    }
                    ll_progressContainer_mybid.setVisibility(View.GONE);

                    if(type == 0) {
                        lv_total.setVisibility(View.VISIBLE);
                        total_adapter.notifyDataSetChanged();
                        startNum = total_arraylist.size();
                        footerString = "검색결과 : 총 " + toNumFormat(String.valueOf(totalNum)) + "건 중 " + toNumFormat(String.valueOf(total_arraylist.size())) + "건";
                    }else if(type == 1){
                        lv_bidable.setVisibility(View.VISIBLE);
                        bid_adapter.notifyDataSetChanged();
                        startNum = bid_arraylist.size();
                        footerString = "검색결과 : 총 " + toNumFormat(String.valueOf(totalNum)) + "건 중 " + toNumFormat(String.valueOf(bid_arraylist.size())) + "건";
                    }else{
                        lv_result.setVisibility(View.VISIBLE);
                        result_adapter.notifyDataSetChanged();
                        startNum = result_arraylist.size();
                        footerString = "검색결과 : 총 " + toNumFormat(String.valueOf(totalNum)) + "건 중 " + toNumFormat(String.valueOf(bid_arraylist.size())) + "건";
                    }
                    ((TextView)footer.findViewById(R.id.tv_listview_footer_count)).setText(footerString);


                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("GCode", GCode);
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("SDate", SDate);
                params.put("EDate", EDate);
                params.put("SMoney", "0");
                params.put("EMoney", "0");
                params.put("Sort", SortType);
                params.put("StartNum", String.valueOf(startNum));
                if(type < 2){
                    params.put("isBid", "Y");
                    if(type == 1) {
                        params.put("isNew", "Y");
                    }
                }

                params.put("RegDTime", RegDTime);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    private String getMonthAgoDate(int month){
        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");
        Calendar cal = Calendar.getInstance(time);
        cal.add(Calendar.MONTH, -month);

        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(time);
        String strDate = sdf.format(date);
        return strDate;
    }

    private String getMonthAgoDateFromSelectedDate(int month, String selectedDate){
        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");
        Calendar cal = Calendar.getInstance(time);
        cal.set(Integer.parseInt(selectedDate.substring(0, 4)), Integer.parseInt(selectedDate.substring(5, 7)), Integer.parseInt(selectedDate.substring(8, 10)));
        cal.add(Calendar.MONTH, - (month + 1));

        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(time);
        String strDate = sdf.format(date);
        return strDate;
    }

    private String parseDateTimeToDate(String dateTime, boolean isToServer){
        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");

        Date date = new Date(Long.parseLong(dateTime));
        if(isToServer) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(time);
            return sdf.format(date);
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(time);
            return sdf.format(date);
        }
    }

    private String toNumFormat(String data){
        DecimalFormat df = new DecimalFormat("#,###");
        BigDecimal bd = new BigDecimal(data);
        return df.format(bd);
    }

    private void initSearchData(){
        if(type == 0){
            SortType = "RegDTime";
        }else if(type == 1){
            SortType = "RegDTime";
        }else{
            SortType = "ResultDTime";
        }
        RegDTime = "0";
        startNum = 0;
        totalNum = 0;

        total_arraylist.clear();
        bid_arraylist.clear();
        result_arraylist.clear();
//
//        total_adapter.notifyDataSetChanged();
//        bid_adapter.notifyDataSetChanged();
//        result_adapter.notifyDataSetChanged();
    }

}
