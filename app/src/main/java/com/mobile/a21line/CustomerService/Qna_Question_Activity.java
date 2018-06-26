package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.a21line.R;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Qna_Question_Activity extends AppCompatActivity {

    Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cs_qna_question_activity);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("문의하기");
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

        mContext = getApplicationContext();


    }
}
