package com.example.dorin.projectapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.Toast;

import java.util.ArrayList;

public class CategorieFragment extends Fragment implements CategorieHelper.Callback {

    View v;
    EditText input_categorieName;
    ArrayList<Categorie> CategorieList;
    Context context;
    String groupsname;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        v = inflater.inflate(R.layout.fragment_categorie, container, false);
        context = getContext();
        input_categorieName = v.findViewById(R.id.edit_categorieName);

        //Log.i("test", "test username 1234 is " + MenuActivity.username);
        //Log.i("test", "test groupsname 1234 is " + GroupsFragment.groupsname);
        //Log.i("test", "test groupsname menu 1234 is " + MenuActivity.groupsname);
        CategorieHelper helper = new CategorieHelper(context);
        helper.getCategorie(this);

        FloatingActionButton addCategorieButton = v.findViewById(R.id.add_button);
        addCategorieButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String categorieName = input_categorieName.getText().toString();
                String groupsname = "zxc";

                CategoriePost post = new CategoriePost(context);
                post.postCategorie(getContext(), groupsname, categorieName);
                input_categorieName.setText("");
                
            }
        });

        return v;
    }

    @Override
    public void gotCategorie(ArrayList<Categorie> CategorieList) {
        this.CategorieList = CategorieList;
        ListView categories = v.findViewById(R.id.list_categories);
        CategorieAdapter adapter = new CategorieAdapter(context, CategorieList);
        categories.setAdapter(adapter);
        //categories.setOnItemClickListener(new CategorieFragment.listClickListener());
    }


    @Override
    public void gotCategorieError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    //private class listClickListener implements AdapterView.OnItemClickListener  {

    //    @Override
    //    public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
    //        Switch switchCategorie = v.findViewById(R.id.switch_categorieName);
    //        switchCategorie.setChecked(true);
    //        MenuActivity.groupsname = switchCategorie.getText().toString();

    //    }

    //}


}
