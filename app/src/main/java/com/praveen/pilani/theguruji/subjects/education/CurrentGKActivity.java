package com.praveen.pilani.theguruji.subjects.education;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.praveen.pilani.theguruji.R;
import com.praveen.pilani.theguruji.adapter.RecyclerAdapter;
import com.praveen.pilani.theguruji.models.DataModel;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class CurrentGKActivity extends AppCompatActivity {
    private static final String TAG = "MathTricksActivity";
    //a list to store all the Data
    List<DataModel> dataModelList;
    //the URL having the json data
    private static final String JSON_URL = "https://comedymamacom.000webhostapp.com/generalscience.txt";

    //the recyclerview
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_activity);

        //getting the recyclerview from xml
                recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataModelList = new ArrayList<>();
        getData();
    }

    public void getData(){

        //getting the progressbar
        final ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, JSON_URL,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        Log.d(TAG,response.toString());
                        //hiding the progressbar after completion
                        progressBar.setVisibility(View.INVISIBLE);

                        //getting the whole json object from the response
                        JSONObject obj = null;
                        try {
                            obj = new JSONObject(response);
                            //so here we are getting that json array
                            JSONArray heroArray = obj.getJSONArray("generalscience");
                            //now looping through all the elements of the json array
                            for (int i = 0; i < heroArray.length(); i++) {
                                //getting the json object of the particular index inside the array
                                JSONObject heroObject = heroArray.getJSONObject(i);
                                DataModel dataModel = new DataModel(heroObject.getString("title"),heroObject.getString("content"));
                                dataModelList.add(dataModel);

                            }

                            RecyclerAdapter adapter = new RecyclerAdapter(getApplicationContext(),dataModelList);
                            recyclerView.setAdapter(adapter);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                //displaying the error in toast if occurrs
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);

    }
}
