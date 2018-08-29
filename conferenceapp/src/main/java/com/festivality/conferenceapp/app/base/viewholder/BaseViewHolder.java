package com.festivality.conferenceapp.app.base.viewholder;

import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;



public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{

    public static View getView(@NonNull ViewGroup parent, @LayoutRes int layoutRes) {
        return LayoutInflater.from(parent.getContext()).inflate(layoutRes, parent, false);
    }

    protected BaseViewHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void bind(@NonNull T t);
}
