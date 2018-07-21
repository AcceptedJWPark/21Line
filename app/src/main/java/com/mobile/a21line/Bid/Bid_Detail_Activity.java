package com.mobile.a21line.Bid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.TooltipCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.BidResultCommon.Popup_MemoAdd;
import com.mobile.a21line.MyBid.MyBid_Request_Result_Popup;
import com.mobile.a21line.MyBid.MyBid_moveGroup;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Accepted on 2018-05-25.
 */

public class Bid_Detail_Activity extends AppCompatActivity {

    Context mContext;
    Button btn_info;
    Button btn_orderinfo;
    Button btn_originalinfo;
    WebView wv_originalinfo;
    WebView wv_ordertype;
    String iBidCode;
    LinearLayout lv_info;

    TextView tv_bidTitle;
    TextView tv_bidNo;
    TextView tv_bidOrder;
    TextView tv_bidCharger;
    TextView tv_bidPhone;
    TextView tv_bidDemand;


    TextView tv_bidPrice1;
    TextView tv_bidPrice2;
    TextView tv_bidLimitPrice;
    TextView tv_bidPercent;
    TextView tv_bidLocation;
    TextView tv_bidBusiness;

    TextView tv_bidPeriod1;
    TextView tv_bidPeriod2;
    TextView tv_bidPeriod3;
    TextView tv_bidPeriod4;
    TextView tv_bidPeriod5;

    String orderTypeData;


    LinearLayout ll_relativeBid_Detail;
    LinearLayout ll_relativeBid;

