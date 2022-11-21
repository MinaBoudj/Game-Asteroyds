package Modele;

/**
* @author Juba
*/
public class BlueAsteroyd extends Asteroyd{

     /* Constructeurs */
     public BlueAsteroyd(String image, int orientation, Position pos){
		super(image, orientation, pos, false);
	}

    @Override
    public void move(Cell[][] gameBoard, int[] directions) { // Mouvements d'un astéroïde bleu : 1 pas + peut pousser si besoin
      Position pos, pos1;
      try{
          // Définir l'orientation
          int neworientation = calculeOrientation(directions[1]);
          pos1 = super.getPosition().getForward(neworientation); // Case 1
          pos2 = pos1.getForward(neworientation); // Case 2

          if(gameBoard[pos1.getX()][pos1.getY()]!= null && gameBoard[pos1.getX()][pos1.getY()] instanceof EmptyCell){ // Si la première case est vide
              super.getPosition().setX(pos1.getX());
              super.getPosition().setY(pos1.getY());
          } else if (gameBoard[pos2.getX()][pos2.getY()]!= null && gameBoard[pos2.getX()][pos2.getY()] instanceof EmptyCell){ // Si la 2ème case est vide
              

          }
      }catch(Exception e){//renvoie une position incorrect
          //faire quoi quand c'est un asteroyd ??
      }	
	}
    
}
