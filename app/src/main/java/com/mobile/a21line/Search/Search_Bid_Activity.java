package com.mobile.a21line.Search;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.mobile.a21line.Bid.Bid_Activity;
import com.mobile.a21line.R;
import com.mobile.a21line.Result.Result_Activity;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.Setbid.Setbid_Activity;
import com.mobile.a21line.Setbid.Setbid_Popup_BusinessSelect;
import com.mobile.a21line.Setbid.Setbid_Popup_LocationSelect;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Search_Bid_Activity extends AppCompatActivity {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;
    boolean isBid;

    String EMoney = "0" , SMoney = "0";

    TextView tv_business;
    TextView tv_location;
    TextView tv_bidType;
    TextView tv_price;
    TextView tv_period;

    String SDate = getMonthAgoDate(1);
    String EDate = getMonthAgoDate(0);

    Button btn_go_search;

    int[] bidTypeCode = {0x1, 0x2, 0x4, 0x8, 0x10, 0x20, 0x40, 0x80, 0x100, 0x200, 0x400, 0x800, 0x1000, 0x4000, 0x8000, 0x10000, 0x20000, 0x40000, 0x80000};
    String[] bidTypeName = { "정정공고", "긴급공고", "결과발표", "계약공고", "전자입찰", "취소공고", "재공고", "견적입찰", "수의계약", "일반", "공동도급", "현장설명참조", "역경매", "재입찰", "지명입찰", "제조", "시담", "여성", "유찰공고"};


    int bidType = 0;

    TextView tv_searchType1;
    TextView tv_searchType2;
    TextView tv_searchType3;
    TextView tv_searchType4;
    TextView tv_searchType5;

    String SearchType = "OrderName";

    LinearLayout ll_container_total;
    LinearLayout ll_container1;
    LinearLayout ll_container2;
    LinearLayout ll_container3;
    LinearLayout ll_container4;
    LinearLayout ll_container5;
    LinearLayout ll_container6;
    LinearLayout ll_container7;
    String SearchMoneyType = "EstimatedPrice";

    int ll_containerTotalHeight;

    EditText et_search;


    BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_activity);
        mContext = getApplicationContext();

        isBid = getIntent().getBooleanExtra("isBid", true);

        Setbid_Activity.arrayList_business.clear();
        Setbid_Activity.arrayList_location.clear();

        if(isBid) {
            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("입찰 통합검색");
        }else{
            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("낙찰 통합검색");
        }
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setText("초기화");
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initSetting();
            }
        });
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.dl_search);
        frameLayout = findViewById(R.id.fl_drawerView_search);




        final View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_search.isFocused())
                {
                    SaveSharedPreference.hideKeyboard(et_search,mContext);
                    DrawerLayout_Open(v, Search_Bid_Activity.this, drawerLayout, frameLayout);
                }
                else
                    {
                    DrawerLayout_Open(v, Search_Bid_Activity.this, drawerLayout, frameLayout);
                }
            }
        };
        DrawerLayout_ClickEvent(Search_Bid_Activity.this, mClicklistener);


        ll_container_total = findViewById(R.id.ll_container_total_search);
        ll_container1 = findViewById(R.id.ll_container1_search);
        ll_container2 = findViewById(R.id.ll_container2_search);
        ll_container3 = findViewById(R.id.ll_container3_search);
        ll_container4 = findViewById(R.id.ll_container4_search);
        ll_container5 = findViewById(R.id.ll_container5_search);
        ll_container6 = findViewById(R.id.ll_container6_search);
        ll_container7 = findViewById(R.id.ll_container7_search);

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        ll_containerTotalHeight = dm.heightPixels-getResources().getDimensionPixelSize(R.dimen.height_40dp)-getResources().getDimensionPixelSize(R.dimen.height_25dp);

