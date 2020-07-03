package com.example.someapp.repositories;

import android.app.Application;
import com.example.someapp.data.local.ILocalUserData;
import com.example.someapp.data.local.LocalDb;
import com.example.someapp.data.local.UserUserDataSource;
import com.example.someapp.data.local.dao.UserDao;
import com.example.someapp.data.local.models.User;
import java.util.List;
import io.reactivex.Flowable;

public class UserDataRepository {

    private ILocalUserData iLocalUserData;

    public UserDataRepository(Application application) {
        UserDao userDao = LocalDb.getInstance(application).userDao();
        iLocalUserData = new UserUserDataSource(userDao);
    }

    public void getUser(int id){
        iLocalUserData.get(id);
    }

    public Flowable<List<User>>  getUsers(){
        return iLocalUserData.getList();
    }

    public void deleteUser(User user){ iLocalUserData.delete(user); }

    public void deleteUserByName(String name){ iLocalUserData.deleteByName(name); }

    public void deleteAllUsers(){ iLocalUserData.deleteAll();}

    public void insertUser(User user){
        iLocalUserData.insert(user);
    }

    public void updateUser(User user){
        iLocalUserData.update(user);
    }

}
