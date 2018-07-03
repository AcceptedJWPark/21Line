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
    MyBid_Schedule_LVAdapter adapter;
    ArrayList<MyBid_Schedule_Listitem> arrayList;

    Button btn_click1;
    Button btn_click2;
    Button btn_click3;
    Button btn_click4;
    Button btn_click5;
    Button btn_click6;
    Button btn_click7;
    CalendarDay selectedDate;
    boolean isSelected = false;

    private static final int COUNT_PAGE = 50;
    private ViewPager viewPager;
    private CalendarWeekAdapter calendarWeekAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mybid_schedule_activity);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("스케줄러");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.dl_mybid);
        frameLayout = findViewById(R.id.fl_drawerView_mybid);

        final View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, MyBid_Schedule_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(MyBid_Schedule_Activity.this, mClicklistener);


        btn_click1 = findViewById(R.id.btn_click1_schedule);
        btn_click2 = findViewById(R.id.btn_click2_schedule);
        btn_click3 = findViewById(R.id.btn_click3_schedule);
        btn_click4 = findViewById(R.id.btn_click4_schedule);
        btn_click5 = findViewById(R.id.btn_click5_schedule);
        btn_click6 = findViewById(R.id.btn_click6_schedule);
        btn_click7 = findViewById(R.id.btn_click7_schedule);

        arrayList = new ArrayList<>();

        arrayList.add(new MyBid_Schedule_Listitem("조달청 용역 20180632355-00 호","청주하이텍고 석면비산정도 및 실내농도측정용역 소액수의 견적 공고 ","충청북도교육청 충청북도청주교육지원청","18/06/27","15,089,000"));
        arrayList.add(new MyBid_Schedule_Listitem("한국철도시설공단 용역 2018-02-000269-00 호","경부선 밀양강교 개량 전기통신설비 신설 기타공사 실시설계  ","한국철도시설공단","18/07/10","172,590,000"));
        arrayList.add(new MyBid_Schedule_Listitem("Cm 입찰 CM1806293002 호","테이프컷팅기  ","충청북도교육청 충청북도청주교육지원청","18/07/13","-"));
        arrayList.add(new MyBid_Schedule_Listitem("한국전자통신연구원 용역 EA20181671-02 호","신흥국 스마트그리드 파일럿(Pilot)용 모니터링 장치 시제품 제작 ","한국전자통신연구원","18/07/11 ","50,900,000"));

        lv_schedule = findViewById(R.id.lv_schedule);
        adapter = new MyBid_Schedule_LVAdapter(mContext,arrayList);
        lv_schedule.setAdapter(adapter);

        btn_click1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(btn_click1,btn_click2,btn_click3,btn_click4,btn_click5,btn_click6,btn_click7);
            }
        });

        btn_click2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(btn_click2,btn_click1,btn_click3,btn_click4,btn_click5,btn_click6,btn_click7);
            }
        });

        btn_click3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(btn_click3,btn_click1,btn_click2,btn_click4,btn_click5,btn_click6,btn_click7);
            }
        });

        btn_click4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(btn_click4,btn_click2,btn_click3,btn_click1,btn_click5,btn_click6,btn_click7);
            }
        });

        btn_click5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(btn_click5,btn_click2,btn_click3,btn_click1,btn_click4,btn_click6,btn_click7);
            }
        });

        btn_click6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(btn_click6,btn_click2,btn_click3,btn_click4,btn_click5,btn_click1,btn_click7);
            }
        });

        btn_click7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(btn_click7,btn_click2,btn_click3,btn_click4,btn_click5,btn_click1,btn_click6);
            }
        });

        viewPager = (ViewPager)findViewById(R.id.calendar_week_pager);
        calendarWeekAdapter = new CalendarWeekAdapter(getSupportFragmentManager());
        viewPager.setAdapter(calendarWeekAdapter);

        calendarWeekAdapter.setOnFragmentListener(this);
        calendarWeekAdapter.setOnItemSelectedListener(this);
        calendarWeekAdapter.setNumOfWeek(COUNT_PAGE);

        viewPager.setCurrentItem(COUNT_PAGE);
//        String title = calendarWeekAdapter.getMonthDisplayed(COUNT_PAGE);
//        getSupportActionBar().setTitle(title);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
//                String title = calendarWeekAdapter.getMonthDisplayed(position);
//                getSupportActionBar().setTitle(title);

                if (position == 0) {
                    calendarWeekAdapter.addPrev();
                    viewPager.setCurrentItem(COUNT_PAGE, false);
                    Log.d("CalendarActivity","position("+position+") COUNT_PAGE("+COUNT_PAGE+")");
                } else if (position == calendarWeekAdapter.getCount() - 1) {
                    calendarWeekAdapter.addNext();
                    viewPager.setCurrentItem(calendarWeekAdapter.getCount() - (COUNT_PAGE + 1), false);
                    Log.d("CalendarActivity","position("+position+") COUNT_PAGE("+(calendarWeekAdapter.getCount() - (COUNT_PAGE + 1))+")");
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

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
    public void onItemSelectedListener(long item){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(item);
        Log.d("selected Date = ", c.get(Calendar.YEAR) + ", " + c.get(Calendar.MONTH) + ", " + c.get(Calendar.DATE));
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

}
