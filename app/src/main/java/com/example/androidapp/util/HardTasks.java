package com.example.androidapp.util;


import android.support.annotation.NonNull;

import com.example.androidapp.data.Parser;
import com.example.androidapp.data.PortContent;
import com.example.androidapp.listeners.OnTaskItemLoadingCallback;


public class HardTasks {

    public static void getTaskItemHardly(@NonNull String taskName, OnTaskItemLoadingCallback callback) {

        if (callback != null) {
            callback.onLoadingStarted();
        }
        Parser.parsePort();

        if (callback != null) {
            callback.onLoadingFinish(PortContent.getPortContentInstance().getListOfBerths());
        }

    }

}
