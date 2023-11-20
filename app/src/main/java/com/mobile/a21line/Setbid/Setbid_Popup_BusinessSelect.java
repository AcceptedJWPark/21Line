package com.mobile.a21line.Setbid;

import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.mobile.a21line.BidUpCode;
import com.mobile.a21line.R;

import java.util.ArrayList;
import java.util.HashMap;


public class Setbid_Popup_BusinessSelect extends AppCompatActivity {

    private ExpandableListView expandableListView;
    private ArrayList<BidUpCode.BidUpCodeItem> arrayList_Parent = new ArrayList<BidUpCode.BidUpCodeItem>();
    static public ArrayList<BidUpCode.BidUpCodeItem> arrayUpcodeList = new ArrayList<>();;
    private HashMap<BidUpCode.BidUpCodeItem, ArrayList<BidUpCode.BidUpCodeItem>> arrayList_Child = new HashMap<BidUpCode.BidUpCodeItem, ArrayList<BidUpCode.BidUpCodeItem>>();
    private Setbid_BusinessSelect_ELVAdapter elvAdapter;
    Context mContext;

    Button btn_save;

    Button btn_Cons;
    Button btn_Serv;
    Button btn_Purc;



    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.setbid_businessselect);

        copyUpcodeList();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("업종분류표");
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.VISIBLE);
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setText("초기화");
        ((TextView)findViewById(R.id.tv_toolbarIcon_Right)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                arrayUpcodeList.clear();
                elvAdapter = new Setbid_BusinessSelect_ELVAdapter(Setbid_Popup_BusinessSelect.this,arrayList_Parent,arrayList_Child);
                expandableListView.setAdapter(elvAdapter);
            }
        });

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mContext = getApplicationContext();
        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        expandableListView = findViewById(R.id.elv_setbid_businessSelect);
        elvAdapter = new Setbid_BusinessSelect_ELVAdapter(Setbid_Popup_BusinessSelect.this,arrayList_Parent,arrayList_Child);


        mContext = getApplicationContext();

        btn_Cons = findViewById(R.id.btn_cons_setbid_business);
        btn_Serv = findViewById(R.id.btn_serv_setbid_business);
        btn_Purc = findViewById(R.id.btn_purc_setbid_business);


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


        ((ImageView)findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        btn_save = findViewById(R.id.btn_save_setbid_businessSelect);
        btn_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Setbid_Activity.arrayList_business.clear();
                for(BidUpCode.BidUpCodeItem item : arrayUpcodeList){
                    Setbid_Activity.arrayList_business.add(item);
                }
                finish();
            }
        });

    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        // TODO Auto-generated method stub
        Rect dialogBounds = new Rect();
        getWindow().getDecorView().getHitRect(dialogBounds);
        if (!dialogBounds.contains((int) ev.getX(), (int) ev.getY())) {
            // Tapped outside so we finish the activity
            finish();
        }
        return super.dispatchTouchEvent(ev);
    }

    private void setArray_Cons()
    {
        arrayList_Parent = BidUpCode.getArrayConsBidCodeParent();
        arrayList_Child = BidUpCode.getMapConsBidCodeChild();

        elvAdapter = new Setbid_BusinessSelect_ELVAdapter(Setbid_Popup_BusinessSelect.this,arrayList_Parent,arrayList_Child);
        expandableListView.setAdapter(elvAdapter);
    }

    private void setArray_Purc()
    {
        arrayList_Parent = BidUpCode.getArrayPurcBidCodeParent();
        arrayList_Child = BidUpCode.getMapPurcBidCodeChild();

        elvAdapter = new Setbid_BusinessSelect_ELVAdapter(Setbid_Popup_BusinessSelect.this,arrayList_Parent,arrayList_Child);
        expandableListView.setAdapter(elvAdapter);
    }

    private void setArray_Serv()
    {
        arrayList_Parent = BidUpCode.getArrayServBidCodeParent();
        arrayList_Child = BidUpCode.getMapServBidCodeChild();

        elvAdapter = new Setbid_BusinessSelect_ELVAdapter(Setbid_Popup_BusinessSelect.this,arrayList_Parent,arrayList_Child);
        expandableListView.setAdapter(elvAdapter);

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
    }

    private void copyUpcodeList(){
        arrayUpcodeList.clear();
        for(BidUpCode.BidUpCodeItem item : Setbid_Activity.arrayList_business){
            arrayUpcodeList.add(item);
        }
    }

}

