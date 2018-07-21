package com.mobile.a21line.BidResultCommon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.BidAreaCode;
import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2018-06-08.
 */

public class Popup_MemoAdd extends AppCompatActivity {

    Context mContext;
    TextView tv_delete;
    EditText et_memo;
    Button btn_save;
    ImageView iv_close;
    String iBidCode;
    boolean isAnal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.memoadd);
        mContext = getApplicationContext();

        iBidCode = getIntent().getStringExtra("iBidCode");
        isAnal = getIntent().hasExtra("isAnal");
        tv_delete = findViewById(R.id.tv_delete_memoadd);
        et_memo = findViewById(R.id.et_memoadd);
        btn_save = findViewById(R.id.btn_save_memoadd);

        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_memo.setText("");
                saveMemo(true);
            }
        });

        iv_close = findViewById(R.id.iv_close_memoadd);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveMemo(false);
            }
        });

        getMemo();

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

    public void getMemo(){
        String url = "";
        if(isAnal){
            url = SaveSharedPreference.getServerIp() + "Mydoc/getAnalMemo.do";
        }else{
            url = SaveSharedPreference.getServerIp() + "Mydoc/getMemo.do";
        }
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    if(response == null){
                        return;
                    }
                    JSONObject obj = new JSONObject(response);
                    et_memo.setText(obj.getString("Memo"));
                    tv_delete.setVisibility(View.VISIBLE);

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                String[] iBidCodes = iBidCode.split("-");
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("BidNo", iBidCodes[0]);
                params.put("BidNoSeq", iBidCodes[1]);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void saveMemo(boolean isDel){
        if(et_memo.getText().toString().isEmpty() && !isDel){
            Toast.makeText(mContext, "메모 내용을 적어주세요.", Toast.LENGTH_SHORT).show();
            return;
        }

        String url = "";
        if(isAnal){
            url = SaveSharedPreference.getServerIp() + "Mydoc/setAnalMemo.do";
        }else{
            url = SaveSharedPreference.getServerIp() + "Mydoc/insertMydoc.do";
        }

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    if(response == null){
                        return;
                    }
                    JSONObject obj = new JSONObject(response);

                    if(obj.getString("result").equals("success")){
                        if(et_memo.getText().toString().isEmpty()){
                            Toast.makeText(mContext, "메모가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }else {
                            if(isAnal){
                                Toast.makeText(mContext, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                            }else {
                                Toast.makeText(mContext, "메모한 공고는 내 서류함 저장 안 되어있을 경우 미분류 그룹에 저장됩니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        finish();
                    }else{
                        Toast.makeText(mContext, "저장 실패하였습니다.", Toast.LENGTH_SHORT).show();
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
                String[] iBidCodes = iBidCode.split("-");
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("BidNo", iBidCodes[0]);
                params.put("BidNoSeq", iBidCodes[1]);
                params.put("Memo", et_memo.getText().toString());
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }




}


