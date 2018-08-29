package com.festivality.conferenceapp.data.model.realm;

import io.realm.RealmObject;

public class RealmString extends RealmObject {

    public RealmString() {}

    public RealmString(String value) {
        this.value = value;
    }

    String value;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

