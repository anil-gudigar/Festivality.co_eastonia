package com.festivality.conferenceapp.features.attendees.view.adapters;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.festivality.conferenceapp.R;
import com.festivality.conferenceapp.app.base.adapter.BaseRecyclerViewModelAdapter;
import com.festivality.conferenceapp.data.model.Attendees.Attendee;
import com.festivality.conferenceapp.databinding.FragmentAttendeeitemBinding;
import com.festivality.conferenceapp.features.attendees.viewmodel.AttendeeItemViewModel;
import com.festivality.conferenceapp.helper.injection.qualifier.ActivityContext;
import com.festivality.conferenceapp.helper.ui.NavigatorHelper;

import javax.inject.Inject;

public class AttendeeRecycleViewAdapter  extends BaseRecyclerViewModelAdapter<Attendee, AttendeeItemViewModel> {

    @Inject
    public AttendeeRecycleViewAdapter(@ActivityContext Context context, NavigatorHelper navigatorHelper) {
        super(context, navigatorHelper);
    }

    @NonNull
    @Override
    public BaseItemViewHolder<Attendee, AttendeeItemViewModel> onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_attendeeitem, parent, false);

        AttendeeItemViewModel viewModel = new AttendeeItemViewModel();
        FragmentAttendeeitemBinding binding = FragmentAttendeeitemBinding.bind(itemView);
        binding.setAttendeeItem(viewModel);
        DocumentItemViewHolder holder = new DocumentItemViewHolder(itemView,binding,viewModel);
        itemView.setOnClickListener(v -> {
            if (itemClickListener != null) {
                Attendee item = getItem(holder.getAdapterPosition());
                if (item != null) {
                    itemClickListener.onItemClick(holder.itemView, item);
                }
            }
        });
        return holder;
    }

    static class DocumentItemViewHolder
            extends BaseItemViewHolder<Attendee, AttendeeItemViewModel> {

        public DocumentItemViewHolder(View itemView, ViewDataBinding binding,
                                      AttendeeItemViewModel viewModel) {
            super(itemView, binding, viewModel);
        }
    }
}
