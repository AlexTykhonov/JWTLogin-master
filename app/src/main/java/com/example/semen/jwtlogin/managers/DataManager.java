package com.example.semen.jwtlogin.managers;

import android.content.Context;

import com.example.semen.jwtlogin.App;
import com.example.semen.jwtlogin.api.Api;
import com.example.semen.jwtlogin.controller.Controller;
import com.example.semen.jwtlogin.model.DaoSession;

public class DataManager {
    private static DataManager INSTANCE = null;

    private Context mContext;
    private PreferencesManager mPreferencesManager;
    private Api api;
    private DaoSession daoSession;

    public DataManager() {
        mContext = App.getContext();
        mPreferencesManager = new PreferencesManager();
        api = Controller.createService(Api.class);
        daoSession = App.getmDaoSession();
    }

    public Api getApi() {
        return api;
    }

    public static DataManager getInstance(){
        if (INSTANCE == null) {
            INSTANCE = new DataManager();
        }
        return INSTANCE;
    }

    public PreferencesManager getPreferencesManager() {
        return mPreferencesManager;
    }

    public Context getContext() {
        return mContext;
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
