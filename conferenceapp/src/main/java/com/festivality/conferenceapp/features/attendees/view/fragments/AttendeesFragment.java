package com.festivality.conferenceapp.features.attendees.view.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.festivality.conferenceapp.app.base.views.fragment.BaseAttendeeRecyclerViewFragment;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.databinding.RefreshRecyclerViewBinding;
import com.festivality.conferenceapp.features.attendees.view.adapters.AttendeeRecycleViewAdapter;
import com.festivality.conferenceapp.features.attendees.viewmodel.AttendeeViewModel;
import com.festivality.conferenceapp.features.home.view.DefaultFragment;
import com.festivality.conferenceapp.helper.BundleConstant;
import com.festivality.conferenceapp.helper.ui.FragmentProvider;

import io.reactivex.annotations.Nullable;

public class AttendeesFragment extends BaseAttendeeRecyclerViewFragment<RefreshRecyclerViewBinding, Attendee, AttendeeRecycleViewAdapter, AttendeeViewModel> {
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;
    @Nullable
    protected AppBarLayout appBar;
    @Nullable
    protected Toolbar toolbar;
    private static FragmentProvider mFragmentProvider;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */

    public AttendeesFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static AttendeesFragment newInstance(String url, int columnCount, FragmentProvider fragmentProvider) {
        mFragmentProvider = fragmentProvider;
        AttendeesFragment fragment = new AttendeesFragment();
        Bundle args = new Bundle();
        args.putString(BundleConstant.EXTRA, url);
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
        setRetainInstance(true);
    }

    @Override
    protected Class<AttendeeViewModel> getViewModelClass() {
        return AttendeeViewModel.class;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.setProvider(mFragmentProvider);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            /*throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");*/
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onBackPressed() {
        Log.i("Anil"," backPressed :"+getChildFragmentManager().getBackStackEntryCount());
        return true;
    }

    /**
     *
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(Attendee item);
        void onListActionFragmentInteraction(Attendee item);
    }

}
