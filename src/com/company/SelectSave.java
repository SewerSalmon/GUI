package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SelectSave extends JPanel {
    private JFrame frame = new JFrame("Choose");

    public SelectSave(){
        frame.setSize(200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);

        JButton button1;
        this.setPreferredSize(new Dimension(200,100));
        button1 = new JButton("Current Save");
        button1.setBounds(0,0,200,100);
        button1.addActionListener(new ClickButton());
        add(button1);

        JButton button2;
        this.setPreferredSize(new Dimension(200,100));
        button2 = new JButton("Overall Save");
        button2.setBounds(0,100,200,100);
        button2.addActionListener(new ClickButton());
        add(button2);

        JButton button3;
        this.setPreferredSize(new Dimension(200,100));
        button3 = new JButton("Choose Save");
        button3.setBounds(0,100,200,100);
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
