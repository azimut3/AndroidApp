package com.example.androidapp.listeners;


import com.example.androidapp.data.Berth;

import java.util.List;
import java.util.TreeMap;

public interface OnTaskItemLoadingCallback {

    void onLoadingStarted();

    void onLoadingFinish(List<Berth> berths);

}
