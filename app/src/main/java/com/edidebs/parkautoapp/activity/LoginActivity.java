package com.edidebs.parkautoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.edidebs.parkautoapp.R;
import com.edidebs.parkautoapp.entity.LoginResponse;
import com.edidebs.parkautoapp.entity.UserEntity;
import com.edidebs.parkautoapp.retrofit.RetrofitLogin;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
   EditText eTEmailAddress, eTPassword;
   Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        //Login credentials connect
        eTEmailAddress = findViewById(R.id.eTEmailAddress);
        eTPassword = findViewById(R.id.eTPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(TextUtils.isEmpty(eTEmailAddress.getText().toString()) ||
                        TextUtils.isEmpty(eTPassword.getText().toString())

                ){
                    Toast.makeText(LoginActivity.this,"Email /Password Required ",Toast.LENGTH_LONG).show();

                }else {
                    //Proceed to login
                    login();

                }

            }
        });


        //No login credentials go to register
        TextView btn = findViewById(R.id.textViewSignUp);
        btn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
                    }
                }
        );
    }

    private void login() {
        UserEntity userEntity = new UserEntity();
        userEntity.setEmail(eTEmailAddress.getText().toString());
        userEntity.setPassword(eTPassword.getText().toString());

        Call<LoginResponse> loginResponseCall = RetrofitLogin.getUserRepository().userLogin(userEntity);
        loginResponseCall.enqueue(
                new Callback<LoginResponse>() {
                    @Override
                    public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(LoginActivity.this,"Login successful",Toast.LENGTH_LONG).show();
                            //Redirect to Dashboard Activity
                            LoginResponse loginResponse = response.body();
                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    startActivity(new Intent(LoginActivity.this, DashboadActivity.class)
                                            .putExtra("data",loginResponse.getEmail()));

                                }
                            },700);
                        }else{
                            Toast.makeText(LoginActivity.this,"Login failled !",Toast.LENGTH_LONG).show();
                        }

                    }

                    @Override
                    public void onFailure(Call<LoginResponse> call, Throwable t) {
                        Toast.makeText(LoginActivity.this,"Throwable"+t.getLocalizedMessage(),Toast.LENGTH_LONG).show();

                    }
                }
        );
    }
}