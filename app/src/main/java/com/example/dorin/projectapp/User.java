package com.example.dorin.projectapp;

import java.io.Serializable;

public class User implements Serializable {

    String username, password;

    // constructor
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // getters and setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {this.username = username;}
    public String getPassword() { return password; }
    public void setPassword(String password) {this.password = password;}

}
