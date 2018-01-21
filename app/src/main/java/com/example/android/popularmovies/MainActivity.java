package com.example.android.popularmovies;

import android.content.Context;
import android.graphics.Movie;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//
//public class MainActivity extends AppCompatActivity {
//
//    private Context context = this;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.movie_item);
//        ImageView image = (ImageView) findViewById(R.id.movie_image);
//        Picasso.with(context).load("http://image.tmdb.org/t/p/w185/zQsEi6096L7PvowV39dtdqdW16f.jpg").into(image);
//    }}

//    @Override
//    public void onClick(View v) {
//        if (v.getId() == R.id.send_request) {
////            sendRequestWithHttpURLConnection();
//            try {
//                HttpUtil.sendRequestWithOkHttp();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }
//}

public class MainActivity extends AppCompatActivity{

    private MovieAdapter adapter;
    private RecyclerView recyclerView;
    private Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<ResultBean.MovieBean> movieList = new ArrayList<>();
        adapter = new MovieAdapter(context, movieList);

        loadMovieData();

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);

    }

    private void loadMovieData(){

        new FetchMovieTask().execute();
    }

    public class FetchMovieTask extends AsyncTask<Void , Void, List<ResultBean.MovieBean>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected List<ResultBean.MovieBean> doInBackground(Void... params) {

            /* If there's no zip code, there's nothing to look up. */

            try {
                String jsonWeatherResponse = HttpUtil.sendRequestWithOkHttp();
                Log.d("ABC", "doInBackground: " + jsonWeatherResponse);

                ResultBean resultBean = new Gson().fromJson(jsonWeatherResponse, ResultBean.class);
                List<ResultBean.MovieBean> movieList = resultBean.getResults();
                Log.d("ABC", "onPostExecute: " + movieList.size());

                return movieList;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<ResultBean.MovieBean> movieList) {
            if(movieList != null)
            adapter.setMovieList(movieList);
            Log.d("abc", adapter.getMovieList().get(2).getPoster_path());
        }
    }
}
