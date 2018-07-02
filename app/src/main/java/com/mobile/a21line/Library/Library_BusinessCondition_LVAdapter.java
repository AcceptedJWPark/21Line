package com.mobile.a21line.Library;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.MyBid.MyBid_List_Activity;
import com.mobile.a21line.MyBid.MyBid_Listitem;
import com.mobile.a21line.MyBid.MyBid_editGroupTitle_Dialog;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2017-10-31.
 */

public class Library_BusinessCondition_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<Library_BusinessCondition_Listitem> arrayList;

    public Library_BusinessCondition_LVAdapter(Context mContext, ArrayList<Library_BusinessCondition_Listitem> arrayList)
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
            view = inflater.inflate(R.layout.library_businesscondition_listbg, null);
            holder = new ViewHolder();

            holder.title = (TextView) view.findViewById(R.id.tv_businesstitle_BusinessCondition);
            holder.ratio1 = (TextView) view.findViewById(R.id.tv_ratio1_BusinessCondition);
            holder.ratio2 = (TextView) view.findViewById(R.id.tv_ratio2_BusinessCondition);
            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        if(position%2==1)
        {
            view.setBackgroundResource(R.color.listview_divider1);
        }
        else
        {
            view.setBackgroundResource(R.color.listview_divider2);
        }

        holder.title.setText(arrayList.get(position).getBusinessTitle());
        holder.ratio1.setText(arrayList.get(position).getRatio1());
        holder.ratio2.setText(arrayList.get(position).getRatio2());
        return view;
    }

    static class ViewHolder
    {
        TextView title;
        TextView ratio1;
        TextView ratio2;
    }





}




