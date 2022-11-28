package Modele;

/**
* @author Maylis
*/

public class EmptyCell extends Cell {

    // Constructeurs
    public EmptyCell(int x, int y)throws Exception{
        super(1, x, y, true);              
    }

    public EmptyCell(Position pos)throws Exception{
        super(1, pos, true);
    }
}   