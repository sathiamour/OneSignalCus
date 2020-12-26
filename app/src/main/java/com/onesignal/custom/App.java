/*
 * Created by lenovo
 * Created on 1/22/19 1:26 PM
 * Last modified 1/22/19 1:26 PM
 * Copyright (c) nStore Technologies Pvt Ltd  2019 . All rights reserved.
 */

package com.onesignal.custom;

import android.app.Application;
import android.content.Context;
import android.util.Log;

import com.onesignal.OSNotificationOpenedResult;
import com.onesignal.OSNotificationReceivedEvent;
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

        // OneSignal Initialization
        OneSignal.setAppId(BuildConfig.onesignal_app_id);
        OneSignal.initWithContext(this);
        OneSignal.setLocationShared(false);
        OneSignal.setRemoteNotificationReceivedHandler(new OneSignalBroadcastReceiver());
    }
}
