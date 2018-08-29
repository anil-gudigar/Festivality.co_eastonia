package com.festivality.conferenceapp.data.local.daos;

import com.festivality.conferenceapp.app.base.dao.BaseRealmDaoImpl;
import com.festivality.conferenceapp.data.model.Attendees.Attendees;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
/**
 * Created by Anil Gudigar on 09/24/2017.
 */
public class AttendeesDao extends BaseRealmDaoImpl<Attendees> {
    @Inject
    public AttendeesDao(RealmConfiguration config) {
        super(Realm.getInstance(config), Attendees.class);
    }
}
