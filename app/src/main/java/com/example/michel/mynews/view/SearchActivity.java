package com.example.michel.mynews.view;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.michel.mynews.Fragments.BtnSearchFragment;
import com.example.michel.mynews.Fragments.DateFragment;
import com.example.michel.mynews.R;

import java.util.Calendar;

public class SearchActivity extends AppCompatActivity
    implements BtnSearchFragment.OnButtonClickedListener,
        DateFragment.OnButtonClickedListener,
                DateFragment.OnButtonClickedListener2{

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
    // Date
    private TextView begin_date;
    private TextView end_date;
    private Calendar mCurrentDate;
    private int day, month, year;
    // SharedPreferences
    private SharedPreferences preferences;
    public static final String MyShared = "MyShared";
    public static final String MyEditText = "MyEditText";
    public static final String MyDateStart = "MyDateStart";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        // Declare SharedPreferences
        preferences = getSharedPreferences(MyShared, Context.MODE_PRIVATE);



        //Add toolbar
        this.configureToolbar();

        //Add CurrentDate
        currentDateMethod();

        //Add checkbox
        methodCheckBox();

    }
    // method for create toolbar view
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

    // method for get the result of the checkBox and the EditText
    @Override
    public void onButonClicked(View view) {
        // receive the editText
        editText = (EditText)findViewById(R.id.search_query_term);
        String et = editText.getText().toString();

        // Create SharedPreferences
        preferences.edit().putString(MyEditText, et).commit();

        // Test Edit Text
        String stg = preferences.getString(MyEditText, "");



        // get value CheckBox
        if(arrayBox[0].isChecked()){
            //Toast.makeText(SearchActivity.this," Arts ", Toast.LENGTH_LONG).show();

        }

    }

    // method for get the date number 1
    @Override
    public void onButonClickedDateLeft(View view) {
        // using DatePickerDialog for show the date
        DatePickerDialog datePickerDialog = new DatePickerDialog
                (SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        begin_date.setText(convertDate(day)+"/"+convertDate(month+1)+"/"+year);

                        // Create String for get date
                        String dateStart = convertDate(day)+"/"+convertDate(month+1)+"/"+year;
                        preferences.edit().putString(MyEditText, dateStart).commit();







                    }
                }, year, month, day);
        datePickerDialog.show();


    }

    // method for get the date number 2
    @Override
    public void onButonClickedDateRight(View view) {
        // using DatePickerDialog for show the date
        DatePickerDialog datePickerDialog = new DatePickerDialog
                (SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        end_date.setText(convertDate(day)+"/"+convertDate(month+1)+"/"+year);

                        // Create String for get date
                        String dateEnd = convertDate(day)+"/"+convertDate(month+1)+"/"+year;
                        Toast.makeText(SearchActivity.this, dateEnd, Toast.LENGTH_SHORT).show();

                    }
                }, year, month, day);
        datePickerDialog.show();
    }
    // Method for add 0 in the date (day and month)
    public String convertDate(int input) {
        if (input >= 10) {
            return String.valueOf(input);
        } else {
            return "0" + String.valueOf(input);
        }
    }

    // method for get current date
    public void currentDateMethod(){
        //date current
        // implement TextView
        begin_date = (TextView) findViewById(R.id.show_date_begin);
        end_date = (TextView) findViewById(R.id.show_date_end);
        //get current date
        mCurrentDate = Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);

        // Create String for get date
        String dateStart = convertDate(day)+"/"+convertDate(month+1)+"/"+year;
        Toast.makeText(SearchActivity.this, dateStart, Toast.LENGTH_SHORT).show();
    }

    //method for implement checkbox
    public void methodCheckBox(){
        //CheckBox name in array
        arrayBox = new CheckBox[] {arts, business, entrepreneurs, politics, travel, sport};
        //CheckBox resource  btn in array
        arrayIdBox = new int[] {R.id.checkbox_art,R.id.checkbox_business,R.id.checkbox_entrepreneurs,
                R.id.checkbox_politics,R.id.checkbox_travel,R.id.checkbox_sport};
        // implement CheckBox
        while(numberarray <6){
            arrayBox[numberarray] = (CheckBox)findViewById(arrayIdBox[numberarray]);
            Log.i("mycoursviewpager","number = " + numberarray);
            numberarray++;
        }
    }
}
