package com.underdogdeveloper.codecontests.adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
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
        ImageView logoImage,alertAlarm;

        public ReminderViewHolder(@NonNull View itemView) {
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
