package com.example.someapp.data.local.models;

import androidx.annotation.Nullable;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;

@Entity(tableName = "users")
public class User {

    @Expose
    @ColumnInfo(name = "id")
    @PrimaryKey(autoGenerate = true)
    private int id;

    @Nullable
    @Expose
    @ColumnInfo(name = "name")
    private String name;

    @Expose
    @ColumnInfo(name = "email")
    private String email;

    @Expose
    @ColumnInfo(name = "password")
    private String password;


//    @Ignore
//    public User(int id, @Nullable String name) {
//        this.id = id;
//        this.name = name;
//    }

    public User(@Nullable String  name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Nullable
    public String getName() {
        return name;
    }

    public void setName(@Nullable String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
