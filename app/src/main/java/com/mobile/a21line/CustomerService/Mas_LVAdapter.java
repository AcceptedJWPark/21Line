package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Mas_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<String> arrayList;

    public Mas_LVAdapter(Context mContext, ArrayList<String> arrayList)
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
            view = inflater.inflate(R.layout.cs_mas_popup_bg, null);
            holder = new ViewHolder();

            holder.title = (TextView) view.findViewById(R.id.tv_select_mas);

            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        holder.title.setText(arrayList.get(position));


        if(position%2==1)
        {
            view.setBackgroundResource(R.color.listview_divider2);
        }
        else
        {
            view.setBackgroundResource(R.color.listview_divider1);
        }



        return view;
    }

    static class ViewHolder
    {
        TextView title;
    }
}




