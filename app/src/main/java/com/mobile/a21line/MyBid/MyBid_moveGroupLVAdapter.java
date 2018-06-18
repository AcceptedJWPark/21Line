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

        View view = convertView;
        ViewHolder holder = null;
        view = null;

        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.mybid_movegroup_dialog_bg, null);
            holder = new ViewHolder();

            holder.groupTitle = (TextView) view.findViewById(R.id.tv_movegroup_grouptitle);
            holder.groupChecked = (ImageView) view.findViewById(R.id.iv_movegroup_checkbox);
            view.setTag(holder);

        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        if(position%2==1)
        {
            view.setBackgroundResource(R.color.listview_divider1);
        }
        else
        {
            view.setBackgroundResource(R.color.listview_divider2);
        }


        final ImageView checked = holder.groupChecked;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(arrayList.get(position).isChecked())
                {
                    checked.setImageResource(R.drawable.icon_chechbox_unchecked);
                    arrayList.get(position).setChecked(false);
                }
                else
                {
                    checked.setImageResource(R.drawable.icon_chechbox_checked);
                    arrayList.get(position).setChecked(true);
                }
            }
        });

        holder.groupTitle.setText(arrayList.get(position).getGroupTitle());

        return view;
    }



    static class ViewHolder
    {
        TextView groupTitle;
        ImageView groupChecked;
    }

}




