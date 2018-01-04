package com.example.michel.mynews.controller;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by michel on 03/01/2018.
 */

public class AlarmReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {

        Intent service_intent = new Intent(context, NotificationService.class);
        context.startService(service_intent);
    }
}
