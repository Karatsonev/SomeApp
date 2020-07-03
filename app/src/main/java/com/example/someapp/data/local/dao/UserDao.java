package com.example.someapp.data.local.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.example.someapp.data.local.models.User;

import java.util.List;
import io.reactivex.Flowable;
import io.reactivex.Maybe;

@Dao
public interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertUser(User user);

    @Update()
    void updateUser(User user);

    @Delete
    void deleteUser(User user);

    @Query("select * from users where id = :id")
    Maybe<User> getUser(int id);

    @Query("select * from users")
    Flowable<List<User>> getUsers();

    @Query("delete from users where name = :name")
    void deleteUserByName(String name);

    @Query("delete from users")
    void deleteAll();
}
