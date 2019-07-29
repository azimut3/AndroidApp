package com.example.androidapp.managers;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.androidapp.R;
import com.example.androidapp.listeners.OnTaskRecyclerItemClickListener;

import java.util.List;

public class SimplifiedForecastAdapter extends RecyclerView.Adapter<SimplifiedForecastAdapter.ViewHolder>{

    private List<SimplifiedForecast> items;
    private OnTaskRecyclerItemClickListener listener;
    private Context ctx;

    public SimplifiedForecastAdapter(List<SimplifiedForecast> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
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
    public void onBindViewHolder(SimplifiedForecastAdapter.ViewHolder holder, int position) {
        holder.date.setText(items.get(position).getShortDate());
        holder.weatherType.setText(items.get(position).getWeatherState());
        holder.minT.setText(String.valueOf(items.get(position).getMinTempToString()));
        holder.maxT.setText(String.valueOf(items.get(position).getMaxTempToString()));


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
