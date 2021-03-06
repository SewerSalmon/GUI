package com.company;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MyFrame extends JFrame {
    BiomeDisplay BD;
    GUI gui = new GUI(this);

    MyFrame(File saveFile) {
        this.setTitle(saveFile.getName());
        this.setSize(1920, 1080);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(gui);
        this.setVisible(true);
    }

    public void toMap(){
        BD.setVisible(false);
        gui.setVisible(true);
    }
    public void toBiome(String biome){
        gui.setVisible(false);
        BD = new BiomeDisplay(this,biome);
        add(BD);
        BD.setVisible(true);
    }

}