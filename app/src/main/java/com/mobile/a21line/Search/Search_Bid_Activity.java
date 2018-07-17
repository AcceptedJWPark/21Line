package com.mobile.a21line.Search;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
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

    EditText et_search;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.search_activity);
        mContext = getApplicationContext();

        isBid = getIntent().getBooleanExtra("isBid", true);

        Setbid_Activity.arrayList_business.clear();
        Setbid_Activity.arrayList_location.clear();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("입찰 통합검색");
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
                DrawerLayout_Open(v, Search_Bid_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Search_Bid_Activity.this, mClicklistener);

        et_search = findViewById(R.id.et_search);
        et_search.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus) {
            SaveSharedPreference.hideKeyboard(v,mContext);}}});

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
                Intent intent = new Intent(Search_Bid_Activity.this,Search_Price_Popup.class);
                intent.putExtra("SMoney", SMoney);
                intent.putExtra("EMoney", EMoney);
                startActivityForResult(intent, 1);
            }
        });

        tv_period = findViewById(R.id.tv_period_search);
        tv_period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                Intent intent = new Intent(Search_Bid_Activity.this,Search_BidType_Popup.class);
                intent.putExtra("BidType", bidType);
                startActivityForResult(intent, 0);
            }
        });

        tv_business = findViewById(R.id.tv_business_search);
        tv_business.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Search_Bid_Activity.this, Setbid_Popup_BusinessSelect.class);
                startActivity(intent);
            }
        });

        tv_location = findViewById(R.id.tv_location_search);
        tv_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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
                intent.putExtra("isTotalSearch", true);

                startActivity(intent);
            }
        });
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


}
