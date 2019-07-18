package com.example.androidapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.androidapp.fragments.FragmentInput;
import com.example.androidapp.fragments.FragmentResult;
import com.example.androidapp.managers.FrgmntMngr;

public class MainActivity extends AppCompatActivity {

    private FrgmntMngr frgmntMngr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frgmntMngr = FrgmntMngr.createManager(getSupportFragmentManager(), this);

        setContentView(R.layout.main_fragments_activity);
        frgmntMngr.addFragment(frgmntMngr.getElement(FrgmntMngr.INPUT_FRAGMENT));
    }

}
