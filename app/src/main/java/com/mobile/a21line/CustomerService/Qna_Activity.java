package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Qna_Activity extends AppCompatActivity {

    Context mContext;


    ListView lv_qna;
    Qna_LVAdapter adapter;
    ArrayList<Qna_Listitem> arrayList;

    Button btn_qna_cs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cs_qna_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("질문/답변");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        lv_qna = findViewById(R.id.lv_qna_cs);



        arrayList = new ArrayList<>();
        adapter = new Qna_LVAdapter(Qna_Activity.this,arrayList);

        arrayList.add(new Qna_Listitem("1","입찰 공고 연람 기간연장요청","18/06/25",true));
        arrayList.add(new Qna_Listitem("2","3일 검토기간 서비스 문의","18/06/25",false));
        arrayList.add(new Qna_Listitem("3","국방전자조달 사업 검색 건","18/06/25",true));
        arrayList.add(new Qna_Listitem("4","범용공인인증서 문의","18/06/25",false));
        arrayList.add(new Qna_Listitem("5","입찰정보를 전체 내역 엑셀 저장 방법","18/06/25",true));

        lv_qna.setAdapter(adapter);

        if(adapter.getCount() == 0)
        {
            lv_qna.setVisibility(View.GONE);
            ((TextView)findViewById(R.id.tv_qna_cs)).setVisibility(View.VISIBLE);
        }
        else
        {
            lv_qna.setVisibility(View.VISIBLE);
            ((TextView)findViewById(R.id.tv_qna_cs)).setVisibility(View.GONE);
        }

        btn_qna_cs =findViewById(R.id.btn_qna_cs);
        btn_qna_cs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Qna_Question_Activity.class);
                startActivity(intent);
            }
        });

    }


}
