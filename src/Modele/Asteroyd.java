package Modele;

/**
* @author Juba
*/

public abstract class Asteroyd extends Cell {
    private int priority;
    
    /* Constructeurs */
    public Asteroyd(int orientation, Position pos, int priority)throws Exception {
        super(orientation, pos, false);
        this.priority = priority;
    }

    public Asteroyd(int orientation, int x, int y, int priority) throws Exception {
        this(orientation, new Position(x,y), priority);
    }


    /* Méthodes */
    public int calculeOrientation(int direction) throws Exception{

        if(direction<=0 || direction>6) { // Vérification de la valeur de la direction.
            throw new Exception("ERREUR : direction incorrecte");
        }

        int neworientation;
        neworientation = super.getOrientation();
        if(direction == 2)
            neworientation = ((super.getOrientation()+3) %6)+1;
        if(direction == 3)
            neworientation = ((super.getOrientation()+1) %6)+1;
        if(direction == 4)
            neworientation = ((super.getOrientation()+4) %6)+1;
        if(direction == 5)
            neworientation = (super.getOrientation() %6)+1;
        if(direction == 6)
            neworientation = ((super.getOrientation()+2) %6)+1;
        return neworientation;
    }

    public int getPriority() {return priority;}

    /* Méthodes abstraites */
    public abstract void move(Cell[][] gameBoard, int[] directions) throws Exception;
}
