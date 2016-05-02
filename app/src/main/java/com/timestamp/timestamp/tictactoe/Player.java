package com.timestamp.timestamp.tictactoe;

import android.graphics.drawable.Drawable;

/**
 * Created by Ryan on 4/27/2016.
 */
public class Player {
    private String name;
    private int piece;

    public Player(String name, int piece){
        this.name=name;
        this.piece=piece;
    }
    public String getName(){
        return name;
    }
    public int getPiece(){
        return piece;
    }

    public void setName(String name){
        this.name=name;
    }
}
