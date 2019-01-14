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
    String gebruikersnaam, wachtwoord, rekeningnummer;

    public class PostRequest extends StringRequest {

        public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        protected Map<String, String> getParams() {

            Map<String, String> params = new HashMap<>();
            params.put("username", gebruikersnaam);
            params.put("password", wachtwoord);
            params.put("number", rekeningnummer);
            return params;
        }
    }

    public interface Callback {
        void gotpostUsers(String highscores);
        void gotpostUsersError(String message);
    }

    public UsersPost(Context context) {
        this.context = context;
    }

    public void postUsers(Context aContext, String users_gebruikersnaam, String users_wachtwoord, String users_rekeningnummer){
        this.context = aContext;
        gebruikersnaam = users_gebruikersnaam;
        wachtwoord = users_wachtwoord;
        rekeningnummer = users_rekeningnummer;
        String json_url = "https://ide50-doordool.legacy.cs50.io:8080/users";
        RequestQueue queue = Volley.newRequestQueue(context);

        PostRequest post = new PostRequest(Request.Method.POST, json_url,this,this);
        queue.add(post);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotpostUsersError(error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        try{
            callback_activity.gotpostUsers(response);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
