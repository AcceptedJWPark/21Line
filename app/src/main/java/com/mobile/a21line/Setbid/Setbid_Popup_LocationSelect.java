package com.mobile.a21line.Setbid;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.BidAreaCode;
import com.mobile.a21line.R;

import java.util.ArrayList;
import java.util.HashMap;


public class Setbid_Popup_LocationSelect extends AppCompatActivity {

    Context mContext;
    ListView lv_location1;
    ListView lv_location2;
    ListView lv_location3;

    Button btn_save;

    ArrayList<BidAreaCode.BidAreaItem> arrayList1;

    Setbid_LVAdapter_Location adapter;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setbid_locationselect);

        mContext = getApplicationContext();
        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("지역분류표");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mContext = getApplicationContext();
        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        lv_location1 = findViewById(R.id.lv_location1_setbid);
        lv_location2 = findViewById(R.id.lv_location2_setbid);
        lv_location3 = findViewById(R.id.lv_location3_setbid);

        setArray_Location1();
        adapter = new Setbid_LVAdapter_Location(mContext,arrayList1, lv_location2, lv_location3);
        lv_location1.setAdapter(adapter);

        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn_save = findViewById(R.id.btn_save_setbid_LocationSelect);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    public void setArray_Location1(){
        arrayList1 = BidAreaCode.getArrayMainAreaName();
    }
}


