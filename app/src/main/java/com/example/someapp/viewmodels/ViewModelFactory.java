package com.example.someapp.viewmodels;

import android.app.Application;
import android.content.Context;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

public class ViewModelFactory implements ViewModelProvider.Factory {

    private Context context;

    public ViewModelFactory(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        if(modelClass.isAssignableFrom(MainActivityViewModel.class)){
            return (T) new MainActivityViewModel((Application) context.getApplicationContext());
        }
        throw new IllegalArgumentException("Unknown ViewModel class!");
    }
}
