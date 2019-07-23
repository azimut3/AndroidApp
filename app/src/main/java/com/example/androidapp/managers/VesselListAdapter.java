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
import com.example.androidapp.data.Vessel;

import java.util.List;

public class VesselListAdapter extends RecyclerView.Adapter<VesselListAdapter.ViewHolder>{

    private List<Vessel> items;
    private Context ctx;

    public VesselListAdapter(List<Vessel> items, Context ctx) {
        this.items = items;
        this.ctx = ctx;
    }

    //TODO create vessel_view.fxml
    @Override
    public VesselListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vessel_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(VesselListAdapter.ViewHolder holder, int position) {
        holder.vesselName.setText("Name: " + items.get(position).getVesselName());
        holder.date.setText("Moored: " + items.get(position).getDate());
        String agent = items.get(position).getAgent();
        holder.agent.setText(agent.isEmpty()? "Agent: -" : "Agent: " + agent);
        String flag = items.get(position).getFlag();
        holder.flag.setText(agent.isEmpty()? "Flag: -" : "Flag: " + flag);
        String code = items.get(position).getCode();
        holder.code.setText(agent.isEmpty()? "Code: -" : "Code: " + code);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    //TODO rearrange this to vessel view
    public static class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout vesselList;
        TextView vesselName;
        TextView date;
        TextView agent;
        TextView flag;
        TextView code;

        public ViewHolder(View itemView) {
            super(itemView);

            Log.e("TaskRecyclerAdapter", "finding views!");

            vesselList = itemView.findViewById(R.id.vessels_list);
            vesselName = itemView.findViewById(R.id.vessel_name);
            date = itemView.findViewById(R.id.date);
            agent = itemView.findViewById(R.id.agent);
            flag = itemView.findViewById(R.id.flag);
            code = itemView.findViewById(R.id.imo);


        }
    }
}
