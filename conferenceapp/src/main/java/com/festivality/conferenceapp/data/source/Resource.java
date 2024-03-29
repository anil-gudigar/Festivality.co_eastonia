package com.festivality.conferenceapp.data.source;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;



import static com.festivality.conferenceapp.data.source.Status.ERROR;
import static com.festivality.conferenceapp.data.source.Status.LOADING;
import static com.festivality.conferenceapp.data.source.Status.SUCCESS;

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */

public class Resource<T> {

    @NonNull
    public final State state;

    @Nullable
    public final T data;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.state = new State(status, message);
        this.data = data;
    }

    @NonNull
    public State getState() {
        return state;
    }

    @Nullable
    public T getData() {
        return data;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(String msg, @Nullable T data) {
        return new Resource<>(ERROR, data, msg);
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Resource<?> resource = (Resource<?>) o;

        return resource.state.equals(this.state) && data != null ? data.equals(resource.data) : resource.data == null;
    }

    @Override
    public int hashCode() {
        int result = state.hashCode();
        result = 31 * result + (data != null ? data.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Resource{" +
                "status=" + state.status +
                ", message='" + state.message + '\'' +
                ", data=" + data +
                '}';
    }
}
