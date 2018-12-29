package com.example.semen.jwtlogin.managers;

import android.content.SharedPreferences;

import com.example.semen.jwtlogin.App;

public class PreferencesManager {
    private SharedPreferences sharedPreferences;

    public PreferencesManager() {
        sharedPreferences = App.getSharedPreferences();
    }

    public void setAuthToken(String token) {
        SharedPreferences.Editor prefEditor = sharedPreferences.edit();
        prefEditor.putString("token",token);
        prefEditor.apply();
    }

    public String getAuthToken(){
        return sharedPreferences.getString("token","null");
    }
}