    boolean isMybid;
    boolean isAnal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bid_detail_activity);
        iBidCode = getIntent().getStringExtra("iBidCode");

        mContext = getApplicationContext();

        isMybid=false;
        isAnal = getIntent().hasExtra("isAnal");

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("입찰공고 상세");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Popup_MemoAdd.class);
                intent.putExtra("iBidCode", iBidCode);

                if(isAnal){
                    intent.putExtra("isAnal", isAnal);
                }

                startActivity(intent);
            }
        });
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setImageResource(R.drawable.icon_mybid_white);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMybid)
                {
                    Toast.makeText(mContext,"이미 저장된 공고입니다.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(mContext, MyBid_moveGroup.class);
                    intent.putExtra("iBidCode", iBidCode);
                    startActivity(intent);
                }
            }
        });
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ll_relativeBid_Detail = findViewById(R.id.ll_relativeBid_Detail);


        ((LinearLayout)findViewById(R.id.ll_multianalysis_bid_detail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Bid_Analysis_Activity.class);
                intent.putExtra("BidName", tv_bidTitle.getText().toString());
                intent.putExtra("BasicMoney", tv_bidPrice1.getText().toString());
                intent.putExtra("EstimatedPrice", tv_bidPrice2.getText().toString());
                intent.putExtra("CutPercent", tv_bidLimitPrice.getText().toString());
                intent.putExtra("YegaRate", tv_bidPercent.getText().toString());
                intent.putExtra("iBidCode", iBidCode);
                startActivity(intent);
            }
        });

        ((LinearLayout)findViewById(R.id.ll_request_bid_detail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyBid_Request_Result_Popup.class);
                startActivity(intent);
            }
        });



        btn_info = findViewById(R.id.btn_info_Detail);
        btn_orderinfo = findViewById(R.id.btn_orderinfo_Detail);
        btn_originalinfo = findViewById(R.id.btn_originalinfo_Detail);

        wv_originalinfo = findViewById(R.id.wv_originalinfo_Detail);
        wv_originalinfo.getSettings().setDefaultFontSize(12);
        //wv_originalinfo.getSettings().setTextZoom(50);
        wv_ordertype = findViewById(R.id.wv_ordertype_Detail);
        wv_ordertype.getSettings().setDefaultFontSize(12);
        //wv_originalinfo.getSettings().setTextZoom(50);
        lv_info = findViewById(R.id.ll_multiple_analysis);


        clickedInfo();

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedInfo();
            }
        });

        btn_orderinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedOrderInfo();
            }
        });

        btn_originalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedOriginalInfo();
            }
        });

        tv_bidTitle = findViewById(R.id.tv_bidTitle_Detail);
        tv_bidNo=findViewById(R.id.tv_bidNo_Detail);
        tv_bidOrder=findViewById(R.id.tv_bidOrder_Detail);
        tv_bidCharger=findViewById(R.id.tv_bidCharger_Detail);
        tv_bidPhone=findViewById(R.id.tv_bidPhone_Detail);
        tv_bidDemand=findViewById(R.id.tv_bidDemand_Detail);

        tv_bidPrice1=findViewById(R.id.tv_bidPrice1_Detail);
        tv_bidPrice2=findViewById(R.id.tv_bidPrice2_Detail);
        tv_bidLimitPrice=findViewById(R.id.tv_bidLimitPrice_Detail);
        tv_bidPercent=findViewById(R.id.tv_bidPercent_Detail);
        tv_bidLocation=findViewById(R.id.tv_bidLocation_Detail);
        tv_bidBusiness=findViewById(R.id.tv_bidBusiness_Detail);

        tv_bidPeriod1=findViewById(R.id.tv_bidPeriod1_Detail);
        tv_bidPeriod2=findViewById(R.id.tv_bidPeriod2_Detail);
        tv_bidPeriod3=findViewById(R.id.tv_bidPeriod3_Detail);
        tv_bidPeriod4=findViewById(R.id.tv_bidPeriod4_Detail);
        tv_bidPeriod5=findViewById(R.id.tv_bidPeriod5_Detail);

        getBidData();
        getOrderTypeData();
    }

    private void getBidData(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getBidDataUri() + "getBidData.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONObject regHistory = obj.getJSONObject("RegHistory");
                    if(regHistory.getInt("totalNum") > 0){
                        ll_relativeBid_Detail.setVisibility(View.VISIBLE);
                        ll_relativeBid = findViewById(R.id.ll_relativeBid);

                        JSONArray historyDatas = regHistory.getJSONArray("datas");
                        for(int i = 0; i < historyDatas.length(); i++){
                            JSONObject regData = historyDatas.getJSONObject(i);
                            final String bidCode = regData.getString("BidNo") + "-" + regData.getString("BidNoSeq");

                            TextView tv_relative = new TextView(mContext);
                            int state = regData.getInt("BidState_Code");
                            int reBid = state & 0x1;
                            int cancel = state & 0x20;

                            tv_relative.setTextColor(Color.parseColor("#ffffff"));
                            tv_relative.setGravity(View.TEXT_ALIGNMENT_CENTER);
                            tv_relative.setPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),
                                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()),
                                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),
                                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                            tv_relative.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.txt_sub));

                            if(regData.getInt("BidNoSeq") == 0) {
                                tv_relative.setText("원문");
                                tv_relative.setBackground(getResources().getDrawable(R.drawable.bgr_btn_original));
                            }else if(reBid > 0){
                                tv_relative.setText(regData.getInt("BidNoSeq") + "차 정정");
                                tv_relative.setBackground(getResources().getDrawable(R.drawable.bgr_btn_edit));
                            }else if(cancel > 0){
                                tv_relative.setText("취소");
                                tv_relative.setBackgroundColor(Color.parseColor("#dd3343"));
                            }

                            tv_relative.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(mContext, Bid_Detail_Activity.class);
                                    intent.putExtra("iBidCode", bidCode);
                                    startActivity(intent);
                                }
                            });

                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            if(i == 0){
                                layoutParams.setMargins((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()), 0, 0, 0);
                            }else {
                                layoutParams.setMargins((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()), 0, 0, 0);
                            }
                            layoutParams.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE);
                            tv_relative.setLayoutParams(layoutParams);

                            ll_relativeBid.addView(tv_relative);
                        }
                    }

                    final String phone = obj.getString("OrderPhone");

                    tv_bidTitle.setText(obj.getString("BidName"));
                    tv_bidNo.setText(obj.getString("OrderBidHNum"));
                    tv_bidOrder.setText(obj.getString("OrderName"));
                    tv_bidCharger.setText(obj.getString("OrderManager"));
                    tv_bidPhone.setText(phone);
                    tv_bidPhone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                            startActivity(i);
                        }
                    });
                    tv_bidDemand.setText((obj.getString("DemandName").equals("null")) ? "-" : obj.getString("DemandName"));

                    tv_bidPrice1.setText(toNumFormat(obj.getString("BasicPrice")) + "원");
                    tv_bidPrice2.setText(toNumFormat(obj.getString("EstimatedPrice")) + "원");
                    tv_bidLimitPrice.setText(obj.getString("CutPercent"));
                    tv_bidPercent.setText(obj.getString("YegaLow") + "%" + " ~ " + obj.getString("YegaHigh")+ "%");
                    tv_bidLocation.setText(obj.getString("AreaName"));
                    tv_bidBusiness.setText(obj.getString("UpcodeName"));


                    SpannableStringBuilder RegDTime = convert24hhToAMPM(obj.getString("RegDTime"));
                    SpannableStringBuilder StartDTime = convert24hhToAMPM(obj.getString("StartDTime"));
                    SpannableStringBuilder ERDDTime = convert24hhToAMPM(obj.getString("ERDDTime"));
                    SpannableStringBuilder FinishDTime = convert24hhToAMPM(obj.getString("FinishDTime"));
                    SpannableStringBuilder OpenDTime = convert24hhToAMPM(obj.getString("OpenDTime"));

                    tv_bidPeriod1.setText(RegDTime);
                    tv_bidPeriod2.setText(StartDTime);
                    tv_bidPeriod3.setText(ERDDTime);
                    tv_bidPeriod4.setText(FinishDTime);
                    tv_bidPeriod5.setText(OpenDTime);

