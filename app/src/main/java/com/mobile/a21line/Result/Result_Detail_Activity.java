package com.mobile.a21line.Result;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;
import com.mobile.a21line.Setbid.Setbid_BusinessSelect_ELVAdapter;

import java.util.ArrayList;
import java.util.HashMap;

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


    ExpandableListView elv_companylist;
    private ArrayList<Result_Detail_CompanyList_Parent_Listitem> arrayList_Parent = new ArrayList<Result_Detail_CompanyList_Parent_Listitem>();
    private HashMap<Result_Detail_CompanyList_Parent_Listitem, ArrayList<Result_Detail_CompanyList_Child_Listitem>> arrayList_Child = new HashMap<Result_Detail_CompanyList_Parent_Listitem, ArrayList<Result_Detail_CompanyList_Child_Listitem>>();
    private Result_Detail_CompanyList_ELVAdapter elvAdapter;
    View header_companylist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.result_detail_activity);

        mContext = getApplicationContext();

        btn_info = findViewById(R.id.btn_info_resultDetail);
        btn_result = findViewById(R.id.btn_result_resultDetail);
        btn_companylist = findViewById(R.id.btn_companylist_resultDetail);
        btn_firstCompany = findViewById(R.id.btn_firstCompany_resultDetail);
        ll_basicInfo = findViewById(R.id.ll_basicInfo_result_detail);

        clickedInfo();

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

        lv_multiple = findViewById(R.id.lv_result_detail_multiple);
        arrayList = new ArrayList();
        arrayList.add(new Result_Detail_Multiple_Listitem("1","2,029,133,700", "-2.5860","97.414",true));
        arrayList.add(new Result_Detail_Multiple_Listitem("2","2,021,405,700", "-2.9560","102.814",false));
        arrayList.add(new Result_Detail_Multiple_Listitem("3","2,111,683,000", "1.3560","97.414",false));
        arrayList.add(new Result_Detail_Multiple_Listitem("4","2,142,594,700", "-1.2860","94.414",false));
        arrayList.add(new Result_Detail_Multiple_Listitem("5","2,132,992,000", "-2.3260","100.14",false));
        arrayList.add(new Result_Detail_Multiple_Listitem("6","2,052,275,800", "-2.1120","99.4",false));
        arrayList.add(new Result_Detail_Multiple_Listitem("7","2,119,244,200", "-0.1860","97.01",false));
        arrayList.add(new Result_Detail_Multiple_Listitem("8","2,100,538,900", "-1.2260","99.065",true));
        arrayList.add(new Result_Detail_Multiple_Listitem("9","2,068,585,700", "2.1260","97.746",false));
        arrayList.add(new Result_Detail_Multiple_Listitem("10","2,083,083,400", "2.5860","97.532",false));
        arrayList.add(new Result_Detail_Multiple_Listitem("11","2,081,771,100", "-1.5230","96.123",true));
        arrayList.add(new Result_Detail_Multiple_Listitem("12","2,061,066,100", "2.5860","97.414",false));
        arrayList.add(new Result_Detail_Multiple_Listitem("13","2,103,621,700", "-1.5860","100.4",false));
        arrayList.add(new Result_Detail_Multiple_Listitem("14","2,043,402,200", "2.2360","100.211",true));
        arrayList.add(new Result_Detail_Multiple_Listitem("15","2,045,464,400", "-0.1260","102.114",false));


        adapter = new Result_Detail_Multiple_LVAdapter(mContext,arrayList);
        lv_multiple.addHeaderView(header_multiple);
        lv_multiple.setAdapter(adapter);
        setListViewHeightBasedOnItems(lv_multiple);


        elv_companylist = findViewById(R.id.elv_result_companyList);
        arrayList_Parent.add(new Result_Detail_CompanyList_Parent_Listitem("1", "동우건설 주식회사","42,419,300","88.042"));
        arrayList_Parent.add(new Result_Detail_CompanyList_Parent_Listitem("2", "(합자)금강건설","42,483,300","88.174"));
        arrayList_Parent.add(new Result_Detail_CompanyList_Parent_Listitem("3", "주식회사 삼우건설","42,538,440","88.289"));

        ArrayList<Result_Detail_CompanyList_Child_Listitem> arrayList1 = new ArrayList<Result_Detail_CompanyList_Child_Listitem>();
        ArrayList<Result_Detail_CompanyList_Child_Listitem> arrayList2 = new ArrayList<Result_Detail_CompanyList_Child_Listitem>();
        ArrayList<Result_Detail_CompanyList_Child_Listitem> arrayList3 = new ArrayList<Result_Detail_CompanyList_Child_Listitem>();
        arrayList1.add(new Result_Detail_CompanyList_Child_Listitem("동우건설 주식회사","123456789","박종우","010-0000-0000","경기도 파주시 광탄면"));
        arrayList2.add(new Result_Detail_CompanyList_Child_Listitem("동우건설 주식회사","123456789","박종우","010-0000-0000","경기도 파주시 광탄면"));
        arrayList3.add(new Result_Detail_CompanyList_Child_Listitem("동우건설 주식회사","123456789","박종우","010-0000-0000","경기도 파주시 광탄면"));

        arrayList_Child.put(arrayList_Parent.get(0),arrayList1);
        arrayList_Child.put(arrayList_Parent.get(1),arrayList2);
        arrayList_Child.put(arrayList_Parent.get(2),arrayList3);

        elvAdapter = new Result_Detail_CompanyList_ELVAdapter(mContext,arrayList_Parent,arrayList_Child);
        header_companylist = getLayoutInflater().inflate(R.layout.result_detail_companylist_header_bg, null, false);

        elv_companylist.addHeaderView(header_companylist);
        elv_companylist.setAdapter(elvAdapter);


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
}
