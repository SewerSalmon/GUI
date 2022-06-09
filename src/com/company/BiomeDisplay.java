package com.company;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class BiomeDisplay extends JPanel{
    ArrayList<JButton> biomes = new ArrayList<>();
    JButton sort;


    public BiomeDisplay(){
        setSize(500,500);
        JFrame frame = new JFrame("Biomes");
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.add(this);
        this.setLayout(null);
        biomes.add(buttonAdd("Forest",5,0,80,40));
        biomes.add(buttonAdd("SnowArea",90,0,80,40));
        biomes.add(buttonAdd("City",175,0,80,40));
        biomes.add(buttonAdd("Tundra",260,0,80,40));
        biomes.add(buttonAdd("Beach",345,0,80,40));
        sort = buttonAdd("Sort",180,100,80,40);
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
            System.out.println(e.getActionCommand());
            if (e.getActionCommand().equals("Sort")) {
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



