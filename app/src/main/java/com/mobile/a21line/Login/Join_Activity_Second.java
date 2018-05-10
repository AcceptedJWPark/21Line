package com.mobile.a21line.Login;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Home.Home_Activity;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.Search_Address_Activity;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Join_Activity_Second extends AppCompatActivity {

    private final int ADDRESS_REQUEST_CODE = 100001;

    Button btn_next;

    Button btn_companyuser;
    Button btn_privateuser;
    Button btn_orderuser;
    Button btn_checkMemID;
    boolean compCheckMemID = false;

    EditText et_memID;
    EditText et_memPW;
    EditText et_memPWComp;
    EditText et_name;
    EditText et_preName;
    EditText et_manName;
    EditText et_bizNo1;
    EditText et_bizNo2;
    EditText et_bizNo3;
    EditText et_identNum;
    EditText et_phone;
    EditText et_phoneNum;
    EditText et_email1;
    EditText et_email2;
    EditText et_homepage;
    EditText et_fax;
    EditText et_sType;
    EditText et_sPart;
    TextView tv_address;
    EditText et_address;

    Context mContext;
    int userType;


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
                ((ScrollView)findViewById(R.id.sv_joinSecond)).fullScroll(ScrollView.FOCUS_UP);
            }
        });
        btn_privateuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                privateUserClicked();
                ((ScrollView)findViewById(R.id.sv_joinSecond)).fullScroll(ScrollView.FOCUS_UP);
            }
        });
        btn_orderuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                orderUserClicked();
                ((ScrollView)findViewById(R.id.sv_joinSecond)).fullScroll(ScrollView.FOCUS_UP);
            }
        });

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
        et_name = findViewById(R.id.et_comName_joinInfo);
        et_preName = findViewById(R.id.et_ceoName_joinInfo);
        et_bizNo1 = findViewById(R.id.et_bizNo1_joinInfo);
        et_bizNo2 = findViewById(R.id.et_bizNo2_joinInfo);
        et_bizNo3 = findViewById(R.id.et_bizNo3_joinInfo);
        tv_address = findViewById(R.id.tv_addr1_joinInfo);
        et_address = findViewById(R.id.et_addr2_joinInfo);
        et_sType = findViewById(R.id.et_txtSType_joinInfo);
        et_sPart = findViewById(R.id.et_txtSPart_joinInfo);
        et_identNum = findViewById(R.id.et_birth_joinInfo);
        et_manName = findViewById(R.id.et_manName_joinInfo);
        et_phone = findViewById(R.id.et_phone_joinInfo);
        et_phoneNum = findViewById(R.id.et_celPhoneNum_joinInfo);
        et_fax = findViewById(R.id.et_faxNum_joinInfo);
        et_homepage = findViewById(R.id.et_homepage_joinInfo);
        et_email1 = findViewById(R.id.et_email1_joinsecond);
        et_email2 = findViewById(R.id.et_email2_joinsecond);

        et_memID.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_memPW.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_memPWComp.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_name.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        ((EditText)findViewById(R.id.et_name_joinInfo)).setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {{if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}}
        });
        et_preName.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_bizNo1.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_bizNo2.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_bizNo3.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_address.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_sType.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_sPart.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_identNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_manName.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_phoneNum.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_fax.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_homepage.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_email1.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_email2.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
    }




    public void companyUserClicked(){
        userType = 1;


        ((ScrollView)findViewById(R.id.sv_joinSecond)).fullScroll(ScrollView.FOCUS_UP);
        View view = this.getCurrentFocus();

        if(view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        et_name = findViewById(R.id.et_comName_joinInfo);
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

        ((TextView)findViewById(R.id.ll_title_joinInfo)).setText("기업정보입력");



    }

    public void privateUserClicked(){
        userType = 2;

        ((ScrollView)findViewById(R.id.sv_joinSecond)).fullScroll(ScrollView.FOCUS_UP);

        View view = this.getCurrentFocus();

        if(view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        et_name = findViewById(R.id.et_name_joinInfo);
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

        ((TextView)findViewById(R.id.ll_title_joinInfo)).setText("개인정보입력");
    }

    public void orderUserClicked(){
        userType = 4;
        et_name = findViewById(R.id.et_comName_joinInfo);

        ((ScrollView)findViewById(R.id.sv_joinSecond)).fullScroll(ScrollView.FOCUS_UP);
        View view = this.getCurrentFocus();

        if(view != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) mContext.getSystemService(Activity.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
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

        ((TextView)findViewById(R.id.ll_title_joinInfo)).setText("발주처정보입력");
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK) {
            if(requestCode == ADDRESS_REQUEST_CODE){
                ((TextView)findViewById(R.id.tv_addr1_joinInfo)).setText(data.getStringExtra("address"));
            }
        }
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
                        Intent intent = new Intent(mContext, Home_Activity.class);
                        startActivity(intent);
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
                params.put("sName", et_name.getText().toString());
                params.put("sPreName", et_preName.getText().toString());
                params.put("sManName", et_manName.getText().toString());
                params.put("sBizNo", et_bizNo1.getText().toString() + "-" + et_bizNo2.getText().toString() + "-" + et_bizNo3.getText().toString());
                params.put("sIdentNum", et_identNum.getText().toString());
                params.put("sPhone", et_phone.getText().toString());
                params.put("sPhoneNum", et_phoneNum.getText().toString());
                params.put("sEmail", et_email1.getText().toString() + "@" + et_email2.getText().toString());
                params.put("sHomePage", et_homepage.getText().toString());
                params.put("sFax", et_fax.getText().toString());
                params.put("sType", et_sType.getText().toString());
                params.put("sPart", et_sPart.getText().toString());
                params.put("sAddr", tv_address.getText().toString() + " " + et_address.getText().toString());
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

    public boolean isValidInput(){

        if(userType==2)
        {
            et_name = findViewById(R.id.et_name_joinInfo);
            if(et_memID.length()==0||et_memPW.length()==0||et_memPWComp.length()==0||et_name.length()==0||et_identNum.length()==0||et_phone.length()==0||et_email1.length()==0||et_email2.length()==0)
            {
                Toast.makeText(mContext, "*표시는 필수 입력 사항입니다.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else if(userType==1||userType==4)
        {
            et_name = findViewById(R.id.et_comName_joinInfo);
            if(et_memID.length()==0||et_memPW.length()==0||et_memPWComp.length()==0||et_name.length()==0||et_bizNo1.length()==0||et_bizNo2.length()==0||et_bizNo3.length()==0||et_phone.length()==0||et_email1.length()==0||et_email2.length()==0)
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

        if(et_name.getText().toString().isEmpty()){
            if(userType == 2){
                Toast.makeText(mContext, "성명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return false;
            }else{
                Toast.makeText(mContext, "회사명을 입력해주세요.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if(userType != 2){
            if(et_bizNo1.getText().toString().length() != 3 || et_bizNo2.getText().toString().length() != 2 || et_bizNo3.getText().toString().length() != 5){
                Toast.makeText(mContext, "사업자등록번호를 확인해주세요.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }else{
            if(!isValidDate()){
                Toast.makeText(mContext, "생년월일을 확인해주세요.", Toast.LENGTH_SHORT).show();
                return false;
            }
            else if(et_identNum.length()!=8)
            {
                Toast.makeText(mContext, "생년월일은 8자리로 입력해주세요.", Toast.LENGTH_SHORT).show();
                return false;
            }
        }

        if(et_phone.getText().toString().isEmpty()){
            Toast.makeText(mContext, "전화번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if(et_email1.getText().toString().isEmpty() || et_email2.getText().toString().isEmpty()){
            Toast.makeText(mContext, "이메일을 확인해주세요.", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    public boolean isValidDate(){
        if(et_identNum.getText().toString().length() < 8 || et_identNum.getText().toString().isEmpty()){
            return false;
        }
        int year = Integer.parseInt(et_identNum.getText().toString().substring(0, 4));
        int month = Integer.parseInt(et_identNum.getText().toString().substring(4, 6));
        int day = Integer.parseInt(et_identNum.getText().toString().substring(6, 8));


        if (month < 1 || month > 12) { // check month range
            return false;
        }

        if (day < 1 || day > 31) {
            return false;
        }

        if ((month==4 || month==6 || month==9 || month==11) && day==31) {
            return false;
        }

        if (month == 2) { // check for february 29th
            boolean isleap = (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0));
            if (day>29 || (day==29 && !isleap)) {
                return false;
            }
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
