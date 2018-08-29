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
public class Type extends RealmObject {
    @SerializedName("text")
    @Expose
    private String text;

    public Type() {
    }


    @Override
    public String toString() {
        return "Type{" +
                "text='" + text + '\'' +
                '}';
    }
}
