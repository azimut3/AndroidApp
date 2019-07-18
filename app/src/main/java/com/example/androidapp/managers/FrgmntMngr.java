package com.example.androidapp.managers;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.androidapp.activities.MainActivity;
import com.example.androidapp.R;
import com.example.androidapp.fragments.FragmentInput;
import com.example.androidapp.fragments.FragmentResult;

import java.util.Map;
import java.util.TreeMap;

public class FrgmntMngr {
    private static FrgmntMngr frgmntMngr;
    private FragmentManager manager;
    private Map<String, Fragment> fragmentsMap;
    MainActivity mainActivity;

    public static final String INPUT_FRAGMENT = "input";
    public static final String RESULT_FRAGMENT = "result";

    private FrgmntMngr(FragmentManager manager, MainActivity mainActivity) {
        this.manager = manager;
        this.mainActivity = mainActivity;
        fragmentsMap = new TreeMap<>();
        fragmentsMap.put(INPUT_FRAGMENT, new FragmentInput());
        fragmentsMap.put(RESULT_FRAGMENT, new FragmentResult());
    }

    public void addFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.fragment_container, fragment);
        transaction.commit();
    }

    public void removeFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.remove(fragment);
        transaction.commit();
    }

    public Fragment replaceFragment(Fragment fragment) {
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragment_container, fragment);
        transaction.commit();
        return fragment;
    }

    public static FrgmntMngr getManager(){
        return frgmntMngr;
    }

    public static FrgmntMngr createManager(FragmentManager manager, MainActivity mainActivity){
        if (frgmntMngr == null) frgmntMngr = new FrgmntMngr(manager, mainActivity);
        frgmntMngr.manager = manager;
        return frgmntMngr;
    }

    public Fragment getElement(String name){
        return fragmentsMap.get(name);
    }

    public MainActivity getMainActivity() {
        return mainActivity;
    }
}
