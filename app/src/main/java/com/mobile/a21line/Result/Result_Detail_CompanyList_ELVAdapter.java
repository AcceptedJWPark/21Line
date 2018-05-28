package com.mobile.a21line.Result;

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
import com.mobile.a21line.Setbid.Setbid_Activity;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Result_Detail_CompanyList_ELVAdapter extends BaseExpandableListAdapter {


    private Context context;
    private ArrayList<Result_Detail_CompanyList_Parent_Listitem> arrayParent;
    private HashMap <Result_Detail_CompanyList_Parent_Listitem, ArrayList<Result_Detail_CompanyList_Child_Listitem>> arrayChild;

    public Result_Detail_CompanyList_ELVAdapter(Context context, ArrayList<Result_Detail_CompanyList_Parent_Listitem> arrayParent, HashMap<Result_Detail_CompanyList_Parent_Listitem,ArrayList<Result_Detail_CompanyList_Child_Listitem>> arrayChild)
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
        Result_Detail_CompanyList_Parent_Listitem item = arrayParent.get(groupPosition);
        View v = convertView;

        if(v==null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.result_detail_companylist_parent_bg, parent,false);
        }

        if(groupPosition%2==1)
        {
            v.setBackgroundResource(R.color.listview_devider2);
        }
        else
        {
            v.setBackgroundResource(R.color.listview_devider1);
        }

        TextView tv_Num_Parent = (TextView) v.findViewById(R.id.tv_result_detail_companylist_num_parent);
        TextView tv_Company_Parent = (TextView) v.findViewById(R.id.tv_result_detail_companylist_company_parent);
        TextView tv_Price_Parent = (TextView) v.findViewById(R.id.tv_result_detail_companylist_price_parent);
        TextView tv_Percent_Parent = (TextView) v.findViewById(R.id.tv_result_detail_companylist_percent_parent);

        tv_Num_Parent.setText(item.getNum());
        tv_Company_Parent.setText(item.getCompany());
        tv_Price_Parent.setText(item.getPrice());
        tv_Percent_Parent.setText(item.getPercent());

        return v;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final Result_Detail_CompanyList_Child_Listitem item = arrayChild.get(arrayParent.get(groupPosition)).get(childPosition);
        View v = convertView;

        if(v==null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v=(LinearLayout) inflater.inflate(R.layout.result_detail_companylist_child_bg, null);
        }


        TextView tv_Company_Child = (TextView) v.findViewById(R.id.tv_result_detail_companylist_company_child);
        TextView tv_CompanyNo_Child = (TextView) v.findViewById(R.id.tv_result_detail_companylist_companyNo_child);
        TextView tv_Ceo_Child = (TextView) v.findViewById(R.id.tv_result_detail_companylist_ceo_child);
        TextView tv_Phone_Child = (TextView) v.findViewById(R.id.tv_result_detail_companylist_phone_child);
        TextView tv_Address_Child = (TextView) v.findViewById(R.id.tv_result_detail_companylist_address_child);

        tv_Company_Child.setText(item.getCompany());
        tv_CompanyNo_Child.setText(item.getCompanyNo());
        tv_Ceo_Child.setText(item.getCeo());
        tv_Phone_Child.setText(item.getPhone());
        tv_Address_Child.setText(item.getAddress());


        return v;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return false;
    }
}




