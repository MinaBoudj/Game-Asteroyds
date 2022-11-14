package Modele;
import java.util.*;
/***
 * @autor amina
*/

import javax.crypto.spec.PSource;

public class SpaceShip extends VisualObject{
	
	private int structurePoints;
	private Color color;
    private int[] relics;
	
	
	/* Constructeur */
	public SpaceShip(String image,int orientation, Position pos, Color color){
        super(image,orientation, pos);// corriger demain avec juba
		this.structurePoints = 6;
        this.relics = new int[]{0,0,0,0};    
        this.color = color;
        if(color == Color.Red)
            super.setImagePath("rouge");
        else if(color ==Color.Blue)
                super.setImagePath("Bleu");
            else if(color == Color.Yellow)
                super.setImagePath("jaune");
                else if(color == Color.Green)
                    super.setImagePath("gris");
                    else if(color == Color.Purple)
                        super.setImagePath("violet");
                        else if(color == Color.Orange)
                            super.setImagePath("orange");
	}
	
	/* Getteur and setteur */
	public Color getColor() { return this.color; }
	public int getStructurePoints() { return this.structurePoints; }
    public int getNumberOfRelics() {  //nombre de relics que possede le vaisseau
        int nb =0;
        for (int i=0; i<=4; i++){
            if(relics[i] != 0){
                nb++;
            }
        }
        return nb;
    }

	public void setStucturepoints(int sttruct) { this.structurePoints = sttruct; }
	
	private Position moveLeft() { 
		Position pos = super.position;
		if(super.orientation == 1) {
			if(super.position.getX() == 0 ) { //ne peut pas bouger il est au bord du plateau
				return pos;
			}else {
				pos.x = super.position.getX() - 1;
				pos.y = super.position.getY();
			}
		}
		if(super.orientation == 2) {
			if(super.position.getY() == 16 ) {
				return pos;
			}
            if(super.position.getY()%2 == 0){
                    pos.x = super.position.getX();
				    pos.y = super.position.getY() + 1;
            }else{
                    pos.x = super.position.getX() + 1;
				    pos.y = super.position.getY() + 1;
            }
        }
		if(super.orientation == 3) {
			if(super.position.getX() == 0 ) {
				return pos;
			}else {
				pos.x = super.position.getX() - 1;
				pos.y = super.position.getY();
			}
		}if(super.orientation == 4) {
			if(super.position.getX() == 0 || (super.position.getX() == 13 && super.position.getY()%2 != 0) ) {
				return pos;
			}else {
                if(super.position.getY()%2 == 0){
                    pos.x = super.position.getX();
				    pos.y = super.position.getY() - 1;
                }else{
                    pos.x = super.position.getX()+ 1;
				    pos.y = super.position.getY() - 1;
                }
			}
		}if(super.orientation == 5) {
			if(super.position.getX() == 0 || (super.position.getX() == 0 && super.position.getY()%2 == 0) ) {
				return pos;
			}else {
                if(super.position.getY()%2 == 0){
                    pos.x = super.position.getX() - 1;
				    pos.y = super.position.getY() - 1;
                }else{
                    pos.x = super.position.getX();
				    pos.y = super.position.getY() - 1;
                }
            }
		}
		if(super.orientation == 6) {
			if(super.position.getX() == 13 ) { //ne peut pas bouger
				return pos;
			}else {
                pos.x = super.position.getX() + 1;
				pos.y = super.position.getY();
            }
		}
		return pos;
	}
	
