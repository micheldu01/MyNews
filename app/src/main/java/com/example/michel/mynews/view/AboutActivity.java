package com.example.michel.mynews.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.example.michel.mynews.R;

public class AboutActivity extends AppCompatActivity {

    //add TextView value
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        // implement TextView
        textView = (TextView) findViewById(R.id.text_about);


        //use the configureToolbar method
        this.configureToolbar();
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
}
