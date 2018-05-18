package com.mobile.a21line.Bid;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.a21line.R;

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


    ListView lv_bidlist;
    ArrayList<Bid_Listitem> arrayList;
    Bid_LVAdapter adapter;

    RelativeLayout rl_sortingbox_open;
    LinearLayout ll_sortingbox;

    View.OnClickListener periodListener;
    View.OnClickListener sortingListener;

    ImageView iv_periodIcon1;
    ImageView iv_periodIcon2;
    ImageView iv_periodIcon3;
    ImageView iv_periodIcon4;
    ImageView iv_periodIcon5;
    ImageView iv_sortingIcon1;
    ImageView iv_sortingIcon2;
    ImageView iv_sortingIcon3;

    Button btn_searchbox_save;

    RelativeLayout rl_searchbox_period1_bid;
    RelativeLayout rl_searchbox_period2_bid;
    RelativeLayout rl_searchbox_period3_bid;
    RelativeLayout rl_searchbox_period4_bid;
    RelativeLayout rl_searchbox_period5_bid;

    RelativeLayout rl_searchbox_sorting1_bid;
    RelativeLayout rl_searchbox_sorting2_bid;
    RelativeLayout rl_searchbox_sorting3_bid;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.bid_activity);

        mContext = getApplicationContext();
        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("맞춤입찰 1.");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

        View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Bid_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Bid_Activity.this, mClicklistener);

        lv_bidlist = findViewById(R.id.lv_bidlist_bid);
        arrayList = new ArrayList<Bid_Listitem>();
        arrayList.add(new Bid_Listitem("[20180519935-00]","당진시립도서관(중앙,합덕,송악) 3개소 도서 구입(2018-2차)","충청남도 당진시 시립도서관","2018-05-16","66,270,000원",true));
        arrayList.add(new Bid_Listitem("[20180510099-01]","2018학년도 청계초 통학차량 임차용역 수의계약 안내 공고","전라남도무안교육청 청계초등학교","2018-05-16","297,320,000원",false));
        arrayList.add(new Bid_Listitem("[20180411212-00]","서생 온곡 소규모 하수처리장 설치사업 건설사업관리용역(감독권한대행)","울산광역시 울주군","2018-05-16","12,210,000원",false));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",true));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",true));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",true));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",true));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",true));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",true));
        arrayList.add(new Bid_Listitem("[20180218754-00]","율곡초 등 2교(두암중) 홈통 교체공사 소액수의계약 견적서 제출 안내 공고","광주광역시교육청 광주광역시동부교육지원청","2018-05-16","1,253,650,000원",true));
        adapter = new Bid_LVAdapter(mContext,arrayList);
        lv_bidlist.setAdapter(adapter);

        rl_sortingbox_open = findViewById(R.id.rl_searchbox_open);
        ll_sortingbox = findViewById(R.id.ll_sortingbox_bid);
        rl_sortingbox_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_sortingbox.getVisibility()==View.GONE) {
                    ll_sortingbox.setVisibility(View.VISIBLE);
                    ((ImageView)findViewById(R.id.iv_searchbox_open)).setImageResource(R.drawable.icon_arrowup);
                }
                else
                {
                    ll_sortingbox.setVisibility(View.GONE);
                    ((ImageView)findViewById(R.id.iv_searchbox_open)).setImageResource(R.drawable.icon_arrowdown);
                }
            }
        });

        btn_searchbox_save = findViewById(R.id.btn_searchbox_save_bid);
        btn_searchbox_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"검색조건이 적용되었습니다.",Toast.LENGTH_SHORT).show();
                ll_sortingbox.setVisibility(View.GONE);
            }
        });

        iv_periodIcon1 = findViewById(R.id.iv_searchbox_period1);
        iv_periodIcon2 = findViewById(R.id.iv_searchbox_period2);
        iv_periodIcon3 = findViewById(R.id.iv_searchbox_period3);
        iv_periodIcon4 = findViewById(R.id.iv_searchbox_period4);
        iv_periodIcon5 = findViewById(R.id.iv_searchbox_period5);
        iv_sortingIcon1 = findViewById(R.id.iv_searchbox_sorting1);
        iv_sortingIcon2 = findViewById(R.id.iv_searchbox_sorting2);
        iv_sortingIcon3 = findViewById(R.id.iv_searchbox_sorting3);

        rl_searchbox_period1_bid = findViewById(R.id.rl_searchbox_period1);
        rl_searchbox_period2_bid = findViewById(R.id.rl_searchbox_period2);
        rl_searchbox_period3_bid = findViewById(R.id.rl_searchbox_period3);
        rl_searchbox_period4_bid = findViewById(R.id.rl_searchbox_period4);
        rl_searchbox_period5_bid = findViewById(R.id.rl_searchbox_period5);

        rl_searchbox_sorting1_bid = findViewById(R.id.rl_searchbox_sorting1);
        rl_searchbox_sorting2_bid = findViewById(R.id.rl_searchbox_sorting2);
        rl_searchbox_sorting3_bid = findViewById(R.id.rl_searchbox_sorting3);

        rl_searchbox_period1_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                periodlistClicked(iv_periodIcon1,iv_periodIcon2,iv_periodIcon3,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period2_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                periodlistClicked(iv_periodIcon2,iv_periodIcon1,iv_periodIcon3,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period3_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                periodlistClicked(iv_periodIcon3,iv_periodIcon2,iv_periodIcon1,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period4_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                periodlistClicked(iv_periodIcon4,iv_periodIcon2,iv_periodIcon3,iv_periodIcon1,iv_periodIcon5);
            }
        });

        rl_searchbox_period5_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                periodlistClicked(iv_periodIcon5,iv_periodIcon2,iv_periodIcon3,iv_periodIcon4,iv_periodIcon1);
            }
        });

        rl_searchbox_sorting1_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortinglistClicked(iv_sortingIcon1,iv_sortingIcon2,iv_sortingIcon3);
            }
        });
        rl_searchbox_sorting2_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortinglistClicked(iv_sortingIcon2,iv_sortingIcon1,iv_sortingIcon3);
            }
        });
        rl_searchbox_sorting3_bid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sortinglistClicked(iv_sortingIcon3,iv_sortingIcon2,iv_sortingIcon1);
            }
        });

    }


    public void periodlistClicked(View view1,View view2,View view3,View view4,View view5)
    {
     view1.setVisibility(View.VISIBLE);
     view2.setVisibility(View.GONE);
     view3.setVisibility(View.GONE);
     view4.setVisibility(View.GONE);
     view5.setVisibility(View.GONE);
    }

    public void sortinglistClicked(View view1,View view2,View view3)
    {
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
    }



}
