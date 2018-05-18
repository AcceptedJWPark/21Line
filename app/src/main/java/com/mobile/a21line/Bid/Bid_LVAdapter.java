package com.mobile.a21line.Bid;

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

public class Bid_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<Bid_Listitem> arrayList;

    public Bid_LVAdapter(Context mContext, ArrayList<Bid_Listitem> arrayList)
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
            convertView = inflater.inflate(R.layout.bid_list_bg, null);
        }
        TextView bidNo = (TextView) convertView.findViewById(R.id.tv_bidNo_Bid);
        TextView orderName = (TextView) convertView.findViewById(R.id.tv_OrderName_Bid);
        TextView bidDate = (TextView) convertView.findViewById(R.id.tv_bidDate_Bid);
        TextView bidPrice = (TextView) convertView.findViewById(R.id.tv_bidPrice_Bid);
        TextView bidTitle = (TextView) convertView.findViewById(R.id.tv_bidTitle_Bid);
        final ImageView myBidClicked = (ImageView) convertView.findViewById(R.id.iv_myBidClicked_Bid);

        bidNo.setText(arrayList.get(position).getBidNo());
        orderName.setText(arrayList.get(position).getOrderName());
        bidDate.setText(arrayList.get(position).getBidDate());
        bidPrice.setText(arrayList.get(position).getBidPrice());
        bidTitle.setText(arrayList.get(position).getBidTitle());


        return convertView;
    }
}




