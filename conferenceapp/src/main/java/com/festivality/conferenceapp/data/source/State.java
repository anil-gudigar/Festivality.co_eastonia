package com.festivality.conferenceapp.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


public class State {
    @NonNull
    public Status status;

    @Nullable
    public String message;

    boolean hardAlert = false;

    public State(@NonNull Status status, @Nullable String message) {
        this.status = status;
        this.message = message;
    }

    public static State loading(String message) {
        return new State(Status.LOADING, message);
    }

    public static State error(String message) {
        return new State(Status.ERROR, message);
    }

    public static State success(String message) {
        return new State(Status.SUCCESS, message);
    }

    @NonNull
    public Status getStatus() {
        return status;
    }

    public void setStatus(@NonNull Status status) {
        this.status = status;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }

    public boolean isHardAlert() {
        return hardAlert;
    }

    public void setHardAlert(boolean hardAlert) {
        this.hardAlert = hardAlert;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        State state = (State)o;

        if (status != state.status) {
            return false;
        }

        return message != null ? message.equals(state.message) : state.message == null;
    }

    @Override
    public int hashCode() {
        int result = status.hashCode();
        return  31 * result + (message != null ? message.hashCode() : 0);
    }

    @Override
    public String toString() {
        return "status: " + status + ", message: " + message;
    }
}
