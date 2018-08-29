package com.festivality.conferenceapp.data.local.daos;

import android.support.annotation.Nullable;

import com.festivality.conferenceapp.app.base.dao.BaseRealmDaoImpl;
import com.festivality.conferenceapp.app.base.dao.LiveRealmResults;
import com.festivality.conferenceapp.data.model.device.DeviceID;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

/**
 * Created by Anil Gudigar on 09/24/2017.
 */
public class DeviceIDDao extends BaseRealmDaoImpl<DeviceID> {
    @Inject
    public DeviceIDDao(RealmConfiguration config) {
        super(Realm.getInstance(config), DeviceID.class);
    }

    @Nullable
    @Override
    protected String getDefaultSortField() {
        return "mTitle";
    }

    public LiveRealmResults<DeviceID> getReceivedDocumentsById(String id) {
        return findAllSorted(getDocumentsQuery(id));
    }

    private RealmQuery<DeviceID> getDocumentsQuery(String id) {
        return query().contains("mId", id);
    }
}
