package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MakeGroupActivity extends AppCompatActivity implements UsersHelper.Callback {

    String username;
    ArrayList<User> UsersList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_group);

        UsersHelper helper = new UsersHelper(this);
        helper.getUser(this);

        Intent intent = getIntent();
        username = StartActivity.username;
        //username = intent.getStringExtra("username");
        Log.i("test", "username onCreate 1234 is " + username);
    }

    @Override
    public void gotUsers(ArrayList<User> UsersList) {
        this.UsersList = UsersList;
    }

    @Override
    public void gotUsersError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void Click_on_plus(View v) {

        EditText groupsname_input = findViewById(R.id.groupsname_input);
        EditText participators_input = findViewById(R.id.participator_input);

        String groupsname = groupsname_input.getText().toString();
        String participator = participators_input.getText().toString();

        Boolean permission = false;
        for (User user: UsersList) {
            if (participator.equals(user.getUsername())) {
                permission = true;
                GroupsPost post = new GroupsPost(MakeGroupActivity.this);
                post.postGroup(MakeGroupActivity.this, groupsname, participator);
                participators_input.setText("");
            }
        }
        if (!permission) {
            String message = "Gebruikersnaam bestaat niet";
            Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        }
    }


    public void Click_on_make(View v) {

        EditText groupsname_input = findViewById(R.id.groupsname_input);
        String groupsname = groupsname_input.getText().toString();
        String participator = username;
        Log.i("test", "username is 1234 " + username);

        GroupsPost post2 = new GroupsPost(MakeGroupActivity.this);
        post2.postGroup(MakeGroupActivity.this, groupsname, participator);


        Intent intent = new Intent(MakeGroupActivity.this, MenuActivity.class);
        startActivity(intent);
    }

}
