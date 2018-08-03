package com.mobile.a21line.MyBid;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Accepted on 2018-05-10.
 */

public class MyBid_addGroup_Dialog extends Dialog {

    private ImageView iv_dialog;
    private Button btn_dialog;

    Context mContext;
    EditText et_title;
    String title;
    int gcode;
    IAddDocGroupDialogEventListener addDocListener;


    public interface IAddDocGroupDialogEventListener{
        public void addDocSuccessEvent(MyBid_Listitem item);
    }


    public MyBid_addGroup_Dialog(Context context, String title, IAddDocGroupDialogEventListener eventListener){
        super(context);
        mContext = context;
        this.title = title;
        this.addDocListener = eventListener;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


        setContentView(R.layout.mybid_groupadd);


        btn_dialog = findViewById(R.id.btn_save_addgroup_mybid);
        iv_dialog = findViewById(R.id.iv_cancel_addgroup_mybid);
        et_title = findViewById(R.id.et_title_addgroup_mybid);
        et_title.setText(title);

        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addNewDocGroup();
            }
        });
        iv_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

    }

    public MyBid_addGroup_Dialog(Context context)
    {
        super(context, android.R.style.Theme_Translucent_NoTitleBar);
        this.mContext = context;
    }

    private void addNewDocGroup(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/insertMydocGroup.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")) {
                        Date now  = new Date();
                        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        sdf.setTimeZone(time);

                        Toast.makeText(mContext, "그룹 생성이 완료되었습니다.", Toast.LENGTH_SHORT).show();
                        gcode = obj.getInt("GCode");
                        addDocListener.addDocSuccessEvent(new MyBid_Listitem(et_title.getText().toString(), "전체 0건", gcode, sdf.format(now)));
                        dismiss();
                    }else{
                        Toast.makeText(mContext, "동일한 그룹명을 사용할 수 없습니다.", Toast.LENGTH_SHORT).show();
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
                params.put("GName", et_title.getText().toString());
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }



}
