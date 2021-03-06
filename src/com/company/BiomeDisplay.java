package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class BiomeDisplay extends JPanel{

    // make it swap map not make enterly new frame
    JButton sort[] = new JButton[3];
    ImageIcon image;
    Point imageCorner;
    MyFrame change;
    File[] folder;
    String currentBiome;
    JComboBox<String> biomes = new JComboBox(){
        @Override public void addItem(Object obj){
        int count = getItemCount();
        String toAdd = (String) obj;

        ArrayList<String> items = new ArrayList<String>();
        for(int i = 0; i < count; i++){
            items.add((String)getItemAt(i));
        }

        if(items.size() == 0){
            super.addItem(toAdd);
            return;
        }else{
            if(toAdd.compareTo(items.get(0)) <= 0){
                insertItemAt(toAdd, 0);
            }else{
                int lastIndexOfHigherNum = 0;
                for(int i = 0; i < count; i++){
                    if(toAdd.compareTo(items.get(i)) > 0){
                        lastIndexOfHigherNum = i;
                    }
                }
                insertItemAt(toAdd, lastIndexOfHigherNum+1);
            }
        }
    }
    };

    public BiomeDisplay(MyFrame a,String biome){

        this.setBackground(Color.black);
        change = a;
        this.setLayout(null);
        biomes.setBounds(10,10,200,100);
        ActionListener switchDisplay = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println((String) biomes.getSelectedItem());
                biomeChange((String) biomes.getSelectedItem());
            }
        };
        biomes.addActionListener(switchDisplay);
        add(biomes);

        folder = new File("Map Squares/").listFiles();
        for(File file: folder){
            boolean exists = false;
            String name = file.getName().substring(0,file.getName().length()-4).replaceAll("[0-9]","");
            name =  name.replace(".", "");
            for(int x = 0 ;x<biomes.getItemCount();x++){
                if(name.equals(biomes.getItemAt(x))){
                   exists = true;
               }
            }
            if(!exists) {
                biomes.addItem(name);
            }
        }
//        sort[0] = buttonAdd("Alphabetical",100,100,80,40);
//        sort[1] = buttonAdd("shuffle",180,100,80,40);
//        sort[2] = buttonAdd("ReverseAlpha",260,100,80,40);
        JButton back = buttonAdd("Back to map",400,500,100,80);
        biomeChange(biome);
        biomes.setSelectedItem(currentBiome);
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

    ArrayList<String> imagesInBiome = new ArrayList<String>();
    private class ClickButton implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
         /*   if (e.getActionCommand().equals("Alphabetical")) {
                String[] names = new String[biomes.getItemCount()];
                for (int i = 0; i < names.length; i++) names[i] = biomes.getItemAt(i);
                names = (String[]) alphabetical(names, false);
                for (int i = 0; i < names.length; i++) {
                   for (int y = 0; y<names.length;y++){
                       if (names[i].equals(biomes.getItemAt(y))){

                          biomes.get(y).setBounds(10+(i*100),0,100,40);
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
                            biomes.get(y).setBounds(10+(i*100),0,100,40);
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
                            biomes.get(y).setBounds(10+(i*100),0,100,40);
                        }
                    }
                }
            }*/
            if(e.getActionCommand().equals("Back to map")){
                change.toMap();
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

    public void biomeChange(String biome){
        imagesInBiome.clear();
        for(File file: folder){
            if(file.getName().contains(biome)){
                imagesInBiome.add(file.getName());
            }
        }
        currentBiome = biome;
        DragPanel("Map Squares/" +imagesInBiome.get(0),new Point(0,0));
        imageCorner.setLocation(prevPt);
        repaint();
    }

    int WIDTH;
    int HEIGHT;
    Point prevPt = new Point(0, 0);
    public void DragPanel(String FileLocation,Point point) {
        image = new ImageIcon(FileLocation);
        WIDTH = image.getIconWidth();
        HEIGHT = image.getIconHeight();
        imageCorner = point;
        ClickListener clickListener = new ClickListener();
        DragListener dragListener = new DragListener();
        addMouseListener(clickListener);
        addMouseMotionListener(dragListener);
    }

    boolean ClickedInImage;

    private class ClickListener extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            ClickedInImage = false;
            if (e.getButton() == MouseEvent.BUTTON1) {
                prevPt = e.getPoint();
                ClickedInImage = true;
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
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (!(imageCorner == null)) {
            image.paintIcon(this, g, (int) imageCorner.getX(), (int) imageCorner.getY());

            for(int x = 1;x<imagesInBiome.size();x++){

                   int moveX = 500 * ( Integer.parseInt(imagesInBiome.get(x).split("\\.")[1]) - Integer.parseInt(imagesInBiome.get(0).split("\\.")[1]) );
                   int moveY = 500 * ( Integer.parseInt(imagesInBiome.get(x).split("\\.")[0]) - Integer.parseInt(imagesInBiome.get(0).split("\\.")[0]) );

                    (new ImageIcon("Map Squares/" +imagesInBiome.get(x))).paintIcon(this,g,(int)imageCorner.getX() + moveX,(int)imageCorner.getY() + moveY);
                }
            }

    }
}


