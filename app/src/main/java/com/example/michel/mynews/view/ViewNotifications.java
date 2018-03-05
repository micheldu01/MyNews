package com.example.michel.mynews.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.example.michel.mynews.R;

public class ViewNotifications extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_notifications);


        //show the toolbar
        this.configureToolbar();

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
}
