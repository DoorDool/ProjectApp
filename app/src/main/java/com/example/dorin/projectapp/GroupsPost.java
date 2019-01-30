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

public class GroupsPost implements Response.Listener<String>, Response.ErrorListener {

    Context context;
    GroupsPost.Callback callback_activity;
    String groupsname;
    String participator;

    public class PostRequest extends StringRequest {

        public PostRequest(int method, String url, Response.Listener<String> listener, Response.ErrorListener errorListener) {
            super(method, url, listener, errorListener);
        }

        @Override
        protected Map<String, String> getParams() {
            // post parameters
            Map<String, String> params = new HashMap<>();
            params.put("groupsname", groupsname);
            params.put("participator", participator);
            return params;
        }
    }

    public interface Callback {
        void gotPostGroups(String groups);
        void gotPostGroupsError(String message);
    }

    public GroupsPost(Context context) {
        this.context = context;
    }

    public void postGroup(Context aContext, String groups_groupsname, String groups_participator) {
        this.context = aContext;
        groupsname = groups_groupsname;
        participator = groups_participator;
        String json_url = "https://ide50-doordool.legacy.cs50.io:8080/groups";
        RequestQueue queue = Volley.newRequestQueue(context);

        GroupsPost.PostRequest post = new GroupsPost.PostRequest(Request.Method.POST, json_url,this,this);
        queue.add(post);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        callback_activity.gotPostGroupsError(error.getMessage());
    }

    @Override
    public void onResponse(String response) {
        try{
            callback_activity.gotPostGroups(response);
        }
        catch(Exception e){
            System.out.println(e.toString());
        }
    }
}
