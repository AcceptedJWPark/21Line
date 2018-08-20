package com.mobile.a21line.CustomerService;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

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
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

/**
 * Created by Accepted on 2018-05-14.
 */

public class Qna_Detail_Activity extends AppCompatActivity {

    Context mContext;

    TextView tv_question_qna_detail;
    TextView tv_answer_qna_detail;

    String GGroup;
    boolean hasAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.cs_qna_detail_activity);

        Display display = ((WindowManager) getSystemService(Context.WINDOW_SERVICE)).getDefaultDisplay();
        mContext = getApplicationContext();

        GGroup = getIntent().getStringExtra("GGroup");
        hasAnswer = getIntent().getBooleanExtra("hasAnswer", false);
        int width = (int) (display.getWidth() * 1);
        int height = (int) (display.getHeight() * 0.9);
        getWindow().getAttributes().width = width;
        getWindow().getAttributes().height = height;

        mContext = getApplicationContext();

        ((TextView)findViewById(R.id.tv_qnaDetail_title)).setText(getIntent().getStringExtra("Title"));
        ((TextView)findViewById(R.id.tv_qnaDetail_comName)).setText(getIntent().getStringExtra("ComName"));
        ((TextView)findViewById(R.id.tv_qnaDetail_date)).setText(getIntent().getStringExtra("Date"));

        ((Button)findViewById(R.id.btn_cancel_qna_detail)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        tv_question_qna_detail = findViewById(R.id.tv_question_qna_detail);
        tv_answer_qna_detail = findViewById(R.id.tv_answer_qna_detail);
        tv_question_qna_detail.setText(getIntent().getStringExtra("Content"));

        if(hasAnswer){
            getQNAList();
        }else{
            tv_answer_qna_detail.setText("답변이 작성되지 않았습니다. 조속히 확인 후 답변드리겠습니다.");
        }

    }

    public void getQNAList(){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Board/getQNAAnswerList.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    StringBuilder sb = new StringBuilder();

                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        TimeZone time = TimeZone.getTimeZone("Asia/Seoul");

                        Date regDate = new Date(o.getLong("RegDate"));
                        SimpleDateFormat sdf = new SimpleDateFormat("yy/MM/dd");
                        sdf.setTimeZone(time);
                        String date = sdf.format(regDate);

                        sb.append(o.getString("Content"));
                        if(i < obj.length() - 1){
                            sb.append("\n=============================================\n");
                        }
                    }
                    tv_answer_qna_detail.setText(sb.toString());
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("GGroup", GGroup);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }
}
