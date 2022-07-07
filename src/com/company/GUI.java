package com.company;

import com.company.MapFolder.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JPanel {
    Map map = new Map();
    MyFrame change;
    public GUI(MyFrame a) {
        change = a;
        this.setLayout(null);
        buttonAdd("Isfan",10,0,80,40);
        buttonAdd("Giri",100,0,80,40);
        map.CreateSurroundings();
        DragPanel("Map Squares/" +map.getCurrent().name()+".jpg",new Point(0,0));
        imageCorner.setLocation(prevPt);
        repaint();
    }

    public void buttonAdd(String name, int x, int y, int width, int height) {
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

    public void DragPanel(String FileLocation,Point point) {
        image = new ImageIcon(FileLocation);
        WIDTH = image.getIconWidth();
        HEIGHT = image.getIconHeight();
        imageCorner = point;
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
            //hover over show stats?
            ImageIcon surrondingImage;
            map.CreateSurroundings();
            if(map.getCurrent().getDown() != null){
                surrondingImage = new ImageIcon("Map Squares/" +map.getCurrent().getDown().name()+".jpg");
                surrondingImage.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY()+500);
            }
            if(map.getCurrent().getUp() != null){
                surrondingImage = new ImageIcon("Map Squares/" +map.getCurrent().getUp().name()+".jpg");
                surrondingImage.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY()-500);
            }
            if(map.getCurrent().getLeft() != null){
                surrondingImage = new ImageIcon("Map Squares/" +map.getCurrent().getLeft().name()+".jpg");
                surrondingImage.paintIcon(this, g, (int) imageCorner.getX()-500, (int) imageCorner.getY());
            }
            if(map.getCurrent().getRight() != null){
                surrondingImage = new ImageIcon("Map Squares/" +map.getCurrent().getRight().name()+".jpg");
                surrondingImage.paintIcon(this, g, (int) imageCorner.getX()+500, (int) imageCorner.getY());
            }
            image.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());
        }
    }

    boolean ClickedInImage;

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            ClickedInImage = false;
            if (e.getButton() == MouseEvent.BUTTON1) {
                if ((e.getPoint().getX() <= imageCorner.getX() + WIDTH && e.getPoint().getX() >= imageCorner.getX()) && (e.getPoint().getY() <= imageCorner.getY() + HEIGHT && e.getPoint().getY() >= imageCorner.getY())) {
                    prevPt = e.getPoint();
                    ClickedInImage = true;
                } else {
                    ClickedInImage = false;
                }
                if ((e.getPoint().getX() <= imageCorner.getX() + WIDTH && e.getPoint().getX() >= imageCorner.getX()) && (e.getPoint().getY() <= imageCorner.getY() + 500 + HEIGHT && e.getPoint().getY() >= imageCorner.getY() + 500) && map.getCurrent().getDown() != null) {
                    imageCorner = new Point((int) imageCorner.getX(), (int) imageCorner.getY() + 500);
                    image = new ImageIcon("Map Squares/" + map.getCurrent().getDown().name() + ".jpg");
                    map.SetCurrent(map.getCurrent().getDown());
                    prevPt = e.getPoint();
                    ClickedInImage = true;
                }
                if ((e.getPoint().getX() <= imageCorner.getX() + WIDTH && e.getPoint().getX() >= imageCorner.getX()) && (e.getPoint().getY() <= imageCorner.getY() - 500 + HEIGHT && e.getPoint().getY() >= imageCorner.getY() - 500) && map.getCurrent().getUp() != null) {
                    imageCorner = new Point((int) imageCorner.getX(), (int) imageCorner.getY() - 500);
                    image = new ImageIcon("Map Squares/" + map.getCurrent().getUp().name() + ".jpg");
                    map.SetCurrent(map.getCurrent().getUp());
                    prevPt = e.getPoint();
                    ClickedInImage = true;
                }
                if ((e.getPoint().getX() <= imageCorner.getX() + 500 + WIDTH && e.getPoint().getX() >= imageCorner.getX() + 500) && (e.getPoint().getY() <= imageCorner.getY() + HEIGHT && e.getPoint().getY() >= imageCorner.getY()) && map.getCurrent().getRight() != null) {
                    imageCorner = new Point((int) imageCorner.getX() + 500, (int) imageCorner.getY());
                    image = new ImageIcon("Map Squares/" + map.getCurrent().getRight().name() + ".jpg");
                    map.SetCurrent(map.getCurrent().getRight());
                    prevPt = e.getPoint();
                    ClickedInImage = true;
                }
                if ((e.getPoint().getX() <= imageCorner.getX() - 500 + WIDTH && e.getPoint().getX() >= imageCorner.getX() - 500) && (e.getPoint().getY() <= imageCorner.getY() + HEIGHT && e.getPoint().getY() >= imageCorner.getY()) && map.getCurrent().getLeft() != null) {
                    imageCorner = new Point((int) imageCorner.getX() - 500, (int) imageCorner.getY());
                    image = new ImageIcon("Map Squares/" + map.getCurrent().getLeft().name() + ".jpg");
                    map.SetCurrent(map.getCurrent().getLeft());
                    prevPt = e.getPoint();
                    ClickedInImage = true;
                }
            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                BiomeDisplay BD;
                if ((e.getPoint().getX() <= imageCorner.getX() + WIDTH && e.getPoint().getX() >= imageCorner.getX()) && (e.getPoint().getY() <= imageCorner.getY() + HEIGHT && e.getPoint().getY() >= imageCorner.getY())) {
                    change.toBiome(map.getCurrent().name().substring(1));
                }
                if ((e.getPoint().getX() <= imageCorner.getX() + 500 + WIDTH && e.getPoint().getX() >= imageCorner.getX() + 500) && (e.getPoint().getY() <= imageCorner.getY() + HEIGHT && e.getPoint().getY() >= imageCorner.getY())) {
                    change.toBiome(map.getCurrent().name().substring(1));
                }
                if ((e.getPoint().getX() <= imageCorner.getX() - 500 + WIDTH && e.getPoint().getX() >= imageCorner.getX() - 500) && (e.getPoint().getY() <= imageCorner.getY() + HEIGHT && e.getPoint().getY() >= imageCorner.getY()) && map.getCurrent().getLeft() != null) {
                    change.toBiome(map.getCurrent().getLeft().name().substring(1));
                }
                if ((e.getPoint().getX() <= imageCorner.getX() + WIDTH && e.getPoint().getX() >= imageCorner.getX()) && (e.getPoint().getY() <= imageCorner.getY() - 500 + HEIGHT && e.getPoint().getY() >= imageCorner.getY() - 500) && map.getCurrent().getUp() != null) {
                    change.toBiome(map.getCurrent().getUp().name().substring(1));
                }
                if ((e.getPoint().getX() <= imageCorner.getX() + WIDTH && e.getPoint().getX() >= imageCorner.getX()) && (e.getPoint().getY() <= imageCorner.getY() + 500 + HEIGHT && e.getPoint().getY() >= imageCorner.getY() + 500) && map.getCurrent().getDown() != null) {
                    change.toBiome(map.getCurrent().getDown().name().substring(1));
                }
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
}