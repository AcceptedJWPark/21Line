package com.mobile.a21line.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.a21line.R;


public class Join_Activity_PrivateInfo extends AppCompatActivity {

    Context mContext;
    CheckBox chb_privateInfo;
    CheckBox chb_clause;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.join_privateinfo);

        mContext = getApplicationContext();

        String txt1 = Join_PrivateInfoTxt.privateInfo1;
        String txt2 = Join_PrivateInfoTxt.privateInfo2;
        ((TextView)findViewById(R.id.privateInfo1)).setText(txt1);
        ((TextView)findViewById(R.id.privateInfo2)).setText(txt2);

        chb_clause = findViewById(R.id.chb_clause);
        chb_privateInfo = findViewById(R.id.chb_privateInfo);

        ((Button)findViewById(R.id.btn_next_joinfirst)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!chb_clause.isChecked()){
                    Toast.makeText(mContext, "가입 약관에 동의해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(!chb_privateInfo.isChecked()){
                    Toast.makeText(mContext, "개인정보 활용에 동의해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent(mContext, Join_Activity_First.class);
                startActivity(intent);
            }
        });


        ((TextView)findViewById(R.id.tv_privateInfoDetail1)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Join_PrivateInfoDetail1_Activity.class);
                startActivity(intent);
            }
        });

        ((TextView)findViewById(R.id.tv_privateInfoDetail2)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Join_PrivateInfoDetail2_Activity.class);
                startActivity(intent);
            }
        });



    }

}
