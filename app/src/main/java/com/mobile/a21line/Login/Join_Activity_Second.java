package com.mobile.a21line.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
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
    Button btn_checkMemID;
    boolean compCheckMemID = false;

    EditText et_memID;
    EditText et_memPW;
    EditText et_memPWComp;
    EditText et_comname;
    EditText et_name;
    EditText et_bizNo1;
    EditText et_bizNo2;
    EditText et_bizNo3;
    EditText et_email1;
    EditText et_email2;
    String CelPhone;

    Context mContext;
    int userType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.join_activity_second);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("회원가입(2/2)");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);

        mContext = getApplicationContext();
        CelPhone = getIntent().getStringExtra("Phone");

        btn_next = findViewById(R.id.btn_next_joinsecond);

        btn_checkMemID = findViewById(R.id.btn_checkMemID_joinsecond);
        btn_checkMemID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!checkId(et_memID)||et_memID.getText().toString().length() < 6||et_memID.getText().toString().length()>12)
                {
                    Toast.makeText(mContext, "ID는 영문+숫자 6~12자리로 입력해주세요.", Toast.LENGTH_SHORT).show();
                }
                else {
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
                goJoin();
            }
        });

        et_memPW = findViewById(R.id.et_password_joinsecond);
        et_memPWComp = findViewById(R.id.et_passwordComp_joinsecond);
        et_comname = findViewById(R.id.et_comName_joinInfo);
        et_bizNo1 = findViewById(R.id.et_bizNo1_joinInfo);
        et_bizNo2 = findViewById(R.id.et_bizNo2_joinInfo);
        et_bizNo3 = findViewById(R.id.et_bizNo3_joinInfo);
        et_email1 = findViewById(R.id.et_email1_joinsecond);
        et_email2 = findViewById(R.id.et_email2_joinsecond);
        et_name = findViewById(R.id.et_manName_joinInfo);

        et_memID.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_memPW.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_memPWComp.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_comname.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_bizNo1.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_bizNo2.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_bizNo3.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_email1.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_email2.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
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


    public void goJoin(){

        if(!isValidInput()){
            return;
        }

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Member/joinMember.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, "가입이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        SaveSharedPreference.setPrefUserId(mContext, et_memID.getText().toString());
                        Intent intent = new Intent(mContext, Home_Activity.class);
                        startActivity(intent);
                        finish();
                    }else{
                        Toast.makeText(mContext, "가입이 실패했습니다. 고객센터에 문의해주세요.", Toast.LENGTH_SHORT).show();
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
                params.put("sMemPW", et_memPW.getText().toString());
                params.put("iMemKind", String.valueOf(userType));
                params.put("sName", et_comname.getText().toString());
                params.put("sBizNo", et_bizNo1.getText().toString() + "-" + et_bizNo2.getText().toString() + "-" + et_bizNo3.getText().toString());
                params.put("sEmail", et_email1.getText().toString() + "@" + et_email2.getText().toString());
                params.put("sManName", et_name.getText().toString());
                params.put("sPhone", CelPhone);
                params.put("sPhoneNum", CelPhone);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }



    public boolean isValidInput(){

        if(userType==2)
        {
            if(et_memID.length()==0||et_memPW.length()==0||et_memPWComp.length()==0|| et_comname.length()==0||et_email1.length()==0||et_email2.length()==0)
            {
                Toast.makeText(mContext, "*표시는 필수 입력 사항입니다.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else if(userType==1||userType==4)
        {
            et_comname = findViewById(R.id.et_comName_joinInfo);
            if(et_memID.length()==0||et_memPW.length()==0||et_memPWComp.length()==0|| et_comname.length()==0||et_bizNo1.length()==0||et_bizNo2.length()==0||et_bizNo3.length()==0||et_email1.length()==0||et_email2.length()==0)
            {
                Toast.makeText(mContext, "*표시는 필수 입력 사항입니다.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }


        if(!compCheckMemID){
            Toast.makeText(mContext, "아이디 중복체크를 해주시기 바랍니다.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(et_memID.getText().toString().isEmpty()){
            Toast.makeText(mContext, "아이디를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!checkId(et_memID)||et_memID.getText().toString().length() < 6||et_memID.getText().toString().length()>12)
        {
            Toast.makeText(mContext, "아이디는 영문+숫자 6~12자리로 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(et_memPW.getText().toString().length() < 6||et_memPW.getText().toString().length()>12){
            Toast.makeText(mContext, "비밀번호는 6~12자리로 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(!et_memPW.getText().toString().equals(et_memPWComp.getText().toString())){
            Toast.makeText(mContext, "비밀번호가 일치하지 않습니다.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(et_comname.getText().toString().isEmpty()){
                {
                Toast.makeText(mContext, "회사명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if(userType != 2){
            if(et_bizNo1.getText().toString().length() != 3 || et_bizNo2.getText().toString().length() != 2 || et_bizNo3.getText().toString().length() != 5){
                Toast.makeText(mContext, "사업자등록번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if(et_name.getText().toString().isEmpty()){
            Toast.makeText(mContext, "담당자명을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        String Email = et_email1.getText().toString() + "@" + et_email2.getText().toString();

        if(et_email1.getText().toString().isEmpty() || et_email2.getText().toString().isEmpty()){
            Toast.makeText(mContext, "이메일을 확인해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }


    public boolean checkId(EditText edt)
    {
        int engNum=0;
        int numNum=0;
        int totalNum=0;

        for(int i =0; i<edt.getText().length(); i++)
        {
            if(Character.isLetter(edt.getText().charAt(i)))
            {
                engNum++;
            }
            if(Character.isDigit(edt.getText().charAt(i)))
            {
                numNum++;
            }
        }

        if (engNum != 0)
        {
            totalNum++;
        }
        if(numNum != 0)
        {
            totalNum++;
        }
        if (totalNum < 2)
        {
            return false;
        }
        return  true;
    }
}
