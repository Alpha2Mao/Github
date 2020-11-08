package com.example.mvvmgithub.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.mvvmgithub.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDataBase extends RoomDatabase {
    private static final String DATABASE_NAME = "user_db.db";
    private static UserDataBase dataBaseInstance;

    public static synchronized UserDataBase getInstance(Context context){
        if (dataBaseInstance == null){
            dataBaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    UserDataBase.class, DATABASE_NAME)
                    .build();
        }
        return dataBaseInstance;
    }

    public abstract UserDao userDao();
}
