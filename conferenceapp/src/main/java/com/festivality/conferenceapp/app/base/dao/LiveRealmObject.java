package com.festivality.conferenceapp.app.base.dao;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import io.realm.RealmChangeListener;
import io.realm.RealmObject;


public class LiveRealmObject<T extends RealmObject> extends LiveData<T> {
    private T mData;
    private final RealmChangeListener<T> listener = this::updateValue;

    public LiveRealmObject(@NonNull T data) {
        this.updateValue(data);
    }

    protected void onActive() {
        super.onActive();
        this.mData.addChangeListener(this.listener);
    }

    protected void onInactive() {
        super.onInactive();
        this.mData.removeChangeListener(this.listener);
    }

    protected void updateValue(T t) {
        try {
            this.setValue(t);
        } catch (Exception var3) {
            this.postValue(t);
        }

    }

    protected void setValue(T value) {
        super.setValue(value);
        this.mData = value;
    }

    protected void postValue(T value) {
        super.postValue(value);
        this.mData = value;
    }

    public T getData() {
        return this.mData;
    }

    public static <E extends RealmObject> LiveRealmObject<E> asLiveData(@NonNull E data) {
        return new LiveRealmObject(data);
    }
}

