package com.festivality.conferenceapp.app.base.views.fragment;

import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;


import com.festivality.conferenceapp.app.base.viewmodel.BaseViewModel;
import com.festivality.conferenceapp.app.base.views.acivity.BaseActivity;
import com.festivality.conferenceapp.data.source.State;
import com.festivality.conferenceapp.data.source.Status;
import com.festivality.conferenceapp.helper.injection.Injectable;
import com.festivality.conferenceapp.helper.ui.AlertUtils;
import com.festivality.conferenceapp.helper.ui.NavigatorHelper;

import javax.inject.Inject;

public abstract class BaseViewModelFragment<B extends ViewDataBinding, VM extends BaseViewModel> extends BaseFragment<B>
        implements Injectable {

    @Inject
    protected ViewModelProvider.Factory viewModelFactory;

    //@Inject
    protected VM viewModel;

    @Inject
    protected NavigatorHelper navigatorHelper;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (! (getActivity() instanceof BaseActivity)) {
            throw new IllegalStateException("All fragment's container must extend BaseActivity");
        }
    }

    @Override
    @CallSuper
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(getViewModelClass());
        viewModel.onCreate(getFragmentArguments(), navigatorHelper);
        viewModel.getStateLiveData().observe(this, this::handleState);
    }

    /**
     * Default state handling, can be override
     * @param state viewModel's state
     */
    protected void handleState(@Nullable State state) {
        setLoading(state != null && state.getStatus() == Status.LOADING);
        handleMessageState(state);
    }

    protected void handleMessageState(@Nullable State state) {
        if (state != null && state.getMessage() != null) {
            if (state.isHardAlert()) {
                AlertUtils.showAlertDialog(getContext(), state.getMessage());
                Log.d(TAG, "handleMessageState: " + state.getMessage());
            } else {
                AlertUtils.showToastShortMessage(getContext(), state.getMessage());
                Log.d(TAG, "handleMessageState: " + state.getMessage());
            }
        }
    }

    public void setLoading(boolean loading) {
        //((BaseActivity)getActivity()).setLoading(loading);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.onDestroyView();
    }

    protected abstract Class<VM> getViewModelClass();
}
