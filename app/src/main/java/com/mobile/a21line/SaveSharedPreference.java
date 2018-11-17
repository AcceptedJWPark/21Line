package com.mobile.a21line;

/**
 * Created by kwonhong on 2018-05-02.
 */


import android.app.Activity;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;
import com.mobile.a21line.Bid.Bid_Activity;
import com.mobile.a21line.CustomerService.Develope_Activity;
import com.mobile.a21line.CustomerService.Education_Activity;
import com.mobile.a21line.CustomerService.Mas_Activity;
import com.mobile.a21line.CustomerService.Notice_Activity;
import com.mobile.a21line.CustomerService.Notice_Detail_Activity;
import com.mobile.a21line.CustomerService.Qna_Activity;
import com.mobile.a21line.Home.Home_Activity;
import com.mobile.a21line.Library.Library_BidLimitPercent_Activity;
import com.mobile.a21line.Library.Library_BidablePrice_Activity;
import com.mobile.a21line.Library.Library_BusinessCondition_Activity;
import com.mobile.a21line.Login.Login_Activity;
import com.mobile.a21line.MyBid.MyBid_Activity;
import com.mobile.a21line.MyBid.MyBid_Request_Activity;
import com.mobile.a21line.MyBid.MyBid_Schedule_Activity;
import com.mobile.a21line.Result.Result_Activity;
import com.mobile.a21line.Search.Search_Bid_Activity;
import com.mobile.a21line.Setbid.Setbid_Activity;
import com.mobile.a21line.Setting.Setting_JoinAgreement_Activity;
import com.mobile.a21line.Setting.Setting_MessagePush_Activity;
import com.mobile.a21line.Setting.Setting_PrivateDataAgreement_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;


