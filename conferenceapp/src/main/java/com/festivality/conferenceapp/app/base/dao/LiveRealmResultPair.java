package com.festivality.conferenceapp.app.base.dao;

import io.realm.OrderedCollectionChangeSet;
import io.realm.RealmModel;
import io.realm.RealmResults;



public class LiveRealmResultPair<T extends RealmModel> {
    private RealmResults<T> data;
    private OrderedCollectionChangeSet changeSet;

    public LiveRealmResultPair(RealmResults<T> data, OrderedCollectionChangeSet set) {
        this.data = data;
        this.changeSet = set;
    }

    public RealmResults<T> getData() {
        return this.data;
    }

    public OrderedCollectionChangeSet getChangeSet() {
        return this.changeSet;
    }
}
