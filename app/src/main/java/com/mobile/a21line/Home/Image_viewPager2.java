package com.mobile.a21line.Home;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.mobile.a21line.R;

/**
 * Created by Accepted on 2018-05-04.
 */

public class Image_viewPager2 extends Fragment {
    public Image_viewPager2()
    {
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout ll_viewPager2 = (LinearLayout) inflater.inflate(R.layout.home_viewpager2, container, false);
        return ll_viewPager2;
    }
}
