package com.af.tutorialapp.RoomDatabase.ui;

import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.af.tutorialapp.R;

public class Main2Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ContactViewModel contactViewModel = ViewModelProviders.of(this).get(ContactViewModel.class);

        contactViewModel.getContacts().observe(Main2Activity.this, contacts -> {

        });

    }

}
