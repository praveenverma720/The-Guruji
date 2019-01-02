package com.praveen.pilani.theguruji;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.praveen.pilani.theguruji.Network.APIClient;
import com.praveen.pilani.theguruji.adapter.EductionSubjectRecyclerAdapter;
import com.praveen.pilani.theguruji.interfaces.APIInterface;
import com.praveen.pilani.theguruji.models.education_modal.EducationSubjectResModal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EducationTricksActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "EducationTricksActivity";

    private CardView math,reasoning,gk,mind,railway,upsc,ssc,currentgk,historygk,indiagk,politicalgk,worldgk;
    private RecyclerView subjectRecycler;
    private EductionSubjectRecyclerAdapter adapter;
    APIInterface apiInterface;
    private EducationSubjectResModal educationSubjectResModal;
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

        apiInterface = APIClient.getClient().create(APIInterface.class);

        subjectRecycler = findViewById(R.id.subjectRecycler);
        getSubject();


    }

    @Override
    public void onClick(View v) {

    }


    public void getSubject(){

        Call<EducationSubjectResModal> call = apiInterface.educationSubjectResModalCall();
        call.enqueue(new Callback<EducationSubjectResModal>() {
            @Override
            public void onResponse(Call<EducationSubjectResModal> call, Response<EducationSubjectResModal> response) {

                Log.d(TAG, response.message().toString());
                if (response.message().toString().equals("OK")) {
                    progressBar.setVisibility(View.GONE);
                    waitTV.setVisibility(View.GONE);
                    educationSubjectResModal = new EducationSubjectResModal();
                    educationSubjectResModal.setData(response.body().getData());
                    educationSubjectResModal.setStatus(response.body().getStatus());
                    Log.d(TAG, response.body().getStatus().toString());
                    adapter = new EductionSubjectRecyclerAdapter(getApplicationContext(), educationSubjectResModal);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(EducationTricksActivity.this, LinearLayoutManager.VERTICAL, false);
                    subjectRecycler.setLayoutManager(linearLayoutManager);
                    subjectRecycler.setAdapter(adapter);
                } else {
                    waitTV.setText("Something Went Wrong...");

                    waitTV.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }
            @Override
            public void onFailure(Call<EducationSubjectResModal> call, Throwable t) {

            }
        });
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        EducationTricksActivity.super.onBackPressed();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
