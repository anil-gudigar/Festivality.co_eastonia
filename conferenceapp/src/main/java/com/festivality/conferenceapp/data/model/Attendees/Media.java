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
public class Media extends RealmObject {
    @SerializedName("files")
    @Expose
    private Files files;
    @SerializedName("label")
    @Expose
    private String label;
    @SerializedName("type")
    @Expose
    private String type;


    @Override
    public String toString() {
        return "Media{" +
                "files=" + files +
                ", label='" + label + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}
