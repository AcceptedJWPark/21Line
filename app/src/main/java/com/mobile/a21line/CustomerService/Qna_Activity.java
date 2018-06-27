package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
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

/**
 * Created by Accepted on 2018-05-14.
 */

public class Qna_Activity extends AppCompatActivity {

    Context mContext;


    ListView lv_qna;
    Qna_LVAdapter adapter;
    ArrayList<Qna_Listitem> arrayList;

    Button btn_question;
    Button btn_answer;
    LinearLayout ll_question;
    LinearLayout ll_answer;

    Button btn_qna_cs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.cs_qna_activity);

        mContext = getApplicationContext();

        ((TextView) findViewById(R.id.tv_toolbarTitle)).setText("질문/답변");
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setVisibility(View.VISIBLE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Menu)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Refresh)).setVisibility(View.GONE);
        ((ImageView) findViewById(R.id.img_toolbarIcon_Sorting)).setVisibility(View.GONE);
        ((TextView) findViewById(R.id.tv_toolbarIcon_Right)).setVisibility(View.GONE);

        ((ImageView) findViewById(R.id.img_toolbarIcon_Left_Back)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        lv_qna = findViewById(R.id.lv_qna_cs);
        btn_question = findViewById(R.id.btn_question_qna);
        btn_answer = findViewById(R.id.btn_answer_qna);
        ll_question = findViewById(R.id.ll_question_qna);
        ll_answer = findViewById(R.id.ll_answer_qna);

        arrayList = new ArrayList<>();


        getQNAList();
    }

    public void getQNAList(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Board/getBoardList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    arrayList.clear();
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");

                        Date regDate = new Date(o.getLong("RegDate"));
                        SimpleDateFormat sdf = new SimpleDateFormat("YY/MM/dd");
                        sdf.setTimeZone(time);
                        String date = sdf.format(regDate);

                        arrayList.add(new Qna_Listitem(String.valueOf(i+1),o.getString("Title"), date,o.getInt("HAS_ANSWER") > 0, o.getString("GGroup"), o.getString("Name"), o.getString("Content")));
                    }
                    adapter = new Qna_LVAdapter(Qna_Activity.this,arrayList);
                    lv_qna.setAdapter(adapter);

                    if(adapter.getCount() == 0)
                    {
                        lv_qna.setVisibility(View.GONE);
                        ((TextView)findViewById(R.id.tv_qna_cs)).setVisibility(View.VISIBLE);
                    }
                    else
                    {
                        lv_qna.setVisibility(View.VISIBLE);
                        ((TextView)findViewById(R.id.tv_qna_cs)).setVisibility(View.GONE);
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
                params.put("BoardName", "FreeBoard");
                params.put("PageRowLimit", String.valueOf(1000));
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

        btn_question.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedQuestion();
            }
        });

        btn_answer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickedAnswer();
            }
        });


    }

    private void clickedQuestion()
    {
        btn_question.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_question.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_question.setTypeface(null, Typeface.BOLD);
        btn_question.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_answer.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_answer.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_answer.setTypeface(null, Typeface.NORMAL);
        btn_answer.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        ll_question.setVisibility(View.VISIBLE);
        ll_answer.setVisibility(View.GONE);
    }

    private void clickedAnswer()
    {
        btn_answer.setBackgroundResource(R.drawable.bgr_btn_clicked);
        btn_answer.setTextColor(getResources().getColor(R.color.colorPrimaryDark));
        btn_answer.setTypeface(null, Typeface.BOLD);
        btn_answer.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnClicked));


        btn_question.setBackgroundResource(R.drawable.bgr_btn_unclicked);
        btn_question.setTextColor(getResources().getColor(R.color.textColor_unclicked));
        btn_question.setTypeface(null, Typeface.NORMAL);
        btn_question.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.Txt_btnUnClicked));

        ll_answer.setVisibility(View.VISIBLE);
        ll_question.setVisibility(View.GONE);
    }



}
