package com.example.michel.mynews.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michel.mynews.controller.AlarmReceiver;
import com.example.michel.mynews.R;

import java.util.Calendar;

public class NotificationsActivity extends AppCompatActivity {

    //Switch
    private Switch aSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);


        this.configureToolbar();

        methodSwitch();

    }
    private void configureToolbar(){
        //Get the toolbar (Serialise)
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //Set the toolbar
        setSupportActionBar(toolbar);
        // Get a support ActionBar corresponding to this toolbar
        ActionBar ab = getSupportActionBar();
        // Enable the Up button
        ab.setDisplayHomeAsUpEnabled(true);
    }

    public void methodAlarmManager(){
        //alarmManager
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        //use calendar for define the hour
        Calendar calendar = Calendar.getInstance();
        //set date
        calendar.set(Calendar.HOUR_OF_DAY, 17); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE, 30);
        calendar.set(Calendar.SECOND, 0);
        // PendingIntent for AlarmReceiver
        PendingIntent pi = PendingIntent.getBroadcast(this, 0,
                new Intent(this, AlarmReceiver.class),PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                //repeat start notification one a day
                AlarmManager.INTERVAL_DAY, pi);
    }

    // method for start notification
    public void methodSwitch(){
        //implementation of Switch
        aSwitch = (Switch) findViewById(R.id.switch_notification);
        //implementation of onCheckedChangeListener
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            // on start onChecked
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //if true start notification
                if (b){
                    //start alarmManager
                    methodAlarmManager();
                }
            }
        });
    }
}
