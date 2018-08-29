package com.festivality.conferenceapp.data.remote.repository;

import android.util.Log;

import com.festivality.conferenceapp.app.base.dao.LiveRealmResults;
import com.festivality.conferenceapp.data.local.daos.AttendeesDao;
import com.festivality.conferenceapp.data.model.Attendees.Attendees;
import com.festivality.conferenceapp.data.remote.FestivalityAPIService;
import com.festivality.conferenceapp.data.source.Resource;
import com.festivality.conferenceapp.helper.RestHelper;

import javax.inject.Inject;

import io.reactivex.Flowable;



public class AttendeesRepo extends BaseRepo<Attendees, AttendeesDao> {

    private final FestivalityAPIService festivalityAPIService;

    private LiveRealmResults<Attendees> data;

    private String targetURL;

    @Inject
    public AttendeesRepo(FestivalityAPIService festivalityAPIService, AttendeesDao attendeesDao) {
        super(attendeesDao);
        this.festivalityAPIService = festivalityAPIService;
    }

    public FestivalityAPIService getFestivalityAPIService() {
        return festivalityAPIService;
    }

    public LiveRealmResults<Attendees> getData() {
        return data;
    }

    public void setData(LiveRealmResults<Attendees> data) {
        this.data = data;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public Flowable<Resource<Attendees>> getUserList(String userlistURL) {
        Log.i("Anil"," getUserList call ");
        return RestHelper.createRemoteSourceMapper(festivalityAPIService.loadUserList(userlistURL, "{\"apiClientId\":\"testing-account-cli\",\"apiToken\":\"$2y$10$C/quaRQUsrWa30hjQJuckOXbW9kIZ.W3G1TlLMYg6lr/XDUes7SM.\"}","{\"deviceId\":\"AC866D58-E8C2-4F54-9241-71D433FE06F0\"}"), users -> {
           // Log.i("Anil","User List to Save :"+ users.getAttendees().toString());
        });
    }
}

