package com.mobile.a21line.Setbid;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Bid.Bid_Activity;
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
import java.util.Map;

import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_ClickEvent;
import static com.mobile.a21line.SaveSharedPreference.DrawerLayout_Open;


public class Setbid_Activity extends AppCompatActivity {

    private Context mContext;
//    private String[] priceType = {"추정금액","기초금액"};

    private JSONObject groupData = new JSONObject();
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

    View frameLayout;
    DrawerLayout drawerLayout;

    ListView lv_business;
    ListView lv_location;

    TextView tv_business_count;
    TextView tv_location_count;

    Setbid_Dialog_EtcSelect dialog1;
    Setbid_Dialog_EtcSelect dialog2;
    Setbid_Dialog_EtcSelect dialog3;

    Setbid_Dialog_Title_Chan dialog4;

    int ll_containerTotalHeight;

    LinearLayout ll_container1;
    LinearLayout ll_container2;
    LinearLayout ll_container3;
    LinearLayout ll_container4;
    LinearLayout ll_container5;
    View ll_container6;


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

        LinearLayout ll_containerTotal = findViewById(R.id.ll_containerTotal_setbid);

        DisplayMetrics dm = getApplicationContext().getResources().getDisplayMetrics();
        ll_containerTotalHeight = dm.heightPixels-getResources().getDimensionPixelSize(R.dimen.height_40dp)-getResources().getDimensionPixelSize(R.dimen.height_25dp);


        ll_container1 = findViewById(R.id.ll_container1_setbid);
        ll_container2 = findViewById(R.id.ll_container2_setbid);
        ll_container3 = findViewById(R.id.ll_container3_setbid);
        ll_container4 = findViewById(R.id.ll_container4_setbid);
        ll_container5 = findViewById(R.id.ll_container5_setbid);
        ll_container6 = findViewById(R.id.ll_container6_setbid);

        ViewGroup.LayoutParams params1 = ll_container1.getLayoutParams();
        ViewGroup.LayoutParams params2 = ll_container2.getLayoutParams();
        ViewGroup.LayoutParams params3 = ll_container3.getLayoutParams();
        ViewGroup.LayoutParams params4 = ll_container4.getLayoutParams();
        ViewGroup.LayoutParams params5 = ll_container5.getLayoutParams();
        ViewGroup.LayoutParams params6 = ll_container6.getLayoutParams();

        params1.height = (int) (ll_containerTotalHeight*0.29);
        params2.height = (int) (ll_containerTotalHeight*0.29);
        params3.height = (int) (ll_containerTotalHeight*0.17);
        params4.height = (int) (ll_containerTotalHeight*0.09);
        params5.height = (int) (ll_containerTotalHeight*0.09);
        params6.height = (int) (ll_containerTotalHeight*0.02);



        ll_container1.setLayoutParams(params1);
        ll_container2.setLayoutParams(params2);
        ll_container3.setLayoutParams(params3);
        ll_container4.setLayoutParams(params4);
        ll_container5.setLayoutParams(params5);
        ll_container6.setLayoutParams(params6);

        Log.d(String.valueOf(ll_containerTotalHeight),"height");
        Log.d(String.valueOf(params1.height),"height1");
        Log.d(String.valueOf(params2.height),"height2");
        Log.d(String.valueOf(params3.height),"height3");
        Log.d(String.valueOf(params4.height),"height4");
        Log.d(String.valueOf(params5.height),"height5");


        arrayList_location = new ArrayList<>();
        arrayList_business = new ArrayList<>();
        if(getIntent().getBooleanExtra("isAdded", false)){
            try {
                groupData = new JSONObject(getIntent().getStringExtra("groupData"));
                GCode = groupData.getString("GCode");
                getAreaCode();
                getUpcode();
                ((EditText) findViewById(R.id.et_price1_setbid)).setText(groupData.getString("SMoney"));
                ((EditText) findViewById(R.id.et_price2_setbid)).setText(groupData.getString("EMoney"));
                ((TextView) findViewById(R.id.tv_toolbarTitle)).setText(groupData.getString("GName"));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            GCode = "new";
            ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("맞춤설정 New");
        }


        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setText("초기화");

        final AlertDialog.Builder deleteAlert = new AlertDialog.Builder(new ContextThemeWrapper(Setbid_Activity.this,R.style.myDialog));



        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                float textSize = getResources().getDimension(R.dimen.txt_main);
                deleteAlert.setMessage("저장한 맞춤설정 데이터가 초기화됩니다.")
                        .setPositiveButton("삭제", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                arrayList1.clear();
                                arrayList2.clear();
                                et_price1.setText("0");
                                et_price2.setText("0");
                                arrayList3.get(0).setChecked(false);
                                arrayList3.get(1).setChecked(false);
                                arrayList_location.clear();
                                arrayList_business.clear();
                                adapter = new Setbid_LVAdapter(mContext, arrayList_business, tv_business_count);
                                lv_business.setAdapter(adapter);
                                ((TextView)findViewById(R.id.tv_businessselectQty_Setbid)).setText("(" + arrayList_business.size() + ")");

                                adapter = new Setbid_LVAdapter(mContext, arrayList_location, tv_location_count);
                                lv_location.setAdapter(adapter);
                                ((TextView)findViewById(R.id.tv_locationselectQty_Setbid)).setText("(" + arrayList_location.size() + ")");
                                Toast.makeText(mContext,"맞춤설정이 초기화 되었습니다.",Toast.LENGTH_SHORT).show();
                            }
                        })
                        .setNegativeButton("닫기", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alertDialog = deleteAlert.create();
                alertDialog.show();
                alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setAllCaps(false);
                alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));
                alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setAllCaps(false);
                alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(getResources().getColor(R.color.textColor_highlight_ngt));
                alertDialog.show();
                TextView msgView = (TextView) alertDialog.findViewById(android.R.id.message);
                msgView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_toolbar));


            }
        });
