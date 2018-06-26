package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

    Button btn_question;
    Button btn_answer;
    LinearLayout ll_question;
    LinearLayout ll_answer;

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
        btn_question = findViewById(R.id.btn_question_qna);
        btn_answer = findViewById(R.id.btn_answer_qna);
        ll_question = findViewById(R.id.ll_question_qna);
        ll_answer = findViewById(R.id.ll_answer_qna);



        arrayList = new ArrayList<>();
        arrayList.add(new Qna_Listitem("1","세종시 아파트 공사장서 대형 화재 발생…소방당국 진화 총력","18/06/28",true));
        arrayList.add(new Qna_Listitem("2","세종시 화재, 엄청난 '검은 연기'…인명 피해는?","18/06/28",true));
        arrayList.add(new Qna_Listitem("3","스캇 반 슬라이크 두산과 계약 … “인지도 최고의 용병 왔다” 들썩","18/06/28",false));
        arrayList.add(new Qna_Listitem("4","두산, LA 다저스 출신 스캇 반 슬라이크 연봉 32만달러에 영입…파레디스 대체 ","18/06/28",true));
        adapter = new Qna_LVAdapter(Qna_Activity.this,arrayList);
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
                Toast.makeText(mContext,"질문이 정상적으로 등록되었습니다.",Toast.LENGTH_SHORT).show();
            }
        });

        btn_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedQuestion();
            }
        });

        btn_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedAnswer();
            }
        });


    }

    private void clickedQuestion()
    {
        btn_question.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_question.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_question.setTypeface(null, Typeface.BOLD);
        btn_question.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_answer.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_answer.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_answer.setTypeface(null, Typeface.NORMAL);
        btn_answer.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        ll_question.setVisibility(View.VISIBLE);
        ll_answer.setVisibility(View.GONE);
    }

    private void clickedAnswer()
    {
        btn_answer.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_answer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_answer.setTypeface(null, Typeface.BOLD);
        btn_answer.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_question.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_question.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_question.setTypeface(null, Typeface.NORMAL);
        btn_question.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        ll_answer.setVisibility(View.VISIBLE);
        ll_question.setVisibility(View.GONE);
    }



}
