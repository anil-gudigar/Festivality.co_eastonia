package com.festivality.conferenceapp.features.intro.viewmodel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.festivality.conferenceapp.app.Constants;
import com.festivality.conferenceapp.app.base.viewmodel.BaseViewModel;
import com.festivality.conferenceapp.helper.CustomSharedPreferences;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IntroViewModel extends BaseViewModel {

    private int currentTab = 0;
    @NonNull
    private final CustomSharedPreferences mSharedPreferences;

    @Inject
    public IntroViewModel(@NonNull CustomSharedPreferences sharedPreferences) {
        mSharedPreferences = sharedPreferences;
    }

    @Override
    protected void onFirsTimeUiCreate(@Nullable Bundle bundle) {
        mSharedPreferences.setPreferences(Constants.PREF_USER_ID,"44779");
    }


}