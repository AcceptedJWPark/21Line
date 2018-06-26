package com.mobile.a21line.CustomerService;

import android.content.Context;
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

public class Notice_Activity extends AppCompatActivity {

    Context mContext;

    TextView[] tv_series;

    int clickNext = 0;
    int clickNumber = 1;

    ListView lv_notice;
    Notice_LVAdapter adapter;
    ArrayList<Notice_Listitem> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cs_notice_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("공지사항");
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


        lv_notice = findViewById(R.id.lv_notice_cs);

        tv_series = new TextView[12];

        tv_series[0] = findViewById(R.id.tv_series_pre);
        tv_series[1] = findViewById(R.id.tv_series1);
        tv_series[2] = findViewById(R.id.tv_series2);
        tv_series[3] = findViewById(R.id.tv_series3);
        tv_series[4] = findViewById(R.id.tv_series4);
        tv_series[5] = findViewById(R.id.tv_series5);
        tv_series[6] = findViewById(R.id.tv_series6);
        tv_series[7] = findViewById(R.id.tv_series7);
        tv_series[8] = findViewById(R.id.tv_series8);
        tv_series[9] = findViewById(R.id.tv_series9);
        tv_series[10] = findViewById(R.id.tv_series10);
        tv_series[11] = findViewById(R.id.tv_series_next);


        arrayList = new ArrayList<>();
        adapter = new Notice_LVAdapter(Notice_Activity.this,arrayList);

        lv_notice.setAdapter(adapter);


        clickNumber(clickNumber);

        tv_series[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickPre();
            }
        });
        tv_series[11].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickNext();
            }
        });

        for(int i=1; i<11; i++)
        {
            final int finalI = i;
            tv_series[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickNumber(finalI);
                }
            });
        }
    }

    public void setArrayList(int clickNumber, int clickNext)
    {
        arrayList.clear();
        for(int i=0;i<10; i++)
        {
            arrayList.add(new Notice_Listitem(String.valueOf(((clickNumber-1)*10)+clickNext*100+1+i),"[한국수력원자력] 시스템 보안 점검 작업으로 인한 시스템 서비스 한시적 중단 알림","18/06/24"));
        }

        adapter.notifyDataSetChanged();

    }

    public void clickNumber(final int i) {

        for (int a = 1; a < 11; a++)
            {
                tv_series[a].setTextColor(getResources().getColor(R.color.textColor_soft));
            }
                tv_series[i].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                setArrayList(i, clickNext);
            }


    public void clickNext()
    {
        clickNext++;
                for(int i=1; i<11; i++)
                {
                    tv_series[i].setText(String.valueOf(i+clickNext*10));
                    tv_series[0].setTextColor(getResources().getColor(R.color.colorPrimaryDark));
                    clickNumber = 1;
                    clickNumber(clickNumber);
                }
    }

    public void clickPre()
    {
                if(clickNext==0) {
                    tv_series[0].setTextColor(getResources().getColor(R.color.textColor_soft));
                    return;
                }
                else {
                    clickNext--;
                    for (int i = 1; i < 11; i++) {
                        tv_series[i].setText(String.valueOf(i + clickNext * 10));
                        clickNumber = 1;
                        clickNumber(clickNumber);
                    }
                }
    }

}
