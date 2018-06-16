package com.mobile.a21line.Setbid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Setbid_BusinessSelect_ELVAdapter extends BaseExpandableListAdapter {


    private Context context;
    private ArrayList<BidUpCode.BidUpCodeItem> arrayParent;
    private HashMap <BidUpCode.BidUpCodeItem, ArrayList<BidUpCode.BidUpCodeItem>> arrayChild;

    public Setbid_BusinessSelect_ELVAdapter(Context context, ArrayList<BidUpCode.BidUpCodeItem> arrayParent, HashMap<BidUpCode.BidUpCodeItem,ArrayList<BidUpCode.BidUpCodeItem>> arrayChild)
    {
        super();
        this.context = context;
        this.arrayParent = arrayParent;
        this.arrayChild = arrayChild;
    }


    @Override
    public int getGroupCount() {
        return arrayParent.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return arrayChild.get(arrayParent.get(groupPosition)).size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return arrayParent.get(groupPosition);
    }


    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return arrayChild.get(groupPosition).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }


    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        BidUpCode.BidUpCodeItem item = arrayParent.get(groupPosition);
        String groupName = item.getName();
        View v = convertView;

        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.setbid_businessselect_parentbg, parent,false);


        }
        TextView tv_Parent = (TextView) v.findViewById(R.id.tv_businessSelect_Parent);
        tv_Parent.setText(groupName);
        return v;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final BidUpCode.BidUpCodeItem item = arrayChild.get(arrayParent.get(groupPosition)).get(childPosition);
        String childName = item.getName();
        View v = convertView;

        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.setbid_businessselect_childbg, null);
        }
        if(childPosition%2==1)
        {
            v.setBackgroundResource(R.color.listview_divider1);
        }
        else
        {
            v.setBackgroundResource(R.color.listview_divider2);
        }

        if(Setbid_Popup_BusinessSelect.arrayUpcodeList.contains(item)){
            ((ImageView) v.findViewById(R.id.iv_businessSelect_Child)).setVisibility(View.VISIBLE);
        }else{
            ((ImageView) v.findViewById(R.id.iv_businessSelect_Child)).setVisibility(View.GONE);
        }

        v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v.findViewById(R.id.iv_businessSelect_Child).getVisibility()==View.GONE) {
                    Setbid_Popup_BusinessSelect.arrayUpcodeList.add(item);
                    ((ImageView) v.findViewById(R.id.iv_businessSelect_Child)).setVisibility(View.VISIBLE);
                }
                else{
                    Setbid_Popup_BusinessSelect.arrayUpcodeList.remove(item);
                    ((ImageView)v.findViewById(R.id.iv_businessSelect_Child)).setVisibility(View.GONE);
                }

            }
        });
        TextView tv_Child = (TextView) v.findViewById(R.id.tv_businessSelect_Child);
        tv_Child.setText(childName);
        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}




