package com.mobile.a21line;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

/**
 * Created by kwonhong on 2018-11-11.
 */

public class NewBidServiceThread extends Thread{
    Handler handler;
    Context mContext;
    SimpleDateFormat sdf;

    public NewBidServiceThread(Handler handler, Context mContext){
        this.handler = handler;
        this.mContext = mContext;
        sdf = new SimpleDateFormat("HH:mm");
        sdf.setTimeZone(TimeZone.getTimeZone("Asia/Seoul"));
    }

    public void stopForever(){
        synchronized (this){
            SaveSharedPreference.setPrefNotiFlag(mContext, false);
            SaveSharedPreference.setPrefNotiSerFlag(mContext, false);
        }
    }

    public void run(){
        while(SaveSharedPreference.getNotiFlag(mContext)){
            Date now = new Date();
            Log.d("asd", "now : " + sdf.format(now) + ", STIME : " + SaveSharedPreference.getNotiStime(mContext) + ", ETIME : " + SaveSharedPreference.getNotiEtime(mContext));
            if(compareTime(SaveSharedPreference.getNotiStime(mContext), sdf.format(now)) && compareTime(sdf.format(now), SaveSharedPreference.getNotiEtime(mContext)))
            {
                handler.sendEmptyMessage(0);
            }
            try{
                Thread.sleep(SaveSharedPreference.getNotiTerm(mContext) * 3600 * 1000);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        SaveSharedPreference.setPrefNotiSerFlag(mContext, false);
    }

    private boolean compareTime(String time1, String time2){
        int parseTime1, parseTime2;
        String[] splitTime1, splitTime2;
        splitTime1 = time1.split(":");
        splitTime2 = time2.split(":");

        parseTime1 = Integer.parseInt(splitTime1[0]) * 60 + Integer.parseInt(splitTime1[1]);
        parseTime2 = Integer.parseInt(splitTime2[0]) * 60 + Integer.parseInt(splitTime2[1]);

        return parseTime1 <= parseTime2;


    }
}
