package com.company.MapFolder;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

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
                    temp = ToBeSplit[x].split("\\s+");//if the row hasn't been split (e.g no commas as it may have been split previously) then it splits by the white space" "
                }
                if(temp.length ==1){

                }else {
                    splited.addAll(Arrays.asList(temp));
                    continu = 1;
                }
            }
            if(continu == 1) {
                setLargestString(splited);
                setCollums(splited);

                int counts = 0;

                WriteToFile("", false, false);
                for (String s : splited) {
                    WriteToFile(s, true, false);
                    for (int x = 0; x < largestString - s.length(); x++) {
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

        collums = splited.size()/lineAmount;

    }

    public void setLargestString(ArrayList<String> splited){
        int largestTest=0;
        for (String s : splited) {
            if (s.length() > largestTest) {
                largestTest = s.length();

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

    public String getRecord(int rowNumber) {
        return readLineAt(rowNumber * ((getLargestString()*getCollums())+2));
    }


    public void WriteToFile(String text,Boolean append, Boolean NextLine){
        try (//true = new line false = replace whole file
             FileWriter fw = new FileWriter(fileName, append);
             PrintWriter pw = new PrintWriter(fw)
        ){
            if(NextLine){
                pw.println(text);
            }else {pw.print(text);}
        }
        catch(IOException e){
            e.printStackTrace();
        }

    }
}

