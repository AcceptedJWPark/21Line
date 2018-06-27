package com.mobile.a21line.MyBid;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CalendarView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-14.
 */

public class MyBid_Schedule_Adapter extends BaseAdapter {

    ArrayList<Schedule_Listitem> arrayList;
    Context mContext;
    int mResource;
    LayoutInflater mLinflater;

    public MyBid_Schedule_Adapter(Context context, int mResource, ArrayList<Schedule_Listitem> arrayList)
    {
        this.mContext = context;
        this.arrayList = arrayList;
        this.mResource = mResource;
        this.mLinflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }




    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return arrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Schedule_Listitem listitem = arrayList.get(position);

        ViewHolder viewHolder;

        if(convertView == null)
        {
            convertView = mLinflater.inflate(mResource,null);
            if(position % 7 == 6)
            {
                convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP()+getRestCellWidthDP(), getCellHeightDP()));
            }else
            {
                convertView.setLayoutParams(new GridView.LayoutParams(getCellWidthDP(), getCellHeightDP()));
            }
            viewHolder = new ViewHolder();
            viewHolder.ll_cell = (LinearLayout)convertView.findViewById(R.id.ll_cell_bgr_schedule);
            viewHolder.day = (TextView) convertView.findViewById(R.id.tv_calender_date_schedule);

            convertView.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        if(listitem != null)
        {
            viewHolder.day.setText(listitem.getDate());
            if(listitem.isInMonth())
            {
                if(position %7 ==0)
                {
                    viewHolder.day.setTextColor(Color.RED);
                }
                else if(position %7 ==6)
                {
                    viewHolder.day.setTextColor(Color.BLUE);
                }
                else
                {
                    viewHolder.day.setTextColor(Color.BLACK);
                }
            }
            else
            {
                viewHolder.day.setTextColor(Color.GRAY);
            }
        }

        return convertView;
    }

    public class ViewHolder
    {
        public LinearLayout ll_cell;
        public TextView day;

    }

    private int getCellWidthDP()
    {
		int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = width/7;

        return cellWidth;
    }

    private int getRestCellWidthDP()
    {
//		int width = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellWidth = 480%7;

        return cellWidth;
    }

    private int getCellHeightDP()
    {
//		int height = mContext.getResources().getDisplayMetrics().widthPixels;
        int cellHeight = 480/6;

        return cellHeight;
    }


}
