package com.example.semen.jwtlogin.managers;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class HeaderInterceptor implements Interceptor {

    @Override
    public Response intercept(Chain chain) throws IOException {
        PreferencesManager pm = DataManager.getInstance().getPreferencesManager();

        Request original = chain.request();
        System.out.println(original.url());

        Request.Builder requestBuilder;
        if(original.url().equals("https://petshop-server.herokuapp.com/auth")){
            requestBuilder = original.newBuilder();
        }else {
            requestBuilder = original.newBuilder()
                    .header("Token", pm.getAuthToken());
        }
        System.out.println("Interceptor");

        Request request = requestBuilder.build();
        return chain.proceed(request);
    }
}