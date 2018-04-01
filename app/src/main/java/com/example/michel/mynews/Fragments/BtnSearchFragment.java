package com.example.michel.mynews.Fragments;


import android.content.Context;
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
public class BtnSearchFragment extends Fragment implements View.OnClickListener {

    // declare callback
    private OnButtonClickedListener mCallback;
    // declare editText
    private EditText editText;

    // declare callback
    public interface OnButtonClickedListener {
        public void onButonClicked(View view);
    }

    public BtnSearchFragment() {
        // Required empty public constructor
    }



    // method View
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View result = inflater.inflate(R.layout.fragment_btn_search, container, false);
        result.findViewById(R.id.btn_search).setOnClickListener(this);
        return result;
    }

    // method for attach the createCallbackToParentActivity into the context
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        // Call the method that creating callback after being attached to parent activity
        this.createCallbackToParentActivity();
    }

    // CREATE METHOD ONCLICK
    @Override
    public void onClick(View view) {
        // Here is handled the button click
        // spread the click to the parent activity
        mCallback.onButonClicked(view);
    }

    // create callback to parent activity
    private void createCallbackToParentActivity(){
        try {
            // Parent activity will automatically subscribe to callback
            mCallback = (OnButtonClickedListener) getActivity();
        }catch (ClassCastException e){
            throw new ClassCastException(e.toString()+" must implement OnButtonClickedListner");
        }
    }
}
