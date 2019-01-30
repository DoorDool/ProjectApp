package com.example.dorin.projectapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CategorieFragment extends Fragment implements CategorieHelper.Callback {

    View v;
    EditText input_categorieName;
    ListView categories;
    ArrayList<Categorie> CategorieList;
    Context context;
    String groupsname;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        v = inflater.inflate(R.layout.fragment_categorie, container, false);
        context = getContext();
        input_categorieName = v.findViewById(R.id.edit_categorieName);

        CategorieHelper helper = new CategorieHelper(context);
        helper.getCategorie(this);

        // on click for plus button
        FloatingActionButton addCategorieButton = v.findViewById(R.id.add_button);
        addCategorieButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // get the value of the editText
                String categorieName = input_categorieName.getText().toString();
                // make new object for categrie
                boolean same = false;
                for (Categorie categorie: CategorieList) {
                    if (categorie.categorieName.equals(categorieName)) {
                        same = true;
                    }
                }
                if (StartActivity.groupsname == null) {
                    input_categorieName.setError("Er is geen groep geselecteerd");
                }
                else if (categorieName.equals("")) {
                    input_categorieName.setError("Geen naam ingevoerd");
                }
                else if (same) {
                    input_categorieName.setError("Deze naam bestaat al");
                }
                else {
                    CategoriePost post = new CategoriePost(context);
                    // post categorie in online database
                    post.postCategorie(getContext(), StartActivity.groupsname, categorieName);
                    // reset editText
                    input_categorieName.setText("");
                }

            }
        });
        categories = v.findViewById(R.id.list_categories);
        categories.setOnItemClickListener(new listClickListener());
        return v;
    }

    @Override
    public void gotCategorie(ArrayList<Categorie> CategorieList) {
        this.CategorieList = CategorieList;
        CategorieAdapter adapter = new CategorieAdapter(context, CategorieList);
        categories.setAdapter(adapter);

        if (StartActivity.groupsname == null) {
            TextView text = v.findViewById(R.id.noText);
            text.setText("geen groep geselecteerd");
        }
        else if (CategorieList.size() == 0) {
            TextView text = v.findViewById(R.id.noText);
            text.setText("er zijn geen categoriën");
        }

    }

    @Override
    public void gotCategorieError(String message) {
        Toast.makeText(getContext(), message, Toast.LENGTH_SHORT).show();
    }

    private class listClickListener implements AdapterView.OnItemClickListener  {
        @Override
        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
            Categorie switchGroup = (Categorie) parent.getItemAtPosition(position);
            StartActivity.categoriename = switchGroup.getCategorieName();
            // go to ExpensesActivity
            ((MenuActivity) getActivity()).moveToExpensesFragment();

        }
    }


}
