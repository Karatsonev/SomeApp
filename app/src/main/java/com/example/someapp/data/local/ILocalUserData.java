package com.example.someapp.data.local;


import com.example.someapp.data.local.models.User;

import java.util.List;

import io.reactivex.Flowable;
import io.reactivex.Maybe;

public interface ILocalUserData {

    void insert(User item);
    void update(User item);
    void delete(User item);
    void deleteByName(String name);
    void deleteAll();
    Maybe<User> get(int id);
    Flowable<List<User>>  getList();

}
