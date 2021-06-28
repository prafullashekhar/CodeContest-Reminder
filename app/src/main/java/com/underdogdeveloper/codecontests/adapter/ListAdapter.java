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
        holder.name.setSelected(true);
        holder.site.setText(contest.getSite());
//        holder.duration.setText((int) contest.getDuration());
        String startTimeWithoutFormat=contest.getStart_time();
        String startTimewithFormat=getStringFormat(startTimeWithoutFormat);
        holder.start.setText(startTimewithFormat);

    }

    private String getStringFormat(String startTime){
        String formatString=null;
        String time=startTime.substring(11,16);
        String year=startTime.substring(0,4);
        String month=startTime.substring(5,7);
        String date=startTime.substring(8,10);
        month=numToEng(Integer.parseInt(month));
        formatString=time+" "+date+"-"+month+"-"+year;
        return formatString;
    }
    private String numToEng(int month){
        String engMonth=null;
        switch (month){
            case 1: engMonth="Jan";
            break;
            case  2: engMonth="Feb";
            break;
            case  3: engMonth="Mar";
                break;
            case  4: engMonth="Apr";
                break;
            case  5: engMonth="May";
                break;
            case  6: engMonth="Jun";
                break;
            case  7: engMonth="Jul";
                break;
            case  8: engMonth="Aug";
                break;
            case  9: engMonth="Sep";
                break;
            case  10: engMonth="Oct";
                break;
            case  11: engMonth="Nov";
                break;
            case  12: engMonth="Dec";
                break;
        }
        return  engMonth;
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    // viewHolder class
    public class viewHolder extends RecyclerView.ViewHolder {
        TextView name,site,duration,start;
        ImageView logoImage,alertAlarm;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.contestName);
            duration=itemView.findViewById(R.id.durationTime);
            site=itemView.findViewById(R.id.site);
            start=itemView.findViewById(R.id.startTime);

            logoImage=itemView.findViewById(R.id.logoImageView);
            alertAlarm=itemView.findViewById(R.id.alertAlarm);
        }
    }
}
