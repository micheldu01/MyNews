package com.example.michel.mynews.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.example.michel.mynews.R;
import com.example.michel.mynews.view.MainActivity;

/**
 * Created by michel on 03/01/2018.
 */

    // create class for use notification service
public class NotificationService extends Service {


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // create method for create the notification (text, icon ...)
    public int onStartCommand(Intent intent, int flags, int startId) {

        // using NotificationManager for get Alarm
        NotificationManager notify_manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        // create intent
        Intent intent_main_activity = new Intent(this.getApplicationContext(), MainActivity.class);
        // create pendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent_main_activity, 0);
        // create notification poupup
        Notification notification_poupup = new Notification.Builder(this)
                //add title
                .setContentTitle("le message est bien passé")
                //add text
                .setContentText("ajout du text")
                //add icon
                .setSmallIcon(R.mipmap.ic_launcher)
                //use pendingIntent
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)
                .build();

        // use notify
        notify_manager.notify(0, notification_poupup);


        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {}
}
