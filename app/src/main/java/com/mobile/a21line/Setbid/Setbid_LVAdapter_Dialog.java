package com.mobile.a21line.Setbid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.a21line.BinaryCode;
import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Setbid_LVAdapter_Dialog extends BaseAdapter {

    Context mContext;
    private ArrayList<BinaryCode> arrayList;

    public Setbid_LVAdapter_Dialog(Context mContext, ArrayList<BinaryCode> arrayList)
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
            convertView = inflater.inflate(R.layout.setbid_businessselect_childbg, null);
        }

        if(position%2==1)
        {
            convertView.setBackgroundResource(R.color.listview_devider1);
        }
        else
        {
            convertView.setBackgroundResource(R.color.listview_devider2);
        }

        if(arrayList.get(position).isChecked()){
            ((ImageView) convertView.findViewById(R.id.iv_businessSelect_Child)).setVisibility(View.VISIBLE);
        }else{
            ((ImageView) convertView.findViewById(R.id.iv_businessSelect_Child)).setVisibility(View.GONE);
        }

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (v.findViewById(R.id.iv_businessSelect_Child).getVisibility() == View.GONE) {
                    ((ImageView) v.findViewById(R.id.iv_businessSelect_Child)).setVisibility(View.VISIBLE);
                    arrayList.get(position).setChecked(true);
                }
                else{
                    ((ImageView)v.findViewById(R.id.iv_businessSelect_Child)).setVisibility(View.GONE);
                    arrayList.get(position).setChecked(false);
                }

            }
        });


        TextView tv_LocationTxt = (TextView) convertView.findViewById(R.id.tv_businessSelect_Child);
        tv_LocationTxt.setText(arrayList.get(position).getName());

        return convertView;
    }
}




