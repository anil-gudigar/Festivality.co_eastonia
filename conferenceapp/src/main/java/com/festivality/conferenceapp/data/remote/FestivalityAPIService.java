package com.festivality.conferenceapp.data.remote;


import com.festivality.conferenceapp.app.Constants;
import com.festivality.conferenceapp.data.model.AttendeeDetail.AttendeeDetail;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.model.Attendees.Attendees;
import com.festivality.conferenceapp.data.model.auth.Authentication;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Streaming;
import retrofit2.http.Url;



public interface FestivalityAPIService {
    @GET
    Single<Authentication> authenticate(@Url String url, @Header(Constants.API_CONSTANTS.X_API_CLIENT) String xapiclient);

    @Streaming
    @GET
    Single<Attendees> loadUserList(@Url String url, @Header(Constants.API_CONSTANTS.X_API_CLIENT) String xapiclient, @Header(Constants.API_CONSTANTS.X_HEADER_REQUEST) String xheaderrequest);

    @Streaming
    @GET
    Single<AttendeeDetail> loadUser(@Url String url, @Header(Constants.API_CONSTANTS.X_API_CLIENT) String xapiclient, @Header(Constants.API_CONSTANTS.X_HEADER_REQUEST) String xheaderrequest);

    @Streaming
    @GET
    Single<Attendee> loadUserDetail(@Url String url, @Header(Constants.API_CONSTANTS.X_API_CLIENT) String xapiclient, @Header(Constants.API_CONSTANTS.X_HEADER_REQUEST) String xheaderrequest);
}
