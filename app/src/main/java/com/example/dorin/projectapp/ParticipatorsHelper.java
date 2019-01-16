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

public class ParticipatorsHelper implements Response.Listener<JSONArray>, Response.ErrorListener {

    private Context context;
    private ArrayList<Participator> ParticipatorsList;
    private ParticipatorsHelper.Callback activity;

    public interface Callback {
        void gotParticipators(ArrayList<Participator> ParticipatorsList);
        void gotParticipatorsError(String message);
    }

    // constructor
    public ParticipatorsHelper(Context aContext) {
        this.context = aContext;
    }

    @Override
    public void onResponse(JSONArray response) {
        ParticipatorsList = new ArrayList<>();

        try {
            // Get all groups
            for (int i =  0; i < response.length(); i++) {
                JSONObject object = response.getJSONObject(i);
                String groupsname = object.getString("groupsname");
                String participator = object.getString("participator");

                Log.i("test", "groupsname from fragment 1234 is " + GroupsFragment.groupsname);

                String groupsna = "zxc";

                if (groupsname.equals(groupsna)) {
                    Log.i ("test", "deelnemer in groep zxc 1234 is " + participator);
                    Participator participatorNew = new Participator(participator);
                    ParticipatorsList.add(participatorNew);
                }
            }
            activity.gotParticipators(ParticipatorsList);
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
        activity.gotParticipatorsError(error.getMessage());
    }

    // Get the menu for category
    public void getParticipators(ParticipatorsHelper.Callback activity) {
        this.activity = activity;
        RequestQueue queue = Volley.newRequestQueue(context);
        String url = "https://ide50-doordool.legacy.cs50.io:8080/groups";
        // get menu from url with category = inputted category
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, this, this);
        queue.add(jsonArrayRequest);
    }
}
