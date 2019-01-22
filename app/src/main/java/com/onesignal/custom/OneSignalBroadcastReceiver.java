/*
 * Created by lenovo
 * Created on 1/22/19 1:27 PM
 * Last modified 11/13/18 8:44 PM
 * Copyright (c) nStore Technologies Pvt Ltd  2019 . All rights reserved.
 */

package com.onesignal.custom;

import android.content.Context;
import android.text.TextUtils;
import android.util.Log;

import com.onesignal.OSNotification;
import com.onesignal.OneSignal;

import org.json.JSONException;
import org.json.JSONObject;

public class OneSignalBroadcastReceiver implements OneSignal.NotificationReceivedHandler {
    private final String TAG="ONESIGNAL NOTIFICATION";

    @Override
    public void notificationReceived(Context context, OSNotification osNotification) {
        Log.i(TAG,"OneSignal notificationReceived!!!!!! : " + osNotification.toJSONObject());
        try {
            JSONObject customJSON = osNotification.toJSONObject();
            Log.i(TAG,"Oriens - OneSignal Receiver customJSON additionalData: " + customJSON);

            if (customJSON.has("payload")) {
                String strPayload = JsonDataParser.getStringValueFromJSON(customJSON, "payload");
                if (!TextUtils.isEmpty(strPayload))
                    customJSON = JsonDataParser.getJsonObject(strPayload);

                if (customJSON != null && customJSON.has("additionalData")) {

                    String strAdditionalData = customJSON.getJSONObject("additionalData").toString();
                    JSONObject additionalData = JsonDataParser.getJsonObject(strAdditionalData);
//                    String responseScreen = JsonDataParser.getStringValueFromJSON(additionalData, "key");

//                    if (!TextUtils.isEmpty(responseScreen)) {
//                        mLog.info("Response Screen Key received : " + responseScreen);

                        if (additionalData.has("data")) {
                            String data = JsonDataParser.getStringValueFromJSON(additionalData, "data");
                            Log.i(TAG,"Data : "+data);
                            parseMessaging(context, JsonDataParser.getJsonObject(data));
                        }

//                    }

                }
            } else {
                Log.i(TAG,"the content does not contains response Screen key detail.");
            }
        } catch (NullPointerException | JSONException t) {
            t.printStackTrace();
            Log.e(TAG,"Exception occurred! Something went wrong while receiving Onesignal push message. " + t.getMessage());
        }
    }

    private void parseMessaging(final Context context, JSONObject jsonObject){
        if (jsonObject == null) return;
//        if (context == null)
//            context = (OneSignal.getAppContext() != null) ? OneSignal.getAppContext() : App.getAppContext();
        if (context == null) return;
        final  String title = JsonDataParser.getStringValueFromJSON(jsonObject, "title");
        final  String message = JsonDataParser.getStringValueFromJSON(jsonObject, "msg");
         String url = JsonDataParser.getStringValueFromJSON(jsonObject, "imageurl");
        Log.i(TAG,"Onesignal received Message : " + message);

        // Broadcast Notification
        NotificationHandler notificationHandler =NotificationHandler.getInstance();
        notificationHandler.sendNotification(context,MainActivity.class.getSimpleName(),title,message,null);
    }

}
