/*
 * Created by lenovo
 * Created on 1/22/19 1:37 PM
 * Last modified 11/13/18 8:43 PM
 * Copyright (c) nStore Technologies Pvt Ltd  2019 . All rights reserved.
 */

package com.onesignal.custom;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import android.text.TextUtils;

import com.onesignal.OneSignal;


public class NotificationHandler {
    private static String TAG="ONESIGNAL NOTIFICATION";

    private static String ONESIGNAL_DASHBOARD="DASHBOARD";
    private static int ONESIGNAL_NOTIFICATION_REQUEST_CODE=100;

    private static Logger mLog=new Logger(TAG);
    private static final NotificationHandler ourInstance = new NotificationHandler();
    public static NotificationHandler getInstance() {
        return ourInstance;
    }

    private NotificationHandler() {
    }

    public void sendNotification(Context context, String responseScreen, String strTitle, String strMessage,Bitmap bitmap) {
        long when = System.currentTimeMillis();
        if (!TextUtils.isEmpty(responseScreen)) {
            mLog.info("Notification response Screen : " + responseScreen);
        }
        String channelId = "Received Notification";
        Intent notifyIntent = null;
        if(TextUtils.equals(responseScreen,MainActivity.class.getSimpleName())) {
        notifyIntent = new Intent(context, MainActivity.class);
        mLog.info("One notification generate to : " + MainActivity.class.getSimpleName());
        }else{
             notifyIntent = new Intent(context, MainActivity.class);
            mLog.info("One notification generate to : " + MainActivity.class.getSimpleName());
        }

        notifyIntent.setAction(ONESIGNAL_DASHBOARD);
        notifyIntent.putExtra("title", strTitle);
        notifyIntent.putExtra("message", strMessage);
        notifyIntent.putExtra("respScreen", responseScreen);
        notifyIntent.putExtra("notification_id", when);
        notifyIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

        PendingIntent pendingIntent = PendingIntent.getActivity(context, ONESIGNAL_NOTIFICATION_REQUEST_CODE, notifyIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Bitmap largeIcon = BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher);

//        if(!App.getInstance().isSessionLoggedIn()) {
//            strMessage="Dear Customer, You are around the Smart Banking area. Please click here to enjoy our services.";
//        }

        NotificationCompat.Builder builder =
                new NotificationCompat.Builder(context,channelId)
                        .setChannelId(channelId)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setLargeIcon(largeIcon)
                        .setContentTitle(strTitle)
                        .setContentText(strMessage)
                      //  .setStyle(new NotificationCompat.BigTextStyle().setBigContentTitle(strTitle).bigText(strMessage))
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true)
                        .setDefaults(Notification.DEFAULT_SOUND)
                        .setGroup(channelId)
                        .setGroupSummary(true)
                        .setWhen(when);
        if(bitmap==null) {
            NotificationCompat.BigTextStyle style = new NotificationCompat.BigTextStyle().setBigContentTitle(strTitle).bigText(strMessage);
            builder.setStyle(style);
        }else {
            NotificationCompat.BigPictureStyle style1 = new NotificationCompat.BigPictureStyle()
                    .bigPicture(bitmap)
                    .setSummaryText(strMessage);
            builder.setStyle(style1);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            builder.setPriority(NotificationCompat.PRIORITY_MAX);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setCategory(Notification.CATEGORY_MESSAGE);
        }


        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationManager notificationManager =
                    (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.createNotificationChannel(getNotificationChannel(channelId));
            notificationManager.notify((int) when, builder.build());
        }else {
            NotificationManagerCompat notificationManager =
                    NotificationManagerCompat.from(context);
            notificationManager.notify((int) when, builder.build());
        }
        mLog.info("Notification Sent. ");
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private NotificationChannel getNotificationChannel(String channelId) {
        // The id of the channel.
        String id = channelId;

        // The user-visible name of the channel.
        CharSequence name = "Payment Received";

        // The user-visible description of the channel.
        String description = name.toString();

        int importance = NotificationManager.IMPORTANCE_MAX;

        NotificationChannel mChannel = new NotificationChannel(id, name, importance);

        // Configure the notification channel.
        mChannel.setDescription(description);

        mChannel.enableLights(true);
        // Sets the notification light color for notifications posted to this
        // channel, if the device supports this feature.
        mChannel.setLightColor(Color.RED);

        mChannel.enableVibration(true);
        mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
        return mChannel;
    }

}
