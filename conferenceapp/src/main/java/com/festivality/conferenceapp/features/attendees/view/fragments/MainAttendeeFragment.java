package com.festivality.conferenceapp.features.attendees.view.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.features.home.view.HomeActivity;

public class MainAttendeeFragment extends Fragment {
    private TabLayout tabs;
    private Toolbar toolbar;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendee_parent, container, false);
        // Inflate the layout for this fragment
        toolbar =  view.findViewById(R.id.toolbar);
        ((HomeActivity) getActivity()).setSupportActionBar(toolbar);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_home));
        return view;
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static MainAttendeeFragment newInstance() {
        MainAttendeeFragment fragment = new MainAttendeeFragment();
        return fragment;
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Fragment childFragment = AttendeesFragment.newInstance(((HomeActivity)getActivity()).getViewModel().getUSER_LIST_URL(), 1);;
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.child_fragment_container, childFragment).commit();
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

}
