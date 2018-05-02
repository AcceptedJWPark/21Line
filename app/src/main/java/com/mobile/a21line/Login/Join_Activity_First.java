package com.mobile.a21line.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.mobile.a21line.R;


public class Join_Activity_First extends AppCompatActivity {

    Context mContext;
    Button btn_next;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.join_activity_first);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("회원가입(1/2)");

        mContext = getApplicationContext();
        btn_next = findViewById(R.id.btn_next_joinfirst);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Join_Activity_Second.class);
                startActivity(intent);
            }
        });

    }

}
