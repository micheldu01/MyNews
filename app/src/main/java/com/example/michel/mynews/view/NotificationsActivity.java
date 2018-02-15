package com.example.michel.mynews.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import com.example.michel.mynews.Notification.AlarmReceiver;
import com.example.michel.mynews.R;

import java.util.Calendar;

public class NotificationsActivity extends AppCompatActivity {

    //Switch
    private Switch aSwitch;
    //EditText
    private EditText editText;
    //CheckBox
    private CheckBox arts;
    private CheckBox business;
    private CheckBox entrepreneurs;
    private CheckBox politics;
    private CheckBox travel;
    private CheckBox sport;
    // SharedPreferences
    private SharedPreferences preferences;
    public static final String MyShared = "MyShared";
    public static final String MyEditTextNoti = "MyEditTextNoti";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notifications);


        // Declare SharedPreferences
        preferences = getSharedPreferences(MyShared, Context.MODE_PRIVATE);


        // show toolbar
        this.configureToolbar();

        // method for use the switch button
        this.methodSwitch();

        // method for save the edit text in shared
        this.methodEditText();

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

    // method for get the time and the design of the notification
    public void methodAlarmManager(){
        //alarmManager
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        //use calendar for define the hour
        Calendar calendar = Calendar.getInstance();
        //time for show the notification
        calendar.set(Calendar.HOUR_OF_DAY, 17); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE, 47);
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

                    //Implement edit text
                    editText = (EditText) findViewById(R.id.search_query_term);
                    //get the edit text
                    String str = editText.getText().toString();
                }
            }
        });
    }

    public void methodEditText(){
        editText = (EditText) findViewById(R.id.search_query_term);

        //Save Edit text in method addTextChangedListener
        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            // after the edit text is changed
            @Override
            public void afterTextChanged(Editable editable) {
                // get edit text
                String edt = editText.getText().toString();

                // Use Shared for save the edit text
                preferences.edit().putString(MyEditTextNoti, edt).commit();

            }
        });


    }
}
