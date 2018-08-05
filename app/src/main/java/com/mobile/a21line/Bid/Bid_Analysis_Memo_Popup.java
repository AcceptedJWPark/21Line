package com.mobile.a21line.Bid;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.AddMemoEvent;
import com.mobile.a21line.AddMemoFlag;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2018-06-08.
 */

public class Bid_Analysis_Memo_Popup extends AppCompatActivity {

    Context mContext;
    ImageView iv_close;
    EditText et_memo;
    Button btn_save;
    String iBidCode;
    int position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.bid_analysis_detail_memo);
        mContext = getApplicationContext();
        iBidCode = getIntent().getStringExtra("iBidCode");
        position = getIntent().getIntExtra("position", -1);
        et_memo = findViewById(R.id.et_analysis_memo);
        btn_save = findViewById(R.id.btn_save_analysis_memo);



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
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);

        iv_close = findViewById(R.id.iv_close_memoadd);
        iv_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


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

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/getAnalMemo.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    if(response == null){
                        return;
                    }
                    JSONObject obj = new JSONObject(response);
                    et_memo.setText(obj.optString("Memo", ""));

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

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/setAnalMemo.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    if(response == null){
                        return;
                    }
                    JSONObject obj = new JSONObject(response);

                    if(obj.getString("result").equals("success")){
                        if(et_memo.getText().toString().isEmpty()){

//                            AddMemoEvent.getInstance().post(new AddMemoFlag(position, false));
                            Toast.makeText(mContext, "메모가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }else {
//                            AddMemoFlag flag = new AddMemoFlag(position, true);
//                            flag.setMemo(et_memo.getText().toString());
//                            AddMemoEvent.getInstance().post(flag);
                            Toast.makeText(mContext, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();

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


