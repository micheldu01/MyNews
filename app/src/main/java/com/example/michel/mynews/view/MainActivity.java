package com.example.michel.mynews.view;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.michel.mynews.controller.PageAdapter;
import com.example.michel.mynews.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //configuring Toolbar
        this.configureToolbar();
        // Configure tablayout
        this.configureViewPagerAndTabs();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //2 - Inflate the menu and add it to the Toolbar
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    private void configureToolbar() {
        // Get the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.activity_main_toolbar);
        // Sets the Toolbar
        setSupportActionBar(toolbar);
    }

    private void configureViewPagerAndTabs(){
        //Get ViewPager from layout
        ViewPager pager = (ViewPager)findViewById(R.id.activity_main_viewpager);
        //Set Adapter PageAdapter and glue it together
        pager.setAdapter(new PageAdapter(getSupportFragmentManager()));

        // 1 - Get TabLayout from layout
        TabLayout tabs= (TabLayout)findViewById(R.id.activity_main_tabs);
        // 2 - Glue TabLayout and ViewPager together
        tabs.setupWithViewPager(pager);
        // 3 - Design purpose. Tabs have the same width
        tabs.setTabMode(TabLayout.MODE_FIXED);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // implement menu selected
        switch (item.getItemId()) {
            // intent for Search
            case R.id.menu_activity_main_search:
                launchDetailActivity(SearchActivity.class);
                return true;
            // intent for notifications
            case R.id.menu_activity_main_notifications:
                launchDetailActivity(NotificationsActivity.class);
                return true;
            // intent for help
            case R.id.menu_activity_main_help:
                launchDetailActivity(HelpActivity.class);
                return true;
            // intent for about
            case R.id.menu_activity_main_about:
                launchDetailActivity(AboutActivity.class);
                return true;
            // if else
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    // method for use intent
    private void launchDetailActivity(Class context2) {
        Intent myIntent = new Intent(MainActivity.this, context2);
        this.startActivity(myIntent);
    }
}