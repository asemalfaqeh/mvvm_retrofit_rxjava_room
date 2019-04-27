package com.af.tutorialapp.view;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.af.tutorialapp.R;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxTextView;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

public class RxBindingDemo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_binding_demo);

        TextView view_text = findViewById(R.id.view_text);
        Button clear_button = findViewById(R.id.clear_button);
        EditText editText = findViewById(R.id.input_text);

       Disposable disposable = RxTextView.textChanges(editText)
                .subscribe(new Consumer<CharSequence>() {
                    @Override
                    public void accept(CharSequence charSequence) throws Exception {
                       view_text.setText(charSequence);
                    }
                });

        Disposable disposable1 = RxView.clicks(clear_button)
                .subscribe(new Consumer<Object>() {
                    @Override
                    public void accept(Object o) throws Exception {
                       editText.setText("");
                       view_text.setText(" ");
                    }
                });


    }

    @Override
    public void onDestroy(){
        super.onDestroy();

    }

}



