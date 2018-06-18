package com.mobile.a21line.MyBid;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;

/**
 * Created by Accepted on 2018-05-14.
 */

public class MyBid_Activity extends AppCompatActivity {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;

    View footer;


    ListView lv_bidgroup;
    MyBid_LVAdapter adapter;

    ArrayList<MyBid_Listitem> arrayList;


    ImageView iv_addMybid;
    LinearLayout ll_mybid_nogroup;

    MyBid_addGroup_Dialog addGroup;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.mybid_activity);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("내 서류함");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setText("편집");

        ((ImageView)findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

        final View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, MyBid_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(MyBid_Activity.this, mClicklistener);

        ll_mybid_nogroup = (LinearLayout)findViewById(R.id.ll_mybid_nogroup);

        arrayList = new ArrayList<MyBid_Listitem>();
        adapter = new MyBid_LVAdapter(MyBid_Activity.this, arrayList);
        lv_bidgroup = findViewById(R.id.lv_bidgroup_mybid);

        iv_addMybid = findViewById(R.id.iv_addmybid_mybid);
        iv_addMybid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(arrayList.size() == 10){
                    Toast.makeText(mContext, "내 서류함 그룹은 최대 10개까지 생성하실 수 있습니다.", Toast.LENGTH_SHORT).show();
                }else {
                    addGroup = new MyBid_addGroup_Dialog(MyBid_Activity.this, "그룹명 " + (arrayList.size() + 1), new MyBid_addGroup_Dialog.IAddDocGroupDialogEventListener() {
                        @Override
                        public void addDocSuccessEvent(MyBid_Listitem item) {
                            arrayList.add(item);
                            adapter.notifyDataSetChanged();
                        }
                    });
                    addGroup.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
                    addGroup.getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND, WindowManager.LayoutParams.FLAG_DIM_BEHIND);
                    addGroup.getWindow().setDimAmount(0.6f);
                    addGroup.show();

                    lv_bidgroup.setAdapter(adapter);
                    lv_bidgroup.setSelection(lv_bidgroup.getAdapter().getCount());
                }
            }
        });

        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(adapter.isModify()){
                    ll_mybid_nogroup.setVisibility(View.VISIBLE);
                }else{
                    ll_mybid_nogroup.setVisibility(View.GONE);
                }
                adapter.modifyGroup();
            }
        });
        footer= getLayoutInflater().inflate(R.layout.mybid_footer,null,false);
        lv_bidgroup.addFooterView(footer);
        lv_bidgroup.setOverscrollFooter(new ColorDrawable(Color.TRANSPARENT));


        getMydocGroup();
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
                        arrayList.add(new MyBid_Listitem(o.getString("GName"), o.getString("BID_CNT") + "건", o.getInt("GCode"), parseDateTimeToDate(o.getString("RegDate"), false)));
                    }

                    lv_bidgroup.setAdapter(adapter);
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
                return params;
            }
        };
        postRequestQueue.add(postJsonRequest);
    }

    private String parseDateTimeToDate(String dateTime, boolean isToServer){
        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");

        Date date = new Date(Long.parseLong(dateTime));
        if(isToServer) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            sdf.setTimeZone(time);
            return sdf.format(date);
        }else{
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            sdf.setTimeZone(time);
            return sdf.format(date);
        }
    }

}
