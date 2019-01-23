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

public class CategorieHelper implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Context context;
    private ArrayList<Categorie> CategorieList;
    private CategorieHelper.Callback activity;

    public interface Callback {
        void gotCategorie(ArrayList<Categorie> CategorieList);
        void gotCategorieError(String message);
    }

    // constructor
    public CategorieHelper(Context aContext) {
        this.context = aContext;
    }

    @Override
    public void onResponse(JSONArray response) {
        CategorieList = new ArrayList<>();

        try {
            // Get all categories
            for (int i =  0; i < response.length(); i++) {
                JSONObject object = response.getJSONObject(i);
                String groupsname = object.getString("groupsname");
                String categorieName = object.getString("categorieName");
                if (groupsname.equals(StartActivity.groupsname)) {
                    Categorie newCategorie = new Categorie(groupsname, categorieName);
                    CategorieList.add(newCategorie);
                }
            }
            activity.gotCategorie(CategorieList);
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
        activity.gotCategorieError(error.getMessage());
    }

    // Get the menu for category
    public void getCategorie(CategorieHelper.Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://ide50-doordool.legacy.cs50.io:8080/categories";
        // get menu from url with category = inputted category
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(jsonArrayRequest);
    }
}
