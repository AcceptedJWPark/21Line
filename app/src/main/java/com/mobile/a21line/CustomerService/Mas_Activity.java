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
    int selectedCode;
    String[] certNames = {"신기술(NET,NEP)", "조달우수제품", "녹색기술인증", "GS (국산우수 S/W) 마크", "고효율 에너지 기자재", "KS 마크", "다수공급자계약 (mas)", "이노비즈", "메인비즈", "벤처기업", "조달청 신청제품 목록화", "경쟁입찰참가자격 등록", "직접 생산 증명서", "기술 평가 등급"};

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
                startActivityForResult(intent, 0);
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

                    certList = certList.substring(1);
                    tv_mas_add.setText(certList);
                    break;
            }
        }
    }


}
