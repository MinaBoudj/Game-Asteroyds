package Modele;
/***
 * @autor amina
*/


public class WhiteRedAsteroyd extends RedAsteroyd {


	public WhiteRedAsteroyd(int orientation, Position pos)throws Exception{
		super(orientation,pos);
	}

	public WhiteRedAsteroyd(int orientation, int x, int y)throws Exception{
		super(orientation, x,y);
	}
	
	@Override
	public void move(Cell[][] gameBoard, int[] directions) throws Exception{
		//verifier que la case est pas null 
		//il bouge selon le chiffre  directions[0] puis directions[1]	
		//peu faire bouger un objet devant lui avec le bleu
		if(directions[1]<=0 || directions[1]>6 || gameBoard[super.getPosition().getY()][super.getPosition().getX()]!= this) throw new Exception("directions du blans incorrect");
		else{
			Position pos;
			//dabord en rouge 
			super.move(gameBoard, directions);
			try{
				//puis en blanche
				//changer l'orientation
				int neworientation = super.calculeOrientation(directions[1]);
				pos = super.getPosition().getForward(neworientation);
				if(gameBoard[pos.getY()][pos.getX()]!= null && gameBoard[pos.getY()][pos.getX()] instanceof EmptyCell){ //case n'est pas null et vide
					if(gameBoard[pos.getY()][pos.getX()].getLSpaceShips().size() == 0){//la pos1 ne contient pas de SpaceShip
						EmptyCell newOne = new EmptyCell(super.getPosition());
						super.getPosition().setX(pos.getX());
						super.getPosition().setY(pos.getY());
					}else{//contient un ou plusier spaceShip
						//infliger des dêgats
						for(int i=0; i<gameBoard[pos.getY()][pos.getX()].getLSpaceShips().size(); i++){
							gameBoard[pos.getY()][pos.getX()].getLSpaceShips().get(i).minusStructurePoint(1);
						}
						//ne bouge pas plus
					}
				}//sinon ne bouge pas 
			}catch(Exception e){//renvoie une position incorrect
				//faire quoi quand c'est un asteroyd ??
			}		
		}
	}

	@Override
	public String toString() {
		return "asteroyd-white_red-" + this.getOrientation();
	}
}