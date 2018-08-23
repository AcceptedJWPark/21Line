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
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Library_BusinessCondition_Activity extends AppCompatActivity {

    Context mContext;


    ListView lv_businesscondition;
    Library_BusinessCondition_LVAdapter adapter;
    ArrayList<Library_BusinessCondition_Listitem> arrayList;

    Button btn1;
    Button btn2;
    Button btn3;
    Button btn4;
    Button btn5;
    Button btn6;
    Button btn7;
    Button btn8;

    BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.library_businesscondition);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("업종별 경영상태 평균비율");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ((ImageView)findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        lv_businesscondition = findViewById(R.id.lv_inc3);

        arrayList = new ArrayList<>();
        adapter = new Library_BusinessCondition_LVAdapter(mContext,arrayList);

        arrayList.add(new Library_BusinessCondition_Listitem("실내건축","80.10%","148.98%"));
        arrayList.add(new Library_BusinessCondition_Listitem("토공","82.89%","158.43%"));
        arrayList.add(new Library_BusinessCondition_Listitem("습식·방수","83.31%","108.28%"));
        arrayList.add(new Library_BusinessCondition_Listitem("석공","59.75%","244.48%"));
        arrayList.add(new Library_BusinessCondition_Listitem("도장","70.50%","118.92%"));
        arrayList.add(new Library_BusinessCondition_Listitem("비계구조물해체","81.65%","120.71%"));
        arrayList.add(new Library_BusinessCondition_Listitem("금속구조물창호","80.33%","145.46%"));
        arrayList.add(new Library_BusinessCondition_Listitem("지붕판금건축물조립","90.28%","93.58%"));
        arrayList.add(new Library_BusinessCondition_Listitem("철근·콘크리트","77.46%","168.26%"));
        arrayList.add(new Library_BusinessCondition_Listitem("상·하수도","73.02%","176.76%"));
        arrayList.add(new Library_BusinessCondition_Listitem("보링","74.80%","188.23%"));
        arrayList.add(new Library_BusinessCondition_Listitem("철도·궤도","58.05%","173.02%"));
        arrayList.add(new Library_BusinessCondition_Listitem("포장","62.10%","226.20%"));
        arrayList.add(new Library_BusinessCondition_Listitem("수중","73.62%","142.29%"));
        arrayList.add(new Library_BusinessCondition_Listitem("조경식재","93.92%","120.70%"));
        arrayList.add(new Library_BusinessCondition_Listitem("조경시설","79.02%","111.45%"));
        arrayList.add(new Library_BusinessCondition_Listitem("강구조물","111.99%","117.73%"));
        arrayList.add(new Library_BusinessCondition_Listitem("철강재","140.91%","113.53%"));
        arrayList.add(new Library_BusinessCondition_Listitem("삭도","124.86%","62.69%"));
        arrayList.add(new Library_BusinessCondition_Listitem("준설","180.38%","120.32%"));
        arrayList.add(new Library_BusinessCondition_Listitem("승강기","86.08%","110.72%"));

        lv_businesscondition.setAdapter(adapter);

        btn1 = findViewById(R.id.btn1_library);
        btn2 = findViewById(R.id.btn2_library);
        btn3 = findViewById(R.id.btn3_library);
        btn4 = findViewById(R.id.btn4_library);
        btn5 = findViewById(R.id.btn5_library);
        btn6 = findViewById(R.id.btn6_library);
        btn7 = findViewById(R.id.btn7_library);
        btn8 = findViewById(R.id.btn8_library);

        clickBackground(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8);
        findViewById(R.id.inc1_library).setVisibility(View.VISIBLE);
        findViewById(R.id.inc2_library).setVisibility(View.GONE);
        findViewById(R.id.inc3_library).setVisibility(View.GONE);
        findViewById(R.id.inc4_library).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv1_inc1_library)).setText("117.02%");
        ((TextView)findViewById(R.id.tv1_inc2_library)).setText("142.35%");
        ((TextView)findViewById(R.id.tv_txt1_dl)).setText("종합건설업종");



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv_txt1_dl)).setText("종합건설업종");
                clickBackground(btn1,btn2,btn3,btn4,btn5,btn6,btn7,btn8);
                findViewById(R.id.inc1_library).setVisibility(View.VISIBLE);
                findViewById(R.id.inc2_library).setVisibility(View.GONE);
                findViewById(R.id.inc3_library).setVisibility(View.GONE);
                findViewById(R.id.inc4_library).setVisibility(View.GONE);
                ((TextView)findViewById(R.id.tv1_inc1_library)).setText("117.02%");
                ((TextView)findViewById(R.id.tv1_inc2_library)).setText("142.35%");
                ((TextView)findViewById(R.id.tv_inc1)).setText("적용일자 : 2018.7.1 입찰공고부터 적용");
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv_txt1_dl)).setText("전문건설업종");
                clickBackground(btn2,btn1,btn3,btn4,btn5,btn6,btn7,btn8);
                findViewById(R.id.inc1_library).setVisibility(View.GONE);
                findViewById(R.id.inc2_library).setVisibility(View.GONE);
                findViewById(R.id.inc3_library).setVisibility(View.VISIBLE);
                findViewById(R.id.inc4_library).setVisibility(View.GONE);
                ((TextView)findViewById(R.id.tv1_inc1_library)).setText("117.02%");
                ((TextView)findViewById(R.id.tv1_inc2_library)).setText("142.35%");
                ((TextView)findViewById(R.id.tv_inc3)).setText("적용일자 : 2018.7.2 입찰공고부터 적용");
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv_txt1_dl)).setText("기계설비공사업 및 가스시설시공업종");
                clickBackground(btn3,btn2,btn1,btn4,btn5,btn6,btn7,btn8);
                findViewById(R.id.inc1_library).setVisibility(View.GONE);
                findViewById(R.id.inc2_library).setVisibility(View.VISIBLE);
                findViewById(R.id.inc3_library).setVisibility(View.GONE);
                findViewById(R.id.inc4_library).setVisibility(View.GONE);
                ((TextView)findViewById(R.id.tv_inc2)).setText("적용일자 : 2018.7.2 입찰공고부터 적용");
            }
        });


        btn4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv_txt1_dl)).setText("시설물유지관리업");
                clickBackground(btn4,btn2,btn3,btn1,btn5,btn6,btn7,btn8);
                findViewById(R.id.inc1_library).setVisibility(View.VISIBLE);
                findViewById(R.id.inc2_library).setVisibility(View.GONE);
                findViewById(R.id.inc3_library).setVisibility(View.GONE);
                findViewById(R.id.inc4_library).setVisibility(View.GONE);
                ((TextView)findViewById(R.id.tv1_inc1_library)).setText("77.99%");
                ((TextView)findViewById(R.id.tv1_inc2_library)).setText("193.42%");
                ((TextView)findViewById(R.id.tv_inc1)).setText("적용일자 : 2018.7.2 입찰공고부터 적용");
            }
        });

        btn5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv_txt1_dl)).setText("소방시설공사업");
                clickBackground(btn5,btn2,btn3,btn4,btn1,btn6,btn7,btn8);
                findViewById(R.id.inc1_library).setVisibility(View.VISIBLE);
                findViewById(R.id.inc2_library).setVisibility(View.GONE);
                findViewById(R.id.inc3_library).setVisibility(View.GONE);
                findViewById(R.id.inc4_library).setVisibility(View.GONE);
                ((TextView)findViewById(R.id.tv1_inc1_library)).setText("120.82%");
                ((TextView)findViewById(R.id.tv1_inc2_library)).setText("135.42%");
                ((TextView)findViewById(R.id.tv_inc1)).setText("적용일자 : 2018.7.31 입찰공고부터 적용");
            }
        });

        btn6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv_txt1_dl)).setText("전기공사업");
                clickBackground(btn6,btn2,btn3,btn4,btn5,btn1,btn7,btn8);
                findViewById(R.id.inc1_library).setVisibility(View.VISIBLE);
                findViewById(R.id.inc2_library).setVisibility(View.GONE);
                findViewById(R.id.inc3_library).setVisibility(View.GONE);
                findViewById(R.id.inc4_library).setVisibility(View.GONE);
                ((TextView)findViewById(R.id.tv1_inc1_library)).setText("150.93%");
                ((TextView)findViewById(R.id.tv1_inc2_library)).setText("136.69%");
                ((TextView)findViewById(R.id.tv_inc1)).setText("적용일자 : 2018.7.31 입찰공고부터 적용");
            }
        });

        btn7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv_txt1_dl)).setText("정보통신공사업");
                clickBackground(btn7,btn2,btn3,btn4,btn5,btn6,btn1,btn8);
                findViewById(R.id.inc1_library).setVisibility(View.VISIBLE);
                findViewById(R.id.inc2_library).setVisibility(View.GONE);
                findViewById(R.id.inc3_library).setVisibility(View.GONE);
                findViewById(R.id.inc4_library).setVisibility(View.GONE);
                ((TextView)findViewById(R.id.tv1_inc1_library)).setText("148.29%");
                ((TextView)findViewById(R.id.tv1_inc2_library)).setText("124.10%");
                ((TextView)findViewById(R.id.tv_inc1)).setText("적용일자 : 2018.7.31 입찰공고부터 적용");
            }
        });

        btn8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((TextView)findViewById(R.id.tv_txt1_dl)).setText("일반용역");
                clickBackground(btn8,btn2,btn3,btn4,btn5,btn6,btn7,btn1);
                findViewById(R.id.inc1_library).setVisibility(View.GONE);
                findViewById(R.id.inc2_library).setVisibility(View.GONE);
                findViewById(R.id.inc3_library).setVisibility(View.GONE);
                findViewById(R.id.inc4_library).setVisibility(View.VISIBLE);
            }
        });


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


    private void clickBackground(Button btn1, Button btn2, Button btn3, Button btn4, Button btn5, Button btn6, Button btn7, Button btn8)
    {
        btn1.setBackgroundResource(R.drawable.bgr_btnbox_home);
        btn1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn1.setTypeface(null, Typeface.BOLD);
        btn1.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked_Exep));


        btn2.setTypeface(null, Typeface.NORMAL);
        btn3.setTypeface(null, Typeface.NORMAL);
        btn4.setTypeface(null, Typeface.NORMAL);
        btn5.setTypeface(null, Typeface.NORMAL);
        btn6.setTypeface(null, Typeface.NORMAL);
        btn7.setTypeface(null, Typeface.NORMAL);
        btn8.setTypeface(null, Typeface.NORMAL);

        btn2.setBackgroundResource(R.drawable.bgr_btnbox_home);
        btn3.setBackgroundResource(R.drawable.bgr_btnbox_home);
        btn4.setBackgroundResource(R.drawable.bgr_btnbox_home);
        btn5.setBackgroundResource(R.drawable.bgr_btnbox_home);
        btn6.setBackgroundResource(R.drawable.bgr_btnbox_home);
        btn7.setBackgroundResource(R.drawable.bgr_btnbox_home);
        btn8.setBackgroundResource(R.drawable.bgr_btnbox_home);

        btn8.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn7.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn2.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn3.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn4.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn5.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn6.setTextColor(getResources().getColor(R.color.textColor_unclicked));

        btn8.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked_Exep));
        btn7.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked_Exep));
        btn2.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked_Exep));
        btn3.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked_Exep));
        btn4.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked_Exep));
        btn5.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked_Exep));
        btn6.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked_Exep));

    }

}
