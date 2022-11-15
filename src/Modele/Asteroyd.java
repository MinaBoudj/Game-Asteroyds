package Modele;

import java.util.ArrayList;

public class Asteroyd implements Cell {
    
    public Asteroyd(String imagePath, int x, int y) { // Cell.java : Cell implementes VO ????
        this.imagePath = imagePath; 
        this.x = x; 
        this.y = y;
    }

    public Asteroyd(String imagePath, Position position){
        this.imagePath = imagePath;

    }

    public void move(ArrayList<ArrayList<Cell>> gameBoard, ArrayList<Integer> directions) { // Besoin de plus d'informations

    }
}
