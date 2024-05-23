package com.example.thuchanh1;

public class Phone {
    private String name;
    private String price;
    private String origin;
    private int drawableId;

    public Phone(String name, String price, String origin, int drawableId) {
        this.name = name;
        this.price = price;
        this.origin = origin;
        this.drawableId = drawableId;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }
}
