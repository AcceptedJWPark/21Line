package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.mobile.a21line.R;

import java.util.ArrayList;

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
                mContext.startActivity(intent);
            }
        });


        holder.qnaNumber.setText(arrayList.get(position).getQnaNumber());
        holder.qnaTitle.setText(arrayList.get(position).getQnaTitle());
        holder.qnaDate.setText(arrayList.get(position).getQnaDate());
        if(arrayList.get(position).isQnaProgress())
        {
            holder.qnaProgress.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_highlight_pgt));
            holder.qnaProgress.setText("완료");
        }else
        {
            holder.qnaProgress.setTextColor(ContextCompat.getColor(mContext,R.color.textColor_highlight_ngt));
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
    }
}




