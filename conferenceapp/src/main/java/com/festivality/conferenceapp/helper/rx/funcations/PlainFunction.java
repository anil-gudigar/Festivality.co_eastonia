package com.festivality.conferenceapp.helper.rx.funcations;

import io.reactivex.functions.Function;

/**
 * Created by Anil Gudigar on 09/23/2018.
 */

public interface PlainFunction<T, R> extends Function<T, R> {
    R apply(T var1);
}
