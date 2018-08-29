package com.festivality.conferenceapp.data.remote;

import javax.inject.Inject;


public class FestivalityAPIRepository {
    private final FestivalityAPIService expressAPIService;

    @Inject
    public FestivalityAPIRepository(FestivalityAPIService expressAPIService) {
        this.expressAPIService = expressAPIService;
    }
}
