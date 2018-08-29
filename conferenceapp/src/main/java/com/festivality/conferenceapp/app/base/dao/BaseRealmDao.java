package com.festivality.conferenceapp.app.base.dao;

import android.support.annotation.NonNull;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;



public interface BaseRealmDao<T extends RealmObject> {
    LiveRealmResults<T> getAll(boolean sorted);

    LiveRealmObject<T> getById(@NonNull Long var1);

    void addAll(@NonNull List<T> var1);

    void addAllAsync(@NonNull List<T> var1);

    void addOrUpdate(@NonNull T var1);

    void addOrUpdateAsync(@NonNull T var1);

    void delete(@NonNull Long var1);

    void deleteAll(RealmQuery<T> var1);

    void deleteAll();

    void deleteAllAsync();

    void closeRealm();

    Realm getRealm();
}