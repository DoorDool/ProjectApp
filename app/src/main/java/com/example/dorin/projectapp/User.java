package com.example.dorin.projectapp;

import java.io.Serializable;

public class User implements Serializable {

    String username, password, number;

    // constructor
    public User(String username, String password, String number) {
        this.username = username;
        this.password = password;
        this.number = number;
    }

    // getters and setters
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {this.username = username;}
    public String getPassword() { return password; }
    public void setPassword(String password) {this.password = password;}
    public String getNumber() { return number; }
    public void setNumber(String number) {this.number = number;}

}
