package com.mobile.a21line.Library;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.a21line.Library.BidablePriceFunction.D2B;
import com.mobile.a21line.Library.BidablePriceFunction.EKR;
import com.mobile.a21line.Library.BidablePriceFunction.EX;
import com.mobile.a21line.Library.BidablePriceFunction.G2B;
import com.mobile.a21line.Library.BidablePriceFunction.KECO;
import com.mobile.a21line.Library.BidablePriceFunction.KHNP;
import com.mobile.a21line.Library.BidablePriceFunction.KOGAS;
import com.mobile.a21line.Library.BidablePriceFunction.KORAIL;
import com.mobile.a21line.Library.BidablePriceFunction.LH;
import com.mobile.a21line.Library.BidablePriceFunction.MOI;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;

import java.math.BigDecimal;
import java.text.DecimalFormat;

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

    private D2B d2b = new D2B();
    private EKR ekr = new EKR();
    private EX ex = new EX();
    private G2B g2b = new G2B();
    private KECO keco = new KECO();
    private KHNP khnp = new KHNP();
    private KOGAS kogas = new KOGAS();
    private KORAIL korail = new KORAIL();
    private MOI moi = new MOI();
    private LH lh = new LH();

    private int[] arrTVBidablePriceID = {R.id.tv_BidablePrice1, R.id.tv_BidablePrice2, R.id.tv_BidablePrice3,R.id.tv_BidablePrice4,R.id.tv_BidablePrice5, R.id.tv_BidablePrice6, R.id.tv_BidablePrice7, R.id.tv_BidablePrice8, R.id.tv_BidablePrice9, R.id.tv_BidablePrice10, R.id.tv_BidablePrice11, R.id.tv_BidablePrice12};
    private int[] arrTVBidableReasonableDateID = {R.id.tv_Bidable_reasonableDate1, R.id.tv_Bidable_reasonableDate2, R.id.tv_Bidable_reasonableDate3, R.id.tv_Bidable_reasonableDate4, R.id.tv_Bidable_reasonableDate5, R.id.tv_Bidable_reasonableDate6, R.id.tv_Bidable_reasonableDate7, R.id.tv_Bidable_reasonableDate8, R.id.tv_Bidable_reasonableDate9, R.id.tv_Bidable_reasonableDate10, R.id.tv_Bidable_reasonableDate11, R.id.tv_Bidable_reasonableDate12};

    private TextView[] arrTVBidablePrice = new TextView[12];
    private TextView[] arrTVBidableReasonableDate = new TextView[12];

    private Button btn_calculate_BidablePrice;
    private TextView btn_init_BidablePrice;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.library_bidableprice);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("실적별 입찰 참여 가능 금액");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Edit_Right)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_calculate_BidablePrice = findViewById(R.id.btn_calculate_BidablePrice);
        btn_calculate_BidablePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!(businesstype1 || businesstype2 || businesstype3)){
                    Toast.makeText(mContext, "업종을 선택해주세요.", Toast.LENGTH_SHORT).show();
                    return;
                }
                long money1 = 0;
                long money2 = 0;
                if(!et_price1.getText().toString().isEmpty())
                    money1 = Long.parseLong(et_price1.getText().toString().replace(",", ""));
                if(!et_price2.getText().toString().isEmpty())
                    money2 = Long.parseLong(et_price2.getText().toString().replace(",", ""));

                if(businesstype1) {
                    if(money1 > 0 && money2 == 0) {
                        // 0: 조달청 G2B, 1: 행안부 MOI, 2: 한국주택공사 LH, 3: 국방부 D2B, 4: 한국도시가스 KOGAS, 5: 한국수자원 EX, 6: 한국마사회 EX, 7: 한국도로공사 EX, 8: 한국수력원자력 KHNP, 9: 한국농어촌공사 EXR, 10: 한국전력 KECO, 11: 한국철도시설 KORAIL
                        arrTVBidablePrice[0].setText(g2b.getLimit_1(money1));
                        arrTVBidablePrice[3].setText(d2b.getLimit_1(money1));
                        arrTVBidablePrice[2].setText(lh.getLimit_1(money1));
                        arrTVBidablePrice[5].setText(ex.getLimit_1(money1));
                        arrTVBidablePrice[6].setText(ex.getLimit_1(money1));
                        arrTVBidablePrice[4].setText(kogas.getLimit_1(money1));
                        arrTVBidablePrice[9].setText(ekr.getLimit_1(money1));
                        arrTVBidablePrice[10].setText(keco.getLimit_1(money1));
                        arrTVBidablePrice[11].setText(korail.getLimit_1(money1));
                    } else {
                        if(money2 > 0) {
                            arrTVBidablePrice[0].setText(g2b.getLimit_1(money2));
                            arrTVBidablePrice[3].setText(d2b.getLimit_1(money2));
                            arrTVBidablePrice[2].setText(lh.getLimit_1(money2));
                            arrTVBidablePrice[5].setText(ex.getLimit_1(money2));
                            arrTVBidablePrice[6].setText(ex.getLimit_1(money2));
                            arrTVBidablePrice[4].setText(kogas.getLimit_1(money2));
                            arrTVBidablePrice[9].setText(ekr.getLimit_1(money2));
                            arrTVBidablePrice[10].setText(keco.getLimit_1(money2));
                            arrTVBidablePrice[11].setText(korail.getLimit_1(money2));
                        }
                    }

                    if(money1 > 0) {
                        arrTVBidablePrice[7].setText(ex.getLimit_1(money1));
                        arrTVBidablePrice[8].setText(khnp.getLimit_1(money1));
                    }

                    if(money2 >= 1000000000 && money2 < 5000000000l){
                        arrTVBidablePrice[1].setText(moi.getLimit_1(money2));
                    } else {
                        arrTVBidablePrice[1].setText(moi.getLimit_1(money1));
                    }
                    if((money2 >= 1000000000 && money2 <5000000000l) || (money2 == 0 && money1 >= 1000000000 && money1 <5000000000l)){
                        arrTVBidableReasonableDate[1].setText("[최근 5년간]");
                    }else{
                        arrTVBidableReasonableDate[1].setText("[최근 3년간]");
                    }

                }


                if(businesstype2) {
                    if(money1 > 0 && money2 == 0) {
                        arrTVBidablePrice[0].setText(g2b.getLimit_2(money1));
                        arrTVBidablePrice[3].setText(d2b.getLimit_2(money1));
                        arrTVBidablePrice[2].setText(lh.getLimit_2(money1));
                        arrTVBidablePrice[5].setText(ex.getLimit_2(money1));
                        arrTVBidablePrice[6].setText(ex.getLimit_2(money1));
                        arrTVBidablePrice[4].setText(kogas.getLimit_2(money1));
                        arrTVBidablePrice[9].setText(ekr.getLimit_2(money1));
                        arrTVBidablePrice[10].setText(keco.getLimit_2(money1));
                        arrTVBidablePrice[11].setText(korail.getLimit_2(money1));
                    } else {
                        if(money2 > 0) {
                            arrTVBidablePrice[0].setText(g2b.getLimit_2(money2));
                            arrTVBidablePrice[3].setText(d2b.getLimit_2(money2));
                            arrTVBidablePrice[2].setText(lh.getLimit_2(money2));
                            arrTVBidablePrice[5].setText(ex.getLimit_2(money2));
                            arrTVBidablePrice[6].setText(ex.getLimit_2(money2));
                            arrTVBidablePrice[4].setText(kogas.getLimit_2(money2));
                            arrTVBidablePrice[9].setText(ekr.getLimit_2(money2));
                            arrTVBidablePrice[10].setText(keco.getLimit_2(money2));
                            arrTVBidablePrice[11].setText(korail.getLimit_2(money2));
                        }
                    }

                    if(money1 > 0) {
                        arrTVBidablePrice[7].setText(ex.getLimit_2(money1));
                        arrTVBidablePrice[8].setText(khnp.getLimit_2(money1));
                    }

                    if(money2 >= 300000000 && money2 != 0) {
                        arrTVBidablePrice[1].setText(moi.getLimit_2(money2));
                    } else {
                        arrTVBidablePrice[1].setText(moi.getLimit_2(money1));
                    }

                    if(money2 >= 300000000 || (money2 == 0 && money1 >= 300000000)) {
                        arrTVBidableReasonableDate[1].setText("[최근 5년간]");
                    }else{
                        arrTVBidableReasonableDate[1].setText("[최근 3년간]");
                    }
                }


                if(businesstype3) {
                    if(money1 > 0 && money2 == 0) {
                        arrTVBidablePrice[0].setText(g2b.getLimit_3(money1));
                        arrTVBidablePrice[3].setText(d2b.getLimit_3(money1));
                        arrTVBidablePrice[2].setText(lh.getLimit_3(money1));
                        arrTVBidablePrice[5].setText(ex.getLimit_3(money1));
                        arrTVBidablePrice[6].setText(ex.getLimit_3(money1));
                        arrTVBidablePrice[4].setText(kogas.getLimit_3(money1));
                        arrTVBidablePrice[9].setText(ekr.getLimit_3(money1));
                        arrTVBidablePrice[10].setText(keco.getLimit_3(money1));
                        arrTVBidablePrice[11].setText(korail.getLimit_3(money1));
                    } else {
                        if(money2 > 0) {
                            arrTVBidablePrice[0].setText(g2b.getLimit_3(money2));
                            arrTVBidablePrice[3].setText(d2b.getLimit_3(money2));
                            arrTVBidablePrice[2].setText(lh.getLimit_3(money2));
                            arrTVBidablePrice[5].setText(ex.getLimit_3(money2));
                            arrTVBidablePrice[6].setText(ex.getLimit_3(money2));
                            arrTVBidablePrice[4].setText(kogas.getLimit_3(money2));
                            arrTVBidablePrice[9].setText(ekr.getLimit_3(money2));
                            arrTVBidablePrice[10].setText(keco.getLimit_3(money2));
                            arrTVBidablePrice[11].setText(korail.getLimit_3(money2));
                        }
                    }

                    if(money1 > 0) {
                        arrTVBidablePrice[7].setText(ex.getLimit_1(money1));
                        arrTVBidablePrice[8].setText(khnp.getLimit_1(money1));
                    }

                    if(money2 >= 300000000 && money2 != 0) {
                        arrTVBidablePrice[1].setText(moi.getLimit_3(money2));
                    } else {
                        arrTVBidablePrice[1].setText(moi.getLimit_3(money1));
                    }

                    if(money2 >= 300000000 || (money2 == 0 && money1 >= 300000000)) {
                        arrTVBidableReasonableDate[1].setText("[최근 5년간]");
                    }else{
                        arrTVBidableReasonableDate[1].setText("[최근 3년간]");
                    }
                }
                if(money1 > 0 || money2 > 0) {
                    arrTVBidableReasonableDate[0].setText("[최근 5년간]");
                    arrTVBidableReasonableDate[2].setText("[최근 5년간]");
                    arrTVBidableReasonableDate[3].setText("[최근 5년간]");
                    arrTVBidableReasonableDate[4].setText("[최근 5년간]");
                    arrTVBidableReasonableDate[5].setText("[최근 5년간]");
                    arrTVBidableReasonableDate[6].setText("[최근 5년간]");
                    arrTVBidableReasonableDate[7].setText("[최근 5년간]");
                    arrTVBidableReasonableDate[8].setText("[최근 3년간]");
                    arrTVBidableReasonableDate[9].setText("[최근 5년간]");
                    arrTVBidableReasonableDate[10].setText("[최근 5년간]");
                    arrTVBidableReasonableDate[11].setText("[최근 5년간]");
                }
            }
        });

        btn_init_BidablePrice = findViewById(R.id.btn_init_BidablePrice);
        btn_init_BidablePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                businesstype1 = false;
                businesstype2 = false;
                businesstype3 = false;

                et_price1.setText("");
                et_price2.setText("");

                iv_businesstype1.setImageResource(R.drawable.icon_chechbox_unchecked);
                iv_businesstype2.setImageResource(R.drawable.icon_chechbox_unchecked);
                iv_businesstype3.setImageResource(R.drawable.icon_chechbox_unchecked);

                for(int i = 0; i < arrTVBidableReasonableDate.length; i++){
                    arrTVBidableReasonableDate[i].setText("[ ]");
                    arrTVBidablePrice[i].setText("[ ]");
                }
            }
        });

        for(int i = 0; i < arrTVBidablePrice.length; i++){
            arrTVBidablePrice[i] = findViewById(arrTVBidablePriceID[i]);
            arrTVBidableReasonableDate[i] = findViewById(arrTVBidableReasonableDateID[i]);
        }

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
