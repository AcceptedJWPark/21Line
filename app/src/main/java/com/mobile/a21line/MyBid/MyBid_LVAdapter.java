package com.mobile.a21line.MyBid;

import android.content.Context;
import android.util.Log;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class MyBid_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<MyBid_Listitem> arrayList;
    private boolean isModify = false;
    private LinearLayout ll_mybid_nogroup;

    public MyBid_LVAdapter(Context mContext, ArrayList<MyBid_Listitem> arrayList, LinearLayout ll_mybid_nogroup)
    {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.ll_mybid_nogroup = ll_mybid_nogroup;
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
            holder.regDate = (TextView) view.findViewById(R.id.tv_groupdate_mybid);
            holder.groupModify = (ImageView) view.findViewById(R.id.iv_groupmodify_edit_mybid);
            holder.groupDelete = (ImageView) view.findViewById(R.id.iv_groupdelete_edit_mybid);
            view.setTag(holder);


        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MyBid_List_Activity.class);
                mContext.startActivity(intent);
            }
        });

        if(isModify){
            ViewGroup.LayoutParams params = ll_mybid_nogroup.getLayoutParams();
            params.height = 1;
            ll_mybid_nogroup.setLayoutParams(params);
            ll_mybid_nogroup.setVisibility(View.GONE);

            holder.count.setVisibility(View.GONE);

            holder.groupModify.setVisibility(View.VISIBLE);
            holder.groupModify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });

            holder.groupDelete.setVisibility(View.VISIBLE);
            holder.groupDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                }
            });
        }else{
            ViewGroup.LayoutParams params = ll_mybid_nogroup.getLayoutParams();
            params.height = ViewGroup.LayoutParams.WRAP_CONTENT;
            ll_mybid_nogroup.setLayoutParams(params);
            ll_mybid_nogroup.setVisibility(View.VISIBLE);

            holder.count.setVisibility(View.VISIBLE);
            holder.groupModify.setVisibility(View.GONE);
            holder.groupDelete.setVisibility(View.GONE);
        }







        holder.groupname.setText(arrayList.get(position).getGroupTitle());
        holder.count.setText(arrayList.get(position).getBidCount());
        holder.regDate.setText("등록일 : " + arrayList.get(position).getRegDate());
        return view;
    }

    static class ViewHolder
    {
        TextView groupname;
        TextView count;
        TextView regDate;
        ImageView groupModify;
        ImageView groupDelete;
    }

    public void modifyGroup(){
        if(isModify){
            isModify = false;
        }
        else{
            isModify = true;
        }
        notifyDataSetChanged();
    }


}




