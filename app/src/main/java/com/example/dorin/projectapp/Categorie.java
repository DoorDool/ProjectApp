package com.example.dorin.projectapp;

import java.io.Serializable;

public class Categorie implements Serializable {

    String groupsname;
    String categorieName;

    public Categorie(String groupsname, String categorieName) {
        this.groupsname = groupsname;
        this.categorieName = categorieName;
    }

    // getters and setters
    public String getGroupsname() {
        return groupsname;
    }
    public void setGroupsname(String groupsname) {this.groupsname = groupsname;}
    public String getCategorieName() {
        return categorieName;
    }
    public void setCategorieName(String categorieName) {this.categorieName = categorieName;}
}
