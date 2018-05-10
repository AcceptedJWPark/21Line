package com.mobile.a21line.Setbid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-10.
 */

public class Setbid_Dialog extends Dialog {

    private TextView tv_title_dialog;
    private Button btn_dialog;
    private ListView lv_dialog;
    private String mTitle;
    private ArrayList mArrayList;
    private View.OnClickListener mClicklistener;

    Context mContext;

    private Setbid_LVAdapter_Dialog adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.8f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.setbid_dialog);



        tv_title_dialog = findViewById(R.id.tv_dialog_setbid);
        btn_dialog = findViewById(R.id.btn_dialog_setbid);
        lv_dialog = findViewById(R.id.lv_dialog_setbid);
        tv_title_dialog.setText(mTitle);

        adapter = new Setbid_LVAdapter_Dialog(mContext,mArrayList);
        lv_dialog.setAdapter(adapter);



    }

    public Setbid_Dialog(Context context, String title, ArrayList<String> arrayList, View.OnClickListener singleListener)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContext = context;
        this.mTitle = title;
        this.mClicklistener = singleListener;
        this.mArrayList = arrayList;
    }

}
