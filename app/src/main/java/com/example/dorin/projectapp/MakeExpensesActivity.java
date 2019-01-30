package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MakeExpensesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_expenses);
    }

    public void Click_on_make(View v) {

        // get values form editTexts
        EditText edit_toWhat = findViewById(R.id.toWhat_input);
        EditText edit_amount = findViewById(R.id.amount_input);
        String toWhat = edit_toWhat.getText().toString();
        String amount = edit_amount.getText().toString();

        Boolean emptyToWhat = false;
        Boolean emptyAmount = false;

        // error message
        if (toWhat.equals("")) {
            edit_toWhat.setError("Vul iets in");
            emptyToWhat = true;
        }
        if (amount.equals("")) {
            edit_amount.setError("Vul een bedrag in");
            emptyAmount = true;
        }

        if (!emptyAmount && !emptyToWhat) {
            ExpensesPost post = new ExpensesPost(MakeExpensesActivity.this);
            post.postExpenses(MakeExpensesActivity.this, StartActivity.username, StartActivity.groupsname, StartActivity.categoriename, toWhat, amount);

            Intent intent = new Intent(MakeExpensesActivity.this, MenuActivity.class);
            startActivity(intent);
        }
    }
}
