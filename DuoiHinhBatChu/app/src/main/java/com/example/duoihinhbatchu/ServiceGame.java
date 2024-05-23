package com.example.duoihinhbatchu;

import java.util.ArrayList;
import java.util.Random;

public class ServiceGame {
    public ArrayList<String> megreData(String str){
        ArrayList<String> result = new ArrayList<>();
        ArrayList<String> temp = new ArrayList<>();
        String[] s = str.split(" ");
        for (int i = 0; i < s.length; i++) {
            temp.add(s[i]);
        }
        System.out.println(temp);
        Random random = new Random();
        while(temp.size()>0){
            int index = random.nextInt(temp.size());
            result.add(temp.remove(index).trim());
        }
        System.out.println(result);
        return result;
    }
    public int findIndexEmpty(ArrayList<String> arr) {
        for (int i = 0; i < arr.size(); i++) {
            if(arr.get(i).equals("")){
                return i;
            }
        }
        return -1;
    }

    public boolean checkAnwser(ArrayList<String> arrAnwser, String resuftAsk) {
        String[] anwser = resuftAsk.trim().replace("  ", " ").split(" ");
        for(int i=0;i<arrAnwser.size();i++){
            if(!arrAnwser.get(i).equals(anwser[i].trim().toUpperCase())){
                return false;
            }
        }
        return true;
    }
}
