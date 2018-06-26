package com.mobile.a21line.Search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Search_Period_Popup extends AppCompatActivity {

    private Button btn_dialog;

    EditText et_period1;
    EditText et_period2;
    Context mContext;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_period_popup);

        mContext = getApplicationContext();

        et_period1 = findViewById(R.id.et_period1_search);
        et_period2 = findViewById(R.id.et_period2_search);

        et_period1.setText(getIntent().getStringExtra("SDate"));
        et_period2.setText(getIntent().getStringExtra("EDate"));

        et_period1.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_period2.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});

        findViewById(R.id.btn_search_today).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_period1.setText(getMonthAgoDate(0));
                et_period2.setText(getMonthAgoDate(0));
            }
        });

        findViewById(R.id.btn_search_1month).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_period1.setText(getMonthAgoDate(1));
                et_period2.setText(getMonthAgoDate(0));
            }
        });

        findViewById(R.id.btn_search_3month).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_period1.setText(getMonthAgoDate(3));
                et_period2.setText(getMonthAgoDate(0));
            }
        });

        findViewById(R.id.btn_search_6month).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_period1.setText(getMonthAgoDate(6));
                et_period2.setText(getMonthAgoDate(0));
            }
        });

        findViewById(R.id.btn_search_12month).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_period1.setText(getMonthAgoDate(12));
                et_period2.setText(getMonthAgoDate(0));
            }
        });

        btn_dialog = findViewById(R.id.btn_save_period_search);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("SDate", et_period1.getText().toString());
                intent.putExtra("EDate", et_period2.getText().toString());
                setResult(RESULT_OK, intent);
                finish();
            }
        });

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

}

