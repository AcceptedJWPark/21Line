package com.mobile.a21line.MyBid;

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
import android.widget.Toast;

import com.mobile.a21line.Bid.Bid_Detail_Activity;
import com.mobile.a21line.Bid.Bid_Listitem;
import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class MyBid_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<MyBid_Listitem> arrayList;

    public MyBid_LVAdapter(Context mContext, ArrayList<MyBid_Listitem> arrayList)
    {
        this.mContext = mContext;
        this.arrayList = arrayList;
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

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        View view = convertView;
        ViewHolder holder = null;
        view = null;

        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mybid_list_group_bg, null);
            holder = new ViewHolder();

            holder.bidNo = (TextView) view.findViewById(R.id.tv_groupname_mybid);
            holder.orderName = (TextView) view.findViewById(R.id.iv_count_mybid);

            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        holder.bidNo.setText(arrayList.get(position).getGroupTitle());
        holder.orderName.setText(arrayList.get(position).getBidCount());

        return view;
    }

    static class ViewHolder
    {
        TextView bidNo;
        TextView orderName;
    }


}




