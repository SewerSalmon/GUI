package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class BiomeDisplay extends JPanel{
    // make it swap map not make enterly new frame
    ArrayList<JButton> biomes = new ArrayList<>();
    JButton sort[] = new JButton[3];
    ImageIcon image;
    Point imageCorner;
    String currentBiome;
    MyFrame change;

    public BiomeDisplay(String biome, MyFrame a){
        change = a;
        currentBiome = biome;
        this.setLayout(null);
        File[] folder = new File("Map Squares/").listFiles();

        int xPos = 10;
        for(File file: folder){
            boolean exists = false;
            String name = file.getName().substring(1,file.getName().length()-4);
            for(int x = 0 ;x<biomes.size();x++){
                if(name.equals(biomes.get(x).getName())){
                   exists = true;
               }
            }
            if(!exists) {
                biomes.add(buttonAdd(name, xPos, 0, 100, 40));
                xPos = xPos+ 110;
            }
        }
        sort[0] = buttonAdd("Alphabetical",100,100,80,40);
        sort[1] = buttonAdd("shuffle",180,100,80,40);
        sort[2] = buttonAdd("ReverseAlpha",260,100,80,40);
        JButton back = buttonAdd("back to map",400,500,100,80);
    }
    public JButton buttonAdd(String name, int x, int y,  int width, int height) {
        JButton button;
        button = new JButton(name);
        button.setBounds(x, y, width, height);
        button.addActionListener(new ClickButton());
        button.setName(name);
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
            }
            else if (e.getActionCommand().equals("shuffle")) {
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
            }
            else if (e.getActionCommand().equals("ReverseAlpha")) {
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
            }
            else if(e.getActionCommand().equals("back to map")){
                change.toMap();
            } else {
                //display whole biome
                    image = new ImageIcon("Map Squares/"+ e.getActionCommand() +".jpg");
                    imageCorner = new Point(0, 0);
                    repaint();
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

        public Object[] shuffle(Object[] array){
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

        void swap(Object[] array, int index1, int index2){
            Object temp = array[index1];
            array[index1] = array[index2];
            array[index2] = temp;
        }
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!(imageCorner == null)) {
            image.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());
        }
    }


}