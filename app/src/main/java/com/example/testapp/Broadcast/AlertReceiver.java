package com.example.testapp.Broadcast;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.core.app.NotificationCompat;

public class AlertReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Context mcontext;
        Bundle bundle=intent.getExtras();
        String text=bundle.getString("event");
        String date=bundle.getString("date")+" "+bundle.getString("time");

        NotificationHelper notificationHelper = new NotificationHelper(context);
        NotificationCompat.Builder nb = notificationHelper.getChannelNotification();


        notificationHelper.getManager().notify(1, nb.build());
    }
}
