package com.festivality.conferenceapp.data.remote.repository;

import android.support.annotation.CallSuper;

import com.festivality.conferenceapp.app.base.dao.BaseRealmDaoImpl;

import io.realm.Realm;
import io.realm.RealmObject;




public abstract class BaseRepo<T extends RealmObject, DAO extends BaseRealmDaoImpl<T>> {

    protected static final String TAG = "repo";

    protected final DAO dao;

    public BaseRepo(DAO dao) {
        this.dao = dao;
    }

    public DAO getDao() {
        return dao;
    }

    public Realm getRealm(){
        return dao.getRealm();
    }

    @CallSuper
    public void onDestroy() {
        dao.closeRealm();
    }
}
