package com.example.dorin.projectapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;

import java.util.ArrayList;

public class GroupsAdapter extends ArrayAdapter<Group> {

    public GroupsAdapter(Context context, ArrayList<Group> GroupsList) {
        super(context, 0, GroupsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.groups_item, parent, false);
        }

        // Get and set textViews and imageViews from item
        Group group = getItem(position);
        Switch name = convertView.findViewById(R.id.switch_groupsname);
        name.setText(group.getGroupsname());
        if (StartActivity.groupsname != null && StartActivity.groupsname.equals(group.getGroupsname())) {
            name.setChecked(true);
        }

        return convertView;
    }
}
