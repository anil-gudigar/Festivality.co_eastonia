package com.festivality.conferenceapp.data.remote.repository;

import android.util.Log;

import com.festivality.conferenceapp.BuildConfig;
import com.festivality.conferenceapp.app.base.dao.LiveRealmResults;
import com.festivality.conferenceapp.data.local.daos.DeviceIDDao;
import com.festivality.conferenceapp.data.model.device.DeviceID;
import com.festivality.conferenceapp.data.remote.FestivalityAPIService;
import com.festivality.conferenceapp.data.source.Resource;
import com.festivality.conferenceapp.helper.RestHelper;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class DeviceIDRepo extends BaseRepo<DeviceID, DeviceIDDao> {
    private final FestivalityAPIService festivalityAPIService;

    private LiveRealmResults<DeviceID> data;

    private String targetURL;

    @Inject
    public DeviceIDRepo(FestivalityAPIService festivalityAPIService, DeviceIDDao deviceIDDao) {
        super(deviceIDDao);
        this.festivalityAPIService = festivalityAPIService;
    }

    public FestivalityAPIService getFestivalityAPIService() {
        return festivalityAPIService;
    }

    public LiveRealmResults<DeviceID> getData() {
        return data;
    }

    public void setData(LiveRealmResults<DeviceID> data) {
        this.data = data;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public Flowable<Resource<DeviceID>> getDeviceID(String deviceIDURL) {
        Log.i("Anil", " Device ID call ");
        return RestHelper.createRemoteSourceMapper(festivalityAPIService.getDeviceID(deviceIDURL, "{\"apiClientId\":\""+ BuildConfig.apiClientId+"\",\"apiToken\":\""+BuildConfig.apiToken+"\"}"), deviceid -> {
            Log.i("Anil", "Device ID:" + deviceid.toString());
        });
    }
}
