package com.company;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyFrame extends JFrame{

   GUI gui = new GUI();

    MyFrame(){

        add(gui);
        gui.buttonAdd("Isfan",200,100,100,900);
        gui.buttonAdd("Giri",200,100,0,0);

        ButtonGroup bg = new ButtonGroup();
        gui.radioButtonAdd("Male",100,20,100,200,bg);
        gui.radioButtonAdd("Female",100,20,100,200,bg);

        this.setTitle("tets");
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private class ClickButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
        }
    }
}