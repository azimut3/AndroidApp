package com.example.androidapp.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.androidapp.R;
import com.example.androidapp.data.Consts;
import com.example.androidapp.data.Database;
import com.example.androidapp.fragments.FragmentInput;
import com.example.androidapp.fragments.FragmentResult;
import com.example.androidapp.managers.FrgmntMngr;

public class MainActivity extends AppCompatActivity {

    private FrgmntMngr frgmntMngr;
    static private boolean inLandscapeMode;
    FragmentResult fragmentResult;
    FragmentInput fragmentInput;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        frgmntMngr = FrgmntMngr.createManager(getSupportFragmentManager(), this);


        setContentView(R.layout.main_fragments_activity);
        inLandscapeMode = findViewById(R.id.fragment_two) != null;
        Log.d("Startup","Landscape1 = " + isInLandscapeMode());

        if (!inLandscapeMode) {
            Log.d("Startup","Landscape2 = " + isInLandscapeMode());
            frgmntMngr.addFragment(frgmntMngr.getElement(FrgmntMngr.INPUT_FRAGMENT));
        } else {
            fragmentInput = (FragmentInput)getSupportFragmentManager().findFragmentById(R.id.fragment_one);
            fragmentResult = (FragmentResult)getSupportFragmentManager().findFragmentById(R.id.fragment_two);
            frgmntMngr.addToFragments(FrgmntMngr.INPUT_FRAGMENT, fragmentInput);
            frgmntMngr.addToFragments(FrgmntMngr.RESULT_FRAGMENT, fragmentResult);
        }
    }

    public static boolean isInLandscapeMode() {
        return inLandscapeMode;
    }
}
