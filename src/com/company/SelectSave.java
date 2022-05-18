package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SelectSave extends JPanel {
    private JFrame frame = new JFrame("Choose");

    String a = "C:/Users/16Walker_B/Desktop/asda.txt";
    String b = "C:/Users/16Walker_B/Desktop/left.txt";
    String c;

    public SelectSave(){
        setSize(200,300);
        frame.setSize(200, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        this.setLayout(null);

        File f = new File(a);
        if(f.exists()) {
            JButton button1;
            button1 = new JButton("Current Save");
            button1.setBounds(17,10,150,50);
            button1.addActionListener(new ClickButton());
            add(button1);
        }
        File g = new File(a);
        if(g.exists()) {
            JButton button2;
            button2 = new JButton("Overall Save");
            button2.setBounds(17, 70, 150, 50);
            button2.addActionListener(new ClickButton());
            add(button2);
        }else {
        }

        JButton button3;
        button3 = new JButton("Choose Save");
        button3.setBounds(17,130,150,50);
        button3.addActionListener(new ClickButton());
        add(button3);

        frame.setVisible(true);
    }


    private class ClickButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
            MyFrame mf = new MyFrame();
            frame.dispose();
        }
    }



}
