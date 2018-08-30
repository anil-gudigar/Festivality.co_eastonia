package com.festivality.conferenceapp.features.intro.view.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.app.Constants;
import com.festivality.conferenceapp.app.base.views.acivity.BaseViewModelActivity;
import com.festivality.conferenceapp.databinding.ActivityHomeBinding;
import com.festivality.conferenceapp.databinding.ActivityIntroBinding;
import com.festivality.conferenceapp.features.home.view.activity.HomeActivity;
import com.festivality.conferenceapp.features.home.view.fragment.DefaultFragment;
import com.festivality.conferenceapp.features.intro.view.adapter.SectionsPagerAdapter;
import com.festivality.conferenceapp.features.intro.viewmodel.IntroViewModel;
import com.festivality.conferenceapp.helper.ui.BottomNavigationViewHelper;
import com.tbruyelle.rxpermissions2.RxPermissions;

import javax.inject.Inject;

public class IntroActivity extends BaseViewModelActivity<ActivityIntroBinding, IntroViewModel> implements ViewPager.OnPageChangeListener, DefaultFragment.OnFragmentInteractionListener {

    @Inject
    SectionsPagerAdapter adapter;

    private RxPermissions rxPermissions = null;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_intro;
    }

    @Override
    protected boolean canBack() {
        return false;
    }

    private void init() {
        checkAllPermisionPassed();
        rxPermissions = new RxPermissions(this);
        binding.pager.setOffscreenPageLimit(3);
        binding.pager.setAdapter(adapter);
        binding.pager.addOnPageChangeListener(this);
        binding.pager.setCurrentItem(viewModel.getCurrentTab());
        onPageSelected(viewModel.getCurrentTab());
    }


    public void onNotificationPageAction(boolean isSkiped, boolean isPermisiongranted) {
        viewModel.getMSharedPreferences().setPreferences(Constants.PREF_NOTIFICATION_SKIP, isSkiped);
        viewModel.getMSharedPreferences().setPreferences(Constants.PREF_NOTIFICATION_ALLOW, isPermisiongranted);
        binding.pager.setCurrentItem(1);
    }

    public void onLocationPageAction(boolean isSkiped, boolean isPermisiongranted) {
        viewModel.getMSharedPreferences().setPreferences(Constants.PREF_LOCATION_SKIP, isSkiped);
        viewModel.getMSharedPreferences().setPreferences(Constants.PREF_LOCATION_ALLOW, isPermisiongranted);
        binding.pager.setCurrentItem(2);
    }

    public void onStartUpAction(boolean startapp) {
        viewModel.getMSharedPreferences().setPreferences(Constants.PREF_APP_STARTED, startapp);
        startActivity(new Intent(this, HomeActivity.class));
        finish();
    }

    private void checkAllPermisionPassed(){
        if(viewModel.getMSharedPreferences().getPreferences(Constants.PREF_APP_STARTED,false)){
            startActivity(new Intent(this, HomeActivity.class));
            finish();
        }
    }

    public RxPermissions getRxPermissions() {
        return rxPermissions;
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
