package com.example.someapp.viewmodels;

import android.app.Application;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.someapp.data.local.models.User;
import com.example.someapp.repositories.UserDataRepository;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivityViewModel extends AndroidViewModel {

    private UserDataRepository userDataRepository;
    private CompositeDisposable disposable = new CompositeDisposable();
    private MutableLiveData<List<User>> mUsers;
    private MutableLiveData<List<User>> mUsersList;

    public MainActivityViewModel(@NonNull Application application) {
        super(application);

        userDataRepository = new UserDataRepository(application);
    }

    public LiveData<List<User>> observeUsers(){
       if(mUsers == null){
           mUsers = new MutableLiveData<>();
           loadUsers();
       }
       return mUsers;
    }

    public LiveData<List<User>> observeUsersList(){
        if(mUsersList == null){
            mUsersList = new MutableLiveData<>();
            loadUsersList();
        }
        return  mUsersList;
    }

    public void insertUser(User user){
        Completable.fromRunnable(() -> userDataRepository.insertUser(user))
          .subscribeOn(Schedulers.io())
          .subscribe();
    }

    public void deleteAllUsers(){
        Completable.fromRunnable(() -> userDataRepository.deleteAllUsers())
          .subscribeOn(Schedulers.io())
          .subscribe();

    }

    private void loadUsers() {
            disposable.add(userDataRepository.getUsers()
            .subscribeOn(Schedulers.io())
            .subscribe(
                    users -> mUsers.postValue(users),
                    error -> Log.d("loadUsers", "loadUsers: " + error.getMessage())));
    }

    private void loadUsersList(){
        disposable.add(userDataRepository.getUsers()
        .subscribeOn(Schedulers.io())
        .subscribe(
                users -> mUsersList.postValue(users),
                error -> Log.d("loadUsersList", "loadUsersList: " + error.getMessage())));
    }


    @Override
    protected void onCleared() {
        super.onCleared();
        disposable.clear();
    }
}
