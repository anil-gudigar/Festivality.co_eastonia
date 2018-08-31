package com.festivality.conferenceapp.data.local.daos;

import android.support.annotation.Nullable;

import com.festivality.conferenceapp.app.base.dao.BaseRealmDaoImpl;
import com.festivality.conferenceapp.app.base.dao.LiveRealmResults;
import com.festivality.conferenceapp.data.model.auth.Authentication;

import javax.inject.Inject;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import io.realm.RealmQuery;

/**
 * Created by Anil Gudigar on 09/23/2018.
 */
public class AuthenticationDao extends BaseRealmDaoImpl<Authentication> {
    @Inject
    public AuthenticationDao(RealmConfiguration config) {
        super(Realm.getInstance(config), Authentication.class);
    }


    public LiveRealmResults<Authentication> getReceivedAuthenticationById(String id) {
        return findAllSorted(getAuthenticationQuery(id));
    }

    private RealmQuery<Authentication> getAuthenticationQuery(String id) {
        return query().contains("mId", id);
    }
}
