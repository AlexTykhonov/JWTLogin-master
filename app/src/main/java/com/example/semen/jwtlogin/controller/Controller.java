package com.example.semen.jwtlogin.controller;

import com.example.semen.jwtlogin.managers.HeaderInterceptor;

import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    static final String BASE_URL = "https://petshop-server.herokuapp.com/";

    private static OkHttpClient.Builder sHttpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder sBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {


        sHttpClient.addInterceptor(new HeaderInterceptor());


        Retrofit retrofit = sBuilder
                .client(sHttpClient.build())
                .build();
        return  retrofit.create(serviceClass);
    }



}
