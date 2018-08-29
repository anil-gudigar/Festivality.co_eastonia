package com.festivality.conferenceapp.app.base.viewmodel;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;


import com.festivality.conferenceapp.app.base.adapter.BaseRecyclerViewModelAdapter;
import com.festivality.conferenceapp.app.base.interfaces.OnActionItemClickListener;
import com.festivality.conferenceapp.app.base.interfaces.OnItemClickListener;
import com.festivality.conferenceapp.app.base.interfaces.PaginationListener;
import com.festivality.conferenceapp.app.base.interfaces.Refreshable;
import com.festivality.conferenceapp.helper.ui.NavigatorHelper;

import java.util.List;

import io.realm.RealmResults;




public abstract class ListViewModel<T, A extends BaseRecyclerViewModelAdapter> extends BaseViewModel implements PaginationListener, Refreshable, OnItemClickListener<T>,OnActionItemClickListener<T> {
    private List<T> data;

    @Nullable
    private A adapter;

    private int currentPage = 1;

    private int previousTotal;

    private int lastPage = Integer.MAX_VALUE;
    private String Title;

    public ListViewModel() {
    }

    @CallSuper
    public void initAdapter(@NonNull A adapter) {
        this.adapter = adapter;
        adapter.setData(data);
        adapter.setItemClickListener(this);
        adapter.setActionMenuClickListener(this);
    }

    @CallSuper
    @Override
    public void onCreate(@Nullable Bundle bundle, NavigatorHelper navigatorHelper) {
        super.onCreate(bundle, navigatorHelper);
    }

    /**
     * Call this to fill data to {@link #adapter}
     * @param newData new data
     * @param refresh true if data come from refresh action (call remote api)
     */
    protected void setData(@NonNull List<T> newData, boolean refresh) {
        if (data == null || data instanceof RealmResults || newData instanceof RealmResults) {
            this.data = newData;
        } else {
            if (refresh) {
                data.clear();
                adapter.notifyDataSetChanged();
            }
            data.addAll(newData);
            adapter.notifyDataSetChanged();
        }
        if (adapter != null) {
            adapter.setData(data);
        }
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    @Nullable
    public A getAdapter() {
        return adapter;
    }

    public void setAdapter(@Nullable A adapter) {
        this.adapter = adapter;
    }

    @Override
    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    @Override
    public int getPreviousTotal() {
        return previousTotal;
    }

    @Override
    public void setPreviousTotal(int previousTotal) {
        this.previousTotal = previousTotal;
    }

    public int getLastPage() {
        return lastPage;
    }

    public void setLastPage(int lastPage) {
        this.lastPage = lastPage;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Override
    public void refresh() {
        onCallApi(currentPage);
    }

    public void refresh(int delay) {
        new Handler(Looper.myLooper()).postDelayed(this::refresh, delay);
    }

    @Override
    public final boolean onCallApi(int page) {
        if (page == 1) {
            lastPage = Integer.MAX_VALUE;
        }
        if (page > lastPage || lastPage == 0) {
            return false;
        }
        currentPage = page;
        callApi(page, (lastPage, isRefresh, response) -> {
            this.lastPage = lastPage;
            setData(response, isRefresh);
        });
        return true;
    }

    protected abstract void callApi(int page, OnCallApiDone<T> onCallApiDone);

    public interface OnCallApiDone<T> {

        /**
         * Called after success response come
         * @param last last page
         * @param isRefresh true if refreshed
         * @param response response data
         */
        void onDone(int last, boolean isRefresh, List<T> response);
    }

    /**
     * IMPORTANCE to clear adapter reference since adapter instance is related to activity / fragment
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (adapter != null) {
            this.data = adapter.getData();
        }
        this.adapter = null;
    }
}
