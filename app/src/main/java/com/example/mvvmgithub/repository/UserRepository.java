package com.example.mvvmgithub.repository;

import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mvvmgithub.api.Api;
import com.example.mvvmgithub.db.UserDao;
import com.example.mvvmgithub.model.User;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    private String TAG = UserRepository.class.getSimpleName();
    private UserDao userDao;
    private Api api;


    public UserRepository(UserDao userDao, Api api){
        this.userDao = userDao;
        this.api = api;
    }

    public LiveData<User> getUser(final String name){
        refresh(name);
        return userDao.getUsersByName(name);
    }


    public void refresh(String userName){
        api.getUser(userName).enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.body() != null){
                    insertUser(response.body());
                    Log.i(TAG, "The response body is " + response.body());
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                Log.i(TAG, "The refresh is error");
            }
        });
    }


    private void insertUser(final User user){
        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                userDao.insertUser(user);
                Log.i(TAG, "The user is " + user.toString());
            }
        });
    }
}
