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
 * Created by kwonhong on 2018-05-10.
 */

public class Setbid_LVAdapter_SubLocation extends BaseAdapter {
    Context mContext;
    private ArrayList<BidAreaCode.BidAreaItem> arrayList;
    ListView lv_selectedLocation;
    private ArrayList<BidAreaCode.BidAreaItem> selectedList = new ArrayList<>();

    public Setbid_LVAdapter_SubLocation(Context mContext, ArrayList<BidAreaCode.BidAreaItem> arrayList, ListView lv_selectedLocation)
    {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.lv_selectedLocation = lv_selectedLocation;
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
            convertView = inflater.inflate(R.layout.setbid_businessselect_childbg, null);
        }
        final View view2 = convertView;
        TextView tv_LocationTxt = (TextView) convertView.findViewById(R.id.tv_businessSelect_Child);
        String name = arrayList.get(position).getName();
        final BidAreaCode.BidAreaItem item = arrayList.get(position);
        if(name.length() == 3){
            name += "전체";
        }
        tv_LocationTxt.setText(name);

        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(selectedList.contains(item)) {
                    selectedList.remove(item);
                    view2.setBackgroundResource(R.drawable.bgr_locationselect2);
                }else{
                    selectedList.add(item);
                    view2.setBackgroundResource(R.color.listview_clicked);
                }
                Setbid_LVAdapter_SelectedLocation adapter_selectedLocation = new Setbid_LVAdapter_SelectedLocation(mContext, selectedList);
                lv_selectedLocation.setAdapter(adapter_selectedLocation);
            }
        });


        return convertView;
    }

}
