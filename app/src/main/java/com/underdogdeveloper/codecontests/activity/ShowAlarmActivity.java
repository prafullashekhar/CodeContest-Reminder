package com.underdogdeveloper.codecontests.activity;

import android.app.AlarmManager;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.underdogdeveloper.codecontests.R;
import com.underdogdeveloper.codecontests.adapter.AlarmAdapter;
import com.underdogdeveloper.codecontests.adapter.ListAdapter;
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
        Contest contest = new Contest("name_pip", "url_pip", "strtime", 3435, "afafdafaf");
        AlarmModel alarmModel = new AlarmModel();
        alarmModel.setContest(contest);
        alarmModelList.add(alarmModel);

        adapter= new AlarmAdapter(this, alarmModelList);

        LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.setAdapter(adapter);
    }
}