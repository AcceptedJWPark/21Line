package com.mobile.a21line.Setbid;

import android.content.Context;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;

import com.mobile.a21line.R;

import java.util.ArrayList;
import java.util.HashMap;


public class Setbid_BusinessSelect extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ArrayList<String> arrayList_Parent = new ArrayList<String>();
    private HashMap<String, ArrayList<String>> arrayList_Child = new HashMap<String, ArrayList<String>>();
    private Setbid_BusinessSelect_ELVAdapter elvAdapter;
    Context mContext;

    Button btn_save;

    Button btn_Cons;
    Button btn_Serv;
    Button btn_Purc;
    Button btn_Sell;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setbid_businessselect);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mContext = getApplicationContext();
        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        expandableListView = findViewById(R.id.elv_setbid_businessSelect);
        elvAdapter = new Setbid_BusinessSelect_ELVAdapter(Setbid_BusinessSelect.this,arrayList_Parent,arrayList_Child);


        mContext = getApplicationContext();

        btn_Cons = findViewById(R.id.btn_cons_setbid_business);
        btn_Serv = findViewById(R.id.btn_serv_setbid_business);
        btn_Purc = findViewById(R.id.btn_purc_setbid_business);
        btn_Sell = findViewById(R.id.btn_sell_setbid_business);


        setArray_Cons();
        clickedCons();
        expandableListView.setAdapter(elvAdapter);

        btn_Cons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setArray_Cons();
                clickedCons();
                expandableListView.setAdapter(elvAdapter);
            }
        });
        btn_Serv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setArray_Serv();
                clickedServ();
                expandableListView.setAdapter(elvAdapter);
            }
        });

        btn_Purc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setArray_Purc();
                clickedPurc();
                expandableListView.setAdapter(elvAdapter);
            }
        });

        btn_Sell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setArray_Sell();
                clickedSell();
                expandableListView.setAdapter(elvAdapter);
            }
        });

        btn_save = findViewById(R.id.btn_save_setbid_businessSelect);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    private void setArray_Cons()
    {
        arrayList_Parent.clear();
        arrayList_Child.clear();
        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList_Parent.add("일반건설정보");
        arrayList1.add("토목공사");
        arrayList1.add("토목건축공사");
        arrayList1.add("건축공사");
        arrayList1.add("조경공사");
        arrayList1.add("산업·환경설비공사");
        arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

        ArrayList<String> arrayList2 = new ArrayList<String>();
        arrayList_Parent.add("전기/통신/산림/환경/기타공사");
        arrayList2.add("전기공사");
        arrayList2.add("정보통신공사");
        arrayList2.add("일반소방시설(기계)");
        arrayList2.add("일반소방시설(전기)");
        arrayList2.add("전문소방시설공사");
        arrayList2.add("대기/수질방지시설업(소음, 진동방지)");
        arrayList2.add("신재생에너지(전체)");
        arrayList2.add("오수처리시설등설계,시공업");
        arrayList2.add("산림사업");
        arrayList2.add("골재채취업(육상골재)");
        arrayList2.add("기상장비업");
        arrayList_Child.put(arrayList_Parent.get(1),arrayList2);

        ArrayList<String> arrayList3 = new ArrayList<String>();
        arrayList_Parent.add("전문건설공사");
        arrayList3.add("실내건축");
        arrayList3.add("습식방수");
        arrayList3.add("도장공사");
        arrayList3.add("석면해체,제거업");
        arrayList3.add("지붕판금건축물조립");
        arrayList3.add("상하수도공사");
        arrayList3.add("철도궤도공사");
        arrayList3.add("수중공사");
        arrayList3.add("조경시설물설치공사");
        arrayList3.add("승강기설치");
        arrayList3.add("삭도설치,준설공사");
        arrayList3.add("기계설비공사업");
        arrayList3.add("난방공사");
        arrayList3.add("특정열사용기자재시공업");
        arrayList3.add("문화재공사");
        arrayList3.add("광해방지사업(토양개량,복원,정화)");
        arrayList3.add("토공공사");
        arrayList3.add("석공공사");
        arrayList3.add("비계구조물해체");
        arrayList3.add("금속구조물창호");
        arrayList3.add("철근콘크리트공사");
        arrayList3.add("보링그라우팅공사");
        arrayList3.add("포장공사");
        arrayList3.add("조경식재공사");
        arrayList3.add("강구조물공사");
        arrayList3.add("철강재설치공사");
        arrayList3.add("시설물유지관리");
        arrayList3.add("지하수개발업");
        arrayList3.add("가스시설공사");
        arrayList3.add("정비사업(재건축,재개발,재정비 등)");
        arrayList3.add("자연휴양림");
        arrayList_Child.put(arrayList_Parent.get(2),arrayList3);


    }

    private void setArray_Purc()
    {
        arrayList_Parent.clear();
        arrayList_Child.clear();
        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList_Parent.add("일반건설정보");
        arrayList1.add("토목공사");
        arrayList1.add("토목건축공사");
        arrayList1.add("건축공사");
        arrayList1.add("조경공사");
        arrayList1.add("산업·환경설비공사");
        arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

        ArrayList<String> arrayList2 = new ArrayList<String>();
        arrayList_Parent.add("전기/통신/산림/환경/기타공사");
        arrayList2.add("전기공사");
        arrayList2.add("정보통신공사");
        arrayList2.add("일반소방시설(기계)");
        arrayList2.add("일반소방시설(전기)");
        arrayList2.add("전문소방시설공사");
        arrayList2.add("대기/수질방지시설업(소음, 진동방지)");
        arrayList2.add("신재생에너지(전체)");
        arrayList2.add("오수처리시설등설계,시공업");
        arrayList2.add("산림사업");
        arrayList2.add("골재채취업(육상골재)");
        arrayList2.add("기상장비업");
        arrayList_Child.put(arrayList_Parent.get(1),arrayList2);

    }

    private void setArray_Serv()
    {
        arrayList_Parent.clear();
        arrayList_Child.clear();
        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList_Parent.add("전기/통신/산림/환경/기타공사");
        arrayList1.add("전기공사");
        arrayList1.add("정보통신공사");
        arrayList1.add("일반소방시설(기계)");
        arrayList1.add("일반소방시설(전기)");
        arrayList1.add("전문소방시설공사");
        arrayList1.add("대기/수질방지시설업(소음, 진동방지)");
        arrayList1.add("신재생에너지(전체)");
        arrayList1.add("오수처리시설등설계,시공업");
        arrayList1.add("산림사업");
        arrayList1.add("골재채취업(육상골재)");
        arrayList1.add("기상장비업");
        arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

    }
    private void setArray_Sell()
    {
        arrayList_Parent.clear();
        arrayList_Child.clear();
        ArrayList<String> arrayList1 = new ArrayList<String>();
        arrayList_Parent.add("일반건설정보");
        arrayList1.add("토목공사");
        arrayList1.add("토목건축공사");
        arrayList1.add("건축공사");
        arrayList1.add("조경공사");
        arrayList1.add("산업·환경설비공사");
        arrayList_Child.put(arrayList_Parent.get(0),arrayList1);

    }

    private void clickedCons()
    {
        btn_Cons.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_Cons.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_Cons.setTypeface(null, Typeface.BOLD);
        btn_Cons.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_Serv.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Serv.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Serv.setTypeface(null, Typeface.NORMAL);
        btn_Serv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_Purc.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Purc.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Purc.setTypeface(null, Typeface.NORMAL);
        btn_Purc.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_Sell.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Sell.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Sell.setTypeface(null, Typeface.NORMAL);
        btn_Sell.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
    }

    private void clickedPurc()
    {
        btn_Purc.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_Purc.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_Purc.setTypeface(null, Typeface.BOLD);
        btn_Purc.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_Serv.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Serv.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Serv.setTypeface(null, Typeface.NORMAL);
        btn_Serv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_Cons.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Cons.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Cons.setTypeface(null, Typeface.NORMAL);
        btn_Cons.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_Sell.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Sell.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Sell.setTypeface(null, Typeface.NORMAL);
        btn_Sell.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
    }

    private void clickedServ()
    {
        btn_Serv.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_Serv.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_Serv.setTypeface(null, Typeface.BOLD);
        btn_Serv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_Purc.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Purc.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Purc.setTypeface(null, Typeface.NORMAL);
        btn_Purc.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_Cons.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Cons.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Cons.setTypeface(null, Typeface.NORMAL);
        btn_Cons.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_Sell.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Sell.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Sell.setTypeface(null, Typeface.NORMAL);
        btn_Sell.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
    }

    private void clickedSell()
    {
        btn_Sell.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_Sell.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_Sell.setTypeface(null, Typeface.BOLD);
        btn_Sell.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_Purc.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Purc.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Purc.setTypeface(null, Typeface.NORMAL);
        btn_Purc.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_Cons.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Cons.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Cons.setTypeface(null, Typeface.NORMAL);
        btn_Cons.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        btn_Serv.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_Serv.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_Serv.setTypeface(null, Typeface.NORMAL);
        btn_Serv.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));
    }
}

