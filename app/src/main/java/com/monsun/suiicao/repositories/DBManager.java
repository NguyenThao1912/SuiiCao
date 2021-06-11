package com.monsun.suiicao.repositories;

import android.content.Context;
import android.content.SharedPreferences;

import com.monsun.suiicao.Utils.CommonUtils;

public class DBManager {
    private static DBManager instance = null;


    private SharedPreferences sharedPreferences;
    private DBManager(Context context)
    {
        sharedPreferences = context.getSharedPreferences(CommonUtils.MY_PREFERENCE,Context.MODE_PRIVATE);
    }
    public static DBManager getInstance(Context context)
    {
        if (instance == null)
        {
            instance = new DBManager(context);
        }
        return instance;
    }
    public SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }


}
