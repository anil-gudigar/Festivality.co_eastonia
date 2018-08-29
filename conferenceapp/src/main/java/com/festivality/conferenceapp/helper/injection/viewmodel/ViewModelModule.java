package com.festivality.conferenceapp.helper.injection.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;

import com.festivality.conferenceapp.features.attendee.viewmodel.AttendeeDetailViewModel;
import com.festivality.conferenceapp.features.attendees.module.AttendeeModule;
import com.festivality.conferenceapp.features.attendees.viewmodel.AttendeeViewModel;
import com.festivality.conferenceapp.features.home.viewmodel.HomeViewModel;
import com.festivality.conferenceapp.features.intro.viewmodel.IntroViewModel;
import com.festivality.conferenceapp.features.profile.viewmodel.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module
public abstract class ViewModelModule {

    @Binds
    abstract ViewModelProvider.Factory bindViewModelFactory(FestivalityViewModelFactory factory);

    @Binds
    @IntoMap
    @ViewModelKey(IntroViewModel.class)
    abstract ViewModel bindLoginViewModel(IntroViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel.class)
    abstract ViewModel bindHomeViewModel(HomeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AttendeeViewModel.class)
    abstract ViewModel attendeeViewModel(AttendeeViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    abstract ViewModel profiViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(AttendeeDetailViewModel.class)
    abstract ViewModel attendeeDetailViewModel(AttendeeDetailViewModel viewModel);

}

