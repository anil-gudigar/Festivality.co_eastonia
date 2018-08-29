package com.festivality.conferenceapp.helper.injection.ui;

import com.festivality.conferenceapp.features.attendee.module.AttendeeDetailModule;
import com.festivality.conferenceapp.features.attendee.view.AttendeeDetailFragment;
import com.festivality.conferenceapp.features.attendees.module.AttendeeModule;
import com.festivality.conferenceapp.features.attendees.view.fragments.AttendeesFragment;
import com.festivality.conferenceapp.features.home.module.HomeActivityModule;
import com.festivality.conferenceapp.features.home.view.HomeActivity;
import com.festivality.conferenceapp.features.intro.module.IntroActivityModule;
import com.festivality.conferenceapp.features.intro.view.IntroActivity;
import com.festivality.conferenceapp.features.profile.module.ProfileModule;
import com.festivality.conferenceapp.features.profile.view.ProfileFragment;


import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class BuildersModule {
    @ContributesAndroidInjector(modules = IntroActivityModule.class)
    abstract IntroActivity introActivity();
    @ContributesAndroidInjector(modules = HomeActivityModule.class)
    abstract HomeActivity homeActivity();
    @ContributesAndroidInjector(modules = AttendeeModule.class)
    abstract AttendeesFragment attendeesFragment();
    @ContributesAndroidInjector(modules = ProfileModule.class)
    abstract ProfileFragment profileFragment();
    @ContributesAndroidInjector(modules = AttendeeDetailModule.class)
    abstract AttendeeDetailFragment attendeeDetailFragment();
}
