package com.festivality.conferenceapp.data.remote.repository;

import android.util.Log;

import com.festivality.conferenceapp.BuildConfig;
import com.festivality.conferenceapp.app.base.dao.LiveRealmResults;
import com.festivality.conferenceapp.data.local.daos.AttendeesDao;
import com.festivality.conferenceapp.data.local.daos.DeviceIDDao;
import com.festivality.conferenceapp.data.model.Attendees.Attendees;
import com.festivality.conferenceapp.data.model.device.DeviceID;
import com.festivality.conferenceapp.data.remote.FestivalityAPIService;
import com.festivality.conferenceapp.data.source.Resource;
import com.festivality.conferenceapp.helper.RestHelper;

import javax.inject.Inject;

import io.reactivex.Flowable;



public class AttendeesRepo extends BaseRepo<Attendees, AttendeesDao> {

    private final FestivalityAPIService festivalityAPIService;

    private LiveRealmResults<Attendees> data;

    private String targetURL;

    private DeviceIDDao mDeviceIDDao;

    @Inject
    public AttendeesRepo(FestivalityAPIService festivalityAPIService, AttendeesDao attendeesDao,DeviceIDDao deviceIDDao) {
        super(attendeesDao);
        this.festivalityAPIService = festivalityAPIService;
        this.mDeviceIDDao = deviceIDDao;
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
        DeviceID deviceID =mDeviceIDDao.getAll(true).getData().first();
        return RestHelper.createRemoteSourceMapper(festivalityAPIService.loadUserList(userlistURL, "{\"apiClientId\":\""+ BuildConfig.apiClientId+"\",\"apiToken\":\""+BuildConfig.apiToken+"\"}","{\"deviceId\":\""+ deviceID.getResponse().getDeviceId()+"\"}"), users -> {
           // Log.i("Anil","User List to Save :"+ users.getAttendees().toString());
        });
    }
}

