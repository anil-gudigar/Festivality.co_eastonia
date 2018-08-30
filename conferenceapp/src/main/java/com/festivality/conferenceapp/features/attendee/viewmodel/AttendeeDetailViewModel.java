package com.festivality.conferenceapp.features.attendee.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.databinding.BindingAdapter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.app.base.viewmodel.BaseViewModel;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.data.remote.repository.AttendeeRepo;
import com.festivality.conferenceapp.features.attendee.view.AttendeeDetailFragment;
import com.festivality.conferenceapp.helper.ui.GlideApp;

import javax.inject.Inject;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class AttendeeDetailViewModel extends BaseViewModel {

    private String user = null;
    private final AttendeeRepo mAttendeeRepo;
    private String user_url;
    private MutableLiveData<Attendee> mAttendee;


    @Inject
    AttendeeDetailViewModel(AttendeeRepo attendeeRepo) {
        this.mAttendeeRepo = attendeeRepo;
        mAttendee = new MutableLiveData<>();
    }

    public void initUser(String userLogin) {
        Log.i("Anil"," initUser :"+user_url+" user :"+user+"  userLogin :"+userLogin);
        if(mAttendeeRepo.getDao().getReceivedAttendeeById(userLogin).getData().isEmpty()){
            this.user = userLogin;
            new Handler(Looper.myLooper()).postDelayed(() -> {
                execute(true, mAttendeeRepo.getUserDetail(user_url+"/"+user), user -> {
                    Log.i("Anil"," user Details:"+user.toString());
                    mAttendeeRepo.getDao().addOrUpdateAsync(user);
                    mAttendee.setValue(user);
                });
            }, 300);
        }else{
            Log.i(TAG, "Realm Result Count " + mAttendeeRepo.getDao().getReceivedAttendeeById(userLogin).getData().first().toString());
            mAttendee.setValue(mAttendeeRepo.getDao().getReceivedAttendeeById(userLogin).getData().first());
        }
    }

    @Override
    protected void onFirsTimeUiCreate(@Nullable Bundle bundle) {
        if (null != bundle.getString(AttendeeDetailFragment.URL)) {
            user_url = bundle.getString(AttendeeDetailFragment.URL);
            user = bundle.getString(AttendeeDetailFragment.USER_ID);
        }
    }

    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return mAttendee.getValue().getMedia().first().getFiles().getVariations().getOriginal();
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

