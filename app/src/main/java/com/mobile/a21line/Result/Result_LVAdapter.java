package com.mobile.a21line.Result;

import static android.view.View.GONE;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.widget.TooltipCompat;

import com.mobile.a21line.MyBid.MyBid_moveGroup;
import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Result_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<Result_Listitem> arrayList;
    private Activity activity;
    private boolean isMydoc;

    public Result_LVAdapter(Context mContext, ArrayList<Result_Listitem> arrayList, Activity activity, boolean isMydoc)
    {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.activity = activity;
        this.isMydoc = isMydoc;
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
            view = inflater.inflate(R.layout.result_list_bg, null);
            holder = new ViewHolder();

            holder.bidNo = (TextView) view.findViewById(R.id.tv_bidNo_Result);
            holder.orderName = (TextView) view.findViewById(R.id.tv_OrderName_Result);
            holder.firstComp = (TextView) view.findViewById(R.id.tv_firstComp_Result);
            holder.resultPrice = (TextView) view.findViewById(R.id.tv_resultPrice_Result);
            holder.bidTitle = (TextView) view.findViewById(R.id.tv_bidTitle_Result);
            holder.myBidClicked = (ImageView) view.findViewById(R.id.iv_myBidClicked_Result);
            holder.failedBid = (TextView) view.findViewById(R.id.tv_failedBid_Result);
            holder.hasMemoFlag = (TextView) view.findViewById(R.id.tv_hasMemoFlag_Result);

            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        final String iBidCode = arrayList.get(position).getiBidCode();

        if(!arrayList.get(position).getMybidClicked())
        {
            holder.myBidClicked.setImageResource(R.drawable.icon_unclicked_mybid_dl);
        }
        else
        {
            holder.myBidClicked.setImageResource(R.drawable.icon_clicked_mybid_dl);
        }

        if(arrayList.get(position).hasMemo()){
            holder.hasMemoFlag.setVisibility(View.VISIBLE);
        }else{
            holder.hasMemoFlag.setVisibility(View.GONE);
        }

        holder.bidNo.setText(arrayList.get(position).getBidNo());
        holder.bidTitle.setText(arrayList.get(position).getBidTitle());
        holder.orderName.setText(arrayList.get(position).getOrderName());


        if (arrayList.get(position).getResultFailed())
        {
            holder.firstComp.setVisibility(GONE);
            holder.resultPrice.setVisibility(GONE);
            holder.failedBid.setText("유찰되었습니다. " + "("+arrayList.get(position).getFailedReason()+")");
            holder.bidTitle.setPaintFlags(holder.bidTitle.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.orderName.setPaintFlags(holder.orderName.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.bidNo.setPaintFlags(holder.bidNo.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            //view.setBackgroundResource(R.color.listBgr_failedBid);
        }
        else
        {
            holder.resultPrice.setText("낙찰가 : " + arrayList.get(position).getResultPrice() + " ");
            holder.firstComp.setText("1순위 : " + arrayList.get(position).getFirstComp());
            holder.failedBid.setVisibility(GONE);
        }

        if(arrayList.get(position).isNewBid){
            view.findViewById(R.id.tv_bidNew_Result).setVisibility(View.VISIBLE);
            view.setBackgroundColor(mContext.getResources().getColor(R.color.listview_new));
        }

        bidState(arrayList.get(position).getBidState(), view);

        final ViewHolder finalHolder = holder;

        holder.myBidClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyBid_moveGroup.class);
                intent.putExtra("iBidCode", arrayList.get(position).getiBidCode());
                intent.putExtra("Position", position);
                intent.putExtra("isMydoc", isMydoc);
                activity.startActivityForResult(intent, 3);
            }
        });

        view.findViewById(R.id.ll_result_list_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Result_Detail_Activity.class);
                intent.putExtra("Position", position);
                intent.putExtra("iBidCode", iBidCode);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        return view;
    }

    static class ViewHolder
    {
        TextView bidNo;
        TextView orderName;
        TextView firstComp;
        TextView resultPrice;
        TextView bidTitle;
        TextView failedBid;
        ImageView myBidClicked;
        TextView hasMemoFlag;
    }

    public void bidState(int bidState, View v)
    {
        LinearLayout ll_bidstateContainer = v.findViewById(R.id.ll_bidstateContainer_result);

        ImageView[] iv_bidstate = new ImageView[9];
        iv_bidstate[0] = v.findViewById(R.id.iv_bidstate1_result);
        iv_bidstate[1] = v.findViewById(R.id.iv_bidstate2_result);
        iv_bidstate[2] = v.findViewById(R.id.iv_bidstate3_result);
        iv_bidstate[3] = v.findViewById(R.id.iv_bidstate4_result);
        iv_bidstate[4] = v.findViewById(R.id.iv_bidstate5_result);
        iv_bidstate[5] = v.findViewById(R.id.iv_bidstate6_result);
        iv_bidstate[6] = v.findViewById(R.id.iv_bidstate7_result);
        iv_bidstate[7] = v.findViewById(R.id.iv_bidstate8_result);
        iv_bidstate[8] = v.findViewById(R.id.iv_bidstate9_result);

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




