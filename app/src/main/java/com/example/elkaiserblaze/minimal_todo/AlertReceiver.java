package com.example.elkaiserblaze.minimal_todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class AlertReceiver extends BroadcastReceiver {
    private Bundle bundle;
    private Task task;
    @Override
    public void onReceive(Context context, Intent intent) {
       NotificationAlarmHelper notificationAlarmHelper =new NotificationAlarmHelper();
       bundle = (Bundle) intent.getBundleExtra("dataTask");
       task =(Task) bundle.getSerializable("task");
       notificationAlarmHelper.sendNotificationAlarm(context,task);
    }
}
