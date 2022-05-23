package com.company.MapFolder;

import java.util.Scanner;

public class Map {
    private MapSquare currentPos;

    public Map() {
    }

    public void SetCurrent(MapSquare b) {
        currentPos = b;
    }

    public MapSquare getCurrent() {
        return currentPos;
    }

    private void CreateSurroundings() {
        Database MG = new Database("MapGrid.txt");
        MapSquare mapSquares[] = new MapSquare[MG.FileManagement.getCollums() * MG.getRecordCount()];
        int counter = 0;
        for (int x = 0; x < MG.getRecordCount(); x++) {
            for (int y = 0; y < MG.FileManagement.getCollums(); y++) {
                mapSquares[counter] = new MapSquare(Integer.parseInt(MG.getRecord(x + 1).split("\\s+")[y]));
                counter++;
            }
        }

        for (int x = 0; x < mapSquares.length; x++) {
            if (currentPos.number() == mapSquares[x].number()) {
                int row = x / MG.FileManagement.getCollums();
                if (x - MG.FileManagement.getCollums() >= 0) {
                    currentPos.setUp(mapSquares[x - MG.FileManagement.getCollums()]);
                }
                if (x + MG.FileManagement.getCollums() <= mapSquares.length - 1) {
                    currentPos.setDown(mapSquares[x + MG.FileManagement.getCollums()]);
                }
                if (x - 1 >= row * MG.FileManagement.getCollums()) {
                    currentPos.setLeft(mapSquares[x - 1]);
                }
                if (x + 1 <= ((row + 1) * MG.FileManagement.getCollums()) - 1) {
                    currentPos.setRight(mapSquares[x + 1]);
                }

            }

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







        public void createGid(){
            Map map = new Map();

            Database MG = new Database("MapGrid.txt");
            MapSquare mapSquares[] = new MapSquare[MG.FileManagement.getCollums()*MG.getRecordCount()];
            int counter=0;
            for(int x = 0; x<MG.getRecordCount();x++) {
                for(int y = 0;y<MG.FileManagement.getCollums();y++) {
                    mapSquares[counter] = new MapSquare(Integer.parseInt(MG.getRecord(x+1).split("\\s+")[y]));
                    counter++;
                }
            }
            SetCurrent(mapSquares[0]);
        }

    }





