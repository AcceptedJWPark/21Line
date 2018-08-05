package com.mobile.a21line.MyBid;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2018-05-10.
 */

public class MyBid_editGroupTitle_Dialog extends Dialog {

    private ImageView iv_dialog;
    private Button btn_dialog;

    Context mContext;
    EditText et_title;
    String title;
    int GCode;
    IEditDocGroupTitleDialogEventListener editDocGroupTitleEventListener;


    public MyBid_editGroupTitle_Dialog(Context context, String title, int GCode, IEditDocGroupTitleDialogEventListener eventListener) {
        super(context);
        mContext = context;
        this.title = title;
        this.GCode = GCode;
        editDocGroupTitleEventListener = eventListener;
    }

    public interface IEditDocGroupTitleDialogEventListener{
        public void editDocTitleSuccessEvent(String title);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mybid_groupedit);
        btn_dialog = findViewById(R.id.btn_save_addgroup_mybid);
        iv_dialog = findViewById(R.id.iv_cancel_addgroup_mybid);
        et_title = findViewById(R.id.et_title_addgroup_mybid);
        et_title.setText(title);

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            updateMydocGroup();
            }
        });
        iv_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public MyBid_editGroupTitle_Dialog(Context context) {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContext = context;
    }

    private void updateMydocGroup(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/updateMydocGroup.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, "그룹명이 변경되었습니다.", Toast.LENGTH_SHORT).show();
                        editDocGroupTitleEventListener.editDocTitleSuccessEvent(et_title.getText().toString());
                        dismiss();
                    }else{
                        Toast.makeText(mContext, "그룹명 변경이 실패하였습니다.", Toast.LENGTH_SHORT).show();
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("GCode", String.valueOf(GCode));
                params.put("GName", et_title.getText().toString());
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            return false;
        }
        return super.dispatchTouchEvent(ev);
    }


}
