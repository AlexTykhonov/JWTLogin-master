package com.example.semen.jwtlogin;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.example.semen.jwtlogin.model.DaoMaster;
import com.example.semen.jwtlogin.model.DaoSession;


public class App extends Application {
    public static SharedPreferences sharedPreferences;
    private static Context context;
    private static DaoSession mDaoSession;



    @Override
    public void onCreate() {
        super.onCreate();
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        context = this;
        mDaoSession = new DaoMaster(
                new DaoMaster.DevOpenHelper(this, "pet.db").getWritableDb()).newSession();

    }

    public static DaoSession getmDaoSession() {
        return mDaoSession;
    }

    public static Context getContext() {
        return context;
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
