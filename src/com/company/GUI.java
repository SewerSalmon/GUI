package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel {

    public GUI() {
    }

    public void buttonAdd(String name, int width, int height, int x, int y) {
        JButton button1;
        this.setPreferredSize(new Dimension(width, height));
        button1 = new JButton(name);
        button1.setBounds(x, y, width, height);
        button1.setLocation(new Point(x, y));
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
            r1.setLocation(new Point(x,y));
            r1.addActionListener(this::actionPerformedRadio);
            e.add(r1);
            add(r1);
        }
        public void actionPerformedRadio(ActionEvent e){
            if(e.getActionCommand().equals("Male")){
                DragPanel("enhanced-buzz-1492-1379411828-15.jpg");
                imageCorner.setLocation(prevPt);
                repaint();
            }
            if(e.getActionCommand().equals("Female")){
                DragPanel("image.jpg");
                imageCorner.setLocation(prevPt);
                repaint();
            }
    }
    }









