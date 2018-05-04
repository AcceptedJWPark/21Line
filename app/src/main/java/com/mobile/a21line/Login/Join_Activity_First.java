package com.mobile.a21line.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.widget.EditText;
import android.view.View;
import android.widget.Button;
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
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Join_Activity_First extends AppCompatActivity {

    Context mContext;
    Button btn_next;
    Button btn_checkCel;
    Button btn_compChkCel;
    EditText et_celPhoneNum;
    EditText et_certNum;
    boolean compCelChk = false;
    String certNum = "";


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.join_activity_first);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("회원가입(1/2)");

        mContext = getApplicationContext();

        et_celPhoneNum = findViewById(R.id.et_celPhoneNum_joinfirst);
        et_certNum = findViewById(R.id.et_certNum_joinfirst);

        et_celPhoneNum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                compCelChk = false;
                certNum = "";
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        btn_next = findViewById(R.id.btn_next_joinfirst);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                if(compCelChk) {
                    Intent intent = new Intent(mContext, Join_Activity_Second.class);
                    startActivity(intent);
//                }else{
//                    Toast.makeText(mContext, "핸드폰 인증을 진행해주세요.", Toast.LENGTH_SHORT).show();
//                }
            }
        });

        btn_checkCel = findViewById(R.id.btn_checkCel_joinfirst);
        btn_checkCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(et_celPhoneNum.getText().toString().isEmpty()){
                    Toast.makeText(mContext, "핸드폰를 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else if(!isValidCellPhoneNumber(et_celPhoneNum.getText().toString())){
                    Toast.makeText(mContext, "핸드폰 번호를 정확히 입력해주세요.", Toast.LENGTH_SHORT).show();
                }else {
                    requestCheckCelPhone();
                    Toast.makeText(mContext, "핸드폰에서 인증번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btn_compChkCel = findViewById(R.id.btn_compChkCel_joinfirst);
        btn_compChkCel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(certNum.isEmpty()){
                    Toast.makeText(mContext, "핸드폰 인증요청을 진행해주세요.", Toast.LENGTH_SHORT).show();
                }else if(certNum.equals(et_certNum.getText().toString())){
                    compCelChk = true;
                    Toast.makeText(mContext, "핸드폰 인증이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(mContext, "인증번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        et_celPhoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }
            }
        });
        et_certNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }
            }
        });

    }

    public static boolean isValidCellPhoneNumber(String cellphoneNumber) {

        boolean returnValue = false;

        String regex = "^\\s*(010|011|012|013|014|015|016|017|018|019)(-|\\)|\\s)*(\\d{3,4})(-|\\s)*(\\d{4})\\s*$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(cellphoneNumber);

        if (m.matches()) {
            returnValue = true;
        }
        return returnValue;
    }

    public void requestCheckCelPhone(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Member/sendJoinSMS.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    certNum = obj.getString("certNum");
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("sRecieveNum", et_celPhoneNum.getText().toString().replace("-", ""));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

}
