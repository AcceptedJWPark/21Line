package com.mobile.a21line.Bid;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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


public class Popup_AnalysisResult extends AppCompatActivity {

    Context mContext;

    double[] checkedMoney, checkedRate;
    TextView[] tv_money, tv_rate;
    int[] moneyIDs = {R.id.tv_analResult_money1, R.id.tv_analResult_money2, R.id.tv_analResult_money3, R.id.tv_analResult_money4};
    int[] rateIDs = {R.id.tv_analResult_rate1, R.id.tv_analResult_rate2, R.id.tv_analResult_rate3, R.id.tv_analResult_rate4};
    double cutPercent;
    String memo, iBidCode;
    double estMoney, tuchalMoney, avgRate;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bid_analysis_result);

        final Activity activity = this;
        mContext = getApplicationContext();

        checkedMoney = getIntent().getDoubleArrayExtra("arrCheckedMoney");
        checkedRate = getIntent().getDoubleArrayExtra("arrCheckedRate");
        cutPercent = Double.parseDouble(getIntent().getStringExtra("CutPercent"));
        iBidCode = getIntent().getStringExtra("iBidCode");

        tv_money = new TextView[moneyIDs.length];
        tv_rate = new TextView[rateIDs.length];

        for(int i = 0; i < moneyIDs.length; i++){
            tv_money[i] = findViewById(moneyIDs[i]);
            tv_rate[i] = findViewById(rateIDs[i]);

            tv_rate[i].setText(String.format("%.4f", checkedRate[i]) + "%");
            tv_money[i].setText(toNumFormat(String.valueOf(checkedMoney[i])) + "원");
        }

        estMoney = getAverage(checkedMoney);
        tuchalMoney  = estMoney * cutPercent / 100;
        avgRate = getAverage(checkedRate);

        ((TextView)findViewById(R.id.tv_analResult_estMoney)).setText(toNumFormat(String.valueOf(estMoney)) + "원");
        ((TextView)findViewById(R.id.tv_analResult_tuchalMoney)).setText(toNumFormat(String.valueOf(tuchalMoney)) + "원");

        ((ImageView)findViewById(R.id.iv_cancel_analysisresult)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        ((Button)findViewById(R.id.btn_analResult_saveMemo)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMemo();
            }
        });

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

    private String toNumFormat(String data){
        DecimalFormat df = new DecimalFormat("#,###");
        BigDecimal bd = new BigDecimal(data);
        return df.format(bd);
    }

    private double getAverage(double[] arr){
        double temp = 0;
        for(int i = 0; i < arr.length; i++){
            temp += arr[i];
        }

        return temp / arr.length;
    }

    public void getMemo(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/getMemo.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    if(response == null){
                        return;
                    }
                    JSONObject obj = new JSONObject(response);
                    memo = obj.optString("Memo", "");
                    StringBuilder sb = new StringBuilder(memo);
                    if(!memo.isEmpty()){
                        sb.append("\n");
                    }

                    sb.append("투찰금액 : ").append(toNumFormat(String.valueOf(tuchalMoney)) + "원").append("\n사정률 : ").append(String.format("%.4f", avgRate) + "%");
                    memo = sb.toString();
                    saveMemo();
                }
                catch(JSONException e){
                    e.printStackTrace();
                    memo = "";
                    StringBuilder sb = new StringBuilder(memo);

                    sb.append("투찰금액 : ").append(toNumFormat(String.valueOf(tuchalMoney)) + "원").append("\n사정률 : ").append(String.format("%.4f", avgRate) + "%");
                    memo = sb.toString();
                    saveMemo();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                String[] iBidCodes = iBidCode.split("-");
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("BidNo", iBidCodes[0]);
                params.put("BidNoSeq", iBidCodes[1]);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void saveMemo(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/insertMydoc.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    if(response == null){
                        return;
                    }
                    JSONObject obj = new JSONObject(response);

                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "저장 실패하였습니다.", Toast.LENGTH_SHORT).show();
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
                String[] iBidCodes = iBidCode.split("-");
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("BidNo", iBidCodes[0]);
                params.put("BidNoSeq", iBidCodes[1]);
                params.put("Memo", memo);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }
}