//                    if(!RegDTime.substring(RegDTime.length()-1).equals(")") && !RegDTime.substring(RegDTime.length()-1).equals("-")){
//                        tv_bidPeriod1.setTextColor(R.color.textColor_highlight_ngt);
//                    }
//                    if(!StartDTime.substring(StartDTime.length()-1).equals(")") && !StartDTime.substring(StartDTime.length()-1).equals("-")){
//                        tv_bidPeriod2.setTextColor(R.color.textColor_highlight_ngt);
//                    }
//                    if(!ERDDTime.substring(ERDDTime.length()-1).equals(")") && !ERDDTime.substring(ERDDTime.length()-1).equals("-")){
//                        tv_bidPeriod3.setTextColor(R.color.textColor_highlight_ngt);
//                    }
//                    if(!FinishDTime.substring(FinishDTime.length()-1).equals(")") && !FinishDTime.substring(FinishDTime.length()-1).equals("-")){
//                        tv_bidPeriod4.setTextColor(R.color.textColor_highlight_ngt);
//                    }
//                    if(!OpenDTime.substring(OpenDTime.length()-1).equals(")") && !OpenDTime.substring(OpenDTime.length()-1).equals("-")){
//                        tv_bidPeriod5.setTextColor(R.color.textColor_highlight_ngt);
//                    }

                    bidState(obj.getInt("BidState_Code"));
                    if(obj.getString("GonggoMun").equals("false")) {
                        btn_originalinfo.setVisibility(View.GONE);
                    }else {
                        wv_originalinfo.loadData(obj.getString("GonggoMun"), "text/html; charset=UTF-8", null);
                    }
                    orderTypeData = obj.getString("DetailPageCont").replace("\\", "");
                    if(orderTypeData != null && !orderTypeData.isEmpty()) {
                        orderTypeData = orderTypeData.replace("width=15%", "width=24%").replace("width=35%", "width=26%");
                        StringBuilder sb = new StringBuilder();
                        sb.append("<HTML><HEAD><LINK href=\"reset.css\" type=\"text/css\" rel=\"stylesheet\"/></HEAD><body>");
                        sb.append(orderTypeData);
                        sb.append("</body></HTML>");

                        wv_ordertype.loadDataWithBaseURL("file:///android_asset/css/", sb.toString(), "text/html; charset=UTF-8", null,null);
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("iBidCode", iBidCode);
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    private String toNumFormat(String data){
        DecimalFormat df = new DecimalFormat("#,###");
        BigDecimal bd = new BigDecimal(data);
        return df.format(bd);
    }

    private void clickedInfo()
    {
        btn_info.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_info.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_info.setTypeface(null, Typeface.BOLD);
        btn_info.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_orderinfo.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_orderinfo.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_orderinfo.setTypeface(null, Typeface.NORMAL);
        btn_orderinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_originalinfo.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_originalinfo.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_originalinfo.setTypeface(null, Typeface.NORMAL);
        btn_originalinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        wv_originalinfo.setVisibility(View.GONE);
        wv_ordertype.setVisibility(View.GONE);
        lv_info.setVisibility(View.VISIBLE);

    }

    private void clickedOrderInfo()
    {
        btn_orderinfo.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_orderinfo.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_orderinfo.setTypeface(null, Typeface.BOLD);
        btn_orderinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_info.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_info.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_info.setTypeface(null, Typeface.NORMAL);
        btn_info.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_originalinfo.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_originalinfo.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_originalinfo.setTypeface(null, Typeface.NORMAL);
        btn_originalinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        wv_originalinfo.setVisibility(View.GONE);
        wv_ordertype.setVisibility(View.VISIBLE);
        lv_info.setVisibility(View.GONE);
    }

    private void clickedOriginalInfo()
    {
        btn_originalinfo.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_originalinfo.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_originalinfo.setTypeface(null, Typeface.BOLD);
        btn_originalinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_orderinfo.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_orderinfo.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_orderinfo.setTypeface(null, Typeface.NORMAL);
        btn_orderinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_info.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_info.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_info.setTypeface(null, Typeface.NORMAL);
        btn_info.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        wv_originalinfo.setVisibility(View.VISIBLE);
        wv_ordertype.setVisibility(View.GONE);
        lv_info.setVisibility(View.GONE);
    }

    private void getOrderTypeData(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getBidDataUri() + "getBidViewData.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                if(!response.isEmpty()){
                    orderTypeData = response;
                }

                if(orderTypeData != null && !orderTypeData.isEmpty()){
                    orderTypeData = orderTypeData.replace("width=15%", "width=24%").replace("width=35%", "width=26%");
                    StringBuilder sb = new StringBuilder();
                    sb.append("<HTML><HEAD><LINK href=\"reset.css\" type=\"text/css\" rel=\"stylesheet\"/></HEAD><body>");
                    sb.append(orderTypeData);
                    sb.append("</body></HTML>");

                    wv_ordertype.loadDataWithBaseURL("file:///android_asset/css/", sb.toString(), "text/html; charset=UTF-8", null,null);
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("iBidCode", iBidCode);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void bidState(int bidState)
    {
        LinearLayout ll_bidstateContainer = findViewById(R.id.tv_bidCategory_Detail);

        ImageView[] iv_bidstate = new ImageView[9];
        iv_bidstate[0] = findViewById(R.id.iv_bidstate1_Detail);
        iv_bidstate[1] = findViewById(R.id.iv_bidstate2_Detail);
        iv_bidstate[2] = findViewById(R.id.iv_bidstate3_Detail);
        iv_bidstate[3] = findViewById(R.id.iv_bidstate4_Detail);
        iv_bidstate[4] = findViewById(R.id.iv_bidstate5_Detail);
        iv_bidstate[5] = findViewById(R.id.iv_bidstate6_Detail);
        iv_bidstate[6] = findViewById(R.id.iv_bidstate7_Detail);
        iv_bidstate[7] = findViewById(R.id.iv_bidstate8_Detail);
        iv_bidstate[8] = findViewById(R.id.iv_bidstate9_Detail);

        int[] states = {0x4, 0x1, 0x20, 0x10, 0x80, 0x100, 0x40, 0x2, 0x8, 0x400, 0x800, 0x1000, 0x4000, 0x8000, 0x40000, 0x20000, 0x80000};
        int[] rescources = { R.drawable.bidstate_kinds9, R.drawable.bidstate_kinds2, R.drawable.bidstate_kinds4, R.drawable.bidstate_kinds5, R.drawable.bidstate_kinds6
                , R.drawable.bidstate_kinds11, R.drawable.bidstate_kinds3, R.drawable.bidstate_kinds7, R.drawable.bidstate_kinds8
                , R.drawable.bidstate_kinds10, R.drawable.bidstate_kinds1, R.drawable.bidstate_kinds12, R.drawable.bidstate_kinds3
                , R.drawable.bidstate_kinds16, R.drawable.bidstate_kinds17, R.drawable.bidstate_kinds15, R.drawable.bidstate_kinds18};
        String[] tooltipValue = { "결과발표", "정정공고",  "취소공고", "전자입찰", "견적입찰", "수의계약", "재공고", "긴급공고", "계약공고", "공동도급", "현장설명참조", "역경매", "재입찰", "지명입찰", "여성", "시담", "유찰공고"};

        int index = 0;
        for(int i = 0; i < states.length; i++){
            int temp = bidState & states[i];
            if(temp > 0){
                iv_bidstate[index].setVisibility(View.VISIBLE);
                iv_bidstate[index].setImageResource(rescources[i]);
                TooltipCompat.setTooltipText(iv_bidstate[index], tooltipValue[i]);
                index++;
            }
        }


    }

    private SpannableStringBuilder convert24hhToAMPM(String date){

        if(date.equals("0000-00-00 00:00:00"))
            return new SpannableStringBuilder("-");

        SimpleDateFormat date12Format = new SimpleDateFormat("a yy-MM-dd hh:mm:ss");
        SimpleDateFormat date24Format = new SimpleDateFormat("yy-MM-dd HH:mm:ss");
        SimpleDateFormat dateYYMMDDFormat = new SimpleDateFormat("yy-MM-dd");
        try {
            Date inputDate = date24Format.parse(date);
            Date nowDate = dateYYMMDDFormat.parse(dateYYMMDDFormat.format(new Date()));

            TimeZone time = TimeZone.getTimeZone("Asia/Seoul");

            Long timeDiff = inputDate.getTime() - time.getOffset(nowDate.getTime()) - nowDate.getTime();
            int differ = (int) Math.floor(timeDiff / (24 * 60 * 60 * 1000));
            if(timeDiff < 0) {
                SpannableStringBuilder ssb = new SpannableStringBuilder(date12Format.format(inputDate));
                ssb.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColor_addition)), 0, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                return ssb;
            }
            else if(differ == 0){
                Date now = new Date();
                Long timeDiffer = inputDate.getTime() - time.getOffset(now.getTime()) - now.getTime();
                if(timeDiffer > 0) {
                    SpannableStringBuilder ssb = new SpannableStringBuilder(date12Format.format(inputDate) + " (D-Day)");
                    ssb.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColor_highlight_ngt)), 20, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                    return ssb;
                }
                else {
                    SpannableStringBuilder ssb = new SpannableStringBuilder(date12Format.format(inputDate));
                    ssb.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColor_addition)), 0, ssb.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
                    return ssb;
                }
            }else{
                SpannableStringBuilder ssb= new SpannableStringBuilder(date12Format.format(inputDate) + " (D-" + differ + ")");
                ssb.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.textColor_highlight_ngt)), 20, ssb.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
                return ssb;
            }
        }catch (ParseException e){
            e.printStackTrace();
            return new SpannableStringBuilder(date);
        }
    }
}
