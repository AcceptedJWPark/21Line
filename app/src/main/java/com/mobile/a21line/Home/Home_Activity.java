package com.mobile.a21line.Home;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.bumptech.glide.Glide;
import com.mobile.a21line.Bid.Bid_Activity;
import com.mobile.a21line.CustomerService.Notice_Detail_Activity;
import com.mobile.a21line.CustomerService.Notice_Activity;
import com.mobile.a21line.MyBid.MyBid_Activity;
import com.mobile.a21line.R;
import com.mobile.a21line.Result.Result_Activity;
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

    Context mContext;
    ViewPager vp_home;
    DrawerLayout drawerLayout;
    View frameLayout;
    TextView[] tv_noticeTitles = new TextView[3];
    TextView[] tv_noticeDates = new TextView[3];

    LinearLayout[] ll_click_home = new LinearLayout[2];


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

        ll_click_home[0].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (mContext, MyBid_Activity.class);
                startActivity(intent);
            }
        });

        ll_click_home[1].setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (mContext, Search_Bid_Activity.class);
                startActivity(intent);
            }
        });

        ((TextView)findViewById(R.id.tv_noticemore_home)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Notice_Activity.class);
                startActivity(intent);
            }
        });


        vp_home = findViewById(R.id.vp_home);
        vp_home.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
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

        ((LinearLayout)findViewById(R.id.btn_dial_customerCenter)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1599-2127"));
                startActivity(i);
            }
        });

        getMemberData();
        getNoticeSummary();
        getMypageGroup();



    }

    @Override
    public void onResume(){
        super.onResume();
        drawerLayout.closeDrawers();
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
        public Object instantiateItem(View pager, int position)
        {
            position %= 3;
            View view = null;
            if(position == 0) {
                view = mInflate.inflate(R.layout.home_viewpager1, null);
                Glide.with(view.findViewById(R.id.viewpager1)).load(R.drawable.viewpager1);

            }
            else if(position == 1){
                view = mInflate.inflate(R.layout.home_viewpager2, null);
                Glide.with(view.findViewById(R.id.viewpager2)).load(R.drawable.viewpager2);
            }
            else{
                view = mInflate.inflate(R.layout.home_viewpager3, null);
                Glide.with(view.findViewById(R.id.viewpager3)).load(R.drawable.viewpager3);
            }
            ((ViewPager) pager).addView(view, 0);
            return view;
        }

        public void destroyItem(View pager, int position, Object view)
        {
            ((ViewPager)pager).removeView((View)view);
        }

        public boolean isViewFromObject(View pager, Object obj)
        {
            return pager == obj;
        }

        public void restoreState(Parcelable arg0, ClassLoader arg1) {}
        public Parcelable saveState() { return null; }
        public void startUpdate(View arg0) {}
        public void finishUpdate(View arg0) {}

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



}
