package com.underdogdeveloper.codecontests.adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.browser.customtabs.CustomTabsIntent;
import androidx.recyclerview.widget.RecyclerView;

import com.underdogdeveloper.codecontests.BroadCasts.AlarmReciever;
import com.underdogdeveloper.codecontests.R;
import com.underdogdeveloper.codecontests.model.AlarmModel;
import com.underdogdeveloper.codecontests.model.Contest;

import java.util.ArrayList;
import java.util.Calendar;

public class AlarmAdapter extends RecyclerView.Adapter<AlarmAdapter.ReminderViewHolder>{
    Context context;
    ArrayList<AlarmModel> list;

    public AlarmAdapter(Context context, ArrayList<AlarmModel> list){
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AlarmAdapter.ReminderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // LayoutInflater is used to convert xml format to view
        View view = LayoutInflater.from(context).inflate(R.layout.item_alarm, parent, false);
        return new ReminderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmAdapter.ReminderViewHolder holder, int position) {

        AlarmModel model = list.get(position);

        Contest contest = model.getContest();
        holder.name.setText(contest.getName());
//        holder.name.setSelected(true);
//        holder.site.setText(contest.getSite());
//        holder.duration.setText((int) contest.getDuration());
//        String startTimeWithoutFormat=contest.getStart_time();
//        String startTimewithFormat=getStringFormat(startTimeWithoutFormat);
//        holder.start.setText(startTimewithFormat);

        // setting logo of various sites
        switch(contest.getSite()){
            case "CodeForces":
                holder.logoImage.setImageResource(R.drawable.codeforces_logo);
                break;
            case "CodeChef":
                holder.logoImage.setImageResource(R.drawable.codechef_logo);
                break;
            case "HackerRank":
                holder.logoImage.setImageResource(R.drawable.hackerrank_logo);
                break;
            case "HackerEarth":
                holder.logoImage.setImageResource(R.drawable.hackerearth_logo);
                break;
            case "LeetCode":
                holder.logoImage.setImageResource(R.drawable.leetcode_logo);
                break;
            case "TopCoder":
                holder.logoImage.setImageResource(R.drawable.topcoder_logo);
                break;
        }
        holder.contestOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomTabsIntent.Builder builder = new CustomTabsIntent.Builder();
                CustomTabsIntent customTabsIntent = builder.build();
                customTabsIntent.launchUrl(context, Uri.parse(contest.getUrl()));
            }
        });
        holder.deleteAlarm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteAlarmDatabase(model);
            }
        });
    }

    private void deleteAlarmDatabase(AlarmModel model) {
        Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
    }

    public String getStringFormat(String startTime){
        String formatString=null;
        String time=startTime.substring(11,16);
        String year=startTime.substring(0,4);
        String month=startTime.substring(5,7);
        String date=startTime.substring(8,10);
        month=numToEng(Integer.parseInt(month));
        formatString=time+" "+date+"-"+month+"-"+year;
        return formatString;
    }
    public String numToEng(int month){
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

    private void setAlarmOn(AlarmModel model){
        int year = model.getYear();
        int month = model.getMonth();
        int date = model.getDate();
        int hour = model.getHour();
        int minute = model.getMinute();

        AlarmManager alarmManager = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent intent = new Intent(context, AlarmReciever.class);

        PendingIntent pendingIntent=PendingIntent.getBroadcast(context,0,intent,0);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.YEAR, year);
        calendar.set(Calendar.MONTH, month);
        calendar.set(Calendar.DAY_OF_MONTH, date);
        calendar.set(Calendar.HOUR_OF_DAY, hour);
        calendar.set(Calendar.MINUTE, minute);
        calendar.set(Calendar.SECOND, 0);

        alarmManager.setExact(
                AlarmManager.RTC_WAKEUP,
                calendar.getTimeInMillis(),
                pendingIntent
        );
    }


    public class ReminderViewHolder extends RecyclerView.ViewHolder {
        TextView name,site,duration,start;
        ImageView logoImage,deleteAlarm;
        LinearLayout contestOpen;

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.contestNameAlarm);
            duration=itemView.findViewById(R.id.durationTimeAlarm);
            site=itemView.findViewById(R.id.siteAlarm);
            start=itemView.findViewById(R.id.startTimeAlarm);

            logoImage=itemView.findViewById(R.id.logoImageViewAlarm);
            deleteAlarm=itemView.findViewById(R.id.deleteAlarm);
            contestOpen=itemView.findViewById(R.id.contestOpenAlarm);
        }
    }
}
