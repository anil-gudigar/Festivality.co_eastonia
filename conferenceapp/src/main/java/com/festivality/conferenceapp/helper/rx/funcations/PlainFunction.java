package com.festivality.conferenceapp.helper.rx.funcations;

import io.reactivex.functions.Function;

/**
 * Created by ankumar on 11/15/2017.
 */

public interface PlainFunction<T, R> extends Function<T, R> {
    R apply(T var1);
}
