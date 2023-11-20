package com.mobile.a21line.Result;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Result_Popup_Sorting extends AppCompatActivity {

    Context mContext;

    EditText et_SDate, et_EDate;

    Button btn_searchbox_save;

    String SortType = "RegDTime";


    ImageView iv_periodIcon1;
    ImageView iv_periodIcon2;
    ImageView iv_periodIcon3;
    ImageView iv_periodIcon4;
    ImageView iv_periodIcon5;
    ImageView iv_sortingIcon1;
    ImageView iv_sortingIcon2;
    ImageView iv_sortingIcon3;
    ImageView iv_sortingIcon4;


    RelativeLayout rl_searchbox_period1;
    RelativeLayout rl_searchbox_period2;
    RelativeLayout rl_searchbox_period3;
    RelativeLayout rl_searchbox_period4;
    RelativeLayout rl_searchbox_period5;

    RelativeLayout rl_searchbox_sorting1;
    RelativeLayout rl_searchbox_sorting2;
    RelativeLayout rl_searchbox_sorting3;
    RelativeLayout rl_searchbox_sorting4;




    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.result_sorting);


        mContext = getApplicationContext();

        final Activity activity = this;

        btn_searchbox_save = findViewById(R.id.btn_searchbox_save_result);
        btn_searchbox_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"검색조건이 적용되었습니다.",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent();
                intent.putExtra("SDate", et_SDate.getText().toString());
                intent.putExtra("EDate", et_EDate.getText().toString());
                intent.putExtra("SortType", SortType);
                activity.setResult(RESULT_OK, intent);
                finish();
            }
        });

        et_SDate = (EditText)findViewById(R.id.et_SDate_result);
        et_EDate = (EditText)findViewById(R.id.et_EDate_result);

        et_SDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_EDate.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {SaveSharedPreference.hideKeyboard(v,mContext);}}});


        et_SDate.setText(getIntent().getStringExtra("SDate"));
        et_EDate.setText(getIntent().getStringExtra("EDate"));

        SortType = getIntent().getStringExtra("SortType");

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
            }
        });

        rl_searchbox_period2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(1, et_EDate.getText().toString()));
            }
        });

        rl_searchbox_period3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(3, et_EDate.getText().toString()));
            }
        });

        rl_searchbox_period4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(6, et_EDate.getText().toString()));
            }
        });

        rl_searchbox_period5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(12, et_EDate.getText().toString()));
            }
        });


        rl_searchbox_sorting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "ResultDTime";
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
                SortType = "ERDDTime";
                sortinglistClicked(iv_sortingIcon3,iv_sortingIcon2,iv_sortingIcon1,iv_sortingIcon4);
            }
        });
        rl_searchbox_sorting4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "RegDTime";
                sortinglistClicked(iv_sortingIcon4,iv_sortingIcon3,iv_sortingIcon2,iv_sortingIcon1);
            }
        });

        switch(SortType){
            case "ResultDTime":
                rl_searchbox_sorting1.performClick();
                break;
            case "OpenDTime":
                rl_searchbox_sorting2.performClick();
                break;
            case "ERDDTime":
                rl_searchbox_sorting3.performClick();
                break;
            case "RegDTime":
                rl_searchbox_sorting4.performClick();
                break;
        }


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            return false;
        }
        return super.dispatchTouchEvent(ev);
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


    public void sortinglistClicked(View view1,View view2,View view3,View view4)
    {
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
        view4.setVisibility(View.GONE);
    }

}

