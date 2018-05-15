package com.mobile.a21line.Setbid;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobile.a21line.Bid.Bid_Period_Dialog;
import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-10.
 */

public class Setbid_Dialog extends Dialog {

    private TextView tv_title_dialog;
    private ImageView iv_dialog;
    private Button btn_dialog;
    private ListView lv_dialog;
    private String mTitle;
    private ArrayList mArrayList;

    Spinner spn_year1;
    Spinner spn_month1;
    Spinner spn_day1;

    private String[] yearType = {"2007","2008","2009","2010","2011","2012","2013","2014","2015","2016","2017","2018"};
    private String[] monthType = {"1","2","3","4","5","6","7","8","9","10","11","12" };
    private String[] dayType = {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31" };

    Bid_Period_Dialog dialog;

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
        iv_dialog = findViewById(R.id.iv_dialog_setbid);
        tv_title_dialog.setText(mTitle);

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

        spn_year1 = findViewById(R.id.spn_year1_bid);
        spn_month1 = findViewById(R.id.spn_month1_bid);
        spn_day1 = findViewById(R.id.spn_day1_bid);

//        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(this,R.layout.setbid_pricespinner,yearType);
//        adapter1.setDropDownViewResource(R.layout.setbid_pricespinner);
//        spn_year1.setAdapter(adapter1);



    }

    public Setbid_Dialog(Context context, String title, ArrayList<String> arrayList)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContext = context;
        this.mTitle = title;
        this.mArrayList = arrayList;
    }

}
