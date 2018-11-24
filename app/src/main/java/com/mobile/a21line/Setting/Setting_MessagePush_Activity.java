package com.mobile.a21line.Setting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Home.Home_Activity;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Setting_MessagePush_Activity extends AppCompatActivity {

    Context mContext;
    Switch aSwitch;
    Switch vSwitch;
    int cycle = 0;

    LinearLayout ll_cycle[];
    ImageView iv_cycle[];
    BroadcastReceiver mReceiver;

    boolean isPushChg = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting_pushmessage);
        mContext = getApplicationContext();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((LinearLayout)findViewById(R.id.ll_setting_alramType)).setVisibility(View.GONE);
        }


        cycle = SaveSharedPreference.getNotiTerm(mContext);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("푸시 설정");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Home_Activity.class);
                startActivity(intent);
                finish();
            }
        });

        aSwitch = findViewById(R.id.swt_push_setting);
        aSwitch.setChecked(SaveSharedPreference.getNotiFlag(mContext));
        if(!SaveSharedPreference.getNotiFlag(mContext)){
            ((LinearLayout)findViewById(R.id.ll_pushon_setting)).setVisibility(View.GONE);
        }
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                isPushChg = !isPushChg;
                if (aSwitch.isChecked())
                {
                    aSwitch.setChecked(true);
                    SaveSharedPreference.setPrefNotiFlag(mContext,true);
                    ((LinearLayout)findViewById(R.id.ll_pushon_setting)).setVisibility(View.VISIBLE);

                }
                else
                {
                    SaveSharedPreference.setPrefNotiFlag(mContext,false);
                    aSwitch.setChecked(false);
                    ((LinearLayout)findViewById(R.id.ll_pushon_setting)).setVisibility(View.GONE);
                }
            }
        });


        ll_cycle = new LinearLayout[4];
        iv_cycle = new ImageView[4];

        ll_cycle[0] = findViewById(R.id.ll_onecycle_setting);
        ll_cycle[1] = findViewById(R.id.ll_twocycle_setting);
        ll_cycle[2] = findViewById(R.id.ll_threecycle_setting);
        ll_cycle[3] = findViewById(R.id.ll_fourcycle_setting);

        iv_cycle[0] = findViewById(R.id.iv_onecycle_setting);
        iv_cycle[1] = findViewById(R.id.iv_twocycle_setting);
        iv_cycle[2] = findViewById(R.id.iv_threecycle_setting);
        iv_cycle[3] = findViewById(R.id.iv_fourcycle_setting);

        setCheckBox(cycle - 1, iv_cycle);

        for(int i = 0; i < 4; i++){
            final int index = i;
            ll_cycle[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCheckBox(index, iv_cycle);
                }
            });

            iv_cycle[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    setCheckBox(index, iv_cycle);
                }
            });
        }
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
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

    private void setCheckBox(int index, ImageView[] ivs){
        isPushChg = true;
        SaveSharedPreference.setPrefNotiTerm(mContext, index + 1);
        cycle = index + 1;
        for(int i = 0; i < ivs.length; i++){
            if(index == i){
                ivs[i].setImageResource(R.drawable.icon_chechbox_checked);
            }else{
                ivs[i].setImageResource(R.drawable.icon_chechbox_unchecked);
            }
        }
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
        if(isPushChg){
            // 여기에 FCM Token 보내는 로직
            Log.d("Token", SaveSharedPreference.getFcmToken(mContext));
            RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
            StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/updateFCMToken.do", new Response.Listener<String>(){
                @Override
                public void onResponse(String response){
                    try {
                        JSONObject obj = new JSONObject(response);
                        if(obj.getString("result").equals("success")){
                            Log.d("saveToken", "토큰 저장 성공");
                        }else{
                            Log.d("saveToken", "토큰 저장 실패");
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
                    params.put("MemID", SaveSharedPreference.getUserID(mContext));
                    params.put("Token", SaveSharedPreference.getFcmToken(mContext));
                    params.put("isUse", aSwitch.isChecked() ? "Y" : "N");
                    params.put("AlarmTerm", String.valueOf(SaveSharedPreference.getNotiTerm(mContext)));
                    return params;
                }
            };

            postRequestQueue.add(postJsonRequest);
        }
    }


}
