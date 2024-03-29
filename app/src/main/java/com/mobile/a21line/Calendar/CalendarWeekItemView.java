package com.mobile.a21line.Calendar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Build;
import androidx.core.content.ContextCompat;
import androidx.viewpager.widget.ViewPager;

import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.mobile.a21line.R;

import java.util.Calendar;

/**
 * Created by kwonhong on 2018-07-02.
 */

public class CalendarWeekItemView extends View {
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintBackground = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintBackgroundToday = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mPaintBackgroundEvent = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Rect rect;
    private long millis;
    private int dp13;
    private int dp11;
    private int dp16;

    private int dayOfWeek = -1;
    private boolean isStaticText = false;
    private boolean isTouchMode;
    private boolean hasEvent = false;
    private boolean hasBid = false;
    private boolean isToday = false;
    private int[] mColorEvents;

    public CalendarWeekItemView(Context context) {
        super(context);
        initialize();
    }

    private void initialize() {
        dp11 = (int) dp2px(getContext(),11);
        dp13 = (int) dp2px(getContext(),13);
        dp16 = (int) dp2px(getContext(),16);

        mPaint.setColor(Color.BLACK);
        mPaint.setTextSize(dp11);

        if (Build.VERSION.SDK_INT >= 23) {
            mPaintBackground.setColor(ContextCompat.getColor(getContext(), R.color.textBgr_list));
            mPaintBackgroundToday.setColor(ContextCompat.getColor(getContext(),R.color.colorAccent));
            mPaintBackgroundEvent.setColor(ContextCompat.getColor(getContext(),R.color.colorPrimary));
        }else{
            mPaintBackground.setColor(getContext().getResources().getColor(R.color.textBgr_list));
            mPaintBackgroundToday.setColor(getContext().getResources().getColor(R.color.colorAccent));
            mPaintBackgroundEvent.setColor(getContext().getResources().getColor(R.color.colorPrimary));
        }
        setClickable(true);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();

                switch (action) {
                    case MotionEvent.ACTION_DOWN:
                        rect = new Rect(v.getLeft(), v.getTop(), v.getRight(), v.getBottom());
                        isTouchMode = true;
                        break;
                    case MotionEvent.ACTION_UP:
                        if (isTouchMode) {
                            ((CalendarWeekView) getParent()).setCurrentSelectedView(v);
                            isTouchMode = false;
                        }
                        break;
                    case MotionEvent.ACTION_CANCEL:
                        isTouchMode = false;
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (!rect.contains(v.getLeft() + (int) event.getX(), v.getTop() + (int) event.getY())) {
                            isTouchMode = false;
                            return true;
                        }
                        break;
                }
                return false;
            }
        });
        setPadding(30, 0, 30, 0);
    }
    @Override
    public void setBackgroundResource(int resid) {
        super.setBackgroundResource(resid);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /**
         * Canvas에 중점 구하기.
         * Paint는 Canvas에 그리는 작업을 하는 객체
         * - ascent 는 baseline 위로의 크기이며 descent 는 밑으로의 크기, 두개를 합치면 높이
         */

        int xPos = canvas.getWidth() / 2;
        ///< 실제 그리는 문자나 숫자의 중간이 중점에 위치하기 위해서 Paint의 높이의 절반 만큼을 뺀다.
        int yPos = (int)((canvas.getHeight() /2) - ((mPaint.descent() + mPaint.ascent()) / 2));
        int yPos2 = (int)((canvas.getHeight() /2));
        ///< 문자는 가운데 정렬로 그린다.
        mPaint.setTextAlign(Paint.Align.CENTER);
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(millis);

        CalendarWeekView calendarWeekView = (CalendarWeekView) getParent();
        if(calendarWeekView.getParent() instanceof ViewPager) {
            ViewGroup parent = (ViewPager) calendarWeekView.getParent();
            CalendarWeekItemView itemView = (CalendarWeekItemView) parent.getTag();

            if (!isStaticText && itemView != null && itemView.getTag() != null && itemView.getTag() instanceof Long) {
                long millis2 = (long) itemView.getTag();

                if (isSameDay(millis2, this.millis)) {
                    if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                        canvas.drawCircle(xPos, yPos2, dp13,mPaintBackground);
                    }else{
                        canvas.drawCircle(xPos, yPos2, dp13,mPaintBackground);
                    }
                }
            }
        }
