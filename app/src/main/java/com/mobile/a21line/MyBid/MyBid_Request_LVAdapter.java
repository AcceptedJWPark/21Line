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

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Bid.Bid_Detail_Activity;
import com.mobile.a21line.Bid.Bid_Listitem;
import com.mobile.a21line.BidResultCommon.Popup_MemoAdd;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-10-31.
 */

public class MyBid_Request_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<MyBid_Request_Listitem> arrayList;
    private Activity activity;
    private OnAnalDataDeleteListener onAnalDataDeleteListener;

    interface OnAnalDataDeleteListener{
        void onAnalDataDeleteListener();
    }

    public void setOnAnalDataDeleteListener(OnAnalDataDeleteListener listener){
        onAnalDataDeleteListener = listener;
    }

    public MyBid_Request_LVAdapter(Context mContext, ArrayList<MyBid_Request_Listitem> arrayList, Activity activity)
    {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.activity = activity;
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
            holder.myBidClicked = (ImageView) view.findViewById(R.id.iv_myBidClicked_Request);

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
        TextView tv_condition1 = ((TextView)view.findViewById(R.id.tv_condition1_request));
        TextView tv_condition2 = ((TextView)view.findViewById(R.id.tv_condition2_request));
        View div_condition1 = ((View)view.findViewById(R.id.view_condition1_div));
        View div_condition2 = ((View)view.findViewById(R.id.view_condition2_div));
        TextView tv_cancel_request = ((TextView)view.findViewById(R.id.tv_cancel_request));

        Log.d("sendDate", arrayList.get(position).getBidPrice());
        if(compareDate(arrayList.get(position).getFinalDate()) && !arrayList.get(position).isChkMoney()){
            tv_condition1.setText("미확인");
        }
        else if(arrayList.get(position).getBidPrice().isEmpty() || arrayList.get(position).getBidPrice().equals("0원"))
        {
            tv_condition1.setText("기초부족");
            tv_condition1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(mContext, "기초금액이 발표되지 않았습니다.", Toast.LENGTH_SHORT).show();
                }
            });

            tv_cancel_request.setVisibility(View.VISIBLE);
            tv_cancel_request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delAnalData(arrayList.get(position).getiBidCode(), position);
                }
            });
        }
        else if(!arrayList.get(position).getMemo().isEmpty() && arrayList.get(position).getSendMoney().equals("0원")){
            tv_condition1.setText("진행중");
            tv_condition2.setVisibility(View.VISIBLE);
            div_condition1.setVisibility(View.VISIBLE);
            tv_condition2.setText("담당자 메모 확인");
            tv_condition2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, Popup_MemoAdd.class);
                    intent.putExtra("isAnalList", true);
                    intent.putExtra("Memo", arrayList.get(position).getMemo());
                    mContext.startActivity(intent);
                }
            });

            tv_cancel_request.setVisibility(View.VISIBLE);
            tv_cancel_request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delAnalData(arrayList.get(position).getiBidCode(), position);
                }
            });
         }

        else if(!arrayList.get(position).getSendDate().equals("1970-01-01") && !arrayList.get(position).isChkMoney())
        {
            tv_condition1.setText("금액확인");
            tv_condition1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MyBid_Request_Listitem item = arrayList.get(position);
                    item.setChkMoney(true);
                    arrayList.set(position, item);
                    notifyDataSetChanged();
                }
            });

            tv_cancel_request.setVisibility(View.VISIBLE);
            tv_cancel_request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delAnalData(arrayList.get(position).getiBidCode(), position);
                }
            });
        }
        else if(arrayList.get(position).getSendDate().equals("1970-01-01"))
        {
            tv_condition1.setText("진행 중");

            tv_cancel_request.setVisibility(View.VISIBLE);
            tv_cancel_request.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    delAnalData(arrayList.get(position).getiBidCode(), position);
                }
            });
        }
        else if(!arrayList.get(position).equals("1970-01-01") && arrayList.get(position).isChkMoney()){
             tv_condition1.setText("분석완료");
             if(!arrayList.get(position).getMemo().isEmpty()){
                 div_condition1.setVisibility(View.VISIBLE);
                 tv_condition2.setText("담당자 메모 확인");
                 tv_condition2.setVisibility(View.VISIBLE);
                 tv_condition2.setOnClickListener(new View.OnClickListener() {
                     @Override
                     public void onClick(View v) {
                         Intent intent = new Intent(mContext, Popup_MemoAdd.class);
                         intent.putExtra("isAnalList", true);
                         intent.putExtra("Memo", arrayList.get(position).getMemo());
                         mContext.startActivity(intent);
                     }
                 });
             }
            div_condition2.setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.tv_sendPrice_request)).setText("추천금액 : " + arrayList.get(position).getSendMoney());
            ((TextView)view.findViewById(R.id.tv_sendPrice_request)).setVisibility(View.VISIBLE);
            ((TextView)view.findViewById(R.id.tv_sendPercent_request)).setText("사정율 : " + arrayList.get(position).getSendPercent());
            ((TextView)view.findViewById(R.id.tv_sendPercent_request)).setVisibility(View.VISIBLE);
        }
        bidState(arrayList.get(position).getBidState(), view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(mContext, Bid_Detail_Activity.class);
                intent.putExtra("iBidCode", arrayList.get(position).getiBidCode());
                intent.putExtra("isAnal", true);
                mContext.startActivity(intent);
            }
        });

        if(!arrayList.get(position).isMybidClicked())
        {
            holder.myBidClicked.setImageResource(R.drawable.icon_unclicked_mybid_dl);
        }
        else
        {
            holder.myBidClicked.setImageResource(R.drawable.icon_clicked_mybid_dl);
        }

        holder.myBidClicked.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, MyBid_moveGroup.class);
                intent.putExtra("isMydoc", false);
                intent.putExtra("iBidCode", arrayList.get(position).getiBidCode());
                intent.putExtra("Position", position);
                activity.startActivityForResult(intent, 3);
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

    private boolean compareDate(String date1){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date pDate2 = new Date();
        try {
            Date pDate1 = sdf.parse(date1);

            int compare = pDate1.compareTo(pDate2);
            if(compare > 0){
                return false;
            }else{
                return true;
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return false;

    }

    public void bidState(int bidState, View v)
    {
        LinearLayout ll_bidstateContainer = v.findViewById(R.id.ll_bidstateContainer_request);

        ImageView[] iv_bidstate = new ImageView[9];
        iv_bidstate[0] = v.findViewById(R.id.iv_bidstate1_request);
        iv_bidstate[1] = v.findViewById(R.id.iv_bidstate2_request);
        iv_bidstate[2] = v.findViewById(R.id.iv_bidstate3_request);
        iv_bidstate[3] = v.findViewById(R.id.iv_bidstate4_request);
        iv_bidstate[4] = v.findViewById(R.id.iv_bidstate5_request);
        iv_bidstate[5] = v.findViewById(R.id.iv_bidstate6_request);
        iv_bidstate[6] = v.findViewById(R.id.iv_bidstate7_request);
        iv_bidstate[7] = v.findViewById(R.id.iv_bidstate8_request);
        iv_bidstate[8] = v.findViewById(R.id.iv_bidstate9_request);

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

    private void delAnalData(final String iBidCode, final int position){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "/Mydoc/delAnalData.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        arrayList.remove(position);
                        notifyDataSetChanged();
                        Toast.makeText(mContext, "분석취소가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        onAnalDataDeleteListener.onAnalDataDeleteListener();
                    }

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                String[] iBidCodes = iBidCode.split("-");
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("BidNo", iBidCodes[0]);
                params.put("BidNoSeq", iBidCodes[1]);

                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }



}




