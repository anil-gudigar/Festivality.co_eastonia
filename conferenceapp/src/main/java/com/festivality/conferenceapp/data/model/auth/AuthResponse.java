package com.festivality.conferenceapp.data.model.auth;

import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class AuthResponse extends RealmObject {
    @SerializedName("message")
    private String message;
    @SerializedName("identifier")
    private String identifier;

    public String getMessage ()
    {
        return message;
    }

    public void setMessage (String message)
    {
        this.message = message;
    }

    public String getIdentifier ()
    {
        return identifier;
    }

    public void setIdentifier (String identifier)
    {
        this.identifier = identifier;
    }

    @Override
    public String toString() {
        return "Response{" +
                "message='" + message + '\'' +
                ", identifier='" + identifier + '\'' +
                '}';
    }
}

