package com.mobile.a21line.Home;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Typeface;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.mobile.a21line.Bid.Bid_Activity;
import com.mobile.a21line.Bid.Bid_Detail_Activity;
import com.mobile.a21line.CustomerService.Mas_Activity;
import com.mobile.a21line.CustomerService.Notice_Detail_Activity;
import com.mobile.a21line.CustomerService.Notice_Activity;
import com.mobile.a21line.CustomerService.Qna_Activity;
import com.mobile.a21line.Login.Login_Activity;
import com.mobile.a21line.MyBid.MyBid_Activity;
import com.mobile.a21line.R;
import com.mobile.a21line.Result.Result_Activity;
import com.mobile.a21line.Result.Result_Detail_Activity;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.Search.Search_Bid_Activity;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;


public class Home_Activity extends AppCompatActivity {

    private TimeZone time= TimeZone.getTimeZone("Asia/Seoul");

    private final long FINISH_INTERVAL_TIME = 2000;
    private long backPressedTime = 0;

    LinearLayout btn_home_bid;
    LinearLayout btn_home_result;
    LinearLayout[] ll_click_home = new LinearLayout[2];


    Context mContext;
    ViewPager vp_home;
    DrawerLayout drawerLayout;
    View frameLayout;
    TextView[] tv_noticeTitles = new TextView[3];
    TextView[] tv_noticeDates = new TextView[3];

    Button btn_recentbid;
    Button btn_resultbid;
    Button btn_modifiedbid;
    Button btn_cancelbid;

    TextView[] tv_newBidNames = new TextView[5];
    TextView[] tv_newBidDates = new TextView[5];

    LinearLayout ll_refreshRecnetBid;
    TextView tv_refreshTime;

    boolean isLogin;

