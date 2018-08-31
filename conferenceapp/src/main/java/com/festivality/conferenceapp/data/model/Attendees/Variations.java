package com.festivality.conferenceapp.data.model.Attendees;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;
/**
 * Created by Anil Gudigar on 09/23/2018.
 */
@Getter
@Setter
public class Variations extends RealmObject {
    @SerializedName("tiny")
    @Expose
    private String tiny;
    @SerializedName("original")
    @Expose
    private String original;
    @SerializedName("small")
    @Expose
    private String small;

    public Variations() {
    }

    @Override
    public String toString() {
        return "Variations{" +
                "tiny='" + tiny + '\'' +
                ", original='" + original + '\'' +
                ", small='" + small + '\'' +
                '}';
    }
}
