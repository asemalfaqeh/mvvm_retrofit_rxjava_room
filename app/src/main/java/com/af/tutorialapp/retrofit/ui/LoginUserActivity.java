package com.af.tutorialapp.retrofit.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.af.tutorialapp.R;
import com.af.tutorialapp.retrofit.model.user;
import com.af.tutorialapp.retrofit.service.ApiEndPoints;
import com.af.tutorialapp.retrofit.service.RetrofitInstance;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginUserActivity extends AppCompatActivity {

    private EditText et_email,et_password;
    private TextView result;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_user);

         et_email = findViewById(R.id.editText);
         et_password = findViewById(R.id.editText2);
         result = findViewById(R.id.textView2);
         button = findViewById(R.id.button2);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // login();
                Intent intent = new Intent(LoginUserActivity.this,AlbumsActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void login(){

        ApiEndPoints apiEndPoints = RetrofitInstance.getRetrofitInstance();

        user data = new user();
        data.setEmail(et_email.getText().toString());
        data.setPassword(et_password.getText().toString());

        Call<user> get_user  = apiEndPoints.postUser(data);
        get_user.enqueue(new Callback<user>() {
            @Override
            public void onResponse(Call<user> call, Response<user> response) {

                user user = response.body();
                result.setText(String.valueOf(user.getId()));

            }

            @Override
            public void onFailure(Call<user> call, Throwable t) {
                Toast.makeText(LoginUserActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });

    }

}
