package com.festivality.conferenceapp.features.profile.view;

import android.arch.lifecycle.Observer;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.app.base.views.fragment.BaseViewModelFragment;
import com.festivality.conferenceapp.data.model.AttendeeDetail.AttendeeDetail;
import com.festivality.conferenceapp.databinding.FragmentUserProfileBinding;
import com.festivality.conferenceapp.features.home.view.fragment.DefaultFragment;
import com.festivality.conferenceapp.features.profile.viewmodel.ProfileViewModel;

public class ProfileFragment extends BaseViewModelFragment<FragmentUserProfileBinding, ProfileViewModel> {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    public static final String URL = "url";
    public static final String USER_ID = "userid";
    private DefaultFragment.OnFragmentInteractionListener mListener;
    // TODO: Rename and change types of parameters
    private String mUrl;
    private String mUserID;
    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param url Parameter 1.
     * @param userid Parameter 2.
     * @return A new instance of fragment SettingsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String url, String userid) {
        ProfileFragment fragment = new ProfileFragment();
        Bundle args = new Bundle();
        args.putString(URL, url);
        args.putString(USER_ID, userid);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mUrl = getArguments().getString(URL);
            mUserID = getArguments().getString(USER_ID);
        }
    }

    @Override
    protected Class<ProfileViewModel> getViewModelClass() {
        return ProfileViewModel.class;
    }

    @Override
    protected int getLayout() {
        return R.layout.fragment_user_profile;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof DefaultFragment.OnFragmentInteractionListener) {
            mListener = (DefaultFragment.OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.getMAttendeeDetail().observe(this, new Observer<AttendeeDetail>() {
            @Override
            public void onChanged(@Nullable AttendeeDetail attendeeDetail) {
                binding.setProfileItem(viewModel);
            }
        });
        viewModel.initUser(mUserID);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public boolean onBackPressed() {
        Log.i("Anil"," backPressed ProfileFragment ");
        return super.onBackPressed();
    }
}
