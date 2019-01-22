/*
 * Created by lenovo
 * Created on 1/22/19 1:26 PM
 * Last modified 1/22/19 1:26 PM
 * Copyright (c) nStore Technologies Pvt Ltd  2019 . All rights reserved.
 */

package com.onesignal.custom;

import android.app.Application;
import android.content.Context;

import com.onesignal.OneSignal;

public class App extends Application {

    private static Context context;
    private static App appInstance;


    public App() {
        this.appInstance = this;
    }

    public static App getInstance() {
        return appInstance;
    }

    public static Context getAppContext() {
        return context;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

        // Logging set to help debug issues, remove before releasing your app.
        OneSignal.setLogLevel(OneSignal.LOG_LEVEL.DEBUG, OneSignal.LOG_LEVEL.NONE);

//        OneSignal.init(this, "402061667585", "a6d52ab1-6626-49b0-95bd-000e3c26454a",new OneSignalBroadcastReceiver());
        // OneSignal Initialization
        OneSignal.startInit(this)
                .autoPromptLocation(true)
//                .unsubscribeWhenNotificationsAreDisabled(true)
                .setNotificationReceivedHandler(new OneSignalBroadcastReceiver())
//                .setNotificationOpenedHandler(new OneSignal.NotificationOpenedHandler() {
//                    @Override
//                    public void notificationOpened(OSNotificationOpenResult result) {
//                        Log.i("OneSignal","******Notification Opened*******");
//                        OneSignal.clearOneSignalNotifications();
//                    }
//                })
                .init();
    }
}
