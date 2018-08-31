package com.festivality.conferenceapp.helper.rx.funcations;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by Anil Gudigar on 09/23/2018.
 */

public interface PlainConsumer<T> extends Consumer<T> {
    void accept(@NonNull T var1);
}
