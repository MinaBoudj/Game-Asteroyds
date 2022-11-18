package Modele;

/**
* @author Juba
*/

public abstract class Asteroyd extends Cell {
    
    public Asteroyd(String image, int orientation, Position pos){
        super(image,orientation, pos, false);
    }

    public Asteroyd(String image, int orientation, Position pos, boolean canContainSpaceShips){
        super(image, orientation, pos, canContainSpaceShips);
    }

    public Asteroyd(String image, int orientation, int x, int y){
        super(image,orientation, x, y, false);
    }

    public abstract void move(Cell[][] gameBoard, int[] directions);
}
