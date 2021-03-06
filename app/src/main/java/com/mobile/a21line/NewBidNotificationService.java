package com.mobile.a21line;

import android.app.Notification;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by kwonhong on 2018-11-11.
 */

public class NewBidNotificationService extends Service {
    Context mContext;
    NewBidServiceThread thread;
    @Override
    public IBinder onBind(Intent intent){
        return null;
    }

    @Override
    public void onCreate(){
        super.onCreate();

        mContext = getApplicationContext();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            startForeground(1, new Notification());
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startID){
        NewBidHandler handler = new NewBidHandler();
        thread = new NewBidServiceThread(handler, mContext);
        thread.start();
        Log.d("Service State", "Start");
        return super.onStartCommand(intent, flags, startID);
    }

    @Override
    public void onDestroy(){
        thread.stopForever();
        SaveSharedPreference.setPrefNotiSerFlag(mContext, false);
        Log.d("Service State", "Quit");
        super.onDestroy();
    }

    class NewBidHandler extends Handler {
        @Override
        public void handleMessage(Message msg){
            SaveSharedPreference.checkHasNewBid(mContext);
        }
    }
}
