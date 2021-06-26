package com.underdogdeveloper.codecontests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.underdogdeveloper.codecontests.adapter.ListAdapter;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView contestName;
    ArrayList<String> list=new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        // Initialising the adapter for recyclerView and setting that adapter
        recyclerView = findViewById(R.id.recyclerView);
        ListAdapter adapter=new ListAdapter(list);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void initView(){
        for(int i=0; i<100; i++){
            String s = "Item " + i;
            list.add(s);
        }
    }

}