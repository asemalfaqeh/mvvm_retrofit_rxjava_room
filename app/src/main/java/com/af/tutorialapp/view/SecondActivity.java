package com.af.tutorialapp.view;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.af.tutorialapp.R;
import com.af.tutorialapp.model.DataInfo;

import java.util.Objects;

public class SecondActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        TextView textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        DataInfo dataInfo = Objects.requireNonNull(bundle).getParcelable("Key");
        assert dataInfo != null;
        textView.setText(String.format("%s %s %s", dataInfo.getName(), dataInfo.getName(),dataInfo.getEmail()));

    }
}
