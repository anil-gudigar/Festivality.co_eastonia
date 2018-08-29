package com.festivality.conferenceapp.features.home.viewmodel;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;

import com.festivality.conferenceapp.BuildConfig;
import com.festivality.conferenceapp.app.base.viewmodel.BaseViewModel;
import com.festivality.conferenceapp.data.model.Attendees.Attendees;
import com.festivality.conferenceapp.data.remote.repository.AttendeesRepo;

import javax.inject.Inject;

import io.realm.RealmResults;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class HomeViewModel extends BaseViewModel {
    private int currentTab = 0;
    private AttendeesRepo mAttendeesRepo;
    private final String USER_LIST_URL= BuildConfig.BASE_URL+"user-list";


    @Inject
    public HomeViewModel(AttendeesRepo attendeesRepo) {
        mAttendeesRepo = attendeesRepo;
    }

    @Override
    protected void onFirsTimeUiCreate(@Nullable Bundle bundle) {
        Log.i("Home","onFirsTimeUiCreate");
        RealmResults<Attendees> attendeesRealmResults = mAttendeesRepo.getDao().getAll(true).getData();
        if (null != attendeesRealmResults && !attendeesRealmResults.isEmpty()) {
            Log.i("Home","Attendees :"+ attendeesRealmResults.size());
        }
    }

}