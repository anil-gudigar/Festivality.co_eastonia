package com.festivality.conferenceapp.helper.injection.ui;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;


import com.festivality.conferenceapp.helper.injection.qualifier.ActivityContext;
import com.festivality.conferenceapp.helper.injection.qualifier.ActivityFragmentManager;
import com.festivality.conferenceapp.helper.injection.qualifier.ChildFragmentManager;
import com.festivality.conferenceapp.helper.ui.ChildFragmentNavigator;
import com.festivality.conferenceapp.helper.ui.FragmentNavigator;
import com.festivality.conferenceapp.helper.ui.FragmentNavigatorHelper;
import com.festivality.conferenceapp.helper.ui.NavigatorHelper;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Anil Gudigar on 09/23/2018.
 */

@Module
public abstract class BaseFragmentModule<T extends Fragment> {

    @Provides
    @ActivityContext
    public Context provideContext(T fragment) {
        return fragment.getContext();
    }

    @Provides
    @ChildFragmentManager
    public FragmentManager provideChildFragmentManager(T fragment) {
        return fragment.getChildFragmentManager();
    }

    @Provides
    @ActivityFragmentManager
    public FragmentManager provideActivityFragmentManager(FragmentActivity activity) {
        return activity.getSupportFragmentManager();
    }

    @Provides
    public FragmentNavigator provideFragmentNavigator(T fragment) {
        return new ChildFragmentNavigator(fragment);
    }

    @Provides
    public FragmentActivity provideActivity(T fragment) {
        return fragment.getActivity();
    }

    @Provides
    public LifecycleOwner provideLifeCycleOwner(T fragment) {
        return fragment;
    }

    @Provides
    public FragmentNavigatorHelper fragmentNavigatorHelper(FragmentNavigator navigator) {
        return new FragmentNavigatorHelper(navigator);
    }

    @Provides
    public NavigatorHelper navigatorHelper(FragmentNavigator navigator) {
        return new NavigatorHelper(navigator);
    }
}