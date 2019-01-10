package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MakeExpensesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_expenses);
    }

    public void Click_on_make(View v) {
        Intent intent = new Intent(MakeExpensesActivity.this, menuActivity.class);
        startActivity(intent);
    }
}
