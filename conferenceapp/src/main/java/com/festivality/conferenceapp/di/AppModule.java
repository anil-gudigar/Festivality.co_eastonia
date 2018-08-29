package com.festivality.conferenceapp.di;

import android.app.Application;
import android.content.Context;

import com.festivality.conferenceapp.app.Constants;
import com.festivality.conferenceapp.app.FestivalityApp;
import com.festivality.conferenceapp.data.providers.GsonProvider;
import com.festivality.conferenceapp.helper.CustomSharedPreferences;
import com.festivality.conferenceapp.helper.injection.qualifier.ApplicationContext;
import com.festivality.conferenceapp.helper.injection.viewmodel.ViewModelModule;
import com.google.gson.Gson;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module(includes = ViewModelModule.class)
public class AppModule {

    public AppModule() {}

    @Provides
    @Singleton
    @ApplicationContext
    Context provideContext(Application application) {
        return application;
    }

    @Provides
    @Singleton
    static Gson provideGson() {
        return GsonProvider.makeGsonForRealm();
    }

    @Provides
    @Singleton
    CustomSharedPreferences provideMySharedPreferences(@ApplicationContext Context context) {
        return CustomSharedPreferences.getInstance(context, Constants.PREF_FILE_NAME);
    }

    @Provides
    @Singleton
    RefWatcher provideRefWatcher() {
        return FestivalityApp.getInstance().getRefWatcher();
    }
}

