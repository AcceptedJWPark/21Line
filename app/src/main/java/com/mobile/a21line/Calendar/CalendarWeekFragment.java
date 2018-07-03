package com.mobile.a21line.Calendar;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.mobile.a21line.R;

import java.util.Calendar;

/**
 * Created by kwonhong on 2018-07-02.
 */

public class CalendarWeekFragment extends Fragment {
    private int position;
    private long timeByMillis;
    private CalendarWeekFragment.OnFragmentListener onFragmentListener;
    private View mRootView;
    private CalendarWeekView calendarView;

    public void setOnFragmentListener(CalendarWeekFragment.OnFragmentListener onFragmentListener) {
        this.onFragmentListener = onFragmentListener;
    }

    public interface OnFragmentListener{
        public void onFragmentListener(View view);
    }

    public static CalendarWeekFragment newInstance(int position) {
        CalendarWeekFragment frg = new CalendarWeekFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        frg.setArguments(bundle);
        return frg;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        position = getArguments().getInt("poisition");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_calendar_week, null);
        calendarView = (CalendarWeekView) mRootView.findViewById(R.id.calendarweekview);
        Log.d("RootViewHeight", mRootView.getHeight() + "");
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(timeByMillis);

        for (int i = 0; i < 7; i++) {
            CalendarWeekItemView child = new CalendarWeekItemView(getActivity().getApplicationContext());
            Log.d("ChildViewHeight", child.getHeight() + "");
            child.setDate(calendar.getTimeInMillis());
            if (i < 7) {
                child.setDayOfWeek(i);
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
}