package com.festivality.conferenceapp.features.intro.view.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.features.home.view.HomeActivity;

public class StartupFragment extends Fragment {
    /**
     * The fragment argument representing the section number for this
     * fragment.
     */
    private static final String ARG_SECTION_NUMBER = "section_number";

    public StartupFragment() {
    }

    /**
     * Returns a new instance of this fragment for the given section
     * number.
     */
    public static StartupFragment newInstance(int sectionNumber) {
        StartupFragment fragment = new StartupFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SECTION_NUMBER, sectionNumber);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_intro_start, container, false);
        rootView.findViewById(R.id.startup_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoHome();
            }
        });
        return rootView;
    }

    public void gotoHome() {
        startActivity(new Intent(getActivity(), HomeActivity.class));
        getActivity().finish();
    }
}