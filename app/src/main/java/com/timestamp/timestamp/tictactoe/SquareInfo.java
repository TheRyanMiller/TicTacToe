package com.timestamp.timestamp.tictactoe;

/**
 * Created by Ryan on 5/1/2016.
 */
public class SquareInfo {
    public int squareNum;
    public int pieceId;
    public int viewId;
    SquareInfo(int squareNum, int pieceId, int viewId){
        this.squareNum=squareNum;
        this.pieceId=pieceId;
        this.viewId=viewId;
    }
    SquareInfo(int squareNum, int viewId){
        this.squareNum=squareNum;
        this.pieceId=0;
        this.viewId=viewId;
    }
}
