package com.mobile.a21line.Bid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

/**
 * Created by Accepted on 2018-05-25.
 */

public class Bid_Analysis_Activity extends AppCompatActivity {

    final int ORDER_CODE = 1;
    final int KIND_CODE = 2;
    final int UPCODE_CODE = 3;

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
    ArrayList<Bid_Analysis_Listitem> analysis_arraylist_order = new ArrayList<>();
    ArrayList<Bid_Analysis_Listitem> analysis_arraylist_kind = new ArrayList<>();
    ArrayList<Bid_Analysis_Listitem> analysis_arraylist_upcode = new ArrayList<>();

    TextView[] arrOrderTV;
    TextView[] arrKindTV;
    TextView[] arrUpcodeTV;

    int[] arrOrderIDs = {R.id.tv_bidAnalysis_orderCount, R.id.tv_bidAnalysis_orderMaxRatio, R.id.tv_bidAnalysis_orderMinRatio, R.id.tv_bidAnalysis_orderMidRatio, R.id.tv_bidAnalysis_orderAvgRatio
                        , R.id.tv_bidAnalysis_orderSS, R.id.tv_bidAnalysis_orderMaxPrice, R.id.tv_bidAnalysis_orderMinPrice, R.id.tv_bidAnalysis_orderAvgPrice};
    int[] arrKindIDs = {R.id.tv_bidAnalysis_kindCount, R.id.tv_bidAnalysis_kindMaxRatio, R.id.tv_bidAnalysis_kindMinRatio, R.id.tv_bidAnalysis_kindMidRatio, R.id.tv_bidAnalysis_kindAvgRatio
            , R.id.tv_bidAnalysis_kindSS, R.id.tv_bidAnalysis_kindMaxPrice, R.id.tv_bidAnalysis_kindMinPrice, R.id.tv_bidAnalysis_kindAvgPrice};
    int[] arrUpcodeIDs = {R.id.tv_bidAnalysis_upcodeCount, R.id.tv_bidAnalysis_upcodeMaxRatio, R.id.tv_bidAnalysis_upcodeMinRatio, R.id.tv_bidAnalysis_upcodeMidRatio, R.id.tv_bidAnalysis_upcodeAvgRatio
            , R.id.tv_bidAnalysis_upcodeSS, R.id.tv_bidAnalysis_upcodeMaxPrice, R.id.tv_bidAnalysis_upcodeMinPrice, R.id.tv_bidAnalysis_upcodeAvgPrice};

    TextView[] LLButtons;
    int[] llButtonIDs = {R.id.tv_bidAnalysis_btnLLOrder, R.id.tv_bidAnalysis_btnLLKind, R.id.tv_bidAnalysis_btnLLUpcode};

    private String iBidCode;

