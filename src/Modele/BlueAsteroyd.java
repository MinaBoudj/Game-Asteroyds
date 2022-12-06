package Modele;

/**
* @author Juba
*/
public class BlueAsteroyd extends Asteroyd{

     /* Constructeurs */
    public BlueAsteroyd(int orientation, Position pos, int priority) throws Exception{ // ERREUR : Lien avec le constructeur de Asteroyd concernant l'exception ? 
		    super(orientation, pos, priority);
	  }
    public BlueAsteroyd(int orientation, int x, int y, int priority) throws Exception{ // ERREUR : Lien avec le constructeur de Asteroyd concernant l'exception ? 
		    super(orientation, x,y, priority);
	  }

    
    /* Méthodes */
    @Override
    public void move(Cell[][] gameBoard, int[] directions) throws Exception{ // Mouvements d'un astéroïde bleu : 1 pas + peut pousser si besoin
      Position pos1, pos2;
      if(directions[2]<=0||directions[2]>6||gameBoard[super.getPosition().getY()][super.getPosition().getX()]!= this) throw new Exception("erreur de direction bleu");
      else{
        try{
          // Définir l'orientation
          int neworientation = calculeOrientation(directions[2]);
          pos1 = super.getPosition().getForward(neworientation); // Case en face.
          pos2 = pos1.getForward(neworientation); // Case derrière la case en face.

          if(gameBoard[pos1.getY()][pos1.getX()]!= null && gameBoard[pos1.getY()][pos1.getX()] instanceof EmptyCell) { // Si la première case est vide
              if (gameBoard[pos1.getY()][pos1.getX()].getLSpaceShips().size() == 0) {
                // Déplacement de l'astéroïde
                EmptyCell nec = new EmptyCell(super.getPosition());
                gameBoard[super.getPosition().getY()][super.getPosition().getX()] = nec;  
                super.getPosition().setX(pos1.getX());
                super.getPosition().setY(pos1.getY());
                gameBoard[super.getPosition().getY()][super.getPosition().getX()] = this;

              } 
              else 
              if (gameBoard[pos2.getY()][pos2.getX()]!= null && gameBoard[pos2.getY()][pos2.getX()] instanceof EmptyCell) { // Si la 2ème case est vide
                if (gameBoard[pos2.getY()][pos2.getX()].getLSpaceShips().size() == 0) {
                  // Déplacement de l'astéroïde (obstacle)
                  EmptyCell nec = new EmptyCell(super.getPosition()); // super au lieu de pos 1 ?
                  gameBoard[super.getPosition().getY()][super.getPosition().getX()] = nec;
                  super.getPosition().setX(pos2.getX()); // super au lieu de pos 1 ?
                  super.getPosition().setY(pos2.getY()); // super au lieu de pos 1 ?
                  gameBoard[super.getPosition().getY()][super.getPosition().getX()] = this;

                } else {
                    // Infliger des dégâts
                    for(int i=0; i<gameBoard[pos1.getY()][pos1.getX()].getLSpaceShips().size(); i++){
                      gameBoard[pos1.getY()][pos1.getX()].getLSpaceShips().get(i).minusStructurePoint(1);
                    }
                  }
              }
           }
          }
        catch(Exception e) {
          // Traitement de l'erreur (orientation négative)
            }
      }	
	}
    
  @Override
  public String toString() {
    return "asteroyd-blue-" + getOrientation() + "-" + getPriority();
  }
}
