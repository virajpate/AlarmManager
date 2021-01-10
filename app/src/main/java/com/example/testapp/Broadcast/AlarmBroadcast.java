package com.example.testapp.Broadcast;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.widget.RemoteViews;

import androidx.core.app.NotificationCompat;

import com.example.testapp.Activity.NotificationMessage;
import com.example.testapp.R;

public class AlarmBroadcast extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Bundle bundle=intent.getExtras();
        String text=bundle.getString("event");
        String date=bundle.getString("date")+" "+bundle.getString("time");

        //Click on Notification
        Intent intent1=new Intent(context, NotificationMessage.class);
        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent1.putExtra("message",text);

        //Notification Builder
        PendingIntent pendingIntent=PendingIntent.getActivity(context,1,intent1,PendingIntent.FLAG_ONE_SHOT);
        NotificationManager notificationManager=(NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder mBuilder=new NotificationCompat.Builder(context,"notify_001");

        RemoteViews contentview=new RemoteViews(context.getPackageName(), R.layout.activity_notification_message);
        //contentview.setImageViewResource(R.id.image,R.drawable.ic_add);
        PendingIntent pendingSwitchIntent=PendingIntent.getBroadcast(context,0,intent,0);
        contentview.setOnClickPendingIntent(R.id.flashBtn,pendingSwitchIntent);
        contentview.setTextViewText(R.id.messages,text);
        contentview.setTextViewText(R.id.date,date);

       // mBuilder.setSmallIcon(R.drawable.ic_add);
        mBuilder.setAutoCancel(false);
        mBuilder.setOngoing(true);
        mBuilder.setPriority(Notification.PRIORITY_HIGH);
        mBuilder.setOnlyAlertOnce(true);
        mBuilder.build().flags =Notification.FLAG_NO_CLEAR | Notification.PRIORITY_HIGH;
        mBuilder.setContent(contentview);
        mBuilder.setContentIntent(pendingIntent);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            String channelId = "channel_id";
            NotificationChannel channel = new NotificationChannel(channelId, "channel name", NotificationManager.IMPORTANCE_HIGH);
            channel.enableVibration(true);
            notificationManager.createNotificationChannel(channel);
            mBuilder.setChannelId(channelId);
        }

        Notification notification =mBuilder.build();
        notificationManager.notify(1,notification);
    }

}
