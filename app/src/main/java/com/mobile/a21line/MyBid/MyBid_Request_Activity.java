package com.mobile.a21line.MyBid;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Calendar.CalendarWeekAdapter;
import com.mobile.a21line.Calendar.CalendarWeekFragment;
import com.mobile.a21line.Calendar.CalendarWeekView;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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

public class MyBid_Request_Activity extends AppCompatActivity implements CalendarWeekFragment.OnFragmentListener, CalendarWeekView.OnItemSelectedListener {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;

    MyBid_Request_LVAdapter adapter;
    ArrayList<MyBid_Request_Listitem> arrayList;
    ListView lv_request;

    private int pageOffset = 0;

    private static final int COUNT_PAGE = 50;
    private ViewPager viewPager;
    private CalendarWeekAdapter calendarWeekAdapter;

    private View curView;

    private TextView tv_month_schedule;
    private String[] arrSearchTxt = {"SaveDate", "ERDDTime", "OpenDTime", "StartDTime", "FinishDTime", "PTDTime", "ResultDTime"};
    private TextView[] arrButton = new TextView[7];
    private ImageView[] arrCheck = new ImageView[7];

    TextView[] arrTodays = new TextView[7];

    String selectedDate = null;

    Calendar today;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mybid_request_activity);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("분석의뢰함");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setText("분석취소");

        ((ImageView)findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);


        drawerLayout = findViewById(R.id.dl_mybid);
        frameLayout = findViewById(R.id.fl_drawerView_mybid);

        final View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, MyBid_Request_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(MyBid_Request_Activity.this, mClicklistener);

        lv_request = findViewById(R.id.lv_requset);
        arrayList = new ArrayList<>();
        adapter = new MyBid_Request_LVAdapter(mContext,arrayList);

        viewPager = (ViewPager)findViewById(R.id.calendar_week_pager);
        calendarWeekAdapter = new CalendarWeekAdapter(getSupportFragmentManager(), false);
        viewPager.setAdapter(calendarWeekAdapter);

        tv_month_schedule = findViewById(R.id.tv_month_schedule);
        arrTodays[0] = findViewById(R.id.tv_schedule_today0);
        arrTodays[1] = findViewById(R.id.tv_schedule_today1);
        arrTodays[2] = findViewById(R.id.tv_schedule_today2);
        arrTodays[3] = findViewById(R.id.tv_schedule_today3);
        arrTodays[4] = findViewById(R.id.tv_schedule_today4);
        arrTodays[5] = findViewById(R.id.tv_schedule_today5);
        arrTodays[6] = findViewById(R.id.tv_schedule_today6);

        calendarWeekAdapter.setOnFragmentListener(this);
        calendarWeekAdapter.setOnItemSelectedListener(this);
        calendarWeekAdapter.setNumOfWeek(COUNT_PAGE);

        viewPager.setCurrentItem(COUNT_PAGE);
        String title = calendarWeekAdapter.getMonthDisplayed(COUNT_PAGE);
        tv_month_schedule.setText(title);

        today = Calendar.getInstance();

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

        arrayList.add(new MyBid_Request_Listitem("조달청 용역 20180714525-00 호","영주고등학교 인조잔디 분리회수 및 재활용 처리용역","제주특별자치도교육청 영주고등학교","요청일 : 18/07/12","84,651,000",5));
        arrayList.add(new MyBid_Request_Listitem("부산광역시 공고 제2018-1662호","내성초등학교 외 1교 냉난방시설개선 전기공사 소액수의 견적제출 안내공고","경상북도교육청 경상북도봉화교육지원청","요청일 : 18/07/12","84,651,000",1));
        arrayList.add(new MyBid_Request_Listitem("(주)경남무역 입찰공고 GNTI 2018-188","소방시설점검(작동기능점검/종합정밀점검)업체선정입찰공고 ","경기도교육청 경기도구리남양주교육지원청","요청일 : 18/07/09","56,610,000",2));
        arrayList.add(new MyBid_Request_Listitem("조달청 시설 20180714568-00 호","구로3빗물펌프장 유수지 내 경로당 신축공사(건축, 기계) ","봉화산 푸르지오","요청일 : 18/07/07","104,411,000",3));
        arrayList.add(new MyBid_Request_Listitem("조달청 시설 20180714568-00 호","구로3빗물펌프장 유수지 내 경로당 신축공사(건축, 기계) ","봉화산 푸르지오","요청일 : 18/07/07","104,411,000",4));
        arrayList.add(new MyBid_Request_Listitem("조달청 시설 20180714568-00 호","구로3빗물펌프장 유수지 내 경로당 신축공사(건축, 기계) ","봉화산 푸르지오","요청일 : 18/07/07","104,411,000",5));
        arrayList.add(new MyBid_Request_Listitem("조달청 시설 20180714568-00 호","구로3빗물펌프장 유수지 내 경로당 신축공사(건축, 기계) ","봉화산 푸르지오","요청일 : 18/07/07","104,411,000",6));

        lv_request.setAdapter(adapter);

        setToday(true);
    }

    @Override
    public void onFragmentListener(View view) {
        resizeHeight(view, false);
    }

    public void resizeHeight(View mRootView, boolean isToday) {

        if (mRootView.getHeight() < 1) {
            return;
        }
        curView = mRootView;

        if(isToday){
            CalendarWeekView calendarView = calendarWeekAdapter.getViewByPosition(COUNT_PAGE);
            calendarView.setToday();
            calendarView.invalidate();
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
        }else{
            selectedDate = "";
            arrayList.clear();
            adapter.notifyDataSetChanged();
        }
    }

    public void initTodayPage(){

        viewPager.setCurrentItem(COUNT_PAGE);
        pageOffset = 0;

        resizeHeight(curView, true);
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

}
