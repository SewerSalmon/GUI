package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel {
    public GUI() {
    }

    public void buttonAdd(String name, int width, int height, int x, int y) {
        JButton button1;
        System.out.println("SQUENCE: Added Button " + name);
        this.setPreferredSize(new Dimension(width, height));
        button1 = new JButton(name);
        button1.setBounds(x, y, width, height);
        button1.addActionListener(new ClickButton());
        add(button1);
    }

    private class ClickButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            System.out.println(e.getActionCommand());
        }
    }

        ImageIcon image;
        int WIDTH;
        int HEIGHT;
        Point imageCorner;
        Point prevPt;
        public void DragPanel(String FileLocation) {
            image = new ImageIcon(FileLocation);
            WIDTH = image.getIconWidth();
            HEIGHT = image.getIconHeight();
            imageCorner = new Point(0, 0);
            ClickListener clickListener = new ClickListener();
            DragListener dragListener = new DragListener();
            this.addMouseListener(clickListener);
            this.addMouseMotionListener(dragListener);
        }

        public void paintComponent(Graphics g) {
            super.paintComponent(g);
            image.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());
        }

        private class ClickListener extends MouseAdapter {
            public void mousePressed(MouseEvent e) {
                prevPt = e.getPoint();
            }
        }

        private class DragListener extends MouseMotionAdapter {

            public void mouseDragged(MouseEvent e) {
                Point currentPt = e.getPoint();//translate moves object by that much
                imageCorner.translate((int) (currentPt.getX() - prevPt.getX()), (int) (currentPt.getY() - prevPt.getY()));
                prevPt = currentPt;
                repaint();
            }
        }


    JRadioButton r1,r2;
    public void radioButtonAdd (){
            r1=new JRadioButton("Male");
            r2=new JRadioButton("Female");
            r1.setBounds(75,50,100,30);
            r2.setBounds(75,100,100,30);
            ButtonGroup bg = new ButtonGroup();
            r1.addActionListener(this::actionPerformedRadio);
            r2.addActionListener(this::actionPerformedRadio);
            bg.add(r1);bg.add(r2);
            add(r1);add(r2);
        }
        public void actionPerformedRadio(ActionEvent e){
        if(r1.isSelected()){
            System.out.println("a");
        }
        if(r2.isSelected()){
            System.out.println("b");
        }
    }
    }









