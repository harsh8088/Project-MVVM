package com.example.project_mvvm.adapters;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.project_mvvm.R;
import com.example.project_mvvm.models.NicePlace;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder> {
    private List<NicePlace> nicePlaces;
    private Context context;

    public RecyclerViewAdapter(Context context, List<NicePlace> nicePlaces) {
        this.nicePlaces = nicePlaces;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View itemView = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_list, viewGroup, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.MyViewHolder viewHolder, int i) {
        NicePlace nicePlace = nicePlaces.get(i);
        viewHolder.mTextTitle.setText(nicePlace.getName());

        Glide.with(context).load(Uri.parse(nicePlace.getImageUrl())).into(viewHolder.mImageUrl);

    }

    @Override
    public int getItemCount() {
        return nicePlaces.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextTitle;
        private ImageView mImageUrl;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextTitle = itemView.findViewById(R.id.text_title);
            mImageUrl = itemView.findViewById(R.id.iv_url);
        }

    }
}
