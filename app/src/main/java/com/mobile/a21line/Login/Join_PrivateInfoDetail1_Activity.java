package com.mobile.a21line.Login;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;


public class Join_PrivateInfoDetail1_Activity extends AppCompatActivity {

    TextView tv_privateinfo1;
    String privateInfo1;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.join_privateinfo_detail1);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("회원가입약관");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        privateInfo1 = Join_PrivateInfoTxt.privateInfo1;

        tv_privateinfo1 = findViewById(R.id.privateInfo_detail1);
        tv_privateinfo1.setText(privateInfo1);

    }

}
