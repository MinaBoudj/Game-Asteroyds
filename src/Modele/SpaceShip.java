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
			if(super.getX() == 0 ) {
				return super.positon;
			}else {
				pos.x = super.getX() - 1;
				pos.y = super.getY();
			}
		}
		if(super.orientation == 2) {
			if(super.getY() == 16 ) {
				return super.positon;
			}else {
				pos.x = super.getX() - 1;
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
			if(super.getX() == 0 ) {
				return super.positon;
			}else {
				pos.x = super.getX() - 1;
				pos.y = super.getY();
			}
		}if(super.orientation == 5) {
			if(super.getX() == 0 ) {
				return super.positon;
			}else {
				pos.x = super.getX() - 1;
				pos.y = super.getY();
			}
		}
		if(super.orientation == 6) {
			if(super.getX() == 0 ) {
				return super.positon;
			}else {
				pos.x = super.getX() - 1;
				pos.y = super.getY();
			}
		}
		return pos;
	}
	
	private Position moveRight() { }
	private Position moveForward() { }
	private Position turnAround() { }
	
	public void move(ArrayList<ArrayList<Cell>> gameBoard, ArrayList<Movement> movement) {
		for(int i=0; i<movement.size(); i++) {
			Position pos;
			if(movement.get(i) == Movement.Left) {
					pos = moveLeft();
					gameBoard[pos.getX()][pos.getY()].spaceShips.add(this.SpaceShip);
				}
			if(movement.get(i) == Movement.Right) {
				pos = moveRight();
				gameBoard[pos.getX()][pos.getY()].spaceShips.add(this.SpaceShip);

			}
			if(movement.get(i) == Movement.Forward) {
				pos = moveForward();
				gameBoard[pos.getX()][pos.getY()].spaceShips.add(this.SpaceShip);

			}
			if(movement.get(i) == Movement.TurnAround) {
				pos = TurnAround();
				gameBoard[pos.getX()][pos.getY()].spaceShips.add(this.SpaceShip);
            }
		}	
	}
}
	
