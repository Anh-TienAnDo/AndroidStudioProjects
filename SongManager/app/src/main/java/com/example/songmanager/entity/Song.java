package com.example.songmanager.entity;

import java.io.Serializable;

public class Song implements Serializable {
    private int id;
    private String name, singer, album, type;
    private boolean love;

    public Song(int id, String name, String singer, String album, String type, boolean love) {
        this.id = id;
        this.name = name;
        this.singer = singer;
        this.album = album;
        this.type = type;
        this.love = love;
    }

    public Song(String name, String singer, String album, String type, boolean love) {
        this.name = name;
        this.singer = singer;
        this.album = album;
        this.type = type;
        this.love = love;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public void setId(int id) {
        this.id = id;
    }

    public boolean isLove() {
        return love;
    }

    public void setLove(boolean love) {
        this.love = love;
    }

    public String getSinger() {
        return singer;
    }

    public void setSinger(String singer) {
        this.singer = singer;
    }
}
