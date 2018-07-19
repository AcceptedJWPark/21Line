package com.mobile.a21line.MyBid;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.widget.TooltipCompat;
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

        if(!arrayList.get(position).getSendDate().isEmpty() && !arrayList.get(position).isChkMoney())
        {
            tv_condition.setText("미확인");
            tv_condition.setTextColor(Color.WHITE);
            tv_condition.setBackgroundResource(R.drawable.bgr_request_condition1);
        }
        else if(arrayList.get(position).getSendDate().isEmpty())
        {
            tv_condition.setText("진행중");
            tv_condition.setTextColor(mContext.getResources().getColor(R.color.textColor_deep));
            tv_condition.setBackgroundResource(R.drawable.bgr_request_condition2);
        }
        else if(arrayList.get(position).getBidPrice().isEmpty() || arrayList.get(position).getBidPrice().equals("0"))
        {
            tv_condition.setText("기초부족");
            tv_condition.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "기초금액이 발표되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
            });
            tv_condition.setTextColor(mContext.getResources().getColor(R.color.textColor_deep));
            tv_condition.setBackgroundResource(R.drawable.bgr_request_condition2);
        }
        else if(!arrayList.get(position).getSendDate().isEmpty() && arrayList.get(position).isChkMoney()){
            tv_condition.setVisibility(View.GONE);
            ((TextView)view.findViewById(R.id.tv_sendPrice_request)).setText("추천금액 : " + arrayList.get(position).getSendMoney());
        }
        else if(!arrayList.get(position).getMemo().isEmpty()){

        }
//        else if(arrayList.get(position).getProgress()==4)
//        {
//            ((TextView)view.findViewById(R.id.tv_condition1_request)).setText("답변완료");
//            ((TextView)view.findViewById(R.id.tv_condition1_request)).setTextColor(Color.WHITE);
//            ((TextView)view.findViewById(R.id.tv_condition1_request)).setBackgroundResource(R.drawable.bgr_request_condition3);
//        }
//        else if(arrayList.get(position).getProgress()==5)
//        {
//            ((TextView)view.findViewById(R.id.tv_condition1_request)).setText("취소공고");
//            ((TextView)view.findViewById(R.id.tv_condition1_request)).setTextColor(Color.WHITE);
//            ((TextView)view.findViewById(R.id.tv_condition1_request)).setBackgroundResource(R.drawable.bgr_request_condition4);
//        }
//        else
//        {
//            ((TextView)view.findViewById(R.id.tv_condition1_request)).setText("확인공고");
//            ((TextView)view.findViewById(R.id.tv_condition1_request)).setTextColor(Color.WHITE);
//            ((TextView)view.findViewById(R.id.tv_condition1_request)).setBackgroundResource(R.drawable.bgr_request_condition5);
//        }

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




