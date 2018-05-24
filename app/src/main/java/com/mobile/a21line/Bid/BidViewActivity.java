package com.mobile.a21line.Bid;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
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

public class BidViewActivity extends AppCompatActivity {

    WebView mWebView;
    Context mContext;
    String iBidCode;
    TextView bidName;
    TextView bidNo;
    TextView orderName;
    TextView manName;
    TextView demandName;
    TextView basicMoney;
    TextView estimationMoney;
    TextView cutPercent;
    TextView yegaPercent;
    TextView areaName;
    TextView upcodeName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.bid_view_activity);

        mContext = getApplicationContext();

        iBidCode = getIntent().getStringExtra("iBidCode");

        mWebView = (WebView)findViewById(R.id.bid_view_webview);

        bidName = (TextView)findViewById(R.id.tv_bid_name_bidview);
        bidNo = (TextView)findViewById(R.id.tv_bid_no_bidview);
        orderName = (TextView)findViewById(R.id.tv_order_name_bidview);
        manName = (TextView)findViewById(R.id.tv_man_name_bidview);
        demandName = (TextView)findViewById(R.id.tv_demand_name_bidview);
        basicMoney = (TextView)findViewById(R.id.tv_basic_money_bidview);
        estimationMoney = (TextView)findViewById(R.id.tv_estimation_money_bidview);
        cutPercent = (TextView)findViewById(R.id.tv_cut_percent_bidview);
        yegaPercent = (TextView)findViewById(R.id.tv_yega_percent_bidview);
        areaName = (TextView)findViewById(R.id.tv_area_name_bidview);
        upcodeName = (TextView)findViewById(R.id.tv_upcode_name_bidview);

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
                    bidName.setText(obj.getString("BidName"));
                    bidNo.setText(obj.getString("OrderBidHNum"));
                    orderName.setText(obj.getString("OrderName"));
                    manName.setText(obj.getString("OrderManager") + " // " + obj.getString("OrderPhone"));
                    demandName.setText(obj.getString("DemandName"));
                    basicMoney.setText(toNumFormat(obj.getString("BasicPrice")) + "원");
                    estimationMoney.setText(toNumFormat(obj.getString("EstimatedPrice")) + "원");
                    cutPercent.setText(obj.getString("CutPercent"));
                    yegaPercent.setText(obj.getString("YegaLow") + " ~ " + obj.getString("YegaHigh"));
                    areaName.setText(obj.getString("AreaName"));
                    upcodeName.setText(obj.getString("UpcodeName"));
                    mWebView.loadData(obj.getString("GonggoMun"), "text/html; charset=UTF-8", null);
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
}
