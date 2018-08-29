package com.festivality.conferenceapp.features.home.view;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


import com.festivality.conferenceapp.features.attendees.view.fragments.AttendeesFragment;
import com.festivality.conferenceapp.features.attendees.view.fragments.MainAttendeeFragment;
import com.festivality.conferenceapp.features.profile.view.ProfileFragment;
import com.festivality.conferenceapp.helper.injection.qualifier.ActivityContext;
import com.festivality.conferenceapp.helper.injection.qualifier.ActivityFragmentManager;

import javax.inject.Inject;



public class HomePagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    @Inject
    public HomePagerAdapter(@ActivityContext Context context, @ActivityFragmentManager FragmentManager fm) {
        super(fm);
        this.context = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return MainAttendeeFragment.newInstance();
            case 1:
                return ProfileFragment.newInstance(((HomeActivity)context).getViewModel().getUSER_LIST_URL(), "44779");
                default:
                return DefaultFragment.newInstance("", "");
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}