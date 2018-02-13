package com.example.michel.mynews.view;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.michel.mynews.FragmentsView.ShowArticlesFragment;
import com.example.michel.mynews.R;

public class ShowArticlesActivity extends AppCompatActivity {



    //declare Show articles fragment
    private ShowArticlesFragment articlesFragment;
    //declare web view
    private WebView webView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_articles);


        //Add toolbar
        this.configureToolbar();


        // implement web view
        webView = (WebView) findViewById(R.id.web_view_show_articles_fragment);

        //call url with LoadUrl and show it in web view with web setting
        webView.loadUrl("http://www.cgtrvi.com");
        WebSettings webSettings =  webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

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
}
