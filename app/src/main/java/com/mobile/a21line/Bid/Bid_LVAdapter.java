package com.mobile.a21line.Bid;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

        final String iBidCode = arrayList.get(position).getiBidCode();

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

}




