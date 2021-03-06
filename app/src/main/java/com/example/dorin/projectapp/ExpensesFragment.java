package com.example.dorin.projectapp;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ExpensesFragment extends Fragment implements ExpensesHelper.Callback {

    Context context;
    ArrayList<Expenses> ExpensesList;
    View v;

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){

        v = inflater.inflate(R.layout.fragment_expenses, container, false);
        context = getContext();

        // method for plus button
        FloatingActionButton addGroupButton = v.findViewById(R.id.add_expenses_button);
        addGroupButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MakeExpensesActivity.class);
                startActivity(intent);

            }
        });

        ExpensesHelper helper = new ExpensesHelper(context);
        helper.getExpenses(this);

        return v;
    }

    @Override
    public void gotExpenses(ArrayList<Expenses> ExpensesList) {
        this.ExpensesList = ExpensesList;
        ListView expenses = v.findViewById(R.id.listView_expenses);
        ExpensesAdapter adapter = new ExpensesAdapter(context, ExpensesList);
        expenses.setAdapter(adapter);

        // if no group is selected or no categorie or expenses error message
        if (StartActivity.groupsname == null) {
            TextView text = v.findViewById(R.id.noText);
            text.setText("geen groep geselecteerd");
        }
        else if (StartActivity.categoriename == null) {
            TextView text = v.findViewById(R.id.noText);
            text.setText("geen categorie geselecteerd");
        }
        else if (ExpensesList.size() == 0) {
            TextView text = v.findViewById(R.id.noText);
            text.setText("er zijn geen uitgaven");
        }
    }

    @Override
    public void gotExpensesError(String message) {
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }


}
