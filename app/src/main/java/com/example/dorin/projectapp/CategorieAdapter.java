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

    // constructor
    public CategorieAdapter(Context context, ArrayList<Categorie> CategorieList) {
        super(context, 0, CategorieList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.categorie_item, parent, false);
        }

        // Get categorie
        Categorie categorie = getItem(position);
        Switch name = convertView.findViewById(R.id.switch_categorieName);
        // set textview of switch
        name.setText(categorie.getCategorieName());
        // if a categorie is active then set switch true
        if (StartActivity.categoriename != null && StartActivity.categoriename.equals(categorie.getCategorieName())) {
            name.setChecked(true);
        }

        return convertView;
    }
}

