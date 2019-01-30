package com.example.dorin.projectapp;

import java.io.Serializable;

public class Group implements Serializable {

    String groupsname;

    // constructor
    public Group(String groupsname) {
        this.groupsname = groupsname;
    }

    // getter and setter
    public String getGroupsname() {
        return groupsname;
    }
    public void setGroupsname(String groupsname) {this.groupsname = groupsname;}

}
