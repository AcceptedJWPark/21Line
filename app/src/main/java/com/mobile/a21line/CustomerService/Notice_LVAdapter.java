package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.content.Intent;
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

public class Notice_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<Notice_Listitem> arrayList;

    public Notice_LVAdapter(Context mContext, ArrayList<Notice_Listitem> arrayList)
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
            view = inflater.inflate(R.layout.cs_notice_bg, null);
            holder = new ViewHolder();

            holder.noticenumber = (TextView) view.findViewById(R.id.tv_number_notice);
            holder.noticeTitle = (TextView) view.findViewById(R.id.tv_title_notice);
            holder.noticeDate = (TextView) view.findViewById(R.id.tv_date_notice);

            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Notice_Detail_Activity.class);
                intent.putExtra("Title", arrayList.get(position).getNoticeTitle());
                intent.putExtra("Date", arrayList.get(position).getNoticeDate());
                intent.putExtra("Content", arrayList.get(position).getNoticeContent());
                intent.putExtra("Code", arrayList.get(position).getNoticeCode());
                intent.putExtra("File1", arrayList.get(position).getNoticeFile1());
                intent.putExtra("File2", arrayList.get(position).getNoticeFile2());
                intent.putExtra("File3", arrayList.get(position).getNoticeFile3());
                intent.putExtra("File4", arrayList.get(position).getNoticeFile4());
                mContext.startActivity(intent);

            }
        });


        holder.noticenumber.setText(arrayList.get(position).getNoticeNumber());
        holder.noticeTitle.setText(arrayList.get(position).getNoticeTitle());
        holder.noticeDate.setText(arrayList.get(position).getNoticeDate());


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
        TextView noticenumber;
        TextView noticeTitle;
        TextView noticeDate;
    }
}




