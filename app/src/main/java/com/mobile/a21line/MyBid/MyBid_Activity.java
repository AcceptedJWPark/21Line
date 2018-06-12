package com.mobile.a21line.MyBid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
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

public class MyBid_Activity extends AppCompatActivity {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;


    ListView lv_bidgroup;
    MyBid_LVAdapter adapter;
    ArrayList<MyBid_Listitem> arrayList;

    TextView tv_edit;
    ImageView iv_edit;
    ImageView iv_delete;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mybid_activity);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("내 서류함");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setText("편집");
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyBid_Edit_Popup.class);
                startActivity(intent);
            }
        });
        ((ImageView)findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

        final View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, MyBid_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(MyBid_Activity.this, mClicklistener);


        arrayList = new ArrayList<MyBid_Listitem>();
        adapter = new MyBid_LVAdapter(mContext, arrayList);
        lv_bidgroup = findViewById(R.id.lv_bidgroup_mybid);

        arrayList.add(new MyBid_Listitem("그룹명 1.","23건"));
        arrayList.add(new MyBid_Listitem("그룹명 2.","132건"));
        arrayList.add(new MyBid_Listitem("그룹명 3.","12건"));
        arrayList.add(new MyBid_Listitem("그룹명 4.","2건"));

        lv_bidgroup.setAdapter(adapter);


    }


}