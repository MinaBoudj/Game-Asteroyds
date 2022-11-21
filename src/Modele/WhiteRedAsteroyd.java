package Modele;
/***
 * @autor amina
*/


public class WhiteRedAsteroyd extends RedAsteroyd {


	public WhiteRedAsteroyd(String image, int orientation, Position pos){
		super(image,orientation,pos, false);
	}
	
	@Override
	public void move(Cell[][] gameBoard, int[] directions) {
		//verifier que la case est pas null 
		//il bouge selon le chiffre  directions[0] puis directions[1]	
		//peu faire bouger un objet devant lui avec le bleu
		Position pos;
		try{
			//dabord en rouge 
			super.move(gameBoard, directions);
			//puis en blanche

			//changer l'orientation
			int neworientation = super.calculeOrientation(directions[1]);
			pos = super.getPosition().getForward(neworientation);
			if(gameBoard[pos.getX()][pos.getY()]!= null && gameBoard[pos.getX()][pos.getY()] instanceof EmptyCell){ //case n'est pas null et vide
				super.getPosition().setX(pos.getX());
				super.getPosition().setY(pos.getY());
			}//sinon ne bouge pas 
		}catch(Exception e){//renvoie une position incorrect
			//faire quoi quand c'est un asteroyd ??
		}		
			
		
	}

}