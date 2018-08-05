package com.mobile.a21line.Bid;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.a21line.R;

/**
 * Created by Accepted on 2018-06-08.
 */

public class Bid_Analysis_Result_Popup extends AppCompatActivity {

    Context mContext;
    ImageView iv_close;

    boolean isMemo;

    String sendMoney, sendPercent, Memo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bid_analysis_detail_result);
        mContext = getApplicationContext();

        sendMoney = getIntent().getStringExtra("sendMoney");
        sendPercent = getIntent().getStringExtra("sendPercent");
        Memo = getIntent().getStringExtra("Memo");

        if(Memo.isEmpty()){
            findViewById(R.id.ll_analysis_detail_request_memo).setVisibility(View.GONE);
        }else{
            ((TextView)findViewById(R.id.tv_analysis_detail_request_memo)).setText(Memo);
        }

        ((TextView)findViewById(R.id.tv_analResult_percent)).setText(sendPercent);
        ((TextView)findViewById(R.id.tv_analResult_tuchalMoney)).setText(sendMoney);


        iv_close = findViewById(R.id.iv_cancel_analysisresult);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        isMemo = true;

        if(isMemo)
        {
            ((LinearLayout)findViewById(R.id.ll_memo_ananlysis_detail_result)).setVisibility(View.VISIBLE);

        }
        else
        {
            ((LinearLayout)findViewById(R.id.ll_memo_ananlysis_detail_result)).setVisibility(View.GONE);

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




}


