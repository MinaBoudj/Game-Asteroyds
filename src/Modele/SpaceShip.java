package Modele;
import java.util.*;

public class SpaceShip implements VisualObject{
	
	private int structurePoints;
	private Color color;
	
	
	/* Constructeur */
	SpaceShip(String imagePath, int orientation, Position pos, Color color){
		super(imagePath, orientation, pos);
		this.structurePoints = 6;
		this.color = color;
	}
	
	/* Getteur and setteur */
	public Color getColor() { return this.color; }
	public int getStructurePoints() { return this.structurePoints; }
	
	public void setStucturepoints(int sttruct) { this.structurePoints = sttruct; }
	
	private Position moveLeft() { 
		Position pos;
		if(super.orientation == 1) {
			if(super.getX() == 0 ) { //ne peut pas bouger il est au bord du plateau
				return super.positon;
			}else {
				pos.x = super.getX() - 1;
				pos.y = super.getY();
			}
		}
		if(super.orientation == 2) {
			if(super.getY() == 16 ) {
				return super.positon;
			}
            if(super.getY()%2 == 0){
                    pos.x = super.getX();
				    pos.y = super.getY() + 1;
            }else{
                    pos.x = super.getX() + 1;
				    pos.y = super.getY() + 1;
            }
        }
		if(super.orientation == 3) {
			if(super.getX() == 0 ) {
				return super.positon;
			}else {
				pos.x = super.getX() - 1;
				pos.y = super.getY();
			}
		}if(super.orientation == 4) {
			if(super.getX() == 0 || (super.getX() == 13 && super.getY()%2 != 0) ) {
				return super.positon;
			}else {
                if(super.getY()%2 == 0){
                    pos.x = super.getX();
				    pos.y = super.getY() - 1;
                }else{
                    pos.x = super.getX()+ 1;
				    pos.y = super.getY() - 1;
                }
			}
		}if(super.orientation == 5) {
			if(super.getX() == 0 || (super.getX() == 0 && super.getY()%2 == 0) ) {
				return super.positon;
			}else {
                if(super.getY()%2 == 0){
                    pos.x = super.getX() - 1;
				    pos.y = super.getY() - 1;
                }else{
                    pos.x = super.getX();
				    pos.y = super.getY() - 1;
                }
            }
		}
		if(super.orientation == 6) {
			if(super.getX() == 13 ) { //ne peut pas bouger
				return super.positon;
			}else {
                pos.x = super.getX() + 1;
				pos.y = super.getY();
            }
		}
		return pos;
	}
	
	private Position moveRight() {  //recuperer la position à droite de cette case
        Position pos;
		if(super.orientation == 1) {
			if(super.getY() == 0 || (super.getX() == 13 && super.getY()%2 != 0)) { //ne peut pas bouger il est au bord du plateau
				return super.positon;
			}
            if(super.getY()%2 == 0){
                    pos.x = super.getX();
				    pos.y = super.getY() - 1;
            }else{
                    pos.x = super.getX() + 1 ;
				    pos.y = super.getY() - 1;
            }
		}
		if(super.orientation == 2) {
			if(super.getX() == 0 ) { //premiere colone
				return super.positon;
			}else{
                    pos.x = super.getX() - 1;
				    pos.y = super.getY();
            }
        }
		if(super.orientation == 3) {
			if(super.getY() == 16 || (super.getX() == 13 && super.getY()%2 != 0) ) { //dernier ligne + deriner colone(pas tous)
				return super.positon;
			}
            if(super.getY()%2 == 0){
                    pos.x = super.getX();
				    pos.y = super.getY() + 1;
            }else{
                    pos.x = super.getX() + 1 ;
				    pos.y = super.getY() + 1;
            }
		}
        if(super.orientation == 4) {
			if(super.getY() == 0 || (super.getX() == 0 && super.getY()%2 == 0) ) { //premier ligne + premier colone (pas toute)
				return super.positon;
			}
            if(super.getY()%2 == 0){
                    pos.x = super.getX() - 1;
				    pos.y = super.getY() - 1;
            }else{
                    pos.x = super.getX();
				    pos.y = super.getY() - 1;
            }
		}
		if(super.orientation == 5) {
			if(super.getY() == 13) { //ne ppeut pas bouger dernier colonne
				return super.positon;
			}else {
                pos.x = super.getX() + 1;
				pos.y = super.getY();
            }
		}
		if(super.orientation == 6) {
			if(super.getY() == 16 ) { //ne peut pas bouger derniere ligne
				return super.positon;
			}else {
                if(super.getY()%2 == 0){
                    pos.x = super.getX() - 1;
				    pos.y = super.getY() + 1;
                }else{
                    pos.x = super.getX();
				    pos.y = super.getY() + 1;
                }
            }
		}
		return pos;
    }

