package com.example.michel.mynews.Fragments;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.michel.mynews.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class DateFragment extends Fragment implements View.OnClickListener {

    // declare callback
    private OnButtonClickedListener mCallback;
    private OnButtonClickedListener2 mCallback2;

    // declare callback
    public interface OnButtonClickedListener {
        public void onButonClickedDateLeft(View view);
    }

    // declare callback
    public interface OnButtonClickedListener2 {
        public void onButonClickedDateRight(View view);
    }

    // CREATE CONSTRUCTOR
    public DateFragment() {
        // Required empty public constructor
    }

    // implement the View (date)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_date, container, false);
        result.findViewById(R.id.btn_date_left).setOnClickListener(this);
        // Inflate second image button
        result.findViewById(R.id.btn_date_right).setOnClickListener(this);
        return result;
    }

    // CALL METHOD FOR ATTACH PARENT ACTIVITY
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Call the method that creating callback after being attached to parent activity
        this.createCallbackToParentActivity();
    }

    // CALL METHOD ON CLICK
    @Override
    public void onClick(View view) {
        // Here is handled the button click
        // spread the click to the parent activity
        // Choice between mCallback mCallback2
        switch (view.getId()){
            case R.id.btn_date_left:
                mCallback.onButonClickedDateLeft(view);
                break;
            case R.id.btn_date_right:
                mCallback2.onButonClickedDateRight(view);
                break;
        }
    }

    // create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            // Parent activity will automatically subscribe to callback
            mCallback = (DateFragment.OnButtonClickedListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(e.toString()+" must implement OnButtonClickedListner");
        }
        try {
            // Parent activity will automatically subscribe to callback
            mCallback2 = (DateFragment.OnButtonClickedListener2) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(e.toString()+" must implement OnButtonClickedListner");
        }
    }
}
