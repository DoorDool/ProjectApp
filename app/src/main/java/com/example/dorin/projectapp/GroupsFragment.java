package com.example.dorin.projectapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class GroupsFragment extends Fragment implements OnClickListener {

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle saveInstanceState){
        return inflater.inflate(R.layout.fragment_groups, container, false);
    }

    public void Click_on_makegroup(View v) {
        Intent intent = new Intent(getActivity(), MakeGroupActivity.class);
        startActivity(intent);
    }
}
