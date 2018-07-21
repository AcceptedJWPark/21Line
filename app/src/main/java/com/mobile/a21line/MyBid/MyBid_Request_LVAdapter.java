package com.mobile.a21line.MyBid;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.TooltipCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.a21line.Bid.Bid_Detail_Activity;
import com.mobile.a21line.Bid.Bid_Listitem;
import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class MyBid_Request_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<MyBid_Request_Listitem> arrayList;

    public MyBid_Request_LVAdapter(Context mContext, ArrayList<MyBid_Request_Listitem> arrayList)
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
            view = inflater.inflate(R.layout.mybid_request_list_bg, null);
            holder = new ViewHolder();

            holder.bidNo = (TextView) view.findViewById(R.id.tv_bidNo_request);
            holder.orderName = (TextView) view.findViewById(R.id.tv_OrderName_request);
            holder.bidDate = (TextView) view.findViewById(R.id.tv_bidDate_request);
            holder.bidPrice = (TextView) view.findViewById(R.id.tv_bidPrice_request);
            holder.bidTitle = (TextView) view.findViewById(R.id.tv_bidTitle_request);

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
        TextView tv_condition = ((TextView)view.findViewById(R.id.tv_condition1_request));

        Log.d("sendDate", arrayList.get(position).getBidPrice());
         if(arrayList.get(position).getBidPrice().isEmpty() || arrayList.get(position).getBidPrice().equals("0원"))
        {
            Log.d("Condition", "C");
            tv_condition.setText("기초부족");
            tv_condition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "기초금액이 발표되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
            });
        }
        else if(!arrayList.get(position).getSendDate().equals("1970-01-01") && !arrayList.get(position).isChkMoney())
        {
            Log.d("Condition", "A");
            tv_condition.setText("미확인");
            tv_condition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyBid_Request_Listitem item = arrayList.get(position);
                    item.setChkMoney(true);
                    arrayList.set(position, item);
                    notifyDataSetChanged();
                }
            });
        }
        else if(arrayList.get(position).getSendDate().equals("1970-01-01"))
        {
            Log.d("Condition", "B");
            tv_condition.setText("진행 중");
        }
        else if(!arrayList.get(position).equals("1970-01-01") && arrayList.get(position).isChkMoney()){
            Log.d("Condition", "D");
            tv_condition.setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.tv_sendPrice_request)).setText("추천금액 : " + arrayList.get(position).getSendMoney());
        }
        else if(!arrayList.get(position).getMemo().isEmpty()){
            Log.d("Condition", "E");
        }

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

}