    private int sortType = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bid_analysis_activity);
        mContext = getApplicationContext();

        iBidCode = getIntent().getStringExtra("iBidCode");

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

        arrOrderTV = new TextView[arrOrderIDs.length];
        arrKindTV = new TextView[arrKindIDs.length];
        arrUpcodeTV = new TextView[arrUpcodeIDs.length];
        for(int i = 0; i < arrOrderIDs.length; i++){
            arrOrderTV[i] = findViewById(arrOrderIDs[i]);
            arrKindTV[i] = findViewById(arrKindIDs[i]);
            arrUpcodeTV[i] = findViewById(arrUpcodeIDs[i]);
        }

        LLButtons = new TextView[llButtonIDs.length];
        for(int i = 0; i < llButtonIDs.length; i++){
            final int index = i + 1;
            LLButtons[i] = findViewById(llButtonIDs[i]);
            LLButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(index == ORDER_CODE){
                        analysis_adapter = new Bid_Analysis_LVAdapter(mContext, analysis_arraylist_order);
                    }else if(index == KIND_CODE){
                        analysis_adapter = new Bid_Analysis_LVAdapter(mContext, analysis_arraylist_kind);
                    }else if(index == UPCODE_CODE){
                        analysis_adapter = new Bid_Analysis_LVAdapter(mContext, analysis_arraylist_upcode);
                    }

                    for(int j = 0; j < LLButtons.length; j++){
                        if(j == index - 1)
                            LLButtons[j].setTextColor(Color.parseColor("#000000"));
                        else
                            LLButtons[j].setTextColor(getResources().getColor(R.color.textColor_addition));
                    }
                    analysis_adapter.chgSort(true, index);
                    lv_analysis.setAdapter(analysis_adapter);
                }
            });
        }

        RelativeLayout[] rl_sortButtons = new RelativeLayout[3];
        rl_sortButtons[0] = findViewById(R.id.rl_bidAnalysis_sortRange);
        rl_sortButtons[1] = findViewById(R.id.rl_bidAnalysis_sortAvg);
        rl_sortButtons[2] = findViewById(R.id.rl_bidAnalysis_sortCount);

        for(int i = 0; i < rl_sortButtons.length; i++){
            final int index = i + 1;
            rl_sortButtons[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(sortType == index){
                        analysis_adapter.chgSort(false, 0);
                    }else{
                        analysis_adapter.chgSort(true, index);
                        sortType = index;
                    }
                }
            });
        }

        getBidData();

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

    public void parseXml(String xml) throws Exception{
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();

        Document doc = dBuilder.parse(new InputSource(new StringReader(xml)));
    }

    public void getBidData(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, "http://new2.21line.co.kr/flex/anal/ratio/getBidInfo.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {

                    XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                    XmlPullParser parser = factory.newPullParser();

                    parser.setInput(new StringReader(response));

                    boolean isOrderManager = false;
                    boolean isUpCodeList = false;
                    boolean isOrderCodes = false;
                    boolean isCutPercent = false;
                    boolean isOrderCode = false;
                    boolean isBidSection = false;
                    boolean isOrderBidHNum = false;
                    boolean isBidName = false;
                    boolean isYegaLow = false;
                    boolean isYegaHigh = false;

                    int eventType = parser.getEventType();
                    String OrderManager = "", UpCodeList = "", OrderCodes = "", CutPercent = "", OrderCode = "", BidSection = "", OrderBidHNum = "", BidName = "";
                    int YegaLow = 0, YegaHigh = 0;
                    String sTag = "";
                    while(eventType != XmlPullParser.END_DOCUMENT){
                        switch(eventType){
                            case XmlPullParser.START_DOCUMENT:
                                break;
                            case XmlPullParser.END_DOCUMENT:
                                break;
                            case XmlPullParser.START_TAG:
                                sTag = parser.getName();
                                if(sTag.equals("OrderManager")){
                                    isOrderManager = true;
                                }

                                if(sTag.equals("UpCodeList")){
                                    isUpCodeList = true;
                                }

                                if(sTag.equals("OrderCodes")){
                                    isOrderCodes = true;
                                }

                                if(sTag.equals("CutPercent")){
                                    isCutPercent = true;
                                }

                                if(sTag.equals("OrderCode")){
                                    isOrderCode = true;
                                }

                                if(sTag.equals("BidSection")){
                                    isBidSection = true;
                                }

                                if(sTag.equals("OrderBidHNum")){
                                    isOrderBidHNum = true;
                                }

                                if(sTag.equals("BidName")){
                                    isBidName = true;
                                }

                                if(sTag.equals("YegaLow")){
                                    isYegaLow = true;
                                }

                                if(sTag.equals("YegaHigh")){
                                    isYegaHigh = true;
                                }
                                break;
                            case XmlPullParser.TEXT:
                                if(isOrderManager){
                                    OrderManager = parser.getText();
                                    isOrderManager = false;
                                }

                                if(isUpCodeList){
                                    UpCodeList = parser.getText();
                                    isUpCodeList = false;
                                }

                                if(isOrderCodes){
                                    OrderCodes = parser.getText();
                                    isOrderCodes = false;
                                }

                                if(isCutPercent){
                                    CutPercent = parser.getText();
                                    isCutPercent = false;
                                }

                                if(isOrderCode){
                                    OrderCode = parser.getText();
                                    isOrderCode = false;
                                }

                                if(isBidSection){
                                    BidSection = parser.getText();
                                    isBidSection = false;
                                }

                                if(isOrderBidHNum){
                                    OrderBidHNum = parser.getText();
                                    isOrderBidHNum = false;
                                }

                                if(isBidName){
                                    BidName = parser.getText();
                                    isBidName = false;
                                }

                                if(isYegaLow){
                                    YegaLow = Integer.parseInt(parser.getText());
                                    isYegaLow = false;
                                }

                                if(isYegaHigh){
                                    YegaHigh = Integer.parseInt(parser.getText());
                                    isYegaHigh = false;
                                }
                                break;
                        }
                        eventType = parser.next();
                    }

                    getBidAnalData(OrderManager, UpCodeList, OrderCodes, CutPercent, OrderCode, BidSection);

                    ((TextView)findViewById(R.id.tv_bidAnalysis_title)).setText(BidName);
                    ((TextView)findViewById(R.id.tv_bidAnalysis_bidHNum)).setText(OrderBidHNum);
                    ((TextView)findViewById(R.id.tv_bidAnalysis_yegaRange)).setText((100 + YegaLow) + "% ~ " + (100 + YegaHigh) + "%");
                }catch(XmlPullParserException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }


            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("BidCode", iBidCode);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void getBidAnalData(final String OrderManager, final String UpCodeList, final String OrderCodes, final String CutPercnet, final String OrderCode, final String BidSection){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, "http://new2.21line.co.kr/flex/anal/ratio/getBidList.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    DocumentBuilderFactory f = DocumentBuilderFactory.newInstance();
                    DocumentBuilder parser = f.newDocumentBuilder();
                    double orderMaxR = -9999;
                    double orderMinR = 9999;
                    double orderTotalR = 0;
                    double orderAvgR = 0;

                    Document xmlDoc = null;
                    xmlDoc = parser.parse(new InputSource(new StringReader(response)));

                    Element root = xmlDoc.getDocumentElement();

                    NodeList orderNode = root.getElementsByTagName("orderList");
                    Element orderElement = (Element)orderNode.item(0);

                    NodeList kindNode = root.getElementsByTagName("kindList");
                    Element kindElement = (Element)kindNode.item(0);

                    NodeList upcodeNode = root.getElementsByTagName("upcodeList");
                    Element upcodeElement = (Element)upcodeNode.item(0);

                    makeDatas(orderElement, ORDER_CODE);
                    makeDatas(kindElement, KIND_CODE);
                    makeDatas(upcodeElement, UPCODE_CODE);

                    analysis_adapter = new Bid_Analysis_LVAdapter(mContext, analysis_arraylist_order);
                    lv_analysis.setAdapter(analysis_adapter);
                }catch(ParserConfigurationException e){
                    e.printStackTrace();
                }catch (IOException e){
                    e.printStackTrace();
                }catch (SAXException e){
                    e.printStackTrace();
                }

            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("BidCode", iBidCode);
                params.put("chkManager", "false");
                params.put("chgKind", "0");
                params.put("OrderManager", OrderManager);
                params.put("UpCodeList", UpCodeList);
                params.put("OrderCodes", OrderCodes);
                params.put("CutPercnet", CutPercnet);
                params.put("OrderCode", OrderCode);
                params.put("BidSection", BidSection);
                params.put("chkCutPercent", "false");

                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void makeDatas(Element element, int type){

        TextView[] arrTV;
        ArrayList<Bid_Analysis_Listitem> arrayList;
        if(type == ORDER_CODE){
            arrTV = arrOrderTV;
            arrayList = analysis_arraylist_order;
        }else if(type == KIND_CODE){
            arrTV = arrKindTV;
            arrayList = analysis_arraylist_kind;
        }else{
            arrTV = arrUpcodeTV;
            arrayList = analysis_arraylist_upcode;
        }

        double maxR = 0;
        double minR = 9999;
        double avgR = 0;
        double sumR = 0;
        NodeList ratioList = element.getElementsByTagName("R");
        for(int i = 0; i < ratioList.getLength(); i++){
            Node nodes = ratioList.item(i);
            if(nodes.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element)nodes;
                double temp = Double.parseDouble(eElement.getFirstChild().getNodeValue());
                if(temp > maxR){
                    maxR = temp;
                }
                if(temp < minR){
                    minR = temp;
                }
                sumR += temp;
            }
        }
        avgR = sumR / ratioList.getLength();

        if(sumR != 0) {

            double blockName = 0;
            int blockCount = 0;
            double blockSum = 0;

            NodeList blockCountList = element.getElementsByTagName("blockCount");
            for (int i = 0; i < blockCountList.getLength(); i++) {
                Node nodes = blockCountList.item(i);
                if (nodes.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nodes;
                    blockName = Double.parseDouble(getChildren(eElement, "name"));
                    blockCount = Integer.parseInt(getChildren(eElement, "count"));
                    blockSum = Double.parseDouble(getChildren(eElement, "sum"));
                    arrayList.add(new Bid_Analysis_Listitem(blockName + " - " + String.format("%.1f",(blockName + 0.1d)), String.format("%.5f", (blockSum / blockCount)) + "%", blockCount + ""));

                }
            }

            arrTV[0].setText(getChildren(element, "TotalNum") + "건");
            arrTV[1].setText(String.format("%.5f", Double.parseDouble(getChildren(element, "MaxRatio"))) + "%");
            arrTV[2].setText(String.format("%.5f", Double.parseDouble(getChildren(element, "MinRatio"))) + "%");
            arrTV[3].setText(String.format("%.5f", Double.parseDouble(getChildren(element, "MidRatio"))) + "%");
            arrTV[4].setText(String.format("%.5f", Double.parseDouble(getChildren(element, "AvgRatio"))) + "%");
            arrTV[5].setText(String.format("%.5f", Double.parseDouble(getChildren(element, "ss"))) + "%");
            arrTV[6].setText(String.format("%.5f", maxR) + "%");
            arrTV[7].setText(String.format("%.5f", minR) + "%");
            arrTV[8].setText(String.format("%.5f", avgR) + "%");
        }else{
            arrTV[0].setText("0건");
            arrTV[1].setText("-");
            arrTV[2].setText("-");
            arrTV[3].setText("-");
            arrTV[4].setText("-");
            arrTV[5].setText("-");
            arrTV[6].setText("-");
            arrTV[7].setText("-");
            arrTV[8].setText("-");
        }
    }

    public static String getChildren(Element element, String tagName) {
        NodeList list = element.getElementsByTagName(tagName);
        Element cElement = (Element) list.item(0);

        if(cElement.getFirstChild()!=null) {
            return cElement.getFirstChild().getNodeValue();
        } else {
            return "";
        }
    }




}
