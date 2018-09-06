package com.nonawax.myels;

import android.app.Application;
import android.util.Log;

import com.google.android.gms.ads.MobileAds;


public class MyEls extends Application {
    private static final String TAG = "MyELS";
    @Override
    public void onCreate() {
        super.onCreate();
        MobileAds.initialize(this, "ca-app-pub-9716462781973217~8018798230");
        Log.i(TAG, "My ELS Application START");
    }
}
