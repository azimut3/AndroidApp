package com.example.androidapp.fragments;

import android.app.LoaderManager;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
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
import com.example.androidapp.activities.MainActivity;
import com.example.androidapp.data.Consts;
import com.example.androidapp.data.Database;
import com.example.androidapp.managers.ComplexForecast;
import com.example.androidapp.managers.ComplexForecastAdapter;
import com.example.androidapp.managers.FrgmntMngr;
import com.example.androidapp.managers.SimplifiedForecastAdapter;

import java.util.ArrayList;
import java.util.Collection;

public class FragmentResult extends Fragment  implements LoaderManager.LoaderCallbacks<Cursor> {
    private RecyclerView recyclerView;
    private ComplexForecastAdapter adapter;
    private Context context;
    private String titlsString;
    private TextView title;

    java.util.List<ComplexForecast> forecasts = new ArrayList<>();
    private Database database;


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

        database = new Database(this.getContext());
        database.open();

        getLoaderManager().initLoader(Consts.LOADER_ID, null, this);

        //adapter = new ComplexForecastAdapter(forecasts, this.getContext());

        adapter = new ComplexForecastAdapter(database.getAllSimpleData(), this.getContext(), null);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
        recyclerView.setAdapter(adapter);
        return view;
    }

    public void addToItems(Collection<ComplexForecast> elements){
        if (forecasts.size()>0) forecasts.clear();
        forecasts.addAll(elements);
        if (adapter != null) adapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(this.getClass().getName(), "onDestroy()");
        getLoaderManager().destroyLoader(Consts.LOADER_ID);
        database.close();
    }

    @Override
    public android.content.Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(this.getContext(), database);
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
            return db.getAllComplexData();
        }

    }
}
