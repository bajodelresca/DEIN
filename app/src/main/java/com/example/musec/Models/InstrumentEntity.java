package com.example.musec.Models;

public class InstrumentEntity {
    private String Name;
    public InstrumentEntity() {
       }

    public String getName() {
        return Name;
    }

    public boolean setName(String name) {
        if(name.startsWith("a")) {
            Name = name;
            return true;
        }else{
            return false;
        }
    }
}