//        ViewGroup.LayoutParams params1 = ll_container1.getLayoutParams();
//        ViewGroup.LayoutParams params2 = ll_container2.getLayoutParams();
//        ViewGroup.LayoutParams params3 = ll_container3.getLayoutParams();
//        ViewGroup.LayoutParams params4 = ll_container4.getLayoutParams();
//        ViewGroup.LayoutParams params5 = ll_container5.getLayoutParams();
//        ViewGroup.LayoutParams params6 = ll_container6.getLayoutParams();
//        ViewGroup.LayoutParams params7 = ll_container7.getLayoutParams();

        ll_container1.setMinimumHeight((int) (ll_containerTotalHeight*0.122));
        ll_container2.setMinimumHeight((int) (ll_containerTotalHeight*0.122));
        ll_container3.setMinimumHeight((int) (ll_containerTotalHeight*0.122));
        ll_container4.setMinimumHeight((int) (ll_containerTotalHeight*0.122));
        ll_container5.setMinimumHeight((int) (ll_containerTotalHeight*0.122));
        ll_container6.setMinimumHeight((int) (ll_containerTotalHeight*0.152));
        ll_container7.setMinimumHeight((int) (ll_containerTotalHeight*0.1));

//        params1.height = (int) (ll_containerTotalHeight*0.122);
//        params2.height = (int) (ll_containerTotalHeight*0.122);
//        params3.height = (int) (ll_containerTotalHeight*0.122);
//        params4.height = (int) (ll_containerTotalHeight*0.122);
//        params5.height = (int) (ll_containerTotalHeight*0.122);
//        params6.height = (int) (ll_containerTotalHeight*0.172);
//        params7.height = (int) (ll_containerTotalHeight*0.1);
//
//        ll_container1.setLayoutParams(params1);
//        ll_container2.setLayoutParams(params2);
//        ll_container3.setLayoutParams(params3);
//        ll_container4.setLayoutParams(params4);
//        ll_container5.setLayoutParams(params5);
//        ll_container6.setLayoutParams(params6);
//        ll_container7.setLayoutParams(params7);


        et_search = findViewById(R.id.et_search);
        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener()
        {
            @Override public void onFocusChange(View v, boolean hasFocus)
            {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);}
            }
        });

        tv_searchType1 = findViewById(R.id.tv_searchType1_search);
        tv_searchType2 = findViewById(R.id.tv_searchType2_search);
        tv_searchType3 = findViewById(R.id.tv_searchType3_search);
        tv_searchType4 = findViewById(R.id.tv_searchType4_search);
        tv_searchType5 = findViewById(R.id.tv_searchType5_search);

        if(isBid){
            findViewById(R.id.view_searchType4_line).setVisibility(View.GONE);
            findViewById(R.id.view_searchType5_line).setVisibility(View.GONE);
            tv_searchType4.setVisibility(View.GONE);
            tv_searchType5.setVisibility(View.GONE);
        }

        tv_searchType1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchType = "OrderName";
                tv_searchType1.setTextColor(Color.BLACK);
                tv_searchType2.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType3.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType4.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType5.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                et_search.setHint("발주처명으로 검색합니다.");
            }
        });

        tv_searchType2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchType = "BidName";
                tv_searchType2.setTextColor(Color.BLACK);
                tv_searchType1.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType3.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType4.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType5.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                et_search.setHint("공고명으로 검색합니다.");
            }
        });

        tv_searchType3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchType = "BidNo";
                tv_searchType3.setTextColor(Color.BLACK);
                tv_searchType2.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType1.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType4.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType5.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                et_search.setHint("공고번호로 검색합니다.");
            }
        });

        tv_searchType4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchType = "RealCompany";
                tv_searchType4.setTextColor(Color.BLACK);
                tv_searchType2.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType3.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType1.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType5.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                et_search.setHint("낙찰업체명으로 검색합니다.");
            }
        });

        tv_searchType5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SearchType = "FirstCompany";
                tv_searchType5.setTextColor(Color.BLACK);
                tv_searchType2.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType3.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType4.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                tv_searchType1.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_addition));
                et_search.setHint("1순위 업체명으로 검색합니다.");
            }
        });




        tv_price = findViewById(R.id.tv_price_search);
        tv_price.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.clearFocus();
                Intent intent = new Intent(Search_Bid_Activity.this,Search_Price_Popup.class);
                intent.putExtra("SMoney", SMoney);
                intent.putExtra("EMoney", EMoney);
                intent.putExtra("SearchMoneyType", SearchMoneyType);

                startActivityForResult(intent, 1);
            }
        });

        tv_period = findViewById(R.id.tv_period_search);
        tv_period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.clearFocus();
                Intent intent = new Intent(Search_Bid_Activity.this,Search_Period_Popup.class);
                intent.putExtra("SDate", SDate);
                intent.putExtra("EDate", EDate);
                startActivityForResult(intent, 2);
            }
        });

        tv_bidType = findViewById(R.id.tv_bidType_search);
        tv_bidType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.clearFocus();
                Intent intent = new Intent(Search_Bid_Activity.this,Search_BidType_Popup.class);
                intent.putExtra("BidType", bidType);
                startActivityForResult(intent, 0);
            }
        });

        tv_business = findViewById(R.id.tv_business_search);
        tv_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.clearFocus();
                Intent intent = new Intent(Search_Bid_Activity.this, Setbid_Popup_BusinessSelect.class);
                startActivity(intent);
            }
        });

        tv_location = findViewById(R.id.tv_location_search);
        tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_search.clearFocus();
                Intent intent = new Intent(Search_Bid_Activity.this,Setbid_Popup_LocationSelect.class);
                startActivity(intent);
            }
        });

        btn_go_search = findViewById(R.id.btn_go_search);
        btn_go_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(isBid){
                    intent = new Intent(mContext, Bid_Activity.class);
                }else{
                    intent = new Intent(mContext, Result_Activity.class);
                }
                intent.putExtra("BidType", bidType);
                intent.putExtra("SMoney", SMoney);
                intent.putExtra("EMoney", EMoney);
                intent.putExtra("SDate", SDate);
                intent.putExtra("EDate", EDate);
                intent.putExtra("SearchType", SearchType);
                intent.putExtra("SearchText", et_search.getText().toString());
                intent.putExtra("SearchMoneyType", SearchMoneyType);
                intent.putExtra("isTotalSearch", true);

                startActivity(intent);
            }
        });


        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("com.mobile.a21line.finishActivity");

        mReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                finish();
            }
        };

        registerReceiver(mReceiver, intentFilter);
    }

    @Override
    public void onDestroy(){
        super.onDestroy();
        unregisterReceiver(mReceiver);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                bidType = intent.getIntExtra("BidType", 0);
                if(bidType == 0){
                    ((TextView)findViewById(R.id.tv_bidType_search)).setText("클릭해서 추가");
                }else{
                    StringBuilder sb = new StringBuilder();
                    for(int i = 0; i < bidTypeCode.length; i++){
                        int temp = bidType;
                        temp = temp & bidTypeCode[i];
                        if(temp > 0){
                            sb.append(bidTypeName[i]).append(", ");
                        }
                    }

                    ((TextView)findViewById(R.id.tv_bidType_search)).setText(sb.toString().substring(0, sb.toString().length() - 2));
                }
            }
        }else if(requestCode == 1){
            if(resultCode == RESULT_OK){
                SMoney = intent.getStringExtra("SMoney");
                EMoney = intent.getStringExtra("EMoney");
                SearchMoneyType = intent.getStringExtra("SearchMoneyType");

                if(EMoney.equals("0")) {
                    ((TextView) findViewById(R.id.tv_price_search)).setText("클릭해서 추가");
                }else{
                    ((TextView) findViewById(R.id.tv_price_search)).setText(SMoney + "억 ~ " + EMoney + "억");
                }
            }
        }else if(requestCode == 2){
            if(resultCode == RESULT_OK){
                SDate = intent.getStringExtra("SDate");
                EDate = intent.getStringExtra("EDate");
                if(SDate.equals(EDate)){
                    ((TextView) findViewById(R.id.tv_period_search)).setText(SDate);
                }else {
                    ((TextView) findViewById(R.id.tv_period_search)).setText(SDate + " ~ " + EDate);
                }
            }
        }
    }

    private void initSetting(){
        Setbid_Activity.arrayList_business.clear();
        Setbid_Activity.arrayList_location.clear();

        ((TextView)findViewById(R.id.tv_business_search)).setText("클릭해서 추가");
        ((TextView)findViewById(R.id.tv_search_businessCount)).setText("업종 선택(" + Setbid_Activity.arrayList_business.size() + ")");

        ((TextView)findViewById(R.id.tv_location_search)).setText("클릭해서 추가");
        ((TextView)findViewById(R.id.tv_search_locationCount)).setText("지역 선택(" + Setbid_Activity.arrayList_location.size() + ")");

        bidType = 0;
        ((TextView)findViewById(R.id.tv_bidType_search)).setText("클릭해서 추가");

        EMoney = "0";
        SMoney = "0";
        ((TextView) findViewById(R.id.tv_price_search)).setText("클릭해서 추가");

        EDate = getMonthAgoDate(0);
        SDate = getMonthAgoDate(1);
        ((TextView) findViewById(R.id.tv_period_search)).setText("클릭해서 추가");
    }

    @Override
    public void onResume(){
        super.onResume();

        drawerLayout.closeDrawers();

        if(Setbid_Activity.arrayList_business.size() > 0){
            StringBuilder selectedBusiness = new StringBuilder();
            for(int i = 0; i < Setbid_Activity.arrayList_business.size(); i++){
                selectedBusiness.append(Setbid_Activity.arrayList_business.get(i).getName()).append(", ");
            }
            ((TextView)findViewById(R.id.tv_business_search)).setText(selectedBusiness.toString().substring(0, selectedBusiness.toString().length() - 2));
        }else{
            ((TextView)findViewById(R.id.tv_business_search)).setText("클릭해서 추가");
        }
        ((TextView)findViewById(R.id.tv_search_businessCount)).setText("업종 선택(" + Setbid_Activity.arrayList_business.size() + ")");

        if(Setbid_Activity.arrayList_location.size() > 0){
            StringBuilder selectedLocation = new StringBuilder();
            for(int i = 0; i < Setbid_Activity.arrayList_location.size(); i++){
                selectedLocation.append(Setbid_Activity.arrayList_location.get(i).getName()).append(", ");
            }
            ((TextView)findViewById(R.id.tv_location_search)).setText(selectedLocation.toString().substring(0, selectedLocation.toString().length() - 2));
        }else{
            ((TextView)findViewById(R.id.tv_location_search)).setText("클릭해서 추가");
        }
        ((TextView)findViewById(R.id.tv_search_locationCount)).setText("지역 선택(" + Setbid_Activity.arrayList_location.size() + ")");
    }

    private String getMonthAgoDate(int month){
        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");
        Calendar cal = Calendar.getInstance(time);
        cal.add(Calendar.MONTH, -month);

        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(time);
        String strDate = sdf.format(date);
        return strDate;
    }

    @Override
    protected void onNewIntent(Intent newIntent){
        super.onNewIntent(newIntent);
        setIntent(newIntent);
        isBid = newIntent.getBooleanExtra("isBid", true);

        if(isBid) {
            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("입찰 통합검색");
        }else{
            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("낙찰 통합검색");
        }

        if(isBid){
            findViewById(R.id.view_searchType4_line).setVisibility(View.GONE);
            findViewById(R.id.view_searchType5_line).setVisibility(View.GONE);
            tv_searchType4.setVisibility(View.GONE);
            tv_searchType5.setVisibility(View.GONE);
        }else{
            findViewById(R.id.view_searchType4_line).setVisibility(View.VISIBLE);
            findViewById(R.id.view_searchType5_line).setVisibility(View.VISIBLE);
            tv_searchType4.setVisibility(View.VISIBLE);
            tv_searchType5.setVisibility(View.VISIBLE);
        }

        btn_go_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent;
                if(isBid){
                    intent = new Intent(mContext, Bid_Activity.class);
                }else{
                    intent = new Intent(mContext, Result_Activity.class);
                }
                intent.putExtra("BidType", bidType);
                intent.putExtra("SMoney", SMoney);
                intent.putExtra("EMoney", EMoney);
                intent.putExtra("SDate", SDate);
                intent.putExtra("EDate", EDate);
                intent.putExtra("SearchType", SearchType);
                intent.putExtra("SearchText", et_search.getText().toString());
                intent.putExtra("isTotalSearch", true);

                startActivity(intent);
            }
        });

        initSetting();
    }


}