	private Position moveRight() {  //recuperer la position à droite de cette case
        Position pos = super.position;
		if(super.orientation == 1) {
			if(super.position.getY() == 0 || (super.position.getX() == 13 && super.position.getY()%2 != 0)) { //ne peut pas bouger il est au bord du plateau
				return pos;
			}
            if(super.position.getY()%2 == 0){
                    pos.x = super.position.getX();
				    pos.y = super.position.getY() - 1;
            }else{
                    pos.x = super.position.getX() + 1 ;
				    pos.y = super.position.getY() - 1;
            }
		}
		if(super.orientation == 2) {
			if(super.position.getX() == 0 ) { //premiere colone
				return pos;
			}else{
                    pos.x = super.position.getX() - 1;
				    pos.y = super.position.getY();
            }
        }
		if(super.orientation == 3) {
			if(super.position.getY() == 16 || (super.position.getX() == 13 && super.position.getY()%2 != 0) ) { //dernier ligne + deriner colone(pas tous)
				return pos;
			}
            if(super.position.getY()%2 == 0){
                    pos.x = super.position.getX();
				    pos.y = super.position.getY() + 1;
            }else{
                    pos.x = super.position.getX() + 1 ;
				    pos.y = super.position.getY() + 1;
            }
		}
        if(super.orientation == 4) {
			if(super.position.getY() == 0 || (super.position.getX() == 0 && super.position.getY()%2 == 0) ) { //premier ligne + premier colone (pas toute)
				return pos;
			}
            if(super.position.getY()%2 == 0){
                    pos.x = super.position.getX() - 1;
				    pos.y = super.position.getY() - 1;
            }else{
                    pos.x = super.position.getX();
				    pos.y = super.position.getY() - 1;
            }
		}
		if(super.orientation == 5) {
			if(super.position.getY() == 13) { //ne ppeut pas bouger dernier colonne
				return pos;
			}else {
                pos.x = super.position.getX() + 1;
				pos.y = super.position.getY();
            }
		}
		if(super.orientation == 6) {
			if(super.position.getY() == 16 ) { //ne peut pas bouger derniere ligne
				return pos;
			}else {
                if(super.position.getY()%2 == 0){
                    pos.x = super.position.getX() - 1;
				    pos.y = super.position.getY() + 1;
                }else{
                    pos.x = super.position.getX();
				    pos.y = super.position.getY() + 1;
                }
            }
		}
		return pos;
    }

