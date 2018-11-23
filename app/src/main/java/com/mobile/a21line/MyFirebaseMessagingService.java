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
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.mobile.a21line.Home.Home_Activity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class MyFirebaseMessagingService extends FirebaseMessagingService {
    private static final String TAG = "MyFirebaseMsgService";
    private static final String GROUP_KEY_ALARM = "Alarm";
    private static final int BOARD_NOTIFICATION_ID = 0;
    private static final int SUMMARY_NOTIFICATION_ID = 1;
    private static final String MY_CHANNEL_ID = "NOTIFICATION_CHANNEL_1";
    public static boolean isNewMessageArrive = false;

    private static NotificationManagerCompat notificationManagerCompat;

    private String alarmType = null;
    private String alarmTxt = null;

    private boolean messagePushGrant;
    private boolean conditionPushGrant;
    private boolean answerPushGrant;

    private Context mContext;


    private TimeZone time= TimeZone.getTimeZone("Asia/Seoul");

    private String topActivityName;


    private Intent intent1 = null;

    private String datas = null;
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
            //Log.d(TAG, "Message content: " + remoteMessage.getData().get("message"));
            if (remoteMessage.getData().get("type").equals("dupLogin")) {
                SaveSharedPreference.removePrefFcmToken(mContext);
                SaveSharedPreference.initPreference(mContext);
                Intent i = new Intent(mContext, Home_Activity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                i.putExtra("dupFlag", true);
                mContext.startActivity(i);
            } else {
                if (remoteMessage.getData().get("type").equals("Message")) {
                    getMessage(remoteMessage.getData().get("datas"));
                }

                datas = remoteMessage.getData().get("datas");

      //          addNotificationList(remoteMessage.getData().get("type"));
//                addAlarmList(remoteMessage.getData().get("type"));

                if (mMessageReceivedListener != null) {
                    update();
                }

                if (/* Check if data needs to be processed by long running job */ true) {
                    // For long-running tasks (10 seconds or more) use Firebase Job Dispatcher.
                    scheduleJob();
                } else {
                    // Handle message within 10 seconds
                    handleNow();
                }

                if (messagePushGrant || conditionPushGrant || answerPushGrant) {

                    // Check if message contains a notification payload.
                    if (remoteMessage.getNotification() != null) {
                        //Log.d(TAG, "Message Notification Body: " + remoteMessage.getNotification().getBody());
                    }

                    if (intent1 != null) {

                        PendingIntent contentIntent = PendingIntent.getActivity(this, 0, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
                        NotificationCompat.Builder mBuilder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationManager notificationManager = (NotificationManager) getSystemService(NotificationManager.class);
                            NotificationChannel channel = new NotificationChannel(MY_CHANNEL_ID,
                                    "Channel human readable title",
                                    NotificationManager.IMPORTANCE_DEFAULT);
                            notificationManager.createNotificationChannel(channel);
                            mBuilder = new NotificationCompat.Builder(this, channel.getId());

                            Log.d("this is ", channel.getId());
                        } else {
                            mBuilder = new NotificationCompat.Builder(this, MY_CHANNEL_ID);

                        }
                        mBuilder.setSmallIcon(R.drawable.ic_stat_name)
                                .setContentTitle(alarmTxt)
                                .setAutoCancel(true)
                                .setVibrate(new long[]{1, 1000})
                                .setDefaults(Notification.DEFAULT_SOUND)
                                .setWhen(System.currentTimeMillis())
                                .setContentIntent(contentIntent);
                        notificationManagerCompat = NotificationManagerCompat.from(this);
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                            Notification summaryNotification =
                                    new NotificationCompat.Builder(this, MY_CHANNEL_ID)
                                            .setContentTitle("새로운 알람이 있습니다.")
                                            .setSmallIcon(R.drawable.ic_stat_name)
                                            .setStyle(new NotificationCompat.InboxStyle()
                                                    .setSummaryText("알람을 확인하세요!"))
                                            //specify which group this notification belongs to
                                            .setGroup(GROUP_KEY_ALARM)
                                            //set this notification as the summary for the group
                                            .setGroupSummary(true)
                                            .setGroupAlertBehavior(Notification.GROUP_ALERT_CHILDREN)
                                            .setAutoCancel(true)
                                            .build();
                            summaryNotification.defaults = 0;
                            notificationManagerCompat.notify(SUMMARY_NOTIFICATION_ID, summaryNotification);
                        }

                        mBuilder.setGroup(GROUP_KEY_ALARM);

                        if(Build.VERSION.SDK_INT <= 18){
                            NotificationManager nm = (NotificationManager) getSystemService(mContext.NOTIFICATION_SERVICE);
                            nm.notify(BOARD_NOTIFICATION_ID, mBuilder.build());
                        }else {
                            notificationManagerCompat.notify(BOARD_NOTIFICATION_ID, mBuilder.build());
                        }

                    }
                }
            }
        }
    }


    private void addNotificationList(String type){
        intent1 = null;

        if (topActivityName.equals("MainActivity")) {
            return;
        } else {
            alarmType = "Message";
            alarmTxt = "새로운 메세지가 도착했습니다.";
            intent1 = new Intent(this,Home_Activity.class);
        }

    }

    private void getMessage(String datas){

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
