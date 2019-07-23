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
import com.example.androidapp.data.Berth;
import com.example.androidapp.listeners.OnTaskRecyclerItemClickListener;

import java.util.ArrayList;
import java.util.List;

public class BerthListAdapter extends RecyclerView.Adapter<BerthListAdapter.ViewHolder>{

    private List<Berth> items;
    private OnTaskRecyclerItemClickListener listener;
    private Context ctx;

    public BerthListAdapter(List<Berth> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
    }

    //TODO create vessel_view.fxml
    @Override
    public BerthListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
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
    public void onBindViewHolder(BerthListAdapter.ViewHolder holder, int position) {
        holder.berthName.setText(items.get(position).getBerthName());
        holder.vesselList.setText(items.get(position).getVesselsNames());


        System.out.println("Binding view holder, " + items.get(position).getVesselsNames());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setListener(OnTaskRecyclerItemClickListener listener) {
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout berthBlock;
        TextView berthName;
        TextView vesselList;

        public ViewHolder(View itemView) {
            super(itemView);

            Log.e("TaskRecyclerAdapter", "finding views!");

            berthBlock = itemView.findViewById(R.id.berth_block);
            berthName = itemView.findViewById(R.id.berth_name);
            vesselList = itemView.findViewById(R.id.vessels_list);


        }
    }

    public List<Berth> getItems() {
        return items;
    }
}
