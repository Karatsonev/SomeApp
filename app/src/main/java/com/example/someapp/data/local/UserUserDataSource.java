package com.example.someapp.data.local;

import com.example.someapp.data.local.models.User;
import com.example.someapp.data.local.dao.UserDao;
import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

public class UserUserDataSource implements ILocalUserData {

    private UserDao userDao;

    public UserUserDataSource(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public void insert(User item) {
        userDao.insertUser(item);
    }

    @Override
    public void update(User item) {
        userDao.updateUser(item);
    }

    @Override
    public void delete(User item) {
        userDao.deleteUser(item);
    }

    @Override
    public void deleteByName(String name){userDao.deleteUserByName(name);}

    @Override
    public void deleteAll() { userDao.deleteAll();}

    @Override
    public Maybe<User> get(int id) {
        return userDao.getUser(id);
    }

    @Override
    public Flowable<List<User>> getList() {
        return userDao.getUsers();
    }
}
