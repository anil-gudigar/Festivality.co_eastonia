package com.festivality.conferenceapp.helper.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.festivality.conferenceapp.helper.BundleConstant;
import com.festivality.conferenceapp.helper.rx.funcations.PlainConsumer;


/**
 * Created by Anil Gudigar on 09/23/2018.
 */

public class FragmentUtils {
    public static <T extends Fragment> T createFragmentInstance(T fragment, PlainConsumer<Bundle> bundlePlainConsumer) {
        Bundle bundle = new Bundle();
        bundlePlainConsumer.accept(bundle);
        fragment.setArguments(bundle);
        return fragment;
    }

    public static <T extends Fragment> T createFragmentInstance(T fragment, String extra) {
        return createFragmentInstance(fragment, bundle -> bundle.putString(BundleConstant.EXTRA, extra));
    }
}