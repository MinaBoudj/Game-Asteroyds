package Modele;
/***
 * @autor amina
*/

import java.util.ArrayList;

public abstract class Cell extends VisualObject {
	
	private boolean canContainSpaceShips;
	private ArrayList<SpaceShip> LSpaceShips;
	
	/* Constructeur */
	public Cell(int orientation, Position pos, boolean canContainSpaceShips)throws Exception{
		super(orientation,pos);
		//super.setImagePath(image);
		this.canContainSpaceShips = canContainSpaceShips;
		this.LSpaceShips = new ArrayList<SpaceShip>();
	}
	
	public Cell(int orientation, int x, int y, boolean canContainSpaceShips)throws Exception {
		super(orientation,x,y);
		//super.setImagePath(image);
		this.canContainSpaceShips = canContainSpaceShips;
		this.LSpaceShips = new ArrayList<SpaceShip>();
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
	public void addLSpaceShip(SpaceShip newSpace) throws Exception{
		if(newSpace == null) throw new Exception("pointeur null");
		else
			this.LSpaceShips.add(newSpace);
	}

	//retirer un vaisseau de la liste des vaisseaux
	public void removeLSpaceShip(SpaceShip newS)throws Exception{
		if(newS == null) throw new Exception("pointeur null");
		else
			this.LSpaceShips.remove(newS);
	}

}
