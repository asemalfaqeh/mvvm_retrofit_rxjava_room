package com.af.tutorialapp.RoomDatabase.ui;

import android.app.Application;
import android.arch.lifecycle.MutableLiveData;
import android.arch.persistence.room.Room;
import android.util.Log;
import android.widget.Toast;

import com.af.tutorialapp.RoomDatabase.MyDatabase;
import com.af.tutorialapp.RoomDatabase.entity.Contact;

import java.util.ArrayList;
import java.util.List;
import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.schedulers.Schedulers;

public class ContactRepositery {

    private Application application;
    private MyDatabase myDatabase;
    private CompositeDisposable compositeDisposable = new CompositeDisposable();
    private MutableLiveData<List<Contact>> mutableLiveData = new MutableLiveData<>();

    public ContactRepositery(Application application){
        this.application = application;
        myDatabase = Room.databaseBuilder(application.getApplicationContext(),MyDatabase.class,"db_app")
                .allowMainThreadQueries().build();
        getContactRx();
    }


      void getContactRx(){

        List<Contact> list = new ArrayList<>();

        compositeDisposable.add((myDatabase.getContacts().getContactRx()).subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread()).subscribe(contacts -> {

                    mutableLiveData.postValue(contacts);

                    for (int i = 0 ; i < list.size() ; i ++){
                        Log.e("E: " , list.get(i).getName());
                    }
                }, throwable -> Toast.makeText(application.getApplicationContext(), throwable.getMessage()  + "", Toast.LENGTH_SHORT).show()));

    }

      void createContact(Contact contact){
        compositeDisposable.add(
                Completable.fromAction(() -> {

                    long i = myDatabase.getContacts().CreateContact(contact);

                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Log.e("new user inserted : " , true + "");
                            }
                            @Override
                            public void onError(Throwable e) {
                            }
                        }));
    }

      void updateContact(Contact contact){
        compositeDisposable.add(
                Completable.fromAction(() -> {
                    myDatabase.getContacts().UpdateContact(contact);
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Log.e("Success Update : " , true + "");
                            }
                            @Override
                            public void onError(Throwable e) {
                            }
                        }));
    }

    void deleteContact(Contact contact){
        compositeDisposable.add(
                Completable.fromAction(() -> {
                    myDatabase.getContacts().UpdateContact(contact);
                }).observeOn(AndroidSchedulers.mainThread()).subscribeOn(Schedulers.io())
                        .subscribeWith(new DisposableCompletableObserver() {
                            @Override
                            public void onComplete() {
                                Log.e("Success Delete Contact:" , true + "");
                            }
                            @Override
                            public void onError(Throwable e) {
                            }
                        }));
    }

     MutableLiveData<List<Contact>> getMutableLiveData(){
        return mutableLiveData;
    }

     void clear(){
        compositeDisposable.clear();
    }

}
