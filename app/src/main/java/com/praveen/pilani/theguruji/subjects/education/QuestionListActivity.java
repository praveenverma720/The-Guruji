package com.praveen.pilani.theguruji.subjects.education;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.praveen.pilani.theguruji.Network.APIClient;
import com.praveen.pilani.theguruji.R;
import com.praveen.pilani.theguruji.adapter.QuestionSetRecyclerAdapter;
import com.praveen.pilani.theguruji.interfaces.APIInterface;
import com.praveen.pilani.theguruji.models.education_modal.QuestionSetResModal;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class QuestionListActivity extends AppCompatActivity {
    private static final String TAG = "QuestionListActivity";

    private QuestionSetRecyclerAdapter adapter;
    APIInterface apiInterface;
    private QuestionSetResModal questionSetResModal;
    RecyclerView recyclerView;
    private int sub_id;
    ProgressBar progressBar;
    TextView waitTV;
    String come_from = "";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_tricks_activity);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        waitTV = findViewById(R.id.waitTV);
        waitTV.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.VISIBLE);

        sub_id = getIntent().getIntExtra("sub_id",0);
        come_from = getIntent().getStringExtra("comefrom");
        Log.d(TAG,String.valueOf(sub_id));

        //getting the recyclerview from xml
        recyclerView = (RecyclerView) findViewById(R.id.subjectRecycler);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        apiInterface = APIClient.getClient().create(APIInterface.class);
        if (come_from.equals("subjectList")){
            getQuestionSet();
        }else if (come_from.equals("subject")){
            getQuestionSetFrom();
        }

    }

    public void getQuestionSet(){

        Call<QuestionSetResModal> call = apiInterface.questionSet(sub_id);
        call.enqueue(new Callback<QuestionSetResModal>() {
            @Override
            public void onResponse(Call<QuestionSetResModal> call, Response<QuestionSetResModal> response) {
                Log.d(TAG,response.message().toString());
                if (response.message().toString().equals("OK")){
                    waitTV.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    questionSetResModal = new QuestionSetResModal();
                    questionSetResModal.setData(response.body().getData());
                    adapter = new QuestionSetRecyclerAdapter(getApplicationContext(),questionSetResModal);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QuestionListActivity.this,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                }else {
                    waitTV.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onFailure(Call<QuestionSetResModal> call, Throwable t) {

            }
        });
    }

    public void getQuestionSetFrom(){

        Call<QuestionSetResModal> call = apiInterface.questionSetFrom(sub_id);
        call.enqueue(new Callback<QuestionSetResModal>() {
            @Override
            public void onResponse(Call<QuestionSetResModal> call, Response<QuestionSetResModal> response) {
                if (response.message().toString().equals("OK")){
                    waitTV.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    questionSetResModal = new QuestionSetResModal();
                    questionSetResModal.setData(response.body().getData());
                    adapter = new QuestionSetRecyclerAdapter(getApplicationContext(),questionSetResModal);
                    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(QuestionListActivity.this,LinearLayoutManager.VERTICAL,false);
                    recyclerView.setLayoutManager(linearLayoutManager);
                    recyclerView.setAdapter(adapter);
                }else {
                    waitTV.setVisibility(View.VISIBLE);
                    progressBar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<QuestionSetResModal> call, Throwable t) {

            }
        });
    }
}
