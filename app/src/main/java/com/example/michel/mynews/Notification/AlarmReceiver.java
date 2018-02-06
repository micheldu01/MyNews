package com.example.michel.mynews.Notification;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

import com.example.michel.mynews.Notification.NotificationService;

/**
 * Created by michel on 03/01/2018.
 */

    //create a broadcastReceiver for use notification
public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        // add intent for go  the NotificationService class
        Intent service_intent = new Intent(context, NotificationService.class);
        context.startService(service_intent);
    }
}
