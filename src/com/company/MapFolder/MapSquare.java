package com.company.MapFolder;
public class MapSquare {

    private final int number;
    private MapSquare up;
    private MapSquare down;
    private MapSquare left;
    private MapSquare right;
    public MapSquare(int number){
        this.number = number;
        up = null;
        down = null;
        left = null;
        right = null;
    }

    public void setUp(MapSquare b){
        up=b;
    }
    public void setDown(MapSquare b){
        down=b;
    }
    public void setLeft(MapSquare b){
        left=b;
    }
    public void setRight(MapSquare b){
        right=b;
    }
    public MapSquare getUp(){
        return up;
    }
    public MapSquare getDown(){
        return down;
    }
    public MapSquare getLeft(){
        return left;
    }
    public MapSquare getRight(){
        return right;
    }
    public void clear(){up = null; down = null; left = null; right = null;}

    public int number(){
        return number;
    }
}

