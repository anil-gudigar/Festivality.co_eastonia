package com.festivality.conferenceapp.helper.rx.funcations;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by ankumar on 11/14/2017.
 */

public interface PlainConsumer<T> extends Consumer<T> {
    void accept(@NonNull T var1);
}
