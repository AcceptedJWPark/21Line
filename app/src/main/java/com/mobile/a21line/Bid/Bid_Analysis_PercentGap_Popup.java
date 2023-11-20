package com.mobile.a21line.Bid;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.a21line.R;


public class Bid_Analysis_PercentGap_Popup extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bid_analysis_percentgap);

        final Activity activity = this;
        mContext = getApplicationContext();


        ((Button)findViewById(R.id.btn_cancel_percentgap)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }
}

