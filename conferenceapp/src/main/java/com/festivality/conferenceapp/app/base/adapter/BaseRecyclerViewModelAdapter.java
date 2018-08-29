package com.festivality.conferenceapp.app.base.adapter;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;


import com.festivality.conferenceapp.app.base.interfaces.OnActionItemClickListener;
import com.festivality.conferenceapp.app.base.interfaces.OnItemClickListener;
import com.festivality.conferenceapp.app.base.viewmodel.ItemModel;
import com.festivality.conferenceapp.helper.injection.qualifier.ActivityContext;
import com.festivality.conferenceapp.helper.ui.NavigatorHelper;
import android.util.Log;

import java.util.List;



public abstract class BaseRecyclerViewModelAdapter<T, VIEW_MODEL_T extends ItemModel<T>>
        extends RecyclerView.Adapter<BaseRecyclerViewModelAdapter.BaseItemViewHolder<T, VIEW_MODEL_T>> {


    @Nullable
    protected List<T> data;
    @Nullable

    protected OnItemClickListener<T> itemClickListener;
    @Nullable

    protected OnActionItemClickListener<T> actionMenuClickListener;
    @NonNull
    protected LayoutInflater mInflater;

    @NonNull
    protected NavigatorHelper navigatorHelper;

    @NonNull
    protected final Context mContext;

    public BaseRecyclerViewModelAdapter(@ActivityContext Context context, NavigatorHelper navigatorHelper) {
        setHasStableIds(true);
        this.mContext = context;
        this.navigatorHelper = navigatorHelper;
        this.mInflater = LayoutInflater.from(context);
        setHasStableIds(true);
    }

    public void setData(@Nullable List<T> newData) {
        if (this.data != newData) {
            this.data = newData;
        }
        if(null != data){
            notifyItemRangeChanged(0, data.size());
        }else{
            notifyDataSetChanged();
        }
    }

    @Override
    public final void onBindViewHolder(BaseItemViewHolder<T, VIEW_MODEL_T> holder, int position) {
       // Log.i("Anil"," Data :"+data.get(position).toString());
        holder.setItem(data.get(position));
    }

    @Nullable
    protected T getItem(int adapterPosition) {
        if (data != null) {
            if (adapterPosition >= 0 && adapterPosition < data.size()) {
                return data.get(adapterPosition);
            }
        }
        return null;
    }

    @Nullable
    public List<T> getData() {
        return data;
    }

    @Nullable
    public OnItemClickListener<T> getItemClickListener() {
        return itemClickListener;
    }

    public void setItemClickListener(@Nullable OnItemClickListener<T> itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Nullable
    public OnActionItemClickListener<T> getActionMenuClickListener() {
        return actionMenuClickListener;
    }

    public void setActionMenuClickListener(@Nullable OnActionItemClickListener<T> actionMenuClickListener) {
        this.actionMenuClickListener = actionMenuClickListener;
    }

    @NonNull
    public LayoutInflater getmInflater() {
        return mInflater;
    }

    public void setmInflater(@NonNull LayoutInflater mInflater) {
        this.mInflater = mInflater;
    }

    @NonNull
    public NavigatorHelper getNavigatorHelper() {
        return navigatorHelper;
    }

    public void setNavigatorHelper(@NonNull NavigatorHelper navigatorHelper) {
        this.navigatorHelper = navigatorHelper;
    }

    @NonNull
    public Context getmContext() {
        return mContext;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (data != null) {
            return data.size();
        }
        return 0;
    }

    public static class BaseItemViewHolder<I, VT extends ItemModel<I>>
            extends RecyclerView.ViewHolder {

        protected final VT viewModel;
        private final ViewDataBinding binding;

        public BaseItemViewHolder(View itemView, ViewDataBinding binding, VT viewModel) {
            super(itemView);
            this.binding = binding;
            this.viewModel = viewModel;
        }

        void setItem(I item) {
            viewModel.setItem(item);
            binding.invalidateAll();
            binding.executePendingBindings();
        }
    }
}
