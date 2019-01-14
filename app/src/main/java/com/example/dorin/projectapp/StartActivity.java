package com.example.dorin.projectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity implements UsersHelper.Callback {

    ArrayList<User> UsersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Intent intent = getIntent();
        UsersHelper helper = new UsersHelper(this);
        helper.getUser(this);
    }

    @Override
    public void gotUsers(ArrayList<User> UsersList) {
        this.UsersList = UsersList;
    }

    @Override
    public void gotUsersError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    // method for navigation to register screen
    public void Click_on_register(View v) {
        Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    // method for navigation after clicked on login button
    public void Click_on_login(View v) {

        EditText username_login = findViewById(R.id.login_username);
        EditText password_login = findViewById(R.id.login_password);

        String username = username_login.getText().toString();
        String password = password_login.getText().toString();

        Log.i("users", "1234 user is" + UsersList);

        for (User user : UsersList) {
            if (user.getUsername().equals(username)) {
                if (user.getPassword().equals(password)) {
                    Intent intent = new Intent(StartActivity.this, MenuActivity.class);
                    intent.putExtra(username, "username");
                    startActivity(intent);
                } else {
                    String message = "Wachtwoord is incorrect";
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                }
            } else {
                String message = "Gebruikersnaam onbekend";
                Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
            }
        }
    }



        // To do
        // hash
        // check of gebruikersnaam bestaat
        // check of wachtwoord correct is
        // inloggen

}
