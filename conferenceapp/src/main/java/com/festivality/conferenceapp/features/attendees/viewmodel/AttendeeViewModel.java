package com.festivality.conferenceapp.features.attendees.viewmodel;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.app.Constants;
import com.festivality.conferenceapp.app.base.viewmodel.ListViewModel;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.remote.repository.AttendeeRepo;
import com.festivality.conferenceapp.data.remote.repository.AttendeesRepo;
import com.festivality.conferenceapp.features.attendees.view.adapters.AttendeeRecycleViewAdapter;
import com.festivality.conferenceapp.helper.BundleConstant;
import com.festivality.conferenceapp.helper.CustomSharedPreferences;
import com.festivality.conferenceapp.helper.ui.FragmentProvider;

import java.util.ArrayList;

import javax.inject.Inject;

import io.realm.RealmResults;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttendeeViewModel extends ListViewModel<Attendee, AttendeeRecycleViewAdapter> {

    private final AttendeesRepo mAttendeesRepo;
    private final AttendeeRepo mAttendeeRepo;
    private final CustomSharedPreferences mCustomSharedPreferences;
    private String user_list_url;
    private FragmentProvider provider;

    @Inject
    public AttendeeViewModel(AttendeesRepo attendeesRepo, AttendeeRepo attendeeRepo,@NonNull CustomSharedPreferences sharedPreferences) {
        mAttendeesRepo = attendeesRepo;
        mAttendeeRepo = attendeeRepo;
        mCustomSharedPreferences = sharedPreferences;
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
        provider.showFragment(1, item.getId());
    }

    public void searchByName(String Query) {
        String userID = mCustomSharedPreferences.getPreferences(Constants.PREF_USER_ID,"");
        if (!mAttendeeRepo.getDao().getAll(false).getData().isEmpty()) {
            if (!Query.isEmpty()) {
                RealmResults<Attendee> filteredMembers = mAttendeeRepo.getDao().getAll(false).getData().where().contains("customFields.fullName", Query).notEqualTo("id",userID).findAll();
                setData(filteredMembers, true);
                Log.i("Anil", "mAttendeeRepo filteredMembers Result Count " + filteredMembers.size());
            } else{
                RealmResults<Attendee> allMembers = mAttendeeRepo.getDao().getAll(false).getData().where().notEqualTo("id", userID).findAll();;
                setData(allMembers, true);
                Log.i("Anil", "mAttendeeRepo allMembers Result Count " + allMembers.size());
            }
        } else {
            Log.i("Anil", "Empty attendees");
        }
    }

    public void getFeatured() {
        String userID = mCustomSharedPreferences.getPreferences(Constants.PREF_USER_ID,"");
        if (!mAttendeeRepo.getDao().getAll(false).getData().isEmpty()) {
                RealmResults<Attendee> filteredMembers = mAttendeeRepo.getDao().getAll(false).getData().where().contains("isFeatured", "1").notEqualTo("id",userID).findAll();
                setData(filteredMembers, true);
                Log.i("Anil", "mAttendeeRepo getFeatured Result Count " + filteredMembers.size());
        } else {
            Log.i("Anil", "Empty attendees");
        }
    }

}
