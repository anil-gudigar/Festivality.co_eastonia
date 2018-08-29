package com.festivality.conferenceapp.helper.injection.ui;

import android.app.Activity;
import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.festivality.conferenceapp.helper.injection.qualifier.ActivityContext;
import com.festivality.conferenceapp.helper.injection.qualifier.ActivityFragmentManager;
import com.festivality.conferenceapp.helper.ui.ActivityNavigator;
import com.festivality.conferenceapp.helper.ui.Navigator;
import com.festivality.conferenceapp.helper.ui.NavigatorHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by ankumar on 11/15/2017.
 */

@Module
public abstract class BaseActivityModule<T extends AppCompatActivity> {

    @Provides
    @ActivityContext
    public Context provideContext(T activity) { return activity; }

    @Provides
    public Activity provideActivity(T activity) { return activity; }

    @Provides
    public Navigator provideNavigator(T activity) { return new ActivityNavigator(activity); }

    @Provides
    public NavigatorHelper provideNavigatorHelper(Navigator navigator) {
        return new NavigatorHelper(navigator);
    }
    @Provides
    @ActivityFragmentManager
    public FragmentManager provideFragmentManager(T activity) { return activity.getSupportFragmentManager(); }

    @Provides
    public LifecycleOwner provideLifeCycleOwner(T activity) {
        return activity;
    }
}
