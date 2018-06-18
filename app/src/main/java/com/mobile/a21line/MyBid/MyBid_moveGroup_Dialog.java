package com.mobile.a21line.MyBid;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.BinaryCode;
import com.mobile.a21line.R;
import com.mobile.a21line.Setbid.Setbid_LVAdapter_Dialog;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-10.
 */

public class MyBid_moveGroup_Dialog extends Dialog {

    private TextView tv_title_dialog;
    private ImageView iv_dialog;
    private Button btn_dialog;
    private ListView lv_dialog;
    private String mTitle;
    private ArrayList mArrayList;

    Context mContext;

    private MyBid_moveGroupLVAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.6f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.mybid_movegroup_dialog);


        tv_title_dialog = findViewById(R.id.tv_dialog_movegroup);
        btn_dialog = findViewById(R.id.btn_dialog_movegroup);
        lv_dialog = findViewById(R.id.lv_dialog_movegroup);
        iv_dialog = findViewById(R.id.iv_dialog_movegroup);
        tv_title_dialog.setText(mTitle);

        adapter = new MyBid_moveGroupLVAdapter(mContext,mArrayList);
        lv_dialog.setAdapter(adapter);

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        iv_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });


    }

    public MyBid_moveGroup_Dialog(Context context, String title, ArrayList<MyBid_moveGroup_ListItem> arrayList)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContext = context;
        this.mTitle = title;
        this.mArrayList = arrayList;
    }



}
