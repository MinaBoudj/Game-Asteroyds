package Modele;

/**
* @author Maylis
*/

public class EmptyCell extends Cell {

    // Constructeurs
    public EmptyCell(String imagePath, int orientation, int x, int y){
        super(imagePath, orientation, x, y, true);              
    }

    public EmptyCell(String imagePath, int orientation, Position pos){
        super(imagePath, orientation, pos, true);
    }
}   