package com.mobile.a21line.Bid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.support.v7.widget.TooltipCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.a21line.MyBid.MyBid_moveGroup;
import com.mobile.a21line.R;

import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Bid_Analysis_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<Bid_Analysis_Listitem> arrayList;

    public Bid_Analysis_LVAdapter(Context mContext, ArrayList<Bid_Analysis_Listitem> arrayList) {
        this.mContext = mContext;
        this.arrayList = arrayList;
        Collections.sort(arrayList);
        notifyDataSetChanged();
    }


    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public void setArrayList(ArrayList<Bid_Analysis_Listitem> arrayList){
        this.arrayList = arrayList;
        notifyDataSetChanged();
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder = null;
        view = null;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.bid_analysis_result_listbg, null);
            holder = new ViewHolder();

            holder.range = (TextView) view.findViewById(R.id.tv_analysis_listview_range);
            holder.average = (TextView) view.findViewById(R.id.tv_analysis_listview_average);
            holder.count = (TextView) view.findViewById(R.id.tv_analysis_listview_count);

            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        if(position%2==1)
        {
            view.setBackgroundResource(R.color.listview_divider1);
        }
        else
        {
            view.setBackgroundResource(R.color.listview_divider2);
        }




        holder.range.setText(arrayList.get(position).getRange());
        holder.average.setText(arrayList.get(position).getAverage());
        holder.count.setText(arrayList.get(position).getCount());

        final ViewHolder finalHolder = holder;


        return view;
    }

    static class ViewHolder {
        TextView range;
        TextView average;
        TextView count;
    }

    public void chgSort(boolean isChg, int sortType){
        for(int i = 0; i < arrayList.size(); i++) {
            Bid_Analysis_Listitem item = arrayList.get(i);
            if (isChg) {
                item.setSortType(sortType);
            } else {
                item.setOrderBy();
            }
            arrayList.set(i, item);
        }

        Collections.sort(arrayList);
        notifyDataSetChanged();
    }
}



