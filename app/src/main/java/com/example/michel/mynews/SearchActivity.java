package com.example.michel.mynews;

import android.app.DatePickerDialog;
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
    private CheckBox[] arrayBox;
    private int[] arrayIdBox;
    private int numberarray = 0;
    // Date
    private TextView begin_date;
    private TextView end_date;
    private Calendar mCurrentDate;
    private int day, month, year;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        this.configureToolbar();

        //date
        begin_date = (TextView) findViewById(R.id.show_date_begin);
        end_date = (TextView) findViewById(R.id.show_date_end);
        //CurrentDate
        mCurrentDate = Calendar.getInstance();
        mCurrentDate = Calendar.getInstance();
        day = mCurrentDate.get(Calendar.DAY_OF_MONTH);
        month = mCurrentDate.get(Calendar.MONTH);
        year = mCurrentDate.get(Calendar.YEAR);
        month = month +1;
        begin_date.setText(convertDate(day)+"/"+convertDate(month)+"/"+year);

        //EditText
        editText = (EditText)findViewById(R.id.search_query_term);

        //CheckBox
        arrayBox = new CheckBox[] {arts, business, entrepreneurs, politics, travel, sport};
        arrayIdBox = new int[] {R.id.checkbox_art,R.id.checkbox_business,R.id.checkbox_entrepreneurs,
                R.id.checkbox_politics,R.id.checkbox_travel,R.id.checkbox_sport};

        // implement CheckBox
        while(numberarray <6){
            arrayBox[numberarray] = (CheckBox)findViewById(arrayIdBox[numberarray]);
            Log.i("mycoursviewpager","number = " + numberarray);
            numberarray++;
        }

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

    @Override
    public void onButonClicked(View view) {
        // receive the editText
        String et = editText.getText().toString();
        Toast.makeText(SearchActivity.this," Button clicked ", Toast.LENGTH_LONG).show();
        if(arrayBox[0].isChecked()){
            Toast.makeText(SearchActivity.this," Arts ", Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void onButonClickedDateLeft(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog
                (SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        begin_date.setText(convertDate(day)+"/"+convertDate(month+1)+"/"+year);
                    }
                }, year, month, day);
        datePickerDialog.show();

    }

    @Override
    public void onButonClickedDateRight(View view) {
        DatePickerDialog datePickerDialog = new DatePickerDialog
                (SearchActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        end_date.setText(convertDate(day)+"/"+convertDate(month+1)+"/"+year);
                    }
                }, year, month, day);
        datePickerDialog.show();
    }
    // Method for add 0 in the date
    public String convertDate(int input) {
        if (input >= 10) {
            return String.valueOf(input);
        } else {
            return "0" + String.valueOf(input);
        }
    }
}
