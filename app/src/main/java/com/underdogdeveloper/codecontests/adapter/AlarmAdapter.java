package com.underdogdeveloper.codecontests.adapter;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.underdogdeveloper.codecontests.BroadCasts.AlarmReciever;
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
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AlarmAdapter.ReminderViewHolder holder, int position) {

        AlarmModel model = list.get(position);



    }

    @Override
    public int getItemCount() {
        return 0;
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

        public ReminderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
