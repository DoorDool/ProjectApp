package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
    }

    public void Click_on_register (View v) {

        EditText gebruikersinvoer = findViewById(R.id.gebruikersnaam_edit);
        EditText wachtwoordinvoer = findViewById(R.id.wachtwoord_edit);
        EditText wachtwoord2invoer = findViewById(R.id.wachtwoord2_edit);
        EditText rekeningnummerinvoer = findViewById(R.id.rekeningnummer_edit);

        String gebruikersnaam = gebruikersinvoer.getText().toString();
        String wachtwoord = wachtwoordinvoer.getText().toString();
        String wachtwoord2 = wachtwoord2invoer.getText().toString();
        String rekeningnummer = rekeningnummerinvoer.getText().toString();

        // TO DO
        // kijken of wachtwoord al bestaat
        // hash maken van wachtwoord

        // als beide wachtwoorden hetzelfde zijn
        if (wachtwoord.equals(wachtwoord2)) {
            UsersPost post = new UsersPost(RegisterActivity.this);
            post.postUsers(RegisterActivity.this, gebruikersnaam, wachtwoord, rekeningnummer);
        }
        Intent intent = new Intent(RegisterActivity.this, MenuActivity.class);
        startActivity(intent);
    }


}
