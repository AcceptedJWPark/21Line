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

public class MyBid_Schedule_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<MyBid_Schedule_Listitem> arrayList;
    private OnScheduleDeleteListener onScheduleDeleteListener;
    private boolean isResult;

    interface OnScheduleDeleteListener{
        void onScheduleDeleteListener();
    }

    public void setOnScheduleDeleteListener(OnScheduleDeleteListener listener){
        onScheduleDeleteListener = listener;
    }
    
    public MyBid_Schedule_LVAdapter(Context mContext, ArrayList<MyBid_Schedule_Listitem> arrayList)
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
            view = inflater.inflate(R.layout.mybid_schedule_list_bg, null);
            holder = new ViewHolder();

            holder.bidNo = (TextView) view.findViewById(R.id.tv_bidNo_schedule);
            holder.orderName = (TextView) view.findViewById(R.id.tv_OrderName_schedule);
            holder.bidDate = (TextView) view.findViewById(R.id.tv_bidDate_schedule);
            holder.bidPrice = (TextView) view.findViewById(R.id.tv_bidPrice_schedule);
            holder.bidTitle = (TextView) view.findViewById(R.id.tv_bidTitle_schedule);

            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }


        holder.bidNo.setText(arrayList.get(position).getBidNo());
        holder.orderName.setText(arrayList.get(position).getOrderName());
        holder.bidDate.setText(arrayList.get(position).getBidDate());
        holder.bidPrice.setText(arrayList.get(position).getBidPrice());
        holder.bidTitle.setText(arrayList.get(position).getBidTitle());

        return view;
    }

    static class ViewHolder
    {
        TextView bidNo;
        TextView orderName;
        TextView bidDate;
        TextView bidPrice;
        TextView bidTitle;
    }

    public void setResult(boolean isResult){
        this.isResult = isResult;
    }

}




