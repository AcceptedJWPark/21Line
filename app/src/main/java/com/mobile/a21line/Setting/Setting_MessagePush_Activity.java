package com.mobile.a21line.Setting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Setting_MessagePush_Activity extends AppCompatActivity {

    Context mContext;
    Switch aSwitch;
    Switch vSwitch;
    int cycle = 0;
    EditText et_noti_stime;
    EditText et_noti_etime;
    LinearLayout ll_cycle[];
    ImageView iv_cycle[];
    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting_pushmessage);
        mContext = getApplicationContext();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            ((LinearLayout)findViewById(R.id.ll_setting_alramType)).setVisibility(View.GONE);
        }


        cycle = SaveSharedPreference.getNotiTerm(mContext);
        et_noti_stime = findViewById(R.id.et_noti_stime);
        et_noti_etime = findViewById(R.id.et_noti_etime);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("푸시 설정");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

        vSwitch = findViewById(R.id.swt_vibe_setting);
        vSwitch.setChecked(SaveSharedPreference.getVibeFlag(mContext));
        vSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (vSwitch.isChecked())
                {
                    vSwitch.setChecked(true);
                    SaveSharedPreference.setPrefVibeFlag(mContext,true);
                }
                else
                {
                    SaveSharedPreference.setPrefVibeFlag(mContext,false);
                    vSwitch.setChecked(false);
                }
            }
        });


        ll_cycle = new LinearLayout[4];
        iv_cycle = new ImageView[4];

        et_noti_stime.setText(SaveSharedPreference.getNotiStime(mContext));
        et_noti_etime.setText(SaveSharedPreference.getNotiEtime(mContext));

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
        SaveSharedPreference.setPrefNotiStime(mContext, et_noti_stime.getText().toString());
        SaveSharedPreference.setPrefNotiEtime(mContext, et_noti_etime.getText().toString());
        unregisterReceiver(mReceiver);
    }

}
