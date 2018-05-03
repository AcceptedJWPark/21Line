package com.mobile.a21line;

/**
 * Created by kwonhong on 2018-05-02.
 */


import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
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

}
