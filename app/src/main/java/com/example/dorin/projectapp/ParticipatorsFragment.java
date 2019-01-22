package com.example.dorin.projectapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ParticipatorsFragment extends Fragment implements ParticipatorsHelper.Callback {

    ArrayList<Participator> ParticipatorsList;
    View v;
    Context context;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        v = inflater.inflate(R.layout.fragment_participators, container, false);
        context = getContext();
        ParticipatorsHelper helper = new ParticipatorsHelper(context);
        helper.getParticipators(this);

        // doet het niet
        String groupsname = MenuActivity.groupsname;
        //Log.i("test", "groupsname in fragment is 1234 " + groupsname);

        return v;

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
