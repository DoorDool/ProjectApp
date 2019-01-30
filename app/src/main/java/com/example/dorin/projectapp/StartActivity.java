package com.example.dorin.projectapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity implements UsersHelper.Callback {

    public static String username;
    public static String groupsname;
    public static String categoriename;
    ArrayList<User> UsersList;
    Boolean server = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        UsersHelper helper = new UsersHelper(this);
        helper.getUser(this);
    }

    @Override
    public void gotUsers(ArrayList<User> UsersList) {
        this.UsersList = UsersList;
    }

    @Override
    public void gotUsersError(String message) {
        Toast.makeText(this, "Server doet het niet", Toast.LENGTH_SHORT).show();
        server = false;
    }

    // method for navigation to register screen
    public void Click_on_register(View v) {

        if (server) {
            Intent intent = new Intent(StartActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(this, "Server doet het niet", Toast.LENGTH_SHORT).show();
        }
    }

    // method for navigation after clicked on login button
    public void Click_on_login(View v) {

        if (server) {
            // get values form editTexts
            EditText username_login = findViewById(R.id.login_username);
            EditText password_login = findViewById(R.id.login_password);

            username = username_login.getText().toString();
            String password = password_login.getText().toString();

            // boolean for user exists
            Boolean exists = false;
            // iterate over all users
            for (User user : UsersList) {
                // if user exists
                if (user.getUsername().equals(username)) {
                    exists = true;
                    // if password equals password of user
                    if (user.getPassword().equals(password)) {
                        Intent intent = new Intent(StartActivity.this, MenuActivity.class);
                        startActivity(intent);
                        break;
                    } else {
                        // error message
                        password_login.setError("Wachtwoord is incorrect");
                        break;
                    }
                }
            }
            if (!exists) {
                // error message
                username_login.setError("Gebruikersnaam onbekend");
            }
        }
        else {
            Toast.makeText(this, "Server doet het niet", Toast.LENGTH_SHORT).show();
        }
    }

    // method for backpressed
    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }

}
