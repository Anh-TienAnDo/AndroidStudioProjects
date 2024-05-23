package com.example.duoihinhbatchu_v2.models;

import java.io.Serializable;

public class Riddle implements Serializable {
    private int id, image;
    private String name, result;

    public Riddle() {
    }

    public Riddle(int id, int image, String name, String result) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.result = result;
    }

    public Riddle(int image, String name, String result) {
        this.image = image;
        this.name = name;
        this.result = result;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }
}
