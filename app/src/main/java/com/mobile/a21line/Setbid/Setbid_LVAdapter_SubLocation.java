package com.mobile.a21line.Setbid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by kwonhong on 2018-05-10.
 */

public class Setbid_LVAdapter_SubLocation extends BaseAdapter {
    Context mContext;
    private ArrayList<String> arrayList;

    public Setbid_LVAdapter_SubLocation(Context mContext, ArrayList<String> arrayList)
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
            convertView = inflater.inflate(R.layout.setbid_locationselect_bg, null);
        }
        TextView tv_LocationTxt = (TextView) convertView.findViewById(R.id.lv_location2_setbid);
        tv_LocationTxt.setText(arrayList.get(position));
        tv_LocationTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return convertView;
    }
}
