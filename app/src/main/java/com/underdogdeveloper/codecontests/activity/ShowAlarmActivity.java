package com.underdogdeveloper.codecontests.activity;

import android.app.AlarmManager;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.underdogdeveloper.codecontests.R;
import com.underdogdeveloper.codecontests.adapter.AlarmAdapter;
import com.underdogdeveloper.codecontests.adapter.ListAdapter;
import com.underdogdeveloper.codecontests.dataBase.DbHandler;
import com.underdogdeveloper.codecontests.model.AlarmModel;
import com.underdogdeveloper.codecontests.model.Contest;

import java.util.ArrayList;

public class ShowAlarmActivity extends AppCompatActivity {
    ArrayList<AlarmModel> alarmModelList;
    RecyclerView recyclerView;
    AlarmAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_alarm);

        recyclerView = findViewById(R.id.show_alarm_recyclerView);
        alarmModelList = new ArrayList<>();
        adapter= new AlarmAdapter(this, alarmModelList);
        DbHandler handler=new DbHandler(this);
        for(int i=0;;i++){
            AlarmModel alarmModel=handler.getAlarm(i+1);
            if(alarmModel.getSno()!=0){
                Contest contest=alarmModel.getContest();
                Log.d("Alarm",contest.getName());
                Log.d("Alarm",alarmModel.getStrDate());
                alarmModelList.add(alarmModel);
                continue;
            }
            else {
                break;
            }
        }

        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);
    }
    private void getData(){

    }
}