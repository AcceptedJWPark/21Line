package com.mobile.a21line.Search;

import android.content.Context;
import android.content.Intent;
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

    RelativeLayout rl_selectAll;
    ImageView iv_selectAll;
    boolean isAllSelected = false;

    int bidType;

    Context mContext;
    int[] bidTypeCode = {0x1, 0x2, 0x4, 0x8, 0x10, 0x20, 0x40, 0x80, 0x100, 0x200, 0x400, 0x800, 0x1000, 0x4000, 0x8000, 0x10000, 0x20000, 0x40000, 0x80000};
    //{ "정정공고", "긴급공고", "결과발표", "계약공고", "전자입찰", "취소공고", "재공고", "견적입찰", "수의계약", "일반", "공동도급", "현장설명참조", "역경매", "재입찰", "지명입찰", "제조", "시담", "여성", "유찰공고"};



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.search_bidtype_popup);

        mContext = getApplicationContext();

        bidType = getIntent().getIntExtra("BidType", 0);

        btn_dialog = findViewById(R.id.btn_bidtype_search);
        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                getBidType();
                intent.putExtra("BidType", bidType);
                setResult(RESULT_OK, intent);
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

        iv_selectAll = findViewById(R.id.iv_total_bidtype_search);

        rl_selectAll = findViewById(R.id.rl_total_bidtype_search);
        rl_selectAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isAllSelected){
                    setAllChecked(false);
                    isAllSelected = false;
                    iv_selectAll.setImageResource(R.drawable.icon_chechbox_unchecked);
                }else{
                    setAllChecked(true);
                    isAllSelected = true;
                    iv_selectAll.setImageResource(R.drawable.icon_chechbox_checked);
                }
            }
        });

        initSelected();

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

    private boolean isAllChecked(){
        for(int i = 0; i < checked.length; i++){
            if(!checked[i]){
                return false;
            }
        }
        return true;
    }

    private void setAllChecked(boolean selected){
        for(int i = 0; i < checked.length; i++){
            checked[i] = selected;
            if(selected){
                iv_bidtype[i].setImageResource(R.drawable.icon_chechbox_checked);
            }else{
                iv_bidtype[i].setImageResource(R.drawable.icon_chechbox_unchecked);
            }
        }
    }

    private void getBidType(){
        bidType = 0;
        for(int i = 0; i < checked.length; i++){
            if(checked[i]){
                bidType |= bidTypeCode[i];
            }
        }
    }

    private void initSelected(){

        for(int i = 0; i < checked.length; i++){
            int temp = bidType;
            temp = temp & bidTypeCode[i];
            if(temp > 0){
                checked[i] = true;
                iv_bidtype[i].setImageResource(R.drawable.icon_chechbox_checked);
            }else{
                checked[i] = false;
                iv_bidtype[i].setImageResource(R.drawable.icon_chechbox_unchecked);
            }
            final int finalI = i;

            rl_bidtype[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(!checked[finalI]) {
                        iv_bidtype[finalI].setImageResource(R.drawable.icon_chechbox_checked);
                        checked[finalI] = true;
                        if(isAllChecked()){
                            iv_selectAll.setImageResource(R.drawable.icon_chechbox_checked);
                            isAllSelected = true;
                        }
                    }
                    else
                    {
                        iv_bidtype[finalI].setImageResource(R.drawable.icon_chechbox_unchecked);
                        checked[finalI] = false;
                        iv_selectAll.setImageResource(R.drawable.icon_chechbox_unchecked);
                        isAllSelected = false;
                    }
                }
            });
        }

        if(isAllChecked()){
            isAllSelected = true;
            iv_selectAll.setImageResource(R.drawable.icon_chechbox_checked);
        }
    }

}

