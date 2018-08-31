package com.festivality.conferenceapp.data.local.daos;

import com.festivality.conferenceapp.app.base.dao.BaseRealmDaoImpl;
import com.festivality.conferenceapp.data.model.AttendeeDetail.AttendeeDetail;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.model.Attendees.Attendees;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
/**
 * Created by Anil Gudigar on 09/23/2018.
 */
public class AttendeeDetailDao extends BaseRealmDaoImpl<AttendeeDetail> {
    @Inject
    public AttendeeDetailDao(RealmConfiguration config) {
        super(Realm.getInstance(config), AttendeeDetail.class);
    }
}
