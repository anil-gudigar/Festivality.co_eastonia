package com.festivality.conferenceapp.data.model.Attendees;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import lombok.Getter;
import lombok.Setter;
/**
 * Created by Anil Gudigar on 09/24/2017.
 */
@Getter
@Setter
public class Attendee extends RealmObject {
    @SerializedName("ilike")
    @Expose
    private String ilike;
    @SerializedName("customFields")
    @Expose
    private CustomFields customFields;
    @SerializedName("isFeatured")
    @Expose
    private String isFeatured;
    @SerializedName("children")
    @Expose
    private RealmList<String> children;
    @SerializedName("object")
    @Expose
    private String object;
    @SerializedName("type")
    @Expose
    private RealmList<Type> type;
    @SerializedName("date")
    @Expose
    private Date date;
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private String id;
    @SerializedName("irate")
    @Expose
    private String irate;
    @SerializedName("share")
    @Expose
    private String share;
    @SerializedName("additions")
    @Expose
    private RealmList<String> additions;
    @SerializedName("likes")
    @Expose
    private String likes;
    @SerializedName("rsvp")
    @Expose
    private String rsvp;
    @SerializedName("slug")
    @Expose
    private String slug;
    @SerializedName("media")
    @Expose
    private  RealmList<Media> media;

    public Attendee() {
    }



    @Override
    public String toString() {
        return "Attendee{" +
                "ilike='" + ilike + '\'' +
                ", customFields=" + customFields +
                ", isFeatured='" + isFeatured + '\'' +
                ", children=" + children +
                ", object='" + object + '\'' +
                ", type=" + type +
                ", date=" + date +
                ", id='" + id + '\'' +
                ", irate='" + irate + '\'' +
                ", share='" + share + '\'' +
                ", additions=" + additions +
                ", likes='" + likes + '\'' +
                ", rsvp='" + rsvp + '\'' +
                ", slug='" + slug + '\'' +
                ", media=" + media +
                '}';
    }
}
