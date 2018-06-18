package com.mobile.a21line.Bid;

import android.content.Context;
import android.content.Intent;
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

import com.mobile.a21line.MyBid.MyBid_moveGroup;
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

        View view = convertView;
        ViewHolder holder = null;
        view = null;

        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.bid_list_bg, null);
            holder = new ViewHolder();

            holder.bidNo = (TextView) view.findViewById(R.id.tv_bidNo_Bid);
            holder.orderName = (TextView) view.findViewById(R.id.tv_OrderName_Bid);
            holder.bidDate = (TextView) view.findViewById(R.id.tv_bidDate_Bid);
            holder.bidPrice = (TextView) view.findViewById(R.id.tv_bidPrice_Bid);
            holder.bidTitle = (TextView) view.findViewById(R.id.tv_bidTitle_Bid);
            holder.myBidClicked = (ImageView) view.findViewById(R.id.iv_myBidClicked_Bid);

            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        if(!arrayList.get(position).getMybidClicked())
        {
            holder.myBidClicked.setImageResource(R.drawable.icon_unclicked_mybid_dl);
        }
        else
        {
            holder.myBidClicked.setImageResource(R.drawable.icon_clicked_mybid_dl);
        }

        holder.bidNo.setText(arrayList.get(position).getBidNo());
        holder.orderName.setText(arrayList.get(position).getOrderName());
        holder.bidDate.setText(arrayList.get(position).getBidDate());
        holder.bidPrice.setText(arrayList.get(position).getBidPrice());
        holder.bidTitle.setText(arrayList.get(position).getBidTitle());
        bidState(arrayList.get(position).getBidState(), view);

        final String iBidCode = arrayList.get(position).getiBidCode();
        if((arrayList.get(position).getBidState() & 0x20) > 0){
            holder.bidNo.setPaintFlags(holder.bidNo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.bidTitle.setPaintFlags(holder.bidTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.orderName.setPaintFlags(holder.orderName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            view.setBackgroundResource(R.color.listBgr_failedBid);
        }

        view.findViewById(R.id.ll_bid_list_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               Intent intent = new Intent(mContext, Bid_Detail_Activity.class);
                intent.putExtra("iBidCode", iBidCode);
                mContext.startActivity(intent);
            }
        });

        final ViewHolder finalHolder = holder;



        holder.myBidClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyBid_moveGroup.class);
                mContext.startActivity(intent);

                if(!arrayList.get(position).getMybidClicked())
                {
                    finalHolder.myBidClicked.setImageResource(R.drawable.icon_clicked_mybid_dl);
                    arrayList.get(position).setMybidClicked(true);
                    Toast.makeText(mContext,"내 입찰 서류함에 저장되었습니다.",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    finalHolder.myBidClicked.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                    arrayList.get(position).setMybidClicked(false);
                    Toast.makeText(mContext,"내 입찰 서류함에서 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    static class ViewHolder
    {
        TextView bidNo;
        TextView orderName;
        TextView bidDate;
        TextView bidPrice;
        TextView bidTitle;
        ImageView myBidClicked;
    }

    public void bidState(int bidState, View v)
    {
        LinearLayout ll_bidstateContainer = v.findViewById(R.id.ll_bidstateContainer_bid);

        ImageView[] iv_bidstate = new ImageView[9];
        iv_bidstate[0] = v.findViewById(R.id.iv_bidstate1_bid);
        iv_bidstate[1] = v.findViewById(R.id.iv_bidstate2_bid);
        iv_bidstate[2] = v.findViewById(R.id.iv_bidstate3_bid);
        iv_bidstate[3] = v.findViewById(R.id.iv_bidstate4_bid);
        iv_bidstate[4] = v.findViewById(R.id.iv_bidstate5_bid);
        iv_bidstate[5] = v.findViewById(R.id.iv_bidstate6_bid);
        iv_bidstate[6] = v.findViewById(R.id.iv_bidstate7_bid);
        iv_bidstate[7] = v.findViewById(R.id.iv_bidstate8_bid);
        iv_bidstate[8] = v.findViewById(R.id.iv_bidstate9_bid);

        int[] states = {0x4, 0x1, 0x20, 0x10, 0x80, 0x100, 0x40, 0x2, 0x8, 0x400, 0x800, 0x1000, 0x4000, 0x8000, 0x40000, 0x20000, 0x80000};
        int[] rescources = { R.drawable.bidstate_kinds9, R.drawable.bidstate_kinds2, R.drawable.bidstate_kinds4, R.drawable.bidstate_kinds5, R.drawable.bidstate_kinds6
                , R.drawable.bidstate_kinds11, R.drawable.bidstate_kinds3, R.drawable.bidstate_kinds7, R.drawable.bidstate_kinds8
                , R.drawable.bidstate_kinds10, R.drawable.bidstate_kinds1, R.drawable.bidstate_kinds12, R.drawable.bidstate_kinds3
                , R.drawable.bidstate_kinds16, R.drawable.bidstate_kinds17, R.drawable.bidstate_kinds15, R.drawable.bidstate_kinds18};
        String[] tooltipValue = { "결과발표", "정정공고",  "취소공고", "전자입찰", "견적입찰", "수의계약", "재공고", "긴급공고", "계약공고", "공동도급", "현장설명참조", "역경매", "재입찰", "지명입찰", "여성", "시담", "유찰공고"};

        int index = 0;
        for(int i = 0; i < states.length; i++){
            int temp = bidState & states[i];
            if(temp > 0){
                iv_bidstate[index].setVisibility(View.VISIBLE);
                iv_bidstate[index].setImageResource(rescources[i]);
                TooltipCompat.setTooltipText(iv_bidstate[index], tooltipValue[i]);
                index++;
            }
        }

        if(index > 0){
            ll_bidstateContainer.setVisibility(View.VISIBLE);
        }else{
            ll_bidstateContainer.setVisibility(View.GONE);
        }

    }

}




