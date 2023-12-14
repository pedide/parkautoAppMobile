package com.edidebs.parkautoapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.edidebs.parkautoapp.R;

public class DashboadActivity extends AppCompatActivity {
TextView email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboad);

        email = findViewById(R.id.email);
        Intent intent = getIntent();
        if(intent.getExtras()!=null){
            String passedEmail = intent.getStringExtra("data");
            email.setText("Welcome"+passedEmail);

        }
    }
}