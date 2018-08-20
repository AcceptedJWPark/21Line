package com.mobile.a21line.CustomerService;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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

public class Education_Activity extends AppCompatActivity {

    Context mContext;
    EditText et_company;
    EditText et_name;
    EditText et_phone;
    TextView et_date;
    EditText et_count;
    boolean[] arrChk = new boolean[5];
    boolean isAccpetPrivateInfo;
    LinearLayout ll_isAcceptPrivateInfo;
    ImageView iv_isAcceptPrivateInfo;
    LinearLayout[] arrLLChk = new LinearLayout[5];
    ImageView[] arrIVChk = new ImageView[5];


    BroadcastReceiver mReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cs_education_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("입찰교육신청");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);
        ((EditText) findViewById(R.id.et_education_comName)).setText(SaveSharedPreference.getUserComName(mContext));

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        arrLLChk[0] = findViewById(R.id.ll_education_chkone);
        arrLLChk[1] = findViewById(R.id.ll_education_chktwo);
        arrLLChk[2] = findViewById(R.id.ll_education_chkthree);
        arrLLChk[3] = findViewById(R.id.ll_education_chkfour);
        arrLLChk[4] = findViewById(R.id.ll_education_chkfive);


        arrIVChk[0] = findViewById(R.id.iv_education_chkone);
        arrIVChk[1] = findViewById(R.id.iv_education_chktwo);
        arrIVChk[2] = findViewById(R.id.iv_education_chkthree);
        arrIVChk[3] = findViewById(R.id.iv_education_chkfour);
        arrIVChk[4] = findViewById(R.id.iv_education_chkfive);

        iv_isAcceptPrivateInfo = findViewById(R.id.iv_isAcceptPrivateInfo);


        ll_isAcceptPrivateInfo = findViewById(R.id.ll_isAcceptPrivateInfo);
        ll_isAcceptPrivateInfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAccpetPrivateInfo){
                    iv_isAcceptPrivateInfo.setImageResource(R.drawable.icon_chechbox_unchecked);
                }else{
                    iv_isAcceptPrivateInfo.setImageResource(R.drawable.icon_chechbox_checked);
                }

                isAccpetPrivateInfo = !isAccpetPrivateInfo;
            }
        });

        for(int i = 0; i < arrLLChk.length; i++){
            final int index = i;
            arrLLChk[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(arrChk[index]){
                        arrIVChk[index].setImageResource(R.drawable.icon_chechbox_unchecked);
                    }else{
                        arrIVChk[index].setImageResource(R.drawable.icon_chechbox_checked);
                    }

                    arrChk[index] = !arrChk[index];
                }
            });
        }

        et_company = findViewById(R.id.et_education_comName);
        et_name = findViewById(R.id.et_name_education);
        et_phone = findViewById(R.id.et_phone_education);
        et_date = findViewById(R.id.et_date_education);
        et_count = findViewById(R.id.et_count_education);

        ((TextView)findViewById(R.id.tv_callnumber_education)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1599-2127"));
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
            }
        });


        et_company.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });
        et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });
        et_date.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });
        et_count.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });

        ((Button)findViewById(R.id.btn_education_request)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAccpetPrivateInfo) {
                    requestEducation();
                }else{
                    Toast.makeText(mContext, "개인정보 보호 동의를 체크해주세요.", Toast.LENGTH_SHORT).show();
                }
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
    public void requestEducation(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getBidDataUri() + "requestEdu.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, "교육신청이 완료되었습니다. 담당자가 전화드리도록 하겠습니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "교육신청이 실패하였습니다. 담당자에게 문의하여 주시기 바랍니다.", Toast.LENGTH_SHORT).show();
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
                params.put("txtComName", ((TextView)findViewById(R.id.et_education_comName)).getText().toString());
                params.put("txtManName", ((TextView)findViewById(R.id.et_name_education)).getText().toString());
                params.put("txtPhone", ((TextView)findViewById(R.id.et_phone_education)).getText().toString());
                params.put("txtNum", ((TextView)findViewById(R.id.et_count_education)).getText().toString());
                params.put("txtPay", String.valueOf(11 * Integer.parseInt(params.get("txtNum"))));
                params.put("chkOne", (arrChk[0])? "Y" : "N");
                params.put("chkTwo", (arrChk[1])? "Y" : "N");
                params.put("chkThree", (arrChk[2])? "Y" : "N");
                params.put("chkFour", (arrChk[3])? "Y" : "N");
                params.put("chkFive", (arrChk[4])? "Y" : "N");
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

}
