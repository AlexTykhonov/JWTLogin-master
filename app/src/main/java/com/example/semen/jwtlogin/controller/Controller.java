package com.example.semen.jwtlogin.controller;

import com.example.semen.jwtlogin.managers.HeaderInterceptor;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Controller {
    static final String BASE_URL = "https://petshop-server.herokuapp.com/";

    private static OkHttpClient.Builder sHttpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder sBuilder =
            new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass) {

//        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
//        logging.setLevel(HttpLoggingInterceptor.Level.BODY);

        sHttpClient.addInterceptor(new HeaderInterceptor());
//        sHttpClient.addInterceptor(logging);
//        sHttpClient.connectTimeout(AppConfig.MAX_CONNECT_TIMEOUT, TimeUnit.MILLISECONDS);
//        sHttpClient.readTimeout(AppConfig.MAX_READ_TIMEOUT, TimeUnit.MILLISECONDS);
//        sHttpClient.cache(new Cache(DevIntensiveApp.getContext().getCacheDir(), Integer.MAX_VALUE));
//        sHttpClient.addNetworkInterceptor(new StethoInterceptor());

        Retrofit retrofit = sBuilder
                .client(sHttpClient.build())
                .build();
        return  retrofit.create(serviceClass);
    }


//    public static Api getApi() {
//        Gson gson = new GsonBuilder()
//                .setLenient()
//                .create();
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .addConverterFactory(GsonConverterFactory.create(gson))
//                .build();
//
//        Api api = retrofit.create(Api.class);
//        return api;
//    }


}
