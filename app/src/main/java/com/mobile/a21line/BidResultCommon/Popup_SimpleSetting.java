package com.mobile.a21line.BidResultCommon;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-06-08.
 */

public class Popup_SimpleSetting extends AppCompatActivity {

    Context mContext;
    ListView lv_business;
    ListView lv_location;
    SimpleSetting_Adapter adapter_business;
    SimpleSetting_Adapter adapter_location;
    ArrayList<String> arrayList_business;
    ArrayList<String> arrayList_location;

    TextView tv_selectAll_business;
    TextView tv_selectAll_location;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.simplesetting);

        mContext = getApplicationContext();

        lv_business = findViewById(R.id.lv_business_simplesetting);
        lv_location = findViewById(R.id.lv_loc_simplesetting);

        arrayList_business = new ArrayList<>();
        arrayList_location = new ArrayList<>();

        adapter_business = new SimpleSetting_Adapter(Popup_SimpleSetting.this, arrayList_business);

        lv_business.setAdapter(adapter_business);

        adapter_location = new SimpleSetting_Adapter(Popup_SimpleSetting.this, arrayList_location);

        lv_location.setAdapter(adapter_location);

        tv_selectAll_business = findViewById(R.id.tv_selectAll_business_simplesetting);
        tv_selectAll_location = findViewById(R.id.tv_selectAll_location_simplesetting);

        tv_selectAll_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        tv_selectAll_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            // Tapped outside so we finish the activity
            finish();
        }
        return super.dispatchTouchEvent(ev);
    }




}
