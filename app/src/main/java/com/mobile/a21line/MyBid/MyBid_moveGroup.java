package com.mobile.a21line.MyBid;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;


public class MyBid_moveGroup extends AppCompatActivity {

    private TextView tv_title_dialog;
    private ImageView iv_dialog;
    private Button btn_dialog;
    private ListView lv_dialog;

    private ArrayList<MyBid_moveGroup_ListItem> arrayList_moveGroup;

    Context mContext;
    private MyBid_moveGroupLVAdapter adapter;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mybid_movegroup_dialog);

        tv_title_dialog = findViewById(R.id.tv_dialog_movegroup);
        btn_dialog = findViewById(R.id.btn_dialog_movegroup);
        lv_dialog = findViewById(R.id.lv_dialog_movegroup);
        iv_dialog = findViewById(R.id.iv_dialog_movegroup);
        tv_title_dialog.setText("내 서류함 이동");



        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        iv_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        arrayList_moveGroup = new ArrayList<>();
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 1.",false));
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 2.",false));
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 2.",false));
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 2.",false));
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 2.",false));
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 2.",false));
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 2.",false));
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 2.",false));
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 2.",false));
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 2.",false));
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 2.",false));
        arrayList_moveGroup.add(new MyBid_moveGroup_ListItem("그룹명 2.",false));

        adapter = new MyBid_moveGroupLVAdapter(MyBid_moveGroup.this,arrayList_moveGroup);
        lv_dialog.setAdapter(adapter);

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

