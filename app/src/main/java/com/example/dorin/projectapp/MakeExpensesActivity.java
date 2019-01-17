package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class MakeExpensesActivity extends AppCompatActivity{

    String username;
    String groupsname;
    String categorieName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_expenses);

        username = StartActivity.username;
        groupsname = "zxc";
        categorieName = "cat";
    }

    public void Click_on_make(View v) {

        EditText edit_toWhat = findViewById(R.id.toWhat_input);
        EditText edit_amount = findViewById(R.id.amount_input);
        String toWhat = edit_toWhat.getText().toString();
        String amount = edit_amount.getText().toString();

        ExpensesPost post = new ExpensesPost(MakeExpensesActivity.this);
        post.postExpenses(MakeExpensesActivity.this, username, groupsname, categorieName, toWhat, amount);

        Intent intent = new Intent(MakeExpensesActivity.this, MenuActivity.class);
        startActivity(intent);
    }
}
