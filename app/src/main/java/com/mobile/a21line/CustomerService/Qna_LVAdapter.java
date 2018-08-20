package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Qna_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<Qna_Listitem> arrayList;

    public Qna_LVAdapter(Context mContext, ArrayList<Qna_Listitem> arrayList)
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
            view = inflater.inflate(R.layout.cs_qna_bg, null);
            holder = new ViewHolder();

            holder.qnaNumber = (TextView) view.findViewById(R.id.tv_number_qna);
            holder.qnaTitle = (TextView) view.findViewById(R.id.tv_title_qna);
            holder.qnaDate = (TextView) view.findViewById(R.id.tv_date_qna);
            holder.qnaProgress = (TextView) view.findViewById(R.id.tv_progress_qna);
            holder.iv_delete_qna = (ImageView) view.findViewById(R.id.iv_delete_qna);

            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,Qna_Detail_Activity.class);
                intent.putExtra("Title", arrayList.get(position).getQnaTitle());
                intent.putExtra("Content", arrayList.get(position).getQnaContent());
                intent.putExtra("Date", arrayList.get(position).getQnaDate());
                intent.putExtra("ComName", arrayList.get(position).getQnaComName());
                intent.putExtra("GGroup", arrayList.get(position).getQnaGGroup());
                intent.putExtra("hasAnswer", arrayList.get(position).isQnaProgress());
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(intent);
            }
        });

        final AlertDialog.Builder AlarmDeleteDialog = new AlertDialog.Builder(mContext);

        view.findViewById(R.id.iv_delete_qna).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlarmDeleteDialog.setMessage("문의내역을 삭제하시겠습니까?")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deleteQna(position);
                                dialog.cancel();
                            }
                        })
                        .setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = AlarmDeleteDialog.create();
                alertDialog.show();
                alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
            }
        });


        holder.qnaNumber.setText((position + 1) + "");
        holder.qnaTitle.setText(arrayList.get(position).getQnaTitle());
        holder.qnaDate.setText(arrayList.get(position).getQnaDate());
        if(arrayList.get(position).isQnaProgress())
        {
            holder.qnaProgress.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_highlight_pgt));
            holder.qnaProgress.setText("완료");
        }else
        {
            holder.qnaProgress.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_deep));
            holder.qnaProgress.setText("대기");
        }


        if(position%2==1)
        {
            view.setBackgroundResource(R.color.listview_divider2);
        }
        else
        {
            view.setBackgroundResource(R.color.listview_divider1);
        }



        return view;
    }

    static class ViewHolder
    {
        TextView qnaNumber;
        TextView qnaTitle;
        TextView qnaDate;
        TextView qnaProgress;
        ImageView iv_delete_qna;
    }

    private void deleteQna(final int position){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Board/deleteQNA.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, "질문이 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        arrayList.remove(position);
                        notifyDataSetChanged();
                    }else{
                        Toast.makeText(mContext, "질문 삭제가 실패했습니다.", Toast.LENGTH_SHORT).show();
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
                params.put("Code", arrayList.get(position).getCode());
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }
}




