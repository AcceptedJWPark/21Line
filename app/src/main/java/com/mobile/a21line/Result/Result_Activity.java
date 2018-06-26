package com.mobile.a21line.Result;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Bid.Bid_Activity;
import com.mobile.a21line.Bid.Bid_LVAdapter;
import com.mobile.a21line.Bid.Bid_Listitem;
import com.mobile.a21line.Bid.Bid_Popup_Sorting;
import com.mobile.a21line.BidAreaCode;
import com.mobile.a21line.BidResultCommon.Popup_SimpleSetting;
import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.Setbid.Setbid_LVAdapter;
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

    String SortType = "ResultDTime";
    String SDate = getMonthAgoDate(1);
    String EDate = getMonthAgoDate(0);

    SwipyRefreshLayout swipyRefreshLayout;
    View footer;

    String RegDTime = "0";
    int totalNum = 0;
    String GroupName;
    JSONObject groupData;

    LinearLayout btn_set_simple;
    ArrayList<BidAreaCode.BidAreaItem> arrayList_location = new ArrayList<>();
    ArrayList<BidUpCode.BidUpCodeItem> arrayList_business = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_activity);

        mContext = getApplicationContext();

        GCode = getIntent().getStringExtra("GCode");
        GroupName = getIntent().getStringExtra("GName");
        try {
            groupData = new JSONObject(getIntent().getStringExtra("groupData"));
        }catch (Exception e){
            e.printStackTrace();
        }


        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText(GroupName);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
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
        frameLayout = findViewById(R.id.fl_drawerView_result);

        View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Result_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Result_Activity.this, mClicklistener);

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


        lv_bidlist = findViewById(R.id.lv_bidlist_result);
        arrayList = new ArrayList<Result_Listitem>();
        footer = getLayoutInflater().inflate(R.layout.listview_footer, null, false);


        adapter = new Result_LVAdapter(mContext, arrayList, this);
        lv_bidlist.setAdapter(adapter);
        lv_bidlist.addFooterView(footer);


        iv_scrollup = findViewById(R.id.iv_scrollup_result);
        iv_scrollup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        findViewById(R.id.btn_set_bid_result).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, Setbid_Activity.class);
                i.putExtra("isAdded", true);
                i.putExtra("groupData", groupData.toString());
                startActivity(i);
                finish();
            }
        });

        getMypageBidList();
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
                    item.setMybidClicked(true);
                    arrayList.set(position, item);
                    adapter.notifyDataSetChanged();
                }
            }
        }
    }

    @Override
    public void onResume(){
        super.onResume();
        drawerLayout.closeDrawers();
    }

    public void getMypageBidList(){

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
                                                                , o.getString("EtcInfo"), o.getString("BidNo") + "-" + o.getString("BidNoSeq"), o.getInt("BidState_Code")));
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
                params.put("GCode", GCode);
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

}
