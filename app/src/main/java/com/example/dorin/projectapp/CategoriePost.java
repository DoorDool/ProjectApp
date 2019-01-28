package com.example.dorin.projectapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class CategoriePost implements Response.Listener<String>, Response.ErrorListener {

    Context context;
    CategoriePost.Callback callback_activity;
    String categorieName;
    String groupsname;

    public class PostRequest extends StringRequest {

        public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        protected Map<String, String> getParams() {
            // post parameters
            Map<String, String> params = new HashMap<>();
            params.put("groupsname", groupsname);
            params.put("categorieName", categorieName);
            return params;
        }
    }

    public interface Callback {
        void gotPostCategorie(String groups);
        void gotPostCategorieError(String message);
    }

    public CategoriePost(Context context) {
        this.context = context;
    }

    public void postCategorie(Context aContext, String categorie_groupsname, String categorie_categorieName) {
        this.context = aContext;
        groupsname = categorie_groupsname;
        categorieName = categorie_categorieName;

        String json_url = "https://ide50-doordool.legacy.cs50.io:8080/categories";
        RequestQueue queue = Volley.newRequestQueue(context);

        CategoriePost.PostRequest post = new CategoriePost.PostRequest(Request.Method.POST, json_url,this,this);
        queue.add(post);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotPostCategorieError(error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        try{
            callback_activity.gotPostCategorie(response);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
