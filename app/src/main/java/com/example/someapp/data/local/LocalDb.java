package com.example.someapp.data.local;

import android.app.Application;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.someapp.data.local.models.User;
import com.example.someapp.data.local.dao.UserDao;

@Database(entities = {User.class}, version = 4, exportSchema = false)
public abstract class LocalDb extends RoomDatabase {

    public abstract UserDao userDao();
    private static volatile LocalDb INSTANCE;

    public static LocalDb getInstance(Application context){

        if(INSTANCE == null){
          synchronized (LocalDb.class){
             INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                 LocalDb.class,"MyDatabase.db")
                 .fallbackToDestructiveMigration()
                 .build();
            }
        }
        return INSTANCE;
    }

}
