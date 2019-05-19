package com.lilliemountain.reportcard;

import android.content.Context;
import android.content.SharedPreferences;

public class ReportCardManager {

    private static final String PREF_NAME = "com.lilliemountain.reportcard";

    private static ReportCardManager sInstance;
    private final SharedPreferences mPref;

    private ReportCardManager(Context context) {
        mPref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }

    public static synchronized void initializeInstance(Context context) {
        if (sInstance == null) {
            sInstance = new ReportCardManager(context);
        }
    }

    public static synchronized ReportCardManager getInstance() {
        if (sInstance == null) {
            throw new IllegalStateException(ReportCardManager.class.getSimpleName() +
                    " is not initialized, call initializeInstance(..) method first.");
        }
        return sInstance;
    }

    public void setValue(String key,String value) {
        mPref.edit()
                .putString(key, value)
                .commit();
    }
    public void deleteValue(String key) {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public String getValue(String key) {
        return mPref.getString(key, "");
    }

    public void remove(String key) {
        mPref.edit()
                .remove(key)
                .commit();
    }

    public boolean clear() {
        return mPref.edit()
                .clear()
                .commit();
    }
}