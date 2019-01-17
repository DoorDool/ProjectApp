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

public class ExpensesPost implements Response.Listener<String>, Response.ErrorListener {

    Context context;
    ExpensesPost.Callback callback_activity;
    String username;
    String groupsname;
    String categorieName;
    String toWhat;
    String amount;

    public class PostRequest extends StringRequest {

        public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        protected Map<String, String> getParams() {

            Map<String, String> params = new HashMap<>();
            params.put("username", username);
            params.put("groupsname", groupsname);
            params.put("categorieName", categorieName);
            params.put("toWhat", toWhat);
            params.put("amount", amount);
            return params;
        }
    }

    public interface Callback {
        void gotPostExpenses(String groups);
        void gotPostExpensesError(String message);
    }

    public ExpensesPost(Context context) {
        this.context = context;
    }

    public void postExpenses(Context aContext,String expenses_username, String expenses_groupsname, String expenses_categorieName, String expenses_toWhat, String expenses_amount) {
        this.context = aContext;
        username = expenses_username;
        groupsname = expenses_groupsname;
        categorieName = expenses_categorieName;
        toWhat = expenses_toWhat;
        amount = expenses_amount;
        String json_url = "https://ide50-doordool.legacy.cs50.io:8080/expenses";
        RequestQueue queue = Volley.newRequestQueue(context);

        ExpensesPost.PostRequest post = new ExpensesPost.PostRequest(Request.Method.POST, json_url,this,this);
        queue.add(post);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotPostExpensesError(error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        try{
            callback_activity.gotPostExpenses(response);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
