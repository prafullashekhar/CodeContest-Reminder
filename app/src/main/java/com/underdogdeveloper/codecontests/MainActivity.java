package com.underdogdeveloper.codecontests;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.underdogdeveloper.codecontests.activity.ProfileActivity;
import com.underdogdeveloper.codecontests.adapter.ListAdapter;
import com.underdogdeveloper.codecontests.model.Contest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contest> contestList=new ArrayList<>();
    RecyclerView recyclerView;
    ListAdapter adapter;

    @Override
    public void onBackPressed() {
        finishAffinity();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        fetchData();
        Intent intent=getIntent();
        final String currentUser=intent.getStringExtra(String.valueOf(R.string.current_user));
// Initialising the adapter for recyclerView and setting that adapter
        recyclerView = findViewById(R.id.recyclerView);
        adapter=new ListAdapter(contestList,this);
        recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id=item.getItemId();
        switch (id){
            case R.id.profile: startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                                break;
            case R.id.logOut : logoutUser();
                                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void logoutUser(){
        SharedPreferences sharedPreferences=getSharedPreferences(String.valueOf(R.string.data_bas_name),MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        String user = sharedPreferences.getString(String.valueOf(R.string.user),null);
        editor.clear();
        editor.apply();
        startActivity(new Intent(MainActivity.this,SignInActivity.class).putExtra(String.valueOf(R.string.prev_user),user));
    }

    private void fetchData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
        String url = "https://codeforces.com/api/contest.list";
        Toast.makeText(MainActivity.this, "Success", Toast.LENGTH_SHORT).show();

        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray=response.getJSONArray("result");
                    contestList.clear();
                    for(int i=0;i<jsonArray.length();i++){
                        JSONObject object=jsonArray.getJSONObject(i);
                        String name=object.getString("name");
                        Contest contest=new Contest();
                        contest.setName(name);
                        contestList.add(contest);
                    }
                    adapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } , error -> Log.d("Nipun","Something went wrong"));
        queue.add(jsonObjectRequest);
    }

}