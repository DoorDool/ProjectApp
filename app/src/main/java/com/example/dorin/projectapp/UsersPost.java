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

public class UsersPost implements Response.Listener<String>, Response.ErrorListener {

    Context context;
    Callback callback_activity;
    String username, password;

    public class PostRequest extends StringRequest {

        public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        protected Map<String, String> getParams() {
            // post parameters
            Map<String, String> params = new HashMap<>();
            params.put("username", username);
            params.put("password", password);
            return params;
        }
    }

    public interface Callback {
        void gotPostUsers(String users);
        void gotPostUsersError(String message);
    }

    public UsersPost(Context context) {
        this.context = context;
    }

    public void postUsers(Context aContext, String users_gebruikersnaam, String users_wachtwoord){
        this.context = aContext;
        username = users_gebruikersnaam;
        password = users_wachtwoord;
        String json_url = "https://ide50-doordool.legacy.cs50.io:8080/users";
        RequestQueue queue = Volley.newRequestQueue(context);

        PostRequest post = new PostRequest(Request.Method.POST, json_url,this,this);
        queue.add(post);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotPostUsersError(error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        try{
            callback_activity.gotPostUsers(response);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
