package com.mobile.a21line.Library;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mobile.a21line.R;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Library_BidLimitPercent_Activity extends AppCompatActivity {

    Context mContext;

    Button btn_cons;
    Button btn_serv;
    Button btn_purc;

    TextView[] tv_cons_click;
    TextView[] tv_serv_click;
    TextView[] tv_purc_click;

    View [] inc_cons;
    View [] inc_serv;
    View [] inc_purc;

    ScrollView sv_cons;
    ScrollView sv_serv;
    ScrollView sv_purc;

    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.library_bidlimitpercent);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("발주처별 투찰하한율");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_cons = findViewById(R.id.btn_cons_bidlimitpercent);
        btn_serv = findViewById(R.id.btn_serv_bidlimitpercent);
        btn_purc = findViewById(R.id.btn_purc_bidlimitpercent);

        btn_cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(btn_cons,btn_serv,btn_purc);
                ((LinearLayout)findViewById(R.id.ll_cons_bidlimitpercent)).setVisibility(View.VISIBLE);
                ((LinearLayout)findViewById(R.id.ll_serv_bidlimitpercent)).setVisibility(View.GONE);
                ((LinearLayout)findViewById(R.id.ll_purc_bidlimitpercent)).setVisibility(View.GONE);
                ((LinearLayout)findViewById(R.id.ll_inc_container_cons_bidmitpercent)).setVisibility(View.VISIBLE);
                ((LinearLayout)findViewById(R.id.ll_inc_container_serv_bidmitpercent)).setVisibility(View.GONE);
                ((LinearLayout)findViewById(R.id.ll_inc_container_purc_bidmitpercent)).setVisibility(View.GONE);
                clickBackground_cons(0);
            }
        });

        btn_serv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(btn_serv,btn_cons,btn_purc);
                ((LinearLayout)findViewById(R.id.ll_cons_bidlimitpercent)).setVisibility(View.GONE);
                ((LinearLayout)findViewById(R.id.ll_serv_bidlimitpercent)).setVisibility(View.VISIBLE);
                ((LinearLayout)findViewById(R.id.ll_purc_bidlimitpercent)).setVisibility(View.GONE);

                ((LinearLayout)findViewById(R.id.ll_inc_container_cons_bidmitpercent)).setVisibility(View.GONE);
                ((LinearLayout)findViewById(R.id.ll_inc_container_serv_bidmitpercent)).setVisibility(View.VISIBLE);
                ((LinearLayout)findViewById(R.id.ll_inc_container_purc_bidmitpercent)).setVisibility(View.GONE);
                clickBackground_serv(0);
            }
        });

        btn_purc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickBackground(btn_purc,btn_serv,btn_cons);
                ((LinearLayout)findViewById(R.id.ll_cons_bidlimitpercent)).setVisibility(View.GONE);
                ((LinearLayout)findViewById(R.id.ll_serv_bidlimitpercent)).setVisibility(View.GONE);
                ((LinearLayout)findViewById(R.id.ll_purc_bidlimitpercent)).setVisibility(View.VISIBLE);

                ((LinearLayout)findViewById(R.id.ll_inc_container_cons_bidmitpercent)).setVisibility(View.GONE);
                ((LinearLayout)findViewById(R.id.ll_inc_container_serv_bidmitpercent)).setVisibility(View.GONE);
                ((LinearLayout)findViewById(R.id.ll_inc_container_purc_bidmitpercent)).setVisibility(View.VISIBLE);
                clickBackground_purc(0);
            }
        });

        sv_cons = findViewById(R.id.sv_cons_bidmitpercent);
        sv_serv = findViewById(R.id.sv_serv_bidmitpercent);
        sv_purc = findViewById(R.id.sv_purc_bidmitpercent);

        tv_cons_click = new TextView[13];
        tv_serv_click = new TextView[15];
        tv_purc_click = new TextView[16];
        inc_cons = new View[13];
        inc_serv = new View[15];
        inc_purc = new View[16];

        tv_cons_click[0] = findViewById(R.id.tv_cons1_click);
        tv_cons_click[1] = findViewById(R.id.tv_cons2_click);
        tv_cons_click[2] = findViewById(R.id.tv_cons3_click);
        tv_cons_click[3] = findViewById(R.id.tv_cons4_click);
        tv_cons_click[4] = findViewById(R.id.tv_cons5_click);
        tv_cons_click[5] = findViewById(R.id.tv_cons6_click);
        tv_cons_click[6] = findViewById(R.id.tv_cons7_click);
        tv_cons_click[7] = findViewById(R.id.tv_cons8_click);
        tv_cons_click[8] = findViewById(R.id.tv_cons9_click);
        tv_cons_click[9] = findViewById(R.id.tv_cons10_click);
        tv_cons_click[10] = findViewById(R.id.tv_cons11_click);
        tv_cons_click[11] = findViewById(R.id.tv_cons12_click);
        tv_cons_click[12] = findViewById(R.id.tv_cons13_click);
        inc_cons[0] = findViewById(R.id.inc1_cons_bidmitpercent);
        inc_cons[1] = findViewById(R.id.inc2_cons_bidmitpercent);
        inc_cons[2] = findViewById(R.id.inc3_cons_bidmitpercent);
        inc_cons[3] = findViewById(R.id.inc4_cons_bidmitpercent);
        inc_cons[4] = findViewById(R.id.inc5_cons_bidmitpercent);
        inc_cons[5] = findViewById(R.id.inc6_cons_bidmitpercent);
        inc_cons[6] = findViewById(R.id.inc7_cons_bidmitpercent);
        inc_cons[7] = findViewById(R.id.inc8_cons_bidmitpercent);
        inc_cons[8] = findViewById(R.id.inc9_cons_bidmitpercent);
        inc_cons[9] = findViewById(R.id.inc10_cons_bidmitpercent);
        inc_cons[10] = findViewById(R.id.inc11_cons_bidmitpercent);
        inc_cons[11] = findViewById(R.id.inc12_cons_bidmitpercent);
        inc_cons[12] = findViewById(R.id.inc13_cons_bidmitpercent);

        for(int i=0; i<13; i++)
        {
            final int finalI = i;
            tv_cons_click[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickBackground_cons(finalI);
                    sv_cons.fullScroll(View.FOCUS_UP);
                }
            });
        }


        tv_serv_click[0] = findViewById(R.id.tv_serv1_click);
        tv_serv_click[1] = findViewById(R.id.tv_serv2_click);
        tv_serv_click[2] = findViewById(R.id.tv_serv3_click);
        tv_serv_click[3] = findViewById(R.id.tv_serv4_click);
        tv_serv_click[4] = findViewById(R.id.tv_serv5_click);
        tv_serv_click[5] = findViewById(R.id.tv_serv6_click);
        tv_serv_click[6] = findViewById(R.id.tv_serv7_click);
        tv_serv_click[7] = findViewById(R.id.tv_serv8_click);
        tv_serv_click[8] = findViewById(R.id.tv_serv9_click);
        tv_serv_click[9] = findViewById(R.id.tv_serv10_click);
        tv_serv_click[10] = findViewById(R.id.tv_serv11_click);
        tv_serv_click[11] = findViewById(R.id.tv_serv12_click);
        tv_serv_click[12] = findViewById(R.id.tv_serv13_click);
        tv_serv_click[13] = findViewById(R.id.tv_serv14_click);
        tv_serv_click[14] = findViewById(R.id.tv_serv15_click);

        inc_serv[0] = findViewById(R.id.inc1_serv_bidmitpercent);
        inc_serv[1] = findViewById(R.id.inc2_serv_bidmitpercent);
        inc_serv[2] = findViewById(R.id.inc3_serv_bidmitpercent);
        inc_serv[3] = findViewById(R.id.inc4_serv_bidmitpercent);
        inc_serv[4] = findViewById(R.id.inc5_serv_bidmitpercent);
        inc_serv[5] = findViewById(R.id.inc6_serv_bidmitpercent);
        inc_serv[6] = findViewById(R.id.inc7_serv_bidmitpercent);
        inc_serv[7] = findViewById(R.id.inc8_serv_bidmitpercent);
        inc_serv[8] = findViewById(R.id.inc9_serv_bidmitpercent);
        inc_serv[9] = findViewById(R.id.inc10_serv_bidmitpercent);
        inc_serv[10] = findViewById(R.id.inc11_serv_bidmitpercent);
        inc_serv[11] = findViewById(R.id.inc12_serv_bidmitpercent);
        inc_serv[12] = findViewById(R.id.inc13_serv_bidmitpercent);
        inc_serv[13] = findViewById(R.id.inc14_serv_bidmitpercent);
        inc_serv[14] = findViewById(R.id.inc15_serv_bidmitpercent);

        for(int i=0; i<15; i++)
        {
            final int finalI = i;
            tv_serv_click[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickBackground_serv(finalI);
                    sv_serv.fullScroll(View.FOCUS_UP);
                }
            });
        }

        tv_purc_click[0] = findViewById(R.id.tv_purc1_click);
        tv_purc_click[1] = findViewById(R.id.tv_purc2_click);
        tv_purc_click[2] = findViewById(R.id.tv_purc3_click);
        tv_purc_click[3] = findViewById(R.id.tv_purc4_click);
        tv_purc_click[4] = findViewById(R.id.tv_purc5_click);
        tv_purc_click[5] = findViewById(R.id.tv_purc6_click);
        tv_purc_click[6] = findViewById(R.id.tv_purc7_click);
        tv_purc_click[7] = findViewById(R.id.tv_purc8_click);
        tv_purc_click[8] = findViewById(R.id.tv_purc9_click);
        tv_purc_click[9] = findViewById(R.id.tv_purc10_click);
        tv_purc_click[10] = findViewById(R.id.tv_purc11_click);
        tv_purc_click[11] = findViewById(R.id.tv_purc12_click);
        tv_purc_click[12] = findViewById(R.id.tv_purc13_click);
        tv_purc_click[13] = findViewById(R.id.tv_purc14_click);
        tv_purc_click[14] = findViewById(R.id.tv_purc15_click);
        tv_purc_click[15] = findViewById(R.id.tv_purc16_click);

        inc_purc[0] = findViewById(R.id.inc1_purc_bidmitpercent);
        inc_purc[1] = findViewById(R.id.inc2_purc_bidmitpercent);
        inc_purc[2] = findViewById(R.id.inc3_purc_bidmitpercent);
        inc_purc[3] = findViewById(R.id.inc4_purc_bidmitpercent);
        inc_purc[4] = findViewById(R.id.inc5_purc_bidmitpercent);
        inc_purc[5] = findViewById(R.id.inc6_purc_bidmitpercent);
        inc_purc[6] = findViewById(R.id.inc7_purc_bidmitpercent);
        inc_purc[7] = findViewById(R.id.inc8_purc_bidmitpercent);
        inc_purc[8] = findViewById(R.id.inc9_purc_bidmitpercent);
        inc_purc[9] = findViewById(R.id.inc10_purc_bidmitpercent);
        inc_purc[10] = findViewById(R.id.inc11_purc_bidmitpercent);
        inc_purc[11] = findViewById(R.id.inc12_purc_bidmitpercent);
        inc_purc[12] = findViewById(R.id.inc13_purc_bidmitpercent);
        inc_purc[13] = findViewById(R.id.inc14_purc_bidmitpercent);
        inc_purc[14] = findViewById(R.id.inc15_purc_bidmitpercent);
        inc_purc[15] = findViewById(R.id.inc16_purc_bidmitpercent);

        for(int i=0; i<16; i++)
        {
            final int finalI = i;
            tv_purc_click[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickBackground_purc(finalI);
                    sv_purc.fullScroll(View.FOCUS_UP);
                }
            });
        }

        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.mobile.a21line.finishActivity");

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                finish();
            }
        };

        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }



    private void clickBackground(Button btn1, Button btn2, Button btn3)
    {
        btn1.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn1.setTypeface(null, Typeface.BOLD);
        btn1.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));

        btn2.setTypeface(null, Typeface.NORMAL);
        btn3.setTypeface(null, Typeface.NORMAL);

        btn2.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn3.setBackgroundResource(R.drawable.bgr_btn_unclicked);

        btn2.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn3.setTextColor(getResources().getColor(R.color.textColor_unclicked));

        btn2.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
        btn3.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
    }

    private void clickBackground_cons(int index)
    {
        for(int i = 0; i < tv_cons_click.length; i++){
            if(i == index){
                tv_cons_click[i].setTextColor(getResources().getColor(R.color.textColor_deep));
                tv_cons_click[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_addition));
                tv_cons_click[i].setTypeface(null, Typeface.BOLD);
                inc_cons[i].setVisibility(View.VISIBLE);
            }else{
                tv_cons_click[i].setTextColor(getResources().getColor(R.color.textColor_addition));
                tv_cons_click[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_addition));
                tv_cons_click[i].setTypeface(null, Typeface.NORMAL);
                inc_cons[i].setVisibility(View.GONE);
            }
        }
    }

    private void clickBackground_serv(int index)
    {
        for(int i = 0; i < tv_serv_click.length; i++){
            if(i == index){
                tv_serv_click[i].setTextColor(getResources().getColor(R.color.textColor_deep));
                tv_serv_click[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_addition));
                tv_serv_click[i].setTypeface(null, Typeface.BOLD);
                inc_serv[i].setVisibility(View.VISIBLE);
            }else{
                tv_serv_click[i].setTextColor(getResources().getColor(R.color.textColor_addition));
                tv_serv_click[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_addition));
                tv_serv_click[i].setTypeface(null, Typeface.NORMAL);
                inc_serv[i].setVisibility(View.GONE);
            }
        }
    }

    private void clickBackground_purc(int index)
    {
        for(int i = 0; i < tv_purc_click.length; i++){
            if(i == index){
                tv_purc_click[i].setTextColor(getResources().getColor(R.color.textColor_deep));
                tv_purc_click[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_addition));
                tv_purc_click[i].setTypeface(null, Typeface.BOLD);
                inc_purc[i].setVisibility(View.VISIBLE);
            }else{
                tv_purc_click[i].setTextColor(getResources().getColor(R.color.textColor_addition));
                tv_purc_click[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_addition));
                tv_purc_click[i].setTypeface(null, Typeface.NORMAL);
                inc_purc[i].setVisibility(View.GONE);
            }
        }
    }


}