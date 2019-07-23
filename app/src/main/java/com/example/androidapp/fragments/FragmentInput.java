package com.example.androidapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.example.androidapp.R;
import com.example.androidapp.data.Berth;
import com.example.androidapp.data.Human;
import com.example.androidapp.data.Parser;
import com.example.androidapp.data.PortContent;
import com.example.androidapp.listeners.OnTaskItemLoadingCallback;
import com.example.androidapp.managers.BerthListAdapter;
import com.example.androidapp.managers.FrgmntMngr;
import com.example.androidapp.util.HardTasks;

import java.util.ArrayList;
import java.util.List;

public class FragmentInput extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Berth> items;
    BerthListAdapter adapter;

    FrgmntMngr frgmntMngr;

    public FragmentInput() {
        this.frgmntMngr = FrgmntMngr.getManager();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_input, container, false);
        recyclerView = view.findViewById(R.id.my_recycler_view);

        adapter = new BerthListAdapter(PortContent.getPortContentInstance().getListOfBerths(), this.getContext());

        new Thread(() -> {HardTasks.getTaskItemHardly("SomeTask", taskItemLoadingCallback);
            }).start();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }

    private OnTaskItemLoadingCallback taskItemLoadingCallback = new OnTaskItemLoadingCallback() {
        @Override
        public void onLoadingStarted() {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                }
            });
        }

        @Override
        public void onLoadingFinish(List<Berth> berths) {
            getActivity().runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //adapter = new BerthListAdapter(berths, getContext());
                    System.out.println("Adapter loaded");
                    adapter.notifyDataSetChanged();
                }
            });

        }
    };
}
