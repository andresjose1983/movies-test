package com.example.andres.movies_test;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

/**
 * Created by andres on 27/01/17.
 */

public class App extends Application {

    private static App mInstance;


    public static App getInstance() {
        return mInstance;
    }

    @Override
    public void onCreate() {
        mInstance = this;
    }

    /**
     * Check internet connection
     *
     * @return
     */
    public static boolean checkInternetConnection() {

        ConnectivityManager connectivityManager = (ConnectivityManager) mInstance.getSystemService(
                Context.CONNECTIVITY_SERVICE);

        NetworkInfo i = connectivityManager.getActiveNetworkInfo();
        if (i == null)
            return false;
        if (!i.isConnected())
            return false;
        return i.isAvailable();
    }
}
