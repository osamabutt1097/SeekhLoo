package com.example.osamanadeem.seekhloo;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.os.Build;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Map;

import androidx.core.app.NotificationCompat;

public class MyFirebaseMessagingService extends FirebaseMessagingService {

    NotificationManager notificationManager;
    private NotificationCompat.Builder builder;
    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        if(remoteMessage.getData().size() > 0)
        {
            Map<String, String> payload = remoteMessage.getData();
            showNotification(payload);
        }
    }

    public void initChannels(Context context)
    {
        if (Build.VERSION.SDK_INT < 26)
        {
            notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            return;
        }

        notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationChannel channel = new NotificationChannel("default", "Channel name", NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("Channel description");
        channel.setLightColor(Color.WHITE);
        channel.setImportance(NotificationManager.IMPORTANCE_HIGH);
        notificationManager.createNotificationChannel(channel);
        //Toast.makeText(context, "APi >= 26", Toast.LENGTH_SHORT).show();
    }

    private void showNotification(final Map<String, String> payload) {
        Intent editIntent = new Intent(this, TutorInfoActivity.class);
        PendingIntent mClick = PendingIntent.getActivity(this, Integer.parseInt(payload.get("userCount")), editIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        initChannels(this);

        // Create Notification
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(this)
                .setLargeIcon(BitmapFactory.decodeResource(this.getResources(), R.drawable.logo))
                .setSmallIcon(R.drawable.logo)
                .setContentTitle("Course Request")
                .setTicker("Course Request")
                .setContentText(payload.get("username") + " send you request to join classroom")
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setContentIntent(mClick)
                .setAutoCancel(true)
                .setOnlyAlertOnce(true).setChannelId("default");

        if (notificationManager != null)
        {
            notificationManager.notify(Integer.parseInt(payload.get("userCount")), mBuilder.build());
        }
    }

}
