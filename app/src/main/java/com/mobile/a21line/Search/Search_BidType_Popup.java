package com.mobile.a21line.Search;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.mobile.a21line.R;


public class Search_BidType_Popup extends AppCompatActivity {

    private Button btn_dialog;

    RelativeLayout[] rl_bidtype;
    ImageView[] iv_bidtype;
    boolean[] checked;


    Context mContext;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_bidtype_popup);

        mContext = getApplicationContext();


        btn_dialog = findViewById(R.id.btn_bidtype_search);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        rl_bidtype = new RelativeLayout[19];
        rl_bidtype[0] = findViewById(R.id.rl_bidtype1_bidtype_search);
        rl_bidtype[1] = findViewById(R.id.rl_bidtype2_bidtype_search);
        rl_bidtype[2] = findViewById(R.id.rl_bidtype3_bidtype_search);
        rl_bidtype[3] = findViewById(R.id.rl_bidtype4_bidtype_search);
        rl_bidtype[4] = findViewById(R.id.rl_bidtype5_bidtype_search);
        rl_bidtype[5] = findViewById(R.id.rl_bidtype6_bidtype_search);
        rl_bidtype[6] = findViewById(R.id.rl_bidtype7_bidtype_search);
        rl_bidtype[7] = findViewById(R.id.rl_bidtype8_bidtype_search);
        rl_bidtype[8] = findViewById(R.id.rl_bidtype9_bidtype_search);
        rl_bidtype[9] = findViewById(R.id.rl_bidtype10_bidtype_search);
        rl_bidtype[10] = findViewById(R.id.rl_bidtype11_bidtype_search);
        rl_bidtype[11] = findViewById(R.id.rl_bidtype12_bidtype_search);
        rl_bidtype[12] = findViewById(R.id.rl_bidtype13_bidtype_search);
        rl_bidtype[13] = findViewById(R.id.rl_bidtype14_bidtype_search);
        rl_bidtype[14] = findViewById(R.id.rl_bidtype15_bidtype_search);
        rl_bidtype[15] = findViewById(R.id.rl_bidtype16_bidtype_search);
        rl_bidtype[16] = findViewById(R.id.rl_bidtype17_bidtype_search);
        rl_bidtype[17] = findViewById(R.id.rl_bidtype18_bidtype_search);
        rl_bidtype[18] = findViewById(R.id.rl_bidtype19_bidtype_search);

        iv_bidtype = new ImageView[19];
        iv_bidtype[0] = findViewById(R.id.iv_bidtype1_bidtype_search);
        iv_bidtype[1] = findViewById(R.id.iv_bidtype2_bidtype_search);
        iv_bidtype[2] = findViewById(R.id.iv_bidtype3_bidtype_search);
        iv_bidtype[3] = findViewById(R.id.iv_bidtype4_bidtype_search);
        iv_bidtype[4] = findViewById(R.id.iv_bidtype5_bidtype_search);
        iv_bidtype[5] = findViewById(R.id.iv_bidtype6_bidtype_search);
        iv_bidtype[6] = findViewById(R.id.iv_bidtype7_bidtype_search);
        iv_bidtype[7] = findViewById(R.id.iv_bidtype8_bidtype_search);
        iv_bidtype[8] = findViewById(R.id.iv_bidtype9_bidtype_search);
        iv_bidtype[9] = findViewById(R.id.iv_bidtype10_bidtype_search);
        iv_bidtype[10] = findViewById(R.id.iv_bidtype11_bidtype_search);
        iv_bidtype[11] = findViewById(R.id.iv_bidtype12_bidtype_search);
        iv_bidtype[12] = findViewById(R.id.iv_bidtype13_bidtype_search);
        iv_bidtype[13] = findViewById(R.id.iv_bidtype14_bidtype_search);
        iv_bidtype[14] = findViewById(R.id.iv_bidtype15_bidtype_search);
        iv_bidtype[15] = findViewById(R.id.iv_bidtype16_bidtype_search);
        iv_bidtype[16] = findViewById(R.id.iv_bidtype17_bidtype_search);
        iv_bidtype[17] = findViewById(R.id.iv_bidtype18_bidtype_search);
        iv_bidtype[18] = findViewById(R.id.iv_bidtype19_bidtype_search);

        checked = new boolean[19];


        for(int i=0; i<19; i++)
        {
            final int finalI = i;
            checked[finalI] = false;
            rl_bidtype[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!checked[finalI]) {
                        iv_bidtype[finalI].setImageResource(R.drawable.icon_chechbox_unchecked);
                        checked[finalI] = true;
                    }
                    else
                    {
                        iv_bidtype[finalI].setImageResource(R.drawable.icon_chechbox_checked);
                        checked[finalI] = false;
                    }
                }
            });
        }


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }



}

