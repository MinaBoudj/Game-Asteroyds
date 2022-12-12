package Modele;
//import java.util.*;
/***
 * @autor amina
*/


public class SpaceShip extends VisualObject{
	
	private int structurePoints;
	private Color color;
    private int[] relics;
	
	
	/* Constructeur d'un vaisseau */
	public SpaceShip(int orientation, Position pos, Color color)throws Exception{//exception dans VisualObject
		super(orientation, pos);
		this.structurePoints = 6;
        this.relics = new int[]{0,0,0,0};    
        this.color = color;
	}
	
	public SpaceShip(int orientation, int x, int y, Color color)throws Exception{//exception dans VisualObject
		this(orientation, new Position(x, y), color);
	}
	
	/* Getteur and setteur */
	public Color getColor() { return this.color; }
	public int getStructurePoints() { return this.structurePoints; }

	//nombre de relics que possede le vaisseau
    public int getNumberOfRelics() {  
        int nb =0;
        for (int i=0; i<4; i++){
            if(relics[i] != 0){
                nb++;
            }
        }
        return nb;
    }
	public int[] getRelics(){ return this.relics; }
	public void setStucturepoints(int sttruct) { this.structurePoints = sttruct; }

	/* Methode */
	//verifie si le vaisseau possede cette relic ou pas
	public boolean hasRelic(int relic){
		boolean resul = false;
		for(int i=0; i<getNumberOfRelics(); i++){
			if(relic == relics[i])
				resul = true;
		}
		return resul;
	}
	
	//diminu les points de structure de ce vaisseau
	public void minusStructurePoint(int value) {
		this.structurePoints -= value;
		if(structurePoints < 0)
			structurePoints = 0;
	}

	//le vaisseau bouge a gauche donc modification de son orientation
	private void moveLeft()throws Exception { 
		if(super.getOrientation() == 1) 
			super.setOrientation(6);
		else if(super.getOrientation() == 2) 
				super.setOrientation(1);
			else if(super.getOrientation() == 3) 
					super.setOrientation(2);
				else if(super.getOrientation() == 4)
						super.setOrientation(3);
					else if(super.getOrientation() == 5)
							super.setOrientation(4);
						else if(super.getOrientation() == 6)
								super.setOrientation(5);
	}
	
	//le vaisseau bouge à droite donc modification de son orientation
	private void moveRight() throws Exception{  
        if(super.getOrientation() == 1) 
			super.setOrientation(2);
		else if(super.getOrientation() == 2) 
				super.setOrientation(3);
			else if(super.getOrientation() == 3) 
					super.setOrientation(4);
				else if(super.getOrientation() == 4)
						super.setOrientation(5);
					else if(super.getOrientation() == 5)
							super.setOrientation(6);
						else if(super.getOrientation() == 6)
								super.setOrientation(1);
    }
     
	//le vaisseau fait demi-tourd donc modification de son orientation
	private void turnAround()throws Exception { 
        if( super.getOrientation() == 1)
            super.setOrientation(4);
        else if( super.getOrientation() == 2)
            	super.setOrientation(5);
        	else if( super.getOrientation() == 3)
            	super.setOrientation(6);
        		else if( super.getOrientation() == 4)
            			super.setOrientation(1);
        			else if( super.getOrientation() == 5)
           					super.setOrientation(2);
        				else if( super.getOrientation() == 6)
            				super.setOrientation(3);
    }
	
	//ajouter des relics dans le tableau de relics
	public void addRelic(int relic) throws Exception{
		if( relic<1 || relic>4 ) throw new Exception("valeur de relic à ajouter < 1 ou > 4");
		if(!hasRelic(relic)) {
			this.relics[getNumberOfRelics()] = relic; 
		}
	}


	// procedure qui deplace un vaisseau selon les mouvements choisis par le joureur
	public void move(Cell[][] gameBoard, Movement[] movement)throws Exception {
		for(Movement m : movement) { //parcourir tous les mouvements for(Movement m : movement)
			if(m == Movement.TurnAround)
				turnAround();
			else if(m == null) 
				break;
			else {
				Position pos;
				if(m == Movement.Left) //changer l'orientation
					moveLeft();
				if(m == Movement.Right)
					moveRight();
				try {
					pos = super.getPosition().getForward(super.getOrientation());
					//ajouter dans la liste des vaiseaux (a l'exterieur du catch)
					gameBoard[pos.getY()][pos.getX()].addLSpaceShip(this); 
					//retiter de la liste ou il etait
					gameBoard[super.getPosition().getY()][super.getPosition().getX()].removeLSpaceShip(this);
					//changer sa position
					super.setPosition(pos);
				} catch (Exception e) {
					//le vaisseau se prend des degats et s'arrete 
					minusStructurePoint(2);
					break;
				}
			}
		}
    }

	@Override
	public String toString() {
		return "space_ship-" + this.color + "-" + this.getOrientation();
	}
}



	
