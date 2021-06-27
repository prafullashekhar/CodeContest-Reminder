package com.underdogdeveloper.codecontests.adapter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.underdogdeveloper.codecontests.R;
import com.underdogdeveloper.codecontests.model.Contest;

import java.util.ArrayList;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.viewHolder> {

    ArrayList<Contest> list;
    Context context;

    public ListAdapter(ArrayList<Contest> list,Context context) {
        this.list = list;
        this.context=context;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater is used to convert xml format to view
        View view = LayoutInflater.from(context).inflate(R.layout.item_contest, parent, false);
        return new viewHolder(view);
    }


    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        Contest contest=list.get(position);

        holder.name.setText(contest.getName());
        holder.site.setText(contest.getSite());
//        holder.duration.setText((int) contest.getDuration());
        holder.start.setText(contest.getStart_time());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    // viewHolder class
    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name,site, duration,start;
        ImageView alertAlarm;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            site=itemView.findViewById(R.id.site);
            duration=itemView.findViewById(R.id.duration);
            start=itemView.findViewById(R.id.start);

            alertAlarm=itemView.findViewById(R.id.alertAlarm);
        }
    }
}
