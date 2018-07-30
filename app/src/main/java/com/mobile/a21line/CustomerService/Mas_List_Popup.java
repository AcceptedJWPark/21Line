package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Mas_List_Popup extends AppCompatActivity {

    Context mContext;

    RelativeLayout[] rl_mas_select;
    ImageView[] iv_mas_select;
    boolean[] isSelect;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cs_mas_bidtype_popup);

        mContext = getApplicationContext();

        rl_mas_select = new RelativeLayout[15];
        iv_mas_select = new ImageView[15];
        isSelect = new boolean[15];

        rl_mas_select[0] = findViewById(R.id.rl_mas_select1);
        rl_mas_select[1] = findViewById(R.id.rl_mas_select2);
        rl_mas_select[2] = findViewById(R.id.rl_mas_select3);
        rl_mas_select[3] = findViewById(R.id.rl_mas_select4);
        rl_mas_select[4] = findViewById(R.id.rl_mas_select5);
        rl_mas_select[5] = findViewById(R.id.rl_mas_select6);
        rl_mas_select[6] = findViewById(R.id.rl_mas_select7);
        rl_mas_select[7] = findViewById(R.id.rl_mas_select8);
        rl_mas_select[8] = findViewById(R.id.rl_mas_select9);
        rl_mas_select[9] = findViewById(R.id.rl_mas_select10);
        rl_mas_select[10] = findViewById(R.id.rl_mas_select11);
        rl_mas_select[11] = findViewById(R.id.rl_mas_select12);
        rl_mas_select[12] = findViewById(R.id.rl_mas_select13);
        rl_mas_select[13] = findViewById(R.id.rl_mas_select14);
        rl_mas_select[14] = findViewById(R.id.rl_mas_select15);

        iv_mas_select[0] = findViewById(R.id.iv_mas_select1);
        iv_mas_select[1] = findViewById(R.id.iv_mas_select2);
        iv_mas_select[2] = findViewById(R.id.iv_mas_select3);
        iv_mas_select[3] = findViewById(R.id.iv_mas_select4);
        iv_mas_select[4] = findViewById(R.id.iv_mas_select5);
        iv_mas_select[5] = findViewById(R.id.iv_mas_select6);
        iv_mas_select[6] = findViewById(R.id.iv_mas_select7);
        iv_mas_select[7] = findViewById(R.id.iv_mas_select8);
        iv_mas_select[8] = findViewById(R.id.iv_mas_select9);
        iv_mas_select[9] = findViewById(R.id.iv_mas_select10);
        iv_mas_select[10] = findViewById(R.id.iv_mas_select11);
        iv_mas_select[11] = findViewById(R.id.iv_mas_select12);
        iv_mas_select[12] = findViewById(R.id.iv_mas_select13);
        iv_mas_select[13] = findViewById(R.id.iv_mas_select14);
        iv_mas_select[14] = findViewById(R.id.iv_mas_select15);
        
        
        for(int i=0; i<15; i++)
        {
            isSelect[i] = false;
            final int finalI = i;
            rl_mas_select[i].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(isSelect[finalI]) {
                        isSelect[finalI] = false;
                        iv_mas_select[finalI].setImageResource(R.drawable.icon_chechbox_unchecked);
                    }
                    else
                    {
                        isSelect[finalI] = true;
                        iv_mas_select[finalI].setImageResource(R.drawable.icon_chechbox_checked);
                    }
                }
            });
        }



        ((Button)findViewById(R.id.btn_bidtype_search)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent result = new Intent();
                int selectedCode = getSelectedCode();
                result.putExtra("selectedCode", selectedCode);
                setResult(RESULT_OK, result);
                finish();
            }
        });

        ((ImageView)findViewById(R.id.iv_cancel_mas)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private int getSelectedCode(){
        int code = 0;
        for(int i = 0; i < isSelect.length; i++){
            if(isSelect[i]){
                code |= (int)Math.pow(2, i);
            }
        }

        return code;
    }


}
