package com.mobile.a21line.Home;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;


public class Home_Activity extends AppCompatActivity {

    Context mContext;
    ViewPager vp_home;
    DrawerLayout dl_home;
    View fl_dl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.home_activity);
        mContext = getApplicationContext();
        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("Home");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);

        dl_home = findViewById(R.id.dl_home);
        fl_dl = findViewById(R.id.fl_drawerView_home);

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



}
