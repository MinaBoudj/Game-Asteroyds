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
		
	}

}