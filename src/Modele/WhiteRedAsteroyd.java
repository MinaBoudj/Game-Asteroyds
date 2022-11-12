package Modele;

public class WhiteRedAsteroyd implements RedAsteroyd {


	WhiteRedAsteroyd(String image, int orientation, Position pos){
		super(image,orientation,pos, false);
	}
	
	@Override
	public void move(ArrayList<ArrayList<Cell>> gameBoard, ArrayList<int> directions) {
		
	}

}