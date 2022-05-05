package com.company;
import javax.swing.JFrame;

public class MyFrame extends JFrame{

   GUI gui = new GUI();

    MyFrame(){
        add(gui);
        gui.buttonAdd("Isfan",200,100,1000,900);
        gui.buttonAdd("Giri",200,100,0,0);
        gui.DragPanel("enhanced-buzz-1492-1379411828-15.jpg");
        gui.radioButtonAdd();

        this.setTitle("tets");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

    }
}