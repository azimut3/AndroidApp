package com.example.androidapp.managers;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidapp.R;
import com.example.androidapp.data.Consts;
import com.example.androidapp.data.CursorRecyclerViewAdapter;
import com.example.androidapp.listeners.OnForecastClickListener;

import java.util.List;

public class SimplifiedForecastAdapter  extends CursorRecyclerViewAdapter<SimplifiedForecastAdapter.ViewHolder> {

    private List<SimplifiedForecast> items;
    private OnForecastClickListener listener;
    private Context ctx;

    public SimplifiedForecastAdapter(Cursor cursor, Context ctx) {
        super(ctx, cursor);
        this.ctx = ctx;
    }

    public SimplifiedForecastAdapter(Cursor cursor, Context ctx, OnForecastClickListener listener) {
        super(ctx, cursor);
        this.ctx = ctx;
        this.listener = listener;
    }

    //TODO create vessel_view.fxml
    @Override
    public SimplifiedForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.forecast_element, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(view1 -> {
            if (listener != null) {
                listener.onItemClick(view1, viewHolder.getAdapterPosition());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(SimplifiedForecastAdapter.ViewHolder holder, Cursor cursor) {
        holder.weatherType.setText(cursor.getColumnIndex(Consts.DB_COL_WEATHER_STATE));
        holder.date.setText(cursor.getColumnIndex(Consts.DB_COL_DATE));
        holder.minT.setText(String.valueOf(cursor.getColumnIndex(Consts.DB_COL_MIN_T)));
        holder.maxT.setText(String.valueOf(cursor.getColumnIndex(Consts.DB_COL_MAX_T)));


        System.out.println("Binding view holder, for SimpleForecast");
    }

    public SimplifiedForecast getItem(int position) {
        Cursor cursor = getCursor();
        SimplifiedForecast item = new SimplifiedForecast();

        if (cursor.moveToPosition(position)) {
            item.setDate(cursor.getString(cursor.getColumnIndex(Consts.DB_COL_DATE)))
                    .setWeatherState(cursor.getString(cursor.getColumnIndex(Consts.DB_COL_WEATHER_STATE)))
                    .setMinT(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COL_MIN_T)))
                    .setMaxT(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COL_MAX_T)));
        }

        return item;
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setListener(OnForecastClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        //LinearLayout layout;
        TextView weatherType;
        TextView date;
        TextView minT;
        TextView maxT;

        public ViewHolder(View itemView) {
            super(itemView);

            Log.e("TaskRecyclerAdapter", "finding views!");

            //layout = itemView.findViewById(R.id.layout);
            weatherType = itemView.findViewById(R.id.weather_type);
            date = itemView.findViewById(R.id.date);
            minT = itemView.findViewById(R.id.t_min);
            maxT = itemView.findViewById(R.id.t_max);


        }
    }

    public List<SimplifiedForecast> getItems() {
        return items;
    }
}
