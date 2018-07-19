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

    private TextView tv_month_anal;
    private String[] arrSearchTxt = {"FinishDTime", "ResultDTime"};
    private TextView[] arrButton = new TextView[2];
    private ImageView[] arrCheck = new ImageView[2];

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

        tv_month_anal = findViewById(R.id.tv_month_anal);
        arrTodays[0] = findViewById(R.id.tv_anal_today0);
        arrTodays[1] = findViewById(R.id.tv_anal_today1);
        arrTodays[2] = findViewById(R.id.tv_anal_today2);
        arrTodays[3] = findViewById(R.id.tv_anal_today3);
        arrTodays[4] = findViewById(R.id.tv_anal_today4);
        arrTodays[5] = findViewById(R.id.tv_anal_today5);
        arrTodays[6] = findViewById(R.id.tv_anal_today6);

        arrButton[0] = (TextView)findViewById(R.id.btn_click1_anal);
        arrButton[1] = (TextView)findViewById(R.id.btn_click2_anal);

        arrCheck[0] = findViewById(R.id.iv_click1_anal);
        arrCheck[1] = findViewById(R.id.iv_click2_anal);

        calendarWeekAdapter.setOnFragmentListener(this);
        calendarWeekAdapter.setOnItemSelectedListener(this);
        calendarWeekAdapter.setNumOfWeek(COUNT_PAGE);

        viewPager.setCurrentItem(COUNT_PAGE);
        String title = calendarWeekAdapter.getMonthDisplayed(COUNT_PAGE);
        tv_month_anal.setText(title);

        today = Calendar.getInstance();

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                String title = calendarWeekAdapter.getMonthDisplayed(position);
                tv_month_anal.setText(title);

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

        ((ImageView)findViewById(R.id.iv_preWeek_anal)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageOffset--;
                viewPager.setCurrentItem(COUNT_PAGE + pageOffset);
            }
        });

        ((ImageView)findViewById(R.id.iv_nextWeek_anal)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pageOffset++;
                viewPager.setCurrentItem(COUNT_PAGE + pageOffset);
            }
        });

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
            initButtonByBidCnt(selectedDate);
            getAnalBidList(selectedDate, 0);
        }else{
            selectedDate = "";
            arrayList.clear();
            adapter.notifyDataSetChanged();
        }

        clickBackground(arrButton.length + 1);
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

    private void clickBackground(int index)
    {
        for(int i = 0; i < arrButton.length; i++){
            if(index == i){
                arrButton[i].setBackgroundResource(R.drawable.bgr_btn_clicked);
                arrButton[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_addition));
                arrCheck[i].setVisibility(View.VISIBLE);
            }else{
                arrButton[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_addition));
                arrCheck[i].setVisibility(View.GONE);
            }

            if(index > arrButton.length){
                arrButton[i].setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
            }
        }

    }

    private void initButtonByBidCnt(final String date){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/getAnalData.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    boolean isFirst = true;
                    for(int i = 0; i < arrSearchTxt.length; i++){
                        int cnt = obj.getInt(arrSearchTxt[i]);
                        final int index = i;
                        if(cnt > 0){
                            arrButton[i].setTextColor(ContextCompat.getColor(mContext,R.color.colorPrimaryDark));
                            arrButton[i].setBackgroundResource(R.drawable.bgr_btn_clicked);
                            arrButton[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_addition));
                            arrButton[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    clickBackground(index);
                                    if(!selectedDate.isEmpty()){
                                        //adapter.setSortType(index + 1);
                                        getAnalBidList(selectedDate, index);
                                    }
                                    else{
                                        Toast.makeText(mContext, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
                                    }
                                }
                            });
                            if(isFirst){
                                isFirst = false;
                                clickBackground(index);
                                if(!selectedDate.isEmpty()){
                                    //adapter.setSortType(index + 1);
                                    getAnalBidList(selectedDate, index);
                                }
                                else{
                                    Toast.makeText(mContext, "날짜를 선택해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }else{
                            arrButton[i].setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                            arrButton[i].setBackgroundResource(R.drawable.bgr_btn_unclicked);
                            arrButton[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_addition));
                            arrButton[i].setOnClickListener(null);
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

    private void getAnalBidList(final String date, final int index){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/getAnalBidList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    arrayList.clear();
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        String date = "";
                        switch(index){
                            case 0:
                                date = parseDateTimeToDate(o.getString("FinishDTime"), false);
                                break;
                            case 1:
                                date = parseDateTimeToDate(o.getString("ResultDTime"), false);
                                break;
                        }

                        arrayList.add(new MyBid_Request_Listitem("[" + o.getString("OrderBidHNum") + "]", o.getString("BidName"), o.getString("OrderName"), date, toNumFormat(o.getString("BasicPrice")) + "원", parseDateTimeToDate(o.optString("SendDate", "0"), false), toNumFormat(o.optString("SendMoney", "0")), o.getString("MemMemo"), o.optString("Memo", ""), o.getInt("chkTuchal") == 1, o.getInt("chkMoney") == 1, o.getInt("chkRegist") == 1));
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
