package com.example.project;

import androidx.annotation.NonNull;
public class Landskap {
    private String name;

    public Landskap(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }
    @NonNull

    @Override
    public String toString() {
        return name;
    }
}
