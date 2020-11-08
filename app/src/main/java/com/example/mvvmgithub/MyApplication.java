package com.example.mvvmgithub;

import android.app.Application;

import com.example.mvvmgithub.api.Api;
import com.example.mvvmgithub.api.RetrofitClient;
import com.example.mvvmgithub.db.UserDataBase;

public class MyApplication extends Application {
    private static UserDataBase userDataBase;
    private static Api api;


    @Override
    public void onCreate() {
        super.onCreate();
        userDataBase = UserDataBase.getInstance(this);
        api = RetrofitClient.getInstance().getApi();
    }


    public static Api getApi(){
        return api;
    }

    public static UserDataBase getUserDataBase(){
        return userDataBase;
    }
}
