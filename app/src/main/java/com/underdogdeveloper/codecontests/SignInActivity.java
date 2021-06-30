package com.underdogdeveloper.codecontests;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;


import org.json.JSONException;
import org.json.JSONObject;

public class SignInActivity extends AppCompatActivity {

    ImageView imageView,next;
    EditText editText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        getSupportActionBar().hide();
        imageView=findViewById(R.id.imageView);
        editText=findViewById(R.id.etHandler);
        next=findViewById(R.id.signIn);
        Intent intent=getIntent();
        String prevUser=intent.getStringExtra(String.valueOf(R.string.prev_user));
        editText.setText(prevUser);

        // Retrieve data from shared preference
        SharedPreferences sharedPreferences=getSharedPreferences(String.valueOf(R.string.data_bas_name),MODE_PRIVATE);
        String userHandler=sharedPreferences.getString(String.valueOf(R.string.user),null);
        
        if(userHandler!=null){
            startActivity(new Intent(SignInActivity.this,MainActivity.class));
        }
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId=editText.getText().toString();
                if(userId.isEmpty()){
                    editText.setError("Please enter handler");
                }
                else {
                    checkUser(userId);
                }
            }
        });
        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    String userId=editText.getText().toString();
                    if(userId.isEmpty()){
                        editText.setError("Please enter handler");
                    }
                    else {
                        checkUser(userId);
                    }
                    handled = true;
                }
                return handled;
            }
        });
    }
    private void logintWith(String userId) {
        SharedPreferences sp = getSharedPreferences(String.valueOf(R.string.data_bas_name), MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(String.valueOf(R.string.user), userId);
        editor.apply();
        startActivity(new Intent(SignInActivity.this, MainActivity.class).putExtra(String.valueOf(R.string.current_user), userId));
    }

    ProgressDialog progressDialog;
    private void checkUser(String userId){
        progressDialog=ProgressDialog.show(SignInActivity.this,"","checking the user...wait",true);
        String url="https://codeforces.com/api/user.info?handles="+userId;
        RequestQueue requestQueue= Volley.newRequestQueue(SignInActivity.this);
        JsonObjectRequest jsonObjectRequest=new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    progressDialog.dismiss();
                    String status=response.getString("status");
                    if(status.equals("OK")){
                        logintWith(userId);
                    }else{
                        Toast.makeText(SignInActivity.this, "User not found", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressDialog.dismiss();
                Toast.makeText(SignInActivity.this, "No user found or something went wrong", Toast.LENGTH_SHORT).show();
                Log.d("Nipun","something went wrong");
            }
        });

        requestQueue.add(jsonObjectRequest);
    }
}