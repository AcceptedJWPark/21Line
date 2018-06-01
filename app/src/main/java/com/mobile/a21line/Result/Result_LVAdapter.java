package com.mobile.a21line.Result;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Bid.Bid_Detail_Activity;
import com.mobile.a21line.Bid.Bid_Listitem;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.view.View.GONE;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Result_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<Result_Listitem> arrayList;

    public Result_LVAdapter(Context mContext, ArrayList<Result_Listitem> arrayList)
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
            view = inflater.inflate(R.layout.result_list_bg, null);
            holder = new ViewHolder();

            holder.bidNo = (TextView) view.findViewById(R.id.tv_bidNo_Result);
            holder.orderName = (TextView) view.findViewById(R.id.tv_OrderName_Result);
            holder.firstComp = (TextView) view.findViewById(R.id.tv_firstComp_Result);
            holder.resultPrice = (TextView) view.findViewById(R.id.tv_resultPrice_Result);
            holder.bidTitle = (TextView) view.findViewById(R.id.tv_bidTitle_Result);
            holder.myBidClicked = (ImageView) view.findViewById(R.id.iv_myBidClicked_Result);
            holder.failedBid = (TextView) view.findViewById(R.id.tv_failedBid_Result);

            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        final String iBidCode = arrayList.get(position).getiBidCode();

        view.findViewById(R.id.ll_result_list_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getBidData(iBidCode);
            }
        });

        if(!arrayList.get(position).getMybidClicked())
        {
            holder.myBidClicked.setImageResource(R.drawable.icon_unclicked_mybid_dl);
        }
        else
        {
            holder.myBidClicked.setImageResource(R.drawable.icon_clicked_mybid_dl);
        }

        holder.bidNo.setText(arrayList.get(position).getBidNo());
        holder.bidTitle.setText(arrayList.get(position).getBidTitle());
        holder.orderName.setText(arrayList.get(position).getOrderName());


        if (arrayList.get(position).getResultFailed())
        {
            holder.firstComp.setVisibility(GONE);
            holder.resultPrice.setVisibility(GONE);
            holder.failedBid.setText("유찰되었습니다. " + "("+arrayList.get(position).getFailedReason()+")");
            view.setBackgroundResource(R.color.listBgr_failedBid);
        }
        else
        {
            holder.resultPrice.setText("낙찰가 : " + arrayList.get(position).getResultPrice() + " ");
            holder.firstComp.setText("1순위 : " + arrayList.get(position).getFirstComp());
            holder.failedBid.setVisibility(GONE);
        }

        bidState(arrayList.get(position).getBidState(), view);

        final ViewHolder finalHolder = holder;

        holder.myBidClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!arrayList.get(position).getMybidClicked())
                {
                    finalHolder.myBidClicked.setImageResource(R.drawable.icon_clicked_mybid_dl);
                    arrayList.get(position).setMybidClicked(true);
                    Toast.makeText(mContext,"내 낙찰 서류함에 저장되었습니다.",Toast.LENGTH_SHORT).show();

                }
                else
                {
                    finalHolder.myBidClicked.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                    arrayList.get(position).setMybidClicked(false);
                    Toast.makeText(mContext,"내 낙찰 서류함에서 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                }
            }
        });

        view.findViewById(R.id.ll_result_list_bg).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Result_Detail_Activity.class);
                intent.putExtra("iBidCode", iBidCode);
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
    }

    private void getBidData(final String iBidCode){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getBidDataUri() + "getBidData.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("bidData = " , obj.toString());
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("iBidCode", iBidCode);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
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

        int index = 0;
        for(int i = 0; i < states.length; i++){
            int temp = bidState & states[i];
            if(temp > 0){
                iv_bidstate[index].setVisibility(View.VISIBLE);
                iv_bidstate[index].setImageResource(rescources[i]);
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




