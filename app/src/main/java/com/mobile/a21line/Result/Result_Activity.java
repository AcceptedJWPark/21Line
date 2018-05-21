package com.mobile.a21line.Result;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Bid.Bid_LVAdapter;
import com.mobile.a21line.Bid.Bid_Listitem;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Result_Activity extends AppCompatActivity {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;

    String GCode;
    int startNum = 0;

    ListView lv_bidlist;
    ArrayList<Result_Listitem> arrayList;
    Result_LVAdapter adapter;

    RelativeLayout rl_sortingbox_open;
    LinearLayout ll_sortingbox;


    ImageView iv_periodIcon1;
    ImageView iv_periodIcon2;
    ImageView iv_periodIcon3;
    ImageView iv_periodIcon4;
    ImageView iv_periodIcon5;
    ImageView iv_sortingIcon1;
    ImageView iv_sortingIcon2;
    ImageView iv_sortingIcon3;
    ImageView iv_sortingIcon4;

    Button btn_searchbox_save;

    RelativeLayout rl_searchbox_period1;
    RelativeLayout rl_searchbox_period2;
    RelativeLayout rl_searchbox_period3;
    RelativeLayout rl_searchbox_period4;
    RelativeLayout rl_searchbox_period5;

    RelativeLayout rl_searchbox_sorting1;
    RelativeLayout rl_searchbox_sorting2;
    RelativeLayout rl_searchbox_sorting3;
    RelativeLayout rl_searchbox_sorting4;

    EditText et_SDate, et_EDate;

    String SortType = "RegDTime";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_activity);

        mContext = getApplicationContext();

        GCode = getIntent().getStringExtra("GCode");

        et_SDate = (EditText)findViewById(R.id.et_SDate_result);
        et_EDate = (EditText)findViewById(R.id.et_EDate_result);

        et_SDate.setText(getMonthAgoDate(1));
        et_EDate.setText(getMonthAgoDate(0));

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("맞춤낙찰 1.");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Right)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

        View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Result_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Result_Activity.this, mClicklistener);

        lv_bidlist = findViewById(R.id.lv_bidlist_result);
        arrayList = new ArrayList<Result_Listitem>();
        arrayList.add(new Result_Listitem("[조달청 물품 20180514959-00 호-01]","주택건설공사 감리자(건축) 모집 공고 - 진도 라포엠 아파트 신축공사","전라남도 진도군","(주)무심종합건축사사무소","66,270,000,000,000원",false,false,null));
        arrayList.add(new Result_Listitem("[조달청 물품 20180514959-00 호-02]","주요 정보통신 기반시설 취약점 분석,평가 컨설팅 사업_중앙조달","인천광역시 서구 검단출장소","(주)진흥중공업","12,310,000원",false,false,null));
        arrayList.add(new Result_Listitem("[조달청 용역 20180515299-00 호]","당진시립도서관(중앙,합덕,송악) 3개소 도서 구입(2018-2차)","한국산업기술대학교","주식회사 다우컨설턴트 ",null,false,true,"유찰 되었습니다. 유찰 사유: 0개업체 참여로 유찰 "));
        arrayList.add(new Result_Listitem("[국방부 물품 수의내자 EGCE018-1-0530A 호]","2018 복지기관 문화예술교육 지원사업 아동복지시설 교육기자재","청라29블럭 호반베르디움","유한회사 풍림종합건설 ","132,270,000,000원",false,false,null));
        arrayList.add(new Result_Listitem("[KG2B전자입찰_용역_26338-01 호]","용암천(동계지구) 지방하천정비사업 실시설계 용역","충청남도 당진시 시립도서관","주식회사 억셉티드 컴퍼니 이앤씨","150,000,000원",false,false,null));
        arrayList.add(new Result_Listitem("[S2B 용역 201805113798238 호]","충현서원외 1개소 상수도 노후관 개량공사(긴급)","항공안전기술원","유한회사 지암건설 ",null,false,true,"유찰 되었습니다. 유찰 사유: 단독 응찰"));
        arrayList.add(new Result_Listitem("[조달청 물품 20180520747-00 호]","치과(보철물) 기공물 단가계약 입찰-01","경상북도교육청 경상북도울진교육지원청","비케이건설산업 주식회사 ",null,false,true,"유찰되었습니다. 유찰사유: 투찰금액과 견적서 금액 불일치로 2인이상 유효한 입찰이 성립되지 않음 "));
        adapter = new Result_LVAdapter(mContext,arrayList);
        lv_bidlist.setAdapter(adapter);

        rl_sortingbox_open = findViewById(R.id.rl_searchbox_open_result);
        ll_sortingbox = findViewById(R.id.ll_sortingbox_result);
        rl_sortingbox_open.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ll_sortingbox.getVisibility()==View.GONE) {
                    ll_sortingbox.setVisibility(View.VISIBLE);
                    ((ImageView)findViewById(R.id.iv_searchbox_open_result)).setImageResource(R.drawable.icon_arrowup);
                }
                else
                {
                    ll_sortingbox.setVisibility(View.GONE);
                    ((ImageView)findViewById(R.id.iv_searchbox_open_result)).setImageResource(R.drawable.icon_arrowdown);
                }
            }
        });

        btn_searchbox_save = findViewById(R.id.btn_searchbox_save_result);
        btn_searchbox_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((ImageView)findViewById(R.id.iv_searchbox_open_result)).setImageResource(R.drawable.icon_arrowdown);
                Toast.makeText(mContext,"검색조건이 적용되었습니다.",Toast.LENGTH_SHORT).show();
                ll_sortingbox.setVisibility(View.GONE);
            }
        });

        iv_periodIcon1 = findViewById(R.id.iv_searchbox_period1_result);
        iv_periodIcon2 = findViewById(R.id.iv_searchbox_period2_result);
        iv_periodIcon3 = findViewById(R.id.iv_searchbox_period3_result);
        iv_periodIcon4 = findViewById(R.id.iv_searchbox_period4_result);
        iv_periodIcon5 = findViewById(R.id.iv_searchbox_period5_result);
        iv_sortingIcon1 = findViewById(R.id.iv_searchbox_sorting1_result);
        iv_sortingIcon2 = findViewById(R.id.iv_searchbox_sorting2_result);
        iv_sortingIcon3 = findViewById(R.id.iv_searchbox_sorting3_result);
        iv_sortingIcon4 = findViewById(R.id.iv_searchbox_sorting4_result);

        rl_searchbox_period1 = findViewById(R.id.rl_searchbox_period1_result);
        rl_searchbox_period2 = findViewById(R.id.rl_searchbox_period2_result);
        rl_searchbox_period3 = findViewById(R.id.rl_searchbox_period3_result);
        rl_searchbox_period4 = findViewById(R.id.rl_searchbox_period4_result);
        rl_searchbox_period5 = findViewById(R.id.rl_searchbox_period5_result);

        rl_searchbox_sorting1 = findViewById(R.id.rl_searchbox_sorting1_result);
        rl_searchbox_sorting2 = findViewById(R.id.rl_searchbox_sorting2_result);
        rl_searchbox_sorting3 = findViewById(R.id.rl_searchbox_sorting3_result);
        rl_searchbox_sorting4 = findViewById(R.id.rl_searchbox_sorting4_result);

        rl_searchbox_period1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDate(0));
                et_EDate.setText(getMonthAgoDate(0));
                periodlistClicked(iv_periodIcon1,iv_periodIcon2,iv_periodIcon3,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(1, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon2,iv_periodIcon1,iv_periodIcon3,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(3, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon3,iv_periodIcon2,iv_periodIcon1,iv_periodIcon4,iv_periodIcon5);
            }
        });

        rl_searchbox_period4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(6, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon4,iv_periodIcon2,iv_periodIcon3,iv_periodIcon1,iv_periodIcon5);
            }
        });

        rl_searchbox_period5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_SDate.setText(getMonthAgoDateFromSelectedDate(12, et_EDate.getText().toString()));
                periodlistClicked(iv_periodIcon5,iv_periodIcon2,iv_periodIcon3,iv_periodIcon4,iv_periodIcon1);
            }
        });

        rl_searchbox_sorting1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "RegDTime";
                sortinglistClicked(iv_sortingIcon1,iv_sortingIcon2,iv_sortingIcon3,iv_sortingIcon4);
            }
        });
        rl_searchbox_sorting2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "OpenDTime";
                sortinglistClicked(iv_sortingIcon2,iv_sortingIcon1,iv_sortingIcon3,iv_sortingIcon4);
            }
        });
        rl_searchbox_sorting3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "FinishDTime";
                sortinglistClicked(iv_sortingIcon3,iv_sortingIcon2,iv_sortingIcon1,iv_sortingIcon4);
            }
        });

        rl_searchbox_sorting4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SortType = "FinishDTime";
                sortinglistClicked(iv_sortingIcon4,iv_sortingIcon3,iv_sortingIcon2,iv_sortingIcon1);
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

    public void sortinglistClicked(View view1,View view2,View view3,View view4)
    {
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.GONE);
        view3.setVisibility(View.GONE);
        view4.setVisibility(View.GONE);
    }


    private String getMonthAgoDate(int month){
        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");
        Calendar cal = Calendar.getInstance(time);
        cal.add(Calendar.MONTH, -month);

        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(time);
        String strDate = sdf.format(date);
        return strDate;
    }

    private String getMonthAgoDateFromSelectedDate(int month, String selectedDate){
        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");
        Calendar cal = Calendar.getInstance(time);
        cal.set(Integer.parseInt(selectedDate.substring(0, 4)), Integer.parseInt(selectedDate.substring(5, 7)), Integer.parseInt(selectedDate.substring(8, 10)));
        cal.add(Calendar.MONTH, - (month + 1));

        Date date = cal.getTime();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(time);
        String strDate = sdf.format(date);
        return strDate;
    }

    private String parseDateTimeToDate(String dateTime){
        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");

        Date date = new Date(Long.parseLong(dateTime));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setTimeZone(time);
        return sdf.format(date);
    }

    private String toNumFormat(String data){
        DecimalFormat df = new DecimalFormat("#,###");
        return df.format(Integer.parseInt(data));
    }

}
