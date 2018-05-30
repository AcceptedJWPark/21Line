package com.mobile.a21line.Setbid;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
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
    static public ArrayList<BidAreaCode.BidAreaItem> arrayLocationList;

    Setbid_LVAdapter_Location adapter;
    TextView tv_count;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setbid_locationselect);

        mContext = getApplicationContext();

        copyLocationList();
        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("지역분류표");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setText("초기화");
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayLocationList.clear();
                adapter = new Setbid_LVAdapter_Location(mContext,arrayList1, lv_location2, lv_location3, tv_count);
                lv_location1.setAdapter(adapter);
                tv_count.setText("선택 (" + arrayLocationList.size() + ")");
            }
        });

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
        tv_count = findViewById(R.id.tv_selectedCount_locationPopup);
        adapter = new Setbid_LVAdapter_Location(mContext,arrayList1, lv_location2, lv_location3, tv_count);
        lv_location1.setAdapter(adapter);

        tv_count.setText("선택 (" + arrayLocationList.size() + ")");

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
                Setbid_Activity.arrayList_location = new ArrayList<>();
                for(BidAreaCode.BidAreaItem item : arrayLocationList)
                    Setbid_Activity.arrayList_location.add(item);
                finish();
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



    public void setArray_Location1(){
        arrayList1 = BidAreaCode.getArrayMainAreaName();
    }

    private void copyLocationList(){

        arrayLocationList = new ArrayList<>();
        for(BidAreaCode.BidAreaItem item : Setbid_Activity.arrayList_location){
            arrayLocationList.add(item);
        }

    }

}


