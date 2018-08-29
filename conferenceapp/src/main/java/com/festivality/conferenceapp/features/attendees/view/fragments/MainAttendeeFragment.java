package com.festivality.conferenceapp.features.attendees.view.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.features.attendee.view.AttendeeDetailFragment;
import com.festivality.conferenceapp.features.home.view.HomeActivity;
import com.festivality.conferenceapp.helper.ui.FragmentProvider;

import lombok.Getter;
import lombok.Setter;

@Getter
public class MainAttendeeFragment extends Fragment implements FragmentProvider{
    private TabLayout tabs;
    private Toolbar toolbar;
    private View list_fragment_container;
    private View detail_fragment_container;
    private boolean isDetail_shown = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_attendee_parent, container, false);
        // Inflate the layout for this fragment
        toolbar =  view.findViewById(R.id.toolbar);
        ((HomeActivity) getActivity()).setSupportActionBar(toolbar);
        ((HomeActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.title_home));
        list_fragment_container =  view.findViewById(R.id.list_fragment_container);
        detail_fragment_container =  view.findViewById(R.id.detail_fragment_container);
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
        Fragment childFragment = AttendeesFragment.newInstance(((HomeActivity)getActivity()).getViewModel().getUSER_LIST_URL(), 1,this);;
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.list_fragment_container, childFragment);
        transaction.commit();
    }

    public void showListFragment(){
        detail_fragment_container.setVisibility(View.GONE);
        list_fragment_container.setVisibility(View.VISIBLE);
        isDetail_shown = false;
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showFragment(int id,String extra) {
        try
        {
            isDetail_shown = true;
            list_fragment_container.setVisibility(View.GONE);
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            Fragment childFragment = AttendeeDetailFragment.newInstance(((HomeActivity)getActivity()).getViewModel().getUSER_LIST_URL(), extra);;
            transaction.replace(R.id.detail_fragment_container, childFragment);
            transaction.commit();
            detail_fragment_container.setVisibility(View.VISIBLE);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

}
