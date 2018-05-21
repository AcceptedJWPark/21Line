package com.mobile.a21line.Bid;

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
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

    ListView lv_bidlist;
    ArrayList<Bid_Listitem> arrayList;
    Bid_LVAdapter adapter;

    RelativeLayout rl_sortingbox_open;
    LinearLayout ll_sortingbox;

    View.OnClickListener periodListener;
    View.OnClickListener sortingListener;

    ImageView iv_periodIcon1;
    ImageView iv_periodIcon2;
    ImageView iv_periodIcon3;
    ImageView iv_periodIcon4;
    ImageView iv_periodIcon5;
    ImageView iv_sortingIcon1;
    ImageView iv_sortingIcon2;
    ImageView iv_sortingIcon3;

    Button btn_searchbox_save;

    RelativeLayout rl_searchbox_period1_bid;
    RelativeLayout rl_searchbox_period2_bid;
    RelativeLayout rl_searchbox_period3_bid;
    RelativeLayout rl_searchbox_period4_bid;
    RelativeLayout rl_searchbox_period5_bid;

    RelativeLayout rl_searchbox_sorting1_bid;
    RelativeLayout rl_searchbox_sorting2_bid;
    RelativeLayout rl_searchbox_sorting3_bid;

    EditText et_SDate, et_EDate;

    String SortType = "RegDTime";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bid_activity);

        mContext = getApplicationContext();

        GCode = getIntent().getStringExtra("GCode");

        et_SDate = (EditText)findViewById(R.id.et_SDate_bid);
        et_EDate = (EditText)findViewById(R.id.et_EDate_bid);

        et_SDate.setText(getMonthAgoDate(1));
        et_EDate.setText(getMonthAgoDate(0));

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("맞춤입찰 1.");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

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
        arrayList = new ArrayList<Bid_Listitem>();
        arrayList.add(new Bid_Listitem("[20180519935-00]","당진시립도서관(중앙,합덕,송악) 3개소 도서 구입(2018-2차)","충청남도 당진시 시립도서관","2018-05-16","66,270,000원",false));
        arrayList.add(new Bid_Listitem("[20180510099-01]","2018학년도 청계초 통학차량 임차용역 수의계약 안내 공고","전라남도무안교육청 청계초등학교","2018-05-16","297,320,000원",false));
        arrayList.add(new Bid_Listitem("[20180411212-00]","서생 온곡 소규모 하수처리장 설치사업 건설사업관리용역(감독권한대행)","울산광역시 울주군","2018-05-16","12,210,000원",false));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",false));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",false));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",false));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",false));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",false));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",false));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",false));
        adapter = new Bid_LVAdapter(mContext,arrayList);
        lv_bidlist.setAdapter(adapter);

        rl_sortingbox_open = findViewById(R.id.rl_searchbox_open);
        ll_sortingbox = findViewById(R.id.ll_sortingbox_bid);
        rl_sortingbox_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_sortingbox.getVisibility()==View.GONE) {
                    ll_sortingbox.setVisibility(View.VISIBLE);
                    ((ImageView)findViewById(R.id.iv_searchbox_open)).setImageResource(R.drawable.icon_arrowup);
                }
                else
                {
                    ll_sortingbox.setVisibility(View.GONE);
                    ((ImageView)findViewById(R.id.iv_searchbox_open)).setImageResource(R.drawable.icon_arrowdown);
                }
            }
        });

        btn_searchbox_save = findViewById(R.id.btn_searchbox_save_bid);
        btn_searchbox_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMypageBidList();
                Toast.makeText(mContext,"검색조건이 적용되었습니다.",Toast.LENGTH_SHORT).show();
                ll_sortingbox.setVisibility(View.GONE);
            }
        });

        iv_periodIcon1 = findViewById(R.id.iv_searchbox_period1);
        iv_periodIcon2 = findViewById(R.id.iv_searchbox_period2);
        iv_periodIcon3 = findViewById(R.id.iv_searchbox_period3);
        iv_periodIcon4 = findViewById(R.id.iv_searchbox_period4);
        iv_periodIcon5 = findViewById(R.id.iv_searchbox_period5);
        iv_sortingIcon1 = findViewById(R.id.iv_searchbox_sorting1);
        iv_sortingIcon2 = findViewById(R.id.iv_searchbox_sorting2);
        iv_sortingIcon3 = findViewById(R.id.iv_searchbox_sorting3);

        rl_searchbox_period1_bid = findViewById(R.id.rl_searchbox_period1);
        rl_searchbox_period2_bid = findViewById(R.id.rl_searchbox_period2);
        rl_searchbox_period3_bid = findViewById(R.id.rl_searchbox_period3);
        rl_searchbox_period4_bid = findViewById(R.id.rl_searchbox_period4);
        rl_searchbox_period5_bid = findViewById(R.id.rl_searchbox_period5);

        rl_searchbox_sorting1_bid = findViewById(R.id.rl_searchbox_sorting1);
        rl_searchbox_sorting2_bid = findViewById(R.id.rl_searchbox_sorting2);
        rl_searchbox_sorting3_bid = findViewById(R.id.rl_searchbox_sorting3);

        rl_searchbox_period1_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDate(0));
                et_EDate.setText(getMonthAgoDate(0));
                periodlistClicked(iv_periodIcon1,iv_periodIcon2,iv_periodIcon3,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period2_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(1, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon2,iv_periodIcon1,iv_periodIcon3,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period3_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(3, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon3,iv_periodIcon2,iv_periodIcon1,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period4_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(6, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon4,iv_periodIcon2,iv_periodIcon3,iv_periodIcon1,iv_periodIcon5);
            }
        });

        rl_searchbox_period5_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(12, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon5,iv_periodIcon2,iv_periodIcon3,iv_periodIcon4,iv_periodIcon1);
            }
        });

        rl_searchbox_sorting1_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "RegDTime";
                sortinglistClicked(iv_sortingIcon1,iv_sortingIcon2,iv_sortingIcon3);
            }
        });
        rl_searchbox_sorting2_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "OpenDTime";
                sortinglistClicked(iv_sortingIcon2,iv_sortingIcon1,iv_sortingIcon3);
            }
        });
        rl_searchbox_sorting3_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "FinishDTime";
                sortinglistClicked(iv_sortingIcon3,iv_sortingIcon2,iv_sortingIcon1);
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

                    ArrayList<Bid_Listitem> arrayList = new ArrayList<>();
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);


                        arrayList.add(new Bid_Listitem("[" + o.getString("OrderBidHNum")+ "]",o.getString("BidName"),o.getString("OrderName"),parseDateTimeToDate(o.getString("RegDTime")), toNumFormat(o.getString("EstimatedPrice"))+"원",o.getInt("MyDocAddedFlag") > 0));
                        Log.d("Bid Data = ", o.toString());
                    }

                    adapter = new Bid_LVAdapter(mContext,arrayList);
                    lv_bidlist.setAdapter(adapter);
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
                params.put("isNew", "Y");
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
        return df.format(Integer.parseInt(data));
    }

}
