package com.example.android.popularmovies;

import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by user on 2018/1/21.
 */
public class HttpUtil {

    public static String TAG = "ABC";

    public static String sendRequestWithOkHttp() throws IOException {

            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    // 指定访问的服务器地址是电脑本机
                    .url("http://api.themoviedb.org/3/movie/top_rated?api_key=d7bd8f9c18844fc7dfeb8bf33217fa76")
                    .build();
            Response response = client.newCall(request).execute();
            String responseData = response.body().string();
            Log.d(TAG, "run: " + responseData);
            return responseData;
    }

}
