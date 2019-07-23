package com.example.androidapp.managers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;
import com.example.androidapp.data.Berth;
import com.example.androidapp.data.Parser;

import java.util.ArrayList;

public class BerthListAdapter extends RecyclerView.Adapter<BerthListAdapter.ViewHolder>{

    private ArrayList<Berth> items;
    private Context ctx;

    public BerthListAdapter(ArrayList<Berth> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
    }

    //TODO create vessel_view.fxml
    @Override
    public BerthListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.berth_element, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);


        return viewHolder;
    }

    @Override
    public void onBindViewHolder(BerthListAdapter.ViewHolder holder, int position) {
        holder.berthName.setText(items.get(position).getBerthName());
        holder.vesselBlock.setText(items.get(position).getVesselsNames());
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    //TODO rearrange this to vessel view
    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout berthBlock;
        TextView berthName;
        TextView vesselBlock;

        public ViewHolder(View itemView) {
            super(itemView);

            Log.e("TaskRecyclerAdapter", "finding views!");

            berthBlock = itemView.findViewById(R.id.berth_block);
            berthName = itemView.findViewById(R.id.berth_name);
            vesselBlock = itemView.findViewById(R.id.vessels_block);


        }
    }
}
