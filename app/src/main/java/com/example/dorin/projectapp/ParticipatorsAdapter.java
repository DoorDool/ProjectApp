package com.example.dorin.projectapp;

import android.content.Context;
import android.provider.Telephony;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ParticipatorsAdapter extends ArrayAdapter<Participator> {

    public ParticipatorsAdapter(Context context, ArrayList<Participator> ParticipatorsList) {
        super(context, 0, ParticipatorsList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.participators_item, parent, false);
        }

        Participator participator = getItem(position);
        // Get and set textViews and imageViews from item
        TextView text = convertView.findViewById(R.id.item_partname);

        if (participator.getParticipator().equals(StartActivity.username)) {
            text.setText("u");
        }
        else {
            text.setText(participator.getParticipator());
        }

        return convertView;
    }
}
