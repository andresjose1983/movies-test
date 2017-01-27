package com.example.andres.movies_test.service;

import com.example.andres.movies_test.App;
import com.example.andres.movies_test.BuildConfig;

import java.io.File;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.CacheControl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by andres on 27/01/17.
 */

public final class RestClient {

    private static IClient mIClient;

    static{
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        // set your desired log level
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        File httpCacheDirectory = new File(App.getInstance().getCacheDir(), "responses");
        int cacheSize = 10 * 1024 * 1024; // 10 MiB
        Cache cache = new Cache(httpCacheDirectory, cacheSize);

        // Cache offline
        httpClient.cache(cache)
                .addInterceptor(chain -> {
                    CacheControl.Builder cacheBuilder = new CacheControl.Builder();
                    cacheBuilder.maxAge(0, TimeUnit.SECONDS);
                    cacheBuilder.maxStale(365,TimeUnit.DAYS);
                    CacheControl cacheControl = cacheBuilder.build();

                    Request request = chain.request();
                    if(App.checkInternetConnection()){
                        request = request.newBuilder()
                                .cacheControl(cacheControl)
                                .build();
                    }
                    Response originalResponse = chain.proceed(request);
                    if (App.checkInternetConnection()) {
                        int maxAge = 60  * 60; // read from cache
                        return originalResponse.newBuilder()
                                .header("Cache-Control", "public, max-age=" + maxAge)
                                .build();
                    } else {
                        int maxStale = 60 * 60 * 24 * 28;
                        return originalResponse.newBuilder()
                                .header("Cache-Control", "public, only-if-cached, max-stale=" + maxStale)
                                .build();
                    }
                });
        httpClient.connectTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS);

        // add logging as last interceptor
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BuildConfig.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        mIClient = retrofit.create(IClient.class);
    }
}
