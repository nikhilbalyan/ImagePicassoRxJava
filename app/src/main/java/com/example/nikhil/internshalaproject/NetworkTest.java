package com.example.nikhil.internshalaproject;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

// test the network connection
public class NetworkTest {

    public static boolean CheckConnection(final Context context) {

        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if (isConnected)
            return true;
        else return false;
    }



}
