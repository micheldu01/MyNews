package com.example.michel.mynews.controller;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.michel.mynews.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class EditTextQueryTermFragment extends Fragment {

    private EditText editText;
    public static final String PREFS = "prefs";

    public EditTextQueryTermFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_text_query_term, container, false);
        editText = (EditText)view.findViewById(R.id.search_query_term);
        String st = editText.getText().toString();
        SharedPreferences preferences = this.getActivity().getSharedPreferences(PREFS, Context.MODE_PRIVATE);
        preferences.edit().putString("values",st).commit();
        return view;
    }
}
