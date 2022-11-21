package Modele;
/***
 * @autor amina
*/


public class RedAsteroyd extends  Asteroyd {
	
	public RedAsteroyd(String image, int orientation, Position pos){
		super(image,orientation,pos, false);
	}
	
	public RedAsteroyd(String image, int orientation, Position pos, boolean canContainSpaceShip) {
		super(image, orientation, pos, canContainSpaceShip);
	}
	
	@Override
	public void move(Cell[][] gameBoard, int[] directions) {
		Position pos1, pos2;
		//changer l'orientation selon la direction
		//ne peu pas faire bouger un objet devant lui
		try{
				//changer l'orientation
				int neworientation = calculeOrientation(directions[0]);
				pos1 = super.getPosition().getForward(neworientation);
				if(gameBoard[pos1.getX()][pos1.getY()]!= null && gameBoard[pos1.getX()][pos1.getY()] instanceof EmptyCell){ //case n'est pas null et vide
					//il bouge selon la direction 1 de 2 case
					pos2 = pos1.getForward(neworientation);
					if(gameBoard[pos2.getX()][pos2.getY()]!= null && gameBoard[pos2.getX()][pos2.getY()] instanceof EmptyCell){ //+ sans vesseau
						//rien dans la case (il a bougé de 2 cases)
						super.getPosition().setX(pos2.getX());
						super.getPosition().setY(pos2.getY());
					}else{//la 2eme case est sois null ou n'est pas vide
						//le metre donc dans la 1eme
						super.getPosition().setX(pos1.getX());
						super.getPosition().setY(pos1.getY());
					}
				}//premiere case sois n'est pas vide sois null donc ne bouge pas 	
			}catch(Exception e){//renvoie une position incorrect
				//faire quoi quand c'est un asteroyd ??
			}		
	}	
}

