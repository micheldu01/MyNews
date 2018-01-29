package com.example.michel.mynews.controller;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.mynews.R;


// create TopStoriesFragment
public class TopStoriesFragment extends Fragment {

    // create constructor
    public static TopStoriesFragment newInstance() {
        return (new TopStoriesFragment());
    }

    @Override
    // Inflate the layout for this fragment
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_top_stories, container, false);
    }


}

