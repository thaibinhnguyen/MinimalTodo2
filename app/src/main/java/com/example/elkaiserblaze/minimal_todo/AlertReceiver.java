package com.example.elkaiserblaze.minimal_todo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
       NotificationChannelDeclaration.sendNotificationAlarm(context);
    }
}
