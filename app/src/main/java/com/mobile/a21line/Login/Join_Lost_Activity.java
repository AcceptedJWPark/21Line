package com.mobile.a21line.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Patterns;
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
import com.mobile.a21line.MyBid.MyBid_Listitem;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Join_Lost_Activity extends AppCompatActivity {

    Context mContext;


    EditText et_email;
    EditText et_id;
    EditText et_phone;
    EditText et_confirm;

    String certNum;
    boolean compCelChk = false;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.join_lost_activity);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("ID / PW 분실");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mContext = getApplicationContext();

        et_email = findViewById(R.id.et_email_lost);
        et_id = findViewById(R.id.et_id_lost);
        et_phone = findViewById(R.id.et_phone_lost);
        et_confirm = findViewById(R.id.et_confirm_lost);



        et_email.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }
            }
        });
        et_id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }
            }
        });

        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }
            }
        });

        et_confirm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }
            }
        });

        findViewById(R.id.btn_email_lost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findMemID();
            }
        });

        findViewById(R.id.btn_request_certNumber_lost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                findMemPW();
            }
        });

        findViewById(R.id.btn_confirm_lost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(certNum.isEmpty()){
                    Toast.makeText(mContext, "핸드폰 인증요청을 진행해주세요.", Toast.LENGTH_SHORT).show();
                }else if(certNum.equals(et_confirm.getText().toString())){
                    compCelChk = true;
                    Toast.makeText(mContext, "핸드폰 인증이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "인증번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        findViewById(R.id.btn_getTempPW_lost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!compCelChk){
                    Toast.makeText(mContext, "핸드폰 인증을 진행해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                sendMail();
            }
        });
    }

    private void findMemID(){

        String Email = et_email.getText().toString();


        if(et_email.getText().toString().isEmpty()){
            Toast.makeText(mContext, "이메일을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(Email).matches())
        {
            Toast.makeText(getApplicationContext(),"잘못된 E-mail 주소입니다.",Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "/Member/findMemID.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    String MemID = obj.optString("MemID", "");
                    if(MemID.isEmpty()){
                        Toast.makeText(mContext, "해당하는 이메일로 가입된 회원이 없습니다.", Toast.LENGTH_LONG).show();
                    }else{
                        Toast.makeText(mContext, "회원님의 아이디는 '" + MemID + "' 입니다.", Toast.LENGTH_LONG).show();
                    }

                }
                catch(JSONException e){
                    Toast.makeText(mContext, "해당하는 이메일로 가입된 회원이 없습니다.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("sEmail", et_email.getText().toString());
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }

    private void findMemPW(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "/Member/findMemPW.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, "요청번호를 확인해주세요.", Toast.LENGTH_LONG).show();
                        certNum = obj.getString("certNum");
                    }else{
                        Toast.makeText(mContext, "해당하는 정보의 회원이 조회되지 않습니다.", Toast.LENGTH_LONG).show();
                    }

                }
                catch(JSONException e){
                    Toast.makeText(mContext, "해당하는 정보의 회원이 조회되지 않습니다.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("MemID", et_id.getText().toString());
                params.put("CelPhone", et_phone.getText().toString());
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }

    private void sendMail(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getBidDataUri() + "sendFindMail.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, "이메일에서 임시 비밀번호를 확인해주세요.", Toast.LENGTH_LONG).show();
                    }

                }
                catch(JSONException e){
                    Toast.makeText(mContext, "오류가 발생했습니다.", Toast.LENGTH_LONG).show();
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("MemID", et_id.getText().toString());
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }


}
