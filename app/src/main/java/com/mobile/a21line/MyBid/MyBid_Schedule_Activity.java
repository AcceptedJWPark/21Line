package com.mobile.a21line.MyBid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.util.Log;
import android.view.View;
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

public class MyBid_Schedule_Activity extends AppCompatActivity implements AdapterView.OnItemClickListener, View.OnClickListener {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;

    public static int SUNDAY 		= 1;
    public static int MONDAY 		= 2;
    public static int TUESDAY 		= 3;
    public static int WEDNSESDAY 	= 4;
    public static int THURSDAY 		= 5;
    public static int FRIDAY 		= 6;
    public static int SATURDAY 		= 7;

    private TextView tv_month;
    private GridView gv_calendar;

    Button btn_pre;
    Button btn_next;

    MyBid_Schedule_Adapter schedule_adapter;

    ArrayList<Schedule_Listitem> arrayList;

    Calendar mLastMonth;
    Calendar mThisMonth;
    Calendar mNextMonth;



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


        tv_month =findViewById(R.id.tv_month_schedule);
        gv_calendar =findViewById(R.id.gv_monthly_schedule_mybid);

        btn_pre = findViewById(R.id.btn_pre);
        btn_next = findViewById(R.id.btn_next);

        btn_pre.setOnClickListener(this);
        btn_next.setOnClickListener(this);
        gv_calendar.setOnItemClickListener(this);

        arrayList = new ArrayList<>();


    }

    @Override
    protected void onResume()
    {
        super.onResume();

        mThisMonth = Calendar.getInstance();
        mThisMonth.set(Calendar.DAY_OF_MONTH, 1);
        getCalendar(mThisMonth);
    }

    private void getCalendar(Calendar calendar)
    {
        int lastMonthStartDay;
        int dayOfMonth;
        int thisMonthLastDay;

        arrayList.clear();

        dayOfMonth = calendar.get(Calendar.DAY_OF_WEEK);
        thisMonthLastDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, -1);
        Log.e("지난달 마지막일", calendar.get(Calendar.DAY_OF_MONTH)+"");

        // 지난달의 마지막 일자를 구한다.
        lastMonthStartDay = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);

        calendar.add(Calendar.MONTH, 1);
        Log.e("이번달 시작일", calendar.get(Calendar.DAY_OF_MONTH)+"");

        if(dayOfMonth == SUNDAY)
        {
            dayOfMonth += 7;
        }

        lastMonthStartDay -= (dayOfMonth-1)-1;


        tv_month.setText(mThisMonth.get(Calendar.YEAR) + "년 " + (mThisMonth.get(Calendar.MONTH) + 1) + "월");

        Schedule_Listitem day;

        Log.e("DayOfMOnth", dayOfMonth+"");

        for(int i=0; i<dayOfMonth-1; i++)
        {
            int date = lastMonthStartDay+i;
            day = new Schedule_Listitem();
            day.setDate(Integer.toString(date));
            day.setInMonth(false);

            arrayList.add(day);
        }
        for(int i=1; i <= thisMonthLastDay; i++)
        {
            day = new Schedule_Listitem();
            day.setDate(Integer.toString(i));
            day.setInMonth(true);

            arrayList.add(day);
        }
        for(int i=1; i<42-(thisMonthLastDay+dayOfMonth-1)+1; i++)
        {
            day = new Schedule_Listitem();
            day.setDate(Integer.toString(i));
            day.setInMonth(false);
            arrayList.add(day);
        }

        initCalendarAdapter();
    }

    private Calendar getLastMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, -1);
        tv_month.setText(mThisMonth.get(Calendar.YEAR) + "년 "
                + (mThisMonth.get(Calendar.MONTH) + 1) + "월");
        return calendar;
    }

    private Calendar getNextMonth(Calendar calendar)
    {
        calendar.set(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), 1);
        calendar.add(Calendar.MONTH, +1);
        tv_month.setText(mThisMonth.get(Calendar.YEAR) + "년 "
                + (mThisMonth.get(Calendar.MONTH) + 1) + "월");
        return calendar;
    }

    private void initCalendarAdapter()
    {
        schedule_adapter = new MyBid_Schedule_Adapter(this, R.layout.mybid_schedule_bgr, arrayList);
        gv_calendar.setAdapter(schedule_adapter);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.btn_pre:
                mThisMonth = getLastMonth(mThisMonth);
                getCalendar(mThisMonth);
                break;
            case R.id.btn_next:
                mThisMonth = getNextMonth(mThisMonth);
                getCalendar(mThisMonth);
                break;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
