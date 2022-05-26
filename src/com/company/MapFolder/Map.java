package com.company.MapFolder;

import java.util.Scanner;

public class Map {
    private MapSquare currentPos;
    FileHandling fH = new FileHandling("Mapgrid.txt");
    MapSquare mapSquares[];

    public Map() {
       fH.paddedSpaces();
        mapSquares = new MapSquare[fH.getCollums()*fH.lineAmount];
        int counter=0;
        for(int x = 0; x<fH.lineAmount;x++) {
            for(int y = 0;y<fH.getCollums();y++) {
                mapSquares[counter] = new MapSquare(Integer.parseInt(fH.getRecord(x).split("\\s+")[y]));
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

    private void CreateSurroundings() {
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


        public void move(){
            Display();
            CreateSurroundings();
            Scanner Ui = new Scanner(System.in);  // Create a Scanner object
            System.out.println("u/d/l/r");
            String input = Ui.nextLine();

            MapSquare oldpos = currentPos;
            if(input.equals("u")){
                if(currentPos.getUp() != null){
                    SetCurrent(currentPos.getUp());
                }
            }
            if(input.equals("d")){
                if(currentPos.getDown() != null){
                    SetCurrent(currentPos.getDown());
                }
            }
            if(input.equals("l")){
                if(currentPos.getLeft() != null){
                    SetCurrent(currentPos.getLeft());
                }
            }
            if(input.equals("r")){
                if(currentPos.getRight() != null){
                    SetCurrent(currentPos.getRight());
                }
            }
            oldpos.clear();
        }

        public void Display(){;
            String newpos;
            newpos = currentPos.number()+"";
            System.out.println("currently at " + newpos);
            return;
        }









    }





