package Modele;

/***
 * @autor amina
*/

public class WhiteBlueAsteroyd extends BlueAsteroyd{

	public WhiteBlueAsteroyd(String image, int orientation, Position pos){
		super(image,orientation,pos);
	}
	
	@Override
	public void move(Cell[][] gameBoard, int[] directions) {
		//il bouge selon le chiffre  directions[1](blanc) puis directions[2](bleu)
		//peu faire bouger un objet devant lui avec le bleu
		Position pos;
		try{
			//changer l'orientation
			int neworientation = super.calculeOrientation(directions[1]);
			
			pos = super.getPosition().getForward(neworientation);
			if(gameBoard[pos.getX()][pos.getY()]!= null && gameBoard[pos.getX()][pos.getY()] instanceof EmptyCell){ //case n'est pas null et vide
				super.getPosition().setX(pos.getX());
				super.getPosition().setY(pos.getY());
			}//sinon ne bouge pas 
			//puis selon le bleu
			super.move(gameBoard, directions); 	
		}catch(Exception e){//renvoie une position incorrect
			//faire quoi quand c'est un asteroyd ??
		}		
		
	}
		
}