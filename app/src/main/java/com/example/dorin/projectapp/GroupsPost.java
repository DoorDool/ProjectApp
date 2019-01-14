package com.example.dorin.projectapp;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class GroupsPost implements Response.Listener<String>, Response.ErrorListener {

    Context context;
    GroupsPost.Callback callback_activity;
    String groupsname;
    String Participator;

    public class PostRequest extends StringRequest {

        public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        protected Map<String, String> getParams() {

            Map<String, String> params = new HashMap<>();
            params.put("groupsname", groupsname);
            params.put("participator", Participator);
            return params;
        }
    }

    public interface Callback {
        void gotpostGroups(String groupsname);
        void gotpostGroupsError(String message);
    }

    public GroupsPost(Context context) {
        this.context = context;
    }

    public void postUsers(Context aContext, String groupsname, String Participator) {
        this.context = aContext;
        groupsname = groupsname;
        Participator = Participator;
        String json_url = "https://ide50-doordool.legacy.cs50.io:8080/groups";
        RequestQueue queue = Volley.newRequestQueue(context);

        GroupsPost.PostRequest post = new GroupsPost.PostRequest(Request.Method.POST, json_url,this,this);
        queue.add(post);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotpostGroupsError(error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        try{
            callback_activity.gotpostGroups(response);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
