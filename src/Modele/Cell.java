package Modele;
/***
 * @autor amina
*/

import java.util.ArrayList;

public  abstract class Cell extends VisualObject {
	
	private boolean canContainSpaceShips;
	private ArrayList<SpaceShip> LSpaceShips;
	
	/* Constructeur */
	Cell(String image, int orientation, Position pos, boolean canContainSpaceShips){
		super(orientation,pos);// coriiger
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

	public ArrayList<SpaceShip> getLSpaceShip(){
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
