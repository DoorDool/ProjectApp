package com.example.dorin.projectapp;

import java.io.Serializable;

public class Payment implements Serializable {

    Float much;
    String fromWho;
    String toWho;

    // constructor
    public Payment(Float much, String fromWho, String toWho) {
        this.much = much;
        this.fromWho = fromWho;
        this.toWho = toWho;
    }

    // getters and setters
    public Float getMuch() {
        return much;
    }
    public void setMuch(Float much) {this.much = much;}
    public String getFromWho() { return fromWho; }
    public void setFromWho(String fromWho) {this.fromWho = fromWho;}
    public String getToWho() { return toWho; }
    public void setToWho(String toWho) {this.toWho = toWho;}

}
