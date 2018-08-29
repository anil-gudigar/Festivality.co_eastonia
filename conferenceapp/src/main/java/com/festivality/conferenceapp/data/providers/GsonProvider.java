package com.festivality.conferenceapp.data.providers;

import com.festivality.conferenceapp.app.Constants;
import com.festivality.conferenceapp.data.model.realm.RealmString;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Modifier;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import io.realm.RealmList;
import io.realm.RealmObject;


public class GsonProvider {

    /**
     * Make gson with custom date time deserializer
     * @return {@link Gson} object
     */
    public static Gson makeGson() {
        return makeDefaultGsonBuilder().create();
    }

    /**
     * Make gson which {@link DateDeserializer} and compatible with {@link RealmObject}
     * @return {@link Gson} object
     */
    public static Gson makeGsonForRealm() {
        return makeDefaultGsonBuilder()
                .setExclusionStrategies(new ExclusionStrategy() {
                    @Override
                    public boolean shouldSkipField(FieldAttributes f) {
                        return f.getDeclaringClass().equals(RealmObject.class);
                    }

                    @Override
                    public boolean shouldSkipClass(Class<?> clazz) {
                        return false;
                    }
                })
                .create();
    }

    public static GsonBuilder makeDefaultGsonBuilder() {
        return new GsonBuilder()
                .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                .excludeFieldsWithModifiers(Modifier.FINAL, Modifier.TRANSIENT, Modifier.STATIC)
                .disableHtmlEscaping()
                .setPrettyPrinting()
                .registerTypeAdapter(Date.class, new DateDeserializer())
                .registerTypeAdapter(new TypeToken<RealmList<RealmString>>() {}.getType(), new RealmStringDeserializer());
    }

    private static class DateDeserializer implements JsonDeserializer<Date> {
        @Override
        public Date deserialize(JsonElement element, Type arg1, JsonDeserializationContext arg2) throws JsonParseException {
            String date = element.getAsString();
            SimpleDateFormat formatter = new SimpleDateFormat(Constants.API_CONSTANTS.DATE_TIME_FORMAT, Locale.getDefault());
            formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
            try {
                return formatter.parse(date);
            } catch (ParseException e) {
                e.printStackTrace();
                return null;
            }
        }
    }

    private static class RealmStringDeserializer implements JsonDeserializer<RealmList<RealmString>> {

        @Override
        public RealmList<RealmString> deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            RealmList<RealmString> list = new RealmList<>();
            JsonArray ja = json.getAsJsonArray();
            for (JsonElement je : ja) {
                list.add(new RealmString(je.getAsString()));
            }
            return list;
        }
    }
}
