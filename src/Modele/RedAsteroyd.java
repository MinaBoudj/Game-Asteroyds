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
		//verifier que la case est pas null 
		//il bouge selon le chiffre  directions[0]
		//ne peu pas faire bouger un objet devant lui
	}
	
	
	

}
