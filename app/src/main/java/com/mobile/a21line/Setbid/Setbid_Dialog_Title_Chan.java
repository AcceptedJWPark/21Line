package com.mobile.a21line.Setbid;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.mobile.a21line.BinaryCode;
import com.mobile.a21line.R;

import java.util.ArrayList;

/**
 * Created by Accepted on 2018-05-10.
 */

public class Setbid_Dialog_Title_Chan extends Dialog {

    private ImageView iv_dialog;
    private Button btn_dialog;

    Context mContext;
    EditText et_title;
    String title;

    public interface ISetbidDialogEventListener{
        public void customDialogEvent(String returnValue);
    }

    private ISetbidDialogEventListener onSetbidDialogEventListener;

    public Setbid_Dialog_Title_Chan(Context context, String title, ISetbidDialogEventListener onSetbidDialogEventListener){
        super(context);
        mContext = context;
        this.title = title;
        this.onSetbidDialogEventListener = onSetbidDialogEventListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.setbid_titlechange);


        btn_dialog = findViewById(R.id.btn_dialog_titleChange_setbid);
        iv_dialog = findViewById(R.id.iv_dialog_titleChange_setbid);
        et_title = findViewById(R.id.et_chg_setbid_title);
        et_title.setText(title);

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onSetbidDialogEventListener.customDialogEvent(et_title.getText().toString());
                Toast.makeText(mContext,"설정명이 변경되었습니다.",Toast.LENGTH_SHORT).show();
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

    public Setbid_Dialog_Title_Chan(Context context)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContext = context;
    }



}
