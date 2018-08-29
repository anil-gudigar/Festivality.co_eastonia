package com.festivality.conferenceapp.data.remote.interceptors;

import android.support.annotation.NonNull;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;


public class ContentTypeInterceptor implements Interceptor {

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        return chain.proceed(request.newBuilder()
                .addHeader("Accept", "application/json;charset=UTF-8")
                .method(request.method(), request.body())
                .build());
    }
}
