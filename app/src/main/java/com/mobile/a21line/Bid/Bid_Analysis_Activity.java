package com.mobile.a21line.Bid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.a21line.R;

/**
 * Created by Accepted on 2018-05-25.
 */

public class Bid_Analysis_Activity extends AppCompatActivity {

    Context mContext;
    LinearLayout[] ll_analysis;
    TextView[] tv_analysis;
    ImageView[] iv_analysis;
    boolean[] checked;

    Button btn_randomNo;
    LinearLayout ll_choiceNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bid_analysis_activity);
        mContext = getApplicationContext();


        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("간편 분석");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setText("초기화");





        ll_analysis = new LinearLayout[15];
        tv_analysis = new TextView[15];
        iv_analysis = new ImageView[15];
        checked = new boolean[15];

        ll_analysis[0] = findViewById(R.id.ll_analysis1_analysis);
        ll_analysis[1] = findViewById(R.id.ll_analysis2_analysis);
        ll_analysis[2] = findViewById(R.id.ll_analysis3_analysis);
        ll_analysis[3] = findViewById(R.id.ll_analysis4_analysis);
        ll_analysis[4] = findViewById(R.id.ll_analysis5_analysis);
        ll_analysis[5] = findViewById(R.id.ll_analysis6_analysis);
        ll_analysis[6] = findViewById(R.id.ll_analysis7_analysis);
        ll_analysis[7] = findViewById(R.id.ll_analysis8_analysis);
        ll_analysis[8] = findViewById(R.id.ll_analysis9_analysis);
        ll_analysis[9] = findViewById(R.id.ll_analysis10_analysis);
        ll_analysis[10] = findViewById(R.id.ll_analysis11_analysis);
        ll_analysis[11] = findViewById(R.id.ll_analysis12_analysis);
        ll_analysis[12] = findViewById(R.id.ll_analysis13_analysis);
        ll_analysis[13] = findViewById(R.id.ll_analysis14_analysis);
        ll_analysis[14] = findViewById(R.id.ll_analysis15_analysis);

        tv_analysis[0] = findViewById(R.id.tv_analysis1_analysis);
        tv_analysis[1] = findViewById(R.id.tv_analysis2_analysis);
        tv_analysis[2] = findViewById(R.id.tv_analysis3_analysis);
        tv_analysis[3] = findViewById(R.id.tv_analysis4_analysis);
        tv_analysis[4] = findViewById(R.id.tv_analysis5_analysis);
        tv_analysis[5] = findViewById(R.id.tv_analysis6_analysis);
        tv_analysis[6] = findViewById(R.id.tv_analysis7_analysis);
        tv_analysis[7] = findViewById(R.id.tv_analysis8_analysis);
        tv_analysis[8] = findViewById(R.id.tv_analysis9_analysis);
        tv_analysis[9] = findViewById(R.id.tv_analysis10_analysis);
        tv_analysis[10] = findViewById(R.id.tv_analysis11_analysis);
        tv_analysis[11] = findViewById(R.id.tv_analysis12_analysis);
        tv_analysis[12] = findViewById(R.id.tv_analysis13_analysis);
        tv_analysis[13] = findViewById(R.id.tv_analysis14_analysis);
        tv_analysis[14] = findViewById(R.id.tv_analysis15_analysis);

        iv_analysis[0] = findViewById(R.id.iv_analysis1_analysis);
        iv_analysis[1] = findViewById(R.id.iv_analysis2_analysis);
        iv_analysis[2] = findViewById(R.id.iv_analysis3_analysis);
        iv_analysis[3] = findViewById(R.id.iv_analysis4_analysis);
        iv_analysis[4] = findViewById(R.id.iv_analysis5_analysis);
        iv_analysis[5] = findViewById(R.id.iv_analysis6_analysis);
        iv_analysis[6] = findViewById(R.id.iv_analysis7_analysis);
        iv_analysis[7] = findViewById(R.id.iv_analysis8_analysis);
        iv_analysis[8] = findViewById(R.id.iv_analysis9_analysis);
        iv_analysis[9] = findViewById(R.id.iv_analysis10_analysis);
        iv_analysis[10] = findViewById(R.id.iv_analysis11_analysis);
        iv_analysis[11] = findViewById(R.id.iv_analysis12_analysis);
        iv_analysis[12] = findViewById(R.id.iv_analysis13_analysis);
        iv_analysis[13] = findViewById(R.id.iv_analysis14_analysis);
        iv_analysis[14] = findViewById(R.id.iv_analysis15_analysis);


        for(int i=0; i<15; i++)
        {
            checked[i] = false;
            final int finalI = i;
            ll_analysis[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!checked[finalI])
                    {
                        tv_analysis[finalI].setVisibility(View.GONE);
                        iv_analysis[finalI].setVisibility(View.VISIBLE);
                        checked[finalI] = true;
                    }else
                    {
                        tv_analysis[finalI].setVisibility(View.VISIBLE);
                        iv_analysis[finalI].setVisibility(View.GONE);
                        checked[finalI] = false;
                    }

                }
            });
        }

        ll_choiceNo = findViewById(R.id.ll_choiceNo_analysis);

        btn_randomNo = findViewById(R.id.btn_randomNo_analysis);
        btn_randomNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ll_choiceNo.setVisibility(View.VISIBLE);
            }
        });

    }


}
