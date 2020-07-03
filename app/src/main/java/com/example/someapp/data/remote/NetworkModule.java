package com.example.someapp.data.remote;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkModule {

    private static Retrofit instance = null;

    private NetworkModule(){}

    public static Retrofit getInstance(String url){
        if(instance == null){
            instance = createRetrofit(url);
        }
        return instance;
    }

    private static OkHttpClient createOkHttpClient(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        return builder.build();
    }

    private static Retrofit createRetrofit(String url) {
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        return new Retrofit.Builder()
                .baseUrl(url)
                .client(createOkHttpClient())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }


}