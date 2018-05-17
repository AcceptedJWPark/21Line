package com.mobile.a21line.Setbid;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.BidAreaCode;
import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.BinaryCode;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;


public class Setbid_Activity extends AppCompatActivity {

    private Context mContext;
    private String[] priceType = {"추정금액","기초금액"};

    private JSONObject groupData;
    private String GCode;

    TextView btn_businessselect;
    TextView btn_locationselect;

    private Setbid_LVAdapter adapter;

    EditText et_price1;
    EditText et_price2;

    Button btn_price1;
    Button btn_price2;
    Button btn_price3;
    Button btn_price4;
    Button btn_price5;
    Button btn_save;

    Button btn_bidType;
    Button btn_exception;
    Button btn_etc;

    Spinner spn_pricetype;

    View frameLayout;
    DrawerLayout drawerLayout;

    ListView lv_business;
    ListView lv_location;

    Setbid_Dialog dialog1;
    Setbid_Dialog dialog2;
    Setbid_Dialog dialog3;

    public static ArrayList<BidAreaCode.BidAreaItem> arrayList_location = new ArrayList<>();
    public static ArrayList<BidUpCode.BidUpCodeItem> arrayList_business = new ArrayList<>();

    public static ArrayList<BinaryCode> arrayList1 = new ArrayList<>();
    public static ArrayList<BinaryCode> arrayList2 = new ArrayList<>();
    public static ArrayList<BinaryCode> arrayList3 = new ArrayList<>();
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.setbid_activity);

        mContext = getApplicationContext();

        arrayList_location = new ArrayList<>();
        arrayList_business = new ArrayList<>();
        if(getIntent().getBooleanExtra("isAdded", false)){
            try {
                groupData = new JSONObject(getIntent().getStringExtra("groupData"));
                GCode = groupData.getString("GCode");
                getAreaCode();
                getUpcode();
                ((EditText) findViewById(R.id.et_price1_setbid)).setText(groupData.getString("EMoney"));
                ((EditText) findViewById(R.id.et_price2_setbid)).setText(groupData.getString("SMoney"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            GCode = "new";
        }

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("맞춤설정 1");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);

        drawerLayout = findViewById(R.id.dl_home);
        frameLayout = findViewById(R.id.fl_drawerView_home);

        View.OnClickListener mClicklistener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DrawerLayout_Open(v, Setbid_Activity.this, drawerLayout, frameLayout);
            }
        };
        DrawerLayout_ClickEvent(Setbid_Activity.this, mClicklistener);


        btn_locationselect = findViewById(R.id.tv_locationselect_Setbid);
        btn_locationselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, Setbid_Popup_LocationSelect.class);
                startActivity(i);
            }
        });

        btn_businessselect = findViewById(R.id.tv_businessselect_Setbid);
        btn_businessselect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(mContext, Setbid_Popup_BusinessSelect.class);
                startActivity(i);
            }
        });

        lv_business = findViewById(R.id.lv_business_setbid);
        lv_location = findViewById(R.id.lv_location_setbid);


        adapter = new Setbid_LVAdapter(mContext, arrayList_business);
        lv_business.setAdapter(adapter);

        adapter = new Setbid_LVAdapter(mContext, arrayList_location);
        lv_location.setAdapter(adapter);


        et_price1 = findViewById(R.id.et_price1_setbid);
        et_price2 = findViewById(R.id.et_price2_setbid);
        et_price1.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });
        et_price2.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (!hasFocus) {
                    SaveSharedPreference.hideKeyboard(v, mContext);
                }
            }
        });

        btn_price1 = findViewById(R.id.btn_price1_setbid);
        btn_price2 = findViewById(R.id.btn_price2_setbid);
        btn_price3 = findViewById(R.id.btn_price3_setbid);
        btn_price4 = findViewById(R.id.btn_price4_setbid);
        btn_price5 = findViewById(R.id.btn_price5_setbid);
        btn_price1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_price1.setText("0");
                et_price2.setText("1");
            }
        });
        btn_price2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_price1.setText("1");
                et_price2.setText("5");
            }
        });
        btn_price3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_price1.setText("5");
                et_price2.setText("10");
            }
        });
        btn_price4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_price1.setText("10");
                et_price2.setText("50");
            }
        });
        btn_price5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_price1.setText("50");
                et_price2.setText("");
            }
        });

        btn_save = (Button)findViewById(R.id.btn_save_setbid);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertMypageGroup();
            }
        });

        spn_pricetype = findViewById(R.id.spn_pricetype_setbid);
        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.setbid_pricespinner, priceType);
        adapter.setDropDownViewResource(R.layout.setbid_pricespinner);
        spn_pricetype.setAdapter(adapter);

        btn_bidType = findViewById(R.id.btn_bidType_setbid);
        btn_exception = findViewById(R.id.btn_exception_setbid);
        btn_etc = findViewById(R.id.btn_etc_setbid);

        arrayList1 = new ArrayList<>();
        arrayList1.add(new BinaryCode("역경매 제외", 4096));
        arrayList1.add(new BinaryCode("최소 제외", 32));
        arrayList1.add(new BinaryCode("시담 제외", 131072));
        arrayList1.add(new BinaryCode("지명 제외", 32768));
        arrayList1.add(new BinaryCode("여성 제외", 262144));
        arrayList1.add(new BinaryCode("공공도급 제외", 1024));
        arrayList1.add(new BinaryCode("일반 제외", 512));
        initCheckedFlag(arrayList1, "GFlags");
        dialog1 = new Setbid_Dialog(Setbid_Activity.this, "공고 구분", arrayList1);


        btn_bidType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1.show();
            }
        });

        arrayList2 = new ArrayList<>();
        arrayList2.add(new BinaryCode("조달청 제외", 1));
        arrayList2.add(new BinaryCode("한국전력 제외", 2));
        arrayList2.add(new BinaryCode("국방부 제외", 4));
        arrayList2.add(new BinaryCode("학교장터 제외", 8));
        arrayList2.add(new BinaryCode("c-market 제외", 16));
        arrayList2.add(new BinaryCode("온비드 제외", 32));
        arrayList2.add(new BinaryCode("EAT 제외", 128));
        initCheckedFlag(arrayList2, "PassOrderCode");
        dialog2 = new Setbid_Dialog(Setbid_Activity.this, "제외 발주처", arrayList2);

        btn_exception.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2.show();
            }
        });

        arrayList3 = new ArrayList<>();
        arrayList3.add(new BinaryCode("아파트관련공고 제외", 1));
        arrayList3.add(new BinaryCode("메일수신 제외", 2));
        try {
            if (groupData != null) {
                arrayList3.get(0).setChecked(groupData.getString("AptOpt").equals("true"));
                arrayList3.get(1).setChecked(groupData.getString("MailOpt").equals("false"));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        dialog3 = new Setbid_Dialog(Setbid_Activity.this, "기타 사항", arrayList3);

        btn_etc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3.show();
            }
        });


    }

    @Override
    public void onResume(){
        super.onResume();
        adapter = new Setbid_LVAdapter(mContext, arrayList_business);
        lv_business.setAdapter(adapter);

        adapter = new Setbid_LVAdapter(mContext, arrayList_location);
        lv_location.setAdapter(adapter);
    }

    public void getAreaCode(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/getAreaCode.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        String name = o.getString("Name");
                        if(name.length() == 2){
                            name += " ";
                        }

                        arrayList_location.add(new BidAreaCode.BidAreaItem(name, o.getString("Code")));
                    }
                    adapter = new Setbid_LVAdapter(mContext, arrayList_location);
                    lv_location.setAdapter(adapter);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                try {
                    params.put("GCode", GCode);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void getUpcode(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/getUpcode.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        arrayList_business.add(new BidUpCode.BidUpCodeItem(o.getString("Name"), o.getString("Code")));
                    }
                    adapter = new Setbid_LVAdapter(mContext, arrayList_business);
                    lv_business.setAdapter(adapter);
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                try {
                    params.put("GCode", GCode);
                }
                catch (Exception e){
                    e.printStackTrace();
                }
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public void initCheckedFlag(ArrayList<BinaryCode> arrayList, String paramName){
        try {
            for (int i = 0; i < arrayList.size(); i++) {
                if (groupData == null)
                    arrayList.get(i).setChecked(false);
                else
                    arrayList.get(i).setChecked(arrayList.get(i).Contains(Integer.parseInt(groupData.getString(paramName))));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void insertMypageGroup(){

        int tempGFlag = 0;
        for(int i = 0; i < arrayList1.size(); i++){
            if(arrayList1.get(i).isChecked()){
                tempGFlag |= arrayList1.get(i).getCode();
            }
        }

        int tempPassOrderCode = 0;
        for(int i = 0; i < arrayList1.size(); i++){
            if(arrayList2.get(i).isChecked()){
                tempPassOrderCode |= arrayList2.get(i).getCode();
            }
        }

        String tempAptOpt;
        if(arrayList3.get(0).isChecked()){
            tempAptOpt = "1";
        }else{
            tempAptOpt = "0";
        }

        String tempMailOpt;
        if(arrayList3.get(0).isChecked()){
            tempMailOpt = "0";
        }else{
            tempMailOpt = "1";
        }

        StringBuilder tempAreaCodes = new StringBuilder();
        for(int i = 0; i < arrayList_location.size(); i++){
            BidAreaCode.BidAreaItem item = arrayList_location.get(i);
            String code = item.getCode();
            if(code.substring(2, 6).equals("0000")){
                code = code.substring(0, 2);
            }
            tempAreaCodes.append(code).append("\n");
        }

        if(tempAreaCodes.toString().isEmpty()){
            tempAreaCodes.append("999999");
        }

        StringBuilder tempUpcodes = new StringBuilder();
        for(int i = 0; i < arrayList_business.size(); i++){
            BidUpCode.BidUpCodeItem item = arrayList_business.get(i);
            String code = item.getCode();

            tempUpcodes.append(code).append("\n");
        }

        final String EMoney = ((EditText)findViewById(R.id.et_price1_setbid)).getText().toString();
        final String SMoney = ((EditText)findViewById(R.id.et_price2_setbid)).getText().toString();

        final int GFlag = tempGFlag;
        final int PassOrderCode = tempPassOrderCode;
        final String AptOpt = tempAptOpt;
        final String MailOpt = tempMailOpt;

        final String AreaCodes = tempAreaCodes.toString();
        final String Upcodes = tempUpcodes.toString();

        Log.d("insert data = ", EMoney + ", " + SMoney + ", " + GFlag + ", " + PassOrderCode + ", " + AptOpt + ", " + MailOpt + ", " + AreaCodes + ", " + Upcodes);

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/insertMypageGroup.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("GCode") != null){
                        GCode = obj.getString("GCode");
                        Toast.makeText(mContext, "맞춤설정이 저장되었습니다.", Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, "맞춤설정이 실패하였습니다.", Toast.LENGTH_SHORT).show();
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

                params.put("GCode", GCode);
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                params.put("GFlags", String.valueOf(GFlag));
                params.put("SMoney", SMoney);
                params.put("EMoney", EMoney);
                params.put("GName", "test2");
                params.put("PassOrderCode", String.valueOf(PassOrderCode));
                params.put("AptOpt", AptOpt);
                params.put("MailOpt", MailOpt);
                params.put("AreaCodes", AreaCodes);
                params.put("Upcodes", Upcodes);

                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

}

