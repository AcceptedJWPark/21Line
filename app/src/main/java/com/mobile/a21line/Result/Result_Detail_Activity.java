package com.mobile.a21line.Result;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.TooltipCompat;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Bid.Bid_Detail_Activity;
import com.mobile.a21line.BidResultCommon.Popup_MemoAdd;
import com.mobile.a21line.MyBid.MyBid_moveGroup;
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
 * Created by Accepted on 2018-05-25.
 */

public class Result_Detail_Activity extends AppCompatActivity {

    Context mContext;
    Button btn_info;
    Button btn_result;
    Button btn_firstCompany;
    Button btn_companylist;

    LinearLayout ll_basicInfo;

    View header_multiple;

    Result_Detail_Multiple_LVAdapter adapter;
    ArrayList<Result_Detail_Multiple_Listitem> arrayList;
    ListView lv_multiple;

    TextView tv_bidTitle;
    TextView tv_bidNo;
    TextView tv_bidOrder;
    TextView tv_bidDemand;
    TextView tv_bidCharger;
    TextView tv_bidPhone;
    TextView tv_bidLocation;
    TextView tv_bidBusiness;
    TextView tv_bidPrice1;
    TextView tv_bidPrice2;
    TextView tv_bidLimitPrice;
    TextView tv_bidPercent;

    String iBidCode;

    boolean isRelativeResult = false;

    LinearLayout ll_relativeResult_Detail;
    LinearLayout ll_relativeResult;

    boolean isMybid;


