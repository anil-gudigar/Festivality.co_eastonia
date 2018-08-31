package com.festivality.conferenceapp.data.model.Attendees;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmList;
import io.realm.RealmObject;
import lombok.Getter;
import lombok.Setter;
/**
 * Created by Anil Gudigar on 09/23/2018.
 */
@Getter
@Setter
public class CustomFields extends RealmObject {
    @SerializedName("position")
    @Expose
    private String position;
    @SerializedName("lastName")
    @Expose
    private String lastName;
    @SerializedName("publicEmail")
    @Expose
    private String publicEmail;
    @SerializedName("phone")
    @Expose
    private String phone;
    @SerializedName("industryComplimentaryTags")
    @Expose
    private RealmList<String> industryComplimentaryTags;
    @SerializedName("attendeeType")
    @Expose
    private RealmList<String> attendeeType;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("companySize")
    @Expose
    private String companySize;
    @SerializedName("attendeeProviding")
    @Expose
    private  RealmList<String> attendeeProviding;
    @SerializedName("industryTags")
    @Expose
    private  RealmList<String> industryTags;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("email")
    @Expose
    private String email;
    @SerializedName("company")
    @Expose
    private String company;
    @SerializedName("age")
    @Expose
    private String age;
    @SerializedName("positionType")
    @Expose
    private  RealmList<String> positionType;
    @SerializedName("gender")
    @Expose
    private String gender;
    @SerializedName("attendeeLookingFor")
    @Expose
    private  RealmList<String> attendeeLookingFor;
    @SerializedName("fullName")
    @Expose
    private String fullName;
    @SerializedName("firstName")
    @Expose
    private String firstName;

    public CustomFields() {
    }


    @Override
    public String toString() {
        return "CustomFields{" +
                "position='" + position + '\'' +
                ", lastName='" + lastName + '\'' +
                ", publicEmail='" + publicEmail + '\'' +
                ", phone='" + phone + '\'' +
                ", industryComplimentaryTags=" +industryComplimentaryTags +
                ", attendeeType=" +attendeeType +
                ", countryCode='" + countryCode + '\'' +
                ", companySize='" + companySize + '\'' +
                ", attendeeProviding=" + attendeeProviding +
                ", industryTags=" + industryTags +
                ", city='" + city + '\'' +
                ", email='" + email + '\'' +
                ", company='" + company + '\'' +
                ", age='" + age + '\'' +
                ", positionType=" +positionType +
                ", gender='" + gender + '\'' +
                ", attendeeLookingFor=" + attendeeLookingFor +
                ", fullName='" + fullName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