//        ((ImageView)findViewById(R.id.iv_toolbarModify)).setOnClickListener(new View.OnClickListener()
//        {
//            @Override
//            public void onClick(View v) {
//
//                dialog4 = new Setbid_Dialog_Title_Chan(Setbid_Activity.this, ((TextView) findViewById(R.id.tv_toolbarTitle)).getText().toString(), new Setbid_Dialog_Title_Chan.ISetbidDialogEventListener() {
//                    @Override
//                    public void customDialogEvent(String returnValue) {
//                        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText(returnValue);
//                    }
//                });
//                dialog4.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
//                dialog4.getWindow().setFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND,WindowManager.LayoutParams.FLAG_DIM_BEHIND);
//                dialog4.getWindow().setDimAmount(0.6f);
//                dialog4.show();
//            }
//        }
//        );

        drawerLayout = findViewById(R.id.dl_setbid);
        frameLayout = findViewById(R.id.fl_drawerView_setbid);



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

        tv_business_count = findViewById(R.id.tv_businessselectQty_Setbid);
        tv_location_count = findViewById(R.id.tv_locationselectQty_Setbid);

        adapter = new Setbid_LVAdapter(mContext, arrayList_business, tv_business_count);
        lv_business.setAdapter(adapter);

        adapter = new Setbid_LVAdapter(mContext, arrayList_location, tv_location_count);
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


        btn_save = findViewById(R.id.btn_next_setbid);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (lv_business.getAdapter().getCount()==0)
                {
                    Toast.makeText(mContext,"업종 선택은 1개 이상 필수입니다.", Toast.LENGTH_SHORT).show();
                    return;
                }
                else
                    {
                    insertMypageGroup();
                }
            }
        });

//        spn_pricetype = findViewById(R.id.spn_pricetype_setbid);
//        ArrayAdapter<CharSequence> adapter = new ArrayAdapter<CharSequence>(this, R.layout.setbid_pricespinner, priceType);
//        adapter.setDropDownViewResource(R.layout.setbid_pricespinner);
//        spn_pricetype.setAdapter(adapter);

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
        dialog1 = new Setbid_Dialog_EtcSelect(Setbid_Activity.this, "공고 구분", arrayList1);


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
        dialog2 = new Setbid_Dialog_EtcSelect(Setbid_Activity.this, "제외 발주처", arrayList2);

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
        dialog3 = new Setbid_Dialog_EtcSelect(Setbid_Activity.this, "기타 사항", arrayList3);

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
        drawerLayout.closeDrawers();
        adapter = new Setbid_LVAdapter(mContext, arrayList_business, tv_business_count);
        lv_business.setAdapter(adapter);
        ((TextView)findViewById(R.id.tv_businessselectQty_Setbid)).setText("(" + arrayList_business.size() + ")");

        adapter = new Setbid_LVAdapter(mContext, arrayList_location, tv_location_count);
        lv_location.setAdapter(adapter);
        ((TextView)findViewById(R.id.tv_locationselectQty_Setbid)).setText("(" + arrayList_location.size() + ")");


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
                    adapter = new Setbid_LVAdapter(mContext, arrayList_location, tv_location_count);
                    lv_location.setAdapter(adapter);

                    ((TextView)findViewById(R.id.tv_locationselectQty_Setbid)).setText("(" + arrayList_location.size() + ")");
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
                    adapter = new Setbid_LVAdapter(mContext, arrayList_business, tv_business_count);
                    lv_business.setAdapter(adapter);

                    ((TextView)findViewById(R.id.tv_businessselectQty_Setbid)).setText("(" + arrayList_business.size() + ")");
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

                        groupData.put("GCode", GCode);
                        groupData.put("MemID", SaveSharedPreference.getUserID(mContext));
                        groupData.put("GFlags", String.valueOf(GFlag));
                        groupData.put("SMoney", EMoney);
                        groupData.put("EMoney", SMoney);
                        groupData.put("GName", ((TextView) findViewById(R.id.tv_toolbarTitle)).getText().toString());
                        groupData.put("PassOrderCode", String.valueOf(PassOrderCode));
                        groupData.put("AptOpt", AptOpt);
                        groupData.put("MailOpt", MailOpt);

                        Intent i = new Intent(mContext, Bid_Activity.class);
                        i.putExtra("GCode", GCode);
                        i.putExtra("GName", ((TextView) findViewById(R.id.tv_toolbarTitle)).getText().toString());
                        i.putExtra("groupData", groupData.toString());
                        startActivity(i);
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
                params.put("SMoney", EMoney);
                params.put("EMoney", SMoney);
                params.put("GName", ((TextView) findViewById(R.id.tv_toolbarTitle)).getText().toString());
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

