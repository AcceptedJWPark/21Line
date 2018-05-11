package com.mobile.a21line.Setbid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.mobile.a21line.BidAreaCode;
import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Setbid_LVAdapter_Location extends BaseAdapter {

    Context mContext;
    private ArrayList<BidAreaCode.BidAreaItem> arrayList;
    ListView lv_subLocation;
    private ArrayList<View> arrayView;

    public Setbid_LVAdapter_Location(Context mContext, ArrayList<BidAreaCode.BidAreaItem> arrayList, ListView lv_subLocation)
    {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.lv_subLocation = lv_subLocation;
        arrayView = new ArrayList<>();
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

        if(convertView==null)
        {
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.setbid_locationselect_bg, null);
        }

        final View view2 = convertView;

        TextView tv_LocationTxt = (TextView) convertView.findViewById(R.id.tv_locationSelect);
        tv_LocationTxt.setText(arrayList.get(position).getName());
        arrayView.add(convertView);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                initBackground();
                ArrayList<BidAreaCode.BidAreaItem> arraySubAreaName = BidAreaCode.getSubAreaName(arrayList.get(position).getName());
                Setbid_LVAdapter_SubLocation subLocationAdapter = new Setbid_LVAdapter_SubLocation(mContext, arraySubAreaName);
                lv_subLocation.setAdapter(subLocationAdapter);
                view2.setBackgroundResource(R.color.listview_devider1);
            }
        });


        return convertView;
    }

    private void initBackground(){
        for(int i = 0; i < arrayView.size(); i++){
            arrayView.get(i).setBackgroundResource(R.color.listview_devider2);
        }
    }

}



