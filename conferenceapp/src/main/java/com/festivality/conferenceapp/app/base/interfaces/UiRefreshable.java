package com.festivality.conferenceapp.app.base.interfaces;



public interface UiRefreshable extends Refreshable{
    void doneRefresh();
    void refreshWithUi();
    void refreshWithUi(int delay);
    void setRefreshEnabled(boolean enabled);
}

