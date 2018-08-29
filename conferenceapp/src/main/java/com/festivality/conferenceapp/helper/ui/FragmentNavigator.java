package com.festivality.conferenceapp.helper.ui;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.View;

/**
 * Created by ankumar on 11/19/2017.
 */

public interface FragmentNavigator extends Navigator {
    void replaceChildFragment(@IdRes int var1, @NonNull Fragment var2, View... var3);

    void replaceChildFragment(@IdRes int var1, @NonNull Fragment var2, Bundle var3, View... var4);

    void replaceChildFragment(@IdRes int var1, @NonNull Fragment var2, @NonNull String var3, Bundle var4, View... var5);

    void replaceChildFragmentAndAddToBackStack(@IdRes int var1, @NonNull Fragment var2, Bundle var3, String var4, View... var5);

    void replaceChildFragmentAndAddToBackStack(@IdRes int var1, @NonNull Fragment var2, @NonNull String var3, Bundle var4, String var5, View... var6);

  /*  @Nullable
    <T extends Fragment> T findChildFragmentByTag(@NonNull String var1);

    @Nullable
    <T extends Fragment> T findChildFragmentById(@IdRes int var1);*/
}

