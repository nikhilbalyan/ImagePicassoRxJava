package com.example.nikhil.internshalaproject.service;

import android.content.Context;

import java.util.HashMap;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;

public interface PresenterInteractor {

    void loadRxData(Context activityView);
    void rxUnSubscribe();

}
