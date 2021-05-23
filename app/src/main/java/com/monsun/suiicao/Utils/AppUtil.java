package com.monsun.suiicao.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;

import java.util.Random;

public class AppUtil {
    public static boolean isNetWorkAvaiable(Context context){
        if(context == null)
        {
            return false;
        }
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(connectivityManager == null) {
            return false;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q)
        {
            Network network = connectivityManager.getActiveNetwork();
            if(network == null)
                return false;
            NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(network);
            return networkCapabilities != null
                    && networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) //wifi connection
                    || networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR); //celluner data use
        }
        else {
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isConnected();
        }
    }
    public static String generateRoomID(String a, String b){
        if (a.compareTo(b) > 0){
            return new StringBuilder(a).append(b).toString();
        }
        else if (a.compareTo(b) < 0){
            return new StringBuilder(b).append(a).toString();
        }
        else
            return new StringBuilder("Chat your self ? ").append(new Random().nextInt()).toString();
    }

}
