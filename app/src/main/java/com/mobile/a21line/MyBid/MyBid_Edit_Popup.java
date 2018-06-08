package com.mobile.a21line.MyBid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2018-05-14.
 */

public class MyBid_Edit_Popup extends AppCompatActivity {

    Context mContext;


    ListView lv_bidgroup;
    MyBid_Edit_LVAdapter adapter;
    ArrayList<MyBid_Edit_Listitem> arrayList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);

        setContentView(R.layout.mybid_edit_popup);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("내 서류함 관리");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView)findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);


        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mContext = getApplicationContext();
        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;


        arrayList = new ArrayList<MyBid_Edit_Listitem>();
        adapter = new MyBid_Edit_LVAdapter(mContext, arrayList);
        lv_bidgroup = findViewById(R.id.lv_bidgroup_mybid);

        arrayList.add(new MyBid_Edit_Listitem("그룹명 1.","23","2018-06-12"));
        arrayList.add(new MyBid_Edit_Listitem("그룹명 2.","132","2018-06-10"));
        arrayList.add(new MyBid_Edit_Listitem("그룹명 3.","12","2018-06-09"));
        arrayList.add(new MyBid_Edit_Listitem("그룹명 4.","2","2018-05-02"));
        arrayList.add(new MyBid_Edit_Listitem("그룹명 4.","2","2018-05-02"));
        arrayList.add(new MyBid_Edit_Listitem("그룹명 4.","2","2018-05-02"));
        arrayList.add(new MyBid_Edit_Listitem("그룹명 4.","2","2018-05-02"));
        arrayList.add(new MyBid_Edit_Listitem("그룹명 4.","2","2018-05-02"));
        arrayList.add(new MyBid_Edit_Listitem("그룹명 4.","2","2018-05-02"));
        arrayList.add(new MyBid_Edit_Listitem("그룹명 4.","2","2018-05-02"));

        lv_bidgroup.setAdapter(adapter);


    }





}
