package com.festivality.conferenceapp.features.intro.view.fragments;

import android.Manifest;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.features.intro.view.activity.IntroActivity;

public class LocationFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public LocationFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static LocationFragment newInstance(int sectionNumber) {
        LocationFragment fragment = new LocationFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_intro_location, container, false);
        rootView.findViewById(R.id.location_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLocationPermissions();
            }
        });
        rootView.findViewById(R.id.location_skip_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((IntroActivity)getActivity()).onLocationPageAction(true,false);
            }
        });
        return rootView;
    }

    private void openLocationPermissions(){
        ((IntroActivity) getActivity()).getRxPermissions()
                .request(Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION)
                .subscribe(granted -> {
                    if (granted) { // Always true pre-M
                        ((IntroActivity)getActivity()).onLocationPageAction(false,true);
                    } else {
                        ((IntroActivity)getActivity()).onLocationPageAction(false,false);
                    }
                });
    }
}
