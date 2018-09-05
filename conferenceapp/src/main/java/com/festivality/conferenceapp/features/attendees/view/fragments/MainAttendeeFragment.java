package com.festivality.conferenceapp.features.attendees.view.fragments;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.features.attendee.view.AttendeeDetailFragment;
import com.festivality.conferenceapp.features.home.view.activity.HomeActivity;
import com.festivality.conferenceapp.helper.ui.FragmentProvider;

import lombok.Getter;

@Getter
public class MainAttendeeFragment extends Fragment implements FragmentProvider,SearchView.OnQueryTextListener{
    private TabLayout tabs;
    private Toolbar toolbar;
    private View list_fragment_container;
    private View detail_fragment_container;
    private boolean isDetail_shown = false;
    private Fragment atendeeDetailFragment;
    private Fragment attendeesFragment;
    private MenuItem searchItem;
    private MenuItem sortItem;
    private MenuItem filterItem;
    private SearchView searchView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
        setHasOptionsMenu(true);
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
        attendeesFragment = AttendeesFragment.newInstance(((HomeActivity)getActivity()).getViewModel().getUSER_LIST_URL(), 1,this);;
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.replace(R.id.list_fragment_container, attendeesFragment);
        transaction.commit();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        MenuInflater menuInflater = getActivity().getMenuInflater();
        menuInflater.inflate(R.menu.dashboard, menu);

        searchItem = menu.findItem(R.id.action_search);
        sortItem = menu.findItem(R.id.action_sort);
        filterItem = menu.findItem(R.id.action_filter);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchItem != null) {
            searchView = (SearchView) searchItem.getActionView();
        }
        if (searchView != null) {
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            EditText editText = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
            editText.setHintTextColor(getResources().getColor(android.R.color.white));
            editText.setTextColor(getResources().getColor(android.R.color.white));
            searchView.setOnQueryTextListener(this);
            searchView.setIconified(false);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                // Not implemented here
                return false;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    public void showListFragment(){
        detail_fragment_container.setVisibility(View.GONE);
        FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
        transaction.remove(atendeeDetailFragment);
        list_fragment_container.setVisibility(View.VISIBLE);
        isDetail_shown = false;
        searchItem.setVisible(true);
        sortItem.setVisible(true);
        filterItem.setVisible(true);
    }

    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void showFragment(int id,String extra) {
        try
        {
            searchItem.setVisible(false);
            sortItem.setVisible(false);
            filterItem.setVisible(false);
            FragmentTransaction transaction = getChildFragmentManager().beginTransaction();
            atendeeDetailFragment = AttendeeDetailFragment.newInstance(((HomeActivity)getActivity()).getViewModel().getUSER_LIST_URL(), extra);;
            transaction.replace(R.id.detail_fragment_container, atendeeDetailFragment);
            transaction.commit();
            detail_fragment_container.setVisibility(View.VISIBLE);
            list_fragment_container.setVisibility(View.GONE);
            isDetail_shown = true;
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        Log.i("Anil","Search Query : "+query);
        ((AttendeesFragment)attendeesFragment).searchByName(query);
        searchView.clearFocus();
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Log.i("Anil","Search QueryTextChange : "+newText);
        ((AttendeesFragment)attendeesFragment).searchByName(newText);
        return true;
    }
}
