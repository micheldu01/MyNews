package com.example.michel.mynews.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
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
    // value into array for implement the CheckBox
    private CheckBox[] arrayBox;
    private int[] arrayIdBox;
    private int numberarray = 0;
    private String[] arrayValue = {",arts", ",business", ",entrepreneurs", ",politics", ",travel", ",sport"};
    // SharedPreferences
    private SharedPreferences preferences;
    public static final String MyShared = "MyShared";
    public static final String MyEditTextNoti = "MyEditTextNoti";
    public static final String[] MyCheckBoxNoti = {"arts", "business", "entrepreneurs", "politics", "travel", "sport"};
    //Share for know if nothing box are checked and nothing edit text
    public static final String CHECK = "NO_CHECK";
    public static final String TEXT = "NO_TEXT";
    public static final String NOTIF = "NOTIF";
    //value for method checkbox
    private int num = 0;
    // value for de hour of the notification
    private int hour_of_day = 11;
    private int minute = 25;
    private int second = 0;


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

        // implement the check box choice
        this.methodCheckBox();

        //Boucle for use the method to put the check box in Shared
        while (num<6){
            //call method choice and save check box
            this.methodClickCheckBox(num, arrayBox[num]);
            num++;
        }

    }

    private void configureToolbar() {
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
    public void methodAlarmManager() {
        //alarmManager
        AlarmManager am = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        //use calendar for define the hour
        Calendar calendar = Calendar.getInstance();
        //time for show the notification
        calendar.set(Calendar.HOUR_OF_DAY, hour_of_day); // For 1 PM or 2 PM
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, second);
        // PendingIntent for AlarmReceiver
        PendingIntent pi = PendingIntent.getBroadcast(this, 0,
                new Intent(this, AlarmReceiver.class), PendingIntent.FLAG_UPDATE_CURRENT);
        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(),
                //repeat start notification one a day
                AlarmManager.INTERVAL_DAY, pi);
    }


    // method for start notification
    public void methodSwitch() {
        //implementation of Switch
        aSwitch = (Switch) findViewById(R.id.switch_notification);
        //implementation of onCheckedChangeListener
        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            // on start onChecked
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                //if true start notification
                if (b) {

                    //-----------------------------------------------------//
                    // if a check box is choice and edit text              //
                    // accept to start method alarmManager else get a Toast//
                    //-----------------------------------------------------//

                    // START METHOD
                    methodOnCheck();
                }
            }
        });
    }

    public void methodEditText() {
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

                //---------------------------------
                // ask is edit text is empty
                // and save the response in shared
                //---------------------------------

                //get shared
                String str_shared = preferences.getString(MyEditTextNoti, "");


                // if is not empty save NO_EMPTY in shared
                if(str_shared.equals("")){
                    // save NO_TEXT in Shared
                    preferences.edit().putString(TEXT,"NO_TEXT").commit();
                    Log.e("mynews", "il n'y a pas de texte" + str_shared);

                }
                else {
                    // SAVE TEXT IN SHARED
                    preferences.edit().putString(TEXT, "TEXT").commit();
                    Log.e("mynews", "il y a du texte" + str_shared);

                }

            }

        });
    }

    //method for implement checkbox
    public void methodCheckBox() {
        //CheckBox name in array
        arrayBox = new CheckBox[]{arts, business, entrepreneurs, politics, travel, sport};
        //CheckBox resource  btn in array
        arrayIdBox = new int[]{R.id.checkbox_art, R.id.checkbox_business, R.id.checkbox_entrepreneurs,
                R.id.checkbox_politics, R.id.checkbox_travel, R.id.checkbox_sport};
        // implement CheckBox
        while (numberarray < 6) {
            arrayBox[numberarray] = (CheckBox) findViewById(arrayIdBox[numberarray]);
            numberarray++;
        }

    }

    // method for save check box choice in shared
    public void methodClickCheckBox(final int i, final CheckBox checkBox ){
            // implement onClick
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                        // ask if arrayBox[position] id checked
                        if(checkBox.isChecked()) {

                            //Save choice box in Shared
                            preferences.edit().putString(MyCheckBoxNoti[i], arrayValue[i]).commit();
                            //Save CHECK fo said a box is choice
                            preferences.edit().putString(CHECK, "CHECK").commit();
                    }
                }
            });
        }

        // METHOD FOR ASK IF TEXT AND CHECK IS OKAY
        private void methodOnCheck(){

            // get the shared EditText for know if is null
            String str_edit_text = preferences.getString(TEXT, "");

            // get the shared CHECK for know the result
            String str_box = preferences.getString(CHECK, "");

            // if edit text is empty
            String str_shared = preferences.getString(MyEditTextNoti, "");



            // IF BOX IF CHEKED
            if(str_box.equals("CHECK") && !str_shared.equals("")){
                //start alarmManager
                methodAlarmManager();
                // clear the shared
                preferences.edit().putString(CHECK, "NO_CHECK").commit();
                Log.e("mynews", "il n'y a pas de texte");

            }

            else{
                //get a Toast for ask to choice a check box
                Toast.makeText(NotificationsActivity.this, R.string.no_choice, Toast.LENGTH_LONG).show();

            }

        }
}