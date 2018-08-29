package com.festivality.conferenceapp.data.remote.converters;

import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.model.Attendees.CustomFields;
import com.festivality.conferenceapp.data.model.Attendees.Media;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import java.lang.reflect.Type;

import io.realm.RealmList;

public class AttendeeResponseDeserializer implements JsonDeserializer<Attendee> {
    @Override
    public Attendee deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {


        if (((JsonObject) json).get("customFields") instanceof JsonObject) {
            return new Gson().fromJson(json, Attendee.class);
        } else {
            Attendee attendee = new Attendee();
            attendee.setId(((JsonObject) json).get("id").isJsonNull()? "":((JsonObject) json).get("id").getAsString() );
            attendee.setIsFeatured(((JsonObject) json).get("isFeatured").isJsonNull() ? "":((JsonObject) json).get("isFeatured").getAsString() );

            Type listType = new TypeToken<RealmList<com.festivality.conferenceapp.data.model.Attendees.Type>>(){}.getType();
            attendee.setType(new Gson().fromJson(((JsonObject)json).get("type").getAsJsonArray(), listType));

            Type dateType = new TypeToken<com.festivality.conferenceapp.data.model.Attendees.Date>(){}.getType();
            attendee.setDate(new Gson().fromJson(((JsonObject)json).get("date").getAsJsonObject(), dateType));

            attendee.setLikes(((JsonObject) json).get("likes").isJsonNull() ? " ":((JsonObject) json).get("likes").getAsString());

            attendee.setObject(((JsonObject) json).get("object").isJsonNull()? " ": ((JsonObject) json).get("object").getAsString());

            attendee.setShare(((JsonObject) json).get("share").isJsonNull()? " ": ((JsonObject) json).get("share").getAsString());

            attendee.setSlug(((JsonObject) json).get("slug").isJsonNull() ? " ": ((JsonObject) json).get("slug").getAsString());

            attendee.setRsvp(((JsonObject) json).get("rsvp").isJsonNull() ? " ":((JsonObject) json).get("rsvp").getAsString());

            Type childrenlistType = new TypeToken<RealmList<String>>(){}.getType();
            attendee.setChildren(new Gson().fromJson(((JsonObject)json).get("children").getAsJsonArray(), childrenlistType));

            Type medialistType = new TypeToken<RealmList<Media>>(){}.getType();
            attendee.setMedia(new Gson().fromJson(((JsonObject)json).get("media").getAsJsonArray(), medialistType));

            attendee.setIlike(((JsonObject) json).get("ilike").isJsonNull() ? "":((JsonObject) json).get("ilike").getAsString());

            attendee.setIrate(((JsonObject) json).get("irate").isJsonNull()? "":((JsonObject) json).get("irate").getAsString() );

            attendee.setCustomFields(new CustomFields());
            return attendee;
        }

    }
}
