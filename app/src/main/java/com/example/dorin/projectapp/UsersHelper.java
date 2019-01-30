package com.example.dorin.projectapp;

import android.content.Context;
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

public class UsersHelper implements Response.Listener<JSONArray>, Response.ErrorListener{

    private Context context;
    private ArrayList<User> UsersList;
    private UsersHelper.Callback activity;

    public interface Callback {
        void gotUsers(ArrayList<User> UsersList);
        void gotUsersError(String message);
    }

    // constructor
    public UsersHelper(Context aContext) {
        this.context = aContext;
    }

    @Override
    public void onResponse(JSONArray response) {
        UsersList = new ArrayList<>();

        try {
            // Get all users
            for (int i =  0; i < response.length(); i++) {
                JSONObject object = response.getJSONObject(i);
                String username = object.getString("username");
                String password = object.getString("password");
                User user = new User(username, password);
                UsersList.add(user);
            }
            activity.gotUsers(UsersList);
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
        activity.gotUsersError(error.getMessage());
    }

    // Get the menu for category
    public void getUser(UsersHelper.Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://ide50-doordool.legacy.cs50.io:8080/users";
        // get menu from url with category = inputted category
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(jsonArrayRequest);
    }
}
