package com.mobile.a21line.Login;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;


public class Join_Activity_Second extends AppCompatActivity {


    Button btn_companyuser;
    Button btn_privateuser;
    Button btn_orderuser;
    Context mContext;

    EditText et_id;
    EditText et_pw;
    EditText et_pw2;
    EditText et_email;
    EditText et_email2;
    EditText et_company_orderUser;
    EditText et_ceo_orderUser;
    EditText et_companyNo1_orderUser;
    EditText et_companyNo2_orderUser;
    EditText et_companyNo3_orderUser;
    EditText et_business_orderUser;
    EditText et_businessType_orderUser;
    EditText et_charge_orderUser;
    EditText et_tel_orderUser;
    EditText et_phone_orderUser;
    EditText et_fax_orderUser;
    EditText et_homepage_orderUser;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.join_activity_second);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("회원가입(2/2)");

        mContext = getApplicationContext();

        btn_companyuser = findViewById(R.id.btn_companyuser_join);
        btn_privateuser = findViewById(R.id.btn_privateuser_join);
        btn_orderuser = findViewById(R.id.btn_orderuser_join);

        et_id = findViewById(R.id.et_id_join2);
        et_pw = findViewById(R.id.et_pw_join2);
        et_pw2 = findViewById(R.id.et_pw2_join2);
        et_email = findViewById(R.id.et_email_join2);
        et_email2 = findViewById(R.id.et_email2_join2);
        et_company_orderUser = findViewById(R.id.et_company_orderUser);
        et_ceo_orderUser= findViewById(R.id.et_ceo_orderUser);
        et_companyNo1_orderUser= findViewById(R.id.et_companyNo1_orderUser);
        et_companyNo2_orderUser= findViewById(R.id.et_companyNo2_orderUser);
        et_companyNo3_orderUser= findViewById(R.id.et_companyNo3_orderUser);
        et_business_orderUser= findViewById(R.id.et_business_orderUser);
        et_businessType_orderUser= findViewById(R.id.et_businessType_orderUser);
        et_charge_orderUser= findViewById(R.id.et_charge_orderUser);
        et_tel_orderUser= findViewById(R.id.et_tel_orderUser);
        et_phone_orderUser= findViewById(R.id.et_phone_orderUser);
        et_fax_orderUser= findViewById(R.id.et_fax_orderUser);
        et_homepage_orderUser= findViewById(R.id.et_homepage_orderUser);

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
        et_company_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_ceo_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_companyNo1_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_companyNo2_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_companyNo3_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_business_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_businessType_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_charge_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_tel_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_phone_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_fax_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_homepage_orderUser.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});


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

        findViewById(R.id.inc_companyUser).setVisibility(View.VISIBLE);
        findViewById(R.id.inc_privateUser).setVisibility(View.GONE);
        findViewById(R.id.inc_orderUser).setVisibility(View.GONE);
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

        findViewById(R.id.inc_companyUser).setVisibility(View.GONE);
        findViewById(R.id.inc_privateUser).setVisibility(View.VISIBLE);
        findViewById(R.id.inc_orderUser).setVisibility(View.GONE);
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

        findViewById(R.id.inc_companyUser).setVisibility(View.GONE);
        findViewById(R.id.inc_privateUser).setVisibility(View.GONE);
        findViewById(R.id.inc_orderUser).setVisibility(View.VISIBLE);
    }
}
