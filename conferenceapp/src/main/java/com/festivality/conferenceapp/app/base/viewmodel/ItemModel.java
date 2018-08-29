package com.festivality.conferenceapp.app.base.viewmodel;

import android.arch.lifecycle.ViewModel;



public abstract class ItemModel<ITEM_T>  extends ViewModel {
    public ItemModel() {
    }
    public abstract void setItem(ITEM_T item);
}
