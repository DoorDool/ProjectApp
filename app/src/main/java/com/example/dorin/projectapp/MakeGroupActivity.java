package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MakeGroupActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make_group);
    }

    public void Click_on_make(View v) {
        Intent intent = new Intent(MakeGroupActivity.this, menuActivity.class);
        startActivity(intent);
    }
}
