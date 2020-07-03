package com.example.someapp.utils;

import android.app.Application;

import com.example.someapp.data.local.ILocalUserData;
import com.example.someapp.data.local.LocalDb;
import com.example.someapp.data.local.UserUserDataSource;

public class Injection {

    public static ILocalUserData provideUserDataSource(Application application){
        LocalDb localDb = LocalDb.getInstance(application);
        return new UserUserDataSource(localDb.userDao());
    }
}
