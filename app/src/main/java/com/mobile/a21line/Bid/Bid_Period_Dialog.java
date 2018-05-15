package com.mobile.a21line.Bid;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;
import com.mobile.a21line.Setbid.Setbid_LVAdapter_Dialog;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-10.
 */

public class Bid_Period_Dialog extends Dialog {

    private ImageView iv_dialog;
    private Button btn_dialog;
    private ListView lv_dialog;
    private ArrayList mArrayList;

    Context mContext;

    private Setbid_LVAdapter_Dialog adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.bid_date_dialog);

        btn_dialog = findViewById(R.id.btn_dialog_bid);
        lv_dialog = findViewById(R.id.lv_dialog_bid);
        iv_dialog = findViewById(R.id.iv_dialog_bid);

        adapter = new Setbid_LVAdapter_Dialog(mContext,mArrayList);
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

    public Bid_Period_Dialog(Context context , ArrayList<String> arrayList)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContext = context;
        this.mArrayList = arrayList;
    }

}
