package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    //public static String groupsname;   /////////////// moet weg kunnen
    BottomNavigationView bottomNav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                new GroupsFragment()).commit();
    }

    private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                    Fragment selectedFragment = null;
                    // check wich navigation item is click on
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
                        case R.id.navigation_participators:
                            selectedFragment = new ParticipatorsFragment();
                            break;
                    }

                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                            selectedFragment).commit();

                    return true;
                }
            };

    // method for move to categorieFragment
    public void moveToCategorieFragment() {
        Fragment fragment = new CategorieFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment).commit();
        bottomNav.setSelectedItemId(R.id.navigation_categories);
    }

    // method for move to expensesFragment
    public void moveToExpensesFragment() {
        Fragment fragment = new ExpensesFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                fragment).commit();
        bottomNav.setSelectedItemId(R.id.navigation_expenses);
    }

    // method for backpressed
    @Override
    public void onBackPressed() {
        finishAffinity();
        System.exit(0);
    }

}
