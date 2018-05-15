package com.mobile.a21line.Setbid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobile.a21line.BidAreaCode;
import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;

import java.util.ArrayList;
import java.util.List;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;


public class Setbid_Activity extends AppCompatActivity {

    private Context mContext;
    private String[] priceType = {"추정금액","기초금액"};

    TextView btn_businessselect;
    TextView btn_locationselect;

    private Setbid_LVAdapter adapter;

    EditText et_price1;
    EditText et_price2;

    Button btn_price1;
    Button btn_price2;
    Button btn_price3;
    Button btn_price4;
    Button btn_price5;

    Button btn_bidType;
    Button btn_exception;
    Button btn_etc;

    Spinner spn_pricetype;

    View frameLayout;
    DrawerLayout drawerLayout;

    ListView lv_business;
    ListView lv_location;

    Setbid_Dialog dialog;
    public static ArrayList<BidAreaCode.BidAreaItem> arrayList_location = new ArrayList<>();
    public static ArrayList<BidUpCode.BidUpCodeItem> arrayList_business = new ArrayList<>();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setbid_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("맞춤설정 1");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

        View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Setbid_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Setbid_Activity.this, mClicklistener);


        btn_locationselect = findViewById(R.id.tv_locationselect_Setbid);
        btn_locationselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, Setbid_Popup_LocationSelect.class);
                startActivity(i);
            }
        });

        btn_businessselect = findViewById(R.id.tv_businessselect_Setbid);
        btn_businessselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, Setbid_Popup_BusinessSelect.class);
                startActivity(i);
            }
        });

        lv_business = findViewById(R.id.lv_business_setbid);
        lv_location = findViewById(R.id.lv_location_setbid);


        adapter = new Setbid_LVAdapter(mContext, arrayList_business);
        lv_business.setAdapter(adapter);

        adapter = new Setbid_LVAdapter(mContext, arrayList_location);
        lv_location.setAdapter(adapter);


        et_price1 = findViewById(R.id.et_price1_setbid);
        et_price2 = findViewById(R.id.et_price2_setbid);
        et_price1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });
        et_price2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });

        btn_price1 = findViewById(R.id.btn_price1_setbid);
        btn_price2 = findViewById(R.id.btn_price2_setbid);
        btn_price3 = findViewById(R.id.btn_price3_setbid);
        btn_price4 = findViewById(R.id.btn_price4_setbid);
        btn_price5 = findViewById(R.id.btn_price5_setbid);
        btn_price1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_price1.setText("0");
                et_price2.setText("1");
            }
        });
        btn_price2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_price1.setText("1");
                et_price2.setText("5");
            }
        });
        btn_price3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_price1.setText("5");
                et_price2.setText("10");
            }
        });
        btn_price4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_price1.setText("10");
                et_price2.setText("50");
            }
        });
        btn_price5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_price1.setText("50");
                et_price2.setText("");
            }
        });

        spn_pricetype = findViewById(R.id.spn_pricetype_setbid);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.setbid_pricespinner, priceType);
        adapter.setDropDownViewResource(R.layout.setbid_pricespinner);
        spn_pricetype.setAdapter(adapter);

        btn_bidType = findViewById(R.id.btn_bidType_setbid);
        btn_exception = findViewById(R.id.btn_exception_setbid);
        btn_etc = findViewById(R.id.btn_etc_setbid);


        btn_bidType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arrayList1 = new ArrayList<>();
                arrayList1.add("역경매 제외");
                arrayList1.add("최소 제외");
                arrayList1.add("시담 제외");
                arrayList1.add("지명 제외");
                arrayList1.add("여성 제외");
                arrayList1.add("공공도급 제외");
                arrayList1.add("일반 제외");
                dialog = new Setbid_Dialog(Setbid_Activity.this, "공고 구분", arrayList1);
                dialog.show();
            }
        });

        btn_exception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arrayList2 = new ArrayList<>();
                arrayList2.add("조달청 제외");
                arrayList2.add("한국전력 제외");
                arrayList2.add("국방부 제외");
                arrayList2.add("학교장터 제외");
                arrayList2.add("market 제외");
                arrayList2.add("온비드 제외");
                arrayList2.add("EAT 제외");
                dialog = new Setbid_Dialog(Setbid_Activity.this, "제외 발주처", arrayList2);
                dialog.show();
            }
        });

        btn_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<String> arrayList3 = new ArrayList<>();
                arrayList3.add("아파트관련공고 제외");
                arrayList3.add("메일수신 제외");
                dialog = new Setbid_Dialog(Setbid_Activity.this, "기타 사항", arrayList3);
                dialog.show();
            }
        });


    }

    @Override
    public void onResume(){
        super.onResume();
        adapter = new Setbid_LVAdapter(mContext, arrayList_business);
        lv_business.setAdapter(adapter);

        adapter = new Setbid_LVAdapter(mContext, arrayList_location);
        lv_location.setAdapter(adapter);
    }

}

