package com.track_management.model;

import java.io.Serializable;

public class Item implements Serializable {
    private int id;
    private String name;
    private String singer;
    private String album;
    private String type;
    private boolean isFavourite;

    public Item() {
    }

    public Item(String name, String singer, String album, String type, boolean isFavourite) {
        this.name = name;
        this.singer = singer;
        this.album = album;
        this.type = type;
        this.isFavourite = isFavourite;
    }

    public Item(int id, String name, String singer, String album, String type, boolean isFavourite) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.album = album;
        this.type = type;
        this.isFavourite = isFavourite;
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

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
