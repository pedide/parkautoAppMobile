package com.edidebs.parkautoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.edidebs.parkautoapp.R;
import com.edidebs.parkautoapp.entity.LoginResponse;
import com.edidebs.parkautoapp.entity.UserEntity;
import com.edidebs.parkautoapp.retrofit.RetrofitLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
  EditText editTextTextFistname,editTextLastname,editTextTextEmailAddress,editTextTextPassword;
  Button buttonRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        //Register credentials to connect
        editTextTextFistname = findViewById(R.id.editTextTextFistname);
        editTextLastname = findViewById(R.id.editTextLastname);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);
        editTextTextPassword = findViewById(R.id.editTextTextPassword);
        buttonRegister = findViewById(R.id.buttonRegister);

        //Btn user registered
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(editTextTextFistname.getText().toString()) ||
                TextUtils.isEmpty(editTextLastname.getText().toString()) ||
                TextUtils.isEmpty(editTextTextEmailAddress.getText().toString())||
                TextUtils.isEmpty(editTextTextPassword.getText().toString())
                ){
                    Toast.makeText(RegisterActivity.this,"Firstname / Lastname / Email / Password are required",Toast.LENGTH_LONG).show();
                }else {
                    //Proceed register
                    register();
                }
            }
        });
    }

    private void register() {
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(editTextTextFistname.getText().toString());
        userEntity.setLastName(editTextLastname.getText().toString());
        userEntity.setEmail(editTextTextEmailAddress.getText().toString());
        userEntity.setPassword(editTextTextPassword.getText().toString());

        Call<LoginResponse> registerResponseCall = RetrofitLogin.getUserRepository().userRegister(userEntity);
        registerResponseCall.enqueue(
                new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(response.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, "Register successful", Toast.LENGTH_LONG).show();
                            //Redirect to Login Activity
                            LoginResponse loginResponse = response.body();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(RegisterActivity.this, LoginActivity.class)
                                            .putExtra("data",loginResponse.getEmail()));

                                }
                            },700);
                        }else{
                            Toast.makeText(RegisterActivity.this,"Register failled !",Toast.LENGTH_LONG).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        //Toast.makeText(RegisterActivity.this,"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();
                    }
                }
        );

    }
}