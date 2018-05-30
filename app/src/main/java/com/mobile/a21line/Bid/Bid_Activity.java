package com.mobile.a21line.Bid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
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

public class Bid_Activity extends AppCompatActivity {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;

    String GCode;
    int startNum = 0;
    int totalNum = 0;

    ListView lv_bidlist;
    ArrayList<Bid_Listitem> arrayList;
    Bid_LVAdapter adapter;

    ImageView iv_scrollup;

    String SortType = "RegDTime";
    String RegDTime = "0";
    String GroupName;

    View footer;

    boolean isGettingBidList = true;

    SwipyRefreshLayout swipyRefreshLayout;


    LinearLayout ll_bidstateContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bid_activity);

        mContext = getApplicationContext();

        GCode = getIntent().getStringExtra("GCode");
        GroupName = getIntent().getStringExtra("GName");


        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText(GroupName);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Bid_Activity.this,Bid_Popup_Sorting.class);
                startActivity(i);
            }
        });
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

        View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Bid_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Bid_Activity.this, mClicklistener);



        lv_bidlist = findViewById(R.id.lv_bidlist_bid);
        footer= getLayoutInflater().inflate(R.layout.listview_footer,null,false);

        arrayList = new ArrayList<Bid_Listitem>();
        adapter = new Bid_LVAdapter(mContext,arrayList);
        lv_bidlist.setAdapter(adapter);
        lv_bidlist.addFooterView(footer);


        iv_scrollup = findViewById(R.id.iv_scrollup_bid);
        iv_scrollup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                lv_bidlist.smoothScrollToPosition(0);
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

        getMypageBidList();

    }

    public void bidState(int[] bidstate)
    {
        ll_bidstateContainer = findViewById(R.id.ll_bidstateContainer_bid);

        ImageView[] iv_bidstate = new ImageView[8];
        iv_bidstate[0] = findViewById(R.id.iv_bidstate1_bid);
        iv_bidstate[1] = findViewById(R.id.iv_bidstate2_bid);
        iv_bidstate[2] = findViewById(R.id.iv_bidstate3_bid);
        iv_bidstate[3] = findViewById(R.id.iv_bidstate4_bid);
        iv_bidstate[4] = findViewById(R.id.iv_bidstate5_bid);
        iv_bidstate[5] = findViewById(R.id.iv_bidstate6_bid);
        iv_bidstate[6] = findViewById(R.id.iv_bidstate7_bid);
        iv_bidstate[7] = findViewById(R.id.iv_bidstate8_bid);
        iv_bidstate[8] = findViewById(R.id.iv_bidstate9_bid);

        int bidstate_rscId[] = new int[9];

        for(int i=0; i<bidstate.length; i++)
        {
            if(bidstate[i]==1)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds9;
                return;
            }
            else if(bidstate[i]==2)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds2;
                return;
            }
            else if(bidstate[i]==3)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds4;
                return;
            }
            else if(bidstate[i]==4)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds5;
                return;
            }
            else if(bidstate[i]==5)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds6;
                return;
            }
            else if(bidstate[i]==6)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds11;
                return;
            }
            else if(bidstate[i]==7)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds3;
                return;
            }
            else if(bidstate[i]==8)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds7;
                return;
            }
            else if(bidstate[i]==9)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds10;
                return;
            }
            else if(bidstate[i]==10)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds1;
                return;
            }
            else if(bidstate[i]==11)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds12;
                return;
            }
            else if(bidstate[i]==12)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds16;
                return;
            }
            else if(bidstate[i]==13)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds17;
                return;
            }
            else if(bidstate[i]==14)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds15;
                return;
            }
            else if(bidstate[i]==15)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds8;
                return;
            }
            else if(bidstate[i]==16)
            {
                bidstate_rscId[i] = R.drawable.bidstate_kinds19;
                return;
            }
        }

        if (bidstate.length ==0)
        {
            ll_bidstateContainer.setVisibility(View.GONE);
        }else
        {
            ll_bidstateContainer.setVisibility(View.VISIBLE);
        }

        for(int i=0; i<bidstate.length; i++)
        {
            iv_bidstate[i].setVisibility(View.VISIBLE);
            iv_bidstate[i].setImageResource(bidstate_rscId[i]);
        }



    }


    public void periodlistClicked(View view1,View view2,View view3,View view4,View view5)
    {
     view1.setVisibility(View.VISIBLE);
     view2.setVisibility(View.GONE);
     view3.setVisibility(View.GONE);
     view4.setVisibility(View.GONE);
     view5.setVisibility(View.GONE);
    }

    public void sortinglistClicked(View view1,View view2,View view3)
    {
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
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
                            arrayList.add(new Bid_Listitem("[" + o.getString("OrderBidHNum") + "]", o.getString("BidName"), o.getString("OrderName"), parseDateTimeToDate(o.getString("RegDTime"), false), toNumFormat(o.getString("EstimatedPrice")) + "원", o.getInt("MyDocAddedFlag") > 0, o.getString("BidNo") + "-" + o.getString("BidNoSeq")));
                            Log.d("Bid Data = ", o.toString());
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
                params.put("SDate", "2018-01-01");
                params.put("EDate", "2018-05-01");
                params.put("Sort", SortType);
                params.put("StartNum", String.valueOf(startNum));
                params.put("isNew", "Y");
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
