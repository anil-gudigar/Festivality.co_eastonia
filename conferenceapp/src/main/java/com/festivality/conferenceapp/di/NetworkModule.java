package com.festivality.conferenceapp.di;

import android.content.Context;

import com.festivality.conferenceapp.data.providers.ServiceFactory;
import com.festivality.conferenceapp.data.remote.FestivalityAPIService;
import com.festivality.conferenceapp.helper.injection.qualifier.ApplicationContext;
import com.google.gson.Gson;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;


@Module
public class NetworkModule {

    public NetworkModule() {}

    @Provides
    @Singleton
    static OkHttpClient provideOkHttpClientNoAuth(@ApplicationContext Context context) {
        return ServiceFactory.makeOkHttpClientBuilder(context).build();
    }

    @Provides
    @Singleton
    static FestivalityAPIService provideFestivalityAPIService(Gson gson, OkHttpClient okHttpClient) {
        return ServiceFactory.makeService(FestivalityAPIService.class, gson, okHttpClient);
    }
}
