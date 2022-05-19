package com.company;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class SelectSave extends JPanel {
    private JFrame frame = new JFrame("Choose");

    String a = "C:/Users/16Walker_B/Desktop/asda.txt";
    String b = "C:/Users/16Walker_B/Desktop/left.txt";
    File Os = new File(b);
    File Cs = new File(a);

    public SelectSave(){
        setSize(200,300);
        frame.setSize(200, 250);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        this.setLayout(null);

        if(Cs.exists()) {
            JButton button1;
            button1 = new JButton("Current Save");
            button1.setBounds(17,10,150,50);
            button1.addActionListener(new ClickButton());
            add(button1);
        }

        if(Os.exists()) {
            //read
            JButton button2;
            button2 = new JButton("Overall Save");
            button2.setBounds(17, 70, 150, 50);
            button2.addActionListener(new ClickButton());
            add(button2);
        }else {
            try{Os.createNewFile();
            }catch (IOException e){
                e.printStackTrace();
            }
            JButton button2;
            button2 = new JButton("Overall Save");
            button2.setBounds(17, 70, 150, 50);
            button2.addActionListener(new ClickButton());
            add(button2);
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
            if(e.getActionCommand().equals("Current Save")){
                MyFrame mf = new MyFrame(Cs);
                frame.dispose();
            }else if (e.getActionCommand().equals("Overall Save")){
                MyFrame mf = new MyFrame(Os);
                frame.dispose();
            }else {
                JFrame ff = new JFrame();
               ff.setSize(120, 50);
               ff.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
               ff.setLayout(null);
                File selectedFile = null;
                JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("TEXT FILES", "txt", "text");
                fileChooser.setFileFilter(filter);
                fileChooser.setCurrentDirectory(new File("C:/"));
                int result = fileChooser.showOpenDialog(ff);
                if (result == JFileChooser.APPROVE_OPTION) {
                    selectedFile = fileChooser.getSelectedFile();
                }
                if(selectedFile.getName().endsWith(".txt")){
                    MyFrame mf = new MyFrame(selectedFile);
                    frame.dispose();
                }else {

                    JLabel l = new JLabel("That file is not allowed");
             l.setBounds(0,0,200,10);
                    ff.add(l);
                    ff.setVisible(true);
                }

            }

        }
    }



}
