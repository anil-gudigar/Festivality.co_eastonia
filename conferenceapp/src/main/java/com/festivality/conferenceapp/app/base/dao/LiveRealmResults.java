package com.festivality.conferenceapp.app.base.dao;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import io.realm.OrderedRealmCollectionChangeListener;
import io.realm.RealmModel;
import io.realm.RealmResults;



public class LiveRealmResults<T extends RealmModel> extends LiveData<LiveRealmResultPair<T>> {
    private RealmResults<T> mRealmResults;
    private final OrderedRealmCollectionChangeListener<RealmResults<T>> listener = (realmResults, changeSet) -> {
        this.updateValue(new LiveRealmResultPair<T>(realmResults, changeSet));
    };

    public LiveRealmResults(@NonNull RealmResults<T> realmResults) {
        this.updateValue(new LiveRealmResultPair(realmResults, null));
    }

    public RealmResults<T> getData() {
        return this.mRealmResults;
    }

    protected void onActive() {
        super.onActive();
        this.mRealmResults.addChangeListener(this.listener);
    }

    protected void onInactive() {
        super.onInactive();
        this.mRealmResults.removeChangeListener(this.listener);
    }

    protected void updateValue(LiveRealmResultPair<T> value) {
        try {
            this.setValue(value);
        } catch (Exception var3) {
            this.postValue(value);
        }

    }

    protected void setValue(LiveRealmResultPair<T> value) {
        super.setValue(value);
        this.mRealmResults = value.getData();
    }

    protected void postValue(LiveRealmResultPair<T> value) {
        super.postValue(value);
        this.mRealmResults = value.getData();
    }

    public static <T extends RealmModel> LiveRealmResults<T> asLiveData(RealmResults<T> realmResults) {
        return new LiveRealmResults(realmResults);
    }


}
