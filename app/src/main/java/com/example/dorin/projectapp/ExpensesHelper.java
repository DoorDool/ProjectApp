package com.example.dorin.projectapp;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class ExpensesHelper implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Context context;
    private ArrayList<Expenses> ExpensesList;
    private ExpensesHelper.Callback activity;

    public interface Callback {
        void gotExpenses(ArrayList<Expenses> ExpensesList);
        void gotExpensesError(String message);
    }

    // constructor
    public ExpensesHelper(Context aContext) {
        this.context = aContext;
    }

    @Override
    public void onResponse(JSONArray response) {
        ExpensesList = new ArrayList<>();

        try {
            // Get all expenses
            for (int i =  0; i < response.length(); i++) {
                JSONObject object = response.getJSONObject(i);
                String username = object.getString("username");
                String groupsname = object.getString("groupsname");
                String categorieName = object.getString("categorieName");
                String toWhat = object.getString("toWhat");
                String amount = object.getString("amount");

                if ((groupsname.equals("zxc")) && (username.equals("test")) && (categorieName.equals("cat"))) {
                    Expenses newExpenses = new Expenses(username, groupsname, categorieName, toWhat, amount);
                    ExpensesList.add(newExpenses);
                }

            }
            activity.gotExpenses(ExpensesList);
        }

        // Catch errors and give message to user
        catch (Exception e) {
            e.printStackTrace();
            String warningMessage = e.getMessage();
            Toast.makeText(context, warningMessage, Toast.LENGTH_LONG).show();
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        activity.gotExpensesError(error.getMessage());
    }

    // Get the menu for category
    public void getExpenses(ExpensesHelper.Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://ide50-doordool.legacy.cs50.io:8080/expenses";
        // get menu from url with category = inputted category
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(jsonArrayRequest);
    }
}
