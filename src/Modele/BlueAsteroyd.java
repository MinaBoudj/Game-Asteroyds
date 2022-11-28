package Modele;

/**
* @author Juba
*/
public class BlueAsteroyd extends Asteroyd{

     /* Constructeurs */
    public BlueAsteroyd(String image, int orientation, Position pos) throws Exception{ // ERREUR : Lien avec le constructeur de Asteroyd concernant l'exception ? 
		    super(image, orientation, pos, false);
	  }

    
    /* Méthodes */
    @Override
    public void move(Cell[][] gameBoard, int[] directions) throws Exception{ // Mouvements d'un astéroïde bleu : 1 pas + peut pousser si besoin
      Position pos1, pos2;
      if(directions[2]<0||directions[2]>6) throw new Exception("erreur de direction bleu");
      else{
        try{
          // Définir l'orientation
          int neworientation = calculeOrientation(directions[2]);
          pos1 = super.getPosition().getForward(neworientation); // Case en face.
          pos2 = pos1.getForward(neworientation); // Case derrière la case en face.

          if(gameBoard[pos1.getX()][pos1.getY()]!= null && gameBoard[pos1.getX()][pos1.getY()] instanceof EmptyCell) { // Si la première case est vide
              if (gameBoard[pos1.getX()][pos1.getY()].getLSpaceShips().size() == 0) {
                // Déplacement de l'astéroïde
                EmptyCell nec = new EmptyCell(super.toString(), super.getOrientation(), super.getPosition());  
                super.getPosition().setX(pos1.getX());
                super.getPosition().setY(pos1.getY());
              } 
              else if (gameBoard[pos2.getX()][pos2.getY()]!= null && gameBoard[pos2.getX()][pos2.getY()] instanceof EmptyCell) { // Si la 2ème case est vide
                if (gameBoard[pos2.getX()][pos2.getY()].getLSpaceShips().size() == 0) {
                  // Déplacement de l'astéroïde (obstacle)
                  EmptyCell nec = new EmptyCell(super.toString(), super.getOrientation(), super.getPosition()); // super au lieu de pos 1 ?
                  super.getPosition().setX(pos2.getX()); // super au lieu de pos 1 ?
                  super.getPosition().setY(pos2.getY()); // super au lieu de pos 1 ?
                } else {
                    // Infliger des dégâts
                    for(int i=0; i<gameBoard[pos1.getX()][pos1.getY()].getLSpaceShips().size(); i++){
                      gameBoard[pos1.getX()][pos1.getY()].getLSpaceShips().get(i).minusStructurePoint(1);
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
    
}
