package Modele;

/**
* @author Juba
*/

public abstract class Asteroyd extends Cell {
    
    /* Constructeurs */
    public Asteroyd(int orientation, Position pos)throws Exception{
        super(orientation, pos, false);
    }

    public Asteroyd(int orientation, int x, int y) throws Exception{
        super(orientation, x, y, false);
    }


    /* Méthodes */
    public int calculeOrientation(int direction)throws Exception{
        if(direction<=0 || direction>6) throw new Exception("orientation incorrect");
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

    /* Méthodes abstraites */
    public abstract void move(Cell[][] gameBoard, int[] directions)throws Exception;
}
