package com.mobile.a21line.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Home.Home_Activity;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Join_Activity_Second extends AppCompatActivity {


    Button btn_next;

    Button btn_companyuser;
    Button btn_privateuser;
    Button btn_orderuser;
    Button btn_checkMemID;
    boolean compCheckMemID = false;
    EditText et_memID;
    Context mContext;


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

        btn_checkMemID = findViewById(R.id.btn_checkMemID_joinsecond);
        btn_checkMemID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_memID.getText().toString().isEmpty()){
                    Toast.makeText(mContext, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    checkMemID();
                }
            }
        });

        et_memID = findViewById(R.id.et_memID_joinsecond);
        et_memID.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                compCheckMemID = false;
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

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

        findViewById(R.id.ll_name_joinInfo).setVisibility(View.GONE);
        findViewById(R.id.ll_birth_joinInfo).setVisibility(View.GONE);

        findViewById(R.id.ll_charge_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_company_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_ceo_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_companyNo_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_address_joinInfo).setVisibility(View.GONE);
        findViewById(R.id.ll_business_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_businessType_joinInfo).setVisibility(View.VISIBLE);
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

        findViewById(R.id.ll_name_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_birth_joinInfo).setVisibility(View.VISIBLE);

        findViewById(R.id.ll_charge_joinInfo).setVisibility(View.GONE);
        findViewById(R.id.ll_company_joinInfo).setVisibility(View.GONE);
        findViewById(R.id.ll_ceo_joinInfo).setVisibility(View.GONE);
        findViewById(R.id.ll_companyNo_joinInfo).setVisibility(View.GONE);
        findViewById(R.id.ll_address_joinInfo).setVisibility(View.GONE);
        findViewById(R.id.ll_business_joinInfo).setVisibility(View.GONE);
        findViewById(R.id.ll_businessType_joinInfo).setVisibility(View.GONE);
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

        findViewById(R.id.ll_name_joinInfo).setVisibility(View.GONE);
        findViewById(R.id.ll_birth_joinInfo).setVisibility(View.GONE);

        findViewById(R.id.ll_charge_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_company_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_ceo_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_companyNo_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_address_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_business_joinInfo).setVisibility(View.VISIBLE);
        findViewById(R.id.ll_businessType_joinInfo).setVisibility(View.VISIBLE);
    }

    public void checkMemID(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Member/checkMemID.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, "사용가능한 아이디 입니다.", Toast.LENGTH_SHORT).show();
                        compCheckMemID = true;
                    }else{
                        Toast.makeText(mContext, "이미 사용중인 아이디 입니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("sMemID", et_memID.getText().toString());
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }
}
