package com.festivality.conferenceapp.data.local;

import com.festivality.conferenceapp.data.model.Attendees.Attendees;
import com.festivality.conferenceapp.data.model.auth.Authentication;
import com.festivality.conferenceapp.data.model.device.DeviceID;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;

import io.realm.Realm;
import io.realm.RealmConfiguration;
/**
 * Created by Anil Gudigar on 09/24/2017.
 */
public class RealmDatabase {

    protected final RealmConfiguration mRealmConfiguration;

    public RealmDatabase(RealmConfiguration realmConfiguration) {
        this.mRealmConfiguration = realmConfiguration;
    }

    public void clearAll() {
        Realm mRealm = Realm.getInstance(mRealmConfiguration);
        mRealm.beginTransaction();
        mRealm.delete(Attendees.class);
        mRealm.delete(Authentication.class);
        mRealm.delete(DeviceID.class);
        mRealm.delete(Attendee.class);
        mRealm.commitTransaction();
        mRealm.close();
    }
}
