package com.mobile.a21line.Bid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Accepted on 2018-05-25.
 */

public class Bid_Analysis_Activity extends AppCompatActivity {

    Context mContext;
    LinearLayout[] ll_analysis;
    TextView[] tv_analysis;
    TextView[] tv_randMoney;
    TextView[] tv_randRate;
    ImageView[] iv_analysis;
    boolean[] checked;
    double[] arrRate;
    long[] arrMoney;

    TextView tv_analysis_bidTitle;
    TextView tv_analysis_estimatedPrice;



    EditText et_analysis_percent;
    EditText et_analysis_basicMoney;


    EditText et_analysis_rateLow;
    EditText et_analysis_rateHigh;
    EditText et_analysis_low_rate;
    EditText et_analysis_high_rate;



    Button btn_randomNo;
    Button btn_calculate;
    LinearLayout ll_choiceNo;
    int chekcedCount = 0;
    String basicMoney;


    Button btn_ratio;
    Button btn_multiple;

    View listview_header;
    View listview_footer;

    ListView lv_analysis;
    Bid_Analysis_LVAdapter analysis_adapter;
    ArrayList<Bid_Analysis_Listitem> analysis_arraylist;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bid_analysis_activity);
        mContext = getApplicationContext();


        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("낙찰가 간편 분석");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setText("초기화");



        ll_analysis = new LinearLayout[15];
        tv_analysis = new TextView[15];
        tv_randMoney = new TextView[15];
        tv_randRate = new TextView[15];
        iv_analysis = new ImageView[15];
        checked = new boolean[15];
        arrRate = new double[15];
        arrMoney = new long[15];

        tv_analysis_bidTitle = findViewById(R.id.tv_analysis_bidTitle);
        tv_analysis_bidTitle.setText(getIntent().getStringExtra("BidName"));

        tv_analysis_estimatedPrice = findViewById(R.id.tv_analysis_estimatedPrice);
        tv_analysis_estimatedPrice.setText(getIntent().getStringExtra("EstimatedPrice").replace("원", ""));

        basicMoney = getIntent().getStringExtra("BasicMoney").replace("원", "");
        et_analysis_basicMoney = findViewById(R.id.et_analysis_basicMoney);
        et_analysis_basicMoney.setText(basicMoney);
        et_analysis_basicMoney.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty()){
                    et_analysis_basicMoney.setText("0");
                    Selection.setSelection(et_analysis_basicMoney.getText(), 1);
                    return;
                }else {
                    Log.d("changed Money", charSequence.toString());
                    Log.d("beforeChanged", basicMoney);
                    if (!basicMoney.equals(charSequence.toString())) {
                        basicMoney = toNumFormat(charSequence.toString().replace(",", ""));
                        Log.d("formated money", basicMoney);
                        et_analysis_basicMoney.setText(basicMoney);
                        Selection.setSelection(et_analysis_basicMoney.getText(), basicMoney.length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        et_analysis_rateLow = findViewById(R.id.et_analysis_rateLow);
        et_analysis_rateHigh = findViewById(R.id.et_analysis_rateHigh);
        et_analysis_low_rate = findViewById(R.id.et_analysis_low_rate);
        et_analysis_high_rate = findViewById(R.id.et_analysis_high_rate);

        et_analysis_percent = findViewById(R.id.et_analysis_percent);
        et_analysis_percent.setText(getIntent().getStringExtra("CutPercent"));

        String yegaRate = getIntent().getStringExtra("YegaRate");
        String[] arrYega = yegaRate.split(" ~ ");


        et_analysis_rateLow.setText(arrYega[0].replace("%", ""));
        et_analysis_rateHigh.setText("+" + arrYega[1].replace("%", ""));

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

        tv_randRate[0] = findViewById(R.id.tv_analysis1_randRate);
        tv_randRate[1] = findViewById(R.id.tv_analysis2_randRate);
        tv_randRate[2] = findViewById(R.id.tv_analysis3_randRate);
        tv_randRate[3] = findViewById(R.id.tv_analysis4_randRate);
        tv_randRate[4] = findViewById(R.id.tv_analysis5_randRate);
        tv_randRate[5] = findViewById(R.id.tv_analysis6_randRate);
        tv_randRate[6] = findViewById(R.id.tv_analysis7_randRate);
        tv_randRate[7] = findViewById(R.id.tv_analysis8_randRate);
        tv_randRate[8] = findViewById(R.id.tv_analysis9_randRate);
        tv_randRate[9] = findViewById(R.id.tv_analysis10_randRate);
        tv_randRate[10] = findViewById(R.id.tv_analysis11_randRate);
        tv_randRate[11] = findViewById(R.id.tv_analysis12_randRate);
        tv_randRate[12] = findViewById(R.id.tv_analysis13_randRate);
        tv_randRate[13] = findViewById(R.id.tv_analysis14_randRate);
        tv_randRate[14] = findViewById(R.id.tv_analysis15_randRate);

        tv_randMoney[0] = findViewById(R.id.tv_analysis1_randMoney);
        tv_randMoney[1] = findViewById(R.id.tv_analysis2_randMoney);
        tv_randMoney[2] = findViewById(R.id.tv_analysis3_randMoney);
        tv_randMoney[3] = findViewById(R.id.tv_analysis4_randMoney);
        tv_randMoney[4] = findViewById(R.id.tv_analysis5_randMoney);
        tv_randMoney[5] = findViewById(R.id.tv_analysis6_randMoney);
        tv_randMoney[6] = findViewById(R.id.tv_analysis7_randMoney);
        tv_randMoney[7] = findViewById(R.id.tv_analysis8_randMoney);
        tv_randMoney[8] = findViewById(R.id.tv_analysis9_randMoney);
        tv_randMoney[9] = findViewById(R.id.tv_analysis10_randMoney);
        tv_randMoney[10] = findViewById(R.id.tv_analysis11_randMoney);
        tv_randMoney[11] = findViewById(R.id.tv_analysis12_randMoney);
        tv_randMoney[12] = findViewById(R.id.tv_analysis13_randMoney);
        tv_randMoney[13] = findViewById(R.id.tv_analysis14_randMoney);
        tv_randMoney[14] = findViewById(R.id.tv_analysis15_randMoney);


        for(int i=0; i<15; i++)
        {
            checked[i] = false;
            final int finalI = i;
            ll_analysis[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!checked[finalI])
                    {
                        if(chekcedCount < 4) {
                            tv_analysis[finalI].setVisibility(View.GONE);
                            iv_analysis[finalI].setVisibility(View.VISIBLE);
                            checked[finalI] = true;
                            chekcedCount++;
                        }else{
                            Toast.makeText(mContext, "복수예가는 최대 4개까지 선택가능합니다.", Toast.LENGTH_SHORT).show();
                        }
                    }else
                    {
                        tv_analysis[finalI].setVisibility(View.VISIBLE);
                        iv_analysis[finalI].setVisibility(View.GONE);
                        checked[finalI] = false;
                        chekcedCount--;
                    }

                }
            });
        }

        lv_analysis = findViewById(R.id.lv_ratio_analysis);
        analysis_arraylist = new ArrayList<>();
        analysis_arraylist.add(new Bid_Analysis_Listitem("99.2 ~ 99.4","99.54897%","4"));
        analysis_arraylist.add(new Bid_Analysis_Listitem("99.4 ~ 99.6","100.230009%","15"));
        analysis_arraylist.add(new Bid_Analysis_Listitem("99.6 ~ 99.8","98.51297%","4"));
        analysis_arraylist.add(new Bid_Analysis_Listitem("99.8 ~ 100","101.54897%","32"));
        analysis_arraylist.add(new Bid_Analysis_Listitem("100 ~ 100.2","98.12697%","7"));
        analysis_arraylist.add(new Bid_Analysis_Listitem("100.2 ~ 100.4","100.1227%","2"));
        analysis_arraylist.add(new Bid_Analysis_Listitem("100.4 ~ 100.6","100.14897%","1"));
        analysis_arraylist.add(new Bid_Analysis_Listitem("100.6 ~ 100.8","101.00897%","45"));
        analysis_arraylist.add(new Bid_Analysis_Listitem("100.8 ~ 101","101.6230%","8"));
        analysis_arraylist.add(new Bid_Analysis_Listitem("101 ~ 101.2","102.00121%","16"));

        listview_footer= getLayoutInflater().inflate(R.layout.bid_analysis_result_listfooter,null,false);
        listview_header= getLayoutInflater().inflate(R.layout.bid_analysis_result_listheader,null,false);

        analysis_adapter = new Bid_Analysis_LVAdapter(mContext,analysis_arraylist);
        lv_analysis.addFooterView(listview_footer);
        lv_analysis.addHeaderView(listview_header);
        lv_analysis.setAdapter(analysis_adapter);


        btn_ratio = findViewById(R.id.btn_ratio_analysis);
        btn_multiple = findViewById(R.id.btn_multiple_analysis);
        clickedBtnBgr(btn_multiple,btn_ratio);






        btn_multiple.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedBtnBgr(btn_multiple,btn_ratio);
                ((LinearLayout)findViewById(R.id.ll_ratio_analysis)).setVisibility(View.GONE);
                ((LinearLayout)findViewById(R.id.ll_multiple_analysis)).setVisibility(View.VISIBLE);
            }
        });


        btn_ratio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedBtnBgr(btn_ratio,btn_multiple);
                ((LinearLayout)findViewById(R.id.ll_ratio_analysis)).setVisibility(View.VISIBLE);
                ((LinearLayout)findViewById(R.id.ll_multiple_analysis)).setVisibility(View.GONE);

            }
        });



        btn_calculate = findViewById(R.id.btn_calculate_analysis);
        btn_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Popup_AnalysisResult.class);
                startActivity(intent);
            }
        });
        ll_choiceNo = findViewById(R.id.ll_choiceNo_analysis);

        btn_randomNo = findViewById(R.id.btn_randomNo_analysis);
        btn_randomNo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                long basicMoney = 0;
                try {
                    basicMoney = Long.parseLong(((EditText) findViewById(R.id.et_analysis_basicMoney)).getText().toString().replace(",", ""));
                }catch (Exception e){
                    Toast.makeText(mContext, "기초금액을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(basicMoney <= 0){
                    Toast.makeText(mContext, "기초금액을 확인해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                SaveSharedPreference.hideKeyboard(v,mContext);

                clearAllChecked();


                EditText et_analysis_low_rate = findViewById(R.id.et_analysis_low_rate);
                EditText et_analysis_high_rate = findViewById(R.id.et_analysis_high_rate);
                EditText et_analysis_rateLow = findViewById(R.id.et_analysis_rateLow);
                EditText et_analysis_rateHigh = findViewById(R.id.et_analysis_rateHigh);


                int index = 0;
                Random random = new Random();
                double rateLow = Double.parseDouble(et_analysis_rateLow.getText().toString().replace(" ", ""));
                double rateHigh = Double.parseDouble(et_analysis_rateHigh.getText().toString().replace(" ", ""));
                for(int i = 0; i < Math.abs(Integer.parseInt(et_analysis_low_rate.getText().toString().replace(" ", ""))); i++){
                    arrRate[index] = Math.round(random.nextDouble() * rateLow * 10000.0)/10000.0;
                    arrMoney[index] = (long)((100.0 + arrRate[index])/100.0 * basicMoney);
                    tv_randRate[index].setText(String.format("%.4f", arrRate[index]) + "%");
                    tv_randMoney[index].setText(toNumFormat(String.valueOf(arrMoney[index])));
                    index++;
                }

                for(int i = 0; i < Integer.parseInt(et_analysis_high_rate.getText().toString().replace(" ", "")); i++){
                    arrRate[index] = Math.round(random.nextDouble() * rateHigh * 10000.0)/10000.0;
                    arrMoney[index] = (long)((100.0 + arrRate[index])/100.0 * basicMoney);
                    tv_randRate[index].setText(String.format("%.4f", arrRate[index]) + "%");
                    tv_randMoney[index].setText(toNumFormat(String.valueOf(arrMoney[index])));
                    index++;
                }

                ll_choiceNo.setVisibility(View.VISIBLE);
            }
        });


        et_analysis_percent.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_analysis_basicMoney.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_analysis_rateLow.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_analysis_rateHigh.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_analysis_low_rate.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_analysis_high_rate.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {SaveSharedPreference.hideKeyboard(v,mContext);}}});

    }



    private String toNumFormat(String data){
        DecimalFormat df = new DecimalFormat("#,###");
        BigDecimal bd = new BigDecimal(data);
        return df.format(bd);
    }

    private void clearAllChecked(){
        for(int i = 0; i < 15; i++){
            tv_analysis[i].setVisibility(View.VISIBLE);
            iv_analysis[i].setVisibility(View.GONE);
            checked[i] = false;
        }

        chekcedCount = 0;
    }

    private void clickedBtnBgr(Button btn1, Button btn2)
    {
        btn1.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn1.setTypeface(null, Typeface.BOLD);
        btn1.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn2.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn2.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn2.setTypeface(null, Typeface.NORMAL);
        btn2.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

    }





}
