package com.festivality.conferenceapp.app.base.views.fragment;

import android.databinding.ViewDataBinding;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.app.base.adapter.BaseRecyclerViewModelAdapter;
import com.festivality.conferenceapp.app.base.interfaces.Scrollable;
import com.festivality.conferenceapp.app.base.interfaces.TabBadgeListener;
import com.festivality.conferenceapp.app.base.interfaces.UiRefreshable;
import com.festivality.conferenceapp.data.source.Status;
import com.festivality.conferenceapp.features.attendees.view.adapters.AttendeeRecycleViewAdapter;
import com.festivality.conferenceapp.features.attendees.viewmodel.AttendeeViewModel;
import com.festivality.conferenceapp.helper.ui.AlertUtils;

import javax.inject.Inject;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;


public abstract class BaseAttendeeRecyclerViewFragment<
        B extends ViewDataBinding,
        T,
        A extends BaseRecyclerViewModelAdapter,
        VM extends AttendeeViewModel>
        extends BaseViewModelFragment<B, AttendeeViewModel> implements UiRefreshable, Scrollable {
    protected SwipeRefreshLayout refreshLayout;
    protected RecyclerView recyclerView;
    protected RelativeLayout emptyView;
    protected RelativeLayout dataView;
    protected RelativeLayout progressView;
    protected TextView toolbar_title;
    protected boolean isRefreshing;

    @Inject
    protected A adapter;


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        refreshLayout = view.findViewById(R.id.refreshLayout);
        recyclerView = view.findViewById(R.id.recyclerView);
        emptyView = view.findViewById(R.id.emptyView);
        dataView = view.findViewById(R.id.dataView);
        progressView = view.findViewById(R.id.progressView);
        DividerItemDecoration horizontalDecoration = new DividerItemDecoration(recyclerView.getContext(),
                DividerItemDecoration.VERTICAL);
        Drawable horizontalDivider = ContextCompat.getDrawable(getActivity(), R.drawable.horizontal_divider);
        horizontalDecoration.setDrawable(horizontalDivider);
        recyclerView.addItemDecoration(horizontalDecoration);
        viewModel.initAdapter((AttendeeRecycleViewAdapter) adapter);
        recyclerView.setAdapter(adapter);
        recyclerView.setHasFixedSize(true);
        Log.i("Anil", "Title " + viewModel.getTitle());
        //showEmptyView();
        refreshLayout.setColorSchemeResources(R.color.material_amber_700, R.color.material_blue_700,
                R.color.material_purple_700, R.color.material_lime_700);
        //refreshLayout.setOnRefreshListener(this::refresh);
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    AlertUtils.showToastShortMessage(v.getContext(), "Fragment -> OnBAckPressed");
                    return true;
                }
                return false;
            }
        });

        adapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                showEmptyView();
            }
        });
    }


    @Override
    protected int getLayout() {
        return R.layout.refresh_recycler_view;
    }

    @Override
    public void setLoading(boolean loading) {
        if (!loading) {
            doneRefresh();
            progressView.setVisibility(GONE);
        } else {
            progressView.setVisibility(VISIBLE);
        }
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void scrollTop(boolean animate) {
        if (recyclerView != null) {
            if (animate) {
                recyclerView.smoothScrollToPosition(0);
            } else {
                recyclerView.scrollToPosition(0);
            }
        }
    }

    @Override
    public void refresh() {
        if (!isRefreshing) {
            viewModel.refresh();
            isRefreshing = true;
        }
    }

    @Override
    public void doneRefresh() {
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(false);
        }
        isRefreshing = false;
        showEmptyView();
    }

    @Override
    public void refreshWithUi() {
        refreshWithUi(0);
    }

    @Override
    public void refreshWithUi(int delay) {
        if (refreshLayout != null) {
            refreshLayout.postDelayed(() -> {
                refreshUi();
                refresh();
            }, delay);
        }
    }

    protected void refreshUi() {
        if (refreshLayout != null) {
            refreshLayout.setRefreshing(true);
        }
    }

    protected void setNoDataText(String text) {
    }

    private void showEmptyView() {
        if(null != viewModel.getStateLiveData() && null != viewModel.getStateLiveData().getValue() && viewModel.getStateLiveData().getValue().status != Status.LOADING){
            if (adapter != null) {
                if (emptyView != null) {
                    if (adapter.getItemCount() == 0) {
                        showParentOrSelf(false);
                    } else {
                        showParentOrSelf(true);
                    }
                }
            } else {
                if (emptyView != null) {
                    showParentOrSelf(false);
                }
            }
        }
    }

    private void showParentOrSelf(boolean showRecyclerView) {
        if (recyclerView != null)
            dataView.setVisibility(showRecyclerView ? VISIBLE : GONE);
        emptyView.setVisibility(!showRecyclerView ? VISIBLE : GONE);
    }

    public void updateTabCount(int tabIndex, Integer count) {
        int n = count != null ? count : 0;
        Fragment fragment = getParentFragment();
        if (fragment != null && fragment instanceof TabBadgeListener) {
            ((TabBadgeListener) fragment).setBadge(tabIndex, n);
        }
    }

    @Override
    public void setRefreshEnabled(boolean enabled) {
        refreshLayout.setEnabled(enabled);
    }
}