package com.example.dorin.projectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    // method for navigation to register screen
    public void Click_on_register(View v) {
        Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    // method for navigation after clicked on login button
    public void Click_on_login(View v) {
        Intent intent = new Intent(StartActivity.this, menuActivity.class);
        startActivity(intent);
    }
}
