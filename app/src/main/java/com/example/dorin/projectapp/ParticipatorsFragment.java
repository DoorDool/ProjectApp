package com.example.dorin.projectapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ParticipatorsFragment extends Fragment implements ParticipatorsHelper.Callback, UsersHelper.Callback {

    ArrayList<Participator> ParticipatorsList;
    View v;
    Context context;
    ArrayList<User> UsersList;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        v = inflater.inflate(R.layout.fragment_participators, container, false);
        context = getContext();
        ParticipatorsHelper helper = new ParticipatorsHelper(context);
        helper.getParticipators(this);
        UsersHelper users = new UsersHelper(context);
        users.getUser(this);

        // method for adding a participator
        FloatingActionButton addParticipatorButton = v.findViewById(R.id.addParticipatorButton);
        addParticipatorButton.setOnClickListener(new View.OnClickListener() {
            //gebruikersnaam mag niet de gebruiker zijn
            // gebruikersnaam mag niet al in de groep zitten
            public void onClick(View view) {
                // get name of participator
                EditText participatorText = v.findViewById(R.id.textParticipator);
                String participator = participatorText.getText().toString();
                // if editTexts is not empty
                if (!participator.equals("")) {
                    // boolean for participator exists
                    Boolean permission = false;
                    // boolean for participator already in group
                    Boolean inGroup = false;
                    // iterate over all participators
                    for (Participator part: ParticipatorsList) {
                        // if participator equals another participator in group
                        if (participator.equals(part.getParticipator())) {
                            inGroup = true;
                        }
                    }
                    // iterate over all users
                    for (User user: UsersList) {
                        // if participater exists and participator is not already in group
                        if (participator.equals(user.getUsername()) && !inGroup) {
                            permission = true;
                            // post participator in group
                            GroupsPost post = new GroupsPost(context);
                            post.postGroup(context, StartActivity.groupsname, participator);
                            participatorText.setText("");
                            //ListView participators = v.findViewById(R.id.list_participators);
                            //ParticipatorsAdapter adapter = new ParticipatorsAdapter(context, ParticipatorsList);
                            //participators.setAdapter(adapter);

                        }
                    }
                    // error messages
                    if (!permission) {
                        String message = "Gebruikersnaam bestaat niet";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                    else if (inGroup) {
                        String message = "Deelnemer zit al in groep";
                        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
        return v;

    }

    @Override
    public void gotUsers(ArrayList<User> UsersList) {
        this.UsersList = UsersList;
    }

    @Override
    public void gotUsersError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void gotParticipators(ArrayList<Participator> ParticipatorsList) {
        this.ParticipatorsList = ParticipatorsList;
        ListView participators = v.findViewById(R.id.list_participators);
        ParticipatorsAdapter adapter = new ParticipatorsAdapter(context, ParticipatorsList);
        participators.setAdapter(adapter);
    }

    @Override
    public void gotParticipatorsError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}
