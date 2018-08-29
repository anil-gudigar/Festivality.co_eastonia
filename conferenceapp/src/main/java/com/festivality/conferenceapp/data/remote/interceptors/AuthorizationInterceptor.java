package com.festivality.conferenceapp.data.remote.interceptors;

import android.support.annotation.NonNull;
import android.text.TextUtils;


import com.festivality.conferenceapp.app.Constants;
import com.festivality.conferenceapp.data.providers.ServiceFactory;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;



public class AuthorizationInterceptor implements Interceptor {

    private final ServiceFactory.UserTokenProducer tokenProducer;

    public AuthorizationInterceptor(@NonNull ServiceFactory.UserTokenProducer tokenProducer) {
        this.tokenProducer = tokenProducer;
    }

    @Override
    public Response intercept(@NonNull Chain chain) throws IOException {
        Request request = chain.request();
        Request.Builder requestBuilder = request.newBuilder();
      /*
        String header = request.header(Constants.API_CONSTANTS.HEADER_AUTH);

        String token;
        if (TextUtils.isEmpty(header) && !(token = tokenProducer.getUserToken()).isEmpty()) {
            requestBuilder.addHeader(Constants.API_CONSTANTS.HEADER_AUTH, token);
        }*/
        return chain.proceed(requestBuilder.build());
    }
}
