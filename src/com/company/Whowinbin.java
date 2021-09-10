package com.company;

public class Whowinbin {
    public String winner(int size, int comp, int user){
        if(comp == user){
            return "DRAW";
        }
        int interval = size / 2;
        while(interval > 0){
            comp++;
            interval--;
            if(comp > size){
                comp = 1;
            }
            if(comp == user){
                return "WIN";
            }
        }
        return"LOSE";
    }
}
