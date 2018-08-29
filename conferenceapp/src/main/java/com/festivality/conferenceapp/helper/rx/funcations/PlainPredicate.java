package com.festivality.conferenceapp.helper.rx.funcations;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * Created by ankumar on 11/15/2017.
 */

public interface PlainPredicate<T> extends Predicate<T> {
    boolean test(@NonNull T var1);
}
