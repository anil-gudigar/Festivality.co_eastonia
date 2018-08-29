package com.festivality.conferenceapp.data.model.Attendees;

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
public class Attendees extends RealmObject {
    @SerializedName("response")
    @Expose
    private RealmList<Attendee> attendees;
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

    public Attendees() {
    }


    @Override
    public String toString() {
        return "Attendees{" +
                "attendees=" + attendees +
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
