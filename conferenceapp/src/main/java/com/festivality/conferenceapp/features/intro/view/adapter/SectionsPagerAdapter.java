package com.festivality.conferenceapp.features.intro.view.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.util.SparseArray;
import android.view.ViewGroup;

import com.festivality.conferenceapp.features.intro.view.fragments.LocationFragment;
import com.festivality.conferenceapp.features.intro.view.fragments.NotificationFragment;
import com.festivality.conferenceapp.features.intro.view.fragments.StartupFragment;
import com.festivality.conferenceapp.helper.injection.qualifier.ActivityContext;
import com.festivality.conferenceapp.helper.injection.qualifier.ActivityFragmentManager;

import javax.inject.Inject;

public class SectionsPagerAdapter extends FragmentPagerAdapter {
    private Context context;
    private SparseArray<Fragment> registeredFragments = new SparseArray<Fragment>();

    @Inject
    public SectionsPagerAdapter(@ActivityContext Context context, @ActivityFragmentManager FragmentManager fm) {
        super(fm);
        this.context = context;
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
