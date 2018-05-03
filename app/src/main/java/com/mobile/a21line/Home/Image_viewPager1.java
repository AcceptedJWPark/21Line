package com.mobile.a21line.Home;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

import com.mobile.a21line.R;

/**
 * Created by Accepted on 2018-05-04.
 */

public class Image_viewPager1 extends Fragment {

    public Image_viewPager1()
    {
    }

    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        LinearLayout ll_viewPager1 = (LinearLayout) inflater.inflate(R.layout.home_viewpager1, container, false);
        return ll_viewPager1;
    }
}
