package com.festivality.conferenceapp.data.model.Attendees;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;
/**
 * Created by Anil Gudigar on 09/24/2017.
 */
@Getter
@Setter
public class Files extends RealmObject {
    @SerializedName("default")
    @Expose
    private String default_date;
    @SerializedName("variations")
    @Expose
    private Variations variations;

    public Files() {
    }

    @Override
    public String toString() {
        return "Files{" +
                "default_date='" + default_date + '\'' +
                ", variations=" + variations +
                '}';
    }
}