	private Position moveForward() { //recuperer la position tous droit 
        Position pos = super.position;
		if(super.orientation == 1) {
			if(super.position.getX() == 0 || super.position.getY() == 0) { //ne peut pas bouger il est au bord du plateau
				return pos;
			}
            if(super.position.getY()%2 == 0){
                    pos.x = super.position.getX() - 1;
				    pos.y = super.position.getY() - 1;
            }else{
                    pos.x = super.position.getX();
				    pos.y = super.position.getY() - 1;
            }
		}
		if(super.orientation == 2) {
			if(super.position.getY() == 16 ||(super.position.getX() == 0 && super.position.getY()%2 == 0)) { //derniere ligne
				return pos;
			}
            if(super.position.getY()%2 == 0){
                    pos.x = super.position.getX() - 1;
				    pos.y = super.position.getY() + 1;
            }else{
                    pos.x = super.position.getX();
				    pos.y = super.position.getY() + 1;
            }
        }	
		if(super.orientation == 3) {
			if(super.position.getX() == 13 ) { //deriere colone
				return pos;
			}else {
				pos.x = super.position.getX() + 1;
				pos.y = super.position.getY();
			}
		}
        if(super.orientation == 4) {
			if(super.position.getX() == 0 ) { // premiere colonne
				return pos;
			}else {
                pos.x = super.position.getX() - 1;
				pos.y = super.position.getY();
			}
		}
        if(super.orientation == 5) {
			if(super.position.getY() == 0 || (super.position.getX() == 13 && super.position.getY()%2 != 0) ) {
				return pos;
			}else {
                if(super.position.getY()%2 == 0){
                    pos.x = super.position.getX();
				    pos.y = super.position.getY() - 1;
                }else{
                    pos.x = super.position.getX() + 1;
				    pos.y = super.position.getY() - 1;
                }
            }
		}
		if(super.orientation == 6) {
			if(super.position.getY() == 16 || (super.position.getX() == 13 && super.position.getY()%2 != 0)) { //ne peut pas bouger
				return pos;
			}
            if(super.position.getY()%2 == 0){
                    pos.x = super.position.getX();
				    pos.y = super.position.getY() + 1;
                }else{
                    pos.x = super.position.getX() + 1;
				    pos.y = super.position.getY() + 1;
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
	
	//ajouter des relics dans le tableau de relics
	public void addRelic(int relic){
		for(int i=0; i< relics.length; i++){
			if(this.relics[i] != 0 ){
				relics[i] = relic;
			}
		}
	}

	// procedure qui deplace un vaisseau selon les mouvements choisis par le joureur
	public void move(Cell[][] gameBoard, Movement[] movement) {
		for(int i=0; i<movement.length; i++) { //parcourir tous les mouvements 
			Position pos;
			if(movement[i] == Movement.Left) {
					pos = moveLeft();
                    //verifier que la case peut contenir des SpaceShips si oui l'ajouter dans la liste sinon le spaceShip se deplace pas
					if(gameBoard[pos.getX()][pos.getY()].getCanContainSpaceShips() == true){
						if(pos.x == this.position.getX() && pos.y == this.position.getY()){ //cas ou il se deplace pas 
								//le vaisseau se prend des degats
								this.structurePoints --;
						}else{
							gameBoard[pos.getX()][pos.getY()].addLSpaceShip(this); //ajouter dans la liste des vaiseaux
                        	//si c un portal ajouter relic
							if(gameBoard[pos.getX()][pos.getY()] instanceof RedPortal || gameBoard[pos.getX()][pos.getY()] instanceof WhitePortal){
								//addRelic(gameBoard[pos.getX()][pos.getY()].getRelic());
							}
							//retiter de la liste ou il etait
							gameBoard[super.getPosition().x][super.getPosition().y].removeLSpaceShip(this);
                        	//changer sa position
							this.position = pos;
						}
                    }else{ //cas ou  dans la position ou il va se deplacer ya un objet qui ne peut pas contenir de vaisseau
						this.structurePoints --;
					}
				}
			if(movement[i] == Movement.Right) {
				pos = moveRight();
                 //verifier que la case peut contenir des SpaceShips si oui l'ajouter dans la liste sinon le spaceShip se deplace pas
					if(gameBoard[pos.getX()][pos.getY()].getCanContainSpaceShips() == true){
						if(pos.x == this.position.getX() && pos.y == this.position.getY()){ //cas ou il se deplace pas 
								//le vaisseau se prend des degats
								this.structurePoints --;
						}else{
							gameBoard[pos.getX()][pos.getY()].addLSpaceShip(this); //ajouter dans la liste des vaiseaux
                        	//si c un portal ajouter relic
							if(gameBoard[pos.getX()][pos.getY()] instanceof RedPortal || gameBoard[pos.getX()][pos.getY()] instanceof WhitePortal){
								//addRelic(gameBoard[pos.getX()][pos.getY()].getRelic());
							}
							//retiter de la liste ou il etait
							gameBoard[super.getPosition().x][super.getPosition().y].removeLSpaceShip(this);
                        	//changer sa position
							this.position = pos;
						}
                    }else{ //cas ou  dans la position ou il va se deplacer ya un objet qui ne peut pas contenir de vaisseau
						this.structurePoints --;
					}
			}
			if(movement[i] == Movement.Forward) {
				pos = moveForward();
                 //verifier que la case peut contenir des SpaceShips si oui l'ajouter dans la liste sinon le spaceShip se deplace pas
					if(gameBoard[pos.getX()][pos.getY()].getCanContainSpaceShips() == true){
						if(pos.x == this.position.getX() && pos.y == this.position.getY()){ //cas ou il se deplace pas 
								//le vaisseau se prend des degats
								this.structurePoints --;
						}else{
							gameBoard[pos.getX()][pos.getY()].addLSpaceShip(this); //ajouter dans la liste des vaiseaux
                        	//si c un portal ajouter relic
							if(gameBoard[pos.getX()][pos.getY()] instanceof RedPortal || gameBoard[pos.getX()][pos.getY()] instanceof WhitePortal){
								//addRelic(gameBoard[pos.getX()][pos.getY()].getRelic());
							}
							//retiter de la liste ou il etait
							gameBoard[super.getPosition().x][super.getPosition().y].removeLSpaceShip(this);
                        	//changer sa position
							this.position = pos;
						}
                    }else{ //cas ou  dans la position ou il va se deplacer ya un objet qui ne peut pas contenir de vaisseau
						this.structurePoints --;
					}
			}
			if(movement[i] == Movement.TurnAround) {
				turnAround();
            }
            if(movement[i] == null) {
				break;
            }
		}
    }

}



	
