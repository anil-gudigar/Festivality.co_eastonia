package com.festivality.conferenceapp.app;

import android.app.Activity;
import android.app.Application;
import android.content.Context;

import com.festivality.conferenceapp.di.AppComponent;
import com.festivality.conferenceapp.helper.injection.AppInjector;
import com.google.gson.Gson;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.HasActivityInjector;

/**
 * Created by Anil Gudigar on 09/23/2017.
 */

public class FestivalityApp extends Application implements HasActivityInjector {
    protected static FestivalityApp sInstance;

    protected static AppComponent sAppComponent;

    @Inject
    Gson gson;

    @Inject
    DispatchingAndroidInjector<Activity> dispatchingAndroidInjector;


    RefWatcher refWatcher;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;
        initializeComponent();
        refWatcher = LeakCanary.install(this);
    }

    public Gson getGson() {
        return gson;
    }

    public RefWatcher getRefWatcher() {
        return refWatcher;
    }

    private void initializeComponent() {
        sAppComponent = AppInjector.init(this);
    }

    public static AppComponent getAppComponent() {
        return sAppComponent;
    }

    public static FestivalityApp getInstance() {
        return sInstance;
    }
    @Override
    public AndroidInjector<Activity> activityInjector() {
        return dispatchingAndroidInjector;
    }
}
