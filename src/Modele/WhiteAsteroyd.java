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

    public WhiteAsteroyd(String imagePath, int orientation, Position position, boolean canContainSpaceShip){
        super(imagePath, orientation, position, canContainSpaceShip);
    }
    
    public void move(Cell[][] gameBoard, int[] directions) { // MOUVEMENTS DU BLANC 1 déplacement
        Position pos;
        try{
            //Définir l'orientation
            int neworientation = calculeOrientation(directions[1]);
            pos = super.getPosition().getForward(neworientation);

            if(gameBoard[pos.getX()][pos.getY()]!= null && gameBoard[pos.getX()][pos.getY()] instanceof EmptyCell){ // Inspection de la case
                super.getPosition().setX(pos.getX());
                super.getPosition().setY(pos.getY());
            } 	
        }catch(Exception e){//renvoie une position incorrect
            //faire quoi quand c'est un asteroyd ??
        }	
    }

}
