package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.content.Intent;
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

public class Mas_Activity extends AppCompatActivity {

    Context mContext;
    TextView tv_mas_add;
    int selectedCode = 0;
    String[] certNames = {"신기술(NET,NEP)", "조달우수제품", "녹색기술인증", "GS (국산우수 S/W) 마크", "고효율 에너지 기자재", "KS 마크", "다수공급자계약 (mas)", "이노비즈", "메인비즈", "벤처기업", "조달청 신청제품 목록화", "경쟁입찰참가자격 등록", "직접 생산 증명서", "기술 평가 등급"};
    String[] certCodes = {"NewTech", "G2B", "Green", "GS", "Energy", "KSMark", "MAS", "Innobiz", "Mainbiz", "Ventrue", "G2BList", "Comp", "Manufacturing", "TCB"};
    String CertType = "";

    EditText etcInfo, comName, sType, sPart, manName, celPhone, phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cs_mas_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("사업인증 컨설팅");
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

        tv_mas_add = findViewById(R.id.tv_mas_add);
        tv_mas_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Mas_List_Popup.class);
                intent.putExtra("selectedCode", selectedCode);
                startActivityForResult(intent, 0);
            }
        });

        etcInfo = findViewById(R.id.et_mas_etcInfo);
        comName = findViewById(R.id.et_mas_comName);
        sType = findViewById(R.id.et_mas_sType);
        sPart = findViewById(R.id.et_mas_sPart);
        manName = findViewById(R.id.et_mas_manName);
        celPhone = findViewById(R.id.et_mas_celPhone);
        phone = findViewById(R.id.et_mas_phone);

        ((Button)findViewById(R.id.btn_requestCert_mas)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requestCert();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent intent){
        super.onActivityResult(requestCode, resultCode, intent);

        if(resultCode == RESULT_OK){
            switch(requestCode){
                case 0:
                    selectedCode = intent.getIntExtra("selectedCode", 0);
                    Log.d("selectedCode", selectedCode + "");
                    String certList = "";
                    for(int i = 0; i < certNames.length; i++){
                        if((selectedCode & (int)Math.pow(2, i)) > 0){
                            certList += "," + certNames[i];
                        }
                    }
                    if(selectedCode > 0) {
                        certList = certList.substring(1);
                        tv_mas_add.setText(certList);
                    }else{
                        tv_mas_add.setText("");
                        tv_mas_add.setHint("클릭해서 추가");
                    }

                    break;
            }
        }
    }

    private void requestCert(){
        if(selectedCode == 0 && etcInfo.getText().toString().isEmpty()){
            Toast.makeText(mContext, "인증 유형 또는 기타 사항을 적어주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(comName.getText().toString().isEmpty()){
            Toast.makeText(mContext, "업체명을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(sType.getText().toString().isEmpty()){
            Toast.makeText(mContext, "업종을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(sPart.getText().toString().isEmpty()){
            Toast.makeText(mContext, "품목명을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(manName.getText().toString().isEmpty()){
            Toast.makeText(mContext, "담당자명을 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(celPhone.getText().toString().isEmpty()){
            Toast.makeText(mContext, "핸드폰 번호를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        if(phone.getText().toString().isEmpty()){
            Toast.makeText(mContext, "전화를 입력해주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, "http://new2.21line.co.kr/ajax/mas/requestMas.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("isSuccess").equals("true")){
                        Toast.makeText(mContext, "인증신청이 완료되었습니다. 담당자가 전화드리도록 하겠습니다.", Toast.LENGTH_SHORT).show();
                        finish();
                    }else{
                        Toast.makeText(mContext, "인증신청이 실패하였습니다. 담당자에게 문의하여 주시기 바랍니다.", Toast.LENGTH_SHORT).show();
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
                makeCertType();
                params.put("ComName", comName.getText().toString());
                params.put("EtcInfo", etcInfo.getText().toString());
                params.put("sType", sType.getText().toString());
                params.put("sPart", sPart.getText().toString());
                params.put("ManName", manName.getText().toString());
                params.put("CelPhone", celPhone.getText().toString());
                params.put("Phone", phone.getText().toString());
                params.put("CertType", CertType);

                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    private void makeCertType(){
        for(int i = 0; i < certCodes.length; i++){
            if((selectedCode & (int)Math.pow(2, i)) > 0){
                CertType += certCodes[i] + ",";
            }
        }

        if(etcInfo.getText().toString().isEmpty()){
            CertType = CertType.substring(0, CertType.length() - 1);
        }else{
            CertType += "Etc";
        }
    }
}
