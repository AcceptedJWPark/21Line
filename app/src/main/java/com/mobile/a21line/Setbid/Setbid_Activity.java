package com.mobile.a21line.Setbid;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;

import java.util.ArrayList;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;


public class Setbid_Activity extends AppCompatActivity {


    private Context mContext;

    TextView btn_businessselect;
    TextView btn_locationselect;
    private ArrayList<String> arrayList_business = new ArrayList<>();
    private ArrayList<String> arrayList_location = new ArrayList<>();
    private Setbid_LVAdapter adapter;

    EditText et_price1;
    EditText et_price2;

    View frameLayout;
    DrawerLayout drawerLayout;

    ListView lv_business;
    ListView lv_location;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setbid_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("맞춤설정 1");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Setbid_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Setbid_Activity.this,mClicklistener);


        btn_locationselect = findViewById(R.id.tv_locationselect_Setbid);
        btn_locationselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, Setbid_Popup_LocationSelect.class);
                startActivity(i);
            }
        });

        btn_businessselect = findViewById(R.id.tv_businessselect_Setbid);
        btn_businessselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, Setbid_Popup_BusinessSelect.class);
                startActivity(i);
            }
        });

        et_price1 = findViewById(R.id.et_price1_setbid);
        et_price2 = findViewById(R.id.et_price2_setbid);
        et_price1.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_price2.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {SaveSharedPreference.hideKeyboard(v,mContext);}}});

        arrayList_business.add("정비사업(재건축,재개발,재정비등)");
        arrayList_business.add("전기공사");
        arrayList_business.add("실내건축");
        arrayList_business.add("습식방수(미장방수조적)");

        adapter = new Setbid_LVAdapter(mContext, arrayList_business);
        lv_business = findViewById(R.id.lv_business_setbid);
        lv_business.setAdapter(adapter);

        arrayList_location.add("서울 전체");
        arrayList_location.add("서울 강남구");
        arrayList_location.add("서울 강동구");
        arrayList_location.add("서울 마포구");
        arrayList_location.add("서울 용산구");

        adapter = new Setbid_LVAdapter(mContext, arrayList_location);

        lv_location = findViewById(R.id.lv_location_setbid);

        lv_location.setAdapter(adapter);



    }



}

