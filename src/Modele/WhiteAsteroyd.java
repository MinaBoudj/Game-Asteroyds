package Modele;

/**
* @author Juba
*/

public class WhiteAsteroyd extends Asteroyd {
    
    /* Constructeurs */
    public WhiteAsteroyd(int orientation, int x, int y)throws Exception{
        super(orientation, x, y);
    }

    public WhiteAsteroyd(int orientation, Position position)throws Exception{
        super(orientation, position);
    }

    public WhiteAsteroyd(int orientation, Position position, boolean canContainSpaceShip)throws Exception{
        super(orientation, position, canContainSpaceShip);
    }
    
    public void move(Cell[][] gameBoard, int[] directions) throws Exception{ // MOUVEMENTS DU BLANC 1 déplacement
        Position pos;
        if(directions[1]<0||directions[1]>6) throw new Exception("direction rouge incorrect ");
		else{
            try {
                //Définir l'orientation
                int neworientation = calculeOrientation(directions[1]);
                pos = super.getPosition().getForward(neworientation);

                if(gameBoard[pos.getX()][pos.getY()]!= null && gameBoard[pos.getX()][pos.getY()] instanceof EmptyCell){ // Vérifie le type de case
                    if (gameBoard[pos.getX()][pos.getY()].getLSpaceShips().size() == 0) { // Vérifie si la case ne contient rien
                        // Déplacement de l'astéroïde
                        EmptyCell nec = new EmptyCell(super.getPosition());
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
    

}
