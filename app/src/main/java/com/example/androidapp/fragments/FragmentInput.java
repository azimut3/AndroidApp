package com.example.androidapp.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.example.androidapp.R;
import com.example.androidapp.api.RestClient;
import com.example.androidapp.data.ErrorReply;
import com.example.androidapp.data.SimplifiedForecat;
import com.example.androidapp.data.WeatherForecastReply;
import com.example.androidapp.managers.FrgmntMngr;
import com.example.androidapp.managers.WeatherForecastAdapter;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class FragmentInput extends Fragment {

    RecyclerView recyclerView;
    WeatherForecastAdapter adapter;
    private List<SimplifiedForecat> items = new ArrayList<>();

    public FragmentInput() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewFragment = inflater.inflate(R.layout.fragment_input, container, false);
        recyclerView = viewFragment.findViewById(R.id.my_recycler_view);
        Button getForecastBtn = viewFragment.findViewById(R.id.get_forecast_btn);

        adapter = new WeatherForecastAdapter(items, this.getContext());
        adapter.setListener((view, position) -> {
            FrgmntMngr.getManager().toRecepientFragment(adapter.getItems().get(position).getDate());
            FrgmntMngr.getManager().replaceFragment(
                    FrgmntMngr.getManager().getElement(FrgmntMngr.RESULT_FRAGMENT));
            System.out.println("Action clicked");
        });

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        getForecastBtn.setOnClickListener(view -> {
            loadForecast("Odessa");
        });

        return viewFragment;
    }

    private void makeErrorToast(String errorMessage) {
        Toast.makeText(this.getContext(), errorMessage, Toast.LENGTH_SHORT).show();
    }

    private void loadForecast(String city) {
        RestClient.getInstance().getService().getForecast(city).enqueue(new Callback<WeatherForecastReply>() {

            @Override
            public void onResponse(@NotNull Call<WeatherForecastReply> call, @NotNull Response<WeatherForecastReply> response) {

                if (!response.isSuccessful()) {
                    Converter<ResponseBody, ErrorReply> converter = RestClient.getInstance()
                            .getRetrofit().responseBodyConverter(ErrorReply.class,
                                    new Annotation[0]);
                    try {
                        ErrorReply weatherError = converter.convert(response.errorBody());
                        makeErrorToast(weatherError.getMessage() + " \n Details: " +
                                weatherError.getMessage());
                    } catch (Exception e) {
                        makeErrorToast("Unhandled error. Code: " + response.code());
                    }

                    return;
                }

                if(adapter.getItems().size()>0) adapter.getItems().clear();
                adapter.getItems().addAll(response.body().getDatesAndTemperatures());
                System.out.println("Content of items: " + adapter.getItems());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NotNull Call<WeatherForecastReply> call, @NotNull Throwable t) {
                makeErrorToast("Error occurred during request: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    /*@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View viewFragment = inflater.inflate(R.layout.fragment_input, container, false);
        recyclerView = viewFragment.findViewById(R.id.my_recycler_view);

        adapter = new BerthListAdapter(PortContent.getPortContentInstance().getListOfBerths(), this.getContext());
        adapter.setListener((view, position) -> {
            FrgmntMngr.getManager().toRecepientFragment(adapter.getItems().get(position).getVessels());
            FrgmntMngr.getManager().replaceFragment(
                    FrgmntMngr.getManager().getElement(FrgmntMngr.RESULT_FRAGMENT));
            System.out.println("Action clicked");
        });

        new Thread(() -> {HardTasks.getTaskItemHardly("SomeTask", taskItemLoadingCallback);
            }).start();
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);

        return viewFragment;
    }*/


    /*private OnTaskItemLoadingCallback taskItemLoadingCallback = new OnTaskItemLoadingCallback() {
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
    };*/
}
