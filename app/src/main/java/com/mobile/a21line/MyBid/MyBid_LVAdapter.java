package com.mobile.a21line.MyBid;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.content.Intent;
import android.view.ContextThemeWrapper;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2017-10-31.
 */

public class MyBid_LVAdapter extends BaseAdapter {

    Context mContext;
    private ArrayList<MyBid_Listitem> arrayList;
    private boolean isModify = false;
    private LinearLayout ll_mybid_nogroup;
    MyBid_editGroupTitle_Dialog changeTitle_Dialog;

    public MyBid_LVAdapter(Context mContext, ArrayList<MyBid_Listitem> arrayList)
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
            view = inflater.inflate(R.layout.mybid_bg, null);
            holder = new ViewHolder();

            holder.groupname = (TextView) view.findViewById(R.id.tv_groupname_mybid);
            holder.count = (TextView) view.findViewById(R.id.tv_count_mybid);
            holder.regDate = (TextView) view.findViewById(R.id.tv_groupdate_mybid);
            holder.groupModify = (ImageView) view.findViewById(R.id.iv_groupmodify_edit_mybid);
            holder.groupDelete = (ImageView) view.findViewById(R.id.iv_groupdelete_edit_mybid);
            view.setTag(holder);
        }
        else
        {
            holder= (ViewHolder) view.getTag();
        }

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext,MyBid_List_Activity.class);
                mContext.startActivity(intent);
            }
        });

        changeTitle_Dialog = new MyBid_editGroupTitle_Dialog(mContext,"그룹명");
        final AlertDialog.Builder deleteDialog = new AlertDialog.Builder(new ContextThemeWrapper(mContext,R.style.myDialog));

        if(isModify){
            holder.count.setVisibility(View.GONE);

            holder.groupModify.setVisibility(View.VISIBLE);
            holder.groupModify.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    changeTitle_Dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    changeTitle_Dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    changeTitle_Dialog.getWindow().setDimAmount(0.6f);
                    changeTitle_Dialog.show();
                }
            });

            holder.groupDelete.setVisibility(View.VISIBLE);
            holder.groupDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    float textSize = mContext.getResources().getDimension(R.dimen.dialogTxt);
                    String positive = "삭제";
                    deleteDialog.setMessage("저장된 그룹 내 모든 공고가 삭제됩니다.")
                            .setPositiveButton(positive, new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    Toast.makeText(mContext,"그룹이 삭제되었습니다.",Toast.LENGTH_SHORT).show();
                                    dialog.cancel();
                                }
                            })
                            .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    AlertDialog alertDialog = deleteDialog.create();
                    alertDialog.show();
                    alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setAllCaps(false);
                    alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                    alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setAllCaps(false);
                    alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                    alertDialog.show();
                    TextView msgView = (TextView) alertDialog.findViewById(android.R.id.message);
                    msgView.setTextSize(textSize);




                }
            });
        }else{

            holder.count.setVisibility(View.VISIBLE);
            holder.groupModify.setVisibility(View.GONE);
            holder.groupDelete.setVisibility(View.GONE);
        }

        holder.groupname.setText(arrayList.get(position).getGroupTitle());
        holder.count.setText(arrayList.get(position).getBidCount());
        holder.regDate.setText("등록일 : " + arrayList.get(position).getRegDate());
        return view;
    }

    static class ViewHolder
    {
        TextView groupname;
        TextView count;
        TextView regDate;
        ImageView groupModify;
        ImageView groupDelete;
    }

    public void modifyGroup(){
        if(isModify){
            isModify = false;
        }
        else{
            isModify = true;
        }
        notifyDataSetChanged();
    }

    public boolean isModify(){
        return isModify;
    }


}




