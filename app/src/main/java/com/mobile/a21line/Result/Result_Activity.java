package com.mobile.a21line.Result;

import android.content.Context;
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
import com.mobile.a21line.Bid.Bid_LVAdapter;
import com.mobile.a21line.Bid.Bid_Listitem;
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

    RelativeLayout rl_sortingbox_open;
    LinearLayout ll_sortingbox;


    ImageView iv_periodIcon1;
    ImageView iv_periodIcon2;
    ImageView iv_periodIcon3;
    ImageView iv_periodIcon4;
    ImageView iv_periodIcon5;
    ImageView iv_sortingIcon1;
    ImageView iv_sortingIcon2;
    ImageView iv_sortingIcon3;
    ImageView iv_sortingIcon4;

    Button btn_searchbox_save;

    RelativeLayout rl_searchbox_period1;
    RelativeLayout rl_searchbox_period2;
    RelativeLayout rl_searchbox_period3;
    RelativeLayout rl_searchbox_period4;
    RelativeLayout rl_searchbox_period5;

    RelativeLayout rl_searchbox_sorting1;
    RelativeLayout rl_searchbox_sorting2;
    RelativeLayout rl_searchbox_sorting3;
    RelativeLayout rl_searchbox_sorting4;

    EditText et_SDate, et_EDate;

    String SortType = "ResultDTime";

    SwipyRefreshLayout swipyRefreshLayout;
    View footer;

    String LastViewBidNo = "0";
    int totalNum = 0;
    String GroupName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_activity);

        mContext = getApplicationContext();

        GCode = getIntent().getStringExtra("GCode");
        GroupName = getIntent().getStringExtra("GName");

        et_SDate = (EditText)findViewById(R.id.et_SDate_result);
        et_EDate = (EditText)findViewById(R.id.et_EDate_result);

        et_SDate.setText(getMonthAgoDate(1));
        et_EDate.setText(getMonthAgoDate(0));

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText(GroupName);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Right)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

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
        footer= getLayoutInflater().inflate(R.layout.listview_footer,null,false);


        adapter = new Result_LVAdapter(mContext,arrayList);
        lv_bidlist.setAdapter(adapter);
        lv_bidlist.addFooterView(footer);

        rl_sortingbox_open = findViewById(R.id.rl_searchbox_open_result);
        ll_sortingbox = findViewById(R.id.ll_sortingbox_result);
        rl_sortingbox_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_sortingbox.getVisibility()==View.GONE) {
                    ll_sortingbox.setVisibility(View.VISIBLE);
                    ((ImageView)findViewById(R.id.iv_searchbox_open_result)).setImageResource(R.drawable.icon_arrowup);
                }
                else
                {
                    ll_sortingbox.setVisibility(View.GONE);
                    ((ImageView)findViewById(R.id.iv_searchbox_open_result)).setImageResource(R.drawable.icon_arrowdown);
                }
            }
        });

        btn_searchbox_save = findViewById(R.id.btn_searchbox_save_result);
        btn_searchbox_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView)findViewById(R.id.iv_searchbox_open_result)).setImageResource(R.drawable.icon_arrowdown);
                Toast.makeText(mContext,"검색조건이 적용되었습니다.",Toast.LENGTH_SHORT).show();
                ll_sortingbox.setVisibility(View.GONE);
            }
        });

        iv_periodIcon1 = findViewById(R.id.iv_searchbox_period1_result);
        iv_periodIcon2 = findViewById(R.id.iv_searchbox_period2_result);
        iv_periodIcon3 = findViewById(R.id.iv_searchbox_period3_result);
        iv_periodIcon4 = findViewById(R.id.iv_searchbox_period4_result);
        iv_periodIcon5 = findViewById(R.id.iv_searchbox_period5_result);
        iv_sortingIcon1 = findViewById(R.id.iv_searchbox_sorting1_result);
        iv_sortingIcon2 = findViewById(R.id.iv_searchbox_sorting2_result);
        iv_sortingIcon3 = findViewById(R.id.iv_searchbox_sorting3_result);
        iv_sortingIcon4 = findViewById(R.id.iv_searchbox_sorting4_result);

        rl_searchbox_period1 = findViewById(R.id.rl_searchbox_period1_result);
        rl_searchbox_period2 = findViewById(R.id.rl_searchbox_period2_result);
        rl_searchbox_period3 = findViewById(R.id.rl_searchbox_period3_result);
        rl_searchbox_period4 = findViewById(R.id.rl_searchbox_period4_result);
        rl_searchbox_period5 = findViewById(R.id.rl_searchbox_period5_result);

        rl_searchbox_sorting1 = findViewById(R.id.rl_searchbox_sorting1_result);
        rl_searchbox_sorting2 = findViewById(R.id.rl_searchbox_sorting2_result);
        rl_searchbox_sorting3 = findViewById(R.id.rl_searchbox_sorting3_result);
        rl_searchbox_sorting4 = findViewById(R.id.rl_searchbox_sorting4_result);

        rl_searchbox_period1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDate(0));
                et_EDate.setText(getMonthAgoDate(0));
                periodlistClicked(iv_periodIcon1,iv_periodIcon2,iv_periodIcon3,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(1, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon2,iv_periodIcon1,iv_periodIcon3,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(3, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon3,iv_periodIcon2,iv_periodIcon1,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(6, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon4,iv_periodIcon2,iv_periodIcon3,iv_periodIcon1,iv_periodIcon5);
            }
        });

        rl_searchbox_period5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(12, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon5,iv_periodIcon2,iv_periodIcon3,iv_periodIcon4,iv_periodIcon1);
            }
        });

        rl_searchbox_sorting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "RegDTime";
                sortinglistClicked(iv_sortingIcon1,iv_sortingIcon2,iv_sortingIcon3,iv_sortingIcon4);
            }
        });
        rl_searchbox_sorting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "OpenDTime";
                sortinglistClicked(iv_sortingIcon2,iv_sortingIcon1,iv_sortingIcon3,iv_sortingIcon4);
            }
        });
        rl_searchbox_sorting3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "FinishDTime";
                sortinglistClicked(iv_sortingIcon3,iv_sortingIcon2,iv_sortingIcon1,iv_sortingIcon4);
            }
        });

        rl_searchbox_sorting4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "FinishDTime";
                sortinglistClicked(iv_sortingIcon4,iv_sortingIcon3,iv_sortingIcon2,iv_sortingIcon1);
            }
        });

        swipyRefreshLayout = (SwipyRefreshLayout)findViewById(R.id.swipy_result_list);
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(SwipyRefreshLayoutDirection direction) {
                getMypageBidList();
                swipyRefreshLayout.setRefreshing(false);
            }
        });

        getMypageBidList();
    }


    public void periodlistClicked(View view1,View view2,View view3,View view4,View view5)
    {
     view1.setVisibility(View.VISIBLE);
     view2.setVisibility(View.GONE);
     view3.setVisibility(View.GONE);
     view4.setVisibility(View.GONE);
     view5.setVisibility(View.GONE);
    }

    public void sortinglistClicked(View view1,View view2,View view3,View view4)
    {
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
        view4.setVisibility(View.GONE);
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

                        if(LastViewBidNo.equals("0")){
                            LastViewBidNo = o.getString("BidNo");
                            Log.d("LastViewBidNo", LastViewBidNo);
                        }
                        if(i == obj.length() -1 && totalNum == 0){
                            totalNum = o.getInt("TotalCnt");
                        }else {
                            String comName = o.optString("ComName", "NoData");
                            arrayList.add(new Result_Listitem("[" + o.getString("OrderBidHNum") + "]", o.getString("BidName"), o.getString("OrderName"), comName, toNumFormat(o.optString("JoinPrice", "0")) + "원", o.getInt("MyDocAddedFlag") > 0, comName.equals("NoData"), o.getString("EtcInfo"), o.getString("BidNo") + "-" + o.getString("BidNoSeq")));
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
                params.put("SDate", et_SDate.getText().toString());
                params.put("EDate", et_EDate.getText().toString());
                params.put("Sort", SortType);
                params.put("StartNum", String.valueOf(startNum));
                params.put("LastViewBidNo", LastViewBidNo);
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

    private String parseDateTimeToDate(String dateTime){
        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");

        Date date = new Date(Long.parseLong(dateTime));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(time);
        return sdf.format(date);
    }

    private String toNumFormat(String data){
        DecimalFormat df = new DecimalFormat("#,###");
        BigDecimal bd = new BigDecimal(data);
        return df.format(bd);
    }

}
