package Modele;

/**
* @author Juba
*/

public abstract class Asteroyd extends Cell {
    
    public Asteroyd(String image, int orientation, Position pos){
        super(image,orientation, pos, false);
    }

    public Asteroyd(String image, int orientation, Position pos, boolean canContainSpaceShips){
        super(image,orientation, pos, canContainSpaceShips);
    }

    public abstract void move(Cell[][] gameBoard, int[] directions);
}
