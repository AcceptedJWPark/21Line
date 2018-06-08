package com.mobile.a21line.MyBid;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.a21line.R;

/**
 * Created by Accepted on 2018-05-14.
 */

public class MyBid_List_Activity extends AppCompatActivity {

    Context mContext;

    View footer;

    Button btn_total;
    Button btn_bid;
    Button btn_result;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mybid_list_activity);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("그룹명 1.");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Sorting)).setImageResource(R.drawable.icon_delete2);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        footer= getLayoutInflater().inflate(R.layout.listview_footer,null,false);


        btn_total = findViewById(R.id.btn_total_list_mybid);
        btn_bid = findViewById(R.id.btn_bid_list_mybid);
        btn_result = findViewById(R.id.btn_result_list_mybid);

        btn_total.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedTotal();
            }
        });

        btn_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedBid();
            }
        });

        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedResult();
            }
        });


    }

    private void clickedTotal()
    {
        btn_total.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_total.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_total.setTypeface(null, Typeface.BOLD);
        btn_total.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_bid.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_bid.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_bid.setTypeface(null, Typeface.NORMAL);
        btn_bid.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_result.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_result.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_result.setTypeface(null, Typeface.NORMAL);
        btn_result.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

    }

    private void clickedBid()
    {
        btn_bid.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_bid.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_bid.setTypeface(null, Typeface.BOLD);
        btn_bid.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_total.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_total.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_total.setTypeface(null, Typeface.NORMAL);
        btn_total.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_result.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_result.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_result.setTypeface(null, Typeface.NORMAL);
        btn_result.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

    }

    private void clickedResult()
    {
        btn_result.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_result.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_result.setTypeface(null, Typeface.BOLD);
        btn_result.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_bid.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_bid.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_bid.setTypeface(null, Typeface.NORMAL);
        btn_bid.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_total.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_total.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_total.setTypeface(null, Typeface.NORMAL);
        btn_total.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

    }




}
