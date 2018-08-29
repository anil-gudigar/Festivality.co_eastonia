package com.festivality.conferenceapp.features.attendees.viewmodel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.app.base.viewmodel.ListViewModel;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.remote.repository.AttendeesRepo;
import com.festivality.conferenceapp.features.attendees.view.adapters.AttendeeRecycleViewAdapter;
import com.festivality.conferenceapp.features.home.view.HomeActivity;
import com.festivality.conferenceapp.helper.BundleConstant;
import com.festivality.conferenceapp.helper.ui.FragmentProvider;

import java.util.ArrayList;

import javax.inject.Inject;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttendeeViewModel extends ListViewModel<Attendee, AttendeeRecycleViewAdapter> {

    private final AttendeesRepo mAttendeesRepo;
    private String user_list_url;
    private FragmentProvider provider;
    @Inject
    public AttendeeViewModel(AttendeesRepo attendeesRepo) {
        mAttendeesRepo = attendeesRepo;
        setTitle(getString(R.string.title_home));
    }

    @Override
    protected void onFirsTimeUiCreate(@Nullable Bundle bundle) {
        if (null != bundle.getString(BundleConstant.EXTRA)) {
            user_list_url = bundle.getString(BundleConstant.EXTRA);
        }
    }

    @Override
    protected void callApi(int page, OnCallApiDone<Attendee> onCallApiDone) {
        execute(true, mAttendeesRepo.getUserList(user_list_url), userlist -> {
            ArrayList<Attendee> attendees = new ArrayList<>();
            if (null != userlist && null != userlist.getAttendees()) {
                Log.i("Anil", "Result Count " + userlist.getAttendees().size());
                mAttendeesRepo.getDao().addOrUpdateAsync(userlist);
                attendees.addAll(userlist.getAttendees());
                onCallApiDone.onDone(page, true, attendees);
            }
        });
    }

    @Override
    public void initAdapter(@NonNull AttendeeRecycleViewAdapter adapter) {
        super.initAdapter(adapter);
        if (mAttendeesRepo.getDao().getAll(true).getData().isEmpty()) {
            setData(new ArrayList<>(), true);
            refresh(100);
        } else {
            Log.i(TAG, "Realm Result Count " + mAttendeesRepo.getDao().getAll(true).getData().size());
            ArrayList<Attendee> entries = new ArrayList<>();
            entries.addAll(mAttendeesRepo.getDao().getAll(true).getData().get(0).getAttendees());
            setData(entries, true);
        }
    }

    @Override
    public void onActionItemClick(View v, Attendee item) {

    }

    @Override
    public void onItemClick(View v, Attendee item) {
        provider.showFragment(1,item.getId());
    }


}
