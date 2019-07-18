package com.example.androidapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;

import com.example.androidapp.R;
import com.example.androidapp.managers.FrgmntMngr;

public class FragmentResult extends Fragment {
    private EditText resultField;
    private String resultFieldText;
    private Button submitBtn;
    FrgmntMngr frgmntMngr;

    public FragmentResult() {
        this.frgmntMngr = FrgmntMngr.getManager();
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("FragmentResult", "onCreate()");
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        resultField = view.findViewById(R.id.resultField);
        if (resultFieldText != null) resultField.setText(resultFieldText);
        submitBtn = view.findViewById(R.id.resultBtn);
        submitBtn.setOnClickListener((e) -> closeAction());
        return view;
    }

    public void setResultFieldText(String text) {
        this.resultFieldText = text;
    }

    private void closeAction(){
        FrgmntMngr.getManager().getMainActivity().finish();
    }

    public Button getSubmitBtn() {
        return submitBtn;
    }

    public EditText getResultField() {
        return resultField;
    }
}
