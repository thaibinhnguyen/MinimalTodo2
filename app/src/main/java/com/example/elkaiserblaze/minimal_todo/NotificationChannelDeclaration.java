package com.example.elkaiserblaze.minimal_todo;

import android.app.Application;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;

public class NotificationChannelDeclaration extends Application {
    public static final String CHANNEL_ALARM_ID="channel_alarm";
    @Override
    public void onCreate() {
        super.onCreate();
        createNotificationChannel();
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel_alarm = new NotificationChannel(
                    CHANNEL_ALARM_ID,
                    "Channel alarm",
                    NotificationManager.IMPORTANCE_HIGH
            );
            channel_alarm.setDescription("This is channel alarm");
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel_alarm);

        }
    }

    public void sendNotificationAlarm(Context context,Task task){
        Notification notification_alarm= new NotificationCompat.Builder(context,NotificationChannelDeclaration.CHANNEL_ALARM_ID)
                .setSmallIcon(R.drawable.ic_alarm)
                .setContentTitle(task.getTitle())
                .setContentText(task.getDate())
                .setPriority(NotificationCompat.PRIORITY_HIGH)
                .build();
        NotificationManagerCompat notificationManager;
        notificationManager = NotificationManagerCompat.from(context);
        notificationManager.notify(1,notification_alarm);
    }

}
