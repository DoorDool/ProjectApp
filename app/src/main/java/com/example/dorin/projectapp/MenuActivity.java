package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new GroupsFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;

                    switch (menuItem.getItemId()) {
                        case R.id.navigation_groups:
                            selectedFragment = new GroupsFragment();
                            break;
                        case R.id.navigation_categories:
                            selectedFragment = new CategorieFragment();
                            break;
                        case R.id.navigation_expenses:
                            selectedFragment = new ExpensesFragment();
                            break;
                        case R.id.navigation_payments:
                            selectedFragment = new PaymentsFragment();
                            break;
                        case R.id.navigation_details:
                            selectedFragment = new ParticipatorsFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(MenuActivity.this, StartActivity.class);
        startActivity(intent);
    }

}
