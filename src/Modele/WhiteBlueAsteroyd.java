package Modele;

public class WhiteBlueAsteroyd implements BlueAsteroyd{

	WhiteBlueAsteroyd(String image, int orientation, Position pos){
		super(image,orientation,pos, false);
	}
	
	@Override
	public void move(ArrayList<ArrayList<Cell>> gameBoard, ArrayList<int> directions) {
		
	}
		
}