package com.festivality.conferenceapp.data.remote.repository;

import android.util.Log;

import com.festivality.conferenceapp.BuildConfig;
import com.festivality.conferenceapp.app.base.dao.LiveRealmResults;
import com.festivality.conferenceapp.data.local.daos.AuthenticationDao;
import com.festivality.conferenceapp.data.model.auth.Authentication;
import com.festivality.conferenceapp.data.remote.FestivalityAPIService;
import com.festivality.conferenceapp.data.source.Resource;
import com.festivality.conferenceapp.helper.RestHelper;

import javax.inject.Inject;

import io.reactivex.Flowable;

public class AuthenticationRepo extends BaseRepo<Authentication, AuthenticationDao> {
    private final FestivalityAPIService festivalityAPIService;

    private LiveRealmResults<Authentication> data;

    private String targetURL;

    @Inject
    public AuthenticationRepo(FestivalityAPIService festivalityAPIService, AuthenticationDao authenticationDao) {
        super(authenticationDao);
        this.festivalityAPIService = festivalityAPIService;
    }

    public FestivalityAPIService getFestivalityAPIService() {
        return festivalityAPIService;
    }

    public LiveRealmResults<Authentication> getData() {
        return data;
    }

    public void setData(LiveRealmResults<Authentication> data) {
        this.data = data;
    }

    public String getTargetURL() {
        return targetURL;
    }

    public void setTargetURL(String targetURL) {
        this.targetURL = targetURL;
    }

    public Flowable<Resource<Authentication>> getAuth(String authURL) {
        Log.i("Anil"," Auth call ");
        return RestHelper.createRemoteSourceMapper(festivalityAPIService.authenticate(authURL,"{\"apiClientId\":\""+ BuildConfig.apiClientId+"\",\"apiToken\":\""+BuildConfig.apiToken+"\"}"), auth -> {
             Log.i("Anil","Auth:"+ auth.toString());
        });
    }
}
