package com.example.androidapp.managers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.androidapp.R;
import com.example.androidapp.data.SimplifiedForecat;
import com.example.androidapp.data.WeatherForecastReply;
import com.example.androidapp.listeners.OnTaskRecyclerItemClickListener;

import java.util.List;

public class WeatherForecastAdapter extends RecyclerView.Adapter<WeatherForecastAdapter.ViewHolder>{

    private List<SimplifiedForecat> items;
    private OnTaskRecyclerItemClickListener listener;
    private Context ctx;

    public WeatherForecastAdapter(List<SimplifiedForecat> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
    }

    //TODO create vessel_view.fxml
    @Override
    public WeatherForecastAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.berth_element, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (listener != null) {
                    listener.onItemClick(view, viewHolder.getAdapterPosition());
                }
            }
        });
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WeatherForecastAdapter.ViewHolder holder, int position) {
        holder.weatherType.setText(items.get(position).getWeatherState());
        holder.date.setText(items.get(position).getDate());
        holder.minT.setText(String.valueOf(items.get(position).getMinT()));
        holder.maxT.setText(String.valueOf(items.get(position).getMaxT()));


        System.out.println("Binding view holder, " + items.get(position));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setListener(OnTaskRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout layout;
        TextView weatherType;
        TextView date;
        TextView minT;
        TextView maxT;

        public ViewHolder(View itemView) {
            super(itemView);

            Log.e("TaskRecyclerAdapter", "finding views!");

            layout = itemView.findViewById(R.id.layout);
            weatherType = itemView.findViewById(R.id.weather_type);
            date = itemView.findViewById(R.id.date);
            minT = itemView.findViewById(R.id.t_min);
            maxT = itemView.findViewById(R.id.t_max);


        }
    }

    public List<SimplifiedForecat> getItems() {
        return items;
    }
}
