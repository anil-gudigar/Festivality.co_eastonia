package com.festivality.conferenceapp.helper;

import android.arch.lifecycle.LiveData;

/**
 * Created by Anil Gudigar on 09/23/2018.
 * Thread-safe live data to resolve this issue: when perform  {@link LiveData#setValue(Object)}
 * not in main Thread. (almost case is in testing)
 */

public class SafeMutableLiveData <T> extends LiveData<T> {

    @Override
    public void setValue(T value) {
        try {
            super.setValue(value);
        } catch (Exception e) {
            // if we can't set value due to not in main thread, must call post value instead
            super.postValue(value);
        }
    }
}

