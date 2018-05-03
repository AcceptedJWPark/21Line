package com.mobile.a21line.Login;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;


public class Join_Activity_First extends AppCompatActivity {

    Context mContext;
    Button btn_next;
    EditText et_phone;
    EditText et_accept;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.join_activity_first);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("회원가입(1/2)");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);

        mContext = getApplicationContext();
        btn_next = findViewById(R.id.btn_next_joinfirst);
        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Join_Activity_Second.class);
                startActivity(intent);
            }
        });

        et_phone = findViewById(R.id.et_phone_join1);
        et_accept = findViewById(R.id.et_accept_join1);
        et_phone.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }
            }
        });
        et_accept.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(!hasFocus)
                {
                    SaveSharedPreference.hideKeyboard(v,mContext);
                }
            }
        });

    }

}
