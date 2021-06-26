package com.underdogdeveloper.codecontests;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.underdogdeveloper.codecontests.adapter.ListAdapter;
import com.underdogdeveloper.codecontests.model.Contest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    TextView contestName;
    ArrayList<Contest> contestList=new ArrayList<>();
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fetchData();

        // Initialising the adapter for recyclerView and setting that adapter
        recyclerView = findViewById(R.id.recyclerView);
        ListAdapter adapter=new ListAdapter(contestList);
        recyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

    }

    private void fetchData() {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = "https://codeforces.com/api/contest.list?gym=true";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        JSONArray contestJsonArray = null;
                        try {
                            contestJsonArray = response.getJSONArray("result");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                        for (int i = 0; i < contestJsonArray.length(); i++) {
                            JSONObject contestJsonObject = null;
                            try {
                                contestJsonObject = contestJsonArray.getJSONObject(i);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                            try {
                                Contest contest = new Contest(
                                        contestJsonObject.getInt("id"),
                                        contestJsonObject.getString("name"),
                                        contestJsonObject.getString("type"),
                                        contestJsonObject.getString("phase"),
                                        contestJsonObject.getInt("durationSeconds"),
                                        contestJsonObject.getInt("startTimeSeconds"),
                                        contestJsonObject.getInt("relativeTimeSeconds"),
                                        contestJsonObject.getString("websiteUrl"));
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                    }
                },
                new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {

                    }
                });

    }

}