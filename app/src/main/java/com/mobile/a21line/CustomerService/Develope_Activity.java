package com.mobile.a21line.CustomerService;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Develope_Activity extends AppCompatActivity {

    Context mContext;
    EditText et_catogory;
    EditText et_comName;
    EditText et_manName;
    EditText et_celPhone;
    Button et_save;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cs_develope_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("컨텐츠 개발의뢰");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        et_catogory = findViewById(R.id.et_develope_catogory);
        et_comName = findViewById(R.id. et_develope_comName);
        et_manName = findViewById(R.id.et_develope_manName);
        et_celPhone = findViewById(R.id.et_develope_celPhone);
        et_save = findViewById(R.id.btn_save_develope);


        et_catogory.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });
        et_comName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });
        et_manName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });
        et_celPhone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });

        et_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(et_catogory.length() == 0)
                {
                    Toast.makeText(mContext,"개발 분야를 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
                else if(et_comName.length() == 0 && et_manName.length() == 0)
                {
                    Toast.makeText(mContext,"업체명 또는 담당자 이름을 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
                else if(et_celPhone.length() == 0)
                {
                    Toast.makeText(mContext,"담당자 번호를 입력해주세요.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Toast.makeText(mContext,"개발 의뢰가 완료되었습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
