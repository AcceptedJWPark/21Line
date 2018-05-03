package com.mobile.a21line.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.a21line.Home.Home_Activity;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;


public class Join_Activity_Second extends AppCompatActivity {


    Button btn_next;

    Button btn_companyuser;
    Button btn_privateuser;
    Button btn_orderuser;
    Context mContext;

    EditText et_id;
    EditText et_pw;
    EditText et_pw2;
    EditText et_email;
    EditText et_email2;

    EditText et_company;
    EditText et_ceo;
    EditText et_companyNo1;
    EditText et_companyNo2;
    EditText et_companyNo3;
    EditText et_name;
    EditText et_birth;
    EditText et_business;
    EditText et_businessType;
    EditText et_charge;
    EditText et_tel;
    EditText et_phone;
    EditText et_fax;
    EditText et_homepage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.join_activity_second);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("회원가입(2/2)");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);

        mContext = getApplicationContext();

        btn_next = findViewById(R.id.btn_next_joinsecond);

        btn_companyuser = findViewById(R.id.btn_companyuser_join);
        btn_privateuser = findViewById(R.id.btn_privateuser_join);
        btn_orderuser = findViewById(R.id.btn_orderuser_join);

        et_id = findViewById(R.id.et_id_join2);
        et_pw = findViewById(R.id.et_pw_join2);
        et_pw2 = findViewById(R.id.et_pw2_join2);
        et_email = findViewById(R.id.et_email_join2);
        et_email2 = findViewById(R.id.et_email2_join2);
        et_company = findViewById(R.id.et_company_joinInfo);
        et_ceo= findViewById(R.id.et_ceo_joinInfo);
        et_companyNo1= findViewById(R.id.et_companyNo1_joinInfo);
        et_companyNo2= findViewById(R.id.et_companyNo2_joinInfo);
        et_companyNo3= findViewById(R.id.et_companyNo3_joinInfo);
        et_business= findViewById(R.id.et_business_joinInfo);
        et_businessType= findViewById(R.id.et_businessType_joinInfo);
        et_charge= findViewById(R.id.et_charge_joinInfo);
        et_tel= findViewById(R.id.et_tel_joinInfo);
        et_phone= findViewById(R.id.et_phone_joinInfo);
        et_fax= findViewById(R.id.et_fax_joinInfo);
        et_homepage= findViewById(R.id.et_homepage_joinInfo);
        et_name= findViewById(R.id.et_name_joinInfo);
        et_birth= findViewById(R.id.et_birth_joinInfo);


        companyUserClicked();

        btn_companyuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                companyUserClicked();

            }
        });
        btn_privateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privateUserClicked();
            }
        });
        btn_orderuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderUserClicked();
            }
        });

        et_id.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_pw.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_pw2.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_email2.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_company.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_ceo.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_companyNo1.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_companyNo2.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_companyNo3.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_business.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_businessType.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_charge.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_tel.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_fax.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_homepage.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_birth.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});


        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Home_Activity.class);
                startActivity(intent);
            }
        });

    }

    public void companyUserClicked(){
        btn_companyuser.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_companyuser.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_companyuser.setTypeface(null, Typeface.BOLD);
        btn_companyuser.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_privateuser.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_privateuser.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_privateuser.setTypeface(null, Typeface.NORMAL);
        btn_privateuser.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_orderuser.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_orderuser.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_orderuser.setTypeface(null, Typeface.NORMAL);
        btn_orderuser.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        ((LinearLayout)findViewById(R.id.ll_name_joinInfo)).setVisibility(View.GONE);
        ((LinearLayout)findViewById(R.id.ll_birth_joinInfo)).setVisibility(View.GONE);
        ((LinearLayout)findViewById(R.id.ll_address_joinInfo)).setVisibility(View.GONE);

        ((LinearLayout)findViewById(R.id.ll_company_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_ceo_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_companyNo_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_business_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_businessType_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_charge_joinInfo)).setVisibility(View.VISIBLE);

        ((TextView)findViewById(R.id.ll_title_joinInfo)).setText("기업정보입력");


   }

    public void privateUserClicked(){
        btn_privateuser.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_privateuser.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_privateuser.setTypeface(null, Typeface.BOLD);
        btn_privateuser.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_companyuser.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_companyuser.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_companyuser.setTypeface(null, Typeface.NORMAL);
        btn_companyuser.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_orderuser.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_orderuser.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_orderuser.setTypeface(null, Typeface.NORMAL);
        btn_orderuser.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        ((LinearLayout)findViewById(R.id.ll_name_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_birth_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_address_joinInfo)).setVisibility(View.GONE);

        ((LinearLayout)findViewById(R.id.ll_company_joinInfo)).setVisibility(View.GONE);
        ((LinearLayout)findViewById(R.id.ll_ceo_joinInfo)).setVisibility(View.GONE);
        ((LinearLayout)findViewById(R.id.ll_companyNo_joinInfo)).setVisibility(View.GONE);
        ((LinearLayout)findViewById(R.id.ll_business_joinInfo)).setVisibility(View.GONE);
        ((LinearLayout)findViewById(R.id.ll_businessType_joinInfo)).setVisibility(View.GONE);
        ((LinearLayout)findViewById(R.id.ll_charge_joinInfo)).setVisibility(View.GONE);

        ((TextView)findViewById(R.id.ll_title_joinInfo)).setText("개인정보입력");
    }

    public void orderUserClicked(){
        btn_orderuser.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_orderuser.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_orderuser.setTypeface(null, Typeface.BOLD);
        btn_orderuser.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_privateuser.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_privateuser.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_privateuser.setTypeface(null, Typeface.NORMAL);
        btn_privateuser.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_companyuser.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_companyuser.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_companyuser.setTypeface(null, Typeface.NORMAL);
        btn_companyuser.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        ((LinearLayout)findViewById(R.id.ll_name_joinInfo)).setVisibility(View.GONE);
        ((LinearLayout)findViewById(R.id.ll_birth_joinInfo)).setVisibility(View.GONE);
        ((LinearLayout)findViewById(R.id.ll_address_joinInfo)).setVisibility(View.VISIBLE);

        ((LinearLayout)findViewById(R.id.ll_company_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_ceo_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_companyNo_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_business_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_businessType_joinInfo)).setVisibility(View.VISIBLE);
        ((LinearLayout)findViewById(R.id.ll_charge_joinInfo)).setVisibility(View.VISIBLE);

        ((TextView)findViewById(R.id.ll_title_joinInfo)).setText("발주처정보입력");
    }
}
