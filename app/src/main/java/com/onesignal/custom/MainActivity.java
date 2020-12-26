package com.onesignal.custom;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.onesignal.OSDeviceState;
import com.onesignal.OSPermissionObserver;
import com.onesignal.OSPermissionStateChanges;
import com.onesignal.OSSubscriptionObserver;
import com.onesignal.OSSubscriptionStateChanges;
import com.onesignal.OneSignal;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private Logger mLog=new Logger(MainActivity.class.getSimpleName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOneSignal();
    }

    private void initOneSignal() {
//        OneSignal.setSubscription(true);
//        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
//            @Override
//            public void idsAvailable(String userId, String registrationId) {
//                String text = "OneSignal UserID:\n" + userId + "\n\n";
//                text += registrationId != null ? "Google Registration Id:\n" + registrationId : "Google Registration Id:\nCould not subscribe for push";
//                mLog.info("OneSignal : OneSignal Device Registered. " + text);
//                mLog.info("OneSignal : userId : " + userId + ", registrationId : " + registrationId);
//                if (!TextUtils.isEmpty(userId)) {
//                    OneSignal.sendTag("onesignal_user_id", userId);
////                    OneSignal.sendTag("uid",mUid);
//                    OneSignal.sendTag("user_key", "100");
//                    OneSignal.setSubscription(true);
//                    mLog.info("OneSignal : user_key : 100");
////                    Toast.makeText(getBaseContext(),"Device registered to Onesignal",Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
        OneSignal.addPermissionObserver(new OSPermissionObserver() {
            @Override
            public void onOSPermissionChanged(OSPermissionStateChanges stateChanges) {
                OSDeviceState deviceState = OneSignal.getDeviceState();
                final boolean isSubscribed = deviceState != null && deviceState.isSubscribed();
                boolean isPermissionEnabled = stateChanges.getTo().areNotificationsEnabled();
                mLog.info("Subscription Enabled : "+isSubscribed);
                mLog.info("Notification Enabled : "+isPermissionEnabled);
            }
        });
        OneSignal.addSubscriptionObserver(new OSSubscriptionObserver() {
            @Override
            public void onOSSubscriptionChanged(OSSubscriptionStateChanges stateChanges) {
                boolean isSubscribed = stateChanges.getTo().isSubscribed();
                mLog.info("onOSSubscriptionChanged : "+isSubscribed);
//                if(isSubscribed){
//                    OneSignal.sendTag("user_key", "100");
//                }
            }
        });
        OneSignal.sendTag("user_key", "100");
        OneSignal.getTags(new OneSignal.OSGetTagsHandler() {
            @Override
            public void tagsAvailable(JSONObject tags) {
                mLog.info("Onesignal Tags : "+tags);
            }
        });

        OneSignal.disablePush(false);
        mLog.info("*************************push enabled*******************************");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
//        OneSignal.setSubscription(false);
    }
}
