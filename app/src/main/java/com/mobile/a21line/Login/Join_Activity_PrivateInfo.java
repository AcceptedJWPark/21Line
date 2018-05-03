package com.mobile.a21line.Login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.a21line.R;


public class Join_Activity_PrivateInfo extends AppCompatActivity {

    Context mContext;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.join_privateinfo);

        mContext = getApplicationContext();

        String txt1 = Join_PrivateInfoTxt.privateInfo1;
        String txt2 = Join_PrivateInfoTxt.privateInfo2;
        ((TextView)findViewById(R.id.privateInfo1)).setText(txt1);
        ((TextView)findViewById(R.id.privateInfo2)).setText(txt2);

        ((Button)findViewById(R.id.btn_next_joinfirst)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Join_Activity_First.class);
                startActivity(intent);
            }
        });


    }

}
