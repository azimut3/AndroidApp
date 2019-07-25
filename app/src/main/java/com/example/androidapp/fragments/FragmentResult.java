package com.example.androidapp.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.androidapp.R;
import com.example.androidapp.managers.ComplexForecast;
import com.example.androidapp.managers.ComplexForecastAdapter;
import com.example.androidapp.managers.FrgmntMngr;

import java.util.ArrayList;
import java.util.Collection;

public class FragmentResult extends Fragment {
    private RecyclerView recyclerView;
    private ComplexForecastAdapter adapter;
    private Context context;
    private String titlsString;
    private TextView title;

    java.util.List<ComplexForecast> forecasts = new ArrayList<>();

    public FragmentResult() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this.getActivity();

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        Log.d("FragmentResult", "onCreate()");
        View view = inflater.inflate(R.layout.fragment_result, container, false);
        recyclerView = view.findViewById(R.id.my_recycler_view);
        Button backBtn = view.findViewById(R.id.back_btn);
        backBtn.setOnClickListener((e) -> FrgmntMngr.getManager()
                .replaceFragment(FrgmntMngr.getManager().getElement(FrgmntMngr.INPUT_FRAGMENT)));
        title = view.findViewById(R.id.title);
        if (titlsString != null) title.setText(titlsString);
        adapter = new ComplexForecastAdapter(forecasts, this.getContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void addToItems(Collection<ComplexForecast> elements){
        if (forecasts.size()>0) forecasts.clear();
        forecasts.addAll(elements);
        if (adapter != null) adapter.notifyDataSetChanged();
    }
}
