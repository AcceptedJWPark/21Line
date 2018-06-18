package com.mobile.a21line.MyBid;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Accepted on 2018-05-10.
 */

public class MyBid_moveGroup_Dialog extends Dialog {

    private TextView tv_title_dialog;
    private ImageView iv_dialog;
    private Button btn_dialog;
    private ListView lv_dialog;
    private String mTitle;
    private ArrayList<MyBid_moveGroup_ListItem> mArrayList;
    private String iBidCode;

    Context mContext;

    private MyBid_moveGroupLVAdapter adapter;

    IAddDocDialogEventListener addDocListener;


    public interface IAddDocDialogEventListener{
        public void addDocSuccessEvent();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);

        WindowManager.LayoutParams lpWindow = new WindowManager.LayoutParams();
        lpWindow.flags = WindowManager.LayoutParams.FLAG_DIM_BEHIND;
        lpWindow.dimAmount = 0.6f;
        getWindow().setAttributes(lpWindow);

        setContentView(R.layout.mybid_movegroup_dialog);


        tv_title_dialog = findViewById(R.id.tv_dialog_movegroup);
        btn_dialog = findViewById(R.id.btn_dialog_movegroup);
        lv_dialog = findViewById(R.id.lv_dialog_movegroup);
        iv_dialog = findViewById(R.id.iv_dialog_movegroup);
        tv_title_dialog.setText(mTitle);

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mArrayList = adapter.getArrayList();
                MyBid_moveGroup_ListItem item = null;
                for(int i = 0; i < mArrayList.size(); i++){
                    if(mArrayList.get(i).isChecked()){
                        item = mArrayList.get(i);
                    }
                }

                if(item != null){
                    insertMydoc(item.getGCode());
                }
            }
        });
        iv_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        getMydocGroup();
    }

    public MyBid_moveGroup_Dialog(Context context, String title, String iBidCode, IAddDocDialogEventListener eventListener)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContext = context;
        this.mTitle = title;
        this.iBidCode = iBidCode;
        mArrayList = new ArrayList();
        this.addDocListener = eventListener;
    }

    private void getMydocGroup(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/getMydocList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);

                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        mArrayList.add(new MyBid_moveGroup_ListItem(o.getString("GName"), o.getInt("HAS_FLAG") > 0, o.getInt("GCode")));
                    }

                    adapter = new MyBid_moveGroupLVAdapter(mContext,mArrayList);
                    lv_dialog.setAdapter(adapter);

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                String[] bidCodes = iBidCode.split("-");
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("BidNo", bidCodes[0]);
                params.put("BidNoSeq", bidCodes[1]);
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }

    private void insertMydoc(final int GCode){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/insertMydoc.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        addDocListener.addDocSuccessEvent();
                        Toast.makeText(mContext, "내서류함 저장 성공하였습니다.", Toast.LENGTH_SHORT).show();
                        dismiss();
                    }else{
                        Toast.makeText(mContext, "내서류함 저장 실패하였습니다.", Toast.LENGTH_SHORT).show();
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
                String[] bidCodes = iBidCode.split("-");
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("BidNo", bidCodes[0]);
                params.put("BidNoSeq", bidCodes[1]);
                params.put("GCode", String.valueOf(GCode));
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }



}
