package com.mobile.a21line.Home;

import android.content.Context;
import android.content.Intent;
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
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
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

    Context mContext;
    ViewPager vp_home;
    DrawerLayout drawerLayout;
    View frameLayout;
    TextView[] tv_noticeTitles = new TextView[5];
    TextView[] tv_noticeDates = new TextView[5];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);
        mContext = getApplicationContext();
        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("Home");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

        View.OnClickListener mClicklistener = new  View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Home_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Home_Activity.this,mClicklistener);


        vp_home = findViewById(R.id.vp_home);
        vp_home.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                if (position%3 == 0)
                {
                    ((ImageView)findViewById(R.id.img_firstImg_Home)).setImageResource(android.R.drawable.ic_notification_overlay);
                    ((ImageView)findViewById(R.id.img_secondImg_Home)).setImageResource(android.R.drawable.presence_invisible);
                    ((ImageView)findViewById(R.id.img_ThirdImg_Home)).setImageResource(android.R.drawable.presence_invisible);
                }
                else if(position%3 == 1)
                {
                    ((ImageView)findViewById(R.id.img_firstImg_Home)).setImageResource(android.R.drawable.presence_invisible);
                    ((ImageView)findViewById(R.id.img_secondImg_Home)).setImageResource(android.R.drawable.ic_notification_overlay);
                    ((ImageView)findViewById(R.id.img_ThirdImg_Home)).setImageResource(android.R.drawable.presence_invisible);
                }
                else if(position%3 == 2)
                {
                    ((ImageView)findViewById(R.id.img_firstImg_Home)).setImageResource(android.R.drawable.presence_invisible);
                    ((ImageView)findViewById(R.id.img_secondImg_Home)).setImageResource(android.R.drawable.presence_invisible);
                    ((ImageView)findViewById(R.id.img_ThirdImg_Home)).setImageResource(android.R.drawable.ic_notification_overlay);
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

        vp_home.setAdapter(new pagerAdapter(mContext));
        vp_home.setCurrentItem(3);

        tv_noticeTitles[0] = findViewById(R.id.tv_notice_title_1_home);
        tv_noticeTitles[1] = findViewById(R.id.tv_notice_title_2_home);
        tv_noticeTitles[2] = findViewById(R.id.tv_notice_title_3_home);
        tv_noticeTitles[3] = findViewById(R.id.tv_notice_title_4_home);
        tv_noticeTitles[4] = findViewById(R.id.tv_notice_title_5_home);

        tv_noticeDates[0] = findViewById(R.id.tv_notice_date_1_home);
        tv_noticeDates[1] = findViewById(R.id.tv_notice_date_2_home);
        tv_noticeDates[2] = findViewById(R.id.tv_notice_date_3_home);
        tv_noticeDates[3] = findViewById(R.id.tv_notice_date_4_home);
        tv_noticeDates[4] = findViewById(R.id.tv_notice_date_5_home);

        //getMemberData();
        //getNoticeSummary();

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

            }
            else if(position == 1){
                view = mInflate.inflate(R.layout.home_viewpager2, null);
            }
            else{
                view = mInflate.inflate(R.layout.home_viewpager3, null);
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
                        String date = sdf.format(regDate);

                        tv_noticeTitles[i].setText(o.getString("Title"));
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
                params.put("PageRowLimit", String.valueOf(5));
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



}
