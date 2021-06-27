package com.underdogdeveloper.codecontests.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.underdogdeveloper.codecontests.R;
import com.underdogdeveloper.codecontests.model.ContestHistory;

import java.util.ArrayList;

public class ContestHistoryAdapter extends RecyclerView.Adapter<ContestHistoryAdapter.ViewHolder> {
    Context context;
    ArrayList<ContestHistory> list;

    public ContestHistoryAdapter(Context context, ArrayList<ContestHistory> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.item_contest_history,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ContestHistory contestHistory=list.get(position);
        holder.contestName.setText(contestHistory.getContestName());
        holder.rank.setText(""+contestHistory.getRank());
        holder.change.setText(""+contestHistory.getRatingChange());
        holder.newRating.setText(""+contestHistory.getNewRating());
        holder.oldRating.setText(""+contestHistory.getOldRating());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView contestName,rank,change,newRating,oldRating;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            contestName=itemView.findViewById(R.id.contestName);
            rank=itemView.findViewById(R.id.rank);
            change=itemView.findViewById(R.id.change);
            newRating=itemView.findViewById(R.id.newRating);
            oldRating=itemView.findViewById(R.id.oldRating);
        }
    }
}
