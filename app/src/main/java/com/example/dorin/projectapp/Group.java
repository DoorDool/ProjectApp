package com.example.dorin.projectapp;

import java.io.Serializable;
import java.util.ArrayList;

public class Group implements Serializable {

    String groupsname;

    public Group(String groupsname) {
        this.groupsname = groupsname;
    }

    // getters and setters
    public String getGroupsname() {
        return groupsname;
    }
    public void setGroupsname(String groupsname) {this.groupsname = groupsname;}

}
