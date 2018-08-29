package com.festivality.conferenceapp.di;

import android.content.Context;

import com.festivality.conferenceapp.data.local.RealmDatabase;
import com.festivality.conferenceapp.helper.injection.qualifier.ApplicationContext;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;
import io.realm.RealmConfiguration;

@Module
public class DataModule {
    public DataModule() {}

    @Provides
    @Singleton
    RealmConfiguration provideRealmConfiguration(@ApplicationContext Context context) {
        int schemaVersion = 1; // first version
        Realm.init(context);
        RealmConfiguration realmConfig = new RealmConfiguration.Builder()
                .schemaVersion(schemaVersion)
                .migration((realm, oldVersion, newVersion) -> {
                    // migrate realm here
                })
                .deleteRealmIfMigrationNeeded()
                .build();
        Realm.setDefaultConfiguration(realmConfig);
        return realmConfig;
    }

    @Provides
    @Singleton
    RealmDatabase provideRealmDatabase(RealmConfiguration realmConfig) {
        return new RealmDatabase(realmConfig);
    }
}
