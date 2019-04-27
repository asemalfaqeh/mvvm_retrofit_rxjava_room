package com.af.tutorialapp.RoomDatabase.ui;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;
import com.af.tutorialapp.RoomDatabase.entity.Contact;
import java.util.List;

public class ContactViewModel extends AndroidViewModel {

    private ContactRepositery contactRepositery;

    public ContactViewModel(@NonNull Application application) {
        super(application);
        contactRepositery = new ContactRepositery(application);
    }

    LiveData<List<Contact>> getContacts(){
        return contactRepositery.getMutableLiveData();
    }

    public void create(Contact contact){ contactRepositery.createContact(contact); }

    public void update(Contact contact){
        contactRepositery.updateContact(contact);
    }

    public void delete(Contact contact){
        contactRepositery.deleteContact(contact);
    }

    public void clear(){
        contactRepositery.clear();
    }

}
