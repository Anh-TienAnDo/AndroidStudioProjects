package com.example.duoihinhbatchu.entity;

public class Questions {
    private int id;
    private String name, result, image;

    public Questions() {
    }

    public Questions(int id, String name, String result, String image) {
        this.id = id;
        this.name = name;
        this.result = result;
        this.image = image;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
