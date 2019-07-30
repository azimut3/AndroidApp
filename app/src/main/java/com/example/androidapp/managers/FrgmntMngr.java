package com.example.androidapp.managers;

import android.content.Context;
import android.database.Cursor;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;


import com.example.androidapp.activities.MainActivity;
import com.example.androidapp.R;
import com.example.androidapp.data.Database;
import com.example.androidapp.fragments.FragmentInput;
import com.example.androidapp.fragments.FragmentResult;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class FrgmntMngr {
    private static FrgmntMngr frgmntMngr;
    private FragmentManager manager;
    private Map<String, Fragment> fragmentsMap;
    MainActivity mainActivity;
    private static Database database;

    public static final String INPUT_FRAGMENT = "input";
    public static final String RESULT_FRAGMENT = "result";

    private FrgmntMngr(FragmentManager manager, MainActivity mainActivity) {
        this.manager = manager;
        this.mainActivity = mainActivity;
        fragmentsMap = new TreeMap<>();
        fragmentsMap.put(INPUT_FRAGMENT, new FragmentInput());
        fragmentsMap.put(RESULT_FRAGMENT, new FragmentResult());
    }

    public void toRecipientFragment(String date){
        ((FragmentResult)fragmentsMap.get(RESULT_FRAGMENT)).updateList(date);
    }


    public void addFragment(Fragment fragment) {
        Log.wtf("WTF", "Add method " + MainActivity.isInLandscapeMode());
        if (MainActivity.isInLandscapeMode() != true) {
            Log.wtf("WTF", "Add method worked");
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.add(R.id.fragment_container, fragment);
            transaction.commit();
        }
    }

    public void removeFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }

    public Fragment replaceFragment(Fragment fragment) {
        Log.wtf("WTF", "Replace method " + MainActivity.isInLandscapeMode());
        if (MainActivity.isInLandscapeMode() != true) {
            Log.wtf("WTF", "Replace method worked");
            FragmentTransaction transaction = manager.beginTransaction();
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
        return fragment;
    }

    public static FrgmntMngr getManager(){
        return frgmntMngr;
    }

    public static FrgmntMngr createManager(FragmentManager manager, MainActivity mainActivity){
        if (frgmntMngr == null) frgmntMngr = new FrgmntMngr(manager, mainActivity);
        frgmntMngr.manager = manager;
        database = new Database(frgmntMngr.getContext());
        database.open();
        return frgmntMngr;
    }

    public Fragment getElement(String name){
        return fragmentsMap.get(name);
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }


    public Fragment addToFragments(String key, Fragment fragment){
        return fragmentsMap.put(key, fragment);
    }

    public Context getContext() {
        System.out.println(mainActivity);
        return mainActivity;
    }

    public Database getDatabase() {
        return database;
    }
}
