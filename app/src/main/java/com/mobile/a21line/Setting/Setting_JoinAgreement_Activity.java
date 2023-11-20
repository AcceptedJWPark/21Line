package com.mobile.a21line.Setting;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.a21line.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Setting_JoinAgreement_Activity extends AppCompatActivity {

    Context mContext;
    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting_joinagreement_activity);
        mContext = getApplicationContext();


        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("이용약관");
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

        ((WebView)findViewById(R.id.wv_articleData)).getSettings().setDefaultFontSize(12);
        String htmlContent = getHtmlFromAsset();

        ((WebView)findViewById(R.id.wv_articleData)).loadDataWithBaseURL("file:///android_asset/css/", htmlContent, "text/html; charset=UTF-8", null,null);

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

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    private String getHtmlFromAsset() {
        InputStream is; StringBuilder builder = new StringBuilder();
        String htmlString = null; try { is = getAssets().open("html/article.html");
            if (is != null) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(is));
                String line;
                while ((line = reader.readLine()) != null)
                {
                    builder.append(line);
                }
                htmlString = builder.toString();
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        return htmlString;
    }
}
