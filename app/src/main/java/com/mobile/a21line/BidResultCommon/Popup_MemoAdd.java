package com.mobile.a21line.BidResultCommon;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.Selection;
import android.text.TextWatcher;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
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
import com.mobile.a21line.AddMemoEvent;
import com.mobile.a21line.AddMemoFlag;
import com.mobile.a21line.BidAreaCode;
import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.math.BigDecimal;
import java.text.DecimalFormat;
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
    EditText et_price_memo;
    EditText et_percent1_memo;
    EditText et_percent2_memo;
    TextView tv_memo_analysis;
    Button btn_save;
    ImageView iv_close;
    String iBidCode;
    int position;
    boolean isAnal;
    boolean isAnalList;

    String basicprice = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.memoadd);
        mContext = getApplicationContext();

        iBidCode = getIntent().getStringExtra("iBidCode");
        position = getIntent().getIntExtra("position", -1);
        isAnal = getIntent().hasExtra("isAnal");
        isAnalList = getIntent().hasExtra("isAnalList");
        tv_delete = findViewById(R.id.tv_delete_memoadd);
        et_memo = findViewById(R.id.et_memoadd);
        et_price_memo = findViewById(R.id.et_price_memo);
        et_percent1_memo = findViewById(R.id.et_percent1_memo);
        et_percent2_memo = findViewById(R.id.et_percent2_memo);

        tv_memo_analysis = findViewById(R.id.tv_memoadd_analysis);
        btn_save = findViewById(R.id.btn_save_memoadd);

        tv_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_memo.setText("");
                et_price_memo.setText("");
                et_percent1_memo.setText("");
                et_percent2_memo.setText("");
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

        if (isAnalList) {
            et_memo.setVisibility(View.GONE);
            tv_memo_analysis.setVisibility(View.VISIBLE);
            tv_delete.setVisibility(View.GONE);
            btn_save.setVisibility(View.GONE);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
            ((TextView) findViewById(R.id.tv_memotitle_analysis)).setText("담당자 메모");
            tv_memo_analysis.setText(getIntent().getStringExtra("Memo"));
            et_price_memo.setVisibility(View.GONE);
            et_percent1_memo.setVisibility(View.GONE);
            et_percent2_memo.setVisibility(View.GONE);


        } else {
            if (isAnal) {
                et_price_memo.setVisibility(View.GONE);
                et_percent1_memo.setVisibility(View.GONE);
                et_percent2_memo.setVisibility(View.GONE);
            }
            getMemo();
            et_memo.setVisibility(View.VISIBLE);
            tv_memo_analysis.setVisibility(View.GONE);
            tv_delete.setVisibility(View.VISIBLE);
            btn_save.setVisibility(View.VISIBLE);
            getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
            ((TextView) findViewById(R.id.tv_memotitle_analysis)).setText("메모 달기");
        }

        et_price_memo.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty()){
                    et_price_memo.setText("0");
                    Selection.setSelection(et_price_memo.getText(), 1);
                    return;
                }
                else {
                    if (!basicprice.equals(charSequence.toString())) {
                        basicprice = toNumFormat(charSequence.toString().replace(",", ""));
                        et_price_memo.setText(basicprice);
                        Selection.setSelection(et_price_memo.getText(), et_price_memo.getText().length());
                    }
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });





    }

    private String toNumFormat(String data){
        DecimalFormat df = new DecimalFormat("#,###");
        BigDecimal bd = new BigDecimal(data);
        return df.format(bd);
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
                    et_memo.setText(obj.optString("Memo", ""));
                    if(!isAnal) {
                        et_price_memo.setText(obj.optString("SelectMoney", ""));
                        et_percent1_memo.setText(obj.optString("RatioPercent", ""));
                        et_percent2_memo.setText(obj.optString("ResultPercent", ""));
                    }
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
        if((et_memo.getText().toString().isEmpty() && et_price_memo.getText().toString().equals("0.0") && et_percent1_memo.getText().toString().equals("0.0") && et_percent2_memo.getText().toString().equals("0.0")) && !isDel){
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
                        if(et_memo.getText().toString().isEmpty() && et_price_memo.getText().toString().isEmpty() && et_percent1_memo.getText().toString().isEmpty() && et_percent2_memo.getText().toString().isEmpty()){
                            if(!isAnal){
                                AddMemoEvent.getInstance().post(new AddMemoFlag(position, false, false));
                            }else{
                                AddMemoEvent.getInstance().post(new AddMemoFlag(position, false, false));
                            }
                            Toast.makeText(mContext, "메모가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        }else {
                            if(isAnal){
                                AddMemoFlag flag = new AddMemoFlag(position, true, false);
                                flag.setMemo(et_memo.getText().toString());
                                AddMemoEvent.getInstance().post(flag);
                                Toast.makeText(mContext, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
                            }else {
                                AddMemoEvent.getInstance().post(new AddMemoFlag(position, true, false));
                                Toast.makeText(mContext, "메모가 저장되었습니다", Toast.LENGTH_SHORT).show();
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
                params.put("Price", et_price_memo.getText().toString());
                params.put("Percent1", et_percent1_memo.getText().toString());
                params.put("Percent2", et_percent2_memo.getText().toString());
                params.put("Memo", et_memo.getText().toString());
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }




}


