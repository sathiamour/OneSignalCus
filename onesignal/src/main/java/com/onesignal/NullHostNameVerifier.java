/*
 * Created by lenovo
 * Created on 8/13/19 6:45 PM
 * Last modified 8/13/19 6:29 PM
 * Copyright (c) nStore Technologies Pvt Ltd  2019 . All rights reserved.
 */

package com.onesignal;

import android.util.Log;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLSession;


public class NullHostNameVerifier implements HostnameVerifier {

    @Override
    public boolean verify(String hostname, SSLSession session) {
        Log.i("HostNameVerifier", "Approving certificate for " + hostname);
        return true;
    }
}
