package com.festivality.conferenceapp.data.model.device;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class DeciveIDResponse  extends RealmObject {
    @SerializedName("deviceId")
    private String deviceId;

    public String getDeviceId ()
    {
        return deviceId;
    }

    public void setDeviceId (String deviceId)
    {
        this.deviceId = deviceId;
    }

    @Override
    public String toString() {
        return "Response{" +
                "deviceId='" + deviceId + '\'' +
                '}';
    }
}