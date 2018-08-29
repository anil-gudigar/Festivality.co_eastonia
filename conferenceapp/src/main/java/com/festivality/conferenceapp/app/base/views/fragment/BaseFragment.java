package com.festivality.conferenceapp.app.base.views.fragment;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.festivality.conferenceapp.helper.AutoClearedValue;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

/**
 * Base class using data binding. The binding object reference will be removed as soon as the fragment view is destroyed
 * See {@link AutoClearedValue}
 */

public abstract class BaseFragment<B extends ViewDataBinding> extends Fragment {

    protected String TAG;

    protected B binding;
    @Inject
    protected RefWatcher refWatcher;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        TAG = this.getClass().getSimpleName();
        B dataBinding = DataBindingUtil.inflate(inflater, getLayout(), container, false);
        this.binding = new AutoClearedValue<B>(this, dataBinding).get();
        return binding.getRoot();
    }

    protected abstract int getLayout();

    protected Bundle getFragmentArguments() {
        return getArguments();
    }

    public void onScrollTop(int index) {

    }
    public boolean onBackPressed() {
        return false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        refWatcher.watch(this);
    }
}