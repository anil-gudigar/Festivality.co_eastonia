package com.festivality.conferenceapp.app.base.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.util.Log;

import com.festivality.conferenceapp.app.FestivalityApp;
import com.festivality.conferenceapp.data.model.error.ErrorEntity;
import com.festivality.conferenceapp.data.source.Resource;
import com.festivality.conferenceapp.data.source.State;
import com.festivality.conferenceapp.data.source.Status;
import com.festivality.conferenceapp.helper.InputHelper;
import com.festivality.conferenceapp.helper.RestHelper;
import com.festivality.conferenceapp.helper.SafeMutableLiveData;
import com.festivality.conferenceapp.helper.rx.funcations.PlainConsumer;
import com.festivality.conferenceapp.helper.ui.NavigatorHelper;

import io.reactivex.Flowable;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;



public abstract class BaseViewModel extends ViewModel {

    protected final String TAG;

    private boolean isFirstTimeUiCreate = true;

    @NonNull
    private CompositeDisposable mCompositeDisposable = new CompositeDisposable();

    @NonNull
    protected final SafeMutableLiveData<State> stateLiveData = new SafeMutableLiveData<>();


    protected NavigatorHelper navigatorHelper;

    public BaseViewModel() {
        TAG = this.getClass().getSimpleName();
    }

    /**
     * called after fragment / activity is created with input bundle arguments
     * @param bundle argument data
     */
    @CallSuper
    public void onCreate(@Nullable Bundle bundle, NavigatorHelper navigatorHelper) {
        Log.d(TAG, "onCreate: UI creating...");
        this.navigatorHelper = navigatorHelper;
        if (isFirstTimeUiCreate) {
            onFirsTimeUiCreate(bundle);
            isFirstTimeUiCreate = false;
        }
    }

    /**
     * Called when UI create for first time only, since activity / fragment might be rotated,
     * we don't need to re-init data, because view model will survive, data aren't destroyed
     * @param bundle
     */
    protected abstract void onFirsTimeUiCreate(@Nullable Bundle bundle);


    protected String getString(@StringRes int res) {
        return FestivalityApp.getInstance().getString(res);
    }

    /**
     * It is importance to un-reference activity / fragment instance after they are destroyed
     * For situation of configuration changes, activity / fragment will be destroyed and recreated,
     * but view model will survive, so if we don't un-reference them, memory leaks will occur
     */
    public void onDestroyView() {
        navigatorHelper = null;
    }

    public void disposeAllExecutions() {
        mCompositeDisposable.dispose();
        mCompositeDisposable = new CompositeDisposable();
        publishState(State.success(null));
    }

    @CallSuper
    @Override
    protected void onCleared() {
        super.onCleared();
        mCompositeDisposable.dispose();
    }

    public boolean isFirstTimeUiCreate() {
        return isFirstTimeUiCreate;
    }

    public void setFirstTimeUiCreate(boolean firstTimeUiCreate) {
        isFirstTimeUiCreate = firstTimeUiCreate;
    }

    @NonNull
    public CompositeDisposable getmCompositeDisposable() {
        return mCompositeDisposable;
    }

    public void setmCompositeDisposable(@NonNull CompositeDisposable mCompositeDisposable) {
        this.mCompositeDisposable = mCompositeDisposable;
    }

    @NonNull
    public SafeMutableLiveData<State> getStateLiveData() {
        return stateLiveData;
    }

    public NavigatorHelper getNavigatorHelper() {
        return navigatorHelper;
    }

    public void setNavigatorHelper(NavigatorHelper navigatorHelper) {
        this.navigatorHelper = navigatorHelper;
    }

    /**
     * Add and execute an resource flowable created by
     * {@link RestHelper#createRemoteSourceMapper(Single, PlainConsumer)}
     * Loading, error, success status will be updated automatically via {@link #stateLiveData} which should be observed
     * by fragments / activities to update UI appropriately
     * @param showProgress true if should show progress when executing, false if not
     * @param resourceFlowable flowable resource, see {@link com.festivality.conferenceapp.data.source.SimpleRemoteSourceMapper}
     * @param responseConsumer consume response data
     * @param <T> type of response
     */
    protected  <T> void execute(boolean showProgress, Flowable<Resource<T>> resourceFlowable,
                                @Nullable PlainConsumer<T> responseConsumer) {
        Disposable disposable = resourceFlowable.observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.newThread())
                .subscribe(resource -> {
                    if (resource != null) {
                        Log.d("source", "addRequest: resource changed: " + resource.toString());
                        if (resource.data != null && responseConsumer != null) {
                            //Log.d("source", "addRequest:  resource.data getTotalCount : " + ((Pageable)resource.data).getTotalCount());
                           // Log.d("source", "addRequest:  resource.data  getLast: " + ((Pageable)resource.data).getLast());
                            responseConsumer.accept(resource.data);
                        }
                        if (resource.state.getStatus() == Status.LOADING && !showProgress) {
                            // do nothing if progress showing is not allowed
                        } else {
                            publishState(resource.state);
                        }
                    }
                });
        mCompositeDisposable.add(disposable);
    }

    protected <T> void execute(boolean showProgress, Single<T> request, @NonNull PlainConsumer<T> responseConsumer) {
        execute(showProgress, true, request, responseConsumer, null);
    }

    protected <T> void execute(boolean showProgress, boolean publishState, Single<T> request,
                               @NonNull PlainConsumer<T> responseConsumer,
                               @Nullable PlainConsumer<ErrorEntity> errorConsumer) {
        // execute(showProgress, RestHelper.createRemoteSourceMapper(request, null), responseConsumer);
        if (showProgress && publishState) {
            publishState(State.loading(null));
        }
        Disposable disposable = RestHelper.makeRequest(request, true, response -> {
            responseConsumer.accept(response);
            if (publishState) {
                publishState(State.success(null));
            }
        }, errorEntity -> {
            if (errorConsumer != null) {
                errorConsumer.accept(errorEntity);
            }
            if (publishState) {
                publishState(State.error(errorEntity.getMessage()));
            }
        });
        mCompositeDisposable.add(disposable);
    }

    public void publishState(State state) {
        stateLiveData.setValue(state);
        if (!InputHelper.isEmpty(state.getMessage())) {
            // if state has a message, after show it, we should reset to prevent
            // message will still be shown if fragment / activity is rotated (re-observe state live data)
            new Handler().postDelayed(() -> stateLiveData.setValue(State.success(null)), 100);
        }
    }
}