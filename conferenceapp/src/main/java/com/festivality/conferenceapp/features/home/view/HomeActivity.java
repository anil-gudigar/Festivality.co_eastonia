package com.festivality.conferenceapp.features.home.view;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.app.base.views.acivity.BaseViewModelActivity;
import com.festivality.conferenceapp.databinding.ActivityHomeBinding;
import com.festivality.conferenceapp.features.home.viewmodel.HomeViewModel;
import com.festivality.conferenceapp.helper.ui.BottomNavigationViewHelper;

import javax.inject.Inject;

public class HomeActivity  extends BaseViewModelActivity<ActivityHomeBinding, HomeViewModel> implements BottomNavigationView.OnNavigationItemSelectedListener, ViewPager.OnPageChangeListener,DefaultFragment.OnFragmentInteractionListener{
    @Inject
    HomePagerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    public int getLayout() {
        return R.layout.activity_home;
    }

    @Override
    protected boolean canBack() {
        return false;
    }

    private void init() {
        binding.navigation.setOnNavigationItemSelectedListener(this);
        binding.pager.setOffscreenPageLimit(2);
        binding.pager.setAdapter(adapter);
        binding.pager.addOnPageChangeListener(this);
        binding.pager.setCurrentItem(viewModel.getCurrentTab());
        onPageSelected(viewModel.getCurrentTab());
        BottomNavigationViewHelper.disableShiftMode(binding.navigation);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.navigation_home:
                binding.pager.setCurrentItem(0);
                return true;
            case R.id.navigation_user:
                binding.pager.setCurrentItem(1);
                return true;
        }
        return false;
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
