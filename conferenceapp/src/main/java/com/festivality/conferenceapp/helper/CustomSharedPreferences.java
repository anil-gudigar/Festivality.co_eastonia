package com.festivality.conferenceapp.helper;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Anil Gudigar on 09/23/2018.
 */

public class CustomSharedPreferences {
    private static final String PREFS_NAME = "CustomSharedPreferences_PREFS_NAME";
    private String mPrefName;
    private SharedPreferences mSharedPre;
    private SharedPreferences.Editor mEditor;
    private Context mContext;
    private static CustomSharedPreferences mInstance;

    public static CustomSharedPreferences getInstance(Context context, String prefName) {
        if(mInstance != null && !mInstance.mPrefName.equals(prefName)) {
            mInstance = null;
        }

        if(mInstance == null) {
            mInstance = new CustomSharedPreferences(context.getApplicationContext(), prefName);
        }

        return mInstance;
    }

    public static CustomSharedPreferences getInstance(Context context) {
        return getInstance(context, "CustomSharedPreferences_PREFS_NAME");
    }

    @SuppressLint({"CommitPrefEdits"})
    public CustomSharedPreferences(Context context, String prefName) {
        this.mPrefName = "CustomSharedPreferences_PREFS_NAME";
        this.mContext = context;
        this.mPrefName = prefName;
        if(null != context) {
            if(this.mSharedPre == null) {
                this.mSharedPre = context.getSharedPreferences(this.mPrefName, 0);
            }

            this.mEditor = this.mSharedPre.edit();
        } else {
            this.mEditor = null;
            this.mSharedPre = null;
        }

    }

    public CustomSharedPreferences(Context context) {
        this(context, "CustomSharedPreferences_PREFS_NAME");
    }

    @SuppressLint({"CommitPrefEdits"})
    private void refresh() {
        if(null != this.mContext) {
            this.mSharedPre = this.mContext.getSharedPreferences(this.mPrefName, 0);
            this.mEditor = this.mSharedPre.edit();
        } else {
            this.mEditor = null;
            this.mSharedPre = null;
        }

    }

    public synchronized void setPreferences(String preName, String value) {
        this.refresh();
        if(null != this.mEditor) {
            this.mEditor.putString(preName, value);
            this.mEditor.commit();
        }

    }

    public String getPreferences(String preName, String defaultValue) {
        this.refresh();
        return null != this.mSharedPre?this.mSharedPre.getString(preName, defaultValue):null;
    }

    public synchronized void setPreferences(String preName, boolean value) {
        if(null != this.mEditor) {
            this.mEditor.putBoolean(preName, value);
            this.mEditor.commit();
        }

    }

    public boolean getPreferences(String preName, boolean defaultValue) {
        return null != this.mSharedPre?this.mSharedPre.getBoolean(preName, defaultValue):defaultValue;
    }

    public synchronized void setPreferences(String preName, int value) {
        if(null != this.mEditor) {
            this.mEditor.putInt(preName, value);
            this.mEditor.commit();
        }

    }

    public int getPreferences(String preName, int defaultValue) {
        return null != this.mSharedPre?this.mSharedPre.getInt(preName, defaultValue):0;
    }

    public synchronized void setPreferences(String preName, long value) {
        if(null != this.mEditor) {
            this.mEditor.putLong(preName, value);
            this.mEditor.commit();
        }

    }

    public long getPreferences(String preName, long defaultValue) {
        return null != this.mSharedPre?this.mSharedPre.getLong(preName, defaultValue):0L;
    }

    public synchronized void setPreferences(String preName, float value) {
        if(null != this.mEditor) {
            this.mEditor.putFloat(preName, value);
            this.mEditor.commit();
        }

    }

    public float getPreferences(String preName, float defaultValue) {
        return null != this.mSharedPre?this.mSharedPre.getFloat(preName, defaultValue):0.0F;
    }
}
