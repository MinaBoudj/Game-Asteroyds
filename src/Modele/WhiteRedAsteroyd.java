package Modele;
/***
 * @autor amina
*/


public class WhiteRedAsteroyd extends RedAsteroyd {


	public WhiteRedAsteroyd(String image, int orientation, Position pos)throws Exception{
		super(image,orientation,pos, false);
	}
	
	@Override
	public void move(Cell[][] gameBoard, int[] directions) throws Exception{
		//verifier que la case est pas null 
		//il bouge selon le chiffre  directions[0] puis directions[1]	
		//peu faire bouger un objet devant lui avec le bleu
		if(directions[1]<0 || directions[1]>6) throw new Exception("directions du blans incorrect");
		else{
			Position pos;
			try{
				//dabord en rouge 
				super.move(gameBoard, directions);
				//puis en blanche

				//changer l'orientation
				int neworientation = super.calculeOrientation(directions[1]);
				pos = super.getPosition().getForward(neworientation);
				if(gameBoard[pos.getX()][pos.getY()]!= null && gameBoard[pos.getX()][pos.getY()] instanceof EmptyCell){ //case n'est pas null et vide
					if(gameBoard[pos.getX()][pos.getY()].getLSpaceShips().size() == 0){//la pos1 ne contient pas de SpaceShip
						EmptyCell newOne = new EmptyCell(super.toString(),super.getOrientation(), super.getPosition());
						super.getPosition().setX(pos.getX());
						super.getPosition().setY(pos.getY());
					}else{//contient un ou plusier spaceShip
						//infliger des dêgats
						for(int i=0; i<gameBoard[pos.getX()][pos.getY()].getLSpaceShips().size(); i++){
							gameBoard[pos.getX()][pos.getY()].getLSpaceShips().get(i).minusStructurePoint(1);
						}
						//ne bouge pas plus
					}
				}//sinon ne bouge pas 
			}catch(Exception e){//renvoie une position incorrect
				//faire quoi quand c'est un asteroyd ??
			}		
		}
	}
}