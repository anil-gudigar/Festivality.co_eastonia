package com.festivality.conferenceapp.features.intro.viewmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.festivality.conferenceapp.app.base.viewmodel.BaseViewModel;

import javax.inject.Inject;

public class IntroViewModel extends BaseViewModel {

    private int currentTab = 0;

    @Inject
    public IntroViewModel() {
    }

    @Override
    protected void onFirsTimeUiCreate(@Nullable Bundle bundle) {

    }


}