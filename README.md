<p align="center">
  # OneSignalCus
</p>
### OneSignal Android Push Notification Plugin - Customized version
---

This plugin is only for native android and except notification all the other services are removed from the library.
OneSignal is a free push notification service for mobile apps. This plugin makes it easy to integrate your native Android or Amazon app with OneSignal.

<p align="center"><img src="https://app.onesignal.com/images/android_notification_image.gif" width="400" alt="Android Notification"></p>

#### Installation
See OneSignal's [Android Native SDK Setup Guide](https://documentation.onesignal.com/docs/android-sdk-setup) for documentation.

#### API
See OneSignal's [Android Native SDK API](https://documentation.onesignal.com/docs/android-native-sdk) page for a list of all available methods.

#### Change Log
See this repository's [release tags](https://github.com/sathiamour/OneSignalCus/releases) for a complete change log of every released version.

#### Custom Library Changes: 
 * Onesignal class, setRemoteNotificationReceivedHandler() method changed as public to receive Notification directly to the app.
 * OSNotificationController class, In the Constructor method notificationJob(OSNotificationGenerationJob) set as null to avoid pushing notification directly from Onesignal library.
 * OSNotificationController class, In the processNotification() method while set modified notification check notificationJob(OSNotificationGenerationJob) is not null to avoid NullPointerException.
 * GenerateNotification class, In the fromJsonPayload() method showNotification() method was commented/removed to avoid showing notification directly from Onesignal library.

#### Demo Project
To make things easier, we have published demo projects in the `/app` folder of this repository.

#### Supports:
* Tested from Android 4.0.3 (API level 15) to Android 11 (30)

