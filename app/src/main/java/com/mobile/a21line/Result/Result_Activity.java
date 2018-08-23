package com.mobile.a21line.Result;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import com.mobile.a21line.Bid.Bid_Listitem;
import com.mobile.a21line.BidAreaCode;
import com.mobile.a21line.BidResultCommon.Popup_SimpleSetting;
import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.Search.Search_Bid_Activity;
import com.mobile.a21line.Setbid.Setbid_Activity;
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

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Result_Activity extends AppCompatActivity {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;

    String GCode;
    int startNum = 0;

    ListView lv_bidlist;
    ArrayList<Result_Listitem> arrayList;
    Result_LVAdapter adapter;

    ImageView iv_scrollup;

    String SMoney;
    String EMoney;
    int BidType;
    boolean isTotalSearch = false;

    String SortType = "ResultDTime";
    String SDate = getMonthAgoDate(1);
    String EDate = getMonthAgoDate(0);

    String SearchType;
    String SearchText;

    SwipyRefreshLayout swipyRefreshLayout;
    View footer;

    String RegDTime = "0";
    int totalNum = 0;
    String GroupName;
    JSONObject groupData;

    LinearLayout btn_set_simple;
    ArrayList<BidAreaCode.BidAreaItem> arrayList_location = new ArrayList<>();
    ArrayList<BidUpCode.BidUpCodeItem> arrayList_business = new ArrayList<>();


    LinearLayout ll_resultlist_bid;
    LinearLayout ll_progressContainer_result;
    String SearchMoneyType;

    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_activity);

        mContext = getApplicationContext();

        isTotalSearch = getIntent().getBooleanExtra("isTotalSearch", false);
        ll_resultlist_bid = findViewById(R.id.ll_resultlist_bid);

        if(isTotalSearch){
            arrayList_location = Setbid_Activity.arrayList_location;
            arrayList_business = Setbid_Activity.arrayList_business;

            SDate = getIntent().getStringExtra("SDate");
            EDate = getIntent().getStringExtra("EDate");
            SMoney = getIntent().getStringExtra("SMoney");
            EMoney = getIntent().getStringExtra("EMoney");

            SearchType = getIntent().getStringExtra("SearchType");
            SearchText = getIntent().getStringExtra("SearchText");

            BidType = getIntent().getIntExtra("BidType", 0);
            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("낙찰 통합검색");


            SearchMoneyType = getIntent().getStringExtra("SearchMoneyType");

            ll_resultlist_bid.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,15f));



        }else{

            GCode = getIntent().getStringExtra("GCode");
            GroupName = getIntent().getStringExtra("GName");

            try {
                groupData = new JSONObject(getIntent().getStringExtra("groupData"));
            }catch (Exception e){
                e.printStackTrace();
            }

            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("맞춤낙찰_" + GroupName);

            btn_set_simple = findViewById(R.id.btn_set_simple_result);
            btn_set_simple.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, Popup_SimpleSetting.class);
                    i.putExtra("GCode", GCode);
                    if(arrayList_location.size() > 0){
                        i.putExtra("LocationArray", arrayList_location);
                        i.putExtra("BusinessArray", arrayList_business);
                    }
                    startActivityForResult(i, 1);
                }
            });

            findViewById(R.id.btn_search_result).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, Search_Bid_Activity.class);
                    i.putExtra("isBid", false);
                    startActivity(i);
                }
            });
        }

        ll_progressContainer_result = (LinearLayout)findViewById(R.id.ll_progressContainer_result);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Result_Activity.this, Result_Popup_Sorting.class);
                i.putExtra("SDate", SDate);
                i.putExtra("EDate", EDate);
                i.putExtra("SortType", SortType);
                startActivityForResult(i, 0);
            }
        });

        drawerLayout = findViewById(R.id.dl_result);
        drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        frameLayout = findViewById(R.id.fl_drawerView_result);

        View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Result_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Result_Activity.this, mClicklistener);


        lv_bidlist = findViewById(R.id.lv_bidlist_result);
        arrayList = new ArrayList<Result_Listitem>();
        footer = getLayoutInflater().inflate(R.layout.listview_footer, null, false);


        adapter = new Result_LVAdapter(mContext, arrayList, this, false);
        lv_bidlist.setAdapter(adapter);
        lv_bidlist.addHeaderView(footer);


        iv_scrollup = findViewById(R.id.iv_scrollup_result);
        iv_scrollup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                arrayList.clear();
