package com.mobile.a21line.Setbid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Setbid_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<String> arrayList;

    public Setbid_LVAdapter(Context mContext, ArrayList<String> arrayList)
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

        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.setbid_selected_bg, null);
        }
        TextView tv_LocationTxt = (TextView) convertView.findViewById(R.id.tv_selectedbusiness_Child);
        tv_LocationTxt.setText(arrayList.get(position));

        if(position%2==1)
        {
            convertView.setBackgroundResource(R.color.listview_devider1);
        }
        else
        {
            convertView.setBackgroundResource(R.color.listview_devider2);
        }

        ImageView iv_DeleteIcon = convertView.findViewById(R.id.iv_selectedbusiness_Child);
        iv_DeleteIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                arrayList.remove(position);
                notifyDataSetChanged();
            }
        });

        return convertView;
    }
}




