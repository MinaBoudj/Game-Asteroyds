package Modele;

/**
* @author Maylis
*/

public class EmptyCell extends Cell {

    // Constructeurs
    public EmptyCell(String imagePath, int orientation, int x, int y)throws Exception{
        super(imagePath, orientation, x, y, true);              
    }

    public EmptyCell(String imagePath, int orientation, Position pos)throws Exception{
        super(imagePath, orientation, pos, true);
    }
}   