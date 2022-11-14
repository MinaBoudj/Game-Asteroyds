package Modele;
/***
 * @autor amina
*/

import java.util.ArrayList;

public class Cell extends VisualObject {
	
	private boolean canContainSpaceShips;
	private ArrayList<SpaceShip> LSpaceShips;
	
	/* Constructeur */
	Cell(String image, int orientation, Position pos, boolean canContainSpaceShips){
		super(orientation,pos);
		super.setImagePath(image);
		this.canContainSpaceShips = canContainSpaceShips;
		this.LSpaceShips = null;
	}
	
	/* getteur and setteur */
	public void setCanContainSpaceShips(boolean value){
		this.canContainSpaceShips = value;
	}
	
	public boolean getCanContainSpaceShips() {
		return this.canContainSpaceShips;
	}

	public void addLSpaceShip(SpaceShip newSpace){
			this.LSpaceShips.add(newSpace);
	}

}
