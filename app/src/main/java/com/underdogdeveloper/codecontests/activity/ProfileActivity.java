package com.underdogdeveloper.codecontests.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.underdogdeveloper.codecontests.R;

import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

public class ProfileActivity extends AppCompatActivity {
    TextView handle, rating, maxrating, friendof, country, organisation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        handle = findViewById(R.id.handle);
        rating = findViewById(R.id.rating);
        maxrating = findViewById(R.id.maxrating);
        friendof = findViewById(R.id.friendsof);
        country = findViewById(R.id.country);
        organisation = findViewById(R.id.organization);

        // assigning the userId from sharedPreferences
        SharedPreferences sharedPreferences=getSharedPreferences(String.valueOf(R.string.data_bas_name),MODE_PRIVATE);
        String userHandler=sharedPreferences.getString(String.valueOf(R.string.user),null);


        getUserData(userHandler);
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
}