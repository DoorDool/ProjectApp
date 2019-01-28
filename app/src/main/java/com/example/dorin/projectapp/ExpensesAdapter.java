package com.example.dorin.projectapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;

public class ExpensesAdapter extends ArrayAdapter<Expenses> {

    // constructor
    public ExpensesAdapter(Context context, ArrayList<Expenses> ExpensesList) {
        super(context, 0, ExpensesList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.expenses_item, parent, false);
        }

        // Get expenses and their values
        Expenses expenses = getItem(position);
        TextView textUsername = convertView.findViewById(R.id.textView_username);
        TextView textToWhat = convertView.findViewById(R.id.textView_toWhat);
        TextView textAmount = convertView.findViewById(R.id.textView_amount);

        // If expense is from user, than change name user in "u"
        if (expenses.getUsername().equals(StartActivity.username)) {
            textUsername.setText("u");
        }
        else {
            textUsername.setText(expenses.getUsername());
        }
        textToWhat.setText(expenses.getToWhat());
        // always show money with a point, not a comma
        textAmount.setText("€ " + expenses.getAmount().replaceAll(",", "."));

        return convertView;
    }
}
