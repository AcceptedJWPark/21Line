package com.mobile.a21line.Setting;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.a21line.Bid.Bid_Activity;
import com.mobile.a21line.Login.Join_PrivateInfoTxt;
import com.mobile.a21line.R;
import com.mobile.a21line.Result.Result_Activity;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.Search.Search_BidType_Popup;
import com.mobile.a21line.Search.Search_Period_Popup;
import com.mobile.a21line.Search.Search_Price_Popup;
import com.mobile.a21line.Setbid.Setbid_Activity;
import com.mobile.a21line.Setbid.Setbid_Popup_BusinessSelect;
import com.mobile.a21line.Setbid.Setbid_Popup_LocationSelect;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Setting_PrivateDataAgreement_Activity extends AppCompatActivity {

    Context mContext;

    BroadcastReceiver mReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setting_privatedataagreement_activity);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("개인정보의 수집 및 이용 약관");
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
        ((WebView)findViewById(R.id.wv_privateData)).getSettings().setDefaultFontSize(12);
        String htmlContent = getHtmlFromAsset();

        ((WebView)findViewById(R.id.wv_privateData)).loadDataWithBaseURL("file:///android_asset/css/", htmlContent, "text/html; charset=UTF-8", null,null);


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
        String htmlString = null; try { is = getAssets().open("html/privacy.html");
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
