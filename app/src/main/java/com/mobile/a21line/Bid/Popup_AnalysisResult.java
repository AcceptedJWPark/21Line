package com.mobile.a21line.Bid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
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
import com.mobile.a21line.AddMemoEvent;
import com.mobile.a21line.AddMemoFlag;
import com.mobile.a21line.MyBid.MyBid_moveGroup;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;
import com.squareup.otto.Subscribe;

import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;


public class Popup_AnalysisResult extends AppCompatActivity {

    Context mContext;

    double[] checkedMoney, checkedRate;
    String[] strSelectedNumbers = {"①", "②", "③", "④", "⑤", "⑥", "⑦", "⑧", "⑨", "⑩", "⑪", "⑫", "⑬", "⑭", "⑮"};
    int[] selectedNumbers;
    TextView[] tv_money, tv_rate, tv_numbers;
    int[] moneyIDs = {R.id.tv_analResult_money1, R.id.tv_analResult_money2, R.id.tv_analResult_money3, R.id.tv_analResult_money4};
    int[] rateIDs = {R.id.tv_analResult_rate1, R.id.tv_analResult_rate2, R.id.tv_analResult_rate3, R.id.tv_analResult_rate4};
    int[] numberIDs = {R.id.tv_analResult_num1, R.id.tv_analResult_num2, R.id.tv_analResult_num3, R.id.tv_analResult_num4};
    double cutPercent;
    String memo, iBidCode;
    double estMoney, tuchalMoney, avgRate, tuchalRate;
    int position;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bid_analysis_result);

        final Activity activity = this;
        mContext = getApplicationContext();

        checkedMoney = getIntent().getDoubleArrayExtra("arrCheckedMoney");
        checkedRate = getIntent().getDoubleArrayExtra("arrCheckedRate");
        selectedNumbers = getIntent().getIntArrayExtra("arrSelectedNumbers");

        position = getIntent().getIntExtra("position", -1);
        try {
            cutPercent = Double.parseDouble(getIntent().getStringExtra("CutPercent"));
        }catch (NumberFormatException e){
            cutPercent = 0;
        }

        iBidCode = getIntent().getStringExtra("iBidCode");

        tv_money = new TextView[moneyIDs.length];
        tv_rate = new TextView[rateIDs.length];
        tv_numbers = new TextView[numberIDs.length];

        for(int i = 0; i < moneyIDs.length; i++){
            tv_money[i] = findViewById(moneyIDs[i]);
            tv_rate[i] = findViewById(rateIDs[i]);
            tv_numbers[i] = findViewById(numberIDs[i]);

            tv_rate[i].setText(String.format("%.4f", checkedRate[i]) + "%");
            tv_money[i].setText(toNumFormat(String.valueOf(checkedMoney[i])) + "원");
            tv_numbers[i].setText(strSelectedNumbers[selectedNumbers[i]]);
        }

        estMoney = getAverage(checkedMoney);
        tuchalMoney  = estMoney * cutPercent / 100;
        avgRate = getAverage(checkedRate) + 100;
        tuchalRate = tuchalMoney / estMoney * 100;

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

        AddMemoEvent.getInstance().register(this);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        AddMemoEvent.getInstance().unregister(this);
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
                    if(obj.optString("SelectMoney", "0.0").equals("0.0") && obj.optString("RatioPercent", "0.0").equals("0.0") && obj.optString("ResultPercent", "0.0").equals("0.0")){
                        saveMemo();
                    }else{

                    }
//                    StringBuilder sb = new StringBuilder(memo);
//                    if(!memo.isEmpty()){
//                        sb.append("\n\n");
//                    }
//                    Date date = new Date();
//                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                    TimeZone tz = TimeZone.getTimeZone("Asia/Seoul");
//                    sdf.setTimeZone(tz);
//
//
//                    sb.append("투찰금액 : ").append(toNumFormat(String.valueOf(tuchalMoney)) + "원").append("\n사정률 : ").append(String.format("%.4f", avgRate) + "%").append("\n저장일 : ").append(sdf.format(date));
//                    memo = sb.toString();

                }
                catch(JSONException e){
                    e.printStackTrace();
                    memo = "";
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
        Intent intent = new Intent(mContext, MyBid_moveGroup.class);
        intent.putExtra("iBidCode", iBidCode);
        intent.putExtra("Position", position);
        intent.putExtra("Memo", memo);
        intent.putExtra("Price", String.valueOf((long)tuchalMoney));
        intent.putExtra("Percent1", String.format("%.4f", avgRate));
        intent.putExtra("Percent2", String.format("%.4f", tuchalRate));
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        mContext.startActivity(intent);
    }

    @Subscribe
    public void getPost(AddMemoFlag flag){
        finish();
    }
}

