package com.mobile.a21line;

/**
 * Created by kwonhong on 2018-05-02.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
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
import com.mobile.a21line.CustomerService.Education_Activity;
import com.mobile.a21line.CustomerService.Mas_Activity;
import com.mobile.a21line.CustomerService.Notice_Activity;
import com.mobile.a21line.CustomerService.Notice_Detail_Activity;
import com.mobile.a21line.CustomerService.Qna_Activity;
import com.mobile.a21line.Home.Home_Activity;
import com.mobile.a21line.Library.Library_BidLimitPercent_Activity;
import com.mobile.a21line.Library.Library_BidablePrice_Activity;
import com.mobile.a21line.Library.Library_BusinessCondition_Activity;
import com.mobile.a21line.Login.Join_PrivateInfoDetail2_Activity;
import com.mobile.a21line.Login.Login_Activity;
import com.mobile.a21line.MyBid.MyBid_Activity;
import com.mobile.a21line.MyBid.MyBid_Request_Activity;
import com.mobile.a21line.MyBid.MyBid_Schedule_Activity;
import com.mobile.a21line.Result.Result_Activity;
import com.mobile.a21line.Search.Search_Bid_Activity;
import com.mobile.a21line.Setbid.Setbid_Activity;
import com.mobile.a21line.Setting.Setting_JoinAgreement_Activity;
import com.mobile.a21line.Setting.Setting_PrivateDataAgreement_Activity;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

import static android.content.Intent.FLAG_ACTIVITY_MULTIPLE_TASK;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;


public class SaveSharedPreference {
    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_ID = "userid";
    static final String PREF_USER_COM_NAME = "userComName";
    static final String PREF_IS_SERVICING = "isServicing";
    static final String PREF_SERVICE_TYPE = "serviceType";
    static final String PREF_SERVICE_DUE_DATE = "serviceDueDate";
    static final String PREF_USER_TYPE = "userType";
    static final String SERVER_IP = "https://13.124.141.242/21LINE_Mobile/";
    //static final String SERVER_IP2 = "http://119.193.35.174:8080/21LINE_Mobile/";
    static final String SERVER_IP2 = "http://192.168.150.251:8080/21LINE_Mobile/";
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

    public static final String CONNECTION_CONFIRM_CLIENT_URL = "http://clients3.google.com/generate_204";

    private static boolean isDarwerOpened = false;

    static boolean isLogin;


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

    public static String getServerIp() {
        return SERVER_IP2;
    }

    public static String getBidDataUri() { return BID_DATA_URI; }

    public static void initPreference(Context ctx){
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
        if(!isDarwerOpened && !getUserID(mContext).isEmpty()){
            getMypageGroup(mContext);
            setUserInfoToDrawer(mContext);
            isDarwerOpened = true;
        }

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

        iv_home_dl.setImageResource(R.drawable.icon_home_dl);
        iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
        iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
        iv_bid_dl.setImageResource(R.drawable.icon_clicked_bid_dl);
        iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
        iv_library_dl.setImageResource(R.drawable.icon_unclicked_library_dl);
        iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
        iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);

        tv_txt1_dl.setText("입찰공고");
        tv_txt2_dl.setText("맞춤 입찰공고를 확인합니다.");

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

                tv_txt1_dl.setText("입찰 자료실");
                tv_txt2_dl.setText("입찰 관련 자료를 확인합니다.");

                final RelativeLayout[] rl_search_dl_contents = new RelativeLayout[3];
                rl_search_dl_contents[0] = ((Activity) mContext).findViewById(R.id.rl_library1);
                rl_search_dl_contents[1] = ((Activity) mContext).findViewById(R.id.rl_library2);
                rl_search_dl_contents[2] = ((Activity) mContext).findViewById(R.id.rl_library3);

                rl_search_dl_contents[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Library_BusinessCondition_Activity.class);
                        mContext.startActivity(intent);
                    }
                });
                rl_search_dl_contents[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Library_BidablePrice_Activity.class);
                        mContext.startActivity(intent);
                    }
                });

                rl_search_dl_contents[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Intent intent = new Intent(mContext, Library_BidLimitPercent_Activity.class);
                        mContext.startActivity(intent);
                    }
                });


                break;
            }

            case R.id.ll_bid_dl: {
                if(!getIsServicing(mContext)){
                    Intent intent = new Intent(mContext, Home_Activity.class);
                    Toast.makeText(mContext, "이용기간이 만료되었습니다.", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(intent);
                    return;
                }
                DrawerLayout_clickedBgr(mContext, tv_bid_dl, tv_home_dl, tv_library_dl, tv_result_dl, tv_mybid_dl, tv_search_dl, tv_cs_dl, tv_setting_dl);
                tv_txt1_dl.setText("입찰공고");
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
                break;
            }

            case R.id.ll_result_dl: {
                if(!getIsServicing(mContext)){
                    Intent intent = new Intent(mContext, Home_Activity.class);
                    Toast.makeText(mContext, "이용기간이 만료되었습니다.", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(intent);
                    return;
                }

                DrawerLayout_clickedBgr(mContext, tv_result_dl, tv_bid_dl, tv_home_dl, tv_library_dl, tv_mybid_dl, tv_search_dl, tv_cs_dl, tv_setting_dl);
                tv_txt1_dl.setText("낙찰공고");
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
                break;
            }

            case R.id.ll_mybid_dl: {
                if(getUserID(mContext).isEmpty()){
                    Intent intent = new Intent(mContext, Login_Activity.class);
                    Toast.makeText(mContext, "로그인을 해주세요.", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                    return;
                }else if(!getIsServicing(mContext)){
                    Intent intent = new Intent(mContext, Home_Activity.class);
                    Toast.makeText(mContext, "이용기간이 만료되었습니다.", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(intent);
                    return;
                }
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

                final TextView[] tv_mybid = new TextView[2];
                final RelativeLayout rl_mybid;
                tv_mybid[0] = ((Activity) mContext).findViewById(R.id.tv_mybid_mybid);
                tv_mybid[1] = ((Activity) mContext).findViewById(R.id.tv_schedule_mybid);
                rl_mybid = ((Activity) mContext).findViewById(R.id.rl_analysis_mybid);

                if(!getServiceType(mContext).equals("분석")){
                    rl_mybid.setVisibility(View.GONE);
                }

                tv_mybid[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, MyBid_Activity.class);
                        mContext.startActivity(intent);
                    }
                });

                tv_mybid[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, MyBid_Schedule_Activity.class);
                        mContext.startActivity(intent);
                    }
                });
                rl_mybid.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, MyBid_Request_Activity.class);
                        mContext.startActivity(intent);
                    }
                });



                break;
            }

            case R.id.ll_search_dl: {
                if(getUserID(mContext).isEmpty()){
                    Intent intent = new Intent(mContext, Login_Activity.class);
                    Toast.makeText(mContext, "로그인을 해주세요.", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(intent);
                    ((Activity) mContext).finish();
                }else if(!getIsServicing(mContext)){
                    Intent intent = new Intent(mContext, Home_Activity.class);
                    Toast.makeText(mContext, "이용기간이 만료되었습니다.", Toast.LENGTH_SHORT).show();
                    mContext.startActivity(intent);
                    return;
                }
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

                final RelativeLayout[] rl_search_dl_contents = new RelativeLayout[2];
                rl_search_dl_contents[0] = ((Activity) mContext).findViewById(R.id.rl_search_bid);
                rl_search_dl_contents[1] = ((Activity) mContext).findViewById(R.id.rl_search_result);

                rl_search_dl_contents[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Search_Bid_Activity.class);
                        //intent.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_MULTIPLE_TASK);
                        mContext.startActivity(intent);
                    }
                });
                rl_search_dl_contents[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Search_Bid_Activity.class);
                        //intent.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_MULTIPLE_TASK);
                        intent.putExtra("isBid", false);
                        mContext.startActivity(intent);
                        Log.d("클릭됨","클릭됨");
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


                final RelativeLayout[] rl_cs_dl_contents = new RelativeLayout[5];
                rl_cs_dl_contents[0] = ((Activity) mContext).findViewById(R.id.rl_cs_notice_dl_contents1);
                rl_cs_dl_contents[1] = ((Activity) mContext).findViewById(R.id.rl_cs_question_dl_contents2);
                rl_cs_dl_contents[2] = ((Activity) mContext).findViewById(R.id.rl_cs_call_dl_contents3);
                rl_cs_dl_contents[3] = ((Activity) mContext).findViewById(R.id.rl_cs_education_dl_contents4);
                rl_cs_dl_contents[4] = ((Activity) mContext).findViewById(R.id.rl_cs_mas_dl_contents5);

                rl_cs_dl_contents[0].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                                Intent intent = new Intent(mContext, Notice_Activity.class);
                                mContext.startActivity(intent);
                            }
                });

                rl_cs_dl_contents[1].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if(getUserID(mContext).isEmpty()){
                            Intent intent = new Intent(mContext, Login_Activity.class);
                            Toast.makeText(mContext, "로그인을 해주세요.", Toast.LENGTH_SHORT).show();
                            mContext.startActivity(intent);
                            ((Activity) mContext).finish();
                        }else {
                            Intent intent = new Intent(mContext, Qna_Activity.class);
                            mContext.startActivity(intent);
                        }

                            }
                });

                rl_cs_dl_contents[2].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:1599-2127"));
                        mContext.startActivity(i);
                    }
                });


                rl_cs_dl_contents[3].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Education_Activity.class);
                        mContext.startActivity(intent);
                    }
                });

                rl_cs_dl_contents[4].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Mas_Activity.class);
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


                ((TextView)((Activity) mContext).findViewById(R.id.tv_privateInfo1_dl)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Setting_PrivateDataAgreement_Activity.class);
                        mContext.startActivity(intent);
                    }
                });


                ((TextView)((Activity) mContext).findViewById(R.id.tv_privateInfo2_dl)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(mContext, Join_PrivateInfoDetail2_Activity.class);
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



        final TextView tv_mybid;
        tv_mybid = ((Activity) mContext).findViewById(R.id.tv_mybid_mybid);


        RequestQueue postRequestQueue = VolleySingleton.getInstance(mContext).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Mypage/getMypageGroup.do", new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONArray obj = new JSONArray(response);
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject o = obj.getJSONObject(i);
                        final String GCode = o.getString("GCode");
                        final String GorupName = o.getString("GName");
                        Log.d("Mypage" + i , o.toString());
                        tv_group_name_bidset[i].setText(o.getString("GName"));
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
                        rl_bidResult_dl_contents[i].setVisibility(View.VISIBLE);
                        view_result_dl_contents[i].setVisibility(View.VISIBLE);

                        final String groupData = o.toString();
                        rl_setmybid_dl_contents[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                isDarwerOpened = false;
                                Intent intent = new Intent(mContext, Setbid_Activity.class);
                                intent.putExtra("isAdded", true);
                                intent.putExtra("groupData", groupData);
                                ((Activity) mContext).startActivity(intent);
                            }
                        });

                        rl_bid_dl_contents[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                isDarwerOpened = false;
                                Intent intent = new Intent(mContext,Bid_Activity.class);
                                intent.putExtra("isAdded", true);
                                intent.putExtra("GCode", GCode);
                                intent.putExtra("GName", GorupName);
                                intent.putExtra("groupData", groupData);
                                mContext.startActivity(intent);
                            }
                        });

                        rl_bidResult_dl_contents[i].setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                isDarwerOpened = false;
                                Intent intent = new Intent(mContext,Result_Activity.class);
                                intent.putExtra("isAdded", true);
                                intent.putExtra("GCode", GCode);
                                intent.putExtra("GName", GorupName);
                                intent.putExtra("groupData", groupData);
                                mContext.startActivity(intent);
                            }
                        });



                        if(i == obj.length() - 1){
                            for(int j = i + 1; j < 5; j++){
                                rl_setmybid_dl_contents[j].setOnClickListener(new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
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

    private static void setUserInfoToDrawer(Context mContext){
        if(!getUserID(mContext).isEmpty()) {
            TextView tv_comName = ((Activity) mContext).findViewById(R.id.user_com_name_drawer);
            TextView tv_service_due_date = ((Activity) mContext).findViewById(R.id.service_due_date_drawer);
            TextView tv_service_dday = ((Activity) mContext).findViewById(R.id.service_dday_drawer);
            TextView icon_view = ((Activity) mContext).findViewById(R.id.icon_view_user_drawer);
            TextView icon_anal = ((Activity) mContext).findViewById(R.id.icon_anal_user_drawer);
            TextView icon_free = ((Activity) mContext).findViewById(R.id.icon_free_user_drawer);

            tv_comName.setText(getUserComName(mContext));

            TimeZone time = TimeZone.getTimeZone("Asia/Seoul");

            Date regDate = new Date(Long.parseLong(getServiceDueDate(mContext)));
            SimpleDateFormat sdf = new SimpleDateFormat("YY-MM-dd");
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
            }
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
}
