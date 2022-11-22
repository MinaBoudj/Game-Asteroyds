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
        try {
            //Définir l'orientation
            int neworientation = calculeOrientation(directions[1]);
            pos = super.getPosition().getForward(neworientation);

            if(gameBoard[pos.getX()][pos.getY()]!= null && gameBoard[pos.getX()][pos.getY()] instanceof EmptyCell){ // Vérifie le type de case
                if (gameBoard[pos.getX()][pos.getY()].getLSpaceShips().size() == 0) { // Vérifie si la case ne contient rien
                    // Déplacement de l'astéroïde
                    EmptyCell nec = new EmptyCell(super.toString(), super.getOrientation(), super.getPosition());
                    super.getPosition().setX(pos.getX());
                    super.getPosition().setY(pos.getY());
                }
                else { // la case contient déja un vaisseau.
                    // Infliger des dégâts au vaisseau.
                    for(int i=0; i<gameBoard[pos.getX()][pos.getY()].getLSpaceShips().size(); i++){
                        gameBoard[pos.getX()][pos.getY()].getLSpaceShips().get(i).minusStructurePoint(1);
                    }
                }
            } 	
        } catch(Exception e) {
        }	
    }

}
