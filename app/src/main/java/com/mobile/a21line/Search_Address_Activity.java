package com.mobile.a21line;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.widget.TextView;

public class Search_Address_Activity extends AppCompatActivity {

    WebView webView;
    Handler handler;
    Context mContext;
    Activity activity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search_address);
        activity = this;
        mContext = getApplicationContext();

        init_webView();

        handler = new Handler();
    }

    public void init_webView(){
        webView = (WebView) findViewById(R.id.webView);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setJavaScriptCanOpenWindowsAutomatically(true);

        webView.addJavascriptInterface(new AndroidBridge(), "twoonelineApp");

        webView.setWebChromeClient(new WebChromeClient());
        webView.loadUrl(SaveSharedPreference.getServerIp() + "Member/getAddress.do");
    }

    private class AndroidBridge{
        @JavascriptInterface
        public void setAddress(final String arg1, final String arg2, final String arg3){
            handler.post(new Runnable() {
                @Override
                public void run() {
                    init_webView();
                    Intent intent = new Intent();
                    intent.putExtra("address", String.format("(%s) %s %s", arg1, arg2, arg3));
                    activity.setResult(Activity.RESULT_OK, intent);
                    activity.finish();
                }
            });
        }
    }
}
