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
 * Created by Anil Gudigar on 09/24/2017.
 */
public class AuthenticationDao extends BaseRealmDaoImpl<Authentication> {
    @Inject
    public AuthenticationDao(RealmConfiguration config) {
        super(Realm.getInstance(config), Authentication.class);
    }

    @Nullable
    @Override
    protected String getDefaultSortField() {
        return "mTitle";
    }

    public LiveRealmResults<Authentication> getReceivedDocumentsById(String id) {
        return findAllSorted(getDocumentsQuery(id));
    }

    private RealmQuery<Authentication> getDocumentsQuery(String id) {
        return query().contains("mId", id);
    }
}
