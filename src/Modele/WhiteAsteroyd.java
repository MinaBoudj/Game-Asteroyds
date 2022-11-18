package Modele;

/**
* @author Juba
*/

public class WhiteAsteroyd extends Asteroyd {
    
    /* Constructeurs */
    public WhiteAsteroyd(int x, int orientation, int y){
        super(imagePath, orientation, x, y);
    }

    public WhiteAsteroyd(Position position){
        super(imagePath, position);
    }

    
    public void move(Cell[][] gameBoard, int[] directions) { // A CODER AVEC LE GROUPE : MOUVEMENTS DU BLANC 1 déplacement
    }
}
