package com.example.michel.mynews.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.mynews.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class BoxFragment extends Fragment {


    // CREATE CONSTRUCTOR
    public BoxFragment() {
        // Required empty public constructor
    }


    // CREATE VIEW
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_box, container, false);
    }
}
