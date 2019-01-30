package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

public class MakeGroupActivity extends AppCompatActivity implements UsersHelper.Callback, GroupsHelper.Callback, ParticipatorsHelper.Callback {

    String username;
    ArrayList<User> UsersList;
    ArrayList<Group> GroupsList;
    ArrayList<Participator> ParticipatorsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_group);

        UsersHelper helper = new UsersHelper(this);
        helper.getUser(this);
        GroupsHelper groups = new GroupsHelper(this);
        groups.getGroup(this);
        ParticipatorsHelper participators = new ParticipatorsHelper(this);
        participators.getParticipators(this);

        // dit is variabel
        username = StartActivity.username;
    }

    @Override
    public void gotUsers(ArrayList<User> UsersList) {
        this.UsersList = UsersList;
    }

    @Override
    public void gotUsersError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotGroups(ArrayList<Group> GroupsList) {
        this.GroupsList = GroupsList;
    }

    @Override
    public void gotGroupsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotParticipators(ArrayList<Participator> ParticipatorsList) {
        this.ParticipatorsList = ParticipatorsList;
    }

    @Override
    public void gotParticipatorsError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void Click_on_plus(View v) {

        // get values from editTexts
        EditText groupsname_input = findViewById(R.id.groupsname_input);
        EditText participators_input = findViewById(R.id.participator_input);
        String groupsname = groupsname_input.getText().toString();
        String participator = participators_input.getText().toString();

        // boolean for add participator
        Boolean permission = false;
        // boolean for exists groupsname already
        Boolean groupDouble = false;
        // boolean for is participator in group already
        Boolean inGroup = false;
        // boolean for empty name
        Boolean emptyName = false;
        // boolean for empty groupsname
        Boolean emptyGroupsname = false;
        // iterate over all groups
        for (Group group: GroupsList) {
            // if groupsname eguals other groupname
            if (group.getGroupsname().equals(groupsname)) {
                groupDouble = true;
            }
        }
        // if user fill not a participator in editText
        if (participator.equals("")) {
            emptyName = true;
            participators_input.setError("Geen naam ingevuld");
        }
        // if user fill not a groupsname in editText
        if (groupsname.equals("")) {
            emptyGroupsname = true;
            groupsname_input.setError("Geen naam ingevuld");
        }
        // iterate over all participators in group
        for (Participator part: ParticipatorsList ) {
            // is participator equals participator who will be add
            if (part.getParticipator().equals(participator)) {
                inGroup = true;
            }
        }
        // iterate over all users
        for (User user: UsersList) {
            // participator may not be user
            if (participator.equals(user.getUsername()) && !groupDouble && !inGroup && !emptyGroupsname && !emptyName) {
                permission = true;
                // make new group
                GroupsPost post = new GroupsPost(MakeGroupActivity.this);
                post.postGroup(MakeGroupActivity.this, groupsname, participator);
                // reset editText
                participators_input.setText("");
            }
        }

        // error messages
        if (groupDouble) {
            groupsname_input.setError("Groepsnaam bestaat al");
        }
        else if (inGroup) {
            participators_input.setError("Deelnemer zit al in groep");
        }
        else if (!permission && !emptyName) {
            participators_input.setError("Gebruikersnaam bestaat niet");
        }
    }


    public void Click_on_make(View v) {

        // get values from editTexts
        EditText groupsname_input = findViewById(R.id.groupsname_input);
        String groupsname = groupsname_input.getText().toString();
        String participator = username;
        // boolean for check if groupname already exists
        boolean groupDouble = false;
        // iterate over all groups
        Boolean emptyName = false;
        if (groupsname.equals("")) {
            emptyName = true;
            groupsname_input.setError("Geen naam ingevoerd");
        }
        // iterate over all groups
        for (Group group: GroupsList) {
            // if groupsname equals other groupsname
            if (group.getGroupsname().equals(groupsname)) {
                groupsname_input.setError("Groepsnaam bestaat al");
                groupDouble = true;
            }
        }
        // add group with username
        if (!groupDouble && !emptyName) {
            GroupsPost post2 = new GroupsPost(MakeGroupActivity.this);
            post2.postGroup(MakeGroupActivity.this, groupsname, participator);

            Intent intent = new Intent(MakeGroupActivity.this, MenuActivity.class);
            startActivity(intent);
        }
    }

}
