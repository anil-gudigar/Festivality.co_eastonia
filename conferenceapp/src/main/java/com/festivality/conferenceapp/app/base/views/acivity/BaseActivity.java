package com.festivality.conferenceapp.app.base.views.acivity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.features.home.view.activity.HomeActivity;
import com.festivality.conferenceapp.helper.ui.AlertUtils;
import com.squareup.leakcanary.RefWatcher;

import javax.inject.Inject;

import dagger.android.AndroidInjector;
import dagger.android.DispatchingAndroidInjector;
import dagger.android.support.HasSupportFragmentInjector;

/**
 * Base activity that will be injected automatically by implementing {@link HasSupportFragmentInjector}
 * SEE {@link com.festivality.conferenceapp.helper.injection.AppInjector}
 * All fragment inside this activity is injected as well
 */
public abstract class BaseActivity extends BasePermissionActivity
        implements HasSupportFragmentInjector {

    @Inject
    protected RefWatcher refWatcher;

    // dispatch android injector to all fragments
    @Inject
    DispatchingAndroidInjector<Fragment> dispatchingAndroidInjector;

    @Nullable
    protected AppBarLayout appBar;
    @Nullable protected Toolbar toolbar;
    private long backPressTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (!shouldUseDataBinding()) {
            // set contentView if child activity not use dataBinding
            setContentView(getLayout());
            initViews();
        }
       // CommonUtils.hideSoftKeyboard(this);
    }

    protected void initViews() {

    }

    public abstract int getLayout();

    @Override
    protected void onStart() {
        super.onStart();

    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        refWatcher.watch(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (canBack()) {
            if (item.getItemId() == android.R.id.home) {
                onBackPressed();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }



    /**
     * @return true if should use back button on toolbar
     */
    protected abstract boolean canBack();



    /**
     * @return true if should register event bus (onStart - onStop)
     */
    protected boolean shouldRegisterEventBus() {
        return false;
    }

    /**
     * @return true if child activity should use data binding instead of {@link #setContentView(View)}
     */
    protected boolean shouldUseDataBinding() {
        return false;
    }


    private void superOnBackPressed(boolean didClickTwice) {
        if (this instanceof HomeActivity) {
            if (didClickTwice) {
                if (canExit()) {
                    super.onBackPressed();
                }
            } else {
                super.onBackPressed();
            }
        } else {
            super.onBackPressed();
        }
    }

    private boolean canExit() {
        if (backPressTimer + 2000 > System.currentTimeMillis()) {
            return true;
        } else {
            showToastLongMessage(getString(R.string.press_again_to_exit));
        }
        backPressTimer = System.currentTimeMillis();
        return false;
    }

    public void showToastLongMessage(String message) {
        AlertUtils.showToastLongMessage(this, message);
    }

    public void showToastShortMessage(String message){
        AlertUtils.showToastShortMessage(this, message);
    }

    @Override
    public AndroidInjector<Fragment> supportFragmentInjector() {
        return dispatchingAndroidInjector;
    }
}

