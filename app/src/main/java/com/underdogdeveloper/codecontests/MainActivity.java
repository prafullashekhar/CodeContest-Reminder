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
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;
import com.underdogdeveloper.codecontests.activity.ProfileActivity;
import com.underdogdeveloper.codecontests.adapter.ListAdapter;
import com.underdogdeveloper.codecontests.model.Contest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicReference;

public class MainActivity extends AppCompatActivity {
    ArrayList<Contest> contestList;
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


        Intent intent=getIntent();
        final String currentUser=intent.getStringExtra(String.valueOf(R.string.current_user));

        // Initialising the adapter for recyclerView and setting that adapter
        recyclerView = findViewById(R.id.recyclerView);
        contestList = new ArrayList<>();

        // calling API and adding elements in contestList
        getContestDAta();

        adapter=new ListAdapter(contestList,this);


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


    private void getContestDAta(){
        String url="https://kontests.net/api/v1/all";
        // initiate the request
        RequestQueue requestQueue= Volley.newRequestQueue(MainActivity.this);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try{
                    contestList.clear();
                    int i=0;
//                    while (i<response.length() && response.getJSONObject(i).getString("status").equals("CODING")){
//                        i++;
//                    }
                    contestList.clear();
                    for(; i<response.length(); i++){
                        JSONObject currentContestJsonObject = response.getJSONObject(i);

                        Contest contest = new Contest(
                                currentContestJsonObject.getString("name").toString(),
                                currentContestJsonObject.getString("url").toString(),
                                currentContestJsonObject.getString("start_time").toString(),
                                currentContestJsonObject.getString("end_time").toString(),
                                currentContestJsonObject.getLong("duration"),
                                currentContestJsonObject.getString("site").toString(),
                                currentContestJsonObject.getString("status").toString()
                        );
                        contestList.add(contest);

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

                recyclerView.setAdapter(adapter);
                LinearLayoutManager layoutManager=new LinearLayoutManager(getApplicationContext());
                recyclerView.setLayoutManager(layoutManager);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(MainActivity.this, "check internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonArrayRequest);

    }

}