//                totalNum = 0;
//                startNum = 0;
//                getMypageBidList();
                lv_bidlist.smoothScrollToPosition(0);
            }
        });

        swipyRefreshLayout = (SwipyRefreshLayout) findViewById(R.id.swipy_result_list);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                getMypageBidList();
                swipyRefreshLayout.setRefreshing(false);
            }
        });

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.mobile.a21line.finishActivity");

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                finish();
            }
        };

        registerReceiver(mReceiver, intentFilter);
        AddMemoEvent.getInstance().register(this);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
        AddMemoEvent.getInstance().unregister(this);
    }

    @Subscribe
    public void getPost(AddMemoFlag flag){
        Result_Listitem item = arrayList.get(flag.getPosition());
        item.setMybidClicked(true);
        if((flag.isMoveGroup() && flag.isAdded()) || !flag.isMoveGroup()){
            item.setHasMemo(flag.isAdded());
        }

        arrayList.set(flag.getPosition(), item);

        adapter.notifyDataSetChanged();
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                SDate = intent.getStringExtra("SDate");
                EDate = intent.getStringExtra("EDate");
                SortType = intent.getStringExtra("SortType");
                arrayList.clear();
                totalNum = 0;
                startNum = 0;
                getMypageBidList();
            }
        }else if(requestCode == 1){
            if(resultCode == RESULT_OK){
                arrayList_location = (ArrayList<BidAreaCode.BidAreaItem>)intent.getSerializableExtra("LocationArray");
                arrayList_business = (ArrayList<BidUpCode.BidUpCodeItem>)intent.getSerializableExtra("BusinessArray");
                arrayList.clear();
                totalNum = 0;
                startNum = 0;
                getMypageBidList();
            }
        }else if(requestCode == 3){
            if(resultCode == RESULT_OK){
                int position = intent.getIntExtra("Position", -1);

                if(position >= 0){
                    Result_Listitem item = arrayList.get(position);
                    if(intent.getBooleanExtra("isDelete", false)){
                        item.setMybidClicked(false);
                        arrayList.set(position, item);
                        adapter.notifyDataSetChanged();
                    }else {
                        item.setMybidClicked(true);
                        arrayList.set(position, item);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        }
    }



    public void getMypageBidList(){
        ll_resultlist_bid.setVisibility(View.GONE);
        ll_progressContainer_result.setVisibility(View.VISIBLE);

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/getMypageBidList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    String footerString;

                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);

                        if(i == obj.length() -1 && totalNum == 0){
                            totalNum = o.getInt("TotalCnt");
                        }else {
                            if(RegDTime.equals("0")){
                                RegDTime = parseDateTimeToDate(o.getString("ResultDTime"), true);
                                Log.d("RegDTime", RegDTime);
                            }
                            String comName = o.optString("ComName", "NoData");
                            arrayList.add(new Result_Listitem("[" + o.getString("OrderBidHNum") + "]", o.getString("BidName"), o.getString("OrderName"), comName
                                                                , toNumFormat(o.optString("JoinPrice", "0")) + "원", o.getInt("MyDocAddedFlag") > 0, comName.equals("NoData")
                                                                , o.optString("EtcInfo", ""), o.getString("BidNo") + "-" + o.getString("BidNoSeq"), o.getInt("BidState_Code"), o.getInt("HasMemoFlag") > 0));
                        }
                    }

                    adapter.notifyDataSetChanged();
                    startNum = arrayList.size();
                    footerString = "검색결과 : 총 " + toNumFormat(String.valueOf(totalNum)) + "건 중 " + toNumFormat(String.valueOf(arrayList.size())) + "건";
                    ((TextView)footer.findViewById(R.id.tv_listview_footer_count)).setText(footerString);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }

                ll_resultlist_bid.setVisibility(View.VISIBLE);
                ll_progressContainer_result.setVisibility(View.GONE);
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                if(isTotalSearch){
                    params.put("isTotalSearch", "Y");
                    params.put("BidType", String.valueOf(BidType));
                    params.put("SMoney", SMoney);
                    params.put("EMoney", EMoney);
                    params.put("SearchType", SearchType);
                    params.put("SearchText", SearchText);
                    params.put("SearchMoneyType", SearchMoneyType);
                }else {
                    params.put("GCode", GCode);
                }
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("SDate", SDate);
                params.put("EDate", EDate);
                params.put("Sort", SortType);
                params.put("StartNum", String.valueOf(startNum));
                params.put("RegDTime", RegDTime);
                if(arrayList_business.size() > 0){
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < arrayList_business.size(); i++){
                        BidUpCode.BidUpCodeItem item = arrayList_business.get(i);
                        if(item.isSelected()){
                            sb.append(item.getCode()).append("\n");
                        }
                    }
                    if(sb.toString().isEmpty()){
                        params.put("BusinessArray", "NoData");
                    }else {
                        params.put("BusinessArray", sb.toString());
                    }
                }else{
                    params.put("BusinessArray", "NoData");
                }
                if(arrayList_location.size() > 0){
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < arrayList_location.size(); i++){
                        BidAreaCode.BidAreaItem item = arrayList_location.get(i);
                        if(item.isSelected()){
                            sb.append(item.getCode()).append("\n");
                        }
                    }
                    if(sb.toString().isEmpty()){
                        params.put("LocationArray", "NoData");
                    }else {
                        params.put("LocationArray", sb.toString());
                    }
                }else{
                    params.put("LocationArray", "NoData");
                }
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

    @Override
    public void onResume(){
        super.onResume();
        getMypageBidList();
        drawerLayout.closeDrawers();
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

    public boolean isTotalSearch(){
        return isTotalSearch;
    }

}
