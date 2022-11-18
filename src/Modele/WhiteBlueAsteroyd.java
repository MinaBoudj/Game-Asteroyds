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
		//verifier que la case est pas null 
		//il bouge selon le chiffre  directions[1] puis directions[2]
		//peu faire bouger un objet devant lui avec le bleu
	}
		
}