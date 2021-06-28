package com.underdogdeveloper.codecontests.activity;

import android.app.AlarmManager;
import android.os.Bundle;

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

        // add data to alarmModelList
        // Getting all the alarms saved in database using sqliteDatabase
        AlarmModel model = new AlarmModel();
        DbHandler handler = new DbHandler(this);
        alarmModelList.clear();
        int i=0;
        while (true){
            model = handler.getAlarm(i+1);
            if(model.getSno()==0)
                break;
            else{
                alarmModelList.add(model);
                i++;
            }
        }
        handler.close();


        adapter= new AlarmAdapter(this, alarmModelList);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}