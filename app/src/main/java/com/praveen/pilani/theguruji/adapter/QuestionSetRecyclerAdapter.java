package com.praveen.pilani.theguruji.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.praveen.pilani.theguruji.R;
import com.praveen.pilani.theguruji.models.education_modal.QuestionSetResModal;
import com.praveen.pilani.theguruji.subjects.education.OpenQuestionSetActivity;

/**
 * Created by Praveen on 10/08/2018.
 */

public class QuestionSetRecyclerAdapter extends RecyclerView.Adapter<QuestionSetRecyclerAdapter.TrickHolder> {

    private static final String TAG = "EductionSubjectRecyclerAdapter";

    private Context context;
    private QuestionSetResModal dataModels;

    public  QuestionSetRecyclerAdapter(Context context, QuestionSetResModal dataModels){

        this.context = context;
        this.dataModels = dataModels;
    }


    @NonNull
    @Override
    public TrickHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.subjectlist_item,parent, false);
        return new TrickHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final TrickHolder holder, final int position) {

        holder.subjectNameTV.setText(dataModels.getData().get(position).getTitle());

        Log.i("--File Type--",dataModels.getData().get(position).getType());
        holder.subjectNameTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, OpenQuestionSetActivity.class);
                intent.putExtra("questionlink",dataModels.getData().get(position).getLink());
                intent.putExtra("filetype",dataModels.getData().get(position).getType());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_MULTIPLE_TASK);
                context.getApplicationContext().startActivity(intent);

            }
        });


//        holder.share.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                try{
//                    Intent sharingIntent = new Intent(Intent.ACTION_SEND);
//                sharingIntent.setType("text/plain");
//                String shareBody = dataModel.getTitle()+":-\n"+ dataModel.getContent()+"\n\n"+"Download the App For More Tricks\n\n"+"https://play.google.com/store/apps/details?id=com.praveen.pilani.movies";
//                sharingIntent.putExtra(Intent.EXTRA_TEXT, shareBody);
//                    Intent chooserIntent = Intent.createChooser(sharingIntent, "Open With");
//                    chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                    context.startActivity(chooserIntent);
//
//                }catch (Exception e){
//                    Log.d(TAG,e.toString());
//                }
//            }
//        });

    }

    @Override
    public int getItemCount() {
        return dataModels.getData().size();
    }

    public class TrickHolder extends RecyclerView.ViewHolder {
    public TextView subjectNameTV;
    public ImageView share;

    public TrickHolder(View itemView) {
        super(itemView);

        subjectNameTV = itemView.findViewById(R.id.subjectNameTV);
    }
}

}
