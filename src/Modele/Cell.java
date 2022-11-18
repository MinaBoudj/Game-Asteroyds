package Modele;
/***
 * @autor amina
*/

import java.util.ArrayList;

public  abstract class Cell extends VisualObject {
	
	private boolean canContainSpaceShips;
	private ArrayList<SpaceShip> LSpaceShips;
	
	/* Constructeur */
	public Cell(String image, int orientation, Position pos, boolean canContainSpaceShips){
		super(orientation,pos);
		//super.setImagePath(image);
		this.canContainSpaceShips = canContainSpaceShips;
		this.LSpaceShips = null;
	}
	
	public Cell(String image,int orientation, int x, int y, boolean canContainSpaceShips) {
		super(orientation,x,y);
		//super.setImagePath(image);
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

	public ArrayList<SpaceShip> getLSpaceShips(){
		return this.LSpaceShips;
	}

	/* Methode  */
	// ajouter un vaisseau dans la liste des vaisseaux
	public void addLSpaceShip(SpaceShip newSpace){
			this.LSpaceShips.add(newSpace);
	}

	//retirer un vaisseau de la liste des vaisseaux
	public void removeLSpaceShip(SpaceShip newS){
		this.LSpaceShips.remove(newS);
	}

}
