package com.example.michel.mynews.FragmentsView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;

import com.example.michel.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ShowArticlesFragment extends Fragment {

    private WebView webView;


    public ShowArticlesFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_show_articles, container, false);

        webView = (WebView) view.findViewById(R.id.web_view_show_articles_fragment);

        webView.loadUrl("http://www.cgtrvi.com");
        WebSettings webSettings =  webView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        return view;
    }

}
