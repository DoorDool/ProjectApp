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

public class CategorieAdapter extends ArrayAdapter<Categorie> {

    public CategorieAdapter(Context context, ArrayList<Categorie> CategorieList) {
        super(context, 0, CategorieList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.categorie_item, parent, false);
        }

        // Get and set textViews and imageViews from item
        Categorie categorie = getItem(position);
        Switch name = convertView.findViewById(R.id.switch_categorieName);
        name.setText(categorie.getCategorieName());

        return convertView;
    }
}

