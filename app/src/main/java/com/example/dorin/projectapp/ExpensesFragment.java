package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class ExpensesFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        View v = inflater.inflate(R.layout.fragment_expenses, container, false);

        FloatingActionButton addGroupButton = v.findViewById(R.id.add_expenses_button);
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MakeExpensesActivity.class);
                startActivity(intent);
            }
        });

        return v;
    }

}