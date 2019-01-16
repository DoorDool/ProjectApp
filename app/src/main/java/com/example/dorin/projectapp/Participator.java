package com.example.dorin.projectapp;

import java.io.Serializable;

public class Participator implements Serializable {

    String participator;

    public Participator(String participator) {
        this.participator = participator;
    }

    // getters and setters
    public String getParticipator() {
        return participator;
    }
    public void setParticipator(String participator) {this.participator = participator;}
}
