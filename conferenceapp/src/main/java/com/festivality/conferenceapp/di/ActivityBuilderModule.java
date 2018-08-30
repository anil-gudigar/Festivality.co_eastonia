package com.festivality.conferenceapp.di;

import com.festivality.conferenceapp.features.home.view.activity.HomeActivity;
import com.festivality.conferenceapp.features.intro.view.activity.IntroActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuilderModule {
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract HomeActivity homeActivity();
    @ContributesAndroidInjector(modules = FragmentBuilderModule.class)
    abstract IntroActivity introActivity();
}
