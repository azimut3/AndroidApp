package com.example.androidapp.managers;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.androidapp.R;

import java.util.ArrayList;

public class VesselListAdapter extends RecyclerView.Adapter<VesselListAdapter.ViewHolder>{

    private ArrayList<Object> items;
    private Context ctx;


    //TODO create vessel_view.fxml
    @Override
    public VesselListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        /*View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vessel_view, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);*/


        //return viewHolder;
        return null;
    }

    @Override
    public void onBindViewHolder(VesselListAdapter.ViewHolder holder, int position) {
        /*holder.isCompleted.setChecked(items.get(position).isCompleted());
        holder.taskTitle.setText(items.get(position).getTaskName());
        holder.taskDate.setText(items.get(position).getTaskDate());
        holder.taskTime.setText(items.get(position).getTaskTime());*/
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    //TODO rearrange this to vessel view
    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView berth;
        TextView vesselName;
        TextView date;
        TextView agent;
        TextView flag;
        TextView code;

        public ViewHolder(View itemView) {
            super(itemView);

            Log.e("TaskRecyclerAdapter", "finding views!");

           /* berth = itemView.findViewById(R.id.item_task_check);
            vesselName = itemView.findViewById(R.id.item_task_check);
            date = itemView.findViewById(R.id.item_task_check);
            agent = itemView.findViewById(R.id.item_task_check);
            flag = itemView.findViewById(R.id.item_task_check);
            code = itemView.findViewById(R.id.item_task_check);*/

        }
    }
}
