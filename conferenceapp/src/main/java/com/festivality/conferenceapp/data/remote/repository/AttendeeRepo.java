package com.festivality.conferenceapp.data.remote.repository;

import android.util.Log;

import com.festivality.conferenceapp.app.base.dao.LiveRealmResults;
import com.festivality.conferenceapp.data.local.daos.AttendeeDao;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.remote.FestivalityAPIService;
import com.festivality.conferenceapp.data.source.Resource;
import com.festivality.conferenceapp.helper.RestHelper;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class AttendeeRepo extends BaseRepo<Attendee, AttendeeDao> {

    private final FestivalityAPIService festivalityAPIService;

    private LiveRealmResults<Attendee> data;

    private String targetURL;

    @Inject
    public AttendeeRepo(FestivalityAPIService festivalityAPIService, AttendeeDao attendeeDao) {
        super(attendeeDao);
        this.festivalityAPIService = festivalityAPIService;
    }

    public FestivalityAPIService getFestivalityAPIService() {
        return festivalityAPIService;
    }

    public LiveRealmResults<Attendee> getData() {
        return data;
    }

    public void setData(LiveRealmResults<Attendee> data) {
        this.data = data;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public Flowable<Resource<Attendee>> getUserDetail(String userURL) {
        Log.i("Anil"," getUser call ");
        return RestHelper.createRemoteSourceMapper(festivalityAPIService.loadUserDetail(userURL, "{\"apiClientId\":\"testing-account-cli\",\"apiToken\":\"$2y$10$C/quaRQUsrWa30hjQJuckOXbW9kIZ.W3G1TlLMYg6lr/XDUes7SM.\"}","{\"deviceId\":\"AC866D58-E8C2-4F54-9241-71D433FE06F0\"}"), user -> {
            Log.i("Anil"," User to Save :"+ user.toString());
        });
    }
}
