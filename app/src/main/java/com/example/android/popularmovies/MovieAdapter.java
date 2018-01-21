package com.example.android.popularmovies;

import android.app.Activity;
import android.content.Context;
import android.graphics.Movie;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.net.ConnectException;
import java.util.List;

/**
 * Created by user on 2018/1/15.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieAdapterViewHolder> {

    private List<ResultBean.MovieBean> mMovieList;
    private Context context ;

    public List<ResultBean.MovieBean> getMovieList() {
        return mMovieList;
    }

    public void setMovieList(List<ResultBean.MovieBean> mMovieList) {
        this.mMovieList = mMovieList;
    }

    public Context getContext() {
        return context;
    }

    public void setContext(Context context) {
        this.context = context;
    }

    static class MovieAdapterViewHolder extends RecyclerView.ViewHolder{

        ImageView movieImage;

        public MovieAdapterViewHolder(View view) {
            super(view);
            movieImage = (ImageView) view.findViewById(R.id.movie_image);
        }
    }


    public MovieAdapter(Context context, List<ResultBean.MovieBean> MovieBeanList) {
        this.context = context;
        mMovieList = MovieBeanList;
    }

    @Override
    public MovieAdapterViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_item, parent, false);
        MovieAdapterViewHolder holder = new MovieAdapterViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MovieAdapterViewHolder holder, int position) {
        ResultBean.MovieBean movie = mMovieList.get(position);
        String imageURL = "http://image.tmdb.org/t/p/w185"+ movie.getPoster_path();
        Log.d("abc", "onBindViewHolder: " + imageURL);
        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/zQsEi6096L7PvowV39dtdqdW16f.jpg").into(holder.movieImage);
    }

    @Override
    public int getItemCount() {
        return mMovieList.size();
    }

}
