package com.example.mvvmgithub.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.mvvmgithub.MyApplication;
import com.example.mvvmgithub.db.UserDao;
import com.example.mvvmgithub.db.UserDataBase;
import com.example.mvvmgithub.model.User;
import com.example.mvvmgithub.repository.UserRepository;

public class UserViewModel extends AndroidViewModel {
    private LiveData<User> user;
    private UserRepository userRepository;
    private String userName = "Alpha2Mao";

    public UserViewModel(Application application) {
        super(application);
        UserDataBase dataBase = MyApplication.getUserDataBase();
        UserDao userDao = dataBase.userDao();
        userRepository = new UserRepository(userDao, MyApplication.getApi());
        user = userRepository.getUser(userName);
    }

    public LiveData<User> getUser(){
        return user;
    }

    public void refresh(){
        userRepository.refresh(userName);
    }
}
