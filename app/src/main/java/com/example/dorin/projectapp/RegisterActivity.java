package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class RegisterActivity extends AppCompatActivity implements UsersHelper.Callback {

    ArrayList<User> UsersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

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


    public void Click_on_register (View v) {

        EditText username_input = findViewById(R.id.gebruikersnaam_edit);
        EditText password_input = findViewById(R.id.wachtwoord_edit);
        EditText password2_input = findViewById(R.id.wachtwoord2_edit);
        EditText number_input = findViewById(R.id.rekeningnummer_edit);

        String username = username_input.getText().toString();
        String password = password_input.getText().toString();
        String password2 = password2_input.getText().toString();
        String number = number_input.getText().toString();

        // TO DO
        // hash maken van wachtwoord

        // als beide wachtwoorden hetzelfde zijn en gebruikersnaam nog niet bestaat
        Boolean permission = true;
        if (password.equals(password2)) {
            for (User user: UsersList) {
                if (user.getUsername().equals(username)) {
                    permission = false;
                    String message = "Gebruikersnaam bestaat al";
                    Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
                }
            }
            if (permission) {
                UsersPost post = new UsersPost(RegisterActivity.this);
                post.postUsers(RegisterActivity.this, username, password, number);
                Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
                intent.putExtra("username", username);
                startActivity(intent);
            }
        }
        else {
            String message = "Wachtwoorden komen niet overeen";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }

    }


}
