package com.example.androidapp.fragments;


import android.content.Context;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.CursorLoader;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


import com.example.androidapp.R;
import com.example.androidapp.api.RestClient;
import com.example.androidapp.data.Consts;
import com.example.androidapp.data.Database;
import com.example.androidapp.data.entities.ErrorReply;
import com.example.androidapp.managers.ComplexForecast;
import com.example.androidapp.managers.SimplifiedForecast;
import com.example.androidapp.data.entities.WeatherForecastReply;
import com.example.androidapp.managers.FrgmntMngr;
import com.example.androidapp.managers.SimplifiedForecastAdapter;

import org.jetbrains.annotations.NotNull;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;

public class FragmentInput extends Fragment implements LoaderManager.LoaderCallbacks<Cursor>{

    private RecyclerView recyclerView;
    private SimplifiedForecastAdapter adapter;
    private List<SimplifiedForecast> items = new ArrayList<>();
    private WeatherForecastReply weatherForecastReply;

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

        getLoaderManager().initLoader(Consts.LOADER_ID, null, this);

        adapter = new SimplifiedForecastAdapter(getDatabase().getAllSimpleData(), this.getContext(), (view, position, date) -> {
            FrgmntMngr.getManager().toRecipientFragment(date);
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

                weatherForecastReply = response.body();
                updateList(weatherForecastReply.getDatesAndTemperatures(), weatherForecastReply.getComplexForecasts());
                //System.out.println("Content of items: " + adapter.getItems());
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(@NotNull Call<WeatherForecastReply> call, @NotNull Throwable t) {
                makeErrorToast("Error occurred during request: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void updateList(List<SimplifiedForecast> simpleItems, List<ComplexForecast> complexItems) {
        getDatabase().clearSimpleData();
        getDatabase().addSimpleData(simpleItems);
        getDatabase().clearComplexData();
        getDatabase().addComplexData(complexItems);
        getLoaderManager().getLoader(Consts.LOADER_ID).forceLoad();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(this.getClass().getName(), "onDestroy()");
        //getLoaderManager().destroyLoader(Consts.LOADER_ID);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(this.getContext(), getDatabase());
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
        adapter.swapCursor(data);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    /**
     * Subclass of {@link android.content.CursorLoader} which provides loader associated
     * with application database's implementation.
     */
    static class MyCursorLoader extends CursorLoader {

        Database db;

        public MyCursorLoader(Context context, Database db) {
            super(context);
            this.db = db;
        }

        @Override
        public Cursor loadInBackground() {
            return db.getAllSimpleData();
        }

    }

    private Database getDatabase() {
        return FrgmntMngr.getManager().getDatabase();
    }
}
