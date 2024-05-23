package com.example.duoihinhbatchu_v2.models;

import com.example.duoihinhbatchu_v2.dao.SQLiteHelper;

import java.util.ArrayList;
import java.util.List;

public class RiddlesGame {
    private List<Riddle> riddles;

    public RiddlesGame(List<Riddle> riddles) {
        this.riddles = riddles;
    }

    public Riddle getRiddle(int id){
        try{
            Riddle riddle = riddles.get(id);
            return riddle;
        }catch (Exception e){
            return riddles.get(0);
        }
    }
}
