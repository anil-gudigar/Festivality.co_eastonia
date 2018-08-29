package com.festivality.conferenceapp.features.intro.view;

import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.features.intro.view.fragments.LocationFragment;
import com.festivality.conferenceapp.features.intro.view.fragments.NotificationFragment;
import com.festivality.conferenceapp.features.intro.view.fragments.StartupFragment;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class IntroActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;
    private RxPermissions rxPermissions = null;
    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        rxPermissions = new RxPermissions(this);
        setContentView(R.layout.activity_intro);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

    }




    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {

            switch (position){
                case 0:
                    return NotificationFragment.newInstance(position + 1);
                case 1:
                    return LocationFragment.newInstance(position + 1);
                case 2:
                    return StartupFragment.newInstance(position + 1);
            }
            return NotificationFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }
    }

    public void movetoLocationPage(boolean isSkiped){
        if(isSkiped){

        }
        mViewPager.setCurrentItem(1);
    }

    public void movetoStartUpPage(boolean isSkiped){
        if(isSkiped){

        }
        mViewPager.setCurrentItem(2);
    }


    public RxPermissions getRxPermissions() {
        return rxPermissions;
    }
}
