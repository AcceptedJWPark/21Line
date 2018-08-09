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
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Notice_Detail_Activity extends AppCompatActivity {

    Context mContext;

    TextView tv_notice_detail;

    int fileCount;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cs_notice_detail_activity);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mContext = getApplicationContext();
        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        mContext = getApplicationContext();

        ((TextView)findViewById(R.id.tv_noticeDetail_title)).setText(getIntent().getStringExtra("Title"));
        ((TextView)findViewById(R.id.tv_noticeDetail_date)).setText(getIntent().getStringExtra("Date"));
        ((TextView)findViewById(R.id.tv_noticeDetail_content)).setText(getIntent().getStringExtra("Content"));

        ((Button)findViewById(R.id.btn_cancel_notice_detail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        fileCount=1;

        if(fileCount<=1)
        {
            ((LinearLayout)findViewById(R.id.ll_file2_notice_detail)).setVisibility(View.GONE);
            ((LinearLayout)findViewById(R.id.ll_file3_notice_detail)).setVisibility(View.GONE);
            ((LinearLayout)findViewById(R.id.ll_file4_notice_detail)).setVisibility(View.GONE);
        }
        else if(fileCount<=2)
        {
            ((LinearLayout)findViewById(R.id.ll_file2_notice_detail)).setVisibility(View.VISIBLE);
            ((LinearLayout)findViewById(R.id.ll_file3_notice_detail)).setVisibility(View.GONE);
            ((LinearLayout)findViewById(R.id.ll_file4_notice_detail)).setVisibility(View.GONE);
        }
        else if(fileCount<=3)
        {
            ((LinearLayout)findViewById(R.id.ll_file2_notice_detail)).setVisibility(View.VISIBLE);
            ((LinearLayout)findViewById(R.id.ll_file3_notice_detail)).setVisibility(View.VISIBLE);
            ((LinearLayout)findViewById(R.id.ll_file4_notice_detail)).setVisibility(View.GONE);
        }
        else
            {
            ((LinearLayout)findViewById(R.id.ll_file2_notice_detail)).setVisibility(View.VISIBLE);
            ((LinearLayout)findViewById(R.id.ll_file3_notice_detail)).setVisibility(View.VISIBLE);
            ((LinearLayout)findViewById(R.id.ll_file4_notice_detail)).setVisibility(View.VISIBLE);
        }

    }
}
