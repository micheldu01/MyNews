package com.example.michel.mynews.Notification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import com.example.michel.mynews.API.NytStreams;
import com.example.michel.mynews.API.TopStories.TopStoriesAPI;
import com.example.michel.mynews.R;
import com.example.michel.mynews.view.MainActivity;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by michel on 03/01/2018.
 */

    // create class for use notification service
public class NotificationService extends Service {

    // DECLARE DISPOSABLE VALUE
    private Disposable disposable;
    private String str;
    // DECLARE SHAREDPREFERENCES VALUES
    private SharedPreferences preferences;
    public static final String MyShared = "MyShared";
    public static final String TITRE = "";
    public static final String YES_NO = "YES";


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }


    // create method for create the notification (text, icon ...)
    public int onStartCommand(Intent intent, int flags, int startId) {

        //--------------------------------------
        // CALL METHOD START OR NOT START ALARM
        //--------------------------------------
        this.myHTTPAlarm();

        // IMPLEMENT SHAREDPREFERENCES
        preferences = getSharedPreferences(MyShared, Context.MODE_PRIVATE);


        // using NotificationManager for get Alarm
        NotificationManager notify_manager = (NotificationManager)
                getSystemService(NOTIFICATION_SERVICE);
        // create intent
        Intent intent_main_activity = new Intent(this.getApplicationContext(), MainActivity.class);
        // create pendingIntent
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                intent_main_activity, 0);

        //-------------------
        // GET SHARED YES_NO
        //-------------------
        String stt = preferences.getString(YES_NO, "");

        //------------------------------
        // IF YES START THE NOTIFICATION
        //------------------------------

        if(stt.equals("YES")) {

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
            }

            return START_NOT_STICKY;
    }

    private void myHTTPAlarm() {

        // 1.2 - Execute the stream subscribing to Observable defined inside GithubStream
        this.disposable = NytStreams.streamTopStories()
                .subscribeWith(new DisposableObserver<TopStoriesAPI>() {

                    @Override
                    public void onNext(TopStoriesAPI nYresult) {

                        //--------------------------------
                        //  I GET THE CURRENT TITRE
                        //  I GET THE SHARED
                        // AND I COMPARED WITH THE SHARED
                        // IF THEY ARE DIFFERENT I SAVE A NEW SHARED
                        // AND I MAKE A NOTIFICATION
                        //-----------------------------------

                        // I GET THE CURRENT TITRE
                        str = nYresult.getResults().get(0).getTitle();
                        // I GET THE SHARED
                        String s = preferences.getString(TITRE,"");
                        // I COMPARE THEM
                        Log.e("mynews","récupération du shared &&&&&&&&&&&&" + s);

                        if(!str.equals(s)){
                            Log.e("mynews","test du if +++++++++++++++++++++++++" + s);
                            preferences.edit().putString(TITRE, str).commit();
                            preferences.edit().putString(YES_NO, "YES");
                        }
                        else{
                            Log.e("mynews","test du if WWWWWWWWWWWWWWWWWWWWWWWWW" + s);
                            preferences.edit().putString(TITRE, "je suis laurent").commit();

                        }


                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("TAG", "On Error" + Log.getStackTraceString(e));
                    }

                    @Override
                    public void onComplete() {
                        Log.e("TAG", "On Complete !!");
                    }
                });
    }


    private void disposeWhenDestroy(){
        if (this.disposable != null && !this.disposable.isDisposed()) this.disposable.dispose();
    }

    @Override
    public void onDestroy() {}
}