//        if (!isStaticText && isToday(millis)) {
//            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//                canvas.drawRect(xPos - dp16, getHeight() / 2 - dp16, xPos + dp16, getHeight() / 2 + dp16, mPaintBackgroundToday);
//            }else{
//                canvas.drawRect(xPos - dp16, getHeight() / 2 - dp16, xPos + dp16, getHeight() / 2 + dp16,  mPaintBackgroundToday);
//            }
//        }

        if (isStaticText) {
            // 요일 표시
            mPaint.setTypeface(Typeface.create((String)null, Typeface.NORMAL));
            if(dayOfWeek == 0) {
                mPaint.setColor(ContextCompat.getColor(getContext(),R.color.textColor_highlight_ngt));
            }else if(dayOfWeek == 6){
                mPaint.setColor(ContextCompat.getColor(getContext(),R.color.textColor_highlight_pgt));
            }
            canvas.drawText(CalendarWeekView.DAY_OF_WEEK[dayOfWeek], xPos, yPos, mPaint);
        } else {
            // 날짜 표시
            mPaint.setTypeface(Typeface.create(Typeface.DEFAULT, Typeface.NORMAL));
            mPaint.setColor(ContextCompat.getColor(getContext(),R.color.textColor_deep));
            if(dayOfWeek == 0) {
                mPaint.setColor(ContextCompat.getColor(getContext(),R.color.textColor_highlight_ngt));
            }else if(dayOfWeek == 6){
                mPaint.setColor(ContextCompat.getColor(getContext(),R.color.textColor_highlight_pgt));
            }

            canvas.drawText((calendar.get(Calendar.MONTH) + 1) + "/" + calendar.get(Calendar.DATE), xPos, yPos, mPaint);
        }

        if (hasEvent) {
            if (Build.VERSION.SDK_INT >= 23) {
                mPaintBackgroundEvent.setColor(ContextCompat.getColor(getContext(),mColorEvents[0]));
            }else{
                mPaintBackgroundEvent.setColor(getResources().getColor(mColorEvents[0]));
            }

            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawRect(xPos - 5, getHeight() / 2 + 20, xPos + 5, getHeight() / 2 + 30, mPaintBackground);
            }else{
                canvas.drawRect(xPos - 5, getHeight() / 2 + 20, xPos + 5, getHeight() / 2 + 30, mPaintBackground);
            }
        }

        if(hasBid){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                canvas.drawRect(xPos - 5, getHeight() / 2 + 20, xPos + 5, getHeight() / 2 + 30, mPaintBackground);
            }else{
                canvas.drawRect(xPos - 5, getHeight() / 2 + 20, xPos + 5, getHeight() / 2 + 30, mPaintBackground);
            }
        }
    }

    private boolean isToday(long millis) {
        Calendar cal1 = Calendar.getInstance();
        return isSameDay(cal1.getTimeInMillis(), millis);

    }

    public void setDate(long millis) {
        this.millis = millis;
        setTag(millis);
    }

    public void setDayOfWeek(int dayOfWeek) {
        if(dayOfWeek > 6){
            isStaticText = false;
            this.dayOfWeek = dayOfWeek % 7;
        }else{
            isStaticText = false;
            this.dayOfWeek = dayOfWeek;
        }
    }

    public void setEvent(int... resid) {
        hasEvent = true;
        mColorEvents = resid;
    }
    public boolean isStaticText() {
        return isStaticText;
    }

    public boolean isSameDay(long millis1, long millis2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTimeInMillis(millis1);
        cal2.setTimeInMillis(millis2);
        return (cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR) && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH) && cal1.get(Calendar.DATE) == cal2.get(Calendar.DATE));
    }

    public static float dp2px(Context context, float dp) {
        return dp * context.getResources().getDisplayMetrics().density;
    }

    public void setBid(boolean hasBid){
        this.hasBid = hasBid;
        CalendarWeekView parent = (CalendarWeekView)getParent();
        parent.setCurrentViewHasBid(this);
    }

    public boolean hasBid(){
        return hasBid;
    }

    public void setToday(CalendarWeekView calendarWeekView){

        if(isToday(millis)){
            ViewGroup parent = (ViewPager) calendarWeekView.getParent();
            CalendarWeekItemView itemView = (CalendarWeekItemView) parent.getTag();
            if(itemView == null || !isToday((Long) itemView.getTag())){
                calendarWeekView.setCurrentSelectedView(this);
            }
        }
    }
}