package com.festivality.conferenceapp.helper.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by Anil Gudigar on 09/23/2018.
 */

public class ChildFragmentNavigator  extends ActivityNavigator implements FragmentNavigator {
    private final Fragment fragment;

    public ChildFragmentNavigator(Fragment fragment) {
        super(fragment.getActivity());
        this.fragment = fragment;
    }

    public void replaceChildFragment(@IdRes int containerId, @NonNull Fragment fragment, View... transitionViews) {
        this.replaceFragmentInternal(this.fragment.getChildFragmentManager(), containerId, fragment, null, null, false, null);
    }

    public final void replaceChildFragment(@IdRes int containerId, @NonNull Fragment fragment, Bundle args, View... transitionViews) {
        this.replaceFragmentInternal(this.fragment.getChildFragmentManager(), containerId, fragment, null, args, false, null);
    }

    public final void replaceChildFragment(@IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag, Bundle args, View... transitionViews) {
        this.replaceFragmentInternal(this.fragment.getChildFragmentManager(), containerId, fragment, fragmentTag, args, false, null);
    }

    public final void replaceChildFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment, Bundle args, String backstackTag, View... transitionViews) {
        this.replaceFragmentInternal(this.fragment.getChildFragmentManager(), containerId, fragment, null, args, true, backstackTag);
    }

    public final void replaceChildFragmentAndAddToBackStack(@IdRes int containerId, @NonNull Fragment fragment, @NonNull String fragmentTag, Bundle args, String backstackTag, View... transitionViews) {
        this.replaceFragmentInternal(this.fragment.getChildFragmentManager(), containerId, fragment, fragmentTag, args, true, backstackTag);
    }

  /*  @Nullable
    public <T extends Fragment> T findChildFragmentByTag(@NonNull String tag) {
        return this.fragment.getChildFragmentManager().findFragmentByTag(tag);
    }

    @Nullable
    public <T extends Fragment> T findChildFragmentById(@IdRes int containerId) {
        return this.fragment.getChildFragmentManager().findFragmentById(containerId);
    }*/
}

