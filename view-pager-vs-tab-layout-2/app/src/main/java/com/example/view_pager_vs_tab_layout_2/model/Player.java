package com.example.view_pager_vs_tab_layout_2.model;

public class Player {
    private int avatar;
    private String name;
    private double price;
    private String description;

    public Player() {
    }

    public Player(int avatar, String name, double price, String description) {
        this.avatar = avatar;
        this.name = name;
        this.price = price;
        this.description = description;
    }

    public int getAvatar() {
        return avatar;
    }

    public void setAvatar(int avatar) {
        this.avatar = avatar;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
