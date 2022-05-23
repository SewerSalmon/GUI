package com.company.MapFolder;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class FileHandling {
    public int lineAmount =0;
    public String fileName;
    public int largestString;
    public int  collums;

    public FileHandling(String FileName) {
        fileName = FileName;
        LinesInFile();
    }

    public void LinesInFile(){
        lineAmount=0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while (br.readLine() != null) lineAmount++;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void paddedSpaces(){
        LinesInFile();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> line = new ArrayList<>();

            for (int x = 0; x < lineAmount; x++) {
                line.add(br.readLine());
            }

            String[] ToBeSplit = (line.toArray(String[]::new));
            String[] temp;
            int continu = 0;
            ArrayList<String> splited = new ArrayList<>();

            for (int x =0;x< lineAmount;x++) {
                temp = ToBeSplit[x].split(",");
                if(temp.length ==1){// if array is only 1
                    temp = ToBeSplit[x].split("\\s+");//if the row hasnt been split (e.g no commas as it may have been split previously) then it splits by he white space" "
                }

                if(temp.length ==1){

                }else {
                    for (int y = 0; y < temp.length; y++) {
                        splited.add(temp[y]);
                    }
                    continu = 1;
                }
            }
            if(continu == 1) {
                setLargestString(splited);
                setCollums(splited);


                int counts = 0;

                WriteToFile("", false, false);
                for (int y = 0; y < splited.size(); y++) {
                    WriteToFile(splited.get(y), true, false);
                    for (int x = 0; x < largestString  - splited.get(y).length(); x++) {
                        WriteToFile(" ", true, false);
                    }
                    counts++;
                    if (counts == collums) {
                        counts = 0;
                        WriteToFile("", true, true);
                    }
                }

            }
        }catch(IOException e){
            e.printStackTrace();
        }


    }

    public int getLargestString(){
        return largestString;
    }

    public int getCollums(){
        return collums;
    }

    public void setCollums(ArrayList<String> splited){
        collums = 0;
        collums = splited.size()/lineAmount;

    }

    public void setLargestString(ArrayList<String> splited){
        int largestTest=0;
        for(int i = 0; i < splited.size(); i++){
            if(splited.get(i).length() > largestTest){
                largestTest = splited.get(i).length();

            }

        }
        if(largestTest>largestString){
            largestString = largestTest+5;
        }
    }

    public String readLineAt(int start) {
        // grab the line from position "start" in the file
        try (RandomAccessFile rf = new RandomAccessFile(fileName, "rws")) {
            rf.seek(start);
            return rf.readLine();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void removeline(int toRemove){
        LinesInFile();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            ArrayList<String> line = new ArrayList<>();

            for (int x = 0; x < lineAmount; x++) {
                line.add(br.readLine());
            }

            line.remove(toRemove-1);// -1 as it indexees at 0

            int counts = 0;

            WriteToFile("", false, false);
            for (int y = 0; y < line.size(); y++) {
                WriteToFile(line.get(y), true, true);
                counts++;
            }
            LinesInFile();

        }catch(IOException e){
            e.printStackTrace();
        }

    }

    public void WriteToFile(String text,Boolean append, Boolean NextLine){
        try (//true = new line false = replace whole file
             FileWriter fw = new FileWriter(fileName, append);
             PrintWriter pw = new PrintWriter(fw);
        ){
            if(NextLine == true){
                pw.println(text);
            }else {pw.print(text);}
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }


}

