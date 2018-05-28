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

public class Result_Detail_Multiple_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<Result_Detail_Multiple_Listitem> arrayList;

    public Result_Detail_Multiple_LVAdapter(Context mContext, ArrayList<Result_Detail_Multiple_Listitem> arrayList)
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
            view = inflater.inflate(R.layout.result_detail_multipleprice_list_bg, null);
            holder = new ViewHolder();

            holder.num = (TextView) view.findViewById(R.id.tv_result_detail_multiple_num);
            holder.price = (TextView) view.findViewById(R.id.tv_result_detail_multiple_price);
            holder.dev = (TextView) view.findViewById(R.id.tv_result_detail_multiple_dev);
            holder.percent = (TextView) view.findViewById(R.id.tv_result_detail_multiple_percent);

            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        if (arrayList.get(position).isChecked()){
            view.setBackgroundResource(R.color.listBgr_multiple_checked);
        }

        holder.num.setText(arrayList.get(position).getNum());
        holder.price.setText(arrayList.get(position).getPrice());
        holder.dev.setText(arrayList.get(position).getDev());
        holder.percent.setText(arrayList.get(position).getPercent());


        return view;
    }

    static class ViewHolder
    {
        TextView num;
        TextView price;
        TextView dev;
        TextView percent;
    }


}




