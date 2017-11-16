package com.invoice.aipxperts;

import android.app.Application;

import io.realm.Realm;

/**
 * Created by aipxperts on 15/3/17.
 */

public class MyApplication extends Application {


    @Override
    public void onCreate() {
        super.onCreate();
       // Fabric.with(this, new Crashlytics.Builder().core(new CrashlyticsCore.Builder().disabled(BuildConfig.DEBUG).build()).build());

        Realm.init(this);



    }
}