    AlertDialog.Builder loginDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);
        mContext = getApplicationContext();
        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("Home");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.GONE);

        ll_refreshRecnetBid = findViewById(R.id.ll_refreshRecnetBid_home);
        ll_refreshRecnetBid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_recentbid.callOnClick();
            }
        });
        tv_refreshTime = findViewById(R.id.tv_refreshTime_home);

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

        btn_home_bid = findViewById(R.id.btn_home_bid);
        btn_home_result = findViewById(R.id.btn_home_result);

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Home_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Home_Activity.this,mClicklistener);

        ll_click_home[0] = findViewById(R.id.ll_mybidclick_home);
        ll_click_home[1] = findViewById(R.id.ll_searchclick_home);
        if(SaveSharedPreference.getUserID(mContext).isEmpty()){
            ll_click_home[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = loginDialog.create();
                    alertDialog.show();
                    alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));
                    alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));

                }
            });

            ll_click_home[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = loginDialog.create();
                    alertDialog.show();
                    alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));
                    alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));

                }
            });
        }
        else if(!SaveSharedPreference.getUserID(mContext).isEmpty() && !SaveSharedPreference.getIsServicing(mContext)) {
            ll_click_home[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "홈페이지에서 이용등록을 해주세요.", Toast.LENGTH_SHORT).show();
                }
            });

            ll_click_home[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "홈페이지에서 이용등록을 해주세요.", Toast.LENGTH_SHORT).show();
                }
            });
        }else{
            ll_click_home[0].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MyBid_Activity.class);
                    startActivity(intent);
                }
            });

            ll_click_home[1].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, Search_Bid_Activity.class);
                    startActivity(intent);
                }
            });
        }

        ((TextView)findViewById(R.id.tv_noticemore_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Notice_Activity.class);
                startActivity(intent);
            }
        });

        isLogin = !SaveSharedPreference.getUserID(mContext).isEmpty();
        if(isLogin)
        {
            ((ImageView)findViewById(R.id.iv_login_out_home)).setImageResource(R.drawable.icon_logout);
            ((TextView)findViewById(R.id.tv_login_out_home)).setText("로그아웃");
            ((LinearLayout)findViewById(R.id.ll_logout_home)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SaveSharedPreference.initPreference(mContext);
                    isLogin = false;
                    Toast.makeText(mContext, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(mContext, Home_Activity.class);
                    mContext.startActivity(i);
                    finish();
                }
            });

        }
        else
        {
            ((ImageView)findViewById(R.id.iv_login_out_home)).setImageResource(R.drawable.icon_login);
            ((TextView)findViewById(R.id.tv_login_out_home)).setText("로그인");
            ((LinearLayout)findViewById(R.id.ll_logout_home)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, Login_Activity.class);
                    startActivity(intent);
                }
            });

        }

        ((LinearLayout)findViewById(R.id.btn_pcversion_customerCenter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://new2.21line.co.kr"));
                startActivity(intent);
            }
        });


        ((ImageView)findViewById(R.id.rl_mas_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Mas_Activity.class);
                startActivity(intent);
            }
        });

        btn_recentbid = findViewById(R.id.btn_recentbid_home);
        btn_resultbid = findViewById(R.id.btn_resultbid_home);
        btn_modifiedbid = findViewById(R.id.btn_modifiedbid_home);
        btn_cancelbid = findViewById(R.id.btn_cancelbid_home);

        btnClickedBgr(btn_recentbid,btn_resultbid,btn_modifiedbid,btn_cancelbid);

        btn_recentbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClickedBgr(btn_recentbid,btn_resultbid,btn_modifiedbid,btn_cancelbid);
                getNewBids("new");
            }
        });
        btn_resultbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClickedBgr(btn_resultbid,btn_recentbid,btn_modifiedbid,btn_cancelbid);
                getNewBids("result");
            }
        });
        btn_modifiedbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClickedBgr(btn_modifiedbid,btn_resultbid,btn_recentbid,btn_cancelbid);
                getNewBids("modify");
            }
        });
        btn_cancelbid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnClickedBgr(btn_cancelbid,btn_resultbid,btn_modifiedbid,btn_recentbid);
                getNewBids("cancel");
            }
        });



        vp_home = findViewById(R.id.vp_home);
        vp_home.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                Log.d("position", position + "");
                if (position%3 == 0)
                {
                    ((ImageView)findViewById(R.id.img_firstImg_Home)).setImageResource(R.drawable.icon_viewpager_selected);
                    ((ImageView)findViewById(R.id.img_secondImg_Home)).setImageResource(R.drawable.icon_viewpager_unselected);
                    ((ImageView)findViewById(R.id.img_ThirdImg_Home)).setImageResource(R.drawable.icon_viewpager_unselected);
                }
                else if(position%3 == 1)
                {
                    ((ImageView)findViewById(R.id.img_firstImg_Home)).setImageResource(R.drawable.icon_viewpager_unselected);
                    ((ImageView)findViewById(R.id.img_secondImg_Home)).setImageResource(R.drawable.icon_viewpager_selected);
                    ((ImageView)findViewById(R.id.img_ThirdImg_Home)).setImageResource(R.drawable.icon_viewpager_unselected);
                }
                else if(position%3 == 2)
                {
                    ((ImageView)findViewById(R.id.img_firstImg_Home)).setImageResource(R.drawable.icon_viewpager_unselected);
                    ((ImageView)findViewById(R.id.img_secondImg_Home)).setImageResource(R.drawable.icon_viewpager_unselected);
                    ((ImageView)findViewById(R.id.img_ThirdImg_Home)).setImageResource(R.drawable.icon_viewpager_selected);
                }

                if(position < 3)
                    vp_home.setCurrentItem(position+3,false);
                else if(position >= 3*2)
                    vp_home.setCurrentItem(position - 3, false);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });

        vp_home.setAdapter(new pagerAdapter(Home_Activity.this));
        vp_home.setCurrentItem(3);


        tv_noticeTitles[0] = findViewById(R.id.tv_notice_title_1_home);
        tv_noticeTitles[1] = findViewById(R.id.tv_notice_title_2_home);
        tv_noticeTitles[2] = findViewById(R.id.tv_notice_title_3_home);

        tv_noticeDates[0] = findViewById(R.id.tv_notice_date_1_home);
        tv_noticeDates[1] = findViewById(R.id.tv_notice_date_2_home);
        tv_noticeDates[2] = findViewById(R.id.tv_notice_date_3_home);

        tv_newBidNames[0] = findViewById(R.id.tv_bidlist_title1_home);
        tv_newBidNames[1] = findViewById(R.id.tv_bidlist_title2_home);
        tv_newBidNames[2] = findViewById(R.id.tv_bidlist_title3_home);
        tv_newBidNames[3] = findViewById(R.id.tv_bidlist_title4_home);
        tv_newBidNames[4] = findViewById(R.id.tv_bidlist_title5_home);

        tv_newBidDates[0] = findViewById(R.id.tv_bidlist_date1_home);
        tv_newBidDates[1] = findViewById(R.id.tv_bidlist_date2_home);
        tv_newBidDates[2] = findViewById(R.id.tv_bidlist_date3_home);
        tv_newBidDates[3] = findViewById(R.id.tv_bidlist_date4_home);
        tv_newBidDates[4] = findViewById(R.id.tv_bidlist_date5_home);


        ((LinearLayout)findViewById(R.id.btn_dial_customerCenter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1599-2127"));
                startActivity(i);
            }
        });

        if(SaveSharedPreference.getUserID(mContext).isEmpty()){
            ((LinearLayout) findViewById(R.id.btn_question_customerCenter)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog alertDialog = loginDialog.create();
                    alertDialog.show();
                    alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));
                    alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));

                }
            });
        }else {
            ((LinearLayout) findViewById(R.id.btn_question_customerCenter)).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent i = new Intent(mContext, Qna_Activity.class);
                    startActivity(i);
                }
            });
        }

        getMemberData();
        getNoticeSummary();
        getMypageGroup();


        loginDialog = new AlertDialog.Builder(Home_Activity.this);
        loginDialog.setMessage("로그인이 필요한 페이지입니다.")
                .setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Home_Activity.this,Login_Activity.class);
                        startActivity(intent);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
}

    private void btnClickedBgr(Button btn1,Button btn2,Button btn3,Button btn4)
    {
        btn1.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn1.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn1.setTypeface(null, Typeface.BOLD);
        btn1.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn2.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn2.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn2.setTypeface(null, Typeface.NORMAL);
        btn2.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn3.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn3.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn3.setTypeface(null, Typeface.NORMAL);
        btn3.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn4.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn4.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn4.setTypeface(null, Typeface.NORMAL);
        btn4.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
    }


    @Override
    public void onResume(){
        super.onResume();
        drawerLayout.closeDrawers();
        getNewBids("new");
    }


    @Override
    public void onBackPressed() {
        long tempTime = System.currentTimeMillis();
        long intervalTime = tempTime - backPressedTime;

        if (0 <= intervalTime && FINISH_INTERVAL_TIME >= intervalTime) {

            finish();
            super.onBackPressed();

        } else {
            backPressedTime = tempTime;
            Toast.makeText(mContext, "뒤로가기를 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
        }
    }



    private class pagerAdapter extends PagerAdapter
    {

        private LayoutInflater mInflate;

        public pagerAdapter(Context context) {
            super();
            mInflate = LayoutInflater.from(context);
        }

        @Override
        public Object instantiateItem(ViewGroup pager, int position)
        {
            position %= 3;
            View view = null;
            if(position == 0) {
                view = mInflate.inflate(R.layout.home_viewpager1, null);
                Glide.with(mContext).load(R.drawable.viewpager1).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)).into((ImageView) view.findViewById(R.id.viewpager1));
            }
                else if(position == 1)
            {
                view = mInflate.inflate(R.layout.home_viewpager2, null);
                Glide.with(mContext).load(R.drawable.viewpager2).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)).into((ImageView)view.findViewById(R.id.viewpager2));
            }
            else{
                view = mInflate.inflate(R.layout.home_viewpager3, null);
                Glide.with(mContext).load(R.drawable.viewpager3).apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.NONE)).into((ImageView)view.findViewById(R.id.viewpager3));
            }
            pager.addView(view, 0);
            return view;
        }

        public void destroyItem(ViewGroup pager, int position, Object view)
        {
            pager.removeView((View)view);
        }

        public boolean isViewFromObject(View pager, Object obj)
        {
            return pager == obj;
        }

        public void restoreState(Parcelable arg0, ClassLoader arg1) {}
        public Parcelable saveState() { return null; }
        public void startUpdate(ViewGroup arg0) {}
        public void finishUpdate(ViewGroup arg0) {}

        @Override
        public int getCount() {
            return 3*3;
        }
    }

    public void getNoticeSummary(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Board/getBoardList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);

                        Date regDate = new Date(o.getLong("RegDate"));
                        SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
                        sdf.setTimeZone(time);
                        final String date = sdf.format(regDate);
                        final String title = o.getString("Title");
                        final String content = o.getString("Content");

                        tv_noticeTitles[i].setText(title);
                        tv_noticeTitles[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, Notice_Detail_Activity.class);
                                intent.putExtra("Title", title);
                                intent.putExtra("Date", date);
                                intent.putExtra("Content", content);
                                startActivity(intent);
                            }
                        });
                        tv_noticeDates[i].setText(date);
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("BoardName", "NoticeBoard");
                params.put("PageRowLimit", String.valueOf(3));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

    public void getMemberData(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Member/getMemberData.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("userData = ", obj.toString());
                    SaveSharedPreference.setPrefUserComName(mContext, obj.getString("ComName"));
                    SaveSharedPreference.setPrefIsServicing(mContext, obj.getString("isService").equals("Y"));
                    SaveSharedPreference.setPrefServiceDueDate(mContext, obj.getString("ESerDate"));
                    SaveSharedPreference.setPrefServiceType(mContext, obj.getString("ServiceType"));
                    SaveSharedPreference.setPrefUserType(mContext, obj.getString("MemKind"));
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

    private void getMypageGroup(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/getMypageGroup.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray o = new JSONArray(response);
                    JSONObject obj = o.getJSONObject(0);
                    if(obj == null){
                        btn_home_bid.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext, "홈페이지에서 맞춤설정을 등록하세요.", Toast.LENGTH_SHORT).show();
                            }
                        });

                        btn_home_result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext, "홈페이지에서 맞춤설정을 등록하세요.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }else {
                        final String GCode = obj.getString("GCode");
                        final String GorupName = obj.getString("GName");
                        final String groupData = obj.toString();

                        btn_home_bid.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, Bid_Activity.class);
                                intent.putExtra("isAdded", true);
                                intent.putExtra("GCode", GCode);
                                intent.putExtra("GName", GorupName);
                                intent.putExtra("groupData", groupData);
                                mContext.startActivity(intent);
                            }
                        });

                        btn_home_result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent intent = new Intent(mContext, Result_Activity.class);
                                intent.putExtra("isAdded", true);
                                intent.putExtra("GCode", GCode);
                                intent.putExtra("GName", GorupName);
                                intent.putExtra("groupData", groupData);
                                mContext.startActivity(intent);
                            }
                        });
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                    if(SaveSharedPreference.getUserID(mContext).isEmpty()) {
                        btn_home_bid.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog alertDialog = loginDialog.create();
                                alertDialog.show();
                                alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));
                                alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));

                            }
                        });

                        btn_home_result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                AlertDialog alertDialog = loginDialog.create();
                                alertDialog.show();
                                alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));
                                alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));

                            }
                        });
                    }else if(!SaveSharedPreference.getIsServicing(mContext)){
                        btn_home_bid.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext, "21라인 웹 사이트에서 서비스 연장이 가능합니다.", Toast.LENGTH_SHORT).show();
                            }
                        });

                        btn_home_result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext, "21라인 웹 사이트에서 서비스 연장이 가능합니다.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    } else {
                        btn_home_bid.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext, "21라인 웹 사이트에서 맞춤설정을 등록하세요.", Toast.LENGTH_SHORT).show();
                            }
                        });

                        btn_home_result.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(mContext, "21라인 웹 사이트에서 맞춤설정을 등록하세요.", Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                }

            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    private void getNewBids(final String type){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getBidDataUri() + "getNewBids.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject o = new JSONObject(response);
                    JSONArray obj = o.getJSONArray(type);
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject data = obj.getJSONObject(i);
                        final String iBidCode = data.getString("BidNo") + "-" + data.getString("BidNoSeq");
                        tv_newBidNames[i].setText(data.getString("BidName"));
                        tv_newBidDates[i].setText(data.getString("RegDate"));
                        if(SaveSharedPreference.getUserID(mContext).isEmpty()){
                            tv_newBidNames[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    AlertDialog alertDialog = loginDialog.create();
                                    alertDialog.show();
                                    alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));
                                    alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));
                                }
                            });
                        }else if(!SaveSharedPreference.getIsServicing(mContext)){
                            tv_newBidNames[i].setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    Toast.makeText(mContext, "홈페이지에서 이용등록을 해주세요.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }else {
                            if(type.equals("result")){
                                tv_newBidNames[i].setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(mContext, Result_Detail_Activity.class);
                                        i.putExtra("iBidCode", iBidCode);
                                        startActivity(i);
                                    }
                                });
                            }else {
                                tv_newBidNames[i].setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        Intent i = new Intent(mContext, Bid_Detail_Activity.class);
                                        i.putExtra("iBidCode", iBidCode);
                                        startActivity(i);
                                    }
                                });
                            }
                        }
                    }

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd HH:mm");
                    sdf.setTimeZone(time);
                    tv_refreshTime.setText("새로고침(" + sdf.format(date) + " 기준)");
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }




}
