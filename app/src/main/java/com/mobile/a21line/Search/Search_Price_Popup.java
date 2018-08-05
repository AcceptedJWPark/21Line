package com.mobile.a21line.Search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;


public class Search_Price_Popup extends AppCompatActivity {

    private Button btn_dialog;

    EditText et_price1;
    EditText et_price2;
    Context mContext;

    TextView tv_price1;
    TextView tv_price2;
    String SearchMoneyType;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_price_popup);

        mContext = getApplicationContext();

        SearchMoneyType = getIntent().getStringExtra("SearchMoneyType");

        et_price1 = findViewById(R.id.et_price1_search);
        et_price2 = findViewById(R.id.et_price2_search);

        et_price1.setText(getIntent().getStringExtra("SMoney"));
        et_price2.setText(getIntent().getStringExtra("EMoney"));

        et_price1.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_price2.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});

        ((ImageView)findViewById(R.id.iv_cancel_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        findViewById(R.id.btn_search_under1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_price1.setText("0");
                et_price2.setText("1");
            }
        });

        findViewById(R.id.btn_search_1to5).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_price1.setText("1");
                et_price2.setText("5");
            }
        });

        findViewById(R.id.btn_search_5to10).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_price1.setText("5");
                et_price2.setText("10");
            }
        });

        findViewById(R.id.btn_search_10to50).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_price1.setText("10");
                et_price2.setText("50");
            }
        });

        findViewById(R.id.btn_search_over50).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et_price1.setText("50");
                et_price2.setText("9999");
            }
        });

        btn_dialog = findViewById(R.id.btn_save_price_search);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("SMoney", et_price1.getText().toString());
                intent.putExtra("EMoney", et_price2.getText().toString());
                intent.putExtra("SearchMoneyType", SearchMoneyType);
                setResult(RESULT_OK, intent);
                finish();
            }
        });

        tv_price1 =findViewById (R.id.tv_price1_price_search);
        tv_price2 =findViewById (R.id.tv_price2_price_search);

        if(SearchMoneyType.equals("EstimatedPrice")){
            tv_price1.setTextColor(Color.BLACK);
            tv_price2.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
        }else{
            tv_price2.setTextColor(Color.BLACK);
            tv_price1.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
        }
        tv_price1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchMoneyType = "EstimatedPrice";
                tv_price1.setTextColor(Color.BLACK);
                tv_price2.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
            }
        });

        tv_price2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchMoneyType = "BasicPrice";
                tv_price2.setTextColor(Color.BLACK);
                tv_price1.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
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



}

