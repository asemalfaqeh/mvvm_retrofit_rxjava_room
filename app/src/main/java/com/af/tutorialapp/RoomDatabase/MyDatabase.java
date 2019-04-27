package com.af.tutorialapp.RoomDatabase;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.af.tutorialapp.RoomDatabase.entity.Contact;

@Database(entities = {Contact.class},version = 1,exportSchema = false)
public abstract class MyDatabase extends RoomDatabase {

   public abstract ContactDao getContacts();

}
