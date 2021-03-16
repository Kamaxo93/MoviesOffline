package com.camacho.moviesoffline.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.camacho.moviesoffline.R;
import com.camacho.moviesoffline.data.local.entity.MovieEntity;
import com.camacho.moviesoffline.data.remote.ApiConstant;

import java.util.List;


public class MyMovieRecyclerViewAdapter extends RecyclerView.Adapter<MyMovieRecyclerViewAdapter.ViewHolder> {

    List<MovieEntity> mValues;
    Context content;

    public MyMovieRecyclerViewAdapter(List<MovieEntity> movieList, Context context) {
        mValues = movieList;
        content = context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        Glide.with(content)
                .load(ApiConstant.IMAGE_API_PREFIX + holder.mItem.getPosterPath())
        .into(holder.imgCover);
    }
    public void setData(List<MovieEntity> movies) {
        this.mValues = movies;
        notifyDataSetChanged();
    }
    @Override
    public int getItemCount() {
        return mValues != null ? mValues.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final ImageView imgCover;
        public MovieEntity mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            imgCover = view.findViewById(R.id.fragment_item__img__img_movie);
        }

    }
}