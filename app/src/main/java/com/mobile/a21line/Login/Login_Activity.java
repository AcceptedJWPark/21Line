package com.mobile.a21line.Login;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.mobile.a21line.Home.Home_Activity;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Login_Activity extends AppCompatActivity {

    EditText et_id;
    EditText et_pw;
    Button btn_login;
    Context mContext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.login_activity);

        mContext = getApplicationContext();

        et_id = findViewById(R.id.et_id_Login);
        et_pw = findViewById(R.id.et_pw_Login);

        et_id.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }
            }
        });
        et_pw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }
            }
        });


        btn_login = findViewById(R.id.btn_Login);
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goLogin();
            }
        });

        ((TextView)findViewById(R.id.tv_clickToJoin_Login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Join_Activity_PrivateInfo.class);
                startActivity(intent);
            }
        });


        ((TextView)findViewById(R.id.tv_clickToLost_Login)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Join_Lost_Activity.class);
                startActivity(intent);
            }
        });

    }

    public void goLogin(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/checkLoginInfo.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        SaveSharedPreference.setPrefUserId(mContext, et_id.getText().toString());
                        Intent intent = new Intent(mContext, Home_Activity.class);
                        startActivity(intent);
                        saveFCMToken();
                        finish();
                    }else{
                        Toast.makeText(mContext, "아이디 또는 비밀번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
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
                params.put("userID", et_id.getText().toString());
                params.put("userPW", et_pw.getText().toString());
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

    private void saveFCMToken(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/saveFCMToken.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Log.d("saveToken", "토큰 저장 성공");
                    }else{
                        Log.d("saveToken", "토큰 저장 실패");
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
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("Token", SaveSharedPreference.getFcmToken(mContext));

                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

}
