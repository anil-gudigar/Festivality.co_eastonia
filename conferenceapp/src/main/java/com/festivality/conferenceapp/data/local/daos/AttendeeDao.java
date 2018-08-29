package com.festivality.conferenceapp.data.local.daos;

import android.support.annotation.Nullable;

import com.festivality.conferenceapp.app.base.dao.BaseRealmDaoImpl;
import com.festivality.conferenceapp.app.base.dao.LiveRealmResults;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

public class AttendeeDao extends BaseRealmDaoImpl<Attendee>{

    @Inject
    public AttendeeDao(RealmConfiguration config) {
        super(Realm.getInstance(config), Attendee.class);
    }

    @Nullable
    @Override
    protected String getDefaultSortField() {
        return "id";
    }

    public LiveRealmResults<Attendee> getReceivedAttendeeById(String id) {
        return findAllSorted(getAttendeeQuery(id));
    }

    private RealmQuery<Attendee> getAttendeeQuery(String id) {
        return query().contains("id", id);
    }
}
