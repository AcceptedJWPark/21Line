package com.mobile.a21line.BidResultCommon;

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

public class SimpleSetting_Adapter extends BaseAdapter {

    Context mContext;
    private ArrayList<String> arrayList;

    public SimpleSetting_Adapter(Context mContext, ArrayList<String> arrayList)
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
            view = inflater.inflate(R.layout.simplesetting_list_bg, null);
            holder = new ViewHolder();

            holder.tv_simpleSetting = (TextView) view.findViewById(R.id.tv_simplesetting);
            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }
        holder.tv_simpleSetting.setText(arrayList.get(position));

        if(position%2==1)
        {
            view.setBackgroundResource(R.color.listview_devider1);
        }
        else
        {
            view.setBackgroundResource(R.color.listview_devider2);
        }


        final ImageView checked =  view.findViewById(R.id.iv_simplesetting);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checked.getVisibility() == View.GONE)
                {
                    checked.setVisibility(View.VISIBLE);
                }
                else
                {checked.setVisibility(View.GONE);}
            }
        });





        return view;
    }

    static class ViewHolder
    {
        TextView tv_simpleSetting;
    }


}




