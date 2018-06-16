package com.mobile.a21line.MyBid;

import android.content.Context;
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
            view = inflater.inflate(R.layout.mybid_bg, null);
            holder = new ViewHolder();

            holder.groupname = (TextView) view.findViewById(R.id.tv_groupname_mybid);
            holder.count = (TextView) view.findViewById(R.id.tv_count_mybid);
            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }


//        if(position%2==1)
//        {
//            view.setBackgroundResource(R.color.listview_divider2);
//        }
//        else
//        {
//            view.setBackgroundResource(R.color.listview_divider1);
//        }


        holder.groupname.setText(arrayList.get(position).getGroupTitle());
        holder.count.setText(arrayList.get(position).getBidCount());

        return view;
    }

    static class ViewHolder
    {
        TextView groupname;
        TextView count;
    }


}




