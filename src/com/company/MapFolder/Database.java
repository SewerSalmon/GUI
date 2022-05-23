package com.company.MapFolder;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Database {

    private String filename;
    private int rowWidth;
    FileHandling FileManagement;

    public Database(String filename) {
        FileManagement= new FileHandling(filename);
        FileManagement.paddedSpaces();
        rowWidth = FileManagement.getLargestString()*FileManagement.getCollums();
        this.filename = filename;
    }

    public void appendRecord(String data,boolean nextLine) {
        // TODO: Pad the data to the correct record width
        // TODO: Report an error if the data is too long for the record
        int testIfgoodData =data.split(",").length;
        if(testIfgoodData == FileManagement.getCollums()){
            FileManagement.WriteToFile(data,true, nextLine);
            FileManagement.paddedSpaces();
        }else{
            System.out.println("make sure you have entered all correct data values");
        }
        rowWidth = FileManagement.getLargestString()*FileManagement.getCollums();

    }

    public void deleteRecord(int rowNumber) {
        if(rowNumber>FileManagement.lineAmount||rowNumber<=0){
            System.out.println("tha row does not exist");
        }else {FileManagement.removeline(rowNumber);}

    }

    public String getRecord(int rowNumber) {
        rowNumber = rowNumber-1;
        return FileManagement.readLineAt(rowNumber * (rowWidth+2));
    }

    public int getRecordCount() {
        return FileManagement.lineAmount;
    }

    public boolean findRecord(String data,int rowNumber){
        String theline =  getRecord(rowNumber);
        String compareTo[] = theline.split("\\s+");
        int exists=FileManagement.getCollums()+1;

        for(int x = 0;x<FileManagement.getCollums();x++){

            if(data.compareTo(compareTo[x])==0){
                exists = x+1;// returns 1 to amoun of collums basically wheree in the line the data is
            }
        }

        if(exists == FileManagement.getCollums()+1){
            return false;
        }else {
            return true;}
    }


    public char[] readFromTo(int startat, int endat) {
        try (RandomAccessFile rf = new RandomAccessFile(filename, "rws")) {
            char read[] = new char[endat];
            for (int x = startat; x < endat; x++) {
                rf.seek(x);
                char letter = (char) rf.read();
                read[x] = letter;

            }
            return read;

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}

