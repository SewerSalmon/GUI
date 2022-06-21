package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel {
    public GUI() {
        this.setLayout(null);
    }

    public void buttonAdd(String name, int x, int y,  int width, int height) {
        JButton button;
        button = new JButton(name);
        button.setBounds(x, y, width, height);
        button.addActionListener(new ClickButton());
        add(button);
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
    Point prevPt = new Point(0, 0);

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

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!(imageCorner == null)) {
            image.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());
        }
    }

    boolean ClickedInImage;

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            if ((e.getPoint().getX() <= imageCorner.getX() + WIDTH && e.getPoint().getX() >= imageCorner.getX()) && (e.getPoint().getY() <= imageCorner.getY() + HEIGHT && e.getPoint().getY() >= imageCorner.getY())) {
                prevPt = e.getPoint();
                ClickedInImage = true;
            } else {
                ClickedInImage = false;
            }
        }
    }

    private class DragListener extends MouseMotionAdapter {

        public void mouseDragged(MouseEvent e) {
            if (ClickedInImage) {
                Point currentPt = e.getPoint();//translate moves object by that much
                imageCorner.translate((int) (currentPt.getX() - prevPt.getX()), (int) (currentPt.getY() - prevPt.getY()));
                prevPt = currentPt;
                repaint();

            }
        }
    }

    public void radioButtonAdd (String name,int x,int y,int width,int height, ButtonGroup e){
            JRadioButton r1;
            r1=new JRadioButton(name);
            r1.setBounds(x,y,width,height);
            r1.addActionListener(this::actionPerformedRadio);
            e.add(r1);
            add(r1);
        }
        public void actionPerformedRadio(ActionEvent e){
            if(e.getActionCommand().equals("0")){
                DragPanel("Map Squares/Street.jpg");
                imageCorner.setLocation(prevPt);
                repaint();
            }
            if(e.getActionCommand().equals("1")){
                DragPanel("Map Squares/StarThing.jpg");
                imageCorner.setLocation(prevPt);
                repaint();
            }
            if(e.getActionCommand().equals("2")){
                DragPanel("Map Squares/Something.jpg");
                imageCorner.setLocation(prevPt);
                repaint();
            }
            if(e.getActionCommand().equals("3")){
                DragPanel("Map Squares/Stairs.jpg");
                imageCorner.setLocation(prevPt);
                repaint();
            }
    }
    }









