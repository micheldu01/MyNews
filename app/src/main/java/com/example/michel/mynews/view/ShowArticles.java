package com.example.michel.mynews.view;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebView;


import com.example.michel.mynews.R;

import static com.example.michel.mynews.FragmentsView.TopStoriesFragment.MYSHARED;
import static com.example.michel.mynews.FragmentsView.TopStoriesFragment.URL_NYT;

public class ShowArticles extends AppCompatActivity {

    private WebView webView;

    private Toolbar toolbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_articles);


        webView = (WebView) findViewById(R.id.web_view_1);
        toolbar = (Toolbar) findViewById(R.id.test_toolbar);
        SharedPreferences prefs = getSharedPreferences(MYSHARED, MODE_PRIVATE);


        String url = prefs.getString(URL_NYT, "");




        setSupportActionBar(toolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        webView.loadUrl(url);




    }
}
