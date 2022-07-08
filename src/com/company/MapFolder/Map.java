package com.company.MapFolder;

import java.util.Scanner;

public class Map {
    private MapSquare currentPos;
    FileHandling fH = new FileHandling("Mapgrid.txt");
    MapSquare[] mapSquares;

    public Map() {
        fH.paddedSpaces();
        mapSquares = new MapSquare[fH.getCollums()*fH.lineAmount];
        int counter=0;
        for(int x = 0; x<fH.lineAmount;x++) {
            for(int y = 0;y<fH.getCollums();y++) {
                String name[] = fH.getRecord(x).split("\\s+");
                mapSquares[counter] = new MapSquare(name[y],counter);
                counter++;
            }
        }
        SetCurrent(mapSquares[0]);
    }

    public void SetCurrent(MapSquare b) {
        currentPos = b;
    }

    public MapSquare getCurrent() {
        return currentPos;
    }

    public void CreateSurroundings() {
        int collums = fH.getCollums();
        int row = currentPos.number()/collums;
        int x = currentPos.number();

        if(x-collums>=0){
            currentPos.setUp(mapSquares[x-collums]);
        }
        if(x+collums<=mapSquares.length-1){
            currentPos.setDown(mapSquares[x+collums]);
        }
        if(x-1>=row*collums){
            currentPos.setLeft(mapSquares[x-1]);
        }
        if(x+1<=((row+1)*collums-1)){
            currentPos.setRight(mapSquares[x+1]);
        }

    }

    public void Display(){
        String newpos;
        newpos = currentPos.name();
        System.out.println("currently at " + newpos);
    }
}