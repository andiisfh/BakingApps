package com.bakingapps.utils;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by andiisfh on 18/09/17.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);
    }
}
