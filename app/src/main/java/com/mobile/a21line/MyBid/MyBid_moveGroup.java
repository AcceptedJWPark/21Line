package com.mobile.a21line.MyBid;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class MyBid_moveGroup extends AppCompatActivity {

    private TextView tv_title_dialog;
    private ImageView iv_dialog;
    private Button btn_dialog;
    private ListView lv_dialog;
    private ImageView iv_checked;
    private RelativeLayout ll_nogroup;

    private ArrayList<MyBid_moveGroup_ListItem> arrayList_moveGroup;
    private MyBid_moveGroup_ListItem noGroupItem;

    Context mContext;
    private MyBid_moveGroupLVAdapter adapter;
    String iBidCode;
    int position;
    int initGroup = -1;
    private Button btn_delete_dialog_movegroup;
    String Memo, Percent1, Percent2, Price;


    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mybid_movegroup_dialog);

        mContext = getApplicationContext();

        iBidCode = getIntent().getStringExtra("iBidCode");
        position = getIntent().getIntExtra("Position", -1);

        if(getIntent().hasExtra("Memo")){
            Memo = getIntent().getStringExtra("Memo");
            Percent1 = getIntent().getStringExtra("Percent1");
            Percent2 = getIntent().getStringExtra("Percent2");
            Price = getIntent().getStringExtra("Price");
        }

        iv_checked = (ImageView) findViewById(R.id.iv_movegroup_checkbox);
        ll_nogroup = (RelativeLayout) findViewById(R.id.ll_mybid_nogroup);
        ll_nogroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                noGroupItem = adapter.getNoGroupItem();
                if(noGroupItem.isChecked()){
                    adapter.setNoGroupItemChecked(false);
                    iv_checked.setImageResource(R.drawable.icon_chechbox_unchecked);
                }else{
                    adapter.removeAllChecked();
                    adapter.notifyDataSetChanged();
                    adapter.setNoGroupItemChecked(true);
                    iv_checked.setImageResource(R.drawable.icon_chechbox_checked);
                }
            }
        });

        btn_delete_dialog_movegroup = findViewById(R.id.btn_delete_dialog_movegroup);
        btn_delete_dialog_movegroup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteMydoc();
            }
        });
        if(!getIntent().getBooleanExtra("isMydoc", false)){
            btn_delete_dialog_movegroup.setVisibility(View.GONE);
        }

        tv_title_dialog = findViewById(R.id.tv_dialog_movegroup);
        btn_dialog = findViewById(R.id.btn_save_dialog_movegroup);
        lv_dialog = findViewById(R.id.lv_dialog_movegroup);
        iv_dialog = findViewById(R.id.iv_dialog_movegroup);
        tv_title_dialog.setText("내 서류함 이동");



        btn_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MyBid_moveGroup_ListItem item = null;
                noGroupItem = adapter.getNoGroupItem();
                if(noGroupItem.isChecked()){
                    item = noGroupItem;
                }else {
                    arrayList_moveGroup = adapter.getArrayList();

                    for (int i = 0; i < arrayList_moveGroup.size(); i++) {
                        if (arrayList_moveGroup.get(i).isChecked()) {
                            item = arrayList_moveGroup.get(i);
                        }
                    }
                }

                if(item != null){
                    if(initGroup == item.getGCode() && Memo == null) {
                        finish();
                    }else{
                        insertMydoc(item.getGCode());
                    }
                }else{
                    Toast.makeText(mContext, "저장할 그룹을 선택해주세요.", Toast.LENGTH_SHORT).show();
                }
            }
        });
        iv_dialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        arrayList_moveGroup = new ArrayList<>();

        getMydocGroup();

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

    private void getMydocGroup(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/getMydocList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);

                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        if(o.getInt("HAS_FLAG") > 0){
                            initGroup = o.getInt("GCode");
                        }
                        if(i == obj.length() - 1){
                            noGroupItem = new MyBid_moveGroup_ListItem("그룹 없음", o.getInt("HAS_FLAG") > 0, 0);
                        }else{
                            arrayList_moveGroup.add(new MyBid_moveGroup_ListItem(o.getString("GName"), o.getInt("HAS_FLAG") > 0, o.getInt("GCode")));
                        }
                    }

                    adapter = new MyBid_moveGroupLVAdapter(mContext,arrayList_moveGroup, noGroupItem, iv_checked);
                    lv_dialog.setAdapter(adapter);
                    ((TextView)findViewById(R.id.tv_movegroup_groupcount)).setText("(" + arrayList_moveGroup.size() + ")");

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
                        //addDocListener.addDocSuccessEvent();
                        if(position >= 0) {
                            if(Memo != null){
                                AddMemoEvent.getInstance().post(new AddMemoFlag(position, true, true));
                                Toast.makeText(mContext, "메모 저장 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            }else {
                                AddMemoEvent.getInstance().post(new AddMemoFlag(position, false, true));
                                Toast.makeText(mContext, "내서류함 저장 성공하였습니다.", Toast.LENGTH_SHORT).show();
                            }
                        }
                        Intent i = new Intent();
                        i.putExtra("Position", position);
                        setResult(RESULT_OK, i);
                        finish();
                    }else{
                        if(Memo != null){
                            Toast.makeText(mContext, "메모 저장 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(mContext, "내서류함 저장 실패하였습니다.", Toast.LENGTH_SHORT).show();
                        }
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
                if(Memo != null){
                    params.put("Memo", Memo);
                    params.put("Price", Price.replaceAll(",", ""));
                    params.put("Percent1", Percent1);
                    params.put("Percent2", Percent2);
                }
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }

    private void deleteMydoc(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mydoc/deleteMydoc.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        //addDocListener.addDocSuccessEvent();
                        Toast.makeText(mContext, "내서류함에서 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                        Intent i = new Intent();
                        i.putExtra("Position", position);
                        i.putExtra("isDelete", true);
                        setResult(RESULT_OK, i);
                        finish();
                    }else{
                        Toast.makeText(mContext, "삭제 실패하였습니다.", Toast.LENGTH_SHORT).show();
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
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }

}

