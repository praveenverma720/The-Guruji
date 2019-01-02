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
import com.praveen.pilani.theguruji.models.DataModel;

import java.util.List;

/**
 * Created by Praveen on 10/08/2018.
 */

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.TrickHolder> {

    private static final String TAG = "RecyclerAdapter";

    private Context context;
    private List<DataModel> dataModels;

    public RecyclerAdapter(Context context,List<DataModel> dataModels){

        this.context = context;
        this.dataModels = dataModels;
    }


    @NonNull
    @Override
    public TrickHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating and returning our view holder
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.recyclerview_item, null);
        return new TrickHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull final TrickHolder holder, int position) {

        final DataModel dataModel = dataModels.get(position);
        holder.textTitle.setText(dataModel.getTitle());
        holder.textContent.setText(dataModel.getContent());
        holder.share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                try{
                    Intent sharingIntent = new Intent(android.content.Intent.ACTION_SEND);
                sharingIntent.setType("text/plain");
                String shareBody = dataModel.getTitle()+":-\n"+ dataModel.getContent()+"\n\n"+"Download the App For More Tricks\n\n"+"https://play.google.com/store/apps/details?id=com.praveen.pilani.movies";
                sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                    Intent chooserIntent = Intent.createChooser(sharingIntent, "Open With");
                    chooserIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(chooserIntent);

                }catch (Exception e){
                    Log.d(TAG,e.toString());
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataModels.size();
    }

    public class TrickHolder extends RecyclerView.ViewHolder {
    public TextView textTitle, textContent, textDate;
    public ImageView share;

    public TrickHolder(View itemView) {
        super(itemView);

        textTitle = itemView.findViewById(R.id.title);
        textContent = itemView.findViewById(R.id.content);
        share = itemView.findViewById(R.id.share);
    }
}

}
