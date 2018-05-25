package com.mobile.a21line.Bid;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2018-05-25.
 */

public class Bid_Detail_Activity extends AppCompatActivity {

    Context mContext;
    Button btn_info;
    Button btn_orderinfo;
    Button btn_originalinfo;
    WebView wv_originalinfo;
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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bid_detail_activity);
        iBidCode = getIntent().getStringExtra("iBidCode");

        mContext = getApplicationContext();

        btn_info = findViewById(R.id.btn_info_Detail);
        btn_orderinfo = findViewById(R.id.btn_orderinfo_Detail);
        btn_originalinfo = findViewById(R.id.btn_originalinfo_Detail);

        wv_originalinfo = findViewById(R.id.wv_originalinfo_Detail);
        lv_info = findViewById(R.id.lv_info_Detail);


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

    }

    private void getBidData(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getBidDataUri() + "getBidData.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("bidData = " , obj.toString());
                    tv_bidTitle.setText(obj.getString("BidName"));
                    tv_bidNo.setText(obj.getString("OrderBidHNum"));
                    tv_bidOrder.setText(obj.getString("OrderName"));
                    tv_bidCharger.setText(obj.getString("OrderManager"));
                    tv_bidPhone.setText(obj.getString("OrderPhone"));
                    tv_bidDemand.setText(obj.getString("DemandName"));

                    tv_bidPrice1.setText(toNumFormat(obj.getString("BasicPrice")) + "원");
                    tv_bidPrice2.setText(toNumFormat(obj.getString("EstimatedPrice")) + "원");
                    tv_bidLimitPrice.setText(obj.getString("CutPercent"));
                    tv_bidPercent.setText(obj.getString("YegaLow") + "%" + " ~ " + obj.getString("YegaHigh")+ "%");
                    tv_bidLocation.setText(obj.getString("AreaName"));
                    tv_bidBusiness.setText(obj.getString("UpcodeName"));

//                    tv_bidPeriod1.setText();
//                    tv_bidPeriod2.setText();
//                    tv_bidPeriod3.setText();
//                    tv_bidPeriod4.setText();
//                    tv_bidPeriod5.setText();

                    wv_originalinfo.loadData(obj.getString("GonggoMun"), "text/html; charset=UTF-8", null);
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
        lv_info.setVisibility(View.GONE);
    }
}
