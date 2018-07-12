package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Mas_List_Popup extends AppCompatActivity {

    Context mContext;
    ListView lv_mas;
    Mas_LVAdapter adapter;
    ArrayList<String> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cs_mas_bidtype_popup);

        mContext = getApplicationContext();

        lv_mas = findViewById(R.id.lv_mas_select);

        arrayList = new ArrayList<>();
        arrayList.add("조달우수제품");
        arrayList.add("성능인증제품");
        arrayList.add("신제품인증");
        arrayList.add("신기술인증");
        arrayList.add("환경인증");
        arrayList.add("K 마크");
        arrayList.add("KS 마크");
        arrayList.add("녹색기술인증");
        arrayList.add("우수재활용인증(GR)");
        arrayList.add("GS(Good Software) 시험인증");
        arrayList.add("고효율 에너지 기자재 인증");
        arrayList.add("다수공급자(mas) 등록");
        arrayList.add("Q 마크");
        arrayList.add("조달청 신청제품 목록화");
        arrayList.add("경쟁입찰참가자격 등록");
        arrayList.add("특허 실용신안등록");
        adapter = new Mas_LVAdapter(mContext,arrayList);
        lv_mas.setAdapter(adapter);




    }


}
