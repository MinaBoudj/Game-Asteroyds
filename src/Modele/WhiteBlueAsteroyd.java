package Modele;

/***
 * @autor amina
*/

public class WhiteBlueAsteroyd extends BlueAsteroyd{

	/* constructeur d'un asteroid Blanc et bleu  */
	public WhiteBlueAsteroyd(int orientation, Position pos)throws Exception{
		super(orientation,pos);
	}

	public WhiteBlueAsteroyd(int orientation, int x, int y)throws Exception{
		super(orientation, x,y);
	}
	
	@Override
	public void move(Cell[][] gameBoard, int[] directions) throws Exception{
		//il bouge selon le chiffre  directions[1](blanc) puis directions[2](bleu)
		//peu faire bouger un objet devant lui avec le bleu
		if(directions[1]<=0||directions[1]>6 ||gameBoard[super.getPosition().getY()][super.getPosition().getX()]!= this) throw new Exception("direction rouge incorrect ");
		else{
			Position pos;
			try{
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
					}
				}//sinon ne bouge pas 	
			}catch(Exception e){//renvoie une position incorrect
				//faire quoi quand c'est un asteroyd ??
			}
			//puis selon le bleu
			super.move(gameBoard, directions); 	
		}	
		
	}
		
	@Override
	public String toString() {
		return "asteroyd-white_blue-" + this.getOrientation();
	}
}