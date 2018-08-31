package com.festivality.conferenceapp.features.home.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;


import com.festivality.conferenceapp.features.attendees.view.fragments.MainAttendeeFragment;
import com.festivality.conferenceapp.features.home.view.activity.HomeActivity;
import com.festivality.conferenceapp.features.home.view.fragment.DefaultFragment;
import com.festivality.conferenceapp.features.profile.view.ProfileFragment;
import com.festivality.conferenceapp.helper.injection.qualifier.ActivityContext;
import com.festivality.conferenceapp.helper.injection.qualifier.ActivityFragmentManager;

import javax.inject.Inject;



public class HomePagerAdapter extends FragmentStatePagerAdapter {
    private Context context;
    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();
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
                return ProfileFragment.newInstance(((HomeActivity)context).getViewModel().getUSER_LIST_URL());
                default:
                return DefaultFragment.newInstance("", "");
        }
    }

    @Override
    public int getCount() {
        return 2;
    }

    // Register the fragment when the item is instantiated
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        Fragment fragment = (Fragment) super.instantiateItem(container, position);
        registeredFragments.put(position, fragment);
        return fragment;
    }

    // Unregister when the item is inactive
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    // Returns the fragment for the position (if instantiated)
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }
}