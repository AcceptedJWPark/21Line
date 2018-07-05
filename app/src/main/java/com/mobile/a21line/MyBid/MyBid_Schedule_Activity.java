package com.mobile.a21line.MyBid;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.StyleSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Bid.Bid_LVAdapter;
import com.mobile.a21line.Bid.Bid_Listitem;
import com.mobile.a21line.Calendar.CalendarWeekAdapter;
import com.mobile.a21line.Calendar.CalendarWeekFragment;
import com.mobile.a21line.Calendar.CalendarWeekView;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;
import com.prolificinteractive.materialcalendarview.format.TitleFormatter;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

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

public class MyBid_Schedule_Activity extends AppCompatActivity implements CalendarWeekFragment.OnFragmentListener, CalendarWeekView.OnItemSelectedListener {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;

    ListView lv_schedule;
    Bid_LVAdapter adapter;
    ArrayList<Bid_Listitem> arrayList;
    TextView[] arrTodays = new TextView[7];

    String selectedDate = null;
    Calendar today;
    boolean isSelected = false;

    private int pageOffset = 0;

    private static final int COUNT_PAGE = 50;
    private ViewPager viewPager;
    private CalendarWeekAdapter calendarWeekAdapter;

    private TextView tv_month_schedule;
    private String[] arrSearchTxt = {"SaveDate", "ERDDTime", "OpenDTime", "StartDTime", "FinishDTime", "PTDTime", "ResultDTime"};
    private Button[] arrButton = new Button[7];


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mybid_schedule_activity);
        mContext = getApplicationContext();

        today = Calendar.getInstance();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("스케줄러");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);
        tv_month_schedule = findViewById(R.id.tv_month_schedule);

        arrTodays[0] = findViewById(R.id.tv_schedule_today0);
        arrTodays[1] = findViewById(R.id.tv_schedule_today1);
        arrTodays[2] = findViewById(R.id.tv_schedule_today2);
        arrTodays[3] = findViewById(R.id.tv_schedule_today3);
        arrTodays[4] = findViewById(R.id.tv_schedule_today4);
        arrTodays[5] = findViewById(R.id.tv_schedule_today5);
        arrTodays[6] = findViewById(R.id.tv_schedule_today6);

        drawerLayout = findViewById(R.id.dl_mybid);
        frameLayout = findViewById(R.id.fl_drawerView_mybid);

        final View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, MyBid_Schedule_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(MyBid_Schedule_Activity.this, mClicklistener);


        arrButton[0] = findViewById(R.id.btn_click1_schedule);
        arrButton[1] = findViewById(R.id.btn_click2_schedule);
        arrButton[2] = findViewById(R.id.btn_click3_schedule);
        arrButton[3] = findViewById(R.id.btn_click4_schedule);
        arrButton[4] = findViewById(R.id.btn_click5_schedule);
        arrButton[5] = findViewById(R.id.btn_click6_schedule);
        arrButton[6] = findViewById(R.id.btn_click7_schedule);

        arrayList = new ArrayList<>();

        lv_schedule = findViewById(R.id.lv_schedule);
        adapter = new Bid_LVAdapter(mContext,arrayList, this, true);
        lv_schedule.setAdapter(adapter);

        arrButton[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(arrButton[0],arrButton[1],arrButton[2],arrButton[3],arrButton[4],arrButton[5],arrButton[6]);
                if(!selectedDate.isEmpty()){
                    getSchedulerBidList(selectedDate, 0);
                }else{
                    Toast.makeText(mContext, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        arrButton[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(arrButton[1],arrButton[0],arrButton[2],arrButton[3],arrButton[4],arrButton[5],arrButton[6]);
                if(!selectedDate.isEmpty()){
                    getSchedulerBidList(selectedDate, 1);
                }else{
                    Toast.makeText(mContext, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        arrButton[2].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(arrButton[2],arrButton[0],arrButton[1],arrButton[3],arrButton[4],arrButton[5],arrButton[6]);
                if(!selectedDate.isEmpty()){
                    getSchedulerBidList(selectedDate, 2);
                }else{
                    Toast.makeText(mContext, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        arrButton[3].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(arrButton[3],arrButton[1],arrButton[2],arrButton[0],arrButton[4],arrButton[5],arrButton[6]);
                if(!selectedDate.isEmpty()){
                    getSchedulerBidList(selectedDate, 3);
                }else{
                    Toast.makeText(mContext, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        arrButton[4].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(arrButton[4],arrButton[1],arrButton[2],arrButton[0],arrButton[3],arrButton[5],arrButton[6]);
                if(!selectedDate.isEmpty()){
                    getSchedulerBidList(selectedDate, 4);
                }else{
                    Toast.makeText(mContext, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        arrButton[5].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(arrButton[5],arrButton[1],arrButton[2],arrButton[3],arrButton[4],arrButton[0],arrButton[6]);
                if(!selectedDate.isEmpty()){
                    getSchedulerBidList(selectedDate, 5);
                }else{
                    Toast.makeText(mContext, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        arrButton[6].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(arrButton[6],arrButton[1],arrButton[2],arrButton[3],arrButton[4],arrButton[0],arrButton[5]);
                if(!selectedDate.isEmpty()){
                    getSchedulerBidList(selectedDate, 6);
                }else{
                    Toast.makeText(mContext, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        viewPager = (ViewPager)findViewById(R.id.calendar_week_pager);
        calendarWeekAdapter = new CalendarWeekAdapter(getSupportFragmentManager());
        viewPager.setAdapter(calendarWeekAdapter);

        calendarWeekAdapter.setOnFragmentListener(this);
        calendarWeekAdapter.setOnItemSelectedListener(this);
        calendarWeekAdapter.setNumOfWeek(COUNT_PAGE);

        viewPager.setCurrentItem(COUNT_PAGE);
        String title = calendarWeekAdapter.getMonthDisplayed(COUNT_PAGE);
        tv_month_schedule.setText(title);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String title = calendarWeekAdapter.getMonthDisplayed(position);
                tv_month_schedule.setText(title);

                if (position == 0) {
                    calendarWeekAdapter.addPrev();
                    viewPager.setCurrentItem(COUNT_PAGE, false);
                    Log.d("CalendarActivity","position("+position+") COUNT_PAGE("+COUNT_PAGE+")");
                    pageOffset = 0;
                } else if (position == calendarWeekAdapter.getCount() - 1) {
                    calendarWeekAdapter.addNext();
                    viewPager.setCurrentItem(calendarWeekAdapter.getCount() - (COUNT_PAGE + 1), false);
                    Log.d("CalendarActivity","position("+position+") COUNT_PAGE("+(calendarWeekAdapter.getCount() - (COUNT_PAGE + 1))+")");
                    pageOffset = position - COUNT_PAGE;
                }else {
                    pageOffset = position - COUNT_PAGE;
                }

                long diffDay = (today.getTimeInMillis() - calendarWeekAdapter.getTimeInMillis(position)) /(1000*60*60*24);
                setToday(diffDay >= 0 && diffDay < 7);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        ((ImageView)findViewById(R.id.iv_preWeek_schedule)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageOffset--;
                viewPager.setCurrentItem(COUNT_PAGE + pageOffset);
            }
        });

        ((ImageView)findViewById(R.id.iv_nextWeek_schedule)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageOffset++;
                viewPager.setCurrentItem(COUNT_PAGE + pageOffset);
            }
        });

        setToday(true);
    }

    @Override
    public void onFragmentListener(View view) {
        resizeHeight(view);
    }

    public void resizeHeight(View mRootView) {

        if (mRootView.getHeight() < 1) {
            return;
        }
        ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
        if (layoutParams.height <= 0) {
            layoutParams.height = mRootView.getHeight();
            viewPager.setLayoutParams(layoutParams);
            return;
        }
        ValueAnimator anim = ValueAnimator.ofInt(viewPager.getLayoutParams().height, mRootView.getHeight());
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                int val = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = viewPager.getLayoutParams();
                layoutParams.height = val;
                viewPager.setLayoutParams(layoutParams);
            }
        });
        anim.setDuration(200);
        anim.start();
    }

    @Override
    public void onItemSelectedListener(long time){
        if(time != 0) {
            Calendar c = Calendar.getInstance();
            c.setTimeInMillis(time);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            selectedDate = sdf.format(new Date(time));
            initButtonByBidCnt(selectedDate);
            getSchedulerBidList(selectedDate, 0);
        }else{
            selectedDate = "";
            arrayList.clear();
            adapter.notifyDataSetChanged();
        }
    }


    private void clickBackground(Button btn1,Button btn2,Button btn3,Button btn4,Button btn5,Button btn6,Button btn7)
    {
        btn1.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn1.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main));

        btn2.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn3.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn4.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn5.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn6.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn7.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn7.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn2.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn3.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn4.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn5.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn6.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn7.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_sub));
        btn2.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_sub));
        btn3.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_sub));
        btn4.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_sub));
        btn5.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_sub));
        btn6.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_sub));

    }

    private void setToday(boolean isFirst){
        for(int i = 0; i < arrTodays.length; i++){
            if(!isFirst){
                arrTodays[i].setText("");
            }else{
                Calendar c = Calendar.getInstance();
                int today = c.get(Calendar.DAY_OF_WEEK) - 1;

                if(today == i){
                    arrTodays[i].setText("Today");
                }else{
                    arrTodays[i].setText("");
                }
            }
        }
    }

    private void initButtonByBidCnt(final String date){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/getSchedulerData.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    for(int i = 0; i < arrSearchTxt.length; i++){
                        int cnt = obj.getInt(arrSearchTxt[i]);
                        if(cnt > 0){
                            arrButton[i].setTextColor(R.color.textColor_deep);
                        }else{
                            arrButton[i].setTextColor(Color.BLACK);
                        }
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("txtSearchDate", date);
                params.put("isIndividual", "Y");
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }

    private void getSchedulerBidList(final String date, final int index){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/getSchedulerBidList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    arrayList.clear();
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);

                        arrayList.add(new Bid_Listitem("[" + o.getString("OrderBidHNum") + "]", o.getString("BidName"), o.getString("OrderName"), parseDateTimeToDate(o.getString("RegDTime"), false), toNumFormat(o.getString("EstimatedPrice")) + "원", o.getInt("MyDocAddedFlag") > 0
                                , o.getString("BidNo") + "-" + o.getString("BidNoSeq"), o.getInt("BidState_Code")));
                        Log.d("Bid Data = ", o.toString());

                    }

                    adapter.notifyDataSetChanged();

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("txtSearchDate", date);
                params.put("lstSearchTxt", arrSearchTxt[index]);
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
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
