package com.festivality.conferenceapp.data.remote.repository;

import android.util.Log;

import com.festivality.conferenceapp.BuildConfig;
import com.festivality.conferenceapp.app.base.dao.LiveRealmResults;
import com.festivality.conferenceapp.data.local.daos.AttendeeDetailDao;
import com.festivality.conferenceapp.data.local.daos.DeviceIDDao;
import com.festivality.conferenceapp.data.model.AttendeeDetail.AttendeeDetail;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.model.Attendees.Attendees;
import com.festivality.conferenceapp.data.model.device.DeviceID;
import com.festivality.conferenceapp.data.remote.FestivalityAPIService;
import com.festivality.conferenceapp.data.source.Resource;
import com.festivality.conferenceapp.helper.RestHelper;

import javax.inject.Inject;

import io.reactivex.Flowable;


public class AttendeeDetailRepo extends BaseRepo<AttendeeDetail, AttendeeDetailDao> {

    private final FestivalityAPIService festivalityAPIService;

    private LiveRealmResults<AttendeeDetail> data;

    private String targetURL;

    private DeviceIDDao mDeviceIDDao;

    @Inject
    public AttendeeDetailRepo(FestivalityAPIService festivalityAPIService, AttendeeDetailDao attendeeDetailDao, DeviceIDDao deviceIDDao) {
        super(attendeeDetailDao);
        this.festivalityAPIService = festivalityAPIService;
        this.mDeviceIDDao = deviceIDDao;
    }

    public FestivalityAPIService getFestivalityAPIService() {
        return festivalityAPIService;
    }

    public LiveRealmResults<AttendeeDetail> getData() {
        return data;
    }

    public void setData(LiveRealmResults<AttendeeDetail> data) {
        this.data = data;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public Flowable<Resource<AttendeeDetail>> getUser(String userURL) {
        Log.i("Anil"," getUser call ");
        DeviceID deviceID =mDeviceIDDao.getAll(true).getData().first();
        return RestHelper.createRemoteSourceMapper(festivalityAPIService.loadUser(userURL, "{\"apiClientId\":\""+ BuildConfig.apiClientId+"\",\"apiToken\":\""+BuildConfig.apiToken+"\"}","{\"deviceId\":\""+ deviceID.getResponse().getDeviceId()+"\"}"), user -> {
            Log.i("Anil"," User to Save :"+ user.toString());
        });
    }
}

