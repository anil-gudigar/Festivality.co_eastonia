package com.festivality.conferenceapp.features.attendees.viewmodel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.app.base.viewmodel.ItemModel;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.helper.ui.GlideApp;

import jp.wasabeef.glide.transformations.RoundedCornersTransformation;
import lombok.Getter;

@Getter
public class AttendeeItemViewModel extends ItemModel<Attendee> {

    private Attendee mAttendee;

    @Override
    public void setItem(Attendee item) {
        mAttendee = item;
    }

    public String getImageUrl() {
        // The URL will usually come from a model (i.e Profile)
        return mAttendee.getMedia().first().getFiles().getVariations().getSmall();
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
