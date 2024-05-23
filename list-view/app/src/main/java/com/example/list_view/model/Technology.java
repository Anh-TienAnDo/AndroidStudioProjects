package com.example.list_view.model;

public class Technology {
    private int img;
    private String name, sub, description;

    public Technology(int img, String name, String sub, String description) {
        this.img = img;
        this.name = name;
        this.sub = sub;
        this.description = description;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSub() {
        return sub;
    }

    public void setSub(String sub) {
        this.sub = sub;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
