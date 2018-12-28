package com.example.elkaiserblaze.minimal_todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

public class AlertReceiver extends BroadcastReceiver {
    private Bundle bundle;
    private Task task;
    @Override
    public void onReceive(Context context, Intent intent) {
       NotificationChannelDeclaration notificationChannelDeclaration=new NotificationChannelDeclaration();
       bundle = (Bundle) intent.getBundleExtra("dataTask");
       task =(Task) bundle.getSerializable("task");
       notificationChannelDeclaration.sendNotificationAlarm(context,task);
    }
}
