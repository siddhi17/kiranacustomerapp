package com.kiranacustomerapp.helper;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;
import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;
import com.kiranacustomerapp.R;


/**
 * Created by Siddhi on 10/18/2016.
 */
public class MyFirebaseMessagingService extends FirebaseMessagingService {

    private static final String TAG = "MyFirebaseMsgService";
    private String mUserId;
    private Boolean mUpdateNotification;

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        //Displaying data in log
        //It is optional
        Log.d(TAG, "From: " + remoteMessage.getFrom());
        Log.d(TAG, "Notification Message Body: " + remoteMessage.getNotification().getBody());

        String clickAction = remoteMessage.getNotification().getClickAction();

        mUserId = remoteMessage.getData().get("user_id");

        String title = remoteMessage.getNotification().getTitle();

        //Calling method to generate notification
      //  sendNotification(remoteMessage.getNotification().getBody(),clickAction,title);
    }

    //This method is only generating push notification
    //It is same as we did in earlier posts
   /* private void sendNotification(String messageBody,String clickAction,String title) {

        mUpdateNotification = true;

        Intent intent = new Intent(clickAction);

        intent.putExtra("userId",mUserId);
        intent.putExtra("updateNotification",mUpdateNotification);

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,
                PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri= RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setContentTitle(title)
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSmallIcon(R.drawable.kirana);
                .setSound(defaultSoundUri)
                .setColor(ContextCompat.getColor(this,R.color.black))
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.notify(0, notificationBuilder.build());
    }
  /*  private int getNotificationIcon() {
        boolean useWhiteIcon = (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP);
       // return useWhiteIcon ? R.drawable.notification_icon : R.drawable.notification_icon;
    }*/
}