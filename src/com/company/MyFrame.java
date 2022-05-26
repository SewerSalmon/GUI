package com.company;
import javax.swing.*;
import java.io.File;

public class MyFrame extends JFrame{

   GUI gui = new GUI();

    MyFrame(File saveFile){
        this.setTitle(saveFile.getName());
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(gui);
        gui.buttonAdd("Isfan",10,0,80,40);
        gui.buttonAdd("Giri",100,0,80,40);
        ButtonGroup bg = new ButtonGroup();
        gui.radioButtonAdd("0",200,0,80,40,bg);
        gui.radioButtonAdd("1",300,0,80,40,bg);
        gui.radioButtonAdd("2",400,0,80,40,bg);
        gui.radioButtonAdd("3",500,0,80,40,bg);
        this.setVisible(true);
    }


}