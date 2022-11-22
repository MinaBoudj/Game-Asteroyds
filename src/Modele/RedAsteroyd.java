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
					if(gameBoard[pos1.getX()][pos1.getY()].getLSpaceShips().size() == 0){//la pos1 ne contient pas de SpaceShip
						pos2 = pos1.getForward(neworientation); //calcule de la pos2
						if(gameBoard[pos2.getX()][pos2.getY()]!= null && gameBoard[pos2.getX()][pos2.getY()] instanceof EmptyCell){ //case n'est pas null et vide 
								//verifier que la case ne contient pas de vesseau
								if(gameBoard[pos2.getX()][pos2.getY()].getLSpaceShips().size() == 0){ //ne contient pas de SpaceShip 
										EmptyCell newOne = new EmptyCell(super.toString(),super.getOrientation(), super.getPosition());
										//rien dans la case (il a bougé de 2 cases)
										super.getPosition().setX(pos2.getX());
										super.getPosition().setY(pos2.getY());
								}else{//contient un ou plusier spaceShip
									//infliger des dêgats
									for(int i=0; i<gameBoard[pos2.getX()][pos2.getY()].getLSpaceShips().size(); i++){
											gameBoard[pos2.getX()][pos2.getY()].getLSpaceShips().get(i).minusStructurePoint(1);
									}
									//et on le met dans la pos1
									EmptyCell newOne = new EmptyCell(super.toString(),super.getOrientation(), super.getPosition());
									super.getPosition().setX(pos1.getX());
									super.getPosition().setY(pos1.getY());
								}
						}else{ //le metre donc dans la 1eme
							EmptyCell newOne = new EmptyCell(super.toString(),super.getOrientation(), super.getPosition());
							super.getPosition().setX(pos1.getX());
							super.getPosition().setY(pos1.getY());
						}
					}else{//la pos1 contient un spaceShip
						//infliger des dêgats
						for(int i=0; i<gameBoard[pos1.getX()][pos1.getY()].getLSpaceShips().size(); i++){
							gameBoard[pos1.getX()][pos1.getY()].getLSpaceShips().get(i).minusStructurePoint(1);
						}
					}
				}	
			}catch(Exception e){//renvoie une position incorrect
				//faire quoi quand c'est un asteroyd ??
			}		
	}	
}

