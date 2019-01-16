package com.example.dorin.projectapp;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class GroupsFragment extends Fragment implements GroupsHelper.Callback{

    ArrayList<Group> GroupsList;
    View v;
    Context context;
    //public static String groupsname;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        v = inflater.inflate(R.layout.fragment_groups, container, false);
        context = getContext();

        FloatingActionButton addGroupButton = v.findViewById(R.id.add_group_button);
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MakeGroupActivity.class);
                startActivity(intent);
            }
        });

        GroupsHelper helper = new GroupsHelper(context);
        helper.getGroup(this);

        return v;
    }


    @Override
    public void gotGroups(ArrayList<Group> GroupsList) {
        this.GroupsList = GroupsList;
        ListView groups = v.findViewById(R.id.listview_groups);
        GroupsAdapter adapter = new GroupsAdapter(context, GroupsList);
        groups.setAdapter(adapter);
        groups.setOnItemClickListener(new listClickListener());
    }


    @Override
    public void gotGroupsError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }

    private class listClickListener implements AdapterView.OnItemClickListener  {

        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Switch switchGroup = v.findViewById(R.id.switch_groupsname);
            switchGroup.setChecked(true);
            MenuActivity.groupsname = switchGroup.getText().toString();



        }

    }





}
