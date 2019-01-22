/*
 * Created by lenovo
 * Created on 1/22/19 1:35 PM
 * Last modified 11/13/18 8:43 PM
 * Copyright (c) nStore Technologies Pvt Ltd  2019 . All rights reserved.
 */

package com.onesignal.custom;

import android.text.TextUtils;


/**
 * This class manages Android Log for this application
 */
public class Logger {
    private static String TAG = "ONESIGNAL CUSTOM";
    private static final java.util.logging.Logger mLog = java.util.logging.Logger.getLogger(TAG);
    private static boolean isDeveloperMode = true;

    public Logger() {
    }

    public Logger(String tag) {
        this.TAG = tag;
    }

    public void info(String message) {
        if (isDeveloperMode && !TextUtils.isEmpty(message)) {
//        mLog.info(message);
            int maxLogSize = 2000;
            for (int i = 0; i <= message.length() / maxLogSize; i++) {
                int start = i * maxLogSize;
                int end = (i + 1) * maxLogSize;
                end = end > message.length() ? message.length() : end;
                mLog.info(message.substring(start, end));
            }
        }
    }

    public void severe(String message) {
        if (isDeveloperMode && !TextUtils.isEmpty(message))
            mLog.severe(message);
    }

    public void warning(String message) {
        if (isDeveloperMode && !TextUtils.isEmpty(message))
            mLog.warning(message);
    }

    public void config(String message) {
        if (isDeveloperMode && !TextUtils.isEmpty(message))
            mLog.config(message);
    }
}
