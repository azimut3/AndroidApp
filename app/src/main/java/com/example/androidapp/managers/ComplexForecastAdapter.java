package com.example.androidapp.managers;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
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

public class ComplexForecastAdapter extends CursorRecyclerViewAdapter<ComplexForecastAdapter.ViewHolder> {

    private OnForecastClickListener listener;
    private Context ctx;

    public ComplexForecastAdapter(Cursor cursor, Context ctx) {
        super(ctx, cursor);
        this.ctx = ctx;
    }

    public ComplexForecastAdapter(Cursor cursor, Context ctx, OnForecastClickListener listener) {
        super(ctx, cursor);
        this.ctx = ctx;
        this.listener = listener;
    }

    //TODO create vessel_view.fxml
    @Override
    public ComplexForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complex_forecast_element, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(view1 -> {
            if (listener != null) {
                listener.onItemClick(view1, viewHolder.getAdapterPosition(), getItem(viewHolder.getAdapterPosition()).getShortDate());
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ComplexForecastAdapter.ViewHolder holder, Cursor cursor) {
        ComplexForecast item = new ComplexForecast()
                .setHumidity(cursor.getString(cursor.getColumnIndex(Consts.DB_COMPLEX_COL_HUMIDITY)))
                .setPressure(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COMPLEX_COL_PRESSURE)))
                .setWindSpeed(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COMPLEX_COL_WIND_SPEED)))
                .setWindDegrees(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COMPLEX_COL_WIND_DEGREES)));
            item.setDate(cursor.getString(cursor.getColumnIndex(Consts.DB_COL_DATE)))
                    .setWeatherState(cursor.getString(cursor.getColumnIndex(Consts.DB_COL_WEATHER_STATE)))
                    .setMinT(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COL_MIN_T)))
                    .setMaxT(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COL_MAX_T)));

        holder.weatherType.setText(item.getWeatherState());
        holder.date.setText(item.getDate());
        holder.minT.setText(item.getMaxTempToString());
        holder.maxT.setText(item.getMaxTempToString());
        holder.humidity.setText(item.getHumidity());
        holder.pressure.setText(item.getPressureToString());
        holder.windSpeed.setText(item.getWindSpeedToString());
        holder.windDir.setText(item.getWindDegreesToString());

        System.out.println("Binding view holder, with ComplexForecast");
    }

    public ComplexForecast getItem(int position) {
        Cursor cursor = getCursor();
        ComplexForecast item = new ComplexForecast();

        if (cursor.moveToPosition(position)) {
            item.setDate(cursor.getString(cursor.getColumnIndex(Consts.DB_COL_DATE)))
                .setWeatherState(cursor.getString(cursor.getColumnIndex(Consts.DB_COL_WEATHER_STATE)))
                .setMinT(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COL_MIN_T)))
                .setMaxT(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COL_MAX_T)));
            item.setHumidity(cursor.getString(cursor.getColumnIndex(Consts.DB_COMPLEX_COL_HUMIDITY)))
                .setPressure(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COMPLEX_COL_PRESSURE)))
                .setWindSpeed(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COMPLEX_COL_WIND_SPEED)))
                .setWindDegrees(cursor.getDouble(cursor.getColumnIndex(Consts.DB_COMPLEX_COL_WIND_DEGREES)));
        }
        return item;
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
        TextView humidity;
        TextView pressure;
        TextView windSpeed;
        TextView windDir;

        public ViewHolder(View itemView) {
            super(itemView);

            Log.e("TaskRecyclerAdapter", "finding views!");

            //layout = itemView.findViewById(R.id.layout);
            weatherType = itemView.findViewById(R.id.weather_type);
            date = itemView.findViewById(R.id.date);
            minT = itemView.findViewById(R.id.t_min);
            maxT = itemView.findViewById(R.id.t_max);
            humidity = itemView.findViewById(R.id.humidity);
            pressure = itemView.findViewById(R.id.pressure);
            windSpeed = itemView.findViewById(R.id.wind_speed);
            windDir = itemView.findViewById(R.id.wind_direction);


        }
    }

    /*public List<ComplexForecast> getItems() {
        return items;
    }*/
}
