package com.festivality.conferenceapp.data.remote.repository;

import android.util.Log;

import com.festivality.conferenceapp.BuildConfig;
import com.festivality.conferenceapp.app.base.dao.LiveRealmResults;
import com.festivality.conferenceapp.data.local.daos.AttendeeDao;
import com.festivality.conferenceapp.data.local.daos.DeviceIDDao;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.model.device.DeviceID;
import com.festivality.conferenceapp.data.remote.FestivalityAPIService;
import com.festivality.conferenceapp.data.source.Resource;
import com.festivality.conferenceapp.helper.RestHelper;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class AttendeeRepo extends BaseRepo<Attendee, AttendeeDao> {

    private final FestivalityAPIService festivalityAPIService;

    private LiveRealmResults<Attendee> data;

    private String targetURL;

    private  DeviceIDDao mDeviceIDDao;

    @Inject
    public AttendeeRepo(FestivalityAPIService festivalityAPIService, AttendeeDao attendeeDao, DeviceIDDao deviceIDDao) {
        super(attendeeDao);
        this.festivalityAPIService = festivalityAPIService;
        this.mDeviceIDDao = deviceIDDao;
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
        DeviceID deviceID =mDeviceIDDao.getAll(true).getData().first();
        return RestHelper.createRemoteSourceMapper(festivalityAPIService.loadUserDetail(userURL, "{\"apiClientId\":\""+ BuildConfig.apiClientId+"\",\"apiToken\":\""+BuildConfig.apiToken+"\"}","{\"deviceId\":\""+ deviceID.getResponse().getDeviceId()+"\"}"), user -> {
            Log.i("Anil"," User to Save :"+ user.toString());
        });
    }
}
