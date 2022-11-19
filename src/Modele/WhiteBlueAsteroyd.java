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
			int neworientation;
			if(super.getOrientation()==1 && directions[0]==2)
				neworientation = 5;
			if(super.getOrientation()==1 && directions[0]==3)
				neworientation = 2;
			if(super.getOrientation()==1 && directions[0]==4)
				neworientation = 6;
			if(super.getOrientation()==1 && directions[0]==5)
				neworientation = 4;
			if(super.getOrientation()==1 && directions[0]==6)
				neworientation = 3;
			if(super.getOrientation()==2 && directions[0]==1)
				neworientation = 5;
			if(super.getOrientation()==2 && directions[0]==2)
				neworientation = 2;
			if(super.getOrientation()==2 && directions[0]==4)
				neworientation = 4;
			if(super.getOrientation()==2 && directions[0]==5)
				neworientation = 3;
			if(super.getOrientation()==2 && directions[0]==6)
				neworientation = 3;
			if(super.getOrientation()==3 && directions[0]==1)
				neworientation = 1;
			if(super.getOrientation()==3 && directions[0]==2)
				neworientation = 5;
			if(super.getOrientation()==3 && directions[0]==3)
				neworientation = 2;
			if(super.getOrientation()==3 && directions[0]==4)
				neworientation = 6;
			if(super.getOrientation()==3 && directions[0]==5)
				neworientation = 4;
			if(super.getOrientation()==4 && directions[0]==1)
				neworientation = 1;
			if(super.getOrientation()==4 && directions[0]==2)
				neworientation = 5;
			if(super.getOrientation()==4 && directions[0]==3)
				neworientation = 2;
			if(super.getOrientation()==4 && directions[0]==4)
				neworientation = 6;
			if(super.getOrientation()==4 && directions[0]==6)
				neworientation = 3;
			if(super.getOrientation()==5 && directions[0]==1)
				neworientation = 1;
			if(super.getOrientation()==5 && directions[0]==3)
				neworientation = 2;
			if(super.getOrientation()==5 && directions[0]==4)
				neworientation = 6;
			if(super.getOrientation()==5 && directions[0]==5)
				neworientation = 2;
			if(super.getOrientation()==5 && directions[0]==6)
				neworientation = 3;
			if(super.getOrientation()==6 && directions[0]==1)
				neworientation = 1;
			if(super.getOrientation()==6 && directions[0]==2)
				neworientation = 5;
			if(super.getOrientation()==6 && directions[0]==3)
				neworientation = 2;
			if(super.getOrientation()==6 && directions[0]==5)
				neworientation = 4;
			if(super.getOrientation()==6 && directions[0]==6)
				neworientation = 3;
			else 
				neworientation = super.getOrientation();
			pos = super.getPosition().getForward(neworientation);
			if(gameBoard[pos.getX()][pos.getY()]!= null && gameBoard[pos.getX()][pos.getY()] instanceof EmptyCell){ //case n'est pas null et vide
				//super.setX(pos.getX());
				//super.setY(pos.getY());
			}//sinon ne bouge pas 
			//puis selon le bleu
			super.move(gameBoard, directions); 	
		}catch(Exception e){//renvoie une position incorrect
			//faire quoi quand c'est un asteroyd ??
		}		
		
	}
		
}