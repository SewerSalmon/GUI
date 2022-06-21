package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BiomeDisplay extends JPanel{
    ArrayList<JButton> biomes = new ArrayList<>();
    JButton sort;
    ImageIcon image;
    Point imageCorner;

    public BiomeDisplay(){
        setSize(500,500);
        JFrame frame = new JFrame("Biomes");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        this.setLayout(null);
        biomes.add(buttonAdd("StarThing",5,0,80,40));
        biomes.add(buttonAdd("Stairs",90,0,80,40));
        biomes.add(buttonAdd("Something",260,0,80,40));
        biomes.add(buttonAdd("Street",345,0,80,40));
        sort = buttonAdd("Alphabetical",100,100,80,40);
        sort = buttonAdd("shuffle",180,100,80,40);
        sort = buttonAdd("ReverseAlpha",260,100,80,40);
        frame.setVisible(true);
    }

    public JButton buttonAdd(String name, int x, int y,  int width, int height) {
        JButton button;
        button = new JButton(name);
        button.setBounds(x, y, width, height);
        button.addActionListener(new ClickButton());
        add(button);
        return button;
    }


    private class ClickButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getActionCommand().equals("Alphabetical")) {
                String[] names = new String[biomes.size()];

                for (int i = 0; i < names.length; i++) names[i] = biomes.get(i).getActionCommand();

                names = (String[]) alphabetical(names, false);

                for (int i = 0; i < names.length; i++) {
                   for (int y = 0; y<names.length;y++){
                       if (names[i].equals(biomes.get(y).getActionCommand())){
                          biomes.get(y).setBounds(5+(i*85),0,80,40);
                       }
                   }
                }
            }else if (e.getActionCommand().equals("shuffle")) {
                String[] names = new String[biomes.size()];

                for (int i = 0; i < names.length; i++) names[i] = biomes.get(i).getActionCommand();

                names = (String[]) shuffle(names);

                for (int i = 0; i < names.length; i++) {
                    for (int y = 0; y<names.length;y++){
                        if (names[i].equals(biomes.get(y).getActionCommand())){
                            biomes.get(y).setBounds(5+(i*85),0,80,40);
                        }
                    }
                }
            }else if (e.getActionCommand().equals("ReverseAlpha")) {
                String[] names = new String[biomes.size()];

                for (int i = 0; i < names.length; i++) names[i] = biomes.get(i).getActionCommand();

                names = (String[]) alphabetical(names, true);

                for (int i = 0; i < names.length; i++) {
                    for (int y = 0; y<names.length;y++){
                        if (names[i].equals(biomes.get(y).getActionCommand())){
                            biomes.get(y).setBounds(5+(i*85),0,80,40);
                        }
                    }
                }
            } else {
                    image = new ImageIcon("Map Squares/"+ e.getActionCommand() +".jpg");
                    imageCorner = new Point(0, 0);
                    repaint();

            }
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!(imageCorner == null)) {
            image.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());
        }
    }

    public Comparable[]  alphabetical(Comparable[] array, boolean reverse){
        int smallest;
        for(int i = 0; i < array.length; i++)
        {
            smallest = i; // set first element as smallest
            for(int j = i + 1; j < array.length; j++) // find smallest
                if(array[j].compareTo(array[smallest]) < 0)
                    smallest = j;
            if(smallest != i)
                swap(array, smallest, i);
        }

        if(reverse){
            for (int i = 0; i < array.length / 2; i++) {
                swap(array,i,array.length - 1 - i);
            }
        }
        return array;
    }
    public static Object[] shuffle(Object[] array){
        // If running on Java 6 or older, use `new Random()` on RHS here
        Random rnd = new Random();
        for (int i = array.length - 1; i > 0; i--)
        {
            swap(array,rnd.nextInt(i + 1),i);
        }
        for (int i = array.length - 1; i > 0; i--)
        {
            swap(array,rnd.nextInt(i + 1),i);
        }
        return array;
    }
    static void swap(Object[] array, int index1, int index2){
        Object temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }
}



