package Modele;
/***
 * @autor amina
*/


public class RedAsteroyd extends  Asteroyd {
	
	/* constructeur d'un asteroid rouge */
	public RedAsteroyd(int orientation, Position pos, int priority)throws Exception{
		super(orientation, pos, priority);
	}
	
	public RedAsteroyd(int orientation, int x, int y, int priority)throws Exception {
		super(orientation, x,y, priority);
	}
	
	@Override
	//mouvement d'un asteroid rouge
	public void move(Cell[][] gameBoard, int[] directions)throws Exception {
		Position pos1, pos2;
		if(directions[0]<=0||directions[0]>6)
            throw new Exception("Bad red direction : " + directions[0]);
        if(gameBoard[super.getPosition().getY()][super.getPosition().getX()]!= this)
            throw new Exception(this + " not at its position : line:" + super.getPosition().getY() + "column:" + super.getPosition().getX());
		else{
			//changer l'orientation selon la direction
			//ne peu pas faire bouger un objet devant lui 
			try{
				//changer l'orientation
				int neworientation = calculeOrientation(directions[0]);
				pos1 = super.getPosition().getForward(neworientation);
				if(gameBoard[pos1.getY()][pos1.getX()]!= null && gameBoard[pos1.getY()][pos1.getX()] instanceof EmptyCell){ //case n'est pas null et vide
					//il bouge selon la direction 1 de 2 case
					if(gameBoard[pos1.getY()][pos1.getX()].getLSpaceShips().size() == 0){//la pos1 ne contient pas de SpaceShip
						pos2 = pos1.getForward(neworientation); //calcule de la pos2
						if(gameBoard[pos2.getY()][pos2.getX()]!= null && gameBoard[pos2.getY()][pos2.getX()] instanceof EmptyCell){ //case n'est pas null et vide 
								//verifier que la case ne contient pas de vesseau
								if(gameBoard[pos2.getY()][pos2.getX()].getLSpaceShips().size() == 0){ //ne contient pas de SpaceShip 
										EmptyCell newOne = new EmptyCell(super.getPosition());
										gameBoard[super.getPosition().getY()][super.getPosition().getX()] = newOne;
										//rien dans la case (il a bougé de 2 cases)
										super.getPosition().setX(pos2.getX());
										super.getPosition().setY(pos2.getY());
										gameBoard[super.getPosition().getY()][super.getPosition().getX()] = this;

								}else{//contient un ou plusier spaceShip
									//infliger des dêgats
									for(int i=0; i<gameBoard[pos2.getY()][pos2.getX()].getLSpaceShips().size(); i++){
											gameBoard[pos2.getY()][pos2.getX()].getLSpaceShips().get(i).minusStructurePoint(1);
									}
									//et on le met dans la pos1
									EmptyCell newOne = new EmptyCell(super.getPosition());
									gameBoard[super.getPosition().getY()][super.getPosition().getX()] = newOne;
									super.getPosition().setX(pos1.getX());
									super.getPosition().setY(pos1.getY());
									gameBoard[super.getPosition().getY()][super.getPosition().getX()] = this;

								}
						}else{ //le metre donc dans la 1eme
							EmptyCell newOne = new EmptyCell(super.getPosition());
							gameBoard[super.getPosition().getY()][super.getPosition().getX()] = newOne;
							super.getPosition().setX(pos1.getX());
							super.getPosition().setY(pos1.getY());
							gameBoard[super.getPosition().getY()][super.getPosition().getX()] = this;

						}
					}else{//la pos1 contient un spaceShip
						//infliger des dêgats
						for(int i=0; i<gameBoard[pos1.getY()][pos1.getX()].getLSpaceShips().size(); i++){
							gameBoard[pos1.getY()][pos1.getX()].getLSpaceShips().get(i).minusStructurePoint(1);
						}
					}
				}	
				}catch(Exception e){//renvoie une position incorrect
				//faire quoi quand c'est un asteroyd ??
				}
		}		
	}	

	@Override
	public String toString() {
		return "asteroyd-red-" + getOrientation() + "-" + getPriority();
	}
}

