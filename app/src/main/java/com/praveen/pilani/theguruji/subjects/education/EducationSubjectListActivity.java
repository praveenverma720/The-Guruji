package com.praveen.pilani.theguruji.subjects.education;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.praveen.pilani.theguruji.Network.APIClient;
import com.praveen.pilani.theguruji.R;
import com.praveen.pilani.theguruji.adapter.EductionSubSubjectRecyclerAdapter;
import com.praveen.pilani.theguruji.interfaces.APIInterface;
import com.praveen.pilani.theguruji.models.DataModel;
import com.praveen.pilani.theguruji.models.education_modal.EducationSubjectResModal;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class EducationSubjectListActivity extends AppCompatActivity {
    private static final String TAG = "MathTricksActivity";
    //a list to store all the Data
    List<DataModel> dataModelList;
    //the URL having the json data
    private static final String JSON_URL = "https://comedymamacom.000webhostapp.com/generalscience.txt";

    private RecyclerView subjectRecycler;
    private EductionSubSubjectRecyclerAdapter adapter;
    APIInterface apiInterface;
    private EducationSubjectResModal educationSubjectResModal;
    //the recyclerview
    RecyclerView recyclerView;
    private int sub_id;
    ProgressBar progressBar;
    TextView waitTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_tricks_activity);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        waitTV = findViewById(R.id.waitTV);
        waitTV.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        sub_id = getIntent().getIntExtra("sub_id",0);
        Log.d(TAG,String.valueOf(sub_id));

        //getting the recyclerview from xml
                recyclerView = (RecyclerView) findViewById(R.id.subjectRecycler);
//        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dataModelList = new ArrayList<>();
        apiInterface = APIClient.getClient().create(APIInterface.class);
        getSubject();

    }

    public void getSubject(){

        Call<EducationSubjectResModal> call = apiInterface.educationSubCat(sub_id);
        call.enqueue(new Callback<EducationSubjectResModal>() {
            @Override
            public void onResponse(Call<EducationSubjectResModal> call, retrofit2.Response<EducationSubjectResModal> response) {

                Log.d(TAG,response.message().toString());
                if (response.message().toString().equals("OK")){
                    waitTV.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    educationSubjectResModal = new EducationSubjectResModal();
                    educationSubjectResModal.setData(response.body().getData());
                    educationSubjectResModal.setStatus(response.body().getStatus());
                    Log.d(TAG,response.body().getStatus().toString());
                    adapter = new EductionSubSubjectRecyclerAdapter(getApplicationContext(),educationSubjectResModal);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(EducationSubjectListActivity.this, LinearLayoutManager.VERTICAL, false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                }else {
                    waitTV.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<EducationSubjectResModal> call, Throwable t) {

            }
        });
    }
}
