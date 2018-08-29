package com.festivality.conferenceapp.data.local.daos;

import android.support.annotation.Nullable;

import com.festivality.conferenceapp.app.base.dao.BaseRealmDaoImpl;
import com.festivality.conferenceapp.app.base.dao.LiveRealmResults;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.model.Attendees.Attendees;
import com.festivality.conferenceapp.data.model.auth.Authentication;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

/**
 * Created by Anil Gudigar on 09/24/2017.
 */
public class AttendeesDao extends BaseRealmDaoImpl<Attendees> {
    @Inject
    public AttendeesDao(RealmConfiguration config) {
        super(Realm.getInstance(config), Attendees.class);
    }


}
