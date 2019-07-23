package com.example.androidapp.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.androidapp.R;
import com.example.androidapp.data.Berth;
import com.example.androidapp.data.Human;
import com.example.androidapp.data.Parser;
import com.example.androidapp.data.PortContent;
import com.example.androidapp.managers.BerthListAdapter;
import com.example.androidapp.managers.FrgmntMngr;

import java.util.ArrayList;

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

        Parser.parsePort();
        adapter = new BerthListAdapter(PortContent.getPortContentInstance().getListOfBerths(), this.getContext());

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        return view;
    }


}
