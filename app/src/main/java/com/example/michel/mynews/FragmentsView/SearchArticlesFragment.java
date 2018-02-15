package com.example.michel.mynews.FragmentsView;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.mynews.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class SearchArticlesFragment extends Fragment {


    // create constructor
    public static SearchArticlesFragment newInstance() {
        return (new SearchArticlesFragment());
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search_articles, container, false);
    }

}
