package Modele;

/**
* @author Juba
*/

public class WhiteAsteroyd extends Asteroyd {
    
    /* Constructeurs */
    public WhiteAsteroyd(int orientation, int x, int y, int priority)throws Exception{
        super(orientation, x, y, priority);
    }

    public WhiteAsteroyd(int orientation, Position position, int priority)throws Exception{
        super(orientation, position, priority);
    }
    
    public void move(Cell[][] gameBoard, int[] directions) throws Exception{ // MOUVEMENTS DU BLANC 1 déplacement
        Position pos;
        if(directions[1]<=0||directions[1]>6 || gameBoard[super.getPosition().getY()][super.getPosition().getX()]!= this) throw new Exception("direction rouge incorrect ");
		else{
            try {
                //Définir l'orientation
                int neworientation = calculeOrientation(directions[2]);
                pos = super.getPosition().getForward(neworientation);
                if(gameBoard[pos.getY()][pos.getX()]!= null && gameBoard[pos.getY()][pos.getX()] instanceof EmptyCell){ // Vérifie le type de case
                    if (gameBoard[pos.getY()][pos.getX()].getLSpaceShips().size() == 0) { // Vérifie si la case ne contient rien
                        // Déplacement de l'astéroïde
                        EmptyCell nec = new EmptyCell(super.getPosition());
                        gameBoard[super.getPosition().getY()][super.getPosition().getX()] = nec;
                        super.getPosition().setX(pos.getX());
                        super.getPosition().setY(pos.getY());
                        gameBoard[super.getPosition().getY()][super.getPosition().getX()] = this;
                    }
                else { // la case contient déja un vaisseau.
                    // Infliger des dégâts au vaisseau.
                    for(int i=0; i<gameBoard[pos.getY()][pos.getX()].getLSpaceShips().size(); i++){
                        gameBoard[pos.getY()][pos.getX()].getLSpaceShips().get(i).minusStructurePoint(1);
                    }
                }
            } 	
            } catch(Exception e) {
            
            }
        }
    }	
    
    @Override
    public String toString() {
        return "asteroyd-white-" + getOrientation() + "-" + getPriority();
    }
}
