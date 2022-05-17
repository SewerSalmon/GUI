package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame{

   GUI gui = new GUI();

    MyFrame(){
        this.setTitle("tets");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().add(gui);
        gui.buttonAdd("Isfan",150,100,100,100);
        gui.buttonAdd("Giri",150,100,0,0);
        ButtonGroup bg = new ButtonGroup();
        gui.radioButtonAdd("Hedgehog",100,20,100,200,bg);
        gui.radioButtonAdd("Boat",100,90,100,200,bg);



        this.setVisible(true);
    }


}