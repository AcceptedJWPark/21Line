package com.mobile.a21line.Login;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.a21line.R;


public class Join_PrivateInfoDetail2_Activity extends AppCompatActivity {

    TextView tv_privateinfo2;
    String privateInfo2;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.join_privateinfo_detail2);

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("개인정보 수집 및 이용에 관한 사항");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        privateInfo2 = Join_PrivateInfoTxt.privateInfo2;

        tv_privateinfo2 = findViewById(R.id.privateInfo_detail2);
        tv_privateinfo2.setText(privateInfo2);


    }


}
