package com.mobile.a21line.Setbid;

import android.content.Context;
import android.util.Log;
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
import java.util.Iterator;

/**
 * Created by kwonhong on 2018-05-10.
 */

public class Setbid_LVAdapter_SubLocation extends BaseAdapter {
    Context mContext;
    private ArrayList<BidAreaCode.BidAreaItem> arrayList;
    ListView lv_selectedLocation;
    TextView tv_count;
    boolean isSelectAll = false;

    ArrayList<AreaCodeClickEvent> areaCodeClickEventArrayList = new ArrayList<>();

    public Setbid_LVAdapter_SubLocation(Context mContext, ArrayList<BidAreaCode.BidAreaItem> arrayList, ListView lv_selectedLocation, TextView tv_count)
    {
        this.mContext = mContext;
        this.arrayList = arrayList;
        this.lv_selectedLocation = lv_selectedLocation;
        this.tv_count = tv_count;
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


        areaCodeClickEventArrayList.add(new AreaCodeClickEvent(convertView, item));
        if(name.length() == 3 && Setbid_Popup_LocationSelect.arrayLocationList.contains(item)){
            isSelectAll = true;
            setClickEvent(true);
        }

        if(name.length() == 3){
            name += "전체";
        }
        tv_LocationTxt.setText(name);

        Log.d("isSelected", isSelectAll + "");

        if(isSelectAll){
            view2.setBackgroundResource(R.drawable.bgr_locationselect_clicked);
        }else{
            if(Setbid_Popup_LocationSelect.arrayLocationList.contains(item)) {
                view2.setBackgroundResource(R.drawable.bgr_locationselect_clicked);
            }else{
                view2.setBackgroundResource(R.drawable.bgr_locationselect2);
            }

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item.getName().length() == 3){
                        Iterator<BidAreaCode.BidAreaItem> iter = Setbid_Popup_LocationSelect.arrayLocationList.iterator();
                        while(iter.hasNext()){
                            BidAreaCode.BidAreaItem addedItem = iter.next();
                            if(addedItem.getCode().substring(0, 2).equals(item.getCode().substring(0, 2))){
                                iter.remove();
                            }
                        }

                        view2.setBackgroundResource(R.drawable.bgr_locationselect_clicked);
                        Setbid_Popup_LocationSelect.arrayLocationList.add(item);
                        setClickEvent(true);
                    }else {
                        if (Setbid_Popup_LocationSelect.arrayLocationList.contains(item)) {
                            Setbid_Popup_LocationSelect.arrayLocationList.remove(item);
                            view2.setBackgroundResource(R.drawable.bgr_locationselect2);
                        } else {
                            Setbid_Popup_LocationSelect.arrayLocationList.add(item);
                            view2.setBackgroundResource(R.drawable.bgr_locationselect_clicked);
                        }
                    }
                    tv_count.setText("선택 (" + Setbid_Popup_LocationSelect.arrayLocationList.size() + ")");
                    Setbid_LVAdapter_SelectedLocation adapter_selectedLocation = new Setbid_LVAdapter_SelectedLocation(mContext, Setbid_Popup_LocationSelect.arrayLocationList);
                    lv_selectedLocation.setAdapter(adapter_selectedLocation);
                }
            });
        }

        return convertView;
    }

    private class AreaCodeClickEvent{
        View myView;
        BidAreaCode.BidAreaItem item;

        AreaCodeClickEvent(View myView, BidAreaCode.BidAreaItem item ){
            this.myView = myView;
            this.item = item;
        }

        public void setOnClickEvent(){

            myView.setBackgroundResource(R.drawable.bgr_locationselect2);
            myView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (Setbid_Popup_LocationSelect.arrayLocationList.contains(item)) {
                        Setbid_Popup_LocationSelect.arrayLocationList.remove(item);
                        myView.setBackgroundResource(R.drawable.bgr_locationselect2);
                    } else {
                        Setbid_Popup_LocationSelect.arrayLocationList.add(item);
                        myView.setBackgroundResource(R.drawable.bgr_locationselect_clicked);
                    }
                    tv_count.setText("선택 (" + Setbid_Popup_LocationSelect.arrayLocationList.size() + ")");
                    Setbid_LVAdapter_SelectedLocation adapter_selectedLocation = new Setbid_LVAdapter_SelectedLocation(mContext, Setbid_Popup_LocationSelect.arrayLocationList);
                    lv_selectedLocation.setAdapter(adapter_selectedLocation);
                }
            });
        }

        public void removeOnClickEvent(){
            myView.setBackgroundResource(R.drawable.bgr_locationselect_clicked);
            myView.setOnClickListener(null);
        }
    }

    private void setClickEvent(boolean isSet){
        for(AreaCodeClickEvent event : areaCodeClickEventArrayList){
            final AreaCodeClickEvent fEvent = event;
            if(isSet) {
                if(event.item.getName().length() == 3){
                    event.myView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Setbid_Popup_LocationSelect.arrayLocationList.remove(fEvent.item);
                            fEvent.myView.setBackgroundResource(R.drawable.bgr_locationselect2);
                            tv_count.setText("선택 (" + Setbid_Popup_LocationSelect.arrayLocationList.size() + ")");
                            Setbid_LVAdapter_SelectedLocation adapter_selectedLocation = new Setbid_LVAdapter_SelectedLocation(mContext, Setbid_Popup_LocationSelect.arrayLocationList);
                            lv_selectedLocation.setAdapter(adapter_selectedLocation);
                            setClickEvent(false);
                        }
                    });
                }else {
                    event.removeOnClickEvent();
                }
            }else{
                if(event.item.getName().length() == 3){
                    event.myView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Iterator<BidAreaCode.BidAreaItem> iter = Setbid_Popup_LocationSelect.arrayLocationList.iterator();
                            while(iter.hasNext()){
                                BidAreaCode.BidAreaItem addedItem = iter.next();
                                if(addedItem.getCode().substring(0, 2).equals(fEvent.item.getCode().substring(0, 2))){
                                    iter.remove();
                                }
                            }
                            Setbid_Popup_LocationSelect.arrayLocationList.add(fEvent.item);
                            fEvent.myView.setBackgroundResource(R.drawable.bgr_locationselect_clicked);
                            tv_count.setText("선택 (" + Setbid_Popup_LocationSelect.arrayLocationList.size() + ")");
                            Setbid_LVAdapter_SelectedLocation adapter_selectedLocation = new Setbid_LVAdapter_SelectedLocation(mContext, Setbid_Popup_LocationSelect.arrayLocationList);
                            lv_selectedLocation.setAdapter(adapter_selectedLocation);
                            setClickEvent(true);
                        }
                    });
                }else {
                    event.setOnClickEvent();
                }
            }
        }
    }

}
