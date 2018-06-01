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
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_activity);

        mContext = getApplicationContext();

        GCode = getIntent().getStringExtra("GCode");
        GroupName = getIntent().getStringExtra("GName");


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

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

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


        adapter = new Result_LVAdapter(mContext, arrayList);
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
        }
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
                params.put("SDate", "2018-05-01");
                params.put("EDate", "2018-05-31");
                params.put("Sort", SortType);
                params.put("StartNum", String.valueOf(startNum));
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

}
