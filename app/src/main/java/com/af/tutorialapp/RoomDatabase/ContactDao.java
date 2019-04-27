package com.af.tutorialapp.RoomDatabase;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.af.tutorialapp.RoomDatabase.entity.Contact;

import java.util.List;

import io.reactivex.Flowable;

@Dao
public interface ContactDao {

 @Insert
 public long CreateContact(Contact contact);

 @Update
 public void UpdateContact(Contact contact);

 @Delete
 public void Delete_contact(Contact contact);

 @Query("select * from contact")
 Flowable <List<Contact>> getContactRx();

 @Query("select * from contact")
 List<Contact> getContact();
 @Query("select * from contact where id=:id")
 public Contact getContact(int id);

}
