package com.mobile.a21line.BidResultCommon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.BidAreaCode;
import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2018-06-08.
 */

public class Popup_SimpleSetting extends AppCompatActivity {

    Context mContext;
    ListView lv_business;
    ListView lv_location;
    SimpleSetting_Adapter adapter_business;
    SimpleSetting_Adapter adapter_location;
    static ArrayList<BidUpCode.BidUpCodeItem> arrayList_business;
    static ArrayList<BidAreaCode.BidAreaItem> arrayList_location;

    TextView tv_selectAll_business;
    TextView tv_selectAll_location;

    Button btn_searchbox_save_bid;

    Activity activity;

    String GCode;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.simplesetting);

        activity = this;
        GCode = getIntent().getStringExtra("GCode");

        mContext = getApplicationContext();

        lv_business = findViewById(R.id.lv_business_simplesetting);
        lv_location = findViewById(R.id.lv_loc_simplesetting);

        arrayList_business = new ArrayList<>();
        arrayList_location = new ArrayList<>();

        tv_selectAll_business = findViewById(R.id.tv_selectAll_business_simplesetting);
        tv_selectAll_location = findViewById(R.id.tv_selectAll_location_simplesetting);
        btn_searchbox_save_bid = findViewById(R.id.btn_searchbox_save_bid);

        tv_selectAll_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter_business.selectAll();
            }
        });

        tv_selectAll_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter_location.selectAll();
            }
        });

        btn_searchbox_save_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(mContext,"간편설정이 적용되었습니다.",Toast.LENGTH_SHORT).show();

                Intent i = new Intent();
                i.putExtra("LocationArray", arrayList_location);
                i.putExtra("BusinessArray", arrayList_business);
                adapter_location = new SimpleSetting_Adapter(mContext, arrayList_location);
                lv_location.setAdapter(adapter_location);
                adapter_business = new SimpleSetting_Adapter(mContext, arrayList_business);
                lv_business.setAdapter(adapter_business);
                activity.setResult(RESULT_OK, i);
                finish();
            }
        });
        if(getIntent().hasExtra("LocationArray")){
            arrayList_location = (ArrayList<BidAreaCode.BidAreaItem>)getIntent().getSerializableExtra("LocationArray");
            arrayList_business = (ArrayList<BidUpCode.BidUpCodeItem>)getIntent().getSerializableExtra("BusinessArray");

            adapter_location = new SimpleSetting_Adapter(mContext, arrayList_location);
            lv_location.setAdapter(adapter_location);

            adapter_business = new SimpleSetting_Adapter(mContext, arrayList_business);
            lv_business.setAdapter(adapter_business);
        }else {
            getAreaCode();
            getUpcode();
        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            // Tapped outside so we finish the activity
            finish();
        }
        return super.dispatchTouchEvent(ev);
    }

    public void getAreaCode(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/getAreaCode.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        String name = o.getString("Name");
                        if(name.length() == 2){
                            name += " ";
                        }

                        arrayList_location.add(new BidAreaCode.BidAreaItem(name, o.getString("Code")));
                    }
                    adapter_location = new SimpleSetting_Adapter(mContext, arrayList_location);
                    lv_location.setAdapter(adapter_location);

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                try {
                    params.put("GCode", GCode);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void getUpcode(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/getUpcode.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        arrayList_business.add(new BidUpCode.BidUpCodeItem(o.getString("Name"), o.getString("Code")));
                    }
                    adapter_business = new SimpleSetting_Adapter(mContext, arrayList_business);
                    lv_business.setAdapter(adapter_business);

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                try {
                    params.put("GCode", GCode);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

}
