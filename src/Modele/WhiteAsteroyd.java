package Modele;

/**
* @author Juba
*/

public class WhiteAsteroyd extends Asteroyd {
    
    /* Constructeurs */
    public WhiteAsteroyd(String imagePath, int orientation, int x, int y){
        super(imagePath, orientation, x, y);
    }

    public WhiteAsteroyd(String imagePath, int orientation, Position position){
        super(imagePath, orientation, position);
    }

    
    public void move(Cell[][] gameBoard, int[] directions) { // A CODER AVEC LE GROUPE : MOUVEMENTS DU BLANC 1 déplacement
    }
}
