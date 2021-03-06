package com.example.nikhil.internshalaproject.service;

import android.app.Application;

import com.example.nikhil.internshalaproject.R;
// activity instance associated with application for giving the service object to the calling class
public class RxApplication extends Application {

    private NetworkService networkService;
    public RxApplication() {

    }
    @Override
    public void onCreate() {
        super.onCreate();
        String url = getString(R.string.api_base_url);
        networkService = new NetworkService(url);

    }

    public NetworkService getNetworkService(){
        return networkService;
    }
}