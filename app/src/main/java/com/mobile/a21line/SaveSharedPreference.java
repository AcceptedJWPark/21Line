package com.mobile.a21line;

/**
 * Created by kwonhong on 2018-05-02.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


public class SaveSharedPreference{
    static final String PREF_USER_NAME = "username";
    static final String PREF_USER_ID = "userid";
    static final String PREF_USER_PW = "userpw";
    static final String SERVER_IP = "https://13.124.141.242/21LINE_Mobile/";
    static final String SERVER_IP2 = "http://119.193.35.16:8080/21LINE_Mobile/";
    static final String IMAGE_URI = "http://13.124.141.242/21LINE_Mobile/";
    static final String IMAGE_URI2 = "http://119.193.35.16:8080/21LINE_Mobile/";
    static String myPicturePath = null;
    static String myThumbPicturePath = null;
    static String fcmToken = null;
    static final String PREF_FIRST_LOADING = "firstLoading";
    public static final String WIFI_STATE = "WIFI";
    public static final String MOBILE_STATE = "MOBILE";
    public static final String NONE_STATE = "NONE";

    public static final String CONNECTION_CONFIRM_CLIENT_URL = "http://clients3.google.com/generate_204";


    static SharedPreferences getSharedPreferences(Context ctx){
        return PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public static void setPrefUsrName(Context ctx, String userName){
        SharedPreferences.Editor editor = getSharedPreferences(ctx).edit();
        editor.putString(PREF_USER_NAME, userName);
        editor.commit();
    }

    public static String getUserName(Context ctx){
        return getSharedPreferences(ctx).getString(PREF_USER_NAME, "");
    }

    public static String getServerIp(){
        return SERVER_IP2;
    }

    public static void hideKeyboard(View view, Context context) {
        InputMethodManager inputMethodManager =(InputMethodManager)context.getSystemService(Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static Response.ErrorListener getErrorListener(final Context context){
        Response.ErrorListener errorListener = new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error){
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
                                               TextView textView2, TextView textView3, TextView textView4, TextView textView5,TextView textView6,TextView textView7,TextView textView8
                                                )
    {
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
        TextView tv_home_dl = ((Activity)mContext).findViewById(R.id.tv_home_dl);
        TextView tv_txt1_dl = ((Activity)mContext).findViewById(R.id.tv_txt1_dl);
        TextView tv_txt2_dl = ((Activity)mContext).findViewById(R.id.tv_txt2_dl);
        TextView tv_bidset_dl = ((Activity)mContext).findViewById(R.id.tv_bidset_dl);
        TextView tv_bid_dl = ((Activity)mContext).findViewById(R.id.tv_bid_dl);
        TextView tv_result_dl = ((Activity)mContext).findViewById(R.id.tv_result_dl);
        TextView tv_mybid_dl = ((Activity)mContext).findViewById(R.id.tv_mybid_dl);
        TextView tv_search_dl = ((Activity)mContext).findViewById(R.id.tv_search_dl);
        TextView tv_cs_dl = ((Activity)mContext).findViewById(R.id.tv_cs_dl);
        TextView tv_setting_dl = ((Activity)mContext).findViewById(R.id.tv_setting_dl);
        ImageView iv_home_dl = ((Activity)mContext).findViewById(R.id.iv_home_dl);
        ImageView iv_setbid_dl = ((Activity)mContext).findViewById(R.id.iv_setbid_dl);
        ImageView iv_bid_dl = ((Activity)mContext).findViewById(R.id.iv_bid_dl);
        ImageView iv_result_dl = ((Activity)mContext).findViewById(R.id.iv_result_dl);
        ImageView iv_mybid_dl = ((Activity)mContext).findViewById(R.id.iv_mybid_dl);
        ImageView iv_search_dl = ((Activity)mContext).findViewById(R.id.iv_search_dl);
        ImageView iv_cs_dl = ((Activity)mContext).findViewById(R.id.iv_cs_dl);
        ImageView iv_setting_dl = ((Activity)mContext).findViewById(R.id.iv_setting_dl);

        DrawerLayout_clickedBgr(mContext,tv_bidset_dl,tv_home_dl,tv_bid_dl,tv_result_dl,tv_mybid_dl,tv_search_dl,tv_cs_dl,tv_setting_dl);
        ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.VISIBLE);
        ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
        ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
        ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);

        iv_home_dl.setImageResource(R.drawable.icon_unclicked_home_dl);
        iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
        iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
        iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
        iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
        iv_setbid_dl.setImageResource(R.drawable.icon_clicked_setbid_dl);
        iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
        iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);

        tv_txt1_dl.setText("맞춤설정");
        tv_txt2_dl.setText("회원님만의 공고를 설정 할 수 있습니다.");

        Intent i;
        switch (view.getId()) {
            case R.id.img_toolbarIcon_Left_Menu: {
                drawerLayout.openDrawer(frameLayout);
                break;
            }

            case R.id.img_close_dl: {
                drawerLayout.closeDrawer(frameLayout);
                break;
            }

            case R.id.ll_home_dl: {
                drawerLayout.closeDrawer(frameLayout);
                DrawerLayout_clickedBgr(mContext,tv_home_dl,tv_bidset_dl,tv_bid_dl,tv_result_dl,tv_mybid_dl,tv_search_dl,tv_cs_dl,tv_setting_dl);
                break;
            }

            case R.id.ll_bidset_dl: {
                DrawerLayout_clickedBgr(mContext,tv_bidset_dl,tv_home_dl,tv_bid_dl,tv_result_dl,tv_mybid_dl,tv_search_dl,tv_cs_dl,tv_setting_dl);
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);

                iv_home_dl.setImageResource(R.drawable.icon_unclicked_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
                iv_setbid_dl.setImageResource(R.drawable.icon_clicked_setbid_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);

                tv_txt1_dl.setText("맞춤설정");
                tv_txt2_dl.setText("회원님만의 공고를 설정 할 수 있습니다.");
                break;
            }

            case R.id.ll_bid_dl: {
                DrawerLayout_clickedBgr(mContext,tv_bid_dl,tv_home_dl,tv_bidset_dl,tv_result_dl,tv_mybid_dl,tv_search_dl,tv_cs_dl,tv_setting_dl);
                tv_txt1_dl.setText("입찰공고");
                tv_txt2_dl.setText("맞춤 입찰공고를 확인합니다.");
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);

                iv_home_dl.setImageResource(R.drawable.icon_unclicked_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_clicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
                iv_setbid_dl.setImageResource(R.drawable.icon_unclicked_setbid_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);
                break;
            }

            case R.id.ll_result_dl: {
                DrawerLayout_clickedBgr(mContext,tv_result_dl,tv_bid_dl,tv_home_dl,tv_bidset_dl,tv_mybid_dl,tv_search_dl,tv_cs_dl,tv_setting_dl);
                tv_txt1_dl.setText("낙찰공고");
                tv_txt2_dl.setText("맞춤 낙찰공고를 확인합니다.");
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);

                iv_home_dl.setImageResource(R.drawable.icon_unclicked_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_clicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
                iv_setbid_dl.setImageResource(R.drawable.icon_unclicked_setbid_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);
                break;
            }

            case R.id.ll_mybid_dl: {
                DrawerLayout_clickedBgr(mContext,tv_mybid_dl,tv_bid_dl,tv_home_dl,tv_bidset_dl,tv_result_dl,tv_search_dl,tv_cs_dl,tv_setting_dl);
                tv_txt1_dl.setText("내 서류함");
                tv_txt2_dl.setText("회원님의 서류함을 확인합니다.");
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.VISIBLE);

                iv_home_dl.setImageResource(R.drawable.icon_unclicked_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_clicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
                iv_setbid_dl.setImageResource(R.drawable.icon_unclicked_setbid_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);
                break;
            }

            case R.id.ll_search_dl: {
                drawerLayout.closeDrawer(frameLayout);
                DrawerLayout_clickedBgr(mContext,tv_search_dl,tv_bid_dl,tv_home_dl,tv_bidset_dl,tv_result_dl,tv_mybid_dl,tv_cs_dl,tv_setting_dl);
                break;
            }

            case R.id.ll_cs_dl: {
                DrawerLayout_clickedBgr(mContext,tv_cs_dl,tv_bid_dl,tv_home_dl,tv_bidset_dl,tv_result_dl,tv_mybid_dl,tv_search_dl,tv_setting_dl);
                tv_txt1_dl.setText("고객센터");
                tv_txt2_dl.setText("회원님의 불편함을 해결해드립니다.");
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);

                iv_home_dl.setImageResource(R.drawable.icon_unclicked_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_unclicked_setting_dl);
                iv_setbid_dl.setImageResource(R.drawable.icon_unclicked_setbid_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_clicked_cs_dl);
                break;
            }

            case R.id.ll_setting_dl: {
                DrawerLayout_clickedBgr(mContext,tv_setting_dl,tv_cs_dl,tv_bid_dl,tv_home_dl,tv_bidset_dl,tv_result_dl,tv_mybid_dl,tv_search_dl);
                DrawerLayout_clickedBgr(mContext,tv_setting_dl,tv_cs_dl,tv_bid_dl,tv_home_dl,tv_bidset_dl,tv_result_dl,tv_mybid_dl,tv_search_dl);
                tv_txt1_dl.setText("설 정");
                tv_txt2_dl.setText("이용약관, 로그인 정보 등을 확인합니다.");
                ((Activity) mContext).findViewById(R.id.inc_bid_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_cs_dl).setVisibility(View.GONE);
                ((Activity) mContext).findViewById(R.id.inc_setting_dl).setVisibility(View.VISIBLE);
                ((Activity) mContext).findViewById(R.id.inc_mybid_dl).setVisibility(View.GONE);

                iv_home_dl.setImageResource(R.drawable.icon_unclicked_home_dl);
                iv_mybid_dl.setImageResource(R.drawable.icon_unclicked_mybid_dl);
                iv_result_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_bid_dl.setImageResource(R.drawable.icon_unclicked_bid_dl);
                iv_setting_dl.setImageResource(R.drawable.icon_clicked_setting_dl);
                iv_setbid_dl.setImageResource(R.drawable.icon_unclicked_setbid_dl);
                iv_search_dl.setImageResource(R.drawable.icon_unclicked_search_dl);
                iv_cs_dl.setImageResource(R.drawable.icon_unclicked_cs_dl);
                break;
            }
        }
    }

        public static void DrawerLayout_ClickEvent(Context context, View.OnClickListener listener) {
            ((ImageView) ((Activity)context).findViewById(R.id.img_toolbarIcon_Left_Menu)).setOnClickListener(listener);
            ((ImageView) ((Activity)context).findViewById(R.id.img_close_dl)).setOnClickListener(listener);
            ((LinearLayout) ((Activity)context).findViewById(R.id.ll_home_dl)).setOnClickListener(listener);
            ((LinearLayout) ((Activity)context).findViewById(R.id.ll_bidset_dl)).setOnClickListener(listener);
            ((LinearLayout) ((Activity)context).findViewById(R.id.ll_bid_dl)).setOnClickListener(listener);
            ((LinearLayout) ((Activity)context).findViewById(R.id.ll_result_dl)).setOnClickListener(listener);
            ((LinearLayout) ((Activity)context).findViewById(R.id.ll_mybid_dl)).setOnClickListener(listener);
            ((LinearLayout) ((Activity)context).findViewById(R.id.ll_search_dl)).setOnClickListener(listener);
            ((LinearLayout) ((Activity)context).findViewById(R.id.ll_cs_dl)).setOnClickListener(listener);
            ((LinearLayout) ((Activity)context).findViewById(R.id.ll_setting_dl)).setOnClickListener(listener);
        }








    }
