package com.festivality.conferenceapp.features.intro.viewmodel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.festivality.conferenceapp.BuildConfig;
import com.festivality.conferenceapp.app.Constants;
import com.festivality.conferenceapp.app.base.viewmodel.BaseViewModel;
import com.festivality.conferenceapp.data.local.daos.AuthenticationDao;
import com.festivality.conferenceapp.data.remote.repository.AuthenticationRepo;
import com.festivality.conferenceapp.data.remote.repository.DeviceIDRepo;
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

    private AuthenticationRepo mAuthenticationRepo;

    private  DeviceIDRepo mDeviceIDRepo;

    @Inject
    public IntroViewModel(@NonNull CustomSharedPreferences sharedPreferences, AuthenticationRepo authenticationRepo, DeviceIDRepo deviceIDRepo) {
        mSharedPreferences = sharedPreferences;
        mAuthenticationRepo = authenticationRepo;
        mDeviceIDRepo = deviceIDRepo;
    }

    @Override
    protected void onFirsTimeUiCreate(@Nullable Bundle bundle) {
            mSharedPreferences.setPreferences(Constants.PREF_USER_ID,"44779");
            if (mAuthenticationRepo.getDao().getAll(true).getData().isEmpty()) {
                new Handler(Looper.myLooper()).postDelayed(() -> {
                    execute(true, mAuthenticationRepo.getAuth(BuildConfig.BASE_URL+"app/ack"), auth -> {
                        Log.i("Anil","  auth:"+auth.toString());
                        mAuthenticationRepo.getDao().addOrUpdateAsync(auth);
                    });
                }, 300);
            }

        if (mDeviceIDRepo.getDao().getAll(true).getData().isEmpty()) {
            new Handler(Looper.myLooper()).postDelayed(() -> {
                execute(true, mDeviceIDRepo.getDeviceID(BuildConfig.BASE_URL+"app/deviceId"), deviceid -> {
                    Log.i("Anil","  deviceid :"+deviceid.toString());
                    mDeviceIDRepo.getDao().addOrUpdateAsync(deviceid);
                });
            }, 300);
        }
        }
}