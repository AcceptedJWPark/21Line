package com.mobile.a21line.Setting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.mobile.a21line.R;

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
    LinearLayout ll_cycle[];
    ImageView iv_cycle[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting_pushmessage);
        mContext = getApplicationContext();


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
        aSwitch.setChecked(false);
        aSwitch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (aSwitch.isChecked())
                {
                    aSwitch.setChecked(true);
                    ((LinearLayout)findViewById(R.id.ll_pushon_setting)).setVisibility(View.VISIBLE);
                }
                else
                {
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


    }

}
