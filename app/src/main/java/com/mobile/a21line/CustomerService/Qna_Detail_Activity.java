package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.a21line.R;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Qna_Detail_Activity extends AppCompatActivity {

    Context mContext;

    TextView tv_question_qna_detail;
    TextView tv_answer_qna_detail;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cs_qna_detail_activity);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mContext = getApplicationContext();
        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        mContext = getApplicationContext();

        ((Button)findViewById(R.id.btn_cancel_qna_detail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_question_qna_detail = findViewById(R.id.tv_question_qna_detail);
        tv_answer_qna_detail = findViewById(R.id.tv_answer_qna_detail);
        tv_question_qna_detail.setText("현재 입찰 공고분은 투찰 가능한 것만 표시되고 있는데\n" +
                " 기간이 지난 공고건도 찾아 볼수 이도록 할수 있는 방법을 강구해 주시기 바랍니다\n" +
                " 감사합니다\n");
        tv_answer_qna_detail.setText("▒▒ (주)장광엔지니어링 님이 쓰신 글 ▒▒\n" +
                "현재 입찰 공고분은 투찰 가능한 것만 표시되고 있는데\n" +
                " 기간이 지난 공고건도 찾아 볼수 이도록 할수 있는 방법을 강구해 주시기 바랍니다\n" +
                " 감사합니다\n" +
                "\n" +
                "\n" +
                "▒▒ 답변 내용입니다. ▒▒\n" +
                "\n" +
                "안녕하세요 (주) 장광엔지니어링 님.\n" +
                "\n" +
                "\n" +
                " [입찰공고] 는 현재 투찰 가능한 공고들만 리스트에 제공하고 있고\n" +
                "[전체공고] 에서 입찰공고및 지난 공고까지 포함해서 확인하실 수 있습니다.\n" +
                "\n" +
                "\n" +
                "\n" +
                "맞춤정보에서 맞춤입찰 클릭후, 하단의\n" +
                "\n" +
                "[맞춤전체/ 맞춤입찰/ 맞춤낙찰/ 스케줄러] 4개의 조건 탭에서 \n" +
                "\n" +
                " 첫번째 [맞춤전체] 탭 클릭하시면 투찰가능공고와 지난공고까지 확인하실 수 있습니다.\n" +
                "\n" +
                "\n" +
                "감사합니다.\n");
    }
}
