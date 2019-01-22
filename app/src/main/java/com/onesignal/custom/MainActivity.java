package com.onesignal.custom;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;

import com.onesignal.OneSignal;

public class MainActivity extends AppCompatActivity {
    private Logger mLog=new Logger(MainActivity.class.getSimpleName());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initOneSignal();
    }

    private void initOneSignal() {
        OneSignal.setSubscription(true);
        OneSignal.idsAvailable(new OneSignal.IdsAvailableHandler() {
            @Override
            public void idsAvailable(String userId, String registrationId) {
                String text = "OneSignal UserID:\n" + userId + "\n\n";
                text += registrationId != null ? "Google Registration Id:\n" + registrationId : "Google Registration Id:\nCould not subscribe for push";
                mLog.info("OneSignal : OneSignal Device Registered. " + text);
                mLog.info("OneSignal : userId : " + userId + ", registrationId : " + registrationId);
                if (!TextUtils.isEmpty(userId)) {
                    OneSignal.sendTag("onesignal_user_id", userId);
//                    OneSignal.sendTag("uid",mUid);
                    OneSignal.sendTag("user_key", "100");
                    OneSignal.setSubscription(true);
                    mLog.info("OneSignal : user_key : 100");
//                    Toast.makeText(getBaseContext(),"Device registered to Onesignal",Toast.LENGTH_SHORT).show();
                }
            }
        });
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
        OneSignal.setSubscription(false);
    }
}
