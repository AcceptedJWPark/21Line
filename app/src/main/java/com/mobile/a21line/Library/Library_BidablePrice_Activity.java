package com.mobile.a21line.Library;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Library_BidablePrice_Activity extends AppCompatActivity {

    Context mContext;
    EditText et_price1;
    EditText et_price2;

    String basicprice;

    ImageView iv_businesstype1;
    ImageView iv_businesstype2;
    ImageView iv_businesstype3;

    LinearLayout ll_businesstype1;
    LinearLayout ll_businesstype2;
    LinearLayout ll_businesstype3;

    boolean businesstype1;
    boolean businesstype2;
    boolean businesstype3;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.library_bidableprice);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("실적별 입찰 참여 가능 금액");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        et_price1 = findViewById(R.id.et_price1_bidableprice);
        et_price2 = findViewById(R.id.et_price2_bidableprice);
        basicprice="";
        et_price1.setText(basicprice);
        et_price2.setText(basicprice);

        businesstype1 = false;
        businesstype2 = false;
        businesstype3 = false;

        ll_businesstype1 = findViewById(R.id.ll_businesstype1_bidableprice);
        ll_businesstype2 = findViewById(R.id.ll_businesstype2_bidableprice);
        ll_businesstype3 = findViewById(R.id.ll_businesstype3_bidableprice);
        iv_businesstype1 = findViewById(R.id.iv_businesstype1_bidableprice);
        iv_businesstype2 = findViewById(R.id.iv_businesstype2_bidableprice);
        iv_businesstype3 = findViewById(R.id.iv_businesstype3_bidableprice);

        ll_businesstype1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_businesstype1.setImageResource(R.drawable.icon_chechbox_checked);
                iv_businesstype2.setImageResource(R.drawable.icon_chechbox_unchecked);
                iv_businesstype3.setImageResource(R.drawable.icon_chechbox_unchecked);
                businesstype1 = true;
                businesstype2 = false;
                businesstype3 = false;
            }
        });

        ll_businesstype2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_businesstype2.setImageResource(R.drawable.icon_chechbox_checked);
                iv_businesstype1.setImageResource(R.drawable.icon_chechbox_unchecked);
                iv_businesstype3.setImageResource(R.drawable.icon_chechbox_unchecked);
                businesstype1 = false;
                businesstype2 = true;
                businesstype3 = false;
            }
        });

        ll_businesstype3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iv_businesstype3.setImageResource(R.drawable.icon_chechbox_checked);
                iv_businesstype2.setImageResource(R.drawable.icon_chechbox_unchecked);
                iv_businesstype1.setImageResource(R.drawable.icon_chechbox_unchecked);
                businesstype1 = false;
                businesstype2 = false;
                businesstype3 = true;
            }
        });

        et_price1.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});
        et_price2.setOnFocusChangeListener(new View.OnFocusChangeListener() {@Override public void onFocusChange(View v, boolean hasFocus) {if(!hasFocus){SaveSharedPreference.hideKeyboard(v,mContext);}}});


            et_price1.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                    if(charSequence.toString().isEmpty()){
                        et_price1.setText("0");
                        Selection.setSelection(et_price1.getText(), 1);
                        return;
                    }else {
                        if (!basicprice.equals(charSequence.toString())) {
                            basicprice = toNumFormat(charSequence.toString().replace(",", ""));
                            et_price1.setText(basicprice);
                            Selection.setSelection(et_price1.getText(), basicprice.length());
                        }
                    }
                }

                @Override
                public void afterTextChanged(Editable editable) {

                }
            });

        et_price2.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty()){
                    et_price2.setText("0");
                    Selection.setSelection(et_price2.getText(), 1);
                    return;
                }else {
                    if (!basicprice.equals(charSequence.toString())) {
                        basicprice = toNumFormat(charSequence.toString().replace(",", ""));
                        et_price2.setText(basicprice);
                        Selection.setSelection(et_price2.getText(), basicprice.length());

                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

    }

    private String toNumFormat(String data){
        DecimalFormat df = new DecimalFormat("#,###");
        BigDecimal bd = new BigDecimal(data);
        return df.format(bd);
    }


}
