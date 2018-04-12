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
import com.example.michel.mynews.API.SearchArticleAPI.SearchActicleAPI;
import com.example.michel.mynews.R;
import com.example.michel.mynews.view.ViewNotificationArticles;

import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

import static com.example.michel.mynews.view.NotificationsActivity.MyCheckBoxNoti;
import static com.example.michel.mynews.view.NotificationsActivity.MyEditTextNoti;
import static com.example.michel.mynews.view.NotificationsActivity.NOTIF;
import static com.example.michel.mynews.view.SearchActivity.MyDateEnd;
import static com.example.michel.mynews.view.SearchActivity.MyDateStart;

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


        // IMPLEMENT SHAREDPREFERENCES
        preferences = getSharedPreferences(MyShared, Context.MODE_PRIVATE);

        //--------------------------------------
        // CALL METHOD START OR NOT START ALARM
        //--------------------------------------
        this.myHTTPAlarm();

        Log.e("mynews","je lance la methode");


        //-------------------
        // GET SHARED YES_NO
        //-------------------
        String yes_no = preferences.getString(YES_NO, "");

        //------------------------------
        // IF YES START THE NOTIFICATION
        //------------------------------

        if(yes_no.equals("")) {

            // clear YES_NO
            preferences.edit().putString(YES_NO,"");

            // put the shared for get the number of pager adapter
            preferences.edit().putString(NOTIF,"pager4").commit();
            //TEST DU SHARED
            String page4 = preferences.getString(NOTIF,"");
            Log.e("mynews","sauvegarde de la page4" + page4);


            // using NotificationManager for get Alarm
            NotificationManager notify_manager = (NotificationManager)
                    getSystemService(NOTIFICATION_SERVICE);
            // create intent
            Intent intent_main_activity = new Intent(this.getApplicationContext(), ViewNotificationArticles.class);
            // create pendingIntent
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0,
                    intent_main_activity, 0);

            Log.e("mynews","je lance le yes");

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

        //---------------------------
        //  IMPLEMENT AND USE SHARED
        //---------------------------

        String term = preferences.getString(MyEditTextNoti,"");
        String dateStart = preferences.getString(MyDateStart,"");
        String dateEnd = preferences.getString(MyDateEnd,"");
        String[] choix = {"choix1","choix2","choix3","choix4","choix5","choix6"} ;

        int a = 0;
        while (a < 6){
            choix[a] = preferences.getString(MyCheckBoxNoti[a],"");
            a ++;
        }

        // 1.2 - Execute the stream subscribing to Observable defined inside GithubStream
        this.disposable = NytStreams.streamNotification(term+choix[0]+choix[1]+choix[2]+choix[3]+choix[4]+choix[5], true)
                .subscribeWith(new DisposableObserver<SearchActicleAPI>() {

                    @Override
                    public void onNext(SearchActicleAPI searchActicleAPI) {

                        //--------------------------------
                        //  I GET THE CURRENT TITRE
                        //  I GET THE SHARED
                        // AND I COMPARED WITH THE SHARED
                        // IF THEY ARE DIFFERENT I SAVE A NEW SHARED
                        // AND I MAKE A NOTIFICATION
                        //-----------------------------------

                        // I GET THE CURRENT TITRE
                        str = searchActicleAPI.getResponse().getDocs().get(0).getHeadline().getMain();
                        // I GET THE SHARED
                        String s = preferences.getString(TITRE,"");
                        // I COMPARE THEM

                        if(!str.equals(s)){

                            preferences.edit().putString(TITRE, str).commit();

                            preferences.edit().putString(YES_NO, "YES").commit();
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
