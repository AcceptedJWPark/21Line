package com.mobile.a21line.Result;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;
import com.mobile.a21line.SaveSharedPreference;
import com.mobile.a21line.Setbid.Setbid_BusinessSelect_ELVAdapter;
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

        btn_info = findViewById(R.id.btn_info_resultDetail);
        btn_result = findViewById(R.id.btn_result_resultDetail);
        btn_companylist = findViewById(R.id.btn_companylist_resultDetail);
        btn_firstCompany = findViewById(R.id.btn_firstCompany_resultDetail);
        ll_basicInfo = findViewById(R.id.ll_basicInfo_result_detail);

        clickedInfo();

        tv_bidTitle = findViewById(R.id.tv_bidTitle_Detail);
        tv_bidNo = findViewById(R.id.tv_bidNo_Detail);
        tv_bidOrder = findViewById(R.id.tv_bidOrder_Detail);
        tv_bidDemand = findViewById(R.id.tv_bidDemand_Detail);
        tv_bidCharger = findViewById(R.id.tv_bidCharger_Detail);
        tv_bidPhone = findViewById(R.id.tv_bidPhone_Detail);
        tv_bidLocation = findViewById(R.id.tv_bidLocation_Detail);
        tv_bidBusiness = findViewById(R.id.tv_bidBusiness_Detail);
        tv_bidPrice1 = findViewById(R.id.tv_bidPrice1_Detail);
        tv_bidPrice2 = findViewById(R.id.tv_bidPrice2_Detail);
        tv_bidLimitPrice = findViewById(R.id.tv_bidLimitPrice_Detail);
        tv_bidPercent = findViewById(R.id.tv_bidPercent_Detail);


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

                    Log.d("bidData = " , obj.toString());
                    tv_bidTitle.setText(obj.getString("BidName"));
                    tv_bidNo.setText(obj.getString("OrderBidHNum"));
                    tv_bidOrder.setText(obj.getString("OrderName"));
                    tv_bidCharger.setText(obj.getString("OrderManager"));
                    tv_bidPhone.setText(obj.getString("OrderPhone"));
                    tv_bidDemand.setText(obj.getString("DemandName"));

                    tv_bidPrice1.setText(toNumFormat(obj.getString("BasicPrice")) + "원");
                    tv_bidPrice2.setText(toNumFormat(obj.getString("EstimatedPrice")) + "원");
                    tv_bidLimitPrice.setText(obj.getString("CutPercent"));
                    tv_bidPercent.setText(obj.getString("YegaLow") + "%" + " ~ " + obj.getString("YegaHigh")+ "%");
                    tv_bidLocation.setText(obj.getString("AreaName"));
                    tv_bidBusiness.setText(obj.getString("UpcodeName"));
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
                        resultCom = resultComsArray.getJSONObject(0);
                    }

                    ((TextView)findViewById(R.id.tv_result_com_name)).setText(resultCom.getString("ComName"));
                    ((TextView)findViewById(R.id.tv_result_com_biz_no)).setText(resultCom.getString("BizNo"));
                    ((TextView)findViewById(R.id.tv_result_com_ceo_name)).setText(resultCom.getString("CeoName"));
                    ((TextView)findViewById(R.id.tv_result_com_phone)).setText(resultCom.getString("Phone"));
                    ((TextView)findViewById(R.id.tv_result_com_addr)).setText(resultCom.getString("Addr"));
                    ((TextView)findViewById(R.id.tv_result_com_joinPrice)).setText(toNumFormat(resultCom.getString("JoinPrice")) + " 원");
                    ((TextView)findViewById(R.id.tv_result_com_joinRate)).setText(resultCom.getString("JoinRate"));
                    ((TextView)findViewById(R.id.tv_result_com_RateRange)).setText(resultCom.getString("RateRange"));
                    ((TextView)findViewById(R.id.tv_result_com_BPercent)).setText(resultCom.getString("BPercent"));


                    JSONObject resultMain = obj.getJSONObject("ResultMain");
                    JSONArray boksu = resultMain.getJSONArray("aBoksu");
                    ((TextView)findViewById(R.id.tv_result_result_basicPrice)).setText(toNumFormat(resultMain.getString("BasicPrice")) + " 원");
                    ((TextView)findViewById(R.id.tv_result_result_decidingPrice)).setText(toNumFormat(resultMain.getString("DecidingPrice")) + " 원");
                    ((TextView)findViewById(R.id.tv_result_result_rateRange)).setText(resultMain.getString("RateRange"));
                    ((TextView)findViewById(R.id.tv_result_result_limitPrice)).setText(obj.getString("CutPercent"));
                    ((TextView)findViewById(R.id.tv_result_result_percent)).setText(obj.getString("YegaLow") + "%" + " ~ " + obj.getString("YegaHigh")+ "%");



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

}
