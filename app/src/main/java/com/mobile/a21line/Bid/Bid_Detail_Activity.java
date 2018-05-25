package com.mobile.a21line.Bid;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2018-05-25.
 */

public class Bid_Detail_Activity extends AppCompatActivity {

    Context mContext;
    Button btn_info;
    Button btn_orderinfo;
    Button btn_originalinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bid_detail_activity);

        mContext = getApplicationContext();

        btn_info = findViewById(R.id.btn_info_Detail);
        btn_orderinfo = findViewById(R.id.btn_orderinfo_Detail);
        btn_originalinfo = findViewById(R.id.btn_originalinfo_Detail);

        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedInfo();
            }
        });

        btn_orderinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedOrderInfo();
            }
        });

        btn_originalinfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedOriginalInfo();
            }
        });



        ((TextView)findViewById(R.id.tv_bidTitle_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidNo_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidCategory_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidOrder_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidDemand_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidCharger_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidPhone_Detail)).setText();

        ((TextView)findViewById(R.id.tv_bidLocation_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidBusiness_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidPrice1_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidPrice2_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidLimitPrice_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidPercent_Detail)).setText();

        ((TextView)findViewById(R.id.tv_bidPeriod1_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidPeriod2_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidPeriod3_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidPeriod4_Detail)).setText();
        ((TextView)findViewById(R.id.tv_bidPeriod5_Detail)).setText();






    }

    private void clickedInfo()
    {
        btn_info.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_info.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_info.setTypeface(null, Typeface.BOLD);
        btn_info.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_orderinfo.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_orderinfo.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_orderinfo.setTypeface(null, Typeface.NORMAL);
        btn_orderinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_originalinfo.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_originalinfo.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_originalinfo.setTypeface(null, Typeface.NORMAL);
        btn_originalinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

    }

    private void clickedOrderInfo()
    {
        btn_orderinfo.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_orderinfo.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_orderinfo.setTypeface(null, Typeface.BOLD);
        btn_orderinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_info.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_info.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_info.setTypeface(null, Typeface.NORMAL);
        btn_info.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_originalinfo.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_originalinfo.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_originalinfo.setTypeface(null, Typeface.NORMAL);
        btn_originalinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

    }

    private void clickedOriginalInfo()
    {
        btn_originalinfo.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_originalinfo.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_originalinfo.setTypeface(null, Typeface.BOLD);
        btn_originalinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_orderinfo.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_orderinfo.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_orderinfo.setTypeface(null, Typeface.NORMAL);
        btn_orderinfo.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_info.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_info.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_info.setTypeface(null, Typeface.NORMAL);
        btn_info.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
    }
}
