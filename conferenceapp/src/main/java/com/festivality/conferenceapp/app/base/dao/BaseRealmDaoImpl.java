package com.festivality.conferenceapp.app.base.dao;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmQuery;
import io.realm.RealmResults;
import io.realm.Sort;



public class BaseRealmDaoImpl<E extends RealmObject> implements BaseRealmDao<E> {

    private final Realm mRealm;
    private final Class<E> mClass;

    @Nullable
    private final String mDefaultSortField;

    @NonNull
    private final String mPrimaryField;

    private Sort mDefaultSort = Sort.ASCENDING;

    public BaseRealmDaoImpl(Realm realm, Class<E> eClass) {
        this.mRealm = realm;
        this.mClass = eClass;
        this.mPrimaryField = getPrimaryField();
        this.mDefaultSortField = getDefaultSortField();
    }

    /**
     * @return primary key of realm object
     */
    @NonNull
    protected String getPrimaryField() {
        return "id";
    }

    /**
     * @return default field that all queries returning {@link RealmResults} will be sorted by
     * If null, results won't be sorted
     */
    @Nullable
    protected String getDefaultSortField() {
        return null;
    }

    /**
     * @return default realm instance {@link Realm}
     */
    public Realm getRealm() {
        return mRealm;
    }

    /**
     * @return basic realm query on object's class
     */
    protected RealmQuery<E> query() {
        return mRealm.where(mClass);
    }

    /**
     * @param id given object id
     * @return basic query on this class with primary key equal to given id
     */
    protected RealmQuery<E> queryById(Long id) {
        return query().equalTo(mPrimaryField, id);
    }

    /**
     * Find all item be given query, sorted by default field
     * if default field to be sort is NULL, output items won't be sort
     * @param query realm query
     * @return live realm results
     */
    protected LiveRealmResults<E> findAllSorted(RealmQuery<E> query) {
        if (mDefaultSortField == null) {
            return asLiveData(query.findAll());
        }
        return asLiveData(query.findAllSorted(mDefaultSortField, mDefaultSort));
    }

    @Override
    public LiveRealmResults<E> getAll(boolean sorted) {
        if (sorted){
            return findAllSorted(query());
        }else{
            return asLiveData(query().findAll());
        }
    }

    @Override
    public LiveRealmObject<E> getById(@NonNull Long id) {
        return asLiveData(queryById(id).findFirst());
    }

    @Override
    public void addAll(@NonNull List<E> data) {
        mRealm.beginTransaction();
        mRealm.insertOrUpdate(data);
        mRealm.commitTransaction();
    }

    @Override
    public void addAllAsync(@NonNull List<E> data) {
        mRealm.executeTransactionAsync(realm -> {
            realm.insertOrUpdate(data);
        });
    }

    @Override
    public void addOrUpdate(@NonNull E item) {
        mRealm.beginTransaction();
        mRealm.insertOrUpdate(item);
        mRealm.commitTransaction();
    }

    @Override
    public void addOrUpdateAsync(@NonNull E item) {
        mRealm.executeTransactionAsync(realm -> {
            realm.insertOrUpdate(item);
        });
    }

    @Override
    public void delete(@NonNull Long itemId) {
        mRealm.beginTransaction();
        queryById(itemId).findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
    }

    @Override
    public void deleteAll(RealmQuery<E> query) {
        mRealm.beginTransaction();
        query.findAll().deleteAllFromRealm();
        mRealm.commitTransaction();
    }

    @Override
    public void deleteAll() {
        mRealm.beginTransaction();
        mRealm.delete(mClass);
        mRealm.commitTransaction();
    }

    @Override
    public void deleteAllAsync() {
        mRealm.executeTransactionAsync(realm -> {
            realm.delete(mClass);
        });
    }

    public LiveRealmResults<E> asLiveData(RealmResults<E> realmResults){
        return new LiveRealmResults<E>(realmResults);
    }

    public LiveRealmObject<E> asLiveData(E data) {
        return new LiveRealmObject<E>(data);
    }

    @Override
    public void closeRealm() {
        mRealm.close();
    }
}