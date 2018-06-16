package com.mobile.a21line.MyBid;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.mobile.a21line.R;

/**
 * Created by Accepted on 2018-05-10.
 */

public class MyBid_addGroup extends Dialog {

    private ImageView iv_dialog;
    private Button btn_dialog;

    Context mContext;
    EditText et_title;
    String title;

    public interface ISetbidDialogEventListener{
        public void customDialogEvent(String returnValue);
    }


    public MyBid_addGroup(Context context, String title){
        super(context);
        mContext = context;
        this.title = title;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.mybid_addgroup);


        btn_dialog = findViewById(R.id.btn_save_addgroup_mybid);
        iv_dialog = findViewById(R.id.iv_cancel_addgroup_mybid);
        et_title = findViewById(R.id.et_title_addgroup_mybid);
        et_title.setText(title);

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"그룹이 추가되었습니다.",Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });
        iv_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public MyBid_addGroup(Context context)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContext = context;
    }



}
