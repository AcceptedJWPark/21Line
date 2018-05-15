package com.mobile.a21line.Bid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.mobile.a21line.Home.Home_Activity;
import com.mobile.a21line.R;
import com.mobile.a21line.Setbid.Setbid_Activity;
import com.mobile.a21line.Setbid.Setbid_Dialog;
import com.mobile.a21line.Setbid.Setbid_LVAdapter;

import java.util.ArrayList;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Bid_Activity extends AppCompatActivity {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;

    Spinner spn_sorting;

    TextView tv_period;

    Bid_Period_Dialog dialog;

    private String[] sortingType = {"입력일순","입찰/개찰일순", "입찰마감일순"};

    private int[] yearType = {2017,2018};
    private int[] monthType = {1,2,3,4,5,6,7,8,9 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bid_activity);

        mContext = getApplicationContext();
        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("맞춤입찰 1.");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Right)).setVisibility(View.VISIBLE);

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Bid_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Bid_Activity.this,mClicklistener);



        spn_sorting = findViewById(R.id.spn_sorting_bid);
        ArrayAdapter<CharSequence> adapter1 = new ArrayAdapter<CharSequence>(this,R.layout.setbid_pricespinner,sortingType);
        adapter1.setDropDownViewResource(R.layout.setbid_pricespinner);
        spn_sorting.setAdapter(adapter1);


        tv_period = findViewById(R.id.tv_period_bid);
        tv_period.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        ArrayList<String> arrayList1 = new ArrayList<>();
                        arrayList1.add("오늘");
                        arrayList1.add("1 개월");
                        arrayList1.add("3 개월");
                        arrayList1.add("6 개월");
                        arrayList1.add("12 개월");
                        dialog = new Bid_Period_Dialog(Bid_Activity.this,arrayList1);
                        dialog.show();
                    }
                });

    }


}
