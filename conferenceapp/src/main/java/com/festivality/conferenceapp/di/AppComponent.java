package com.festivality.conferenceapp.di;

import android.app.Application;

import com.festivality.conferenceapp.app.FestivalityApp;
import com.festivality.conferenceapp.helper.injection.ui.BuildersModule;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import dagger.android.support.AndroidSupportInjectionModule;

@Singleton
@Component(
        modules = {
                AndroidSupportInjectionModule.class,
                AndroidInjectionModule.class,
                AppModule.class,
                NetworkModule.class,
                DataModule.class,
                BuildersModule.class
        }
)
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        AppComponent build();
    }

    void inject(FestivalityApp application);
}

