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
          
          if(gameBoard[pos1.getY()][pos1.getX()]!= null){
            if(gameBoard[pos1.getY()][pos1.getX()] instanceof EmptyCell){//c une case vide
              if (gameBoard[pos1.getY()][pos1.getX()].getLSpaceShips().size() == 0) { // la case ne contient aucun vaisseau
                // Déplacement de l'astéroïde
                EmptyCell nec = new EmptyCell(super.getPosition());
                gameBoard[super.getPosition().getY()][super.getPosition().getX()] = nec;  
                super.getPosition().setX(pos1.getX());
                super.getPosition().setY(pos1.getY());
                gameBoard[super.getPosition().getY()][super.getPosition().getX()] = this;
                //verification de la deusieme case 
                if(gameBoard[pos2.getY()][pos2.getX()]!= null){
                  if(gameBoard[pos2.getY()][pos2.getX()] instanceof EmptyCell){//c une case vide
                    if (gameBoard[pos2.getY()][pos2.getX()].getLSpaceShips().size() == 0) { // la case ne contient aucun vaisseau
                      // Déplacement de l'astéroïde de pos1 à pos2 et this de sa pos à pos1
                      gameBoard[pos1.getY()][pos1.getX()].setPosition(pos2);
                      gameBoard[pos2.getX()][pos2.getY()] = gameBoard[pos1.getY()][pos1.getX()];
                      
                      super.getPosition().setX(pos1.getX());
                      super.getPosition().setY(pos1.getY());

                      EmptyCell nec1 = new EmptyCell(super.getPosition());
                      gameBoard[super.getPosition().getY()][super.getPosition().getX()] = nec1;  
                      gameBoard[super.getPosition().getY()][super.getPosition().getX()] = this;

                    }else{
                      // Infliger des dégâts
                      for(int i=0; i<gameBoard[pos2.getY()][pos2.getX()].getLSpaceShips().size(); i++){
                        gameBoard[pos2.getY()][pos2.getX()].getLSpaceShips().get(i).minusStructurePoint(1);
                      }
                    }
                  }//ne rien deplacer si c pas une case vide
                }
              }else{
                // Infliger des dégâts
                for(int i=0; i<gameBoard[pos1.getY()][pos1.getX()].getLSpaceShips().size(); i++){
                  gameBoard[pos1.getY()][pos1.getX()].getLSpaceShips().get(i).minusStructurePoint(1);
                }
              }
            }else if(gameBoard[pos1.getY()][pos1.getX()] instanceof Asteroyd){//dans la premiere case c un asteroid
                //verification de la deusieme case 
                if(gameBoard[pos2.getY()][pos2.getX()]!= null){
                  if(gameBoard[pos2.getY()][pos2.getX()] instanceof EmptyCell){//c une case vide
                    if (gameBoard[pos2.getY()][pos2.getX()].getLSpaceShips().size() == 0) { // la case ne contient aucun vaisseau
                      // Déplacement de l'astéroïde de pos1 à pos2 et this de sa pos à pos1
                      gameBoard[pos1.getY()][pos1.getX()].setPosition(pos2);
                      gameBoard[pos2.getY()][pos2.getX()] = gameBoard[pos1.getY()][pos1.getX()];
                      
                      super.getPosition().setX(pos1.getX());
                      super.getPosition().setY(pos1.getY());

                      EmptyCell nec = new EmptyCell(super.getPosition());
                      gameBoard[super.getPosition().getY()][super.getPosition().getX()] = nec;  
                      gameBoard[super.getPosition().getY()][super.getPosition().getX()] = this;

                    }else{
                      // Infliger des dégâts
                      for(int i=0; i<gameBoard[pos2.getY()][pos2.getX()].getLSpaceShips().size(); i++){
                        gameBoard[pos2.getY()][pos2.getX()].getLSpaceShips().get(i).minusStructurePoint(1);
                      }
                    }
                  }//ne rien deplacer si c pas une case vide
                }
            }
          }
        }catch(Exception e) {
          // Traitement de l'erreur (orientation négative)
            }
      }	
	}
    
  @Override
  public String toString() {
    return "asteroyd-blue-" + getOrientation() + "-" + getPriority();
  }
}
