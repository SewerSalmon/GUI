package com.company;

import com.company.MapFolder.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;

public class GUI extends JPanel {
    String[] mapSquares;
    String currentSquare;
    MyFrame change;

    public GUI(MyFrame a) {
        change = a;
        this.setLayout(null);
        buttonAdd("Isfan", 10, 0, 80, 40);
        buttonAdd("Giri", 100, 0, 80, 40);
        File[] folder = new File("Map Squares/").listFiles();
        mapSquares = new String[folder.length];
        int counter = 0;
        for (File file : folder) {
            mapSquares[counter] = file.getName();
            counter++;
        }

        currentSquare = mapSquares[2];
        DragPanel("Map Squares/" + currentSquare, new Point(0, 0));
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

    public void DragPanel(String FileLocation, Point point) {
        image = new ImageIcon(FileLocation);
        WIDTH = image.getIconWidth();
        HEIGHT = image.getIconHeight();
        imageCorner = point;
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        this.addMouseListener(clickListener);
        this.addMouseMotionListener(dragListener);
    }

    Point imagesPositive;
    Point imagesNegative;

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        imagesPositive = new Point(0, 0);
        imagesNegative = new Point(0, 0);

        if (!(imageCorner == null)) {
            image.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());

            for (int x = 0; x < mapSquares.length; x++) {
                if (Math.abs(Integer.parseInt(currentSquare.split("\\.")[0]) - Integer.parseInt(mapSquares[x].split("\\.")[0])) <= 2) {
                    if (Math.abs(Integer.parseInt(currentSquare.split("\\.")[1]) - Integer.parseInt(mapSquares[x].split("\\.")[1])) <= 2) {

                        if (Integer.parseInt(mapSquares[x].split("\\.")[1]) - Integer.parseInt(currentSquare.split("\\.")[1]) > imagesPositive.getX()) {
                            imagesPositive.setLocation(Integer.parseInt(mapSquares[x].split("\\.")[1]) - Integer.parseInt(currentSquare.split("\\.")[1]), imagesPositive.getY());
                        }
                        if (Integer.parseInt(mapSquares[x].split("\\.")[1]) - Integer.parseInt(currentSquare.split("\\.")[1]) < imagesNegative.getX()) {
                            imagesNegative.setLocation(Integer.parseInt(mapSquares[x].split("\\.")[1]) - Integer.parseInt(currentSquare.split("\\.")[1]), imagesNegative.getY());
                        }
                        if (Integer.parseInt(mapSquares[x].split("\\.")[0]) - Integer.parseInt(currentSquare.split("\\.")[0]) > imagesPositive.getY()) {
                            imagesPositive.setLocation(imagesPositive.getX(), Integer.parseInt(mapSquares[x].split("\\.")[0]) - Integer.parseInt(currentSquare.split("\\.")[0]));
                        }
                        if (Integer.parseInt(mapSquares[x].split("\\.")[0]) - Integer.parseInt(currentSquare.split("\\.")[0]) < imagesNegative.getY()) {
                            imagesNegative.setLocation(imagesNegative.getX(), Integer.parseInt(mapSquares[x].split("\\.")[0]) - Integer.parseInt(currentSquare.split("\\.")[0]));
                        }

                        int moveX = 500 * (Integer.parseInt(mapSquares[x].split("\\.")[1]) - Integer.parseInt(currentSquare.split("\\.")[1]));
                        int moveY = 500 * (Integer.parseInt(mapSquares[x].split("\\.")[0]) - Integer.parseInt(currentSquare.split("\\.")[0]));

                        (new ImageIcon("Map Squares/" + mapSquares[x])).paintIcon(this, g, (int) imageCorner.getX() + moveX, (int) imageCorner.getY() + moveY);
                    }
                }

            }
        }
    }


    boolean ClickedInImage;

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            ClickedInImage = false;
            if (e.getButton() == MouseEvent.BUTTON1) {
                if ((e.getPoint().getX() <= imageCorner.getX() + WIDTH + (500 * imagesPositive.getX()) && e.getPoint().getX() >= imageCorner.getX() + (500 * imagesNegative.getX())) && (e.getPoint().getY() <= imageCorner.getY() + HEIGHT + (500 * imagesPositive.getY()) && e.getPoint().getY() >= imageCorner.getY() + (500 * imagesNegative.getY()))) {
                    prevPt = e.getPoint();
                    int addX = 0;
                    int addY = 0;
                    int checkX = Integer.parseInt(currentSquare.split("\\.")[1]);
                    int checkY = Integer.parseInt(currentSquare.split("\\.")[0]);

                    if (e.getPoint().getX() - imageCorner.getX() > 1000) {
                        System.out.println("a");
                        checkX = Integer.parseInt(currentSquare.split("\\.")[1]) + 2;
                        addX = 1000;
                    } else if (e.getPoint().getX() - imageCorner.getX() > 500) {
                        checkX = Integer.parseInt(currentSquare.split("\\.")[1]) + 1;
                        System.out.println("b");
                        addX = 500;
                    }
                    if (e.getPoint().getX() - imageCorner.getX() < -500) {
                        checkX = Integer.parseInt(currentSquare.split("\\.")[1]) - 2;
                        System.out.println("c");
                        addX = -1000;
                    } else if (e.getPoint().getX() - imageCorner.getX() < 0) {
                        checkX = Integer.parseInt(currentSquare.split("\\.")[1]) - 1;
                        System.out.println("d");
                        addX = -500;
                    }

                    if (e.getPoint().getY() - imageCorner.getY() > 1000) {
                        checkY = Integer.parseInt(currentSquare.split("\\.")[0]) + 2;
                        System.out.println("1");
                        addY = 1000;
                    } else if (e.getPoint().getY() - imageCorner.getY() > 500) {
                        checkY = Integer.parseInt(currentSquare.split("\\.")[0]) + 1;
                        System.out.println("2");
                        addY = 500;
                    }
                    if (e.getPoint().getY() - imageCorner.getY() < -500) {
                        checkY = Integer.parseInt(currentSquare.split("\\.")[0]) - 2;
                        System.out.println("3");
                        addY = -1000;
                    } else if (e.getPoint().getY() - imageCorner.getY() < 0) {
                        checkY = Integer.parseInt(currentSquare.split("\\.")[0]) - 1;
                        System.out.println("4");
                        addY = -500;
                    }


                    for (int x = 0; x < mapSquares.length; x++) {
                        if (mapSquares[x].contains(checkY + "." + checkX)) {
                            currentSquare = mapSquares[x];
                            continue;
                        }
                    }

                    imageCorner.setLocation(imageCorner.getX() + addX, imageCorner.getY() + addY);


                    image = new ImageIcon("Map Squares/" + currentSquare);

                    ClickedInImage = true;
                } else {
                    ClickedInImage = false;
                }

            }
            if (e.getButton() == MouseEvent.BUTTON3) {
                if ((e.getPoint().getX() <= imageCorner.getX() + WIDTH + (500 * imagesPositive.getX()) && e.getPoint().getX() >= imageCorner.getX() + (500 * imagesNegative.getX())) && (e.getPoint().getY() <= imageCorner.getY() + HEIGHT + (500 * imagesPositive.getY()) && e.getPoint().getY() >= imageCorner.getY() + (500 * imagesNegative.getY()))) {
                   change.toBiome();
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