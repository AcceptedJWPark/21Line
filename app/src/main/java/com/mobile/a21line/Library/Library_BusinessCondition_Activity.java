package com.mobile.a21line.Library;

import android.content.Context;
import android.content.Intent;
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
import com.mobile.a21line.MyBid.MyBid_LVAdapter;
import com.mobile.a21line.MyBid.MyBid_List_Activity;
import com.mobile.a21line.MyBid.MyBid_Listitem;
import com.mobile.a21line.MyBid.MyBid_addGroup_Dialog;
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

public class Library_BusinessCondition_Activity extends AppCompatActivity {

    Context mContext;
    DrawerLayout drawerLayout;
    View frameLayout;


    ListView lv_businesscondition;
    Library_BusinessCondition_LVAdapter adapter;
    ArrayList<Library_BusinessCondition_Listitem> arrayList;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.library_businesscondition);
        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("업종별 경영상태 평균비율");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ((ImageView)findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);

        drawerLayout = findViewById(R.id.dl_mybid);
        frameLayout = findViewById(R.id.fl_drawerView_mybid);

        final View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Library_BusinessCondition_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Library_BusinessCondition_Activity.this, mClicklistener);

        lv_businesscondition = findViewById(R.id.lv_inc3);

        arrayList = new ArrayList<>();
        adapter = new Library_BusinessCondition_LVAdapter(mContext,arrayList);

        arrayList.add(new Library_BusinessCondition_Listitem("실내건축","80.10%","148.98%"));
        arrayList.add(new Library_BusinessCondition_Listitem("토공","82.89%","158.43%"));
        arrayList.add(new Library_BusinessCondition_Listitem("습식·방수","83.31%","108.28%"));
        arrayList.add(new Library_BusinessCondition_Listitem("석공","59.75%","244.48%"));
        arrayList.add(new Library_BusinessCondition_Listitem("도장","70.50%","118.92%"));
        arrayList.add(new Library_BusinessCondition_Listitem("비계구조물해체","81.65%","120.71%"));
        arrayList.add(new Library_BusinessCondition_Listitem("금속구조물창호","80.33%","145.46%"));
        arrayList.add(new Library_BusinessCondition_Listitem("지붕판금건축물조립","90.28%","93.58%"));
        arrayList.add(new Library_BusinessCondition_Listitem("철근·콘크리트","77.46%","168.26%"));
        arrayList.add(new Library_BusinessCondition_Listitem("상·하수도","73.02%","176.76%"));
        arrayList.add(new Library_BusinessCondition_Listitem("보링","74.80%","188.23%"));
        arrayList.add(new Library_BusinessCondition_Listitem("철도·궤도","58.05%","173.02%"));
        arrayList.add(new Library_BusinessCondition_Listitem("포장","62.10%","226.20%"));
        arrayList.add(new Library_BusinessCondition_Listitem("수중","73.62%","142.29%"));
        arrayList.add(new Library_BusinessCondition_Listitem("조경식재","93.92%","120.70%"));
        arrayList.add(new Library_BusinessCondition_Listitem("조경시설","79.02%","111.45%"));
        arrayList.add(new Library_BusinessCondition_Listitem("강구조물","111.99%","117.73%"));
        arrayList.add(new Library_BusinessCondition_Listitem("철강재","140.91%","113.53%"));
        arrayList.add(new Library_BusinessCondition_Listitem("삭도","124.86%","62.69%"));
        arrayList.add(new Library_BusinessCondition_Listitem("준설","180.38%","120.32%"));
        arrayList.add(new Library_BusinessCondition_Listitem("승강기","86.08%","110.72%"));

        lv_businesscondition.setAdapter(adapter);


    }

}
