package com.festivality.conferenceapp.helper.rx;



import com.festivality.conferenceapp.helper.rx.funcations.PlainFunction;
import com.festivality.conferenceapp.helper.rx.funcations.PlainPredicate;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by ankumar on 11/15/2017.
 */

public class Rx {
    public Rx() {
    }

    @NonNull
    public static <T, R> List<R> map(@NonNull List<T> input, @NonNull PlainFunction<T, R> func) {
        List<R> output = new ArrayList();
        Iterator var3 = input.iterator();

        while(var3.hasNext()) {
            T t = (T) var3.next();
            output.add(func.apply(t));
        }

        return output;
    }

    @NonNull
    public static <T> List<T> filter(@NonNull List<T> input, @NonNull PlainPredicate<T> predicate) {
        List<T> out = new ArrayList();
        Iterator var3 = input.iterator();

        while(var3.hasNext()) {
            T t = (T) var3.next();
            if(predicate.test(t)) {
                out.add(t);
            }
        }

        return out;
    }

    @NonNull
    public static <T> List<T> filter(@NonNull T[] input, @NonNull PlainPredicate<T> predicate) {
        List<T> out = new ArrayList();
        Object[] var3 = input;
        int var4 = input.length;

        for(int var5 = 0; var5 < var4; ++var5) {
            T t = (T) var3[var5];
            if(predicate.test(t)) {
                out.add(t);
            }
        }

        return out;
    }

    @NonNull
    public static <T> boolean[] filterIndex(@NonNull List<T> input, @NonNull PlainPredicate<T> predicate) {
        boolean[] result = new boolean[input.size()];
        int i = 0;

        Object t;
        for(Iterator var4 = input.iterator(); var4.hasNext(); result[i++] = predicate.test((T) t)) {
            t = var4.next();
        }

        return result;
    }
}