    ExpandableListView elv_companylist;
    private ArrayList<Result_Detail_CompanyList_Parent_Listitem> arrayList_Parent = new ArrayList<Result_Detail_CompanyList_Parent_Listitem>();
    private HashMap<Result_Detail_CompanyList_Parent_Listitem, ArrayList<Result_Detail_CompanyList_Child_Listitem>> arrayList_Child = new HashMap<Result_Detail_CompanyList_Parent_Listitem, ArrayList<Result_Detail_CompanyList_Child_Listitem>>();
    private Result_Detail_CompanyList_ELVAdapter elvAdapter;
    View header_companylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_detail_activity);
        iBidCode = getIntent().getStringExtra("iBidCode");
        mContext = getApplicationContext();
        isMybid=true;


        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("낙찰공고 상세");
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setImageResource(R.drawable.icon_mybid_white);
        ((ImageView) findViewById(R.id.img_toolbarIcon_MyBid)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isMybid)
                {
                    Toast.makeText(mContext,"이미 저장된 공고입니다.",Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Intent intent = new Intent(mContext, MyBid_moveGroup.class);
                    startActivity(intent);
                }
            }
        });
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setVisibility(View.VISIBLE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Edit_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, Popup_MemoAdd.class);
                intent.putExtra("iBidCode", iBidCode);
                startActivity(intent);
            }
        });
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ll_relativeResult_Detail = findViewById(R.id.ll_relativeResult_Detail);
        ll_relativeResult = findViewById(R.id.ll_relativeResult);

        btn_info = findViewById(R.id.btn_info_resultDetail);
        btn_result = findViewById(R.id.btn_result_resultDetail);
        btn_companylist = findViewById(R.id.btn_companylist_resultDetail);
        btn_firstCompany = findViewById(R.id.btn_firstCompany_resultDetail);
        ll_basicInfo = findViewById(R.id.ll_basicInfo_result_detail);

        clickedInfo();

        tv_bidTitle = findViewById(R.id.tv_result_detail_basic_bidTitle);
        tv_bidNo = findViewById(R.id.tv_result_detail_basic_bidNo);
        tv_bidOrder = findViewById(R.id.tv_result_detail_basic_bidOrder);
        tv_bidDemand = findViewById(R.id.tv_result_detail_basic_demand);
        tv_bidCharger = findViewById(R.id.tv_result_detail_basic_charger);
        tv_bidPhone = findViewById(R.id.tv_result_detail_basic_phone);
        tv_bidLocation = findViewById(R.id.tv_result_detail_basic_location);
        tv_bidBusiness = findViewById(R.id.tv_result_detail_basic_business);
        tv_bidPrice1 = findViewById(R.id.tv_result_detail_basic_price1);
        tv_bidPrice2 = findViewById(R.id.tv_result_detail_basic_price2);
        tv_bidLimitPrice = findViewById(R.id.tv_result_detail_basic_limit);
        tv_bidPercent = findViewById(R.id.tv_result_detail_basic_bidpercent);


        btn_info.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedInfo();
            }
        });
        btn_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedResult();
            }
        });

        btn_companylist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedCompanyList();
            }
        });
        btn_firstCompany.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedFirstCompany();
            }
        });

        header_multiple = getLayoutInflater().inflate(R.layout.result_detail_multipleprice_header_bg, null, false);

        getBidData();
        getResultComsList();

        elv_companylist = findViewById(R.id.elv_result_companyList);
        elv_companylist.setOnGroupClickListener(new ExpandableListView.OnGroupClickListener() {
            int lastClickedPosition = 0;
            @Override
            public boolean onGroupClick(ExpandableListView parent, View v, int groupPosition, long id) {
                boolean isExpand = (!elv_companylist.isGroupExpanded(groupPosition));
                elv_companylist.collapseGroup(lastClickedPosition);
                if(isExpand)
                {
                    elv_companylist.expandGroup(groupPosition);
                }lastClickedPosition = groupPosition;
                return true;
            }
        });
    }


    private void clickedInfo()
    {
        ll_basicInfo.setVisibility(View.VISIBLE);
        findViewById(R.id.inc_basicInfo_result_companylist).setVisibility(View.GONE);
        findViewById(R.id.inc_basicInfo_result_firstcompany).setVisibility(View.GONE);
        findViewById(R.id.inc_basicInfo_result_result).setVisibility(View.GONE);

        btn_info.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_info.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_info.setTypeface(null, Typeface.BOLD);
        btn_info.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));

        btn_result.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_result.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_result.setTypeface(null, Typeface.NORMAL);
        btn_result.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));


        btn_companylist.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_companylist.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_companylist.setTypeface(null, Typeface.NORMAL);
        btn_companylist.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_firstCompany.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_firstCompany.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_firstCompany.setTypeface(null, Typeface.NORMAL);
        btn_firstCompany.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));



    }


    private void clickedResult()
    {

        ll_basicInfo.setVisibility(View.GONE);
        findViewById(R.id.inc_basicInfo_result_companylist).setVisibility(View.GONE);
        findViewById(R.id.inc_basicInfo_result_firstcompany).setVisibility(View.GONE);
        findViewById(R.id.inc_basicInfo_result_result).setVisibility(View.VISIBLE);


        btn_result.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_result.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_result.setTypeface(null, Typeface.BOLD);
        btn_result.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));

        btn_info.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_info.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_info.setTypeface(null, Typeface.NORMAL);
        btn_info.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));


        btn_companylist.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_companylist.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_companylist.setTypeface(null, Typeface.NORMAL);
        btn_companylist.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_firstCompany.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_firstCompany.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_firstCompany.setTypeface(null, Typeface.NORMAL);
        btn_firstCompany.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
    }



    private void clickedCompanyList()
    {
        ll_basicInfo.setVisibility(View.GONE);
        findViewById(R.id.inc_basicInfo_result_companylist).setVisibility(View.VISIBLE);
        findViewById(R.id.inc_basicInfo_result_firstcompany).setVisibility(View.GONE);
        findViewById(R.id.inc_basicInfo_result_result).setVisibility(View.GONE);


        btn_companylist.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_companylist.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_companylist.setTypeface(null, Typeface.BOLD);
        btn_companylist.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_result.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_result.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_result.setTypeface(null, Typeface.NORMAL);
        btn_result.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));



        btn_info.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_info.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_info.setTypeface(null, Typeface.NORMAL);
        btn_info.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_firstCompany.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_firstCompany.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_firstCompany.setTypeface(null, Typeface.NORMAL);
        btn_firstCompany.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

    }

    private void clickedFirstCompany()
    {
        ll_basicInfo.setVisibility(View.GONE);
        findViewById(R.id.inc_basicInfo_result_companylist).setVisibility(View.GONE);
        findViewById(R.id.inc_basicInfo_result_firstcompany).setVisibility(View.VISIBLE);
        findViewById(R.id.inc_basicInfo_result_result).setVisibility(View.GONE);


        btn_firstCompany.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_firstCompany.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_firstCompany.setTypeface(null, Typeface.BOLD);
        btn_firstCompany.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_result.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_result.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_result.setTypeface(null, Typeface.NORMAL);
        btn_result.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));



        btn_info.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_info.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_info.setTypeface(null, Typeface.NORMAL);
        btn_info.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_companylist.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_companylist.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_companylist.setTypeface(null, Typeface.NORMAL);
        btn_companylist.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

    }


    private void setListViewHeightBasedOnItems(ListView listView)
    {
        ListAdapter listAdapter = listView.getAdapter();
        if (listAdapter == null) {
            return;
        }
        int numberOfItems = listAdapter.getCount();
        int totalItemsHeight = 0; for (int itemPos = 0; itemPos < numberOfItems; itemPos++)
        {
            View item = listAdapter.getView(itemPos, null, listView);
            item.measure(0, 0);
            totalItemsHeight += item.getMeasuredHeight();
        }
        int totalDividersHeight = listView.getDividerHeight() * (numberOfItems - 1);
        ViewGroup.LayoutParams params = listView.getLayoutParams();
        params.height = totalItemsHeight + totalDividersHeight;
        listView.setLayoutParams(params); listView.requestLayout();
    }

    private void getBidData(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getBidDataUri() + "getBidData.php", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);

                    JSONObject regHistory = obj.getJSONObject("RegHistory");
                    if(regHistory.getInt("totalNum") > 0){
                        ll_relativeResult_Detail.setVisibility(View.VISIBLE);
                        ll_relativeResult = findViewById(R.id.ll_relativeResult);

                        JSONArray historyDatas = regHistory.getJSONArray("datas");
                        for(int i = 0; i < historyDatas.length(); i++){
                            JSONObject regData = historyDatas.getJSONObject(i);
                            final String bidCode = regData.getString("BidNo") + "-" + regData.getString("BidNoSeq");

                            TextView tv_relative = new TextView(mContext);
                            int state = regData.getInt("BidState_Code");
                            int reBid = state & 0x1;
                            int cancel = state & 0x20;

                            tv_relative.setTextColor(Color.parseColor("#ffffff"));
                            tv_relative.setGravity(View.TEXT_ALIGNMENT_CENTER);
                            tv_relative.setPadding((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),
                                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()),
                                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 5, getResources().getDisplayMetrics()),
                                    (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, getResources().getDisplayMetrics()));
                            tv_relative.setTextSize(TypedValue.COMPLEX_UNIT_PX, getResources().getDimension(R.dimen.txt_sub));

                            if(regData.getInt("BidNoSeq") == 0) {
                                tv_relative.setText("원문");
                                tv_relative.setBackground(getResources().getDrawable(R.drawable.bgr_btn_original));
                            }else if(reBid > 0){
                                tv_relative.setText(regData.getInt("BidNoSeq") + "차 정정");
                                tv_relative.setBackground(getResources().getDrawable(R.drawable.bgr_btn_edit));
                            }else if(cancel > 0){
                                tv_relative.setText("취소");
                                tv_relative.setBackgroundColor(Color.parseColor("#dd3343"));
                            }

                            tv_relative.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    Intent intent = new Intent(mContext, Bid_Detail_Activity.class);
                                    intent.putExtra("iBidCode", bidCode);
                                    startActivity(intent);
                                }
                            });

                            RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                            if(i == 0){
                                layoutParams.setMargins((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10, getResources().getDisplayMetrics()), 0, 0, 0);
                            }else {
                                layoutParams.setMargins((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, getResources().getDisplayMetrics()), 0, 0, 0);
                            }
                            layoutParams.addRule(RelativeLayout.ALIGN_LEFT, RelativeLayout.TRUE);
                            tv_relative.setLayoutParams(layoutParams);

                            ll_relativeResult.addView(tv_relative);
                        }
                    }

                    final String phone = obj.getString("OrderPhone");

                    tv_bidTitle.setText(obj.getString("BidName"));
                    tv_bidNo.setText(obj.getString("OrderBidHNum"));
                    tv_bidOrder.setText(obj.getString("OrderName"));
                    tv_bidCharger.setText(obj.getString("OrderManager"));
                    tv_bidPhone.setText(phone);
                    tv_bidPhone.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phone));
                            startActivity(i);
                        }
                    });

                    tv_bidDemand.setText((obj.getString("DemandName").equals("null")) ? "-" : obj.getString("DemandName"));

                    tv_bidPrice1.setText(toNumFormat(obj.getString("BasicPrice")) + "원");
                    tv_bidPrice2.setText(toNumFormat(obj.getString("EstimatedPrice")) + "원");
                    tv_bidLimitPrice.setText(obj.getString("CutPercent"));
                    tv_bidPercent.setText(obj.getString("YegaLow") + "%" + " ~ " + obj.getString("YegaHigh")+ "%");
                    tv_bidLocation.setText(obj.getString("AreaName"));
                    tv_bidBusiness.setText(obj.getString("UpcodeName"));
                    bidState(obj.getInt("BidState_Code"));
                    JSONObject resultCom = null;
                    try{
                        resultCom = obj.getJSONObject("FinalComs");
                        int fBizno = resultCom.getInt("BizNo");
                        int index = 0;
                        JSONObject resultComs = obj.getJSONObject("ResultComs");
                        JSONArray resultComsArray = resultComs.getJSONArray("datas");
                        for(int i = 0; i < resultComsArray.length(); i++){
                            JSONObject o = resultComsArray.getJSONObject(i);
                            if(fBizno == o.getInt("BizNo")) {
                                index = i;
                                break;
                            }
                        }
                        ((TextView)findViewById(R.id.tv_result_com_type)).setText("최종낙찰");
                        resultCom = resultComsArray.getJSONObject(index);
                    }catch(JSONException e){
                        ((TextView)findViewById(R.id.tv_result_com_type)).setText("1순위");
                        JSONObject resultComs = obj.getJSONObject("ResultComs");
                        JSONArray resultComsArray = resultComs.getJSONArray("datas");
                        try {
                            resultCom = resultComsArray.getJSONObject(0);
                        }catch(JSONException e2){
                            e2.printStackTrace();
                        }
                    }

                    JSONObject resultMain = obj.getJSONObject("ResultMain");
                    JSONArray boksu = resultMain.getJSONArray("aBoksu");
                    ((TextView)findViewById(R.id.tv_result_result_basicPrice)).setText(toNumFormat(resultMain.getString("BasicPrice")) + " 원");
                    ((TextView)findViewById(R.id.tv_result_result_decidingPrice)).setText(toNumFormat(resultMain.getString("DecidingPrice")) + " 원");
                    ((TextView)findViewById(R.id.tv_result_result_rateRange)).setText(resultMain.getString("RateRange"));
                    ((TextView)findViewById(R.id.tv_result_result_limitPrice)).setText(obj.getString("CutPercent"));
                    ((TextView)findViewById(R.id.tv_result_result_percent)).setText(obj.getString("YegaLow") + "%" + " ~ " + obj.getString("YegaHigh")+ "%");

                    if(resultCom != null) {
                        ((TextView) findViewById(R.id.tv_result_com_name)).setText(resultCom.getString("ComName"));
                        ((TextView) findViewById(R.id.tv_result_com_biz_no)).setText(resultCom.getString("BizNo"));
                        ((TextView) findViewById(R.id.tv_result_com_ceo_name)).setText(resultCom.getString("CeoName"));

                        final String resultComPhone = resultCom.getString("Phone");
                        ((TextView) findViewById(R.id.tv_result_com_phone)).setText(resultComPhone);
                        ((TextView) findViewById(R.id.tv_result_com_phone)).setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + resultComPhone));
                                startActivity(i);
                            }
                        });
                        ((TextView) findViewById(R.id.tv_result_com_addr)).setText(resultCom.getString("Addr"));
                        ((TextView) findViewById(R.id.tv_result_com_joinPrice)).setText(toNumFormat(resultCom.getString("JoinPrice")) + " 원");
                        ((TextView) findViewById(R.id.tv_result_com_joinRate)).setText(resultCom.getString("JoinRate"));
                        ((TextView) findViewById(R.id.tv_result_com_RateRange)).setText(resultCom.getString("RateRange"));
                        ((TextView) findViewById(R.id.tv_result_com_BPercent)).setText(resultCom.getString("BPercent"));
                    }else{
                        ((TextView)findViewById(R.id.tv_result_com_type)).setText("유찰되었습니다. (유찰사유 : " + resultMain.getString("EtcInfo") + ")");
                    }





                    lv_multiple = findViewById(R.id.lv_result_detail_multiple);
                    arrayList = new ArrayList();
                    for(int i = 0; i < boksu.length(); i++) {
                        arrayList.add(new Result_Detail_Multiple_Listitem(String.valueOf(i + 1), toNumFormat(boksu.getJSONObject(i).getString("money")), boksu.getJSONObject(i).getString("devPercent"), boksu.getJSONObject(i).getString("percent"), boksu.getJSONObject(i).getBoolean("isWin")));
                    }

                    adapter = new Result_Detail_Multiple_LVAdapter(mContext,arrayList);
                    lv_multiple.addHeaderView(header_multiple);
                    lv_multiple.setAdapter(adapter);
                    setListViewHeightBasedOnItems(lv_multiple);

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("iBidCode", iBidCode);
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    private void getResultComsList(){
        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/getResultComsList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    elv_companylist = findViewById(R.id.elv_result_companyList);

                    for(int i = 0; i < obj.length(); i++) {
                        JSONObject o = obj.getJSONObject(i);
                        Result_Detail_CompanyList_Parent_Listitem item = new Result_Detail_CompanyList_Parent_Listitem((o.getInt("InRankSeq") < 1) ? "-" : o.getString("InRankSeq"), o.getString("ComName"), toNumFormat(o.getString("JoinPrice")), o.getString("JoinRate"));
                        arrayList_Parent.add(item);
                        ArrayList<Result_Detail_CompanyList_Child_Listitem> arrayList = new ArrayList<>();

                        arrayList.add(new Result_Detail_CompanyList_Child_Listitem(o.getString("ComName"), o.getString("BizNo"), o.getString("CeoName")));
                        arrayList_Child.put(item, arrayList);
                    }

                    elvAdapter = new Result_Detail_CompanyList_ELVAdapter(mContext,arrayList_Parent,arrayList_Child);
                    header_companylist = getLayoutInflater().inflate(R.layout.result_detail_companylist_header_bg, null, false);

                    elv_companylist.addHeaderView(header_companylist);
                    elv_companylist.setAdapter(elvAdapter);

                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("BidNo", iBidCode.substring(0, 13));
                params.put("BidNoSeq", iBidCode.substring(14, 15));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    private String toNumFormat(String data){
        DecimalFormat df = new DecimalFormat("#,###");
        BigDecimal bd = new BigDecimal(data);
        return df.format(bd);
    }

    public void bidState(int bidState)
    {
        LinearLayout ll_bidstateContainer = findViewById(R.id.tv_bidCategory_detail_basic);

        ImageView[] iv_bidstate = new ImageView[9];
        iv_bidstate[0] = findViewById(R.id.iv_bidstate1_detail_basic);
        iv_bidstate[1] = findViewById(R.id.iv_bidstate2_detail_basic);
        iv_bidstate[2] = findViewById(R.id.iv_bidstate3_detail_basic);
        iv_bidstate[3] = findViewById(R.id.iv_bidstate4_detail_basic);
        iv_bidstate[4] = findViewById(R.id.iv_bidstate5_detail_basic);
        iv_bidstate[5] = findViewById(R.id.iv_bidstate6_detail_basic);
        iv_bidstate[6] = findViewById(R.id.iv_bidstate7_detail_basic);
        iv_bidstate[7] = findViewById(R.id.iv_bidstate8_detail_basic);
        iv_bidstate[8] = findViewById(R.id.iv_bidstate9_detail_basic);

        int[] states = {0x4, 0x1, 0x20, 0x10, 0x80, 0x100, 0x40, 0x2, 0x8, 0x400, 0x800, 0x1000, 0x4000, 0x8000, 0x40000, 0x20000, 0x80000};
        int[] rescources = { R.drawable.bidstate_kinds9, R.drawable.bidstate_kinds2, R.drawable.bidstate_kinds4, R.drawable.bidstate_kinds5, R.drawable.bidstate_kinds6
                , R.drawable.bidstate_kinds11, R.drawable.bidstate_kinds3, R.drawable.bidstate_kinds7, R.drawable.bidstate_kinds8
                , R.drawable.bidstate_kinds10, R.drawable.bidstate_kinds1, R.drawable.bidstate_kinds12, R.drawable.bidstate_kinds3
                , R.drawable.bidstate_kinds16, R.drawable.bidstate_kinds17, R.drawable.bidstate_kinds15, R.drawable.bidstate_kinds18};
        String[] tooltipValue = { "결과발표", "정정공고",  "취소공고", "전자입찰", "견적입찰", "수의계약", "재공고", "긴급공고", "계약공고", "공동도급", "현장설명참조", "역경매", "재입찰", "지명입찰", "여성", "시담", "유찰공고"};

        int index = 0;
        for(int i = 0; i < states.length; i++){
            int temp = bidState & states[i];
            if(temp > 0){
                iv_bidstate[index].setVisibility(View.VISIBLE);
                iv_bidstate[index].setImageResource(rescources[i]);
                TooltipCompat.setTooltipText(iv_bidstate[index], tooltipValue[i]);
                index++;
            }
        }


    }

}
