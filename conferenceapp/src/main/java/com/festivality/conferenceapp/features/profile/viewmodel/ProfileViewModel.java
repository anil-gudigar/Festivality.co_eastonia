package com.festivality.conferenceapp.features.profile.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.app.base.viewmodel.BaseViewModel;
import com.festivality.conferenceapp.data.model.AttendeeDetail.AttendeeDetail;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.remote.repository.AttendeeDetailRepo;
import com.festivality.conferenceapp.features.profile.view.ProfileFragment;
import com.festivality.conferenceapp.helper.ui.GlideApp;

import java.util.ArrayList;

import javax.inject.Inject;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import lombok.Getter;

@Getter
public class ProfileViewModel extends BaseViewModel {

    private String user = null;
    private final AttendeeDetailRepo mAttendeeDetailRepo;
    private String user_url;
    private MutableLiveData<AttendeeDetail> mAttendeeDetail;
    private MutableLiveData<Attendee> mAttendee;


    @Inject
    ProfileViewModel(AttendeeDetailRepo attendeeDetailRepo) {
        this.mAttendeeDetailRepo = attendeeDetailRepo;
        mAttendeeDetail = new MutableLiveData<>();
        mAttendee = new MutableLiveData<>();
    }

    public void initUser(String userLogin) {
        Log.i("Anil"," initUser :"+user_url);
        if (mAttendeeDetailRepo.getDao().getAll(true).getData().isEmpty()) {
            this.user = userLogin;
            new Handler(Looper.myLooper()).postDelayed(() -> {
                execute(true, mAttendeeDetailRepo.getUser(user_url+"/"+user), user -> {
                    Log.i("Anil"," user Details:"+user.toString());
                    mAttendeeDetail.setValue(user);
                    mAttendeeDetailRepo.getDao().addOrUpdateAsync(user);
                    mAttendee.setValue(user.getResponse().first());
                    Log.i("Anil"," message :"+mAttendeeDetail.getValue().getMessage());
                });
            }, 300);
        } else {
            Log.i(TAG, "Realm Result Count " + mAttendeeDetailRepo.getDao().getAll(true).getData().size());
            mAttendeeDetail.setValue(mAttendeeDetailRepo.getDao().getAll(true).getData().first());
            mAttendee.setValue(mAttendeeDetail.getValue().getResponse().first());
        }

    }

    @Override
    protected void onFirsTimeUiCreate(@Nullable Bundle bundle) {
        if (null != bundle.getString(ProfileFragment.URL)) {
            user_url = bundle.getString(ProfileFragment.URL);
            user = bundle.getString(ProfileFragment.USER_ID);
        }
    }

    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return mAttendee.getValue().getMedia().first().getFiles().getDefault_date();
    }


    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        int radius = 30; // corner radius, higher value = more rounded
        int margin = 10; // crop margin, set to 0 for corners with no crop
        GlideApp.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.ic_location)
                .transform(new RoundedCornersTransformation(radius, margin))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(view);
    }

}
