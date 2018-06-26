package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Education_Activity extends AppCompatActivity {

    Context mContext;
    EditText et_company;
    EditText et_name;
    EditText et_phone;
    EditText et_date;
    EditText et_count;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cs_education_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("입찰교육신청");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_company = findViewById(R.id.et_company_education);
        et_name = findViewById(R.id.et_name_education);
        et_phone = findViewById(R.id.et_phone_education);
        et_date = findViewById(R.id.et_date_education);
        et_count = findViewById(R.id.et_count_education);

        et_company.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}    }
        });
        et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}    }
        });
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}    }
        });
        et_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}    }
        });
        et_count.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}    }
        });
    }

}
