package com.mobile.a21line.Calendar;

import android.content.Context;
import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by kwonhong on 2018-07-02.
 */

public class CalendarWeekFragment extends Fragment {
    private int position;
    private long timeByMillis;
    private CalendarWeekFragment.OnFragmentListener onFragmentListener;
    private View mRootView;
    private CalendarWeekView calendarView;
    private CalendarWeekView.OnItemSelectedListener onItemSelectedListener;
    private Context mContext;
    private boolean isMydoc;

    public void setOnItemSelectedListener(CalendarWeekView.OnItemSelectedListener onItemSelectedListener) {
        this.onItemSelectedListener = onItemSelectedListener;
    }

    public void setOnFragmentListener(CalendarWeekFragment.OnFragmentListener onFragmentListener) {
        this.onFragmentListener = onFragmentListener;
    }

    public interface OnFragmentListener{
        public void onFragmentListener(View view);
    }

    public static CalendarWeekFragment newInstance(int position, boolean isMydoc) {
        CalendarWeekFragment frg = new CalendarWeekFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        bundle.putBoolean("isMydoc", isMydoc);
        frg.setArguments(bundle);
        return frg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("position");
        isMydoc = getArguments().getBoolean("isMydoc");
        mContext = getActivity().getApplicationContext();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_calendar_week, null);
        calendarView = (CalendarWeekView) mRootView.findViewById(R.id.calendarweekview);
        calendarView.setOnItemSelectedListener(this.onItemSelectedListener);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeByMillis);

        for (int i = 0; i < 7; i++) {
            // 여기에 init 함수 추가해서 CalendarWeekItemView 생성자에 변수 넘겨줘야함.
            CalendarWeekItemView child = new CalendarWeekItemView(getActivity().getApplicationContext());
            child.setDate(calendar.getTimeInMillis());
            if (i < 7) {
                child.setDayOfWeek(i);
                initDateByBidCnt(calendar.getTimeInMillis(), child);

                calendar.add(Calendar.DATE, 1);
            } else {
                child.setDayOfWeek(i);
                calendar.add(Calendar.DATE, 1);
            }
            calendarView.addView(child);
        }
        return mRootView;
    }

    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        if (isVisibleToUser && onFragmentListener != null && mRootView != null) {
            onFragmentListener.onFragmentListener(mRootView);
        }
        super.setUserVisibleHint(isVisibleToUser);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getUserVisibleHint()) {

            mRootView.post(new Runnable() {
                @Override
                public void run() {
                    // TODO Auto-generated method stub
                    onFragmentListener.onFragmentListener(mRootView);
                }
            });

        }
    }

    public void setTimeByMillis(long timeByMillis) {
        this.timeByMillis = timeByMillis;
    }

    private void initDateByBidCnt(final long timeByMilis, final CalendarWeekItemView child){
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis((long)child.getTag());
        String url;
        if(isMydoc)
            url = SaveSharedPreference.getServerIp() + "Mydoc/getSchedulerData.do";
        else
            url = SaveSharedPreference.getServerIp() + "Mydoc/getAnalData.do";

       // Log.d("initDate = ", c.get(Calendar.YEAR) + "-" + c.get(Calendar.MONTH) + "-" + c.get(Calendar.DATE));
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    child.setBid(obj.has("hasBid"));
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("txtSearchDate", sdf.format(new Date(timeByMilis)));
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }

    public void refreshDate(){
        for(int i = 0; i < calendarView.getChildCount(); i++){
            CalendarWeekItemView child = (CalendarWeekItemView)calendarView.getChildAt(i);
            initDateByBidCnt((Long)child.getTag(), child);
        }
    }

    public CalendarWeekView getCalendarView(){
        return (CalendarWeekView)mRootView;
    }
}