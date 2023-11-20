package com.mobile.a21line;

/**
 * Created by kwonhong on 2018-03-09.
 */

import android.app.ActivityManager;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.PowerManager;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mobile.a21line.Home.Home_Activity;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static android.app.Notification.DEFAULT_SOUND;
import static android.app.Notification.DEFAULT_VIBRATE;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    private static final String GROUP_KEY_ALARM = "Alarm";
    private static final int NEW_BID_NOTIFICATION_ID = 0;
    private static final int SUMMARY_NOTIFICATION_ID = 1;
    private static final String MY_CHANNEL_ID = "NOTIFICATION_CHANNEL_1";
    public static boolean isNewMessageArrive = false;

    private static NotificationManagerCompat notificationManagerCompat;

    private String alarmTxt = null;

    private Context mContext;


    private TimeZone time= TimeZone.getTimeZone("Asia/Seoul");

    private String topActivityName;


    private Intent intent1 = null;

    private String datas = null;
    private String type = null;
    /**
     * Called when message is received.
     *
     * @param remoteMessage Object representing the message received from Firebase Cloud Messaging.
     */
    // [START receive_message]
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {

        mContext = getApplicationContext();

        ActivityManager am = (ActivityManager) getSystemService(Context.ACTIVITY_SERVICE);
        List<ActivityManager.RunningTaskInfo> info = am.getRunningTasks(1);
        ComponentName topActivity = info.get(0).topActivity;
        topActivityName = topActivity.getClassName();


        // TODO(developer): Handle FCM messages here.
        // Not getting messages here? See why this may be: https://goo.gl/39bRNJ

        // Check if message contains a data payload.
        if (remoteMessage.getData().size() > 0) {
            Log.d(TAG, "Message data payload: " + remoteMessage.getData());

            datas = remoteMessage.getData().get("datas");
            type =  remoteMessage.getData().get("type");

            if (/* Check if data needs to be processed by long running job */ true) {
                // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                scheduleJob();
            } else {
                // Handle message within 10 seconds
                handleNow();
            }
            try {
                String Message = "";
                String TitleMessage = "21라인 새로운 맞춤정보 등록";
                JSONObject obj = new JSONObject(datas);
                if (type.equals("Both")) {
                    Message = "새로운 맞춤입찰 " + obj.getInt("bidNum") + "건, 맞춤낙찰 " + obj.getInt("resultNum") + "건이 있습니다.";
                } else if (type.equals("Bid")) {
                    Message = "새로운 맞춤입찰 " + obj.getInt("bidNum") + "건이 있습니다.";
                } else if (type.equals("Result")) {
                    Message = "새로운 맞춤낙찰 " + obj.getInt("resultNum") + "건이 있습니다.";
                }
                Intent intent = new Intent(mContext, Home_Activity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                if (!Message.isEmpty()) {
                    PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_CANCEL_CURRENT);
                    NotificationCompat.Builder mBuilder;
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(NotificationManager.class);
                        NotificationChannel channel = new NotificationChannel(MY_CHANNEL_ID,
                                "Channel human readable title",
                                NotificationManager.IMPORTANCE_HIGH);
                        channel.setShowBadge(false);

                        notificationManager.createNotificationChannel(channel);
                        mBuilder = new NotificationCompat.Builder(mContext, channel.getId());

                        Log.d("this is ", channel.getId());
                    } else {
                        mBuilder = new NotificationCompat.Builder(mContext, MY_CHANNEL_ID);

                    }
//
//                    Intent push = new Intent(this, Home_Activity.class);
//                    push.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

                    mBuilder.setSmallIcon(R.drawable.ic_stat_name)
                            .setLargeIcon(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.icon_logo))
                            .setPriority(Notification.PRIORITY_HIGH)
                            .setContentTitle(TitleMessage)
                            .setContentText(Message)
                            .setAutoCancel(true)
                            .setWhen(System.currentTimeMillis())
                            .setContentIntent(contentIntent);
                    if(SaveSharedPreference.getVibeFlag(mContext)) {
                            mBuilder.setDefaults(DEFAULT_SOUND | DEFAULT_VIBRATE);
                    }else{
                            mBuilder.setVibrate(new long[]{0, 0});
                    }
                    notificationManagerCompat = NotificationManagerCompat.from(mContext);
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                        NotificationCompat.Builder sBuilder =
                                new NotificationCompat.Builder(mContext, MY_CHANNEL_ID)
                                        .setContentTitle(TitleMessage)
                                        .setContentText(Message)
                                        .setSmallIcon(R.drawable.ic_stat_name)
                                        .setStyle(new NotificationCompat.InboxStyle()
                                                .setSummaryText(Message))
                                        //specify which group this notification belongs to
                                        .setGroup(GROUP_KEY_ALARM)
                                        //set this notification as the summary for the group
                                        .setGroupSummary(true)
                                        .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_CHILDREN)
                                        .setAutoCancel(true);
                        //summaryNotification.defaults = 0;

                        notificationManagerCompat.notify(SUMMARY_NOTIFICATION_ID, sBuilder.build());
                    }

                    mBuilder.setGroup(GROUP_KEY_ALARM);

                    if (Build.VERSION.SDK_INT <= 18) {
                        NotificationManager nm = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
                        nm.notify(NEW_BID_NOTIFICATION_ID, mBuilder.build());
                    } else {
                        notificationManagerCompat.notify(NEW_BID_NOTIFICATION_ID, mBuilder.build());
                    }

                    PowerManager pm = (PowerManager) mContext.getSystemService(Context.POWER_SERVICE);
                    boolean isScreenOn = pm.isScreenOn();
                    if (isScreenOn == false) {
                        PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.ON_AFTER_RELEASE,"21line:MyLock");
                        wl.acquire(10000);
                        PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"21line:MyCpuLock");
                        wl_cpu.acquire(10000);
                    }
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }

    }

    private void scheduleJob() {

    }
    /**
     * Handle time allotted to BroadcastReceivers.
     */
    private void handleNow() {
        Log.d(TAG, "Short lived task is done.");
    }

    /**
     * Create and show a simple notification containing the received FCM message.
     *
     * @param messageBody FCM message body received.
     */
    private void sendNotification(String messageBody) {
//        Intent intent = new Intent(this, MainActivity.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
//                PendingIntent.FLAG_ONE_SHOT);
//
//        String channelId = getString(R.string.default_notification_channel_id);
//        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//        NotificationCompat.Builder notificationBuilder =
//                new NotificationCompat.Builder(this, channelId)
//                        .setSmallIcon(R.drawable.ic_stat_ic_notification)
//                        .setContentTitle("FCM Message")
//                        .setContentText(messageBody)
//                        .setAutoCancel(true)
//                        .setSound(defaultSoundUri)
//                        .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager =
//                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//
//        // Since android Oreo notification channel is needed.
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            NotificationChannel channel = new NotificationChannel(channelId,
//                    "Channel human readable title",
//                    NotificationManager.IMPORTANCE_DEFAULT);
//            notificationManager.createNotificationChannel(channel);
//        }
//
//        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build());
    }

    private String dateFormat(String lastDate)
    {
        Date tempDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd,a hh:mm:ss");
        sdf.setTimeZone(time);
        String nowDateStr = sdf.format(tempDate);
        String[] nowDateTemp = nowDateStr.split(",");
        String nowDate = nowDateTemp[0];

        Log.d("LastDate, NowDate", lastDate + ", " + nowDateStr);

        String[] dateTemp = lastDate.split(",");
        lastDate = dateTemp[0];
        String dateTime = dateTemp[1].substring(0, 8);
        lastDate = (lastDate.equals(nowDate)) ? dateTime : lastDate;

        return lastDate;
    }

    private static MessageReceivedListener mMessageReceivedListener;
    public interface MessageReceivedListener{
        public void onMessageRecieved();
    }

    public static void setOnMessageReceivedListener(MessageReceivedListener listener){
        mMessageReceivedListener = listener;
    }

    private void update(){
        if(mMessageReceivedListener != null){
            mMessageReceivedListener.onMessageRecieved();
        }
    }


}
