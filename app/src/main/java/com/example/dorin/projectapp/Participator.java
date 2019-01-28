package com.example.dorin.projectapp;

import java.io.Serializable;

public class Participator implements Serializable {

    String participator;

    // constructor
    public Participator(String participator) {
        this.participator = participator;
    }

    // getter and setter
    public String getParticipator() {
        return participator;
    }
    public void setParticipator(String participator) {this.participator = participator;}
}
