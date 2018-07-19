package com.mobile.a21line.Bid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
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
import java.util.Random;
import java.util.TimeZone;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Bid_Activity extends AppCompatActivity {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;

    String GCode;
    int startNum = 0;
    int totalNum = 0;

    boolean isTotalSearch = false;

    ListView lv_bidlist;
    ArrayList<Bid_Listitem> arrayList;
    Bid_LVAdapter adapter;

    ImageView iv_scrollup;

    String SortType = "RegDTime";
    String RegDTime = "0";
    String GroupName;

    String SMoney;
    String EMoney;
    int BidType;

    String SearchType;
    String SearchText;

    String SDate = getMonthAgoDate(1);
    String EDate = getMonthAgoDate(0);
    JSONObject groupData;

    View footer;

    boolean isGettingBidList = true;

    SwipyRefreshLayout swipyRefreshLayout;
    ArrayList<BidAreaCode.BidAreaItem> arrayList_location = new ArrayList<>();
    ArrayList<BidUpCode.BidUpCodeItem> arrayList_business = new ArrayList<>();

    LinearLayout btn_set_simple;
    LinearLayout ll_bidlist_bid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.bid_activity);

        mContext = getApplicationContext();

        isTotalSearch = getIntent().getBooleanExtra("isTotalSearch", false);

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
            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("통합검색");


            ll_bidlist_bid = findViewById(R.id.ll_bidlist_bid);
            ll_bidlist_bid.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,0,15f));
        }else{

            GCode = getIntent().getStringExtra("GCode");
            GroupName = getIntent().getStringExtra("GName");

            try {
                groupData = new JSONObject(getIntent().getStringExtra("groupData"));
            }catch (Exception e){
                e.printStackTrace();
            }

            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("맞춤입찰_" + GroupName);

            btn_set_simple = findViewById(R.id.btn_set_simple_bid);
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

            findViewById(R.id.btn_search_bid).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent i = new Intent(mContext, Search_Bid_Activity.class);
                    startActivity(i);
                    finish();
                }
            });
        }


        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bid_Activity.this,Bid_Popup_Sorting.class);
                i.putExtra("SDate", SDate);
                i.putExtra("EDate", EDate);
                i.putExtra("SortType", SortType);
                startActivityForResult(i, 0);
            }
        });
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);

        drawerLayout = findViewById(R.id.dl_bid);
        frameLayout = findViewById(R.id.fl_drawerView_bid);

        final View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Bid_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Bid_Activity.this, mClicklistener);


        lv_bidlist = findViewById(R.id.lv_bidlist_bid);
        footer= getLayoutInflater().inflate(R.layout.listview_footer,null,false);

        arrayList = new ArrayList<Bid_Listitem>();
        adapter = new Bid_LVAdapter(Bid_Activity.this,arrayList, this, false);
        lv_bidlist.setAdapter(adapter);
        lv_bidlist.addFooterView(footer);


        iv_scrollup = findViewById(R.id.iv_scrollup_bid);
        iv_scrollup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.clear();
                totalNum = 0;
                startNum = 0;
                getMypageBidList();
                //lv_bidlist.smoothScrollToPosition(0);
            }
        });


        swipyRefreshLayout = findViewById(R.id.swipy_bid_list);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                if(totalNum > startNum)
                    getMypageBidList();
                swipyRefreshLayout.setRefreshing(false);
            }
        });

    }

    @Override
    public void onResume(){
        super.onResume();
        getMypageBidList();
        drawerLayout.closeDrawers();
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
                    Bid_Listitem item = arrayList.get(position);
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

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/getMypageBidList.do", new Response.Listener<String>(){
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
                            Bid_Listitem item = new Bid_Listitem("[" + o.getString("OrderBidHNum") + "]", o.getString("BidName"), o.getString("OrderName"), parseDateTimeToDate(o.getString("RegDTime"), false), toNumFormat(o.getString("EstimatedPrice")) + "원", o.getInt("MyDocAddedFlag") > 0
                                    , o.getString("BidNo") + "-" + o.getString("BidNoSeq"), o.getInt("BidState_Code"), o.getInt("HasMemoFlag") > 0);

                            arrayList.add(item);
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
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                if(isTotalSearch){
                    params.put("isTotalSearch", "Y");
                    params.put("BidType", String.valueOf(BidType));
                    params.put("SearchType", SearchType);
                    params.put("SearchText", SearchText);
                    params.put("SMoney", SMoney);
                    params.put("EMoney", EMoney);
                }else {
                    params.put("GCode", GCode);
                }

                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("SDate", SDate);
                params.put("EDate", EDate);
                params.put("Sort", SortType);
                params.put("StartNum", String.valueOf(startNum));
                params.put("isNew", "Y");
                params.put("RegDTime", RegDTime);
                if (arrayList_business.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < arrayList_business.size(); i++) {
                        BidUpCode.BidUpCodeItem item = arrayList_business.get(i);
                        if (item.isSelected()) {
                            sb.append(item.getCode()).append("\n");
                        }
                    }
                    if (sb.toString().isEmpty()) {
                        params.put("BusinessArray", "NoData");
                    } else {
                        params.put("BusinessArray", sb.toString());
                    }
                } else {
                    params.put("BusinessArray", "NoData");
                }
                if (arrayList_location.size() > 0) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < arrayList_location.size(); i++) {
                        BidAreaCode.BidAreaItem item = arrayList_location.get(i);
                        if (item.isSelected()) {
                            sb.append(item.getCode()).append("\n");
                        }
                    }
                    if (sb.toString().isEmpty()) {
                        params.put("LocationArray", "NoData");
                    } else {
                        params.put("LocationArray", sb.toString());
                    }
                } else {
                    params.put("LocationArray", "NoData");
                }
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

    public boolean isTotalSearch(){
        return isTotalSearch;
    }

}
