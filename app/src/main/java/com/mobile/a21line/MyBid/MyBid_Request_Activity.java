package com.mobile.a21line.MyBid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2018-05-14.
 */

public class MyBid_Request_Activity extends AppCompatActivity {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;

    MyBid_Request_LVAdapter adapter;
    ArrayList<MyBid_Request_Listitem> arrayList;
    ListView lv_request;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mybid_request_activity);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("분석의뢰함");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setText("분석취소");

        ((ImageView)findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);


        drawerLayout = findViewById(R.id.dl_mybid);
        frameLayout = findViewById(R.id.fl_drawerView_mybid);

        final View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, MyBid_Request_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(MyBid_Request_Activity.this, mClicklistener);

        lv_request = findViewById(R.id.lv_requset);
        arrayList = new ArrayList<>();
        adapter = new MyBid_Request_LVAdapter(mContext,arrayList);

        arrayList.add(new MyBid_Request_Listitem("조달청 용역 20180714525-00 호","영주고등학교 인조잔디 분리회수 및 재활용 처리용역","제주특별자치도교육청 영주고등학교","요청일 : 18/07/12","84,651,000",false));
        arrayList.add(new MyBid_Request_Listitem("부산광역시 공고 제2018-1662호","내성초등학교 외 1교 냉난방시설개선 전기공사 소액수의 견적제출 안내공고","경상북도교육청 경상북도봉화교육지원청","요청일 : 18/07/12","84,651,000",true));
        arrayList.add(new MyBid_Request_Listitem("(주)경남무역 입찰공고 GNTI 2018-188","소방시설점검(작동기능점검/종합정밀점검)업체선정입찰공고 ","경기도교육청 경기도구리남양주교육지원청","요청일 : 18/07/09","56,610,000",false));
        arrayList.add(new MyBid_Request_Listitem("조달청 시설 20180714568-00 호","구로3빗물펌프장 유수지 내 경로당 신축공사(건축, 기계) ","봉화산 푸르지오","요청일 : 18/07/07","104,411,000",true));

        lv_request.setAdapter(adapter);

    }

}
