package com.mobile.a21line.CustomerService;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Notice_Activity extends AppCompatActivity {

    Context mContext;

    TextView[] tv_series;

    int clickNext = 0;
    int clickNumber = 1;

    ListView lv_notice;
    Notice_LVAdapter adapter;
    ArrayList<Notice_Listitem> arrayList;
    int startNum = 0;
    int totalNum = 0;

    String searchWord = "";

    BroadcastReceiver mReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cs_notice_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("공지사항");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        lv_notice = findViewById(R.id.lv_notice_cs);

        tv_series = new TextView[12];

        tv_series[0] = findViewById(R.id.tv_series_pre);
        tv_series[1] = findViewById(R.id.tv_series1);
        tv_series[2] = findViewById(R.id.tv_series2);
        tv_series[3] = findViewById(R.id.tv_series3);
        tv_series[4] = findViewById(R.id.tv_series4);
        tv_series[5] = findViewById(R.id.tv_series5);
        tv_series[6] = findViewById(R.id.tv_series6);
        tv_series[7] = findViewById(R.id.tv_series7);
        tv_series[8] = findViewById(R.id.tv_series8);
        tv_series[9] = findViewById(R.id.tv_series9);
        tv_series[10] = findViewById(R.id.tv_series10);
        tv_series[11] = findViewById(R.id.tv_series_next);


        arrayList = new ArrayList<>();
        adapter = new Notice_LVAdapter(Notice_Activity.this,arrayList);

        lv_notice.setAdapter(adapter);

        ((ImageView)findViewById(R.id.iv_notice_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                searchWord = ((EditText)findViewById(R.id.et_notice_search)).getText().toString();
                clickNext = 0;
                clickNumber = 1;
                getNoticeListCount();
            }
        });

        getNoticeListCount();

        tv_series[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPre();
            }
        });
        tv_series[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNext();
            }
        });


        for(int i=1; i<11; i++)
        {
            final int finalI = i;
            tv_series[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickNumber(finalI);
                }
            });
        }

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



    public void clickNumber(final int i) {

        for (int a = 1; a < 11; a++)
        {
            tv_series[a].setTextColor(getResources().getColor(R.color.textColor_soft));
            tv_series[a].setText(String.valueOf(a + clickNext * 10));
            if(i == 1) {
                if (((a - 1) * 10) + clickNext * 100 + 1 > totalNum) {
                    tv_series[a].setVisibility(View.INVISIBLE);
                }else{
                    tv_series[a].setVisibility(View.VISIBLE);
                }
            }
        }

        if(i == 1){
            if((clickNext + 1) * 100 + 1 > totalNum){
                tv_series[11].setTextColor(getResources().getColor(R.color.textColor_soft));
                tv_series[11].setOnClickListener(null);
            }else{
                tv_series[11].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                tv_series[11].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        clickNext();
                    }
                });
            }
        }
        tv_series[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        startNum = ((i-1)*10)+clickNext*100;
        Log.d("StartNum = ", String.valueOf(startNum));
        getNoticeList();
    }


    public void clickNext()
    {
        clickNext++;
        for(int i=1; i<11; i++)
        {
            if(((i-1)*10)+clickNext*100 + 1 > totalNum){
                tv_series[i].setVisibility(View.INVISIBLE);
                tv_series[11].setTextColor(getResources().getColor(R.color.textColor_soft));
                tv_series[11].setOnClickListener(null);
            }else {
                tv_series[i].setText(String.valueOf(i + clickNext * 10));
                tv_series[0].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                clickNumber = 1;
                clickNumber(clickNumber);
            }
        }
    }

    public void clickPre()
    {
        tv_series[11].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        tv_series[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNext();
            }
        });
        if(clickNext==0) {
            tv_series[0].setTextColor(getResources().getColor(R.color.textColor_soft));
            return;
        }
        else {
            clickNext--;
            for (int i = 1; i < 11; i++) {
                tv_series[i].setText(String.valueOf(i + clickNext * 10));
                tv_series[i].setVisibility(View.VISIBLE);
                clickNumber = 1;
                clickNumber(clickNumber);
            }
            if(clickNext == 0){
                tv_series[0].setTextColor(getResources().getColor(R.color.textColor_soft));
            }
        }
    }

    public void getNoticeList(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Board/getBoardList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    arrayList.clear();
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");

                        Date regDate = new Date(o.getLong("RegDate"));
                        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
                        sdf.setTimeZone(time);
                        String date = sdf.format(regDate);
                        arrayList.add(new Notice_Listitem(String.valueOf(startNum + 1 + i),o.getString("Title"),date, o.getString("Content"), o.getString("Code"), o.optString("File1", ""), o.optString("File2", ""), o.optString("File3", ""), o.optString("File4", "")));
                    }

                    adapter.notifyDataSetChanged();
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("BoardName", "NoticeBoard");
                params.put("PageRowLimit", String.valueOf(10));
                params.put("StartNum", String.valueOf(startNum));
                if(!searchWord.isEmpty()){
                    params.put("ListSearchWord", "Title");
                    params.put("TxtSearchWord", searchWord);
                }
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

    public void getNoticeListCount(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Board/getBoardListCount.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    totalNum = obj.getInt("TotalCnt");
                }
                catch(JSONException e){
                    e.printStackTrace();
                }

                clickNumber(clickNumber);
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("BoardName", "NoticeBoard");
                if(!searchWord.isEmpty()){
                    params.put("ListSearchWord", "Title");
                    params.put("TxtSearchWord", searchWord);
                }
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }
}
