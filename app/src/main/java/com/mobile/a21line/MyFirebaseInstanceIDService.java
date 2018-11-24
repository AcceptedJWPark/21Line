package com.mobile.a21line;

import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by kwonhong on 2018-03-09.
 */

public class MyFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = "MyFirebaseIIDService";

    /**
     * Called if InstanceID token is updated. This may occur if the security of
     * the previous token had been compromised. Note that this is called when the InstanceID token
     * is initially generated so this is where you would retrieve the token.
     */
    // [START refresh_token]
    @Override
    public void onTokenRefresh() {
        // Get updated InstanceID token.
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + refreshedToken);
        SaveSharedPreference.setPrefFcmToken(getApplicationContext(), refreshedToken);
        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(refreshedToken);
    }
    // [END refresh_token]

    /**
     * Persist token to third-party servers.
     *
     * Modify this method to associate the user's FCM InstanceID token with any server-side account
     * maintained by your application.
     *
     * @param token The new token.
     */
    private void sendRegistrationToServer(final String token) {
        // TODO: Implement this method to send token to your app server.
        if(SaveSharedPreference.getUserID(getApplicationContext()).isEmpty()){
            return;
        }
        String uri = "refreshFCMToken.do";
        if(SaveSharedPreference.getFcmToken(getApplicationContext()) == null   || SaveSharedPreference.getFcmToken(getApplicationContext()).isEmpty()){
            uri = "saveFCMToken.do";
        }

        final String furi = uri;
        RequestQueue postRequestQueue = VolleySingleton.getInstance(getApplicationContext()).getRequestQueue();
        StringRequest postJsonRequest = new StringRequest(Request.Method.POST, SaveSharedPreference.getServerIp() + "Login/" + uri, new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try {
                    JSONObject obj = new JSONObject(response);
                    if(obj.getString("result").equals("success")){
                        Log.d("saveToken", "토큰 저장 성공");
                    }else{
                        Log.d("saveToken", "토큰 저장 실패");
                    }
                }
                catch(JSONException e){
                    e.printStackTrace();
                }
            }
        }, SaveSharedPreference.getErrorListener(getApplicationContext())) {
            @Override
            protected Map<String, String> getParams(){
                Map<String, String> params = new HashMap();
                params.put("MemID", SaveSharedPreference.getUserID(getApplicationContext()));
                params.put("Token", token);
                if(furi.equals("refreshFCMToken.do")) {
                    params.put("PreToken", SaveSharedPreference.getFcmToken(getApplicationContext()));
                }
                return params;
            }
        };

        postRequestQueue.add(postJsonRequest);
    }


}