	private Position moveForward() { //recuperer la position tous droit 
        Position pos;
		if(super.orientation == 1) {
			if(super.getX() == 0 || super.getY() == 0) { //ne peut pas bouger il est au bord du plateau
				return super.positon;
			}
            if(super.getY()%2 == 0){
                    pos.x = super.getX() - 1;
				    pos.y = super.getY() - 1;
            }else{
                    pos.x = super.getX();
				    pos.y = super.getY() - 1;
            }
		}
		if(super.orientation == 2) {
			if(super.getY() == 16 ||(super.getX() == 0 && super.getY()%2 == 0)) { //derniere ligne
				return super.positon;
			}
            if(super.getY()%2 == 0){
                    pos.x = super.getX() - 1;
				    pos.y = super.getY() + 1;
            }else{
                    pos.x = super.getX();
				    pos.y = super.getY() + 1;
            }
        }	
		if(super.orientation == 3) {
			if(super.getX() == 13 ) { //deriere colone
				return super.positon;
			}else {
				pos.x = super.getX() + 1;
				pos.y = super.getY();
			}
		}
        if(super.orientation == 4) {
			if(super.getX() == 0 ) { // premiere colonne
				return super.positon;
			}else {
                pos.x = super.getX() - 1;
				pos.y = super.getY();
			}
		}
        if(super.orientation == 5) {
			if(super.getY() == 0 || (super.getX() == 13 && super.getY()%2 != 0) ) {
				return super.positon;
			}else {
                if(super.getY()%2 == 0){
                    pos.x = super.getX();
				    pos.y = super.getY() - 1;
                }else{
                    pos.x = super.getX() + 1;
				    pos.y = super.getY() - 1;
                }
            }
		}
		if(super.orientation == 6) {
			if(super.getY() == 16 || (super.getX() == 13 && super.getY()%2 != 0)) { //ne peut pas bouger
				return super.positon;
			}
            if(super.getY()%2 == 0){
                    pos.x = super.getX();
				    pos.y = super.getY() + 1;
                }else{
                    pos.x = super.getX() + 1;
				    pos.y = super.getY() + 1;
                }
		}
		return pos;
    }

	private void turnAround() { 
        if( super.getOrientation() == 1)
            super.setOrientation(6);
        if( super.getOrientation() == 2)
            super.setOrientation(5);
        if( super.getOrientation() == 3)
            super.setOrientation(4);
        if( super.getOrientation() == 4)
            super.setOrientation(3);
        if( super.getOrientation() == 5)
            super.setOrientation(2);
        if( super.getOrientation() == 6)
            super.setOrientation(1);
    }
	
	public void move(ArrayList<ArrayList<Cell>> gameBoard, ArrayList<Movement> movement) {
		for(int i=0; i<movement.size(); i++) { //parcourir tous les mouvements 
			Position pos;
			if(movement.get(i) == Movement.Left) {
					pos = moveLeft();
                    //verifier que la case peut contenir des SpaceShips si oui l'ajouter dans la liste sinon le spaceShip se deplace pas
					if(gameBoard[pos.getX()][pos.getY()].canContainSpaceShips == true){
                        gameBoard[pos.getX()][pos.getY()].spaceShips.add(this);
                    }
				}
			if(movement.get(i) == Movement.Right) {
				pos = moveRight();
                //verifier que la case peut contenir des SpaceShips si oui l'ajouter dans la liste sinon le spaceShip se deplace pas
				if(gameBoard[pos.getX()][pos.getY()].canContainSpaceShips == true){
                    gameBoard[pos.getX()][pos.getY()].spaceShips.add(this);
                }
			}
			if(movement.get(i) == Movement.Forward) {
				pos = moveForward();
                //verifier que la case peut contenir des SpaceShips si oui l'ajouter dans la liste sinon le spaceShip se deplace pas
				if(gameBoard[pos.getX()][pos.getY()].canContainSpaceShips == true){
                    gameBoard[pos.getX()][pos.getY()].spaceShips.add(this);
                }
			}
			if(movement.get(i) == Movement.TurnAround) {
				TurnAround();
            }
		}
    }
    	
}



	
