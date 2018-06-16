package com.mobile.a21line.MyBid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.Bid.Bid_Activity;
import com.mobile.a21line.Bid.Bid_LVAdapter;
import com.mobile.a21line.Bid.Bid_Listitem;
import com.mobile.a21line.Bid.Bid_Popup_Sorting;
import com.mobile.a21line.R;
import com.mobile.a21line.Result.Result_LVAdapter;
import com.mobile.a21line.Result.Result_Listitem;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-14.
 */

public class MyBid_List_Activity extends AppCompatActivity {

    Context mContext;

    View footer;

    Button btn_total;
    Button btn_bid;
    Button btn_result;

    ListView lv_total;
    ListView lv_bidable;
    ListView lv_result;

    Bid_LVAdapter total_adapter;
    Bid_LVAdapter bid_adapter;
    Result_LVAdapter result_adapter;

    ArrayList<Bid_Listitem> total_arraylist;
    ArrayList<Bid_Listitem> bid_arraylist;
    ArrayList<Result_Listitem> result_arraylist;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mybid_list_activity);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("그룹명");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);

        footer= getLayoutInflater().inflate(R.layout.listview_footer,null,false);

        btn_total = findViewById(R.id.btn_total_list_mybid);
        btn_bid = findViewById(R.id.btn_bid_list_mybid);
        btn_result = findViewById(R.id.btn_result_list_mybid);

        lv_total = findViewById(R.id.lv_total_bidlist_mybid);
        lv_bidable = findViewById(R.id.lv_bidable_bidlist_mybid);
        lv_result = findViewById(R.id.lv_result_bidlist_mybid);

        total_arraylist = new ArrayList<>();
        bid_arraylist = new ArrayList<>();
        result_arraylist = new ArrayList<>();

        total_adapter = new Bid_LVAdapter(mContext,total_arraylist);
        bid_adapter = new Bid_LVAdapter(mContext,bid_arraylist);
        result_adapter = new Result_LVAdapter(mContext,result_arraylist);

        lv_total.setAdapter(total_adapter);
        lv_bidable.setAdapter(total_adapter);
        lv_result.setAdapter(total_adapter);


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
        lv_total.setVisibility(View.VISIBLE);
        lv_bidable.setVisibility(View.GONE);
        lv_result.setVisibility(View.GONE);

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
        lv_bidable.setVisibility(View.VISIBLE);
        lv_total.setVisibility(View.GONE);
        lv_result.setVisibility(View.GONE);


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

        lv_result.setVisibility(View.VISIBLE);
        lv_bidable.setVisibility(View.GONE);
        lv_total.setVisibility(View.GONE);

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
