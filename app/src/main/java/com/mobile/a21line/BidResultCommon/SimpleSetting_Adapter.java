package com.mobile.a21line.BidResultCommon;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.mobile.a21line.BidAreaCode;
import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class SimpleSetting_Adapter extends BaseAdapter {

    Context mContext;
    private ArrayList arrayList;
    private ArrayList<ImageView> checkedIVList = new ArrayList<>();

    public SimpleSetting_Adapter(Context mContext, ArrayList arrayList)
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
        final ImageView checked;
        if(view==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.simplesetting_list_bg, null);
            holder = new ViewHolder();

            holder.tv_simpleSetting = (TextView) view.findViewById(R.id.tv_simplesetting);
            view.setTag(holder);
            checked =  view.findViewById(R.id.iv_simplesetting);

            checkedIVList.add(checked);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
            checked =  view.findViewById(R.id.iv_simplesetting);
        }
        String name;
        boolean isSelected = true;
        if(arrayList.get(position) instanceof BidAreaCode.BidAreaItem){
            name = ((BidAreaCode.BidAreaItem)arrayList.get(position)).getName();
            isSelected = ((BidAreaCode.BidAreaItem)arrayList.get(position)).isSelected();
        }else{
            name = ((BidUpCode.BidUpCodeItem)arrayList.get(position)).getName();
            isSelected = ((BidUpCode.BidUpCodeItem)arrayList.get(position)).isSelected();
        }
        holder.tv_simpleSetting.setText(name);
        if(isSelected){
            checked.setVisibility(View.VISIBLE);
        }else{
            checked.setVisibility(View.GONE);
        }

        final Object item = arrayList.get(position);
        final int fPosition = position;
        if(position%2==1)
        {
            view.setBackgroundResource(R.color.listview_divider1);
        }
        else
        {
            view.setBackgroundResource(R.color.listview_divider2);
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checked.getVisibility() == View.GONE)
                {
                    if(item instanceof BidUpCode.BidUpCodeItem){
                        BidUpCode.BidUpCodeItem iitem = Popup_SimpleSetting.arrayList_business.get(fPosition);
                        iitem.setSelected(true);
                        Popup_SimpleSetting.arrayList_business.set(fPosition, iitem);
                    }else{
                        BidAreaCode.BidAreaItem iitem = Popup_SimpleSetting.arrayList_location.get(fPosition);
                        iitem.setSelected(true);
                        Popup_SimpleSetting.arrayList_location.set(fPosition, iitem);
                    }
                    checked.setVisibility(View.VISIBLE);
                }
                else
                {
                    if(item instanceof BidUpCode.BidUpCodeItem){
                        BidUpCode.BidUpCodeItem iitem = Popup_SimpleSetting.arrayList_business.get(fPosition);
                        iitem.setSelected(false);
                        Popup_SimpleSetting.arrayList_business.set(fPosition, iitem);
                    }else{
                        BidAreaCode.BidAreaItem iitem = Popup_SimpleSetting.arrayList_location.get(fPosition);
                        iitem.setSelected(false);
                        Popup_SimpleSetting.arrayList_location.set(fPosition, iitem);
                    }
                    checked.setVisibility(View.GONE);
                }
            }
        });





        return view;
    }

    static class ViewHolder
    {
        TextView tv_simpleSetting;
    }

    public void selectAll(){
        Log.d("count = ", arrayList.size() + ", " + checkedIVList.size());
        for(int i = 0; i < arrayList.size(); i++){
            Object obj = arrayList.get(i);
            if(obj instanceof BidAreaCode.BidAreaItem){
                BidAreaCode.BidAreaItem item = (BidAreaCode.BidAreaItem)obj;
                item.setSelected(true);
                arrayList.set(i, item);

            }else{
                BidUpCode.BidUpCodeItem item = (BidUpCode.BidUpCodeItem)obj;
                item.setSelected(true);
                arrayList.set(i, item);
            }
        }
        notifyDataSetChanged();
    }

}




