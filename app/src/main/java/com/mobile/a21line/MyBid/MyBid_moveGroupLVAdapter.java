package com.mobile.a21line.MyBid;

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

public class MyBid_moveGroupLVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<MyBid_moveGroup_ListItem> arrayList;

    public MyBid_moveGroupLVAdapter(Context mContext, ArrayList<MyBid_moveGroup_ListItem> arrayList)
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
            convertView = inflater.inflate(R.layout.mybid_movegroup_dialog_bg, null);
        }

        ((TextView)convertView.findViewById(R.id.tv_movegroup_grouptitle)).setText(arrayList.get(position).getGroupTitle());
        final ImageView mybidCheck = convertView.findViewById(R.id.iv_movegroup_checkbox);
        if(arrayList.get(position).isChecked()){
            mybidCheck.setImageResource(R.drawable.icon_chechbox_checked);
        }else {
            mybidCheck.setImageResource(R.drawable.icon_chechbox_unchecked);
        }
        mybidCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.get(position).isChecked())
                {
                    removeAllChecked();
                    notifyDataSetChanged();
                }
                else
                {
                    removeAllChecked();
                    MyBid_moveGroup_ListItem item = arrayList.get(position);
                    item.setChecked(true);
                    arrayList.set(position, item);
                    notifyDataSetChanged();
                }
            }
        });

        return convertView;
    }

    private void removeAllChecked(){
        for(int i = 0; i < arrayList.size(); i++){
            MyBid_moveGroup_ListItem item = arrayList.get(i);
            item.setChecked(false);
            arrayList.set(i, item);
        }
    }

    public ArrayList<MyBid_moveGroup_ListItem> getArrayList(){
        return arrayList;
    }
}




