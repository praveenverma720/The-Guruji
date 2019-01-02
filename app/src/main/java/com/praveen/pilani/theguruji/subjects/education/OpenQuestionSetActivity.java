package com.praveen.pilani.theguruji.subjects.education;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.praveen.pilani.theguruji.R;

import java.io.UnsupportedEncodingException;

public class OpenQuestionSetActivity extends AppCompatActivity {
    private static final String TAG = "OpenQuestionSetActivity";

    String link;
    TextView openFileTV;
    String questionlink;
    WebView webView;
    String filetype;
    ProgressBar progressBar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open_question_activity);

        questionlink = getIntent().getStringExtra("questionlink");
        filetype = getIntent().getStringExtra("filetype");
        webView = findViewById(R.id.webview);
        openFileTV = findViewById(R.id.openFileTV);
        //getting the progressbar
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        //making the progressbar visible
        progressBar.setVisibility(View.VISIBLE);
        webView.setVisibility(View.GONE);


        link = "http://3.16.93.165/"+questionlink;


        if (filetype.toString().equals("pdf")) {
            //making the progressbar visible


            openFileTV.setVisibility(View.GONE);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + link);

            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    progressBar.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                    // do your stuff here
                    //  progressbar.setVisibility(View.GONE);
                    Log.d(TAG, "---file is lodaed--");
                }

            });
        }else if (filetype.toString().equals("docs")){
            openFileTV.setVisibility(View.GONE);
            webView.getSettings().setJavaScriptEnabled(true);
            webView.loadUrl("http://docs.google.com/gview?embedded=true&url=" + link);

            webView.setWebViewClient(new WebViewClient() {

                public void onPageFinished(WebView view, String url) {
                    progressBar.setVisibility(View.GONE);
                    webView.setVisibility(View.VISIBLE);
                    // do your stuff here
                    //  progressbar.setVisibility(View.GONE);
                    Log.d(TAG, "---file is lodaed--");
                }

            });

        }else {
            openQuestionSet();
        }

    }

    public void openQuestionSet(){

        StringRequest stringRequest = new StringRequest(Request.Method.GET, link, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                progressBar.setVisibility(View.GONE);
                openFileTV.setVisibility(View.VISIBLE);
                webView.setVisibility(View.GONE);
                Log.d(TAG,response.toString());
               //
                 openFileTV.setText(response.toString());

//                Typeface hindiFont = Typeface.createFromAsset(OpenQuestionSetActivity.this.getAssets(), "font/hindifont.ttf");
//                openFileTV.setTypeface(hindiFont);



                try {
                    String decodeString = new String(response.toString().getBytes("ISO-8859-1"),"UTF-8");
                    Log.d(TAG,decodeString);
                    openFileTV.setText(decodeString);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        //creating a request queue
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        //adding the string request to request queue
        requestQueue.add(stringRequest);
    }


}
