package com.festivality.conferenceapp.di;

import dagger.Subcomponent;

@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder{
        ViewModelSubComponent build();
    }
}
