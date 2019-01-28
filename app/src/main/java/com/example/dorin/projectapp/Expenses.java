package com.example.dorin.projectapp;

import java.io.Serializable;

public class Expenses implements Serializable {

    String username;
    String groupsname;
    String categorieName;
    String toWhat;
    String amount;

    // constructor
    public Expenses(String username, String groupsname, String categorieName, String toWhat, String amount) {
        this.username = username;
        this.groupsname = groupsname;
        this.categorieName = categorieName;
        this.toWhat = toWhat;
        this.amount = amount;
    }

    // getters and setter
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {this.username = username;}
    public String getGroupsname() {
        return groupsname;
    }
    public void setGroupsname(String groupsname) {this.groupsname = groupsname;}
    public String getCategorieName() {
        return categorieName;
    }
    public void setCategorieName(String categorieName) {this.categorieName = categorieName;}
    public String getToWhat() {
        return toWhat;
    }
    public void setToWhat(String toWhat) {this.toWhat = toWhat;}
    public String getAmount() {
        return amount;
    }
    public void setAmount(String amount) {this.amount = amount;}
}
