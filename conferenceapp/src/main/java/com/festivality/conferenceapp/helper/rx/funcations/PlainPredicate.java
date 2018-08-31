package com.festivality.conferenceapp.helper.rx.funcations;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Predicate;

/**
 * Created by Anil Gudigar on 09/23/2018.
 */

public interface PlainPredicate<T> extends Predicate<T> {
    boolean test(@NonNull T var1);
}