public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_ID = "userid";
    static final String PREF_USER_COM_NAME = "userComName";
    static final String PREF_IS_SERVICING = "isServicing";
    static final String PREF_SERVICE_TYPE = "serviceType";
    static final String PREF_SERVICE_DUE_DATE = "serviceDueDate";
    static final String PREF_USER_TYPE = "userType";
    static final String PREF_NOTI_FLAG = "notiFlag";
    static final String PREF_VIBE_FLAG = "vibeFlag";
    static final String PREF_NOTI_STIME = "notiSTime";
    static final String PREF_NOTI_ETIME = "notiETime";
    static final String PREF_NOTI_TERM = "notiTerm";
    static final String PREF_NOTI_SER_FLAG = "notiSerFlag";
    static final String SERVER_IP = "http://13.209.191.97/21LINE_Mobile/";
    static final String SERVER_IP2 = "http://119.193.35.130:80/21LINE_Mobile/";
    static final String IMAGE_URI = "http://13.124.141.242/21LINE_Mobile/";
    static final String IMAGE_URI2 = "http://119.193.35.174:8080/21LINE_Mobile/";
    static final String BID_DATA_URI = "http://new2.21line.co.kr/ajax/application/";
    static String myPicturePath = null;
    static String myThumbPicturePath = null;
    static String fcmToken = null;
    static final String PREF_FIRST_LOADING = "firstLoading";
    public static final String WIFI_STATE = "WIFI";
    public static final String MOBILE_STATE = "MOBILE";
    public static final String NONE_STATE = "NONE";
    public static boolean isFirstLogin = true;

    public static final String CONNECTION_CONFIRM_CLIENT_URL = "http://clients3.google.com/generate_204";

    private static boolean isDarwerOpened = false;

    static boolean isLogin;
    static boolean isService;


    static private boolean isNewBid = false;
    static private boolean isNewResult =false;
    static public boolean[] isNewBidArr = {false, false, false, false, false};
    static public boolean[] isNewResultArr = {false, false, false, false, false};

    public static void setPrefFirstLoading(boolean firstLoading, Context ctx)
    {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_FIRST_LOADING, firstLoading);
        editor.commit();
    }

    public static boolean getPrefFirstLoading(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_FIRST_LOADING, true);
    }


    public static boolean isNewBid(){
        return isNewBidArr[0] | isNewBidArr[1] | isNewBidArr[2] | isNewBidArr[3] | isNewBidArr[4];
    }

    public static boolean isNewResult(){
        return isNewResultArr[0] | isNewResultArr[1] | isNewResultArr[2] | isNewResultArr[3] | isNewResultArr[4];
    }

    static SharedPreferences getSharedPreferences(Context ctx) {
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setPrefUsrName(Context ctx, String userName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static void setPrefUserId(Context ctx, String userID) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_ID, userID);
        editor.commit();
    }

    public static void setPrefUserComName(Context ctx, String userComName) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_COM_NAME, userComName);
        editor.commit();
    }

    public static void setPrefIsServicing(Context ctx, boolean isServicing) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_IS_SERVICING, isServicing);
        editor.commit();
    }

    public static void setPrefServiceType(Context ctx, String serviceType) {
        String type;
        if (serviceType.contains("분석")) {
            type = "분석";
        } else {
            type = "조회";
        }

        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_SERVICE_TYPE, type);
        editor.commit();
    }

    public static void setPrefServiceDueDate(Context ctx, String serviceDueDate) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_SERVICE_DUE_DATE, serviceDueDate);
        editor.commit();
    }

    public static void setPrefUserType(Context ctx, String userType) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_TYPE, userType);
        editor.commit();
    }
    public static void setPrefNotiFlag(Context ctx, boolean isNoti) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_NOTI_FLAG, isNoti);
        editor.commit();
    }
    public static void setPrefVibeFlag(Context ctx, boolean isVibe) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_VIBE_FLAG, isVibe);
        editor.commit();
    }
    public static void setPrefNotiStime(Context ctx, String STime) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_NOTI_STIME, STime);
        editor.commit();
    }
    public static void setPrefNotiEtime(Context ctx, String ETime) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_NOTI_ETIME, ETime);
        editor.commit();
    }
    public static void setPrefNotiTerm(Context ctx, int term) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putInt(PREF_NOTI_TERM, term);
        editor.commit();
    }

    public static void setPrefNotiSerFlag(Context ctx, boolean isSer) {
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putBoolean(PREF_NOTI_SER_FLAG, isSer);
        editor.commit();
    }

    public static String getUserName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getUserID(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_ID, "");
    }

    public static String getUserComName(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_COM_NAME, "");
    }

    public static boolean getIsServicing(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_IS_SERVICING, false);
    }

    public static String getServiceType(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_SERVICE_TYPE, "");
    }

    public static String getServiceDueDate(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_SERVICE_DUE_DATE, "");
    }

    public static String getUserType(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_USER_TYPE, "");
    }

    public static boolean getNotiFlag(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_NOTI_FLAG, false);
    }
    public static boolean getVibeFlag(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_VIBE_FLAG, false);
    }
    public static String getNotiStime(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_NOTI_STIME, "09:00");
    }
    public static String getNotiEtime(Context ctx) {
        return getSharedPreferences(ctx).getString(PREF_NOTI_ETIME, "18:00");
    }
    public static int getNotiTerm(Context ctx) {
        return getSharedPreferences(ctx).getInt(PREF_NOTI_TERM, 1);
    }

    public static boolean getNotiSerFlag(Context ctx) {
        return getSharedPreferences(ctx).getBoolean(PREF_NOTI_SER_FLAG, false);
    }

    public static String getServerIp() {
        return SERVER_IP;
    }

    public static String getBidDataUri() { return BID_DATA_URI; }

    public static void initPreference(Context ctx){
        initNewFlags();
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.clear();
        editor.commit();
    }

    public static void hideKeyboard(View view, Context context) {
        InputMethodManager inputMethodManager = (InputMethodManager) context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static Response.ErrorListener getErrorListener(final Context context) {
        Response.ErrorListener errorListener = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                NetworkResponse response = error.networkResponse;
                if (error instanceof ServerError && response != null) {
                    try {
                        String res = new String(response.data,
                                HttpHeaderParser.parseCharset(response.headers, "utf-8"));
                        // Now you can use any deserializer to make sense of data
                        Log.d("res", res);
                        Toast.makeText(context, "네트워크 상태를 확인해주세요.", Toast.LENGTH_SHORT).show();

                        JSONObject obj = new JSONObject(res);
                    } catch (UnsupportedEncodingException e1) {
                        // Couldn't properly decode data to string
                        e1.printStackTrace();
                    } catch (JSONException e2) {
                        // returned data is not JSONObject?
                        e2.printStackTrace();
                    }
                }

                if(error instanceof com.android.volley.TimeoutError){
                    Toast.makeText(context, "서버통신에 문제가 발생하였습니다.", Toast.LENGTH_SHORT).show();
                }
            }
        };

        return errorListener;
    }

    public static void DrawerLayout_clickedBgr(Context mContext, TextView clickedtextView,
                                               TextView textView2, TextView textView3, TextView textView4, TextView textView5, TextView textView6, TextView textView7, TextView textView8
    ) {
        clickedtextView.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        clickedtextView.setTypeface(null, Typeface.BOLD);
        clickedtextView.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main_big));

        textView2.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
        textView3.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
        textView4.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
        textView5.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
        textView6.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
        textView7.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
        textView8.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
        textView2.setTypeface(null, Typeface.NORMAL);
        textView3.setTypeface(null, Typeface.NORMAL);
        textView4.setTypeface(null, Typeface.NORMAL);
        textView5.setTypeface(null, Typeface.NORMAL);
        textView6.setTypeface(null, Typeface.NORMAL);
        textView7.setTypeface(null, Typeface.NORMAL);
        textView8.setTypeface(null, Typeface.NORMAL);
        textView2.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main));
        textView3.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main));
        textView4.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main));
        textView5.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main));
        textView6.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main));
        textView7.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main));
        textView8.setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main));

    }



    public static void DrawerLayout_Open(View view, final Context mContext, DrawerLayout drawerLayout, View frameLayout) {
        getMemberData(mContext);
        TextView tv_new_bid_dl = ((Activity) mContext).findViewById(R.id.tv_new_bid_dl);
        TextView tv_new_result_dl = ((Activity) mContext).findViewById(R.id.tv_new_result_dl);
        if(!isDarwerOpened && !getUserID(mContext).isEmpty() && getIsServicing(mContext) && view.getId() == R.id.img_toolbarIcon_Left_Menu){
            tv_new_bid_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
            tv_new_result_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
            getMypageGroup(mContext);
            isDarwerOpened = true;
        }

        setUserInfoToDrawer(mContext);

        isLogin = !getUserID(mContext).isEmpty();
        isService = getIsServicing(mContext);

        final AlertDialog.Builder loginDialog = new AlertDialog.Builder(mContext);
        loginDialog.setMessage("로그인이 필요한 페이지입니다.")
                .setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mContext,Login_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        ((Activity)mContext).finish();
                        dialog.cancel();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });


        final AlertDialog.Builder extensionDialog = new AlertDialog.Builder(mContext);
        extensionDialog.setMessage("기간연장 신청 하시겠습니까?")
                .setPositiveButton("신청하기", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(final DialogInterface dialog, int which) {
                        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
                        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, getBidDataUri() + "requestSettle.php", new Response.Listener<String>(){
                            @Override
                            public void onResponse(String response){
                                try {
                                    JSONObject obj = new JSONObject(response);
                                    if(obj.getString("result").equals("success")){
                                        Toast.makeText(mContext, "기간연장 신청 완료되었습니다.", Toast.LENGTH_SHORT).show();
                                        dialog.cancel();
                                    }else{
                                        Toast.makeText(mContext, "신청이 실패했습니다. 고객센터로 연락부탁드립니다.", Toast.LENGTH_SHORT).show();
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
                                params.put("MemID", getUserID(mContext));
                                return params;
                            }
                        };

                        postRequestQueue.add(postJsonRequest);
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        isDarwerOpened = false;
        TextView tv_home_dl = ((Activity) mContext).findViewById(R.id.tv_home_dl);
        TextView tv_txt1_dl = ((Activity) mContext).findViewById(R.id.tv_txt1_dl);
        TextView tv_txt2_dl = ((Activity) mContext).findViewById(R.id.tv_txt2_dl);
        TextView tv_library_dl = ((Activity) mContext).findViewById(R.id.tv_library_dl);
        TextView tv_bid_dl = ((Activity) mContext).findViewById(R.id.tv_bid_dl);
        TextView tv_result_dl = ((Activity) mContext).findViewById(R.id.tv_result_dl);

        TextView tv_mybid_dl = ((Activity) mContext).findViewById(R.id.tv_mybid_dl);
        TextView tv_search_dl = ((Activity) mContext).findViewById(R.id.tv_search_dl);
        TextView tv_cs_dl = ((Activity) mContext).findViewById(R.id.tv_cs_dl);
        TextView tv_setting_dl = ((Activity) mContext).findViewById(R.id.tv_setting_dl);
        TextView tv_extension_dl = ((Activity) mContext).findViewById(R.id.icon_extension_drawer);

        ImageView iv_home_dl = ((Activity) mContext).findViewById(R.id.iv_home_dl);
        ImageView iv_library_dl = ((Activity) mContext).findViewById(R.id.iv_library_dl);
        ImageView iv_bid_dl = ((Activity) mContext).findViewById(R.id.iv_bid_dl);
        ImageView iv_result_dl = ((Activity) mContext).findViewById(R.id.iv_result_dl);
        ImageView iv_mybid_dl = ((Activity) mContext).findViewById(R.id.iv_mybid_dl);
        ImageView iv_search_dl = ((Activity) mContext).findViewById(R.id.iv_search_dl);
        ImageView iv_cs_dl = ((Activity) mContext).findViewById(R.id.iv_cs_dl);
        ImageView iv_setting_dl = ((Activity) mContext).findViewById(R.id.iv_setting_dl);

        DrawerLayout_clickedBgr(mContext, tv_bid_dl, tv_library_dl, tv_home_dl, tv_result_dl, tv_mybid_dl, tv_search_dl, tv_cs_dl, tv_setting_dl);
        ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.VISIBLE);
        ((Activity) mContext).findViewById(R.id.inc_bidresult_dl).setVisibility(View.GONE);
        ((Activity) mContext).findViewById(R.id.inc_library_dl).setVisibility(View.GONE);
        ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
        ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
        ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);


        tv_new_bid_dl.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
        tv_new_bid_dl.setTypeface(null, Typeface.BOLD);



        iv_home_dl.setImageResource(R.drawable.icon_home_dl);
        iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
        iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
        iv_bid_dl.setImageResource(R.drawable.icon_clicked_bid_dl);
        iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
        iv_library_dl.setImageResource(R.drawable.icon_unclicked_library_dl);
        iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
        iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);

        tv_txt1_dl.setText("맞춤입찰");
        tv_txt2_dl.setText("맞춤 입찰공고를 확인합니다.");

        tv_extension_dl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alertDialog = extensionDialog.create();
                alertDialog.show();
                alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
            }
        });

        switch (view.getId()) {
            case R.id.img_toolbarIcon_Left_Menu: {
                drawerLayout.openDrawer(frameLayout);
                if(mContext instanceof Bid_Activity && !((Bid_Activity)mContext).isTotalSearch()){
                    view.setId(R.id.ll_bid_dl);
                    DrawerLayout_Open(view, mContext, drawerLayout, frameLayout);
                }else if(mContext instanceof Result_Activity && !((Result_Activity)mContext).isTotalSearch()){
                    view.setId(R.id.ll_result_dl);
                    DrawerLayout_Open(view, mContext, drawerLayout, frameLayout);
                }else if(mContext instanceof MyBid_Schedule_Activity || mContext instanceof MyBid_Activity || mContext instanceof MyBid_Request_Activity){
                    view.setId(R.id.ll_mybid_dl);
                    DrawerLayout_Open(view, mContext, drawerLayout, frameLayout);
                }else if((mContext instanceof Bid_Activity && ((Bid_Activity)mContext).isTotalSearch()) || (mContext instanceof Result_Activity && ((Result_Activity)mContext).isTotalSearch()) || mContext instanceof Search_Bid_Activity){
                    view.setId(R.id.ll_search_dl);
                    DrawerLayout_Open(view, mContext, drawerLayout, frameLayout);
                }else if(mContext instanceof Library_BidablePrice_Activity || mContext instanceof Library_BidLimitPercent_Activity || mContext instanceof Library_BusinessCondition_Activity){
                    view.setId(R.id.ll_library_dl);
                    DrawerLayout_Open(view, mContext, drawerLayout, frameLayout);
                }else if(mContext instanceof Education_Activity || mContext instanceof Mas_Activity || mContext instanceof Notice_Detail_Activity || mContext instanceof Qna_Activity){
                    view.setId(R.id.ll_cs_dl);
                    DrawerLayout_Open(view, mContext, drawerLayout, frameLayout);
                }else if(mContext instanceof Setting_JoinAgreement_Activity || mContext instanceof Setting_PrivateDataAgreement_Activity){
                    view.setId(R.id.ll_setting_dl);
                    DrawerLayout_Open(view, mContext, drawerLayout, frameLayout);
                }
                view.setId(R.id.img_toolbarIcon_Left_Menu);
                break;
            }

            case R.id.img_close_dl: {
                drawerLayout.closeDrawer(frameLayout);
                break;
            }

            case R.id.ll_home_dl: {
                drawerLayout.closeDrawer(frameLayout);
                DrawerLayout_clickedBgr(mContext, tv_home_dl, tv_library_dl, tv_bid_dl, tv_result_dl, tv_mybid_dl, tv_search_dl, tv_cs_dl, tv_setting_dl);
                Intent i = new Intent(mContext, Home_Activity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                mContext.startActivity(i);
                ((Activity) mContext).finish();

                break;
            }

            case R.id.ll_library_dl: {
                DrawerLayout_clickedBgr(mContext, tv_library_dl, tv_home_dl, tv_bid_dl, tv_result_dl, tv_mybid_dl, tv_search_dl, tv_cs_dl, tv_setting_dl);

                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_bidresult_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_library_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_search_dl).setVisibility(View.GONE);

                iv_home_dl.setImageResource(R.drawable.icon_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
                iv_library_dl.setImageResource(R.drawable.icon_clicked_library_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);

                tv_new_bid_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_bid_dl.setTypeface(null, Typeface.NORMAL);
                tv_new_result_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_result_dl.setTypeface(null, Typeface.NORMAL);



                tv_txt1_dl.setText("입찰 자료실");
                tv_txt2_dl.setText("입찰 관련 자료를 확인합니다.");

                final RelativeLayout[] rl_search_dl_contents = new RelativeLayout[3];
                rl_search_dl_contents[0] = ((Activity) mContext).findViewById(R.id.rl_library1);
                rl_search_dl_contents[1] = ((Activity) mContext).findViewById(R.id.rl_library2);
                rl_search_dl_contents[2] = ((Activity) mContext).findViewById(R.id.rl_library3);

                rl_search_dl_contents[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                        mContext.sendBroadcast(finishIntent);

                        Intent intent = new Intent(mContext, Library_BusinessCondition_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });
                rl_search_dl_contents[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                        mContext.sendBroadcast(finishIntent);

                        Intent intent = new Intent(mContext, Library_BidablePrice_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });

                rl_search_dl_contents[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                        mContext.sendBroadcast(finishIntent);

                        Intent intent = new Intent(mContext, Library_BidLimitPercent_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });

                break;
            }

            case R.id.ll_bid_dl: {
                DrawerLayout_clickedBgr(mContext, tv_bid_dl, tv_home_dl, tv_library_dl, tv_result_dl, tv_mybid_dl, tv_search_dl, tv_cs_dl, tv_setting_dl);
                tv_txt1_dl.setText("맞춤입찰");
                tv_txt2_dl.setText("맞춤 입찰공고를 확인합니다.");
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_bidresult_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_library_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_search_dl).setVisibility(View.GONE);



                iv_home_dl.setImageResource(R.drawable.icon_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_clicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
                iv_library_dl.setImageResource(R.drawable.icon_unclicked_library_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);

                tv_new_bid_dl.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
                tv_new_bid_dl.setTypeface(null, Typeface.BOLD);
                tv_new_result_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_result_dl.setTypeface(null, Typeface.NORMAL);

                break;
            }

            case R.id.ll_result_dl: {

                DrawerLayout_clickedBgr(mContext, tv_result_dl, tv_bid_dl, tv_home_dl, tv_library_dl, tv_mybid_dl, tv_search_dl, tv_cs_dl, tv_setting_dl);
                tv_txt1_dl.setText("맞춤낙찰");
                tv_txt2_dl.setText("맞춤 낙찰공고를 확인합니다.");
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_bidresult_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_library_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_search_dl).setVisibility(View.GONE);

                iv_home_dl.setImageResource(R.drawable.icon_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_clicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
                iv_library_dl.setImageResource(R.drawable.icon_unclicked_library_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);

                tv_new_result_dl.setTextColor(mContext.getResources().getColor(R.color.colorPrimaryDark));
                tv_new_result_dl.setTypeface(null, Typeface.BOLD);
                tv_new_bid_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_bid_dl.setTypeface(null, Typeface.NORMAL);

                break;
            }

            case R.id.ll_mybid_dl: {

                DrawerLayout_clickedBgr(mContext, tv_mybid_dl, tv_bid_dl, tv_home_dl, tv_library_dl, tv_result_dl, tv_search_dl, tv_cs_dl, tv_setting_dl);
                tv_txt1_dl.setText("내 서류함");
                tv_txt2_dl.setText("회원님의 서류함을 확인합니다.");
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_bidresult_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_library_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_search_dl).setVisibility(View.GONE);

                iv_home_dl.setImageResource(R.drawable.icon_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_clicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
                iv_library_dl.setImageResource(R.drawable.icon_unclicked_library_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);

                tv_new_bid_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_bid_dl.setTypeface(null, Typeface.NORMAL);
                tv_new_result_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_result_dl.setTypeface(null, Typeface.NORMAL);

                final TextView[] tv_mybid = new TextView[2];
                final RelativeLayout rl_mybid;
                tv_mybid[0] = ((Activity) mContext).findViewById(R.id.tv_mybid_mybid);
                tv_mybid[1] = ((Activity) mContext).findViewById(R.id.tv_schedule_mybid);
                rl_mybid = ((Activity) mContext).findViewById(R.id.rl_analysis_mybid);


                tv_mybid[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!isLogin)
                        {
                            AlertDialog alertDialog = loginDialog.create();
                            alertDialog.show();
                            alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                            alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));

                        }
                        else if(!isService)
                        {
                            Toast.makeText(mContext,"중지중 회원입니다. 서비스 연장 후 사용 가능합니다.",Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                            mContext.sendBroadcast(finishIntent);

                            Intent intent = new Intent(mContext, MyBid_Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }
                    }
                });

                tv_mybid[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!isLogin)
                        {
                            AlertDialog alertDialog = loginDialog.create();
                            alertDialog.show();
                            alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                            alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                        }
                        else if(!isService)
                        {
                            Toast.makeText(mContext,"중지중 회원입니다. 서비스 연장 후 사용 가능합니다.",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                            mContext.sendBroadcast(finishIntent);

                            Intent intent = new Intent(mContext, MyBid_Schedule_Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }
                    }
                });
                rl_mybid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!isLogin)
                        {
                            AlertDialog alertDialog = loginDialog.create();
                            alertDialog.show();
                            alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                            alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));

                        }
                        else if(!isService)
                        {
                            Toast.makeText(mContext,"중지중 회원입니다. 서비스 연장 후 사용 가능합니다.",Toast.LENGTH_SHORT).show();
                        }
                        else if(!getServiceType(mContext).equals("분석"))
                        {
                            Toast.makeText(mContext,"21라인 웹 사이트에서 분석 서비스 신청이 가능합니다.",Toast.LENGTH_SHORT).show();
                        }
                        else
                        {
                            Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                            mContext.sendBroadcast(finishIntent);

                            Intent intent = new Intent(mContext, MyBid_Request_Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }
                    }
                });

                break;
            }

            case R.id.ll_search_dl: {
                DrawerLayout_clickedBgr(mContext, tv_search_dl, tv_bid_dl, tv_home_dl, tv_library_dl, tv_result_dl, tv_mybid_dl, tv_cs_dl, tv_setting_dl);
                tv_txt1_dl.setText("통합 검색");
                tv_txt2_dl.setText("입찰, 낙찰 공고를 검색합니다.");
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_bidresult_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_library_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_search_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);

                iv_home_dl.setImageResource(R.drawable.icon_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
                iv_library_dl.setImageResource(R.drawable.icon_unclicked_library_dl);
                iv_search_dl.setImageResource(R.drawable.icon_clicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);

                tv_new_bid_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_bid_dl.setTypeface(null, Typeface.NORMAL);
                tv_new_result_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_result_dl.setTypeface(null, Typeface.NORMAL);



                final RelativeLayout[] rl_search_dl_contents = new RelativeLayout[2];
                rl_search_dl_contents[0] = ((Activity) mContext).findViewById(R.id.rl_search_bid);
                rl_search_dl_contents[1] = ((Activity) mContext).findViewById(R.id.rl_search_result);

                rl_search_dl_contents[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!isLogin)
                        {
                            AlertDialog alertDialog = loginDialog.create();
                            alertDialog.show();
                            alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                            alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                        }
                        else if(!isService)
                        {
                            Toast.makeText(mContext,"중지중 회원입니다. 서비스 연장 후 사용 가능합니다.",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                            mContext.sendBroadcast(finishIntent);

                            Intent intent = new Intent(mContext, Search_Bid_Activity.class);
                            //intent.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_MULTIPLE_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }
                    }
                });
                rl_search_dl_contents[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!isLogin)
                        {
                            AlertDialog alertDialog = loginDialog.create();
                            alertDialog.show();
                            alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                            alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));

                        }
                        else if(!isService)
                        {
                            Toast.makeText(mContext,"중지중 회원입니다. 서비스 연장 후 사용 가능합니다.",Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                            mContext.sendBroadcast(finishIntent);

                            Intent intent = new Intent(mContext, Search_Bid_Activity.class);
                            //intent.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_MULTIPLE_TASK);
                            intent.putExtra("isBid", false);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                            Log.d("클릭됨", "클릭됨");
                        }
                    }
                });
                break;
            }

            case R.id.ll_cs_dl: {
                DrawerLayout_clickedBgr(mContext, tv_cs_dl, tv_bid_dl, tv_home_dl, tv_library_dl, tv_result_dl, tv_mybid_dl, tv_search_dl, tv_setting_dl);
                tv_txt1_dl.setText("고객센터");
                tv_txt2_dl.setText("회원님의 불편함을 해결해드립니다.");
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_bidresult_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_library_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_search_dl).setVisibility(View.GONE);

                iv_home_dl.setImageResource(R.drawable.icon_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
                iv_library_dl.setImageResource(R.drawable.icon_unclicked_library_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_clicked_cs_dl);

                tv_new_bid_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_bid_dl.setTypeface(null, Typeface.NORMAL);
                tv_new_result_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_result_dl.setTypeface(null, Typeface.NORMAL);




                final RelativeLayout[] rl_cs_dl_contents = new RelativeLayout[6];
                rl_cs_dl_contents[0] = ((Activity) mContext).findViewById(R.id.rl_cs_notice_dl_contents1);
                rl_cs_dl_contents[1] = ((Activity) mContext).findViewById(R.id.rl_cs_question_dl_contents2);
                rl_cs_dl_contents[2] = ((Activity) mContext).findViewById(R.id.rl_cs_call_dl_contents3);
                rl_cs_dl_contents[3] = ((Activity) mContext).findViewById(R.id.rl_cs_education_dl_contents4);
                rl_cs_dl_contents[4] = ((Activity) mContext).findViewById(R.id.rl_cs_mas_dl_contents5);
                rl_cs_dl_contents[5] = ((Activity) mContext).findViewById(R.id.rl_cs_develope_dl_contents6);

                rl_cs_dl_contents[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                        mContext.sendBroadcast(finishIntent);

                        Intent intent = new Intent(mContext, Notice_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                            }
                });

                rl_cs_dl_contents[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(!isLogin)
                        {
                            AlertDialog alertDialog = loginDialog.create();
                            alertDialog.show();
                            alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                            alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                        }
                        else {
                            Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                            mContext.sendBroadcast(finishIntent);

                            Intent intent = new Intent(mContext, Qna_Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }
                            }
                });

                rl_cs_dl_contents[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1599-2127"));
                        i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(i);
                    }
                });


                rl_cs_dl_contents[3].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                        mContext.sendBroadcast(finishIntent);

                        Intent intent = new Intent(mContext, Education_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });

                rl_cs_dl_contents[4].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                        mContext.sendBroadcast(finishIntent);

                        Intent intent = new Intent(mContext, Mas_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });

                rl_cs_dl_contents[5].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Develope_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });

                break;
            }

            case R.id.ll_setting_dl: {
                DrawerLayout_clickedBgr(mContext, tv_setting_dl, tv_cs_dl, tv_bid_dl, tv_home_dl, tv_library_dl, tv_result_dl, tv_mybid_dl, tv_search_dl);
                DrawerLayout_clickedBgr(mContext, tv_setting_dl, tv_cs_dl, tv_bid_dl, tv_home_dl, tv_library_dl, tv_result_dl, tv_mybid_dl, tv_search_dl);
                tv_txt1_dl.setText("설 정");
                tv_txt2_dl.setText("이용약관, 로그인 등을 확인합니다.");
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_bidresult_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_library_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_search_dl).setVisibility(View.GONE);

                iv_home_dl.setImageResource(R.drawable.icon_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_clicked_setting_dl);
                iv_library_dl.setImageResource(R.drawable.icon_unclicked_library_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);

                tv_new_bid_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_bid_dl.setTypeface(null, Typeface.NORMAL);
                tv_new_result_dl.setTextColor(mContext.getResources().getColor(R.color.textColor_addition));
                tv_new_result_dl.setTypeface(null, Typeface.NORMAL);




                ((TextView)((Activity) mContext).findViewById(R.id.tv_privateInfo1_dl)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                        mContext.sendBroadcast(finishIntent);

                        Intent intent = new Intent(mContext, Setting_PrivateDataAgreement_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });

                ((TextView)((Activity) mContext).findViewById(R.id.tv_pushmessage_dl)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                        mContext.sendBroadcast(finishIntent);

                        Intent intent = new Intent(mContext, Setting_MessagePush_Activity.class);
                        mContext.startActivity(intent);
                    }
                });






                ((TextView)((Activity) mContext).findViewById(R.id.tv_privateInfo2_dl)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                        mContext.sendBroadcast(finishIntent);

                        Intent intent = new Intent(mContext, Setting_JoinAgreement_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                    }
                });


                isLogin = !SaveSharedPreference.getUserID(mContext).isEmpty();
                if(isLogin)
                {
                    ((TextView)((Activity) mContext).findViewById(R.id.tv_logout_dl)).setText("로그아웃");
                    ((TextView)((Activity) mContext).findViewById(R.id.tv_logout_dl)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            SaveSharedPreference.initPreference(mContext);
                            Toast.makeText(mContext, "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(mContext,Home_Activity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }
                    });
                }else
                {
                    ((TextView)((Activity) mContext).findViewById(R.id.tv_logout_dl)).setText("로그인");
                    ((TextView)((Activity) mContext).findViewById(R.id.tv_logout_dl)).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent intent = new Intent(mContext, Login_Activity.class);
                            ((Activity)mContext).finish();
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            mContext.startActivity(intent);
                        }
                    });
                }

                break;
            }
        }
    }

    public static void DrawerLayout_ClickEvent(Context context, View.OnClickListener listener) {
        ((ImageView) ((Activity) context).findViewById(R.id.img_toolbarIcon_Left_Menu)).setOnClickListener(listener);
        ((ImageView) ((Activity) context).findViewById(R.id.img_close_dl)).setOnClickListener(listener);
        ((LinearLayout) ((Activity) context).findViewById(R.id.ll_home_dl)).setOnClickListener(listener);
        ((LinearLayout) ((Activity) context).findViewById(R.id.ll_library_dl)).setOnClickListener(listener);
        ((LinearLayout) ((Activity) context).findViewById(R.id.ll_bid_dl)).setOnClickListener(listener);
        ((LinearLayout) ((Activity) context).findViewById(R.id.ll_result_dl)).setOnClickListener(listener);
        ((LinearLayout) ((Activity) context).findViewById(R.id.ll_mybid_dl)).setOnClickListener(listener);
        ((LinearLayout) ((Activity) context).findViewById(R.id.ll_search_dl)).setOnClickListener(listener);
        ((LinearLayout) ((Activity) context).findViewById(R.id.ll_cs_dl)).setOnClickListener(listener);
        ((LinearLayout) ((Activity) context).findViewById(R.id.ll_setting_dl)).setOnClickListener(listener);
    }

    private static void getMypageGroup(final Context mContext){

        final AlertDialog.Builder loginDialog = new AlertDialog.Builder(mContext);
        loginDialog.setMessage("로그인이 필요한 페이지입니다.")
                .setPositiveButton("로그인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(mContext,Login_Activity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        mContext.startActivity(intent);
                        dialog.cancel();
                    }
                })
                .setNegativeButton("취소", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        final TextView[] tv_group_name_bidset = new TextView[5];
        tv_group_name_bidset[0] = ((Activity) mContext).findViewById(R.id.tv_group_name_1_bidset);
        tv_group_name_bidset[1] = ((Activity) mContext).findViewById(R.id.tv_group_name_2_bidset);
        tv_group_name_bidset[2] = ((Activity) mContext).findViewById(R.id.tv_group_name_3_bidset);
        tv_group_name_bidset[3] = ((Activity) mContext).findViewById(R.id.tv_group_name_4_bidset);
        tv_group_name_bidset[4] = ((Activity) mContext).findViewById(R.id.tv_group_name_5_bidset);


        final RelativeLayout[] rl_setmybid_dl_contents = new RelativeLayout[5];
        rl_setmybid_dl_contents[0] = ((Activity) mContext).findViewById(R.id.rl_setmybid1_dl_contents);
        rl_setmybid_dl_contents[1] = ((Activity) mContext).findViewById(R.id.rl_setmybid2_dl_contents);
        rl_setmybid_dl_contents[2] = ((Activity) mContext).findViewById(R.id.rl_setmybid3_dl_contents);
        rl_setmybid_dl_contents[3] = ((Activity) mContext).findViewById(R.id.rl_setmybid4_dl_contents);
        rl_setmybid_dl_contents[4] = ((Activity) mContext).findViewById(R.id.rl_setmybid5_dl_contents);

        final TextView[] tv_group_name_bid = new TextView[5];
        tv_group_name_bid[0] = ((Activity) mContext).findViewById(R.id.tv_group_name_1_bid);
        tv_group_name_bid[1] = ((Activity) mContext).findViewById(R.id.tv_group_name_2_bid);
        tv_group_name_bid[2] = ((Activity) mContext).findViewById(R.id.tv_group_name_3_bid);
        tv_group_name_bid[3] = ((Activity) mContext).findViewById(R.id.tv_group_name_4_bid);
        tv_group_name_bid[4] = ((Activity) mContext).findViewById(R.id.tv_group_name_5_bid);


        final RelativeLayout[] rl_bid_dl_contents = new RelativeLayout[5];
        rl_bid_dl_contents[0] = ((Activity) mContext).findViewById(R.id.rl_bid1_dl_contents);
        rl_bid_dl_contents[1] = ((Activity) mContext).findViewById(R.id.rl_bid2_dl_contents);
        rl_bid_dl_contents[2] = ((Activity) mContext).findViewById(R.id.rl_bid3_dl_contents);
        rl_bid_dl_contents[3] = ((Activity) mContext).findViewById(R.id.rl_bid4_dl_contents);
        rl_bid_dl_contents[4] = ((Activity) mContext).findViewById(R.id.rl_bid5_dl_contents);

        final View[] view_bid_dl_contents = new View[5];
        view_bid_dl_contents[0] = ((Activity) mContext).findViewById(R.id.view_divider1_bid_dl_contents);
        view_bid_dl_contents[1] = ((Activity) mContext).findViewById(R.id.view_divider2_bid_dl_contents);
        view_bid_dl_contents[2] = ((Activity) mContext).findViewById(R.id.view_divider3_bid_dl_contents);
        view_bid_dl_contents[3] = ((Activity) mContext).findViewById(R.id.view_divider4_bid_dl_contents);
        view_bid_dl_contents[4] = ((Activity) mContext).findViewById(R.id.view_divider5_bid_dl_contents);

        final TextView[] tv_group_name_bidResult = new TextView[5];
        tv_group_name_bidResult[0] = ((Activity) mContext).findViewById(R.id.tv_group_name_1_bidResult);
        tv_group_name_bidResult[1] = ((Activity) mContext).findViewById(R.id.tv_group_name_2_bidResult);
        tv_group_name_bidResult[2] = ((Activity) mContext).findViewById(R.id.tv_group_name_3_bidResult);
        tv_group_name_bidResult[3] = ((Activity) mContext).findViewById(R.id.tv_group_name_4_bidResult);
        tv_group_name_bidResult[4] = ((Activity) mContext).findViewById(R.id.tv_group_name_5_bidResult);


        final RelativeLayout[] rl_bidResult_dl_contents = new RelativeLayout[5];
        rl_bidResult_dl_contents[0] = ((Activity) mContext).findViewById(R.id.rl_bidresult1_dl_contents);
        rl_bidResult_dl_contents[1] = ((Activity) mContext).findViewById(R.id.rl_bidresult2_dl_contents);
        rl_bidResult_dl_contents[2] = ((Activity) mContext).findViewById(R.id.rl_bidresult3_dl_contents);
        rl_bidResult_dl_contents[3] = ((Activity) mContext).findViewById(R.id.rl_bidresult4_dl_contents);
        rl_bidResult_dl_contents[4] = ((Activity) mContext).findViewById(R.id.rl_bidresult5_dl_contents);

        final View[] view_result_dl_contents = new View[5];
        view_result_dl_contents[0] = ((Activity) mContext).findViewById(R.id.view_divider1_result_dl_contents);
        view_result_dl_contents[1] = ((Activity) mContext).findViewById(R.id.view_divider2_result_dl_contents);
        view_result_dl_contents[2] = ((Activity) mContext).findViewById(R.id.view_divider3_result_dl_contents);
        view_result_dl_contents[3] = ((Activity) mContext).findViewById(R.id.view_divider4_result_dl_contents);
        view_result_dl_contents[4] = ((Activity) mContext).findViewById(R.id.view_divider5_result_dl_contents);

        final TextView[] tv_newBid_Count = new TextView[5];
        tv_newBid_Count[0] = ((Activity)mContext).findViewById(R.id.tv_group_count_1_bid);
        tv_newBid_Count[1] = ((Activity)mContext).findViewById(R.id.tv_group_count_2_bid);
        tv_newBid_Count[2] = ((Activity)mContext).findViewById(R.id.tv_group_count_3_bid);
        tv_newBid_Count[3] = ((Activity)mContext).findViewById(R.id.tv_group_count_4_bid);
        tv_newBid_Count[4] = ((Activity)mContext).findViewById(R.id.tv_group_count_5_bid);

        final TextView[] tv_newResult_Count = new TextView[5];
        tv_newResult_Count[0] = ((Activity)mContext).findViewById(R.id.tv_group_count_1_result);
        tv_newResult_Count[1] = ((Activity)mContext).findViewById(R.id.tv_group_count_2_result);
        tv_newResult_Count[2] = ((Activity)mContext).findViewById(R.id.tv_group_count_3_result);
        tv_newResult_Count[3] = ((Activity)mContext).findViewById(R.id.tv_group_count_4_result);
        tv_newResult_Count[4] = ((Activity)mContext).findViewById(R.id.tv_group_count_5_result);



        final TextView tv_mybid;
        tv_mybid = ((Activity) mContext).findViewById(R.id.tv_mybid_mybid);

        final TextView tv_new_bid_dl = ((Activity) mContext).findViewById(R.id.tv_new_bid_dl);
        final TextView tv_new_result_dl = ((Activity) mContext).findViewById(R.id.tv_new_result_dl);

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/getMypageGroup.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);

                    for(int i = 0; i < obj.length(); i++){
                        final int index = i;
                        JSONObject o = obj.getJSONObject(i);
                        if(o.getInt("BidNewCount") > 0){
                            isNewBidArr[i] = true;
                        }else{
                            isNewBidArr[i] = false;
                        }

                        if(o.getInt("ResultNewCount") > 0){
                            isNewResultArr[i] = true;
                        }else{
                            isNewResultArr[i] = false;
                        }

                        final String GCode = o.getString("GCode");
                        final String GorupName = o.getString("GName");
                        Log.d("Mypage" + i , o.toString());
                        tv_group_name_bidset[i].setText(o.getString("GName"));
                        if(o.getInt("BidNewCount") > 0){
                            tv_newBid_Count[i].setText(o.getInt("BidNewCount") + "건");
                        }
                        else
                        {
                            tv_newBid_Count[i].setText("");
                        }
                        tv_group_name_bidset[i].setTextColor(mContext.getResources().getColor(R.color.textColor_deep));
                        tv_group_name_bidset[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main));


                        tv_group_name_bid[i].setText(o.getString("GName"));
                        tv_group_name_bid[i].setTextColor(mContext.getResources().getColor(R.color.textColor_deep));
                        tv_group_name_bid[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main));
                        rl_bid_dl_contents[i].setVisibility(View.VISIBLE);
                        view_bid_dl_contents[i].setVisibility(View.VISIBLE);

                        tv_group_name_bidResult[i].setText(o.getString("GName"));
                        tv_group_name_bidResult[i].setTextColor(mContext.getResources().getColor(R.color.textColor_deep));
                        tv_group_name_bidResult[i].setTextSize(TypedValue.COMPLEX_UNIT_PX, mContext.getResources().getDimension(R.dimen.txt_main));
                        if(o.getInt("ResultNewCount") > 0){
                            tv_newResult_Count[i].setText(o.getInt("ResultNewCount") + "건");
                        }else
                        {
                            tv_newResult_Count[i].setText("");
                        }
                        rl_bidResult_dl_contents[i].setVisibility(View.VISIBLE);
                        view_result_dl_contents[i].setVisibility(View.VISIBLE);

                        final String groupData = o.toString();
                        rl_setmybid_dl_contents[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(!isLogin)
                                {
                                    AlertDialog alertDialog = loginDialog.create();
                                    alertDialog.show();
                                    alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                                    alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                                }
                                else if(!isService)
                                {
                                    Toast.makeText(mContext,"중지중 회원입니다. 서비스 연장 후 사용 가능합니다.",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                                    mContext.sendBroadcast(finishIntent);

                                    isDarwerOpened = false;
                                    Intent intent = new Intent(mContext, Setbid_Activity.class);
                                    intent.putExtra("isAdded", true);
                                    intent.putExtra("groupData", groupData);
                                    ((Activity) mContext).startActivity(intent);
                                }
                            }
                        });

                        rl_bid_dl_contents[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(!isLogin)
                                {
                                    AlertDialog alertDialog = loginDialog.create();
                                    alertDialog.show();
                                    alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                                    alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                                }
                                else if(!isService)
                                {
                                    Toast.makeText(mContext,"중지중 회원입니다. 서비스 연장 후 사용 가능합니다.",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    isNewBidArr[index] = false;
                                    Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                                    mContext.sendBroadcast(finishIntent);

                                    isDarwerOpened = false;
                                    Intent intent = new Intent(mContext, Bid_Activity.class);
                                    intent.putExtra("isAdded", true);
                                    intent.putExtra("GCode", GCode);
                                    intent.putExtra("GName", GorupName);
                                    intent.putExtra("groupData", groupData);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    mContext.startActivity(intent);
                                }
                            }
                        });

                        rl_bidResult_dl_contents[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if(!isLogin)
                                {
                                    AlertDialog alertDialog = loginDialog.create();
                                    alertDialog.show();
                                    alertDialog.getButton((DialogInterface.BUTTON_NEGATIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                                    alertDialog.getButton((DialogInterface.BUTTON_POSITIVE)).setTextColor(mContext.getResources().getColor(R.color.textColor_highlight_ngt));
                                }
                                else if(!isService)
                                {
                                    Toast.makeText(mContext,"중지중 회원입니다. 서비스 연장 후 사용 가능합니다.",Toast.LENGTH_SHORT).show();
                                }
                                else {
                                    isNewResultArr[index] = false;
                                    Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                                    mContext.sendBroadcast(finishIntent);

                                    isDarwerOpened = false;
                                    Intent intent = new Intent(mContext, Result_Activity.class);
                                    intent.putExtra("isAdded", true);
                                    intent.putExtra("GCode", GCode);
                                    intent.putExtra("GName", GorupName);
                                    intent.putExtra("groupData", groupData);
                                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                    mContext.startActivity(intent);
                                }
                            }
                        });

                        if(i == obj.length() - 1){
                            for(int j = i + 1; j < 5; j++){
                                rl_setmybid_dl_contents[j].setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Intent finishIntent = new Intent("com.mobile.a21line.finishActivity");
                                        mContext.sendBroadcast(finishIntent);

                                        isDarwerOpened = false;
                                        Intent intent = new Intent(mContext, Setbid_Activity.class);
                                        intent.putExtra("isAdded", false);
                                        intent.putExtra("GName", "맞춤입찰 New");
                                        ((Activity) mContext).startActivity(intent);
                                    }
                                });
                            }
                        }
                    }

                    if(isNewBid())
                    {
                        tv_new_bid_dl.setVisibility(View.VISIBLE);
                    }else
                    {
                        tv_new_bid_dl.setVisibility(View.GONE);
                    }

                    if(isNewResult())
                    {
                        tv_new_result_dl.setVisibility(View.VISIBLE);
                    }else
                    {
                        tv_new_result_dl.setVisibility(View.GONE);
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
                params.put("MemID", getUserID(mContext));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    private static void setUserInfoToDrawer(final Context mContext){

        TextView tv_comName = ((Activity) mContext).findViewById(R.id.user_com_name_drawer);
        TextView tv_userType = ((Activity) mContext).findViewById(R.id.user_type_drawer);

        TextView tv_service_due_date = ((Activity) mContext).findViewById(R.id.service_due_date_drawer);
        TextView tv_service_dday = ((Activity) mContext).findViewById(R.id.service_dday_drawer);

        TextView icon_view = ((Activity) mContext).findViewById(R.id.icon_view_user_drawer);
        TextView icon_anal = ((Activity) mContext).findViewById(R.id.icon_anal_user_drawer);
        TextView icon_free = ((Activity) mContext).findViewById(R.id.icon_free_user_drawer);
        TextView icon_extension = ((Activity) mContext).findViewById(R.id.icon_extension_drawer);

        LinearLayout ll_login = ((Activity) mContext).findViewById(R.id.ll_login_drawer);
        LinearLayout ll_logout = ((Activity) mContext).findViewById(R.id.ll_logout_drawer);
        TextView tv_logout = ((Activity) mContext).findViewById(R.id.tv_logout_drawer);

        Log.d("userID", getUserID(mContext));

        if(!getUserID(mContext).isEmpty()) {
            ll_login.setVisibility(View.VISIBLE);
            ll_logout.setVisibility(View.GONE);

            if(getUserType(mContext).equals("2")){
                tv_userType.setText("회원명 : ");
            }


            tv_comName.setText(getUserComName(mContext));
            TimeZone time = TimeZone.getTimeZone("Asia/Seoul");

            Date regDate = new Date(Long.parseLong(getServiceDueDate(mContext)));
            SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
            sdf.setTimeZone(time);
            String date = sdf.format(regDate);
            tv_service_due_date.setText(date);

            Date now = new Date();

            Long serviceDDay = Long.parseLong(getServiceDueDate(mContext)) + time.getOffset(now.getTime()) - now.getTime();
            if (serviceDDay < 0) {
                tv_service_dday.setVisibility(View.GONE);
            } else {

                int differ = (int) Math.floor(serviceDDay / (24 * 60 * 60 * 1000));
                tv_service_dday.setText("(D-" + differ + ")");
            }

            if (getIsServicing(mContext)) {
                if (getServiceType(mContext).equals("조회")) {
                    icon_view.setVisibility(View.VISIBLE);
                } else if (getServiceType(mContext).equals("분석")) {
                    icon_anal.setVisibility(View.VISIBLE);
                }
            } else {
                icon_free.setVisibility(View.VISIBLE);
                icon_extension.setVisibility(View.VISIBLE);

            }
        }
        else
        {
            ll_login.setVisibility(View.GONE);
            ll_logout.setVisibility(View.VISIBLE);
            tv_logout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,Login_Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    mContext.startActivity(intent);
                }
            });
        }
    }

    public static void daoMydoc(final Context mContext, final int type, final String MemID, final String BidNo, final String BidNoSeq, final String GCode){
        String uri = "Mypage/";
        final String successMessage;
        final String failureMessage;

        if(type == 1){
            uri += "insertMydoc.do";
            successMessage = "내서류함 저장이 성공하였습니다.";
            failureMessage = "내서류함 저장이 실패하였습니다.";
        }else if(type == 2){
            uri += "updateMydoc.do";
            successMessage = "내서류함 그룹 변경이 성공하였습니다.";
            failureMessage = "내서류함 그룹 변경이 실패하였습니다.";
        }else{
            uri += "deleteMydoc.do";
            successMessage = "내서류함 삭제가 성공하였습니다.";
            failureMessage = "내서류함 삭제가 실패하였습니다.";
        }

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + uri, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Toast.makeText(mContext, successMessage, Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(mContext, failureMessage, Toast.LENGTH_SHORT).show();
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
                params.put("MemID", getUserID(mContext));
                params.put("BidNo", BidNo);
                params.put("BidNoSeq", BidNoSeq);
                params.put("GCode", GCode);
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    public static String toNumFormat(String data){
        DecimalFormat df = new DecimalFormat("#,###");
        BigDecimal bd = new BigDecimal(data);
        return df.format(bd);
    }

    public static boolean checkDate(String date){

        if(date == null || date.length() != 10 ){
            return false;
        }

        date = date.replaceAll("-", "");
        if(date.length() != 8 || Integer.parseInt(date.substring(0, 4)) < 2000){
            return false;
        }

        boolean dateVaildity = true;

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd", Locale.KOREA);
        sdf.setLenient(false);
        try{
            Date dt = sdf.parse(date);
        }catch(ParseException e){
            dateVaildity = false;
        }catch(IllegalArgumentException e){
            dateVaildity = false;
        }

        return dateVaildity;
    }

    private static void getMemberData(final Context mContext){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Member/getMemberData.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    Log.d("userData = ", obj.toString());
                    SaveSharedPreference.setPrefUserId(mContext, obj.getString("MemID"));
                    SaveSharedPreference.setPrefUserComName(mContext, obj.getString("ComName"));
                    SaveSharedPreference.setPrefIsServicing(mContext, obj.getString("isService").equals("Y"));
                    SaveSharedPreference.setPrefServiceDueDate(mContext, obj.getString("ESerDate"));
                    SaveSharedPreference.setPrefServiceType(mContext, obj.getString("ServiceType"));
                    SaveSharedPreference.setPrefUserType(mContext, obj.getString("MemKind"));
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(mContext)) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("MemID", SaveSharedPreference.getUserID(mContext));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);

    }

    private static final String MY_CHANNEL_ID = "NOTIFICATION_CHANNEL_1";
    private static final int NEW_BID_NOTIFICATION_ID = 1;
    private static final int SUMMARY_NOTIFICATION_ID = 0;
    private static NotificationManagerCompat notificationManagerCompat;
    private static final String GROUP_KEY_ALARM = "Alarm";

    public static void checkHasNewBid(final Context mContext){

        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/getMypageGroup.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    int totalNewBid= 0, totalNewResult = 0;
                    JSONArray obj = new JSONArray(response);
                    initNewFlags();
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        totalNewResult += o.getInt("ResultNewCount");
                        totalNewBid += o.getInt("BidNewCount");
                    }
                    String Message = "";
                    String TitleMessage = "21라인 새로운 맞춤정보 등록";
                    if(totalNewBid > 0 && totalNewResult > 0){
                        Message = "새로운 맞춤입찰 " + totalNewBid + "건, 맞춤낙찰 " + totalNewResult + "건이 있습니다.";
                    }
                    else if(totalNewBid > 0){
                        Message = "새로운 맞춤입찰 " + totalNewBid + "건이 있습니다.";
                    }
                    else if(totalNewResult > 0){
                        Message = "새로운 맞춤낙찰 " + totalNewResult + "건이 있습니다.";
                    }

                    Intent intent = new Intent(mContext, Home_Activity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    if(!Message.isEmpty()){
                        PendingIntent contentIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
                        NotificationCompat.Builder mBuilder;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                            NotificationManager notificationManager = (NotificationManager) mContext.getSystemService(NotificationManager.class);
                            NotificationChannel channel = new NotificationChannel(MY_CHANNEL_ID,
                                    "Channel human readable title",
                                    NotificationManager.IMPORTANCE_MAX);
                            channel.setShowBadge(false);

                            notificationManager.createNotificationChannel(channel);
                            mBuilder = new NotificationCompat.Builder(mContext, channel.getId());

                            Log.d("this is ", channel.getId());
                        } else {
                            mBuilder = new NotificationCompat.Builder(mContext, MY_CHANNEL_ID);

                        }

                        mBuilder.setSmallIcon(R.drawable.ic_stat_name)
                                .setLargeIcon(BitmapFactory.decodeResource( mContext.getResources(), R.drawable.icon_logo))
                                .setPriority(Notification.PRIORITY_HIGH)
                                .setContentTitle(TitleMessage)
                                .setContentText(Message)
                                .setAutoCancel(true)
                                .setWhen(System.currentTimeMillis())
                                .setContentIntent(contentIntent);

                        if(!getVibeFlag(mContext))
                        {
                            mBuilder.setVibrate(new long[]{0, 0});
                            mBuilder.setSound(null);
                        }
                        if (getVibeFlag(mContext))
                        {
                            mBuilder.setVibrate(new long[]{1, 1000});
                            mBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
                        }
                        notificationManagerCompat = NotificationManagerCompat.from(mContext);
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
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
                                            .setGroupAlertBehavior(Notification.GROUP_ALERT_CHILDREN)
                                            .setAutoCancel(true);
                            //summaryNotification.defaults = 0;
                            if(!getVibeFlag(mContext))
                            {
                                sBuilder.setVibrate(new long[]{0, 0});
                                sBuilder.setSound(null);
                            }
                            if (getVibeFlag(mContext))
                            {
                                sBuilder.setVibrate(new long[]{1, 1000});
                                sBuilder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
                            }

                            notificationManagerCompat.notify(SUMMARY_NOTIFICATION_ID, sBuilder.build());
                        }

                        mBuilder.setGroup(GROUP_KEY_ALARM);

                        if(Build.VERSION.SDK_INT <= 18){
                            NotificationManager nm = (NotificationManager) mContext.getSystemService(mContext.NOTIFICATION_SERVICE);
                            nm.notify(NEW_BID_NOTIFICATION_ID, mBuilder.build());
                        }else {
                                notificationManagerCompat.notify(NEW_BID_NOTIFICATION_ID, mBuilder.build());
                        }

                        PowerManager pm = (PowerManager)mContext.getSystemService(Context.POWER_SERVICE);
                        boolean isScreenOn = pm.isScreenOn();
                        if(isScreenOn==false)
                        {
                            PowerManager.WakeLock wl = pm.newWakeLock(PowerManager.FULL_WAKE_LOCK |PowerManager.ACQUIRE_CAUSES_WAKEUP |PowerManager.ON_AFTER_RELEASE,"MyLock");
                            wl.acquire(10000);
                            PowerManager.WakeLock wl_cpu = pm.newWakeLock(PowerManager.PARTIAL_WAKE_LOCK,"MyCpuLock");
                            wl_cpu.acquire(10000);
                        }

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
                params.put("MemID", getUserID(mContext));
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }

    static private void initNewFlags(){
        for(int i = 0 ; i < 5; i++){
            isNewBidArr[i] = false;
            isNewResultArr[i] = false;
        }
    }
}
