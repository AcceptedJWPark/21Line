package com.mobile.a21line.Result;

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

}




