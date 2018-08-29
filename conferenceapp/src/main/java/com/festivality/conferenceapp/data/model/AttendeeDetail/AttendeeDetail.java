package com.festivality.conferenceapp.data.model.AttendeeDetail;

import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;
/**
 * Created by Anil Gudigar on 09/24/2017.
 */
@Getter
@Setter
public class AttendeeDetail extends RealmObject {
    @SerializedName("response")
    @Expose
    private RealmList<Attendee> response;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("host")
    @Expose
    private String host;
    @SerializedName("userAuthenticated")
    @Expose
    private String userAuthenticated;
    @SerializedName("responseSize")
    @Expose
    private String responseSize;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("path")
    @Expose
    private String path;
    @SerializedName("method")
    @Expose
    private String method;
    @SerializedName("apiAuthenticated")
    @Expose
    private String apiAuthenticated;

    @Override
    public String toString() {

        return "AttendeeDetail{" +
                "response=" + response +
                ", message='" + message + '\'' +
                ", host='" + host + '\'' +
                ", userAuthenticated='" + userAuthenticated + '\'' +
                ", responseSize='" + responseSize + '\'' +
                ", status='" + status + '\'' +
                ", path='" + path + '\'' +
                ", method='" + method + '\'' +
                ", apiAuthenticated='" + apiAuthenticated + '\'' +
                '}';
    }
}
