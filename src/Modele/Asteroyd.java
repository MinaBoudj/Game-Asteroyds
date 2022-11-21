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

    int calculeOrientation(int direction){
        int neworientation;
        neworientation = super.getOrientation();
        if(direction == 2 )
            neworientation = (super.getOrientation()+3 %6)+1;
        if(direction == 3)
            neworientation = (super.getOrientation()+1 %6)+1;
        if(direction == 4)
            neworientation = (super.getOrientation()+4 %6)+1;
        if(direction == 5)
            neworientation = (super.getOrientation() %6)+1;
        if(direction == 6)
            neworientation = (super.getOrientation()+2 %6)+1;
        
        return neworientation;
    }

    public abstract void move(Cell[][] gameBoard, int[] directions);
}
