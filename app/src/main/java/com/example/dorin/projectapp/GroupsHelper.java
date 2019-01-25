package com.example.dorin.projectapp;

import android.content.Context;
import android.util.Log;
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

public class GroupsHelper  implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Context context;
    private ArrayList<Group> GroupsList;
    private GroupsHelper.Callback activity;

    public interface Callback {
        void gotGroups(ArrayList<Group> GroupsList);
        void gotGroupsError(String message);
    }

    // constructor
    public GroupsHelper(Context aContext) {
        this.context = aContext;
    }

    @Override
    public void onResponse(JSONArray response) {
        GroupsList = new ArrayList<>();

        try {
            // Get all groups
            for (int i =  0; i < response.length(); i++) {
                JSONObject object = response.getJSONObject(i);
                String groupsname = object.getString("groupsname");
                String participator = object.getString("participator");

                // the first group where username is in always add
                if (participator.equals(StartActivity.username)) {
                    Group newGroup = new Group(groupsname);
                    GroupsList.add(newGroup);
                    Log.i("test", "1234 " + groupsname);
                }
            }
            activity.gotGroups(GroupsList);
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
        activity.gotGroupsError(error.getMessage());
    }

    // Get the menu for category
    public void getGroup(GroupsHelper.Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://ide50-doordool.legacy.cs50.io:8080/groups";
        // get menu from url with category = inputted category
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(jsonArrayRequest);
    }

}
