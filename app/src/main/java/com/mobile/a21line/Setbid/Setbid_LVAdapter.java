package com.mobile.a21line.Setbid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.a21line.BidAreaCode;
import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Setbid_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList arrayList;
    TextView tv_count;

    public Setbid_LVAdapter(Context mContext, ArrayList arrayList, TextView tv_count)
    {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.tv_count = tv_count;
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
    public View getView(final int position, View convertView, final ViewGroup parent) {
        View view = convertView;
        ViewHolder holder = null;
        view = null;

        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.setbid_selected_bg, null);
            holder = new ViewHolder();
            holder.tv_LocationTxt =view.findViewById(R.id.tv_selectedbusiness_Child);

            view.setTag(holder);
        }
        else
        {
            holder = (ViewHolder) view.getTag();
        }
        String item;

        if(arrayList.get(position) instanceof BidAreaCode.BidAreaItem){
            item = ((BidAreaCode.BidAreaItem)arrayList.get(position)).getName();
        }else{
            item = ((BidUpCode.BidUpCodeItem)arrayList.get(position)).getName();
        }
        holder.tv_LocationTxt.setText(item);

        if(position%2==1)
        {
            view.setBackgroundResource(R.color.listview_divider1);
        }
        else
        {
            view.setBackgroundResource(R.color.listview_divider2);
        }

        holder.iv_DeleteIcon = view.findViewById(R.id.iv_selectedbusiness_Child);
        holder.iv_DeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(position);
                tv_count.setText("(" + arrayList.size() + ")");
                notifyDataSetChanged();
            }
        });

        return view;
    }

    static class ViewHolder
    {
        TextView tv_LocationTxt;
        ImageView iv_DeleteIcon;
    }

}




