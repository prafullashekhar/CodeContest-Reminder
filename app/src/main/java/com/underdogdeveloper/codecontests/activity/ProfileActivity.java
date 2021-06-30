package com.underdogdeveloper.codecontests.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import com.underdogdeveloper.codecontests.R;

import com.squareup.picasso.Picasso;
import com.underdogdeveloper.codecontests.adapter.ContestHistoryAdapter;

import com.underdogdeveloper.codecontests.model.ContestHistory;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProfileActivity extends AppCompatActivity {
    TextView handle, rating, maxrating, friendof, country, organisation;
    RecyclerView recyclerView;

    ArrayList<ContestHistory> contestHistories;
    ContestHistoryAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        contestHistories = new ArrayList<>();

        handle = findViewById(R.id.handle);
        rating = findViewById(R.id.rating);
        maxrating = findViewById(R.id.maxrating);
        friendof = findViewById(R.id.friendsof);
        country = findViewById(R.id.country);
        organisation = findViewById(R.id.organization);
        recyclerView=findViewById(R.id.prevContestView);

        adapter=new ContestHistoryAdapter(this,contestHistories);


        // assigning the userId from sharedPreferences
        SharedPreferences sharedPreferences=getSharedPreferences(String.valueOf(R.string.data_bas_name),MODE_PRIVATE);
        String userHandler=sharedPreferences.getString(String.valueOf(R.string.user),null);


        getUserData(userHandler);
        getContestHistoryData(userHandler);
    }

    private void getUserData(String userId){
        String url="https://codeforces.com/api/user.info?handles="+userId;
        RequestQueue requestQueue= Volley.newRequestQueue(ProfileActivity.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONObject profileJsonObject = response.getJSONArray("result").getJSONObject(0);

                    ImageView profile_pic = findViewById(R.id.profile_pic);
                    Picasso.get()
                            .load( profileJsonObject.getString("titlePhoto"))
                            .placeholder(R.drawable.ic_user)
                            .error(R.drawable.ic_user)
                            .into(profile_pic);

                    handle.setText(profileJsonObject.getString("handle"));
                    rating.setText(profileJsonObject.getString("rating"));
                    maxrating.setText(profileJsonObject.getString("maxRating"));
                    friendof.setText(profileJsonObject.has("friendOfCount") ? profileJsonObject.getString("friendOfCount") : "0");
                    country.setText(profileJsonObject.has("country") ? profileJsonObject.getString("country") : "N/A");
                    organisation.setText(profileJsonObject.has("organization") ? profileJsonObject.getString("organization") : "N/A");

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, "check internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);
    }

    private void getContestHistoryData(String userId){
        String url="https://codeforces.com/api/user.rating?handle=" + userId;
        // initiate the request
        RequestQueue requestQueue= Volley.newRequestQueue(ProfileActivity.this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override

            // String contestName, int rank, int ratingChange, int newRating, int oldRating

            public void onResponse(JSONObject response) {
                try{
                    contestHistories.clear();

                    JSONArray contestHistoryJsonArray = response.getJSONArray("result");
                    int length = contestHistoryJsonArray.length();

                    for(int i=length-1; i>=0 && length-i<=5; i--){
                        JSONObject currentContestJsonObject = contestHistoryJsonArray.getJSONObject(i);

                        ContestHistory contestHistory = new ContestHistory(
                                currentContestJsonObject.getString("contestName").toString(),
                                currentContestJsonObject.getInt("rank"),
                                currentContestJsonObject.getInt("newRating"),
                                currentContestJsonObject.getInt("oldRating")
                        );
                        contestHistories.add(contestHistory);

                    }
                }catch (JSONException e){
                    e.printStackTrace();
                }

                recyclerView.setAdapter(adapter);
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(ProfileActivity.this, "check internet connection", Toast.LENGTH_SHORT).show();
            }
        });

        requestQueue.add(jsonObjectRequest);

    }

}